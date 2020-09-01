package com.deroussenicolas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deroussenicolas.entities.Article;
import com.deroussenicolas.service.ArticleService;
import com.deroussenicolas.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/private")
public class privateController {

	private final UserService userService;
	private final ArticleService articleService;

	@Autowired
	public privateController(UserService userService, ArticleService articleService) {
		this.userService = userService;
		this.articleService = articleService;
	}

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Page<Article>> moderatorAccess(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size) {
		String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Page<Article> pagesArticles = articleService.findAllArticlesFromUser(userEmail, page, size);
		return new ResponseEntity<Page<Article>>(pagesArticles, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

}
