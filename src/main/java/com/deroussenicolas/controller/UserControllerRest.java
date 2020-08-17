package com.deroussenicolas.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deroussenicolas.entities.User;
import com.deroussenicolas.service.UserService;
/**
 * 
 * 
 * @author deroussen nicolas
 * 
 */
@RestController
public class UserControllerRest {
	
	private final UserService userService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerRest.class);
	
	@Autowired
	public UserControllerRest(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/user/create")
	public ResponseEntity<Object> creatingUser(@RequestBody @Valid User user) {
		try {
			userService.save(user);
			LOGGER.info("New user created in database = " + user.toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error during the user creation in database = " + user.toString());
			throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error during the user creation in database", e
            );
		}
	}
	
	@GetMapping("/user/get")
	public List<User> readingUser() {
		try {
			return userService.findAll();
		} catch (Exception e) {
            throw new ResponseStatusException(    		
                    HttpStatus.BAD_REQUEST, "Error", e
            );
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
			throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error during the user update in database", e
            );
		}
	}
	
	@DeleteMapping("/user/delete/{id}")
	public void deletingUser(@PathVariable("id") Long id) {
		try {
			User user = userService.findOneUserById(id);
			userService.delete(id);
			LOGGER.info("User sucessfully delete in database = " + user.toString());
		} catch (Exception e) {
			LOGGER.error("Error during the deletion in database");
			throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error during the user deletion in database", e
            );
		}
	}
	
	
}
