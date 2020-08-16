package com.deroussenicolas;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.deroussenicolas.dao.UserRepository;
import com.deroussenicolas.entities.User;

@SpringBootTest
class AssociationsSportivesApplicationTests {

	@Autowired
	private UserRepository userR;
	private User user;
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
	@Order(1)
	public void creatingUserTestAndDeletingIt() throws Exception {
		user = new User();
		user.setFirstname("Firstname");
		user.setLastname("Lastname");
		user.setEmail("aaabbbcccdddeee@aol.fr");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("123456");
		user.setPassword(hashedPassword);
		userR.save(user);
		Assert.assertEquals(user.getEmail(), userR.findAll().get(1).getEmail());
		userR.delete(user);
	}
	
}
