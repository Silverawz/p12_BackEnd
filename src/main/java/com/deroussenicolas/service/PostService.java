package com.deroussenicolas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deroussenicolas.entities.Post;
import com.deroussenicolas.exception.InvalidPostException;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface PostService {

	Page<Post> findPostsFromTopic(Long idLong, Pageable pageable) throws InvalidPostException;

}
