package com.uncc.ssdi.supermarket_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uncc.ssdi.supermarket_management_system.vo.User;

@RestController
@RequestMapping({ "/api" })
public class LoginController {
	

	@GetMapping(produces = "application/json")
	@RequestMapping({ "/admin/validateLogin" })
	public User validateLogin(@RequestHeader HttpHeaders headers) {
		System.out.println("in authentiaction service");
		return new User("admin successfully authenticated");
	}
	
}
