package com.deroussenicolas.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.exception.InvalidTopicException;
import com.deroussenicolas.service.CategoryService;
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
	@Autowired
	private CategoryService categoryService;
	
	
	
	public TopicServiceImplementation(TopicRepository topicRepository, CategoryService categoryService) {
		super();
		this.topicRepository = topicRepository;
		this.categoryService = categoryService;
	}

	@Override
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	/**
	 * Find all topics from the category, active and titleContains
	 * @return the topics list
	 * @throws InvalidTopicException 
	 */
	@Override
	public Page<Topic> findTopicsActiveByCategoryIdAndByTitleContains(Integer page, Integer size, String category, String active,
			String titleContains) throws InvalidTopicException {
		Pageable pageable = PageRequest.of(page, size); 	
		Long idCategory = categoryService.findCategoryByCategoryName(category).getId_category();	
		if(idCategory == null) {
			throw new InvalidTopicException("Category is null");
		}
		boolean activeBoolean;		
		if(active.equals("true")) {
			activeBoolean = true;
		} else if(active.equals("false")){
			activeBoolean = false;
		} else {
			throw new InvalidTopicException("Active is not true or false.");
		}
		if(titleContains.length() > 15) {
			throw new InvalidTopicException("Size is incorrect for titleContains, maximum size is 15.");
		}
		return topicRepository.findTopicsActiveByCategoryIdAndByTitleContains(idCategory, activeBoolean, titleContains, pageable);
	}
	
}
