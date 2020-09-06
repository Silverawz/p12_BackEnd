package com.deroussenicolas.service;

import java.util.List;

import com.deroussenicolas.entities.Category;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface CategoryService {

	public Category findCategoryByCategoryName(String description);
	public List<Category> findAllCategories();
}
