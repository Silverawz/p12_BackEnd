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
		List<Article> activeArticles = articleService.findAllArticleActive(true);
		List<Article> articlesSortedByDate = articleService.sortArticleByDate(activeArticles);
		return articlesSortedByDate;
	}
}
