package com.deroussenicolas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.deroussenicolas.entities.Topic;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface TopicService {

	public List<Topic> findAll();
	public Page<Topic> findTopicsActiveByCategoryIdAndByTitleContains(Integer page, Integer size, Long categoryId, boolean active, String titleContains);
}
