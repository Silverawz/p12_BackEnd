package com.deroussenicolas.service;

import com.deroussenicolas.entities.Category;

/**
 * 
 * @author deroussen nicolas
 *
 */
public interface CategoryService {

	public Category findCategoryByCategoryName(String description);
}
