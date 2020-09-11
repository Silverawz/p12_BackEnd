package com.deroussenicolas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deroussenicolas.entities.Category;
import com.deroussenicolas.service.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sport")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories/all")
	public ResponseEntity<List<Category>> categoriesList() {
	      return new ResponseEntity<List<Category>>(categoryService.findAllCategories(), new HttpHeaders(), HttpStatus.OK); 
	}
	
}
