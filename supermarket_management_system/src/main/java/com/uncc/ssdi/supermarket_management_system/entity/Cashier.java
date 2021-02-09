package com.uncc.ssdi.supermarket_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cashier")
public class Cashier {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	public int cashier_id;
	
	@Column(name = "username")
	public String username;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "email")
	public String email;
	
	@Column(name = "password")
	public String password;
	
	@Column(name = "contact")
	public String contact;
	
	@OneToOne
	@JoinColumn(name = "admin_id")
	public Admin admin;
	
	public Cashier() {
		
	}



	public Cashier(int cashier_id, String username, String name, String email, String password, String contact,
			Admin admin) {
		super();
		this.cashier_id = cashier_id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.admin = admin;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
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

	
	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Cashier [cashier_id=" + cashier_id + ", username=" + username + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", contact=" + contact + ", admin=" + admin + "]";
	}
	
}
