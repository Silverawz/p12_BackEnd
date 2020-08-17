package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.User;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	@Query("from User where id_user=?1")
	public User findOneUserById(Long id);
	
}
