package com.deroussenicolas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Post;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface PostRepository extends JpaRepository <Post, Long> {

	@Query(nativeQuery=true, 
			value="SELECT * FROM Post p where p.topic_id_topic=?1", 
			countQuery = "SELECT * FROM Post p where p.topic_id_topic=?1")
	Page<Post> findPostsFromTopic(Long id_topic, Pageable pageable);

}
