package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, Long> {

}
