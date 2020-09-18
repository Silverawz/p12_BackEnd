package com.deroussenicolas.entities.unit.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.entities.Post;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.entities.User;
@RunWith(MockitoJUnitRunner.class)
public class PostEntityTest {

	@Test
	public void postEntity_allParams() {
		Post post = new Post();
		post.setId_post(1L);
		post.setMessage("message");
		Date date = new Date();
		post.setDate(date);
		User user = new User();
		post.setUser(user);
		Topic topic = new Topic();
		post.setTopic(topic);
        assertThat(post.getId_post()).isEqualTo(1L);  
        assertThat(post.getMessage()).isEqualTo("message");  
        assertThat(post.getDate()).isEqualTo(date);  
        assertThat(post.getUser()).isEqualTo(user);  
        assertThat(post.getTopic()).isEqualTo(topic);  
	}
	
}
