package com.uncc.ssdi.supermarket_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.service.AdminService;

@RestController
@RequestMapping({ "/api" })
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/api")
	public String startTheProject() {
		
		return "Hello Your application is working";
		
	}
	
	
	@RequestMapping(value="/admindetails", method= RequestMethod.GET)
	public List<Admin> findAllAdmins(){
		
		return adminService.findAllAdmins();
		
	}

}
