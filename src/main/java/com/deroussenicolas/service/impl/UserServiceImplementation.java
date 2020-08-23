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
public class UserServiceImplementation implements UserDetailsService, UserService {
	
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

}
