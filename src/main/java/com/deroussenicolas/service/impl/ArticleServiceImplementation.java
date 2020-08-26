package com.deroussenicolas.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.ArticleRepository;
import com.deroussenicolas.entities.Article;
import com.deroussenicolas.service.ArticleService;

/**
 * implements ArticleService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("ArticleServiceImplementation")
@Transactional
public class ArticleServiceImplementation implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public void save(Article article) {
		articleRepository.save(article);	
	}

	@Override
	public List<Article> findAllArticleActive(boolean isActive) {
		return articleRepository.findAllArticleActive(isActive);
	}
}
