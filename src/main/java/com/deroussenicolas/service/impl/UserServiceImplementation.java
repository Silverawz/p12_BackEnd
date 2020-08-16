package com.deroussenicolas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.deroussenicolas.dao.UserRepository;
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
}
