package com.deroussenicolas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.deroussenicolas.entities.Article;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface ArticleService {

	public void save(Article article);
	public Page<Article> findAllFootballArticlesActive(boolean active, Integer pageNo, Integer pageSize);
	public List<Article> sortArticleByDate(List<Article> articles);
	public Page<Article> findAllVolleyballArticlesActive(boolean active, Integer pageNo, Integer pageSize);
	public Page<Article> findAllBasketballArticlesActive(boolean active, Integer pageNo, Integer pageSize);
	public Page<Article> findAllArticlesFromUser(String userEmail, Integer pageNo, Integer pageSize);
	//public Page<Article> getAllArticles(Integer pageNo, Integer pageSize);
	public Article findArticleById(int article_id);
	
	
}
