package com.uncc.ssdi.supermarket_management_system.vo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;

public class CredentialsVO implements UserDetails{

	private String userName;
	
	private String password;
	
	private boolean active;
	
	
	public CredentialsVO() {
		
	}
	
	public CredentialsVO(Admin admin) {
		
		this.userName = admin.getUsername();
		this.password = admin.getPassword();
		this.active= true;
	}
	
	public CredentialsVO(Cashier cashier) {
		
		this.userName = cashier.getUsername();
		this.password = cashier.getPassword();
		this.active= true;
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	

}
