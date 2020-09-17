package com.deroussenicolas.service.unit.test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.Category;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.exception.InvalidArticleException;
import com.deroussenicolas.service.ArticleService;
import com.deroussenicolas.service.CategoryService;
import com.deroussenicolas.service.UserService;
import com.deroussenicolas.service.impl.ArticleServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

	
    private ArticleService articleService = mock(ArticleService.class);
	private static CategoryService categoryService = mock(CategoryService.class);
	private static ArticleRepository articleRepository = mock(ArticleRepository.class);
	private static UserService userService = mock(UserService.class);;
	@InjectMocks
	private static ArticleServiceImplementation articleServiceImplementation;
	private String userEmail = "aaa@aol.fr";
	private static List<Category> categories;
	private static Article article;
	
	@BeforeAll
	public static void initializeBeforeClass() throws InvalidArticleException {
		categories = new ArrayList<>();
		Category cat1 = new Category(); cat1.setId_category(1L); cat1.setDescription("foot");
		Category cat2 = new Category(); cat2.setId_category(2L); cat2.setDescription("basket");
		Category cat3 = new Category(); cat3.setId_category(3L); cat3.setDescription("volley");
		categories.add(cat1);categories.add(cat2);categories.add(cat3);
		articleServiceImplementation = new ArticleServiceImplementation(articleRepository, categoryService, userService);	
		given(categoryService.findAllCategories()).willReturn(categories);
		/*
		Article article = new Article();
		article.setId_article(1L);
		given(articleRepository.findArticleById(1L)).willReturn(article);*/
	}
	
	
	/** create article
	 *
	 * @throws InvalidArticleException
	 */
	@Test
    public void createArticle_WithNullArticle() throws InvalidArticleException {
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(null, userEmail));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException Article is null"); 
	}
	
	@Test
    public void createArticle_WithInvalidTitleSize() throws InvalidArticleException {
		Article article = new Article();
		 // check for 4 chars
		article.setTitle("aaaa");
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException Size of title is invalid, must be between 5 and 70."); 
        // check for 71 chars
        article.setTitle("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); 
		InvalidArticleException exception2 = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception2.getMessage()).isEqualTo("InvalidArticleException Size of title is invalid, must be between 5 and 70."); 
	}
	
	@Test
    public void createArticle_WithInvalidTextSize() throws InvalidArticleException {
		Article article = new Article();
		article.setTitle("valid title");
		 // check for 4 chars
		article.setMessage("aaaa");
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException Size of message is invalid, must be between 5 and 1000."); 
        // check for 1001 chars
        article.setMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); 
		InvalidArticleException exception2 = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception2.getMessage()).isEqualTo("InvalidArticleException Size of message is invalid, must be between 5 and 1000."); 
	}
	
	@Test
    public void createArticle_WithEmptyOrNullCategories() throws InvalidArticleException {
		Article article = new Article();
		article.setTitle("valid title");
		article.setMessage("valid message");
		//null categories
		article.setCategories(null);	
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException No category founds. An article can't exists without atlast one category.");   
        List<Category> categories = new ArrayList<>();
        article.setCategories(categories);	
		//empty categories
		InvalidArticleException exception2 = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception2.getMessage()).isEqualTo("InvalidArticleException No category founds. An article can't exists without atlast one category.");  
	}	
	
	@Test
    public void createArticle_WithWrongCategories() throws InvalidArticleException {
		Article article = new Article();
		article.setTitle("valid title");
		article.setMessage("valid message");
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setId_category(1L); category.setDescription("rugby");
        categories.add(category);
        article.setCategories(categories);	
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException An exception occured with the category list, one category does not exists in database.");  
	}
	
	@Test
    public void createArticle_WithNullUser() throws InvalidArticleException {
		Article article = new Article();
		article.setTitle("valid title");
		article.setMessage("valid message");
		article.setUser(null);
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setId_category(1L); category.setDescription("foot");
        categories.add(category);
        article.setCategories(categories);	
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.createArticle(article, userEmail));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException User not found.");  
	}
	
	/** update article
	 *
	 * @throws InvalidArticleException
	 */
	@Test
    public void updateArticle_WithNullArticle() throws InvalidArticleException {
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(null));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException Article is null"); 
	}
	
	@Test
    public void updateArticle_WithInvalidTitleSize() throws InvalidArticleException {
		Article article = new Article();
		 // check for 4 chars
		article.setTitle("aaaa");
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException Size of title is invalid, must be between 5 and 70."); 
        // check for 71 chars
        article.setTitle("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); 
		InvalidArticleException exception2 = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception2.getMessage()).isEqualTo("InvalidArticleException Size of title is invalid, must be between 5 and 70.");    
        // check for null
        article.setTitle(null); 
		InvalidArticleException exception3 = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception3.getMessage()).isEqualTo("InvalidArticleException Size of title is invalid, must be between 5 and 70.");   
	}
	
	@Test
    public void updateArticle_WithInvalidTextSize() throws InvalidArticleException {
		Article article = new Article();
		article.setTitle("valid title");
		 // check for 4 chars
		article.setMessage("aaaa");
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException Size of message is invalid, must be between 5 and 1000."); 
        // check for 1001 chars
        article.setMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); 
		InvalidArticleException exception2 = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception2.getMessage()).isEqualTo("InvalidArticleException Size of message is invalid, must be between 5 and 1000."); 
	}
	
	@Test
    public void updateArticle_WithEmptyOrNullCategories() throws InvalidArticleException {
		Article article = new Article();
		article.setTitle("valid title");
		article.setMessage("valid message");
		//null categories
		article.setCategories(null);	
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException No category founds. An article can't exists without atlast one category.");   
        List<Category> categories = new ArrayList<>();
        article.setCategories(categories);	
		//empty categories
		InvalidArticleException exception2 = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception2.getMessage()).isEqualTo("InvalidArticleException No category founds. An article can't exists without atlast one category.");  
	}	
	
	@Test
    public void updateArticle_WithNullPreviousArticle() throws InvalidArticleException {
		Article article = new Article();
		article.setId_article(1L);
		article.setTitle("valid title");
		article.setMessage("valid message");
		Category category = new Category();
		category.setId_category(1L); category.setDescription("foot");
        categories.add(category);
        article.setCategories(categories); 
		given(articleRepository.findArticleById(1L)).willReturn(null);
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException Article not found with id =1");   
	}		
	
	@Test
    public void updateArticle_WithWrongCategories() throws InvalidArticleException {
		Article article = new Article();
		article.setId_article(1L);
		article.setTitle("valid title");
		article.setMessage("valid message");
		Category category = new Category();
		category.setId_category(1L); 
		category.setDescription("rugby");
		List<Category> categories = new ArrayList<>();
        categories.add(category);
        article.setCategories(categories); 
        Article previousArticle = new Article();
		given(articleRepository.findArticleById(1L)).willReturn(previousArticle);
		InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleServiceImplementation.updateArticle(article));
        assertThat(exception.getMessage()).isEqualTo("InvalidArticleException An exception occured with the category list, one category does not exists in database.");   
	}		
	
	
	@Test
	public void sortArticleByDate() {
		List<Article> articles = new ArrayList<>();
		Date date = new Date();
		User user = new User();
		user.setEmail("aaa@aol.fr");		
		Article article = new Article();article.setId_article(1L);article.setTitle("valid title");article.setMessage("valid message");
		article.setDate(date);article.setActive(true);article.setUser(user);
		articles.add(article);		
		Calendar cal = Calendar.getInstance();cal.setTime(date);cal.add(Calendar.HOUR, -1);
		Date oneHourBack = cal.getTime();		
		Article article1 = new Article();article1.setId_article(2L);article1.setTitle("valid title");article1.setMessage("valid message");
		article1.setDate(oneHourBack);article1.setActive(true);article1.setUser(user);
		articles.add(article1);	
		assertThat(articleServiceImplementation.sortArticleByDate(articles).get(0)).isEqualTo(article1); 
	}
	
}
