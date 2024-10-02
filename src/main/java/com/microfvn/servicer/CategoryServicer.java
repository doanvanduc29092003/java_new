package com.microfvn.servicer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microfvn.models.Category;

public interface CategoryServicer {
	List<Category> getAll();
	Boolean create(Category category);
	Category findById(Integer id);
	Boolean update(Category category);
	Boolean delete(Integer id);
	List<Category> searchCategory(String keyword);
	
	//Phân trang JAVA
	Page<Category> getAll(Integer pageNo);
	Page<Category> searchCategory(String keyword, Integer pageNo);
}
