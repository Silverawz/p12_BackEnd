package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Category;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {

}
