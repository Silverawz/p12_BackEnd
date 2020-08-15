package com.deroussenicolas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
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
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.Category;
import com.deroussenicolas.entities.Post;
import com.deroussenicolas.entities.Role;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.entities.User;
/**
 * implements CommandLineRunner
 * 
 * @author deroussen nicolas
 * @version 1.0.0
 */
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
	private static final Logger LOGGER = LoggerFactory.getLogger(AssociationsSportivesApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AssociationsSportivesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		System.err.println("commandlineRunner.... START");
		LOGGER.error("Message logged at ERROR level");
		LOGGER.warn("Message logged at WARN level");
		LOGGER.info("Message logged at INFO level");
		LOGGER.debug("Message logged at DEBUG level");
		
		// OK
		/*
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
		*/
		
		
		
		// OK
		
		/*
		Category category = new Category();
		category.setDescription("Football");
		categoryR.save(category);
		*/
		
		
		/*
		Article article = new Article();
		article.setUser(userR.findAll().get(0));
		article.setDate(new Date());
		article.setMessage("Les fils du triomphe ont finalement décroché la victoire à Marseille.");
		article.setTitle("Ils y arrivent !");
		articleR.save(article);
		
		
		
		Set<Article> articleSet = new HashSet<>();
		articleSet.add(articleR.findAll().get(0));
		Category category = categoryR.findAll().get(0);
		category.setArticlecList(articleSet);
		categoryR.save(category);
		
		

		
		Topic topic = new Topic();
		topic.setTitle("je ne comprends pas!");
		topic.setUser(userR.findAll().get(0));
		topicR.save(topic);
		
		
		Post post = new Post();
		post.setTopic(topic);
		post.setDate(new Date());
		post.setUser(userR.findAll().get(0));
		post.setMessage("ceci est un messagececi est un messagececi est un messagececi est un messagececi est un messagececi est un message");
		postR.save(post);
		
		Set<Topic> topicSet = new HashSet<>();
		Topic topic1 = topicR.findAll().get(0);
		topicSet.add(topic1);
		Category category = categoryR.findAll().get(0);
		
		category.setTopicList(topicSet);
		categoryR.save(category);
		*/
		System.err.println("commandlineRunner.... END");
		
		
	}

}
