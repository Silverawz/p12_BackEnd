package com.deroussenicolas.configuration;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.deroussenicolas.service.impl.UserServiceImplementation;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserServiceImplementation userServiceImplementation;
	private final String HEADER_STRING = "Authorization";
	private final String TOKEN_PREFIX = "Bearer ";
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
                username = jwtUtil.extractUsername(authToken);
            } catch (IllegalArgumentException e) {
            	LOGGER.error("An error occured during getting username from token");
            } catch (ExpiredJwtException e) {
            	LOGGER.error("The token is expired and not valid anymore");
            } catch(SignatureException e){
            	LOGGER.error("Authentication Failed. Username or Password not valid.");
            } catch(UnsupportedJwtException e) {
            	LOGGER.error("Authentication Failed. Unsigned Claims JWTs are not supported.");
            } catch (RuntimeException e) {
            	LOGGER.error("RuntimeException.");
            }
        } else {
            //logger.warn("couldn't find bearer string, will ignore the header");
        }   
        try {
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userServiceImplementation.loadUserByUsername(username);
                if (jwtUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = 
                    		jwtUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    LOGGER.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
		} catch (Exception e) {			
			LOGGER.error("An exception occured.");
		}
        chain.doFilter(req, res);
    }

}
