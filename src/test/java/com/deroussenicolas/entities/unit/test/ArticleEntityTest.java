package com.deroussenicolas.entities.unit.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.User;

@RunWith(MockitoJUnitRunner.class)
public class ArticleEntityTest {

	@Test
	public void article_toString() {
		Article article = new Article();
		article.setId_article(1L);
		article.setTitle("titlevalid");
		article.setActive(true);
		article.setCategories(null);
		article.setMessage("messagevalid");
		article.setDate(new Date());
		User user = new User(); user.setEmail("ggg@aol.fr");
		article.setUser(user);
        assertThat("Article [id_article=1, title=titlevalid, message=messagevalid, date="+article.getDate()+", active=true, user=ggg@aol.fr]").isEqualTo(article.toString());    
        assertThat(user).isEqualTo(article.getUser());  
        assertThat(true).isEqualTo(article.isActive());  
	}
}
