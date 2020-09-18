package com.deroussenicolas.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deroussenicolas.dao.TopicRepository;
import com.deroussenicolas.entities.Topic;
import com.deroussenicolas.exception.InvalidTopicException;
import com.deroussenicolas.service.TopicService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/forum")
public class TopicController {

	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicRepository topicR;
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

	@GetMapping("/topics")
	public ResponseEntity<?> topicsByCategoryAndActiveAndTitleContains (
			@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size,
			@RequestParam String category,
			@RequestParam String active,
			@RequestParam(defaultValue = "") String titleContains) throws InvalidTopicException {	 
		try {
			Page<Topic> pages = topicService.findTopicsActiveByCategoryIdAndByTitleContains(page, size, category, active, titleContains);
			return new ResponseEntity<>(pages, new HttpHeaders(), HttpStatus.OK);
		} catch (InvalidTopicException e) {
			LOGGER.error("InvalidTopicException occured, message:" + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An exception occured, message : " + e.getMessage());
		}
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
	}
	
	
	@GetMapping("/topic")
	public ResponseEntity<?> topicByTopicId (@RequestParam int topicId) throws InvalidTopicException {
		Long idLong = Long.valueOf(topicId);
		if(idLong == null) {
			LOGGER.error("id topic is null");
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
		} else {
			Optional<Topic> topic = topicR.findById(idLong);
			if(topic == null) {
				LOGGER.error("Topic is null");
				return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
			}
			return new ResponseEntity<>(topic, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
}
