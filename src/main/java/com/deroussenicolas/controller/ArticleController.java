package com.deroussenicolas.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deroussenicolas.entities.Article;
import com.deroussenicolas.exception.InvalidArticleException;
import com.deroussenicolas.service.ArticleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sport")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
	private final String  CREATION_ARTICLE_FAILED = "Failed to create the article : ";
	private final String  ERROR_MESSAGE_NEXT_LINE = "\n | Error message = " ;

	public ArticleController(ArticleService articleService) {
		super();
		this.articleService = articleService;
	}

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
	public ResponseEntity<Article> getArticleById(@RequestParam int id) throws InvalidArticleException {
		try {
			Article article = articleService.findArticleById(id);	
			if(article!= null) {
				return new ResponseEntity<Article>(article, new HttpHeaders(), HttpStatus.OK); 
			} else {
				throw new InvalidArticleException("Article not found with the id : "+ id);
			}		
		} catch (InvalidArticleException e) {
			LOGGER.error("   "+e);
		}
		return new ResponseEntity<Article>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PutMapping("/article") 
	public ResponseEntity<?> updateArticle(@RequestBody @Valid Article article) throws InvalidArticleException {
		try {
			articleService.updateArticle(article);
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		} catch (InvalidArticleException e1) {
			LOGGER.error(CREATION_ARTICLE_FAILED + article.toString() + ERROR_MESSAGE_NEXT_LINE + e1);
		} catch (Exception e2) {
			LOGGER.error(CREATION_ARTICLE_FAILED + article.toString() + ERROR_MESSAGE_NEXT_LINE + e2);
		}
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PostMapping("/article")
	public ResponseEntity<?> createArticle(@RequestBody @Valid Article article) throws InvalidArticleException {
		try {
			String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			articleService.createArticle(article, userEmail);
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
		} catch (InvalidArticleException e1) {
			LOGGER.error(CREATION_ARTICLE_FAILED + article.toString() + ERROR_MESSAGE_NEXT_LINE + e1);
		} catch (Exception e2) {
			LOGGER.error(CREATION_ARTICLE_FAILED + article.toString() + ERROR_MESSAGE_NEXT_LINE + e2);
		}
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
