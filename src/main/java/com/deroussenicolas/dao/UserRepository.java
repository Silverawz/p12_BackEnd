package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

}
