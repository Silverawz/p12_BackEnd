package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
