package com.deroussenicolas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deroussenicolas.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository <Article, Long> {

}
