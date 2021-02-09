package com.uncc.ssdi.supermarket_management_system;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.uncc.ssdi.supermarket_management_system.service.StartupService;

@SpringBootApplication
public class SupermarketManagementSystemApplication {

	private static final Logger m_logger = LoggerFactory.getLogger(SupermarketManagementSystemApplication.class);
	
	@Autowired
	StartupService startupservice;
	
	@PostConstruct	
	public void init(){		
		startupservice.initilizeData();
		m_logger.info("Initializing Namelist Tool");
	}
	public static void main(String[] args) {
		SpringApplication.run(SupermarketManagementSystemApplication.class, args);
	}

}
