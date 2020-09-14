package com.deroussenicolas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Topic;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface TopicRepository extends JpaRepository <Topic, Long> {

	@Query(nativeQuery=true, 
	value="SELECT * FROM Topic t JOIN Category_has_topic c ON t.id_topic=c.Topic_id_topic where c.category_id_category=?1 and t.active=?2  and t.title like %?3%", 
	countQuery = "SELECT * FROM Topic t JOIN Category_has_topic c ON t.id_topic=c.Topic_id_topic where c.category_id_category=?1 and t.active=?2  and t.title like %?3%")
	Page<Topic> findTopicsActiveByCategoryIdAndByTitleContains(Long id, boolean active, String titleContains, Pageable pageable);

}
