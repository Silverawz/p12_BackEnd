package com.deroussenicolas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	

	@GetMapping("/football/test")
	public ResponseEntity<Page<Article>> footballTestPageable(@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size) {	
	      Page<Article> pagesArticles = articleService.getAllArticles(page, size);      
	      return new ResponseEntity<Page<Article>>(pagesArticles, new HttpHeaders(), HttpStatus.OK); 
	}	
	
	
	
	
	@GetMapping("/football/active")
	public List<Article> footballArticleActiveList() {	
		return articleService.findAllFootballArticlesActive(true);
	}
	
	@GetMapping("/volleyball/active")
	public List<Article> volleyballArticleActiveList() {
		return articleService.findAllVolleyballArticlesActive(true);
	}
	
	@GetMapping("/basketball/active")
	public List<Article> basketballArticleActiveList() {
		return articleService.findAllBasketballArticlesActive(true);
	}

}
