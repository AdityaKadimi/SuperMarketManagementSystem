package com.uncc.ssdi.supermarket_management_system.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uncc.ssdi.supermarket_management_system.SupermarketManagementSystemApplication;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.repository.ProductRepository;
import com.uncc.ssdi.supermarket_management_system.service.ProductService;
import com.uncc.ssdi.supermarket_management_system.service.impl.ProductServiceimpl;
import com.uncc.ssdi.supermarket_management_system.util.JSONUtil;
import com.uncc.ssdi.supermarket_management_system.vo.CashierVo;
import com.uncc.ssdi.supermarket_management_system.vo.ProductVo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketManagementSystemApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ProductControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Mock
	private ProductServiceimpl productServiceimpl;
	
	@Mock
	private ProductService productService;
	
	
	@Before
    public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		List<ProductVo> products = new ArrayList<ProductVo>();
		
		Product product1 = new Product(0, "testProductName1", "10", "testDescription1", "100","lb");
		Product product2 = new Product(0, "testProductName2", "20", "testDescription2", "200","lb");
		Product product3 = new Product(0, "testProductName3", "30" , "testDescription3", "300","lb");
		product1.setProduct_id(1);
		product2.setProduct_id(2);
		product3.setProduct_id(3);
		
				ProductVo productVo1 = new ProductVo(1, "testProductName1", "10", "testDescription1", "100","lb");
				ProductVo productVo2 = new ProductVo(0, "testProductName2", "20", "testDescription2", "200","lb"); productService.getAllProducts();
				ProductVo productVo3 = new ProductVo(0, "testProductName3", "30" , "testDescription3", "300","lb");productService.updateProduct(productVo1);
				
				productVo2.setProduct_id(2);
				productVo3.setProduct_id(3);
				ResponseEntity<ProductVo> responseproduct = new ResponseEntity<ProductVo>(productVo1, HttpStatus.OK);productService.addProduct(productVo1);
				
		products.add(productVo1);
		products.add(productVo2);
		products.add(productVo3);
		
		when(productServiceimpl.getAllProducts()).thenReturn(products);
		when(productServiceimpl.addProduct(productVo1)).thenReturn(responseproduct);
		when(productServiceimpl.updateProduct(productVo1)).thenReturn(responseproduct);
//		when(productRepository.getOne(1)).thenReturn(product1);
//		when(productRepository.getOne(2)).thenReturn(product2);
//		when(productRepository.getOne(3)).thenReturn(product3);
       
    }
	
	@Test
	public void getAllProductsTest() throws Exception{
		 
		mockMvc.perform(get("/api/products/all"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));
		 verify(productService,times(1)).getAllProducts();
	}
	
	@Test
	public void addProductTest() throws Exception {
		
		ProductVo product = new ProductVo(1, "testProductName1", "10", "testDescription1", "100","lb");
		mockMvc.perform(post("/api/addproduct")
				.content(JSONUtil.asJSONString(product))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			verify(productService,times(1)).addProduct(product);
	}
	
	@Test
	public void getProductByIdTest() throws Exception{
			
		Product product = new Product(1,"testProductName1", "10", "testDescription1", "100", "lb");
		
		product = productRepository.save(product);
			
			mockMvc.perform(get("/api/products/all")
					.content(JSONUtil.asJSONString(product))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
					
			List<Product> products = productRepository.findAll();
			
			mockMvc.perform(get("/api/productWithId/{pid}",products.get(0).getProduct_id())).andExpect(status().isOk());
			verify(productService,times(1)).getProductById(product.product_id); 			
		}
	
	@Test
	public void updateProductTest() throws Exception {
		
		ProductVo productVo1 = new ProductVo(1, "testProductName1", "10", "testDescription1", "100","lb");
		mockMvc.perform(post("/api/updateproduct")
				.content(JSONUtil.asJSONString(productVo1))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(productService,times(1)).updateProduct(productVo1); 
	}
	
	@Test
	@Ignore
	public void deleteProductTest() throws Exception {
		
		Product product = new Product(5, "testProductName1", "10", "testDescription1", "100","lb");
		
				productRepository.delete(product);
		
		mockMvc.perform((RequestBuilder) ((ResultActions) delete("/api/deletecashier/{id}")).andExpect(status().isOk()));
		
//		mockMvc.perform(get("/api/productWithId/{pid}", products.get(0).getProduct_id())).andExpect(status().isOk());
		
	}
	
		
}
