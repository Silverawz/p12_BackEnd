package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

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

}
