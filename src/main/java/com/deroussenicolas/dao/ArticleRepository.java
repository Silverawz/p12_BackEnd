package com.deroussenicolas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Article;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>, JpaRepository <Article, Long> {
	
	
	@Query(nativeQuery=true, value = "SELECT * FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where a.user_id_user=?1 group by a.id_article order by a.date desc",
			countQuery = "SELECT count(*) FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where a.user_id_user=?1 group by a.id_article order by a.date desc")
	Page<Article> findAllArticlesFromUser(Long user_id,  Pageable pageable);
	
	
	@Query(nativeQuery=true, value = "SELECT * FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where b.category_id_category=?1 and a.active=?2 order by a.date desc", 
			countQuery = "SELECT count(*) FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where b.category_id_category=?1 and a.active=?2 order by a.date desc")
	Page<Article> findAllArticlesActiveByCategoryId(Long id, boolean active, Pageable pageable);

	@Query(value = "select a from Article a where a.id_article=?1")
	Article findArticleById(Long id);
	


}
