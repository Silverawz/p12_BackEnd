package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Role;
/**
 * extends JpaRepository
 * 
 * @author deroussen nicolas
 * 
 */
@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

	@Query("from Role where description=?1")
	Role findSpecificRole(String description);
	

}
