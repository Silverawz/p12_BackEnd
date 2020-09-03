package com.deroussenicolas.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.service.ArticleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sport")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleRepository articleRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
/*
	@GetMapping("/football/test")
	public ResponseEntity<Page<Article>> footballTestPageable(@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size) {	
	      Page<Article> pagesArticles = articleService.getAllArticles(page, size);      
	      return new ResponseEntity<Page<Article>>(pagesArticles, new HttpHeaders(), HttpStatus.OK); 
	}	*/
	
	
		
	@GetMapping("/football/active")
	public ResponseEntity<Page<Article>> footballArticleActiveList(@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size) {	
	      Page<Article> pagesArticles = articleService.findAllFootballArticlesActive(true, page, size);      
	      return new ResponseEntity<Page<Article>>(pagesArticles, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@GetMapping("/volleyball/active")
	public ResponseEntity<Page<Article>> volleyballArticleActiveList(@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size) {	
	      Page<Article> pagesArticles = articleService.findAllVolleyballArticlesActive(true, page, size);      
	      return new ResponseEntity<Page<Article>>(pagesArticles, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@GetMapping("/basketball/active")
	public ResponseEntity<Page<Article>> basketballArticleActiveList(@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size) {	
	      Page<Article> pagesArticles = articleService.findAllBasketballArticlesActive(true, page, size);      
	      return new ResponseEntity<Page<Article>>(pagesArticles, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@GetMapping("/article")
	public ResponseEntity<Article> getArticleById(@RequestParam int id) {
		Article article = articleService.findArticleById(id);	
		return new ResponseEntity<Article>(article, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@PutMapping("/article") 
	public ResponseEntity<?> updateArticle(@RequestBody @Valid Article article) {
		try {
			articleService.updateArticle(article);
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Update article failed for = " + article.toString() +
					" || error ="+ e);
		}
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
