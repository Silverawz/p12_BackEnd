package com.deroussenicolas.service;

import java.util.List;

import com.deroussenicolas.entities.Article;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface ArticleService {

	public void save(Article article);
	public List<Article> findAllFootballArticlesActive(boolean active);
	public List<Article> sortArticleByDate(List<Article> articles);
	public List<Article> findAllVolleyballArticlesActive(boolean active);
	public List<Article> findAllBasketballArticlesActive(boolean active);
	public List<Article> findAllArticlesFromUser(String userEmail);
}
