package com.deroussenicolas.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deroussenicolas.entities.Article;
import com.deroussenicolas.service.ArticleService;

/**
 * 
 * 
 * @author deroussen nicolas
 * 
 */
@RestController
public class ArticleControllerRest {
	
	private final ArticleService articleService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleControllerRest.class);
	
	@Autowired
	public ArticleControllerRest(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@PostMapping("/article/create")
	public ResponseEntity<Object> creatingArticle(@RequestBody @Valid Article article) {
		try {
			articleService.save(article);
			LOGGER.info("New article created in database = " + article.toString());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error during the article creation in database = " + article.toString());
			throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error during the user creation in database", e
            );
		}
	}
	
}
