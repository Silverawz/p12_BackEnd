package com.deroussenicolas;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.dao.PostRepository;
import com.deroussenicolas.dao.RoleRepository;
import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.dao.UserRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.Category;
import com.deroussenicolas.service.ArticleService;
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
	private ArticleService articleService;
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
		/*
		Long id = Long.valueOf(1);
		List<Article> articles = articleR.findAllArticlesFromUser(id);
		for (Article a : articles) {
			System.err.println(a.getId_article());
		}
		
		
		

		Long id = Long.valueOf(2);
		
		List<Article> articles = articleR.findAllArticlesActiveByCategoryId(id, true);
		
		for (Article a : articles) {
			System.err.println("id de l'article=" + a.getId_article());
			System.err.println("date de l'article=" + a.getDate());
		}
		
		Article article = new Article();
		article.setUser(userR.findByEmail("aaa@aol.fr"));
		article.setDate(new Date());
		article.setMessage("Lorem Ipsum is simply dummy text of the was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
		article.setTitle("volley ball titre!");
		article.setActive(true);
		articleR.save(article);
	
		Article article1 = new Article();
		article1.setUser(userR.findByEmail("bbb@aol.fr"));
		article1.setDate(new Date());
		article1.setMessage("Lorem Ipsum is simply dummy text of the was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
		article1.setTitle("volley ball! ZZZZZZZZZZZZZZZZZZZZZ");
		article1.setActive(true);
		articleR.save(article1);
		
		
		List<Article> articleList = new ArrayList<>();
		Category category = categoryR.findAll().get(2);
		
		articleList = category.getArticlecList();
		articleList.add(articleR.findAll().get(7));
		articleList.add(articleR.findAll().get(8));

		category.setArticlecList(articleList);
		categoryR.save(category);*/
		/*
		Long idParsedAsLong = Long.valueOf(1); 
		List<Article> list = articleR.findAllFootballArticlesActive(idParsedAsLong, true);
		for (Article article : list) {
			System.err.println(article.toString());
		}

		System.err.println(articleService.findAllFootballArticlesActive(1));
		List<Article> articles = articleService.findAllFootballArticlesActive(1);
		for (Article article : articles) {
			System.err.println(article.getTitle());
			System.err.println(article.getDate());
		}
		
		
		
		/*
		LOGGER.error("Message logged at ERROR level");
		LOGGER.warn("Message logged at WARN level");
		LOGGER.info("Message logged at INFO level");
		LOGGER.debug("Message logged at DEBUG level");
		
		
		Article article = new Article();
		article.setUser(userR.findByEmail("aaa@aol.fr"));
		article.setDate(new Date());
		article.setMessage("Lorem Ipsum is simply dummy text of the was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
		article.setTitle("TITRE TITRETITRETITRETITRETITRE!");
		article.setActive(true);
		articleR.save(article);
		
		Article article1 = new Article();
		article1.setUser(userR.findByEmail("aaa@aol.fr"));
		article1.setDate(new Date());
		article1.setMessage("texte.........................texte.........................texte.........................");
		article1.setTitle("TITRE TITRETITRETITRETITRETITRE!");
		article1.setActive(true);
		articleR.save(article1);
		
			
		Set<Article> articleSet = new HashSet<>();
		articleSet.add(articleR.findAll().get(0));
		articleSet.add(articleR.findAll().get(1));
		articleSet.add(articleR.findAll().get(2));
		articleSet.add(articleR.findAll().get(3));
		articleSet.add(articleR.findAll().get(4));
		Category category = categoryR.findAll().get(0);
		category.setArticlecList(articleSet);
		categoryR.save(category);
		*/
		/*
		User user = userR.findByEmail("aaa@aol.fr");
		System.err.println(user.getFirstname());
		*/
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
