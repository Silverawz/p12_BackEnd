package com.deroussenicolas.service.unit.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.exception.InvalidTopicException;
import com.deroussenicolas.service.CategoryService;
import com.deroussenicolas.service.impl.TopicServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTest {

	private static TopicRepository topicRepository = mock(TopicRepository.class);
	private static CategoryService categoryService = mock(CategoryService.class);
	private static CategoryRepository categoryRepository = mock(CategoryRepository.class);
	@InjectMocks
	private static TopicServiceImplementation topicServiceImplementation;
	
	@BeforeAll
	public static void initializeBeforeClass() throws InvalidTopicException {
		topicServiceImplementation = new TopicServiceImplementation(topicRepository, categoryService);
	}

	@Test 
	public void findAll() {
		List<Topic> topics = new ArrayList<>();
		given(topicRepository.findAll()).willReturn(topics);
		assertThat(topicServiceImplementation.findAll()).isEqualTo(topics); 		
	}
	
}
