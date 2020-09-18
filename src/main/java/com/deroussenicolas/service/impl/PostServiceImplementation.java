package com.deroussenicolas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.PostRepository;
import com.deroussenicolas.entities.Post;
import com.deroussenicolas.exception.InvalidPostException;
import com.deroussenicolas.service.PostService;

/**
 * implements PostService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("PostServiceImplementation")
@Transactional
public class PostServiceImplementation implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	
	/**
	 * Find all posts from one topic with the topic id
	 * @return the posts list as Page
	 * @exception InvalidPostException
	 */
	@Override
	public Page<Post> findPostsFromTopic(Long idLong, Pageable pageable) throws InvalidPostException {
		if(pageable == null) {
			throw new InvalidPostException("pageable is null");
		}
		return postRepository.findPostsFromTopic(idLong, pageable);
	}
}
