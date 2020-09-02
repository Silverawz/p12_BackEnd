package com.deroussenicolas.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Article;
import com.deroussenicolas.entities.Category;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>, JpaRepository <Article, Long> {
	

	
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
	 * */
	 //join fetch a.user
	
	//@Query(nativeQuery=true, value = "SELECT * FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where b.category_id_category=?1 and a.active=?2 order by a.date desc")
	//List<Article> findAllArticlesActiveByCategoryId(Long id, boolean active);

	/*
	@Query(nativeQuery=true, value = "SELECT * FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where a.user_id_user=?1 order by a.date desc")
	List<Article> findAllArticlesFromUser(Long user_id);
*/
	
	
	@Query(nativeQuery=true, value = "SELECT * FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where a.user_id_user=?1 order by a.date desc",
			countQuery = "SELECT * FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where a.user_id_user=?1 order by a.date desc")
	Page<Article> findAllArticlesFromUser(Long user_id,  Pageable pageable);
	
	
	@Query(nativeQuery=true, value = "SELECT * FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where b.category_id_category=?1 and a.active=?2 order by a.date desc", 
			countQuery = "SELECT count(*) FROM Article a JOIN Category_has_article b ON a.id_article=b.Article_id_article where b.category_id_category=?1 and a.active=?2 order by a.date desc")
	Page<Article> findAllArticlesActiveByCategoryId(Long id, boolean active, Pageable pageable);

	@Query(value = "select a from Article a where a.id_article=?1")
	Article findArticleById(Long id);

}
