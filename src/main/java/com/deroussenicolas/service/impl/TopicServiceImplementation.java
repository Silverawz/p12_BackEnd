package com.deroussenicolas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.service.TopicService;
import org.springframework.data.domain.Pageable;
/**
 * implements TopicService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("TopicServiceImplementation")
@Transactional
public class TopicServiceImplementation implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	/**
	 * Find all topics from the category, active and titleContains
	 * @return the topics list
	 */
	@Override
	public Page<Topic> findTopicsActiveByCategoryIdAndByTitleContains(Integer page, Integer size, Long categoryId, boolean active,
			String titleContains) {
		//check parameter first
		Pageable pageable = PageRequest.of(page, size); 
		
		
		
		return topicRepository.findTopicsActiveByCategoryIdAndByTitleContains(categoryId, active, titleContains, pageable);
	}
}
