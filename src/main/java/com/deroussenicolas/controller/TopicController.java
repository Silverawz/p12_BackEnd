package com.deroussenicolas.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
import com.deroussenicolas.service.TopicService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/forum")
public class TopicController {

	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicRepository topicR;
	
	
	@GetMapping("/topics")
	public ResponseEntity<?> topicsByCategoryAndActiveAndTitleContains(
			@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size,
			@RequestParam Long categoryId,
			@RequestParam Boolean active,		
			@RequestParam(defaultValue = "") String titleContains) {	 
		Page<Topic> pages = topicService.findTopicsActiveByCategoryIdAndByTitleContains(page, size, categoryId, active, titleContains);
		return new ResponseEntity<>(pages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
}
