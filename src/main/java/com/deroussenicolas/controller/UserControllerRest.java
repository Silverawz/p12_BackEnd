package com.deroussenicolas.controller;

import java.util.HashSet;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deroussenicolas.exception.InvalidUserException;
import com.deroussenicolas.configuration.JwtUtil;
import com.deroussenicolas.entities.AuthRequest;
import com.deroussenicolas.entities.JwtResponse;
import com.deroussenicolas.entities.RegisterRequest;
import com.deroussenicolas.entities.Role;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.service.RoleService;
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
	private final RoleService roleService;
	private final UserServiceImplementation userServiceImpl;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerRest.class);
	private Set<Role> roleSet;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	public UserControllerRest(UserService userService, UserServiceImplementation userServiceImpl, RoleService roleService ) {
		this.userService = userService;
		this.userServiceImpl = userServiceImpl;
		this.roleService = roleService;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody AuthRequest authRequest) throws InvalidUserException  {
		try {
			userService.verificationAuthRequestIsValid(authRequest);
		} catch (InvalidUserException e) {
			LOGGER.error("User informations are incorrect.");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User informations are incorrect.");
		}catch (Exception e) {
			LOGGER.error("Error occured while authentication : " + e);
		}		 
		User user = userService.findByEmail(authRequest.getUsername());
		if(user == null) {
			LOGGER.error("User is null.");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not exists.");
		} else if(!user.isActive()) {
			LOGGER.error("User is inactive | For email : " + user.getEmail());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not active.");
		}
		try {
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		authRequest.getUsername(),
	                		authRequest.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        return ResponseEntity.ok(new JwtResponse(
	        		jwtUtil.generateToken(authentication), 
	        		user.getId_user(),
	        		user.getFirstname(),
	        		user.getEmail(),
	        		user.getRoles()));
		} catch (Exception e) {
			LOGGER.error("Bad credentials" + e);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("An error has occured with the request.");
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody RegisterRequest registerRequest) throws InvalidUserException  {
		try {
			if(userService.findByEmail(registerRequest.getEmail()) == null) {
				userService.verificationRegisterRequestIsValid(registerRequest);
				String newPasswordEncoded = encoder.encode(registerRequest.getPassword());
				roleSet = new HashSet<>();
				roleSet.add(roleService.findSpecificRole("USER"));
				User user = new User(registerRequest.getFirstname(), registerRequest.getLastname(), 
						registerRequest.getEmail(), newPasswordEncoded , true, roleSet);
				userService.save(user);
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email already exists!");
			}
		} catch (InvalidUserException e) {
			LOGGER.error("User informations are incorrect.");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User informations are incorrect.");
		} catch (Exception e) {
			LOGGER.error("Error occured while authentication : " + e);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error");	
	}
	
}
