package com.uncc.ssdi.supermarket_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	public int product_id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name="quantity")
	public String quantity;
	
	@Column(name="description")
	public String description;
	
	@Column(name="price")
	public String price;
	
	@Column(name="unit")
	public String unit;
	
	
	public Product(int product_id, String name, String quantity, String description, String price, String unit) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
		this.unit = unit;
	}

	public Product() {
		
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", quantity=" + quantity + ", description="
				+ description + ", price=" + price + ", unit=" + unit + "]";
	}

	

}
