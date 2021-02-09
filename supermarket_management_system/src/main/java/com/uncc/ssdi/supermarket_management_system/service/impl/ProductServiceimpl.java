package com.uncc.ssdi.supermarket_management_system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.repository.ProductRepository;
import com.uncc.ssdi.supermarket_management_system.service.ProductService;
import com.uncc.ssdi.supermarket_management_system.util.ResourceNotFoundException;
import com.uncc.ssdi.supermarket_management_system.vo.CashierVo;
import com.uncc.ssdi.supermarket_management_system.vo.ProductVo;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	private final Logger m_logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ResponseEntity<ProductVo> addProduct(ProductVo productVo) {
		
		m_logger.info("saving the user to database");
		HttpStatus status=HttpStatus.OK;
		
//		ExampleMatcher productMatcher = ExampleMatcher.matching()
//				  .withIgnorePaths("id") 
//				  .withMatcher("name", new ExampleMatcher.MatcherConfigurer<ExampleMatcher.GenericPropertyMatcher>() {
//	                  @Override
//	                  public void configureMatcher(ExampleMatcher.GenericPropertyMatcher matcher) {
//	                      matcher.ignoreCase();
//	                  }
//	              });
//		
//		
//		Product productprobe = new Product();
//		productprobe.setName(productVo.getName());
//		Example<Product> productexample = Example.of(productprobe, productMatcher);
//		boolean productexists = productRepository.exists(productexample);
//		System.out.println("product name :"+productexists);
		
		
			Product product = new Product();
			try {
				product.setName(productVo.getName());
				product.setDescription(productVo.getDescription());
				product.setPrice(productVo.getPrice());
				product.setQuantity(productVo.getQuantity());
				product.setUnit(productVo.getUnit());
				productRepository.save(product);
				
			}
			catch(Exception e) {
		    	System.out.println(e.toString());
		    	status=HttpStatus.INTERNAL_SERVER_ERROR;
		    }
			
			m_logger.info("user saved to database");
		
	    return ResponseEntity.status(status).body(productVo);
		
	}

	public List<ProductVo> getAllProducts() {		
		List<ProductVo> vos=new ArrayList<>();
		
		for(Product product:productRepository.findAll()) {
			
			ProductVo productVo = new ProductVo();
			productVo.setProduct_id(product.getProduct_id());
			productVo.setDescription(product.getDescription());
			productVo.setName(product.getName());
			productVo.setPrice(product.getPrice());
			productVo.setUnit(product.getUnit());
			productVo.setQuantity(product.getQuantity());
			vos.add(productVo);
		
		}
		return vos;
	}

	@Override
	public ProductVo getProductById(int pid) {
		
		System.out.println("prpduct for id: "+pid);
		
		Product product = new Product();
		
		product = productRepository.getOne(pid);
		ProductVo productVo = new ProductVo();
		productVo.setProduct_id(product.getProduct_id());
		productVo.setDescription(product.getDescription());
		productVo.setName(product.getName());
		productVo.setPrice(product.getPrice());
		productVo.setUnit(product.getUnit());
		productVo.setQuantity(product.getQuantity());
		
		return productVo;
		
	}

	@Override
	public ResponseEntity<?> deleteProduct(int productId) {
		 Product product = productRepository.findById(productId)
			
		 .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		 productRepository.delete(product);

		return ResponseEntity.ok().build();
	}

	
	
	@Override
	public ProductVo getProductByName(String pname) {
		
		Product product = new Product();
		
		product = productRepository.findByName(pname);
		
		ProductVo productVo = new ProductVo();
		productVo.setProduct_id(product.getProduct_id());
		productVo.setDescription(product.getDescription());
		productVo.setName(product.getName());
		productVo.setPrice(product.getPrice());
		productVo.setUnit(product.getUnit());
		productVo.setQuantity(product.getQuantity());
		
		return productVo;
	}

	@Override
	public ResponseEntity<ProductVo> updateProduct(ProductVo productVo) {
		
		HttpStatus status=HttpStatus.OK;
		ProductVo updatedproduct = new ProductVo();
		
		try {
			
			Product product = productRepository.getOne(productVo.getProduct_id());
			product.setName(productVo.getName());
			product.setDescription(productVo.getDescription());
			product.setPrice(productVo.getPrice());
			product.setUnit(productVo.getUnit());
			product.setQuantity(productVo.getQuantity());
			productRepository.save(product);
			
		}
		catch(Exception e) {
	    	System.out.println(e.toString());
	    	status=HttpStatus.INTERNAL_SERVER_ERROR;
	    }
		
		try {
			
			Product product = productRepository.getOne(productVo.getProduct_id());
			System.out.println("Updated product retrived from DB : "+product);
			updatedproduct.setProduct_id(product.getProduct_id());
			updatedproduct.setName(product.getName());
			updatedproduct.setDescription(product.getDescription());
			updatedproduct.setPrice(product.getPrice());
			updatedproduct.setUnit(product.getUnit());
			updatedproduct.setQuantity(product.getQuantity());
			
		} catch (Exception e) {
			System.out.println(e.toString());
	    	status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		m_logger.info("user saved to database");
	
    return ResponseEntity.status(status).body(updatedproduct);
	}
	
}
