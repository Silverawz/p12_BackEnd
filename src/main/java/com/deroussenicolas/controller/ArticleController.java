package com.deroussenicolas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deroussenicolas.entities.Article;
import com.deroussenicolas.service.ArticleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sport")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
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
