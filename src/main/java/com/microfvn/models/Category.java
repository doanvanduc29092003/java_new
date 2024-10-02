package com.microfvn.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name =  "categoryName")
	private String categoryName;
	@Column(name = "categoryStatus")
	private Boolean categoryStatus;
	@Column(name = "image")
	private String image;
	@OneToMany(mappedBy = "category")
	private Set<Product> products;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(Integer id, String categoryName, Boolean categoryStatus, Set<Product> products,String image) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryStatus = categoryStatus;
		this.products = products;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(Boolean categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
}
