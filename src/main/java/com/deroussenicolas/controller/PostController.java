package com.deroussenicolas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deroussenicolas.entities.Post;
import com.deroussenicolas.exception.InvalidPostException;
import com.deroussenicolas.service.PostService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/forum")
public class PostController {

	@Autowired
	private PostService postService;
	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
	
	@GetMapping("/posts")
	public ResponseEntity<?> getPostsFromTopicId (@RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size,
			@RequestParam int id_topic) throws InvalidPostException {
		try {
			Long idLong = Long.valueOf(id_topic);
			if(idLong == null) {
				LOGGER.error("id post is null");
				throw new InvalidPostException("id post is null");
			}
			Pageable pageable = PageRequest.of(page, size); 
			Page<Post> pages = postService.findPostsFromTopic(idLong, pageable);
			if(pages == null) {
				LOGGER.error("result is null");
				throw new InvalidPostException("pages is null");
			} else {
				return new ResponseEntity<>(pages, new HttpHeaders(), HttpStatus.OK);
			}
		} catch (InvalidPostException e) {
			LOGGER.error("InvalidPostException occured, message:" + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An exception occured, message : " + e.getMessage());
		}
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
