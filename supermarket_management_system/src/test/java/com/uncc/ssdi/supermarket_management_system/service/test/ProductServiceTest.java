package com.uncc.ssdi.supermarket_management_system.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.uncc.ssdi.supermarket_management_system.SupermarketManagementSystemApplication;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.repository.ProductRepository;
import com.uncc.ssdi.supermarket_management_system.service.impl.ProductServiceimpl;
import com.uncc.ssdi.supermarket_management_system.vo.ProductVo;
import com.uncc.ssdi.supermarket_management_system.vo.TreeVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketManagementSystemApplication.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository; 
	
	@InjectMocks
	private ProductServiceimpl productServiceimpl;
	
	@Before
	public void setup() {
	
		MockitoAnnotations.initMocks(this);
		List<Product> products = new ArrayList<Product>();
		
		Product product1 = new Product(0, "testProductName1", "10", "testDescription1", "100","lb");
		Product product2 = new Product(0, "testProductName2", "20", "testDescription2", "200","lb");
		Product product3 = new Product(0, "testProductName3", "30" , "testDescription3", "300","lb");
		product1.setProduct_id(1);
		product2.setProduct_id(2);
		product3.setProduct_id(3);
		
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		when(productRepository.findAll()).thenReturn(products);
		when(productRepository.getOne(1)).thenReturn(product1);
		when(productRepository.getOne(2)).thenReturn(product2);
		when(productRepository.getOne(3)).thenReturn(product3);
		
		
		when(productRepository.save(product1)).thenReturn(product1);
		
	
		
	}
	
	@Test
	public void findAllProductsTest() throws Exception{
		
		List<ProductVo> actual = productServiceimpl.getAllProducts();
		
		assertEquals("testProductName1",actual.get(0).getName());
		assertEquals("testProductName2",actual.get(1).getName());
		assertEquals("testProductName3",actual.get(2).getName());
		
		assertNotEquals("10", actual.get(1).getPrice());
		assertNotEquals("200", actual.get(2).getPrice());
		
	}	
	
	
	@Test
	public void findProductByIdTest() throws Exception{
		
		ProductVo actual = productServiceimpl.getProductById(1);
		
		assertEquals("testProductName1",actual.getName());
		assertNotEquals("220", actual.getPrice());
	}
	
	@Test
	public void addProductTest() throws Exception{
		ProductVo product = new ProductVo(1, "testProductName1", "10", "testDescription1", "100","lb");
		ResponseEntity<ProductVo> actual = productServiceimpl.addProduct(product);
		
		assertNotNull(actual.getBody().getName());
		assertTrue(actual.getBody().getProduct_id() == 1);
		assertEquals("testProductName1",actual.getBody().getName());
		
	}
	
	@Test
	public void updateProductTest() throws Exception{
		
		ProductVo product = new ProductVo(1, "testProductName1", "10", "testDescription1", "100","lb");
		ResponseEntity<ProductVo> actual = productServiceimpl.updateProduct(product);
		
		assertNotNull(actual.getBody().getName());
		assertTrue(actual.getBody().getProduct_id() == 1);
		assertEquals("testProductName1",actual.getBody().getName());
		
	}

}
