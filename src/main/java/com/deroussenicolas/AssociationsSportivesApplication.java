package com.deroussenicolas;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.dao.PostRepository;
import com.deroussenicolas.dao.RoleRepository;
import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.dao.UserRepository;
import com.deroussenicolas.entities.Role;
import com.deroussenicolas.entities.User;

@SpringBootApplication
public class AssociationsSportivesApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userR;
	@Autowired
	private RoleRepository roleR;
	@Autowired
	private ArticleRepository articleR;
	@Autowired
	private CategoryRepository categoryR;
	@Autowired
	private PostRepository postR;
	@Autowired
	private TopicRepository topicR;
	
	public static void main(String[] args) {
		SpringApplication.run(AssociationsSportivesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("testing.... START");
		
		
		User user = new User();
		user.setFirstname("Gerard");
		user.setLastname("Elastique");
		user.setEmail("aaa@aol.fr");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("123456");
		user.setPassword(hashedPassword);
		userR.save(user);
		
		Role role = new Role();
		role.setDescription("ADMIN");
		roleR.save(role);
		
		
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(roleR.findAll().get(0));
		user.setRoles(roleSet);
		userR.save(user);
		
		System.err.println("testing.... END");
	}

}
