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
import com.deroussenicolas.entities.Category;
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

	@Override
	public Article findArticleById(int article_id) {
		Long id_as_Long = Long.valueOf(article_id);
		return articleRepository.findArticleById(id_as_Long);
	}

	@Override
	public void updateArticle(Article article) {
		Article previousArticle = articleRepository.findArticleById(article.getId_article());
		boolean validation = true;
		if(previousArticle == null) {
			System.err.println("1");
			validation = false;
		}
		
		if(!verificationArticleTextSize(5, 70, article.getTitle())) {
			System.err.println("2");
			validation = false;
		}
		if(!verificationArticleTextSize(5, 1000, article.getMessage())) {	
			System.err.println("3");
			validation = false;
		}
		
		// categories ici
		// check if atleast 1 category is set
		if(article.getCategories().size() < 1) {
			validation = false;
			System.err.println("4");
		}
		if(validation) {
			article.setUser(previousArticle.getUser());
			articleRepository.save(article);
			
	
		}


		
		
		
		
		
		//set categories
		/*
		for (Category c : article.getCategories()) {
			if(c.getDescription() == "Football") {
				Category footballCategory = categoryRepository.findCategoryByCategoryName("Football");
				List<Article> articlesOfFootballCategory = footballCategory.getArticles();
				articlesOfFootballCategory.add(article);
				categoryRepository.save(footballCategory);
			} else if (c.getDescription() == "Basketball") {
				Category basketballCategory = categoryRepository.findCategoryByCategoryName("Basketball");
				List<Article> articlesOfBasketballCategory = basketballCategory.getArticles();
				articlesOfBasketballCategory.add(article);
				categoryRepository.save(basketballCategory);
			} else if (c.getDescription() == "Volleyball") {
				Category volleyballCategory = categoryRepository.findCategoryByCategoryName("Volleyball");
				List<Article> articlesOfVolleyballCategory = volleyballCategory.getArticles();
				articlesOfVolleyballCategory.add(article);
				categoryRepository.save(volleyballCategory);
			}			
		} */
		
		
		
		
		

		
		/* WORK
		 * 
		 * 		System.out.println(previousArticle.isActive()+("========>")+article.isActive());
		for (Category c : article.getCategories()) {
			System.out.println("news one="+c.getDescription());
		}
		
		for (Category c : previousArticle.getCategories()) {
			System.out.println("previous="+c.getDescription());
		}*/
		/* WORK
		System.out.println(previousArticle.getTitle()+" ====> "+article.getTitle());
		System.out.println(previousArticle.getMessage()+" ====> "+article.getMessage());
		System.out.println(previousArticle.getDate()+" ====> "+article.getDate());
		System.out.println(previousArticle.getUser().getEmail()+" ====> "+article.getUser().getEmail());
		System.out.println(previousArticle.getId_article()+" ====> "+article.getId_article());	*/	
		
		/*previousArticle.setTitle(article.getTitle());
		previousArticle.setMessage(article.getMessage());
		previousArticle.setActive(article.isActive());
		
		previousArticle.setCategories(article.getCategories());*/

	}


	/*
	public Page<Article> getAllArticles(Integer pageNo, Integer pageSize) {
		 Pageable pageable = PageRequest.of(pageNo, pageSize); 
		 Long id = Long.valueOf(1);
		 return articleRepository.getAllArticles(id, true, pageable);
	}*/

	
	/**
	 * check if the size of title, message are respected in the article
	 * @return true if respected otherwise false
	 */
	private boolean verificationArticleTextSize(int minSize, int maxSize, String subject) {
		int lengthOfSubject = subject.length();
		if(lengthOfSubject < minSize || lengthOfSubject > maxSize) {
			return false;
		} else {
			return true;
		}
	}
	
}
