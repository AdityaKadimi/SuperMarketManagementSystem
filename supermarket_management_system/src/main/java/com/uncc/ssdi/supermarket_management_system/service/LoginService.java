package com.uncc.ssdi.supermarket_management_system.service;

import org.springframework.security.core.Authentication;

import com.uncc.ssdi.supermarket_management_system.vo.User;

public interface LoginService {

	Authentication authenticate(String userName, String password);

	User validatecadshierLogin();

}
