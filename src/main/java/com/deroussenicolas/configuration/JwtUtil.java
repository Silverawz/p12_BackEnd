package com.deroussenicolas.configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	@Value("${secret.jwt.string}")
	private String secret;
	private final long ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 60 * 10;
	private final String AUTHORITIES_KEY = "roles";
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
	
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); 
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) { 	
    		return Jwts.parser()
    	    		.setSigningKey(secret)
    	    		.parseClaimsJws(token)
    	    		.getBody(); 
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    
    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) throws Exception {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(secret);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = (Claims) claimsJws.getBody();
        Collection <SimpleGrantedAuthority> authorities = null;
        try {
            authorities =
                    Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), authorities);
		} catch (Exception e) {
			LOGGER.error("Roles list is empty.");
		}
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), authorities);
    }
    
}
