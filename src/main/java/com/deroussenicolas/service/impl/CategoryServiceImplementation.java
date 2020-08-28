package com.deroussenicolas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deroussenicolas.dao.CategoryRepository;
import com.deroussenicolas.entities.Category;
import com.deroussenicolas.service.CategoryService;

/**
 * implements CategoryService
 * 
 * @author deroussen nicolas
 * 
 */
@Service("CategoryServiceImplementation")
@Transactional
public class CategoryServiceImplementation implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findCategoryByCategoryName(String description) {
		return categoryRepository.findCategoryByCategoryName(description);
	}
}
