package com.deroussenicolas;
import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.dao.PostRepository;
import com.deroussenicolas.dao.RoleRepository;
import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.dao.UserRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.Category;
import com.deroussenicolas.entities.User;
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
		
		// test here
		/*
		int a = 1;
		Long idLong = Long.valueOf(a);
		Pageable pageable = PageRequest.of(5, 1); 
		Page page = topicR.findAllTopicsActiveByCategoryIdAndByDesignation(idLong, true, "a", pageable);
		System.out.println(page.getSize());*/
		System.err.println("commandlineRunner.... END");	
	}

}
