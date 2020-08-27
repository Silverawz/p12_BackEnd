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
	public List<Article> findAllArticleActive(boolean isActive);
	public List<Article> sortArticleByDate(List<Article> articles);
}
