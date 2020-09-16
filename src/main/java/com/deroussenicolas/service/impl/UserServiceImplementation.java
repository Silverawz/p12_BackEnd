package com.deroussenicolas.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.UserRepository;
import com.deroussenicolas.entities.AuthRequest;
import com.deroussenicolas.entities.RegisterRequest;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.exception.InvalidUserException;
import com.deroussenicolas.service.UserService;
/**
 * implements UserService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("UserServiceImplementation")
@Transactional
public class UserServiceImplementation implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepository;
	private final String AUTHREQUEST_USERNAME = "Username in auth request";
	private final String AUTHREQUEST_PASSWORD = "Password in auth request";	
	private final String REGISTER_EMAIL = "Email in register request";
	private final String REGISTER_FIRSTNAME = "Firstname in register request";
	private final String REGISTER_LASTNAME = "Lastname in register request";
	private final String REGISTER_PASSWORD = "Password in register request";
	private final String IS_NULL = "is null";
	private final String WRONG_SIZE = "size is incorrect";
	
	
	public UserServiceImplementation(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);		
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOneUserById(Long id) {
		return userRepository.findOneUserById(id);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getDescription()));
		});
		return authorities;
	}

	@Override
	public User findByEmail(String username) {
		return userRepository.findByEmail(username);
	}

	/**
	 * check if authRequest is valid (not null and size not too small nor too big)
	 * @return void
	 * @exception InvalidUserException
	 */
	@Override
	public void verificationAuthRequestIsValid(AuthRequest authRequest) throws InvalidUserException {
		if(authRequest == null) {
			throw new InvalidUserException("authRequest "+IS_NULL);
		}
		if(authRequest.getUsername() == null) {
			throw new InvalidUserException(AUTHREQUEST_USERNAME+" "+IS_NULL);
		} else if (authRequest.getPassword() == null) {
			throw new InvalidUserException(AUTHREQUEST_PASSWORD+" "+IS_NULL);	
		}
		if(authRequest.getUsername().length() < 5 || authRequest.getUsername().length() > 70) {
			throw new InvalidUserException(AUTHREQUEST_USERNAME+" "+WRONG_SIZE);
		} else if(authRequest.getPassword().length() < 5 || authRequest.getPassword().length() > 255) {
			throw new InvalidUserException(AUTHREQUEST_PASSWORD+" "+WRONG_SIZE);
		}
	}

	/**
	 * check if RegisterRequest is valid (not null and size not too small nor too big)
	 * @return void
	 * @exception InvalidUserException
	 */
	@Override
	public void verificationRegisterRequestIsValid(RegisterRequest registerRequest) throws InvalidUserException {
		if(registerRequest == null) {
			throw new InvalidUserException("RegisterRequest "+IS_NULL);
		} 
		if(registerRequest.getFirstname() == null) {
			throw new InvalidUserException(REGISTER_FIRSTNAME+" "+IS_NULL);
		} 
		int sizeFirstname = registerRequest.getFirstname().length();
		if (sizeFirstname < 3 || sizeFirstname > 70) {
			throw new InvalidUserException(REGISTER_FIRSTNAME+" "+WRONG_SIZE);
		}
		if(registerRequest.getLastname() == null) {
			throw new InvalidUserException(REGISTER_LASTNAME+" "+IS_NULL);
		}
		int sizeLastname = registerRequest.getLastname().length();
		if (sizeLastname < 3 || sizeLastname > 70) {
			throw new InvalidUserException(REGISTER_LASTNAME+" "+WRONG_SIZE);
		}
		if(registerRequest.getEmail() == null) {
			throw new InvalidUserException(REGISTER_EMAIL+" "+IS_NULL);
		} 
		int sizeEmail = registerRequest.getEmail().length();
		if (sizeEmail < 5 || sizeEmail > 70) {
			throw new InvalidUserException(REGISTER_EMAIL+" "+WRONG_SIZE);
		} 
		if(registerRequest.getPassword() == null) {
			throw new InvalidUserException(REGISTER_PASSWORD+" "+IS_NULL);
		} 
		int sizePassword = registerRequest.getPassword().length();
		if (sizePassword < 5 || sizePassword > 255) {
			throw new InvalidUserException(REGISTER_PASSWORD+" "+WRONG_SIZE);
		} 				
	}

}
