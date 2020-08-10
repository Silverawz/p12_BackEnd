package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository <Topic, Long> {

}
