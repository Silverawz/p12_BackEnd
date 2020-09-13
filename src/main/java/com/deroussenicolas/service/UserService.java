package com.deroussenicolas.service;
import java.util.List;

import com.deroussenicolas.entities.AuthRequest;
import com.deroussenicolas.entities.RegisterRequest;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.exception.InvalidUserException;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface UserService {
	public void save(User user);

	public List<User> findAll();
	
	public User findOneUserById(Long id);

	public User findByEmail(String username);
	
	public void verificationAuthRequestIsValid(AuthRequest authRequest) throws InvalidUserException;

	public void verificationRegisterRequestIsValid(RegisterRequest registerRequest) throws InvalidUserException;

}
