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
	

	
	/*@Query(value = "from Category, Article where id_category=?1") //,nativeQuery=true)
	@Query(value = "SELECT * FROM Category_has_article where Category_id_category= :id", nativeQuery = true) //,nativeQuery=true)
	List<List<Long>> findAllFootballArticlesActive(Long id);
	select u.email, r.role from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id=r.role_id) where u.email=?
	*/
	
	@Query(value = "select ar from Category, Article ar left join fetch ar.user where id_category=?1 AND ar.active=?2")
	List<Article> findAllFootballArticlesActive(Long id, boolean active);
}
