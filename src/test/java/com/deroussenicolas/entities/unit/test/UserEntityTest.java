package com.deroussenicolas.entities.unit.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.entities.Role;
import com.deroussenicolas.entities.User;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityTest {

	@Test
	public void user_constructor() {
		Role role = new Role(); role.setId_role(1L); role.setDescription("MEMBER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User("jack", "dray", "jack@aol.fr", "987456123", true, roles);
		user.toString();
	}
	
	@Test
	public void user_setters() {
		Role role = new Role(); role.setId_role(1L); role.setDescription("MEMBER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User();
		user.setId_user(1L);
		user.setActive(true);
		user.setEmail("aaa@aol.fr");
		user.setFirstname("jack");
		user.setLastname("holee");
		user.setPassword("123456");
		user.setRoles(roles);
		user.setTopicList(null);
		user.setPostList(null);
		user.setArticleList(null);
	}
}
