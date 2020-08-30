package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
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
	
	
	//ar left join fetch
	// AND a.active=?2
	//@Query(value = "select a from Category c, Article a join fetch a.user where id_category = :idCategory and a.active = :active")
	//c.id_category=?1 and 
	//join fetch a.user
	//@Query(value = "select a from Article a cht join cht.id_category c where a.active = :active and c.id_category = :idCategory")
	@Query(value = "select a from Category c, Article a join fetch a.user where id_category=?1 and a.active=?2")
	List<Article> findAllArticlesActiveByCategoryId(Long idCategory, boolean active);*/
}
