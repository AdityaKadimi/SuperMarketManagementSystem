package com.uncc.ssdi.supermarket_management_system.repository.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uncc.ssdi.supermarket_management_system.SupermarketManagementSystemApplication;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.repository.ProductRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketManagementSystemApplication.class)
@Transactional
@Profile("test")
public class ProductRepositoryTest {

	private static final Logger m_logger = LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Before
	public void setup() throws Exception{
		m_logger.info("Initializing ProductRepository Test");
	}
	
	
	@Test
	public void addProductTest(){
		m_logger.info("Add Product Test");
		Product product = new Product(1, "testProductName1", "10", "testDescription1", "100","lb");
		product = productRepository.save(product);
		Assert.assertNotNull(product);
		product=productRepository.findByName("testProductName1");
		Assert.assertNotNull(product);
		Assert.assertTrue(product.getName().equalsIgnoreCase("testProductName1"));
		
	}
	
	
	@Test
	@Ignore
	public void findAllProductsTest(){
		m_logger.info("Find all products test");
		
		Product product1 = new Product(2, "testProductName1", "10", "testDescription1", "100","lb");
		Product product2 = new Product(3, "testProductName2", "20", "testDescription2", "200","lb");
		Product product3 = new Product(4, "testProductName3", "30" , "testDescription3", "300","lb");
		
		product1 = productRepository.save(product1);
		product2 = productRepository.save(product2);
		product3 = productRepository.save(product3);
		
		List<Product> products = productRepository.findAll();
		
		assertEquals("testDescription1",products.get(0).getDescription());
		assertEquals("testDescription2",products.get(1).getDescription());
		assertEquals("testDescription3",products.get(2).getDescription());
		
		product1 =productRepository.findByName("testProductName1");		
		Assert.assertNotNull(product1);
		product2 =productRepository.findByName("testProductName2");		
		Assert.assertNotNull(product2);
		product3 =productRepository.findByName("testProductName3");		
		Assert.assertNotNull(product3);
		
	}
	
	@Test
	public void updateProductTest(){
		m_logger.info("update Product Test");
		Product product = new Product(4, "testProductName4", "40", "testDescription4", "400","lb");
		Optional<Product> result;
		product = productRepository.save(product);
		Assert.assertNotNull(product);
		result = productRepository.findById(product.getProduct_id());
		Assert.assertNotNull(result);
		assertEquals("testDescription4",result.get().getDescription());
		product=productRepository.findByName("testProductName4");
		Assert.assertNotNull(product);
		Assert.assertTrue(product.getName().equalsIgnoreCase("testProductName4"));
		
	}
	
}
