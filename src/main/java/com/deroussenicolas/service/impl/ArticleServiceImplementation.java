package com.deroussenicolas.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.service.ArticleService;

/**
 * implements ArticleService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("ArticleServiceImplementation")
@Transactional
public class ArticleServiceImplementation implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void save(Article article) {
		articleRepository.save(article);	
	}

	
	/**
	 * Find all articles for the "Football" category, active or not depending of the parameter
	 * @return the articles list
	 */
	@Override
	public List<Article> findAllFootballArticlesActive(boolean active) {
		Long idCategoryFootball = categoryRepository.findCategoryByCategoryName("Football").getId_category();	
		return sortArticleByDate(articleRepository.findAllFootballArticlesActive(idCategoryFootball, active));
	}
	
	/**
	 * sort a list of articles by date
	 * @return the list sorted
	 */
	@Override
	public List<Article> sortArticleByDate(List<Article> articles) {			
		List<Article> sortedListOfArticles = new ArrayList<>();
		if(articles.size() > 1) {
			while(articles.size() != 1) {
				Article lowestArticleFound = comparatorDateToGetTheLowestDate(articles);
				sortedListOfArticles.add(lowestArticleFound);
				articles.remove(lowestArticleFound);
			}
			sortedListOfArticles.add(articles.get(0));
		}		
		return sortedListOfArticles;
	}
	
	/**
	 * Compare the date of each articles in a List<Article>
	 * @return Article with the lowest date
	 */
	private Article comparatorDateToGetTheLowestDate(List<Article> articles) {
		Article lowestArticleDateFound = articles.get(0);
		for (Article article : articles) {
			if(article.getDate().compareTo(lowestArticleDateFound.getDate()) > 0) {
				lowestArticleDateFound = article;
			}
		}
		return lowestArticleDateFound;	
	}

}
