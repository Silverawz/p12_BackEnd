package com.deroussenicolas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.PostRepository;
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
}
