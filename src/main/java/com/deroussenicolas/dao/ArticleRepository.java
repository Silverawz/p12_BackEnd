package com.deroussenicolas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Article;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface ArticleRepository extends JpaRepository <Article, Long> {
	
	@Query("from Article where active=?1")
	List<Article> findAllArticleActive(boolean isActive);

}
