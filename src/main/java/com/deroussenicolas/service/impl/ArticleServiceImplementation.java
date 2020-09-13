package com.deroussenicolas.service.impl;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.Category;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.exception.InvalidArticleException;
import com.deroussenicolas.service.ArticleService;
import com.deroussenicolas.service.CategoryService;
import com.deroussenicolas.service.UserService;

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
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	private Pageable pageable;
	
	private final String ARTICLE_NULL = "Article is null";
	private final String ARTICLE_NOT_FOUND = "Article not found with id =";
	private final String ARTICLE_SIZE_INVALID_TITLE = "Size of title is invalid, must be between 5 and 70.";	
	private final String ARTICLE_SIZE_INVALID_MESSAGE = "Size of message is invalid, must be between 5 and 1000.";
	private final String CATEGORY_NOT_FOUND =  "No category founds. An article can't exists without atlast one category.";
	private final String CATEGORY_DOES_NOT_MATCHES = "An exception occured with the category list, one category does not exists in database.";
	private final String USER_NOT_FOUND = "User not found.";
	private final String CATEGORY_LIST_EMPTY_OR_NULL = "An exception occured with the category list, it's empty or null.";
	
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
		Long idCategoryFootball = categoryService.findCategoryByCategoryName("Football").getId_category();	
		pageable = PageRequest.of(pageNo, pageSize); 
		return articleRepository.findAllArticlesActiveByCategoryId(idCategoryFootball, active, pageable);
	}
	
	/**
	 * Find all articles for the "Volleyball" category ordered, active or not depending of the parameter
	 * @return the articles list
	 */
	@Override
	public Page<Article> findAllVolleyballArticlesActive(boolean active, Integer pageNo, Integer pageSize) {
		Long idCategoryVolleyball = categoryService.findCategoryByCategoryName("Volleyball").getId_category();	
		pageable = PageRequest.of(pageNo, pageSize); 
		return articleRepository.findAllArticlesActiveByCategoryId(idCategoryVolleyball, active, pageable);
	}

	/**
	 * Find all articles for the "Basketball" category ordered, active or not depending of the parameter
	 * @return the articles list
	 */
	@Override
	public Page<Article> findAllBasketballArticlesActive(boolean active, Integer pageNo, Integer pageSize) {
		Long idCategoryBasketball = categoryService.findCategoryByCategoryName("Basketball").getId_category();	
		pageable = PageRequest.of(pageNo, pageSize); 
		return articleRepository.findAllArticlesActiveByCategoryId(idCategoryBasketball, active, pageable);
	}	
		
	/**
	 * Find all articles made by the user ordered, active and inactive are included
	 * @return the articles list
	 */
	@Override
	public Page<Article> findAllArticlesFromUser(String userEmail, Integer pageNo, Integer pageSize) {	
		Long user_id = userService.findByEmail(userEmail).getId_user();
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

	/**
	 * update article in database
	 * @return void
	 * @exception InvalidArticleException
	 */
	@Override
	public void updateArticle(Article article) throws InvalidArticleException {
		Article previousArticle = articleRepository.findArticleById(article.getId_article());
		if(previousArticle == null) {
			throw new InvalidArticleException(ARTICLE_NOT_FOUND + article.getId_article());
		} 
		else if(!verificationArticleTextSize(5, 70, article.getTitle())) {
			throw new InvalidArticleException(ARTICLE_SIZE_INVALID_TITLE);
		}
		else if(!verificationArticleTextSize(5, 1000, article.getMessage())){
			throw new InvalidArticleException(ARTICLE_SIZE_INVALID_MESSAGE);
		}
		else if(article.getCategories().size() < 1) {
			throw new InvalidArticleException(CATEGORY_NOT_FOUND);
		}
		else if(!verificationCategoryExists(article.getCategories())) {
			throw new InvalidArticleException(CATEGORY_DOES_NOT_MATCHES);
		}		
		article.setUser(previousArticle.getUser());
		articleRepository.save(article);		
	}

	/**
	 * create article in database
	 * @return void
	 * @exception InvalidArticleException
	 */
	@Override
	public void createArticle(Article article, String userEmail) throws InvalidArticleException {
		if(article == null) {
			throw new InvalidArticleException(ARTICLE_NULL);
		}
		else if(!verificationArticleTextSize(5, 70, article.getTitle())) {
			throw new InvalidArticleException(ARTICLE_SIZE_INVALID_TITLE);
		}
		else if(!verificationArticleTextSize(5, 1000, article.getMessage())){
			throw new InvalidArticleException(ARTICLE_SIZE_INVALID_MESSAGE);
		}
		else if(article.getCategories().size() < 1) {
			throw new InvalidArticleException(CATEGORY_NOT_FOUND);
		}
		if(!verificationCategoryExists(article.getCategories())) {
			throw new InvalidArticleException(CATEGORY_DOES_NOT_MATCHES);
		}
		article.setDate(new Date());
		User user = userService.findByEmail(userEmail);
		if(user != null) {
			article.setUser(user);
		} else {
			throw new InvalidArticleException(USER_NOT_FOUND);
		}
		articleRepository.save(article);		
	}
	
	
	/**
	 * check if the size of title, message are respected in the article
	 * @return true if respected otherwise false
	 */
	private boolean verificationArticleTextSize(int minSize, int maxSize, String subject) {
		if(subject == null) {
			return false;
		}
		int lengthOfSubject = subject.length();
		if(lengthOfSubject < minSize || lengthOfSubject > maxSize) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * check if every category in the category list exists in database and recreate a list for the insertion of the categories
	 * @return true if the list in parameter contains only category already existing in database otherwise false
	 */
	private boolean verificationCategoryExists(List<Category> categoriesFromParameter) throws InvalidArticleException {
		if(categoriesFromParameter.size() == 0 || categoriesFromParameter == null) {
			throw new InvalidArticleException(CATEGORY_LIST_EMPTY_OR_NULL);
		}
		List<Category> categoriesListFromDatabase = categoryService.findAllCategories();
		int sizeOfcategoriesFromParameter = categoriesFromParameter.size();
		for (Category category : categoriesListFromDatabase) {
			int incrementalNotMarches = 0;
			for (Category categoryFromParameter : categoriesFromParameter) {
				if(category.getDescription() != categoryFromParameter.getDescription()) {
					incrementalNotMarches++;
				} else if(sizeOfcategoriesFromParameter == incrementalNotMarches) {
					return false;
				}
			}
		}
		return true;
	}	
	
}
