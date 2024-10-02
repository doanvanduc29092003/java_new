package com.microfvn.servicer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.microfvn.models.Category;
import com.microfvn.models.Product;

public interface ProductServicer {
    List<Product> getAll();
    Boolean create(Product product);
    Product findById(Integer id);
    Boolean update(Product product);
    Boolean delete(Integer id);
    
    List<Product> searchProduct(String keyword);
	
	//Phân trang JAVA
	Page<Product> getAll(Integer pageNo);
	Page<Product> searchProduct(String keyword, Integer pageNo);
}
