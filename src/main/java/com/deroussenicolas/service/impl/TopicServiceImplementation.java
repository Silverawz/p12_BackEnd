package com.deroussenicolas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.service.TopicService;

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
}
