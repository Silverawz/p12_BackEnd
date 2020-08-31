package com.deroussenicolas.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<Article> moderatorAccess() {
		String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return articleService.findAllArticlesFromUser(userEmail);
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {	
		return "Admin Board.";
	}
	
}
