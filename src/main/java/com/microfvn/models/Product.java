package com.microfvn.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "productName")
	private String productName;
	@Column(name = "price")
	private Double price;
	@Column(name = "image")
	private String image;
	//@Column(name = "description")
	@Column(name = "description" , length = 4000)
	private String description;
	@Column(name = "quantity") // số lượng còn 
	private int quantity;
	@Column(name = "quantitySold") // số lượng đã bán 
	private int quantitySold;
	
	
	@ManyToOne
	@JoinColumn(name = "categoryId",referencedColumnName = "id")
	private Category category;
	
	public Product() {
		
	}

	public Product(Integer id, String productName, Double price, String image, String description, Category category, Integer quantity,Integer quantitySold) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.image = image;
		this.description = description;
		this.category = category;
		this.quantity = quantity;
		this.quantitySold = quantitySold;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantitySold() {
		return quantitySold;
	}
	
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	
}
