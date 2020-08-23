package com.deroussenicolas.controller;

import java.net.URI;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.function.Consumer;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deroussenicolas.configuration.JwtUtil;
import com.deroussenicolas.entities.AuthRequest;
import com.deroussenicolas.entities.JwtResponse;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.service.UserService;
import com.deroussenicolas.service.impl.UserServiceImplementation;

/**
 * 
 * 
 * @author deroussen nicolas
 * 
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class UserControllerRest {

	private final UserService userService;
	private final UserServiceImplementation userServiceImpl;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerRest.class);

	@Autowired
	public UserControllerRest(UserService userService, UserServiceImplementation userServiceImpl ) {
		this.userService = userService;
		this.userServiceImpl = userServiceImpl;
	}

	/***
	 *  
	 * 
	 */

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/signin")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception  {
		System.err.println(authRequest.getUsername()+"+"+authRequest.getPassword());
		try {
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		authRequest.getUsername(),
	                		authRequest.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        // return new ResponseEntity<>(jwtUtil.generateToken(authentication), HttpStatus.OK) ;     //jwtUtil.generateToken(authentication), authRequest.getEmail()
	        User user = userService.findByEmail(authRequest.getUsername());
	        return ResponseEntity.ok(new JwtResponse(
	        		jwtUtil.generateToken(authentication), 
	        		user.getId_user(),
	        		user.getFirstname(),
	        		user.getEmail(),
	        		user.getRoles()));
		} catch (Exception e) {
			LOGGER.error("Bad credentials" + e);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
	}
	
	/*
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception  {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authRequest.getEmail(), authRequest.getPassword()));		        
			return jwtUtil.generateToken(authRequest.getEmail());
		} catch (Exception e) {
			LOGGER.error("Bad credentials" + e);
		}
		return "Bad credentials";
	}
	
	  @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	    public ResponseEntity register(@RequestBody LoginUser loginUser) throws AuthenticationException {

	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginUser.getUsername(),
	                        loginUser.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        final String token = jwtUtil.generateToken(authentication);
	        return ResponseEntity.ok(new AuthToken(token));
	    }
	  
	 */ 
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/test")
	public String welcome() {
		return "Welcome to javatechie !!";
	}

	/***
	 * 
	 * 
	 */

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid User user) {
		try {
			if (user.getEmail().equals("aaa@aol.fr") && user.getPassword().equals("12345")) {
				System.out.println("ok");
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error("exception = " + e);
		}
		System.out.println("pas ok");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/user/create")
	public ResponseEntity<Object> creatingUser(@RequestBody @Valid User user) {
		try {
			userService.save(user);
			LOGGER.info("New user created in database = " + user.toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error during the user creation in database = " + user.toString());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during the user creation in database", e);
		}
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
		try {
			return userService.findOneUserById(Long.valueOf(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error", e);
		}
	}

	@PutMapping("/user/update")
	public ResponseEntity<Object> updatingUser(@RequestBody @Valid User user) {
		try {
			User userFromDB = userService.findOneUserById(user.getId_user());
			userFromDB.setEmail(user.getEmail());
			userFromDB.setFirstname(user.getFirstname());
			userFromDB.setLastname(user.getLastname());
			userFromDB.setPassword(user.getPassword());
			userFromDB.setArticleList(user.getArticleList());
			userFromDB.setTopicList(user.getTopicList());
			userFromDB.setRoles(user.getRoles());
			userFromDB.setPostList(user.getPostList());
			userService.save(userFromDB);
			LOGGER.info("User updated in database = " + userFromDB.toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error during the user creation in database = " + user.toString());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during the user update in database", e);
		}
	}
}
