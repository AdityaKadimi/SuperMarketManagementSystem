package com.uncc.ssdi.supermarket_management_system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.vo.ProductVo;

public interface ProductService {

	ResponseEntity<ProductVo> addProduct(ProductVo productVo);

	List<ProductVo> getAllProducts();

	ProductVo getProductById(int pid);

	ResponseEntity<?> deleteProduct(int productId);

	ProductVo getProductByName(String pname);

	ResponseEntity<ProductVo> updateProduct(ProductVo productVo);
	
	

}
