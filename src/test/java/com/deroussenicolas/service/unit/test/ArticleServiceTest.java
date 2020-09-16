package com.deroussenicolas.service.unit.test;

import static org.mockito.Mockito.doAnswer;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.junit.Assert;
import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.Category;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.exception.InvalidArticleException;
import com.deroussenicolas.service.ArticleService;
import com.deroussenicolas.service.CategoryService;
import com.deroussenicolas.service.impl.ArticleServiceImplementation;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

	
    private ArticleService articleService = mock(ArticleService.class);
	private static CategoryService categoryService = mock(CategoryService.class);
	private static ArticleRepository articleRepository = mock(ArticleRepository.class);;
	@InjectMocks
	private static ArticleServiceImplementation articleServiceImplementation;
	private String userEmail = "aaa@aol.fr";
	private static List<Category> categories;
	private static Article article;
	
	
	@BeforeAll
	public static void initializeBeforeClass() throws InvalidArticleException {
		categories = new ArrayList<>();
		Category cat1 = new Category(); cat1.setId_category(1L); cat1.setDescription("foot");
		Category cat2 = new Category(); cat2.setId_category(2L); cat1.setDescription("basket");
		Category cat3 = new Category(); cat3.setId_category(3L); cat1.setDescription("volley");
		categories.add(cat1);categories.add(cat2);categories.add(cat3);
		articleServiceImplementation = new ArticleServiceImplementation();	
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
