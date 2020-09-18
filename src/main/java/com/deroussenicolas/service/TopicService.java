package com.deroussenicolas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.exception.InvalidTopicException;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface TopicService {

	public List<Topic> findAll();
	public Page<Topic> findTopicsActiveByCategoryIdAndByTitleContains(Integer page, Integer size, String category, String active, String titleContains) throws InvalidTopicException;
}
