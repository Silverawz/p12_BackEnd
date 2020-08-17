package com.deroussenicolas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.UserRepository;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.service.UserService;
/**
 * implements UserService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("UserServiceImplementation")
@Transactional
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;

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
	public void delete(Long id) {
		User user = this.findOneUserById(id);
		userRepository.delete(user);	
	}
}
