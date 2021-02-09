package com.uncc.ssdi.supermarket_management_system.vo;

import javax.persistence.Column;

public class ProductVo {

	public int product_id;
	
	public String name;
	
	public String quantity;
	
	public String description;
	
	public String price;
	
	public String unit;

	
	public ProductVo() {
		
	}


	public ProductVo(int product_id, String name, String quantity, String description, String price, String unit) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
		this.unit = unit;
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
		return "ProductVo [product_id=" + product_id + ", name=" + name + ", quantity=" + quantity + ", description="
				+ description + ", price=" + price + ", unit=" + unit + "]";
	}

	
}
