package com.deroussenicolas.service;
import java.util.List;

import com.deroussenicolas.entities.User;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface UserService {
	public void save(User user);

	public List<User> findAll();
	
	public User findOneUserById(Long id);
	
	public void delete(Long id);
}
