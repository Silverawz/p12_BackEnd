package com.deroussenicolas.entities.unit.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.entities.Category;
import com.deroussenicolas.entities.Post;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.entities.User;

@RunWith(MockitoJUnitRunner.class)
public class TopicEntityTest {

	@Test
	public void topic_setters() {
		Topic topic = new Topic();
		topic.setId_topic(1L);
		topic.setTitle("titletopic");
		topic.setActive(true);
		List<Category> categories = new ArrayList<>();
		Category category = new Category(); category.setId_category(1L); category.setDescription("football");
		categories.add(category);
		topic.setCategories(categories);
		User user = new User();
		topic.setUser(user);
		List<Post> posts = new ArrayList<>();
		Post post = new Post();
		posts.add(post);
		topic.setPostList(posts);
        assertThat(topic.getId_topic()).isEqualTo(1L);  
        assertThat(topic.getTitle()).isEqualTo("titletopic");         
        assertThat(topic.isActive()).isEqualTo(true);  
        assertThat(topic.getCategories()).isEqualTo(categories);  
        assertThat(topic.getUser()).isEqualTo(user);  
        assertThat(topic.getPostList()).isEqualTo(posts); 
	}
}
