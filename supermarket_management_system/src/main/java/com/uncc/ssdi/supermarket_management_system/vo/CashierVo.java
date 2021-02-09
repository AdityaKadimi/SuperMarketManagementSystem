package com.uncc.ssdi.supermarket_management_system.vo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CashierVo {


	public int cashier_id;
	
	public String username;
	
	public String email;
	
	public String name;
	
	public String password;
	
	public String contact;
	
	public int admin_id;
	
	
	public CashierVo(int cashier_id, String username, String email, String name, String password, String contact,
			int admin_id) {
		super();
		this.cashier_id = cashier_id;
		this.username = username;
		this.email = email;
		this.name = name;
		this.password = password;
		this.contact = contact;
		this.admin_id = admin_id;
	}

	public CashierVo() {
		
	}

	public int getCashier_id() {
		return cashier_id;
	}

	public void setCashier_id(int cashier_id) {
		this.cashier_id = cashier_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CashierVo [cashier_id=" + cashier_id + ", username=" + username + ", email=" + email + ", name=" + name
				+ ", password=" + password + ", contact=" + contact + ", admin_id=" + admin_id + "]";
	}

}
