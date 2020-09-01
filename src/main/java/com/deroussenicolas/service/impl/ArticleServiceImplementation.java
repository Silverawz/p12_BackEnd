package com.deroussenicolas.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.dao.UserRepository;
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
	@Autowired
	private UserRepository userRepository;
	private Pageable pageable;
	
	@Override
	public void save(Article article) {
		articleRepository.save(article);	
	}
	
	/**
	 * Find all articles for the "Football" category ordered, active or not depending of the parameter
	 * @return the articles list
	 */
	@Override
	public Page<Article> findAllFootballArticlesActive(boolean active, Integer pageNo, Integer pageSize) {
		Long idCategoryFootball = categoryRepository.findCategoryByCategoryName("Football").getId_category();	
		pageable = PageRequest.of(pageNo, pageSize); 
		return articleRepository.findAllArticlesActiveByCategoryId(idCategoryFootball, active, pageable);
	}
	
	/**
	 * Find all articles for the "Volleyball" category ordered, active or not depending of the parameter
	 * @return the articles list
	 */
	@Override
	public Page<Article> findAllVolleyballArticlesActive(boolean active, Integer pageNo, Integer pageSize) {
		Long idCategoryVolleyball = categoryRepository.findCategoryByCategoryName("Volleyball").getId_category();	
		pageable = PageRequest.of(pageNo, pageSize); 
		return articleRepository.findAllArticlesActiveByCategoryId(idCategoryVolleyball, active, pageable);
	}

	/**
	 * Find all articles for the "Basketball" category ordered, active or not depending of the parameter
	 * @return the articles list
	 */
	@Override
	public Page<Article> findAllBasketballArticlesActive(boolean active, Integer pageNo, Integer pageSize) {
		Long idCategoryBasketball = categoryRepository.findCategoryByCategoryName("Basketball").getId_category();	
		pageable = PageRequest.of(pageNo, pageSize); 
		return articleRepository.findAllArticlesActiveByCategoryId(idCategoryBasketball, active, pageable);
	}	
		
	/**
	 * Find all articles made by the user ordered, active and inactive are included
	 * @return the articles list
	 */
	@Override
	public Page<Article> findAllArticlesFromUser(String userEmail, Integer pageNo, Integer pageSize) {	
		Long user_id = userRepository.findByEmail(userEmail).getId_user();
		pageable = PageRequest.of(pageNo, pageSize); 
		return articleRepository.findAllArticlesFromUser(user_id, pageable);
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

	/*
	public Page<Article> getAllArticles(Integer pageNo, Integer pageSize) {
		 Pageable pageable = PageRequest.of(pageNo, pageSize); 
		 Long id = Long.valueOf(1);
		 return articleRepository.getAllArticles(id, true, pageable);
	}*/


}
