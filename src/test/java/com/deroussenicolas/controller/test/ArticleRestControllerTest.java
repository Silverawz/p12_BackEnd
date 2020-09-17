package com.deroussenicolas.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.deroussenicolas.controller.ArticleController;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.exception.InvalidArticleException;
import com.deroussenicolas.service.ArticleService;

@RunWith(MockitoJUnitRunner.class)
public class ArticleRestControllerTest {

	@InjectMocks
    private static ArticleController articleController;
    private static ArticleService articleService = mock(ArticleService.class);
    
	
	@BeforeAll
	public static void initializeBeforeClass() {
		articleController = new ArticleController(articleService);
	}
    
    @Test
    public void footballArticleActiveList() {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Page<Article> pagesArticles = null;
        when(articleService.findAllFootballArticlesActive(true, 1, 1)).thenReturn(pagesArticles);         
        ResponseEntity<Page<Article>> responseEntity = articleController.footballArticleActiveList(new Integer(1) ,new Integer(1));      
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void volleyballArticleActiveList() {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Page<Article> pagesArticles = null;
        when(articleService.findAllVolleyballArticlesActive(true, 1, 1)).thenReturn(pagesArticles);         
        ResponseEntity<Page<Article>> responseEntity = articleController.volleyballArticleActiveList(new Integer(1) ,new Integer(1));      
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void basketballArticleActiveList() {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Page<Article> pagesArticles = null;
        when(articleService.findAllBasketballArticlesActive(true, 1, 1)).thenReturn(pagesArticles);         
        ResponseEntity<Page<Article>> responseEntity = articleController.basketballArticleActiveList(new Integer(1) ,new Integer(1));      
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void getArticleById() throws InvalidArticleException {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Article article = new Article();
        when(articleService.findArticleById(1)).thenReturn(article); 
        ResponseEntity<Article> responseEntity = articleController.getArticleById(1);      
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void getArticleById_withNullArticle() throws InvalidArticleException {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(articleService.findArticleById(1)).thenReturn(null); 
        ResponseEntity<Article> responseEntity = articleController.getArticleById(1);    
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);

    }
}
