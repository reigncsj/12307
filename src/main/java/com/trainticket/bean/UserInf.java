package com.trainticket.bean;

public class UserInf {
	private String phone;
	private String email;

	public UserInf(String phone, String email) {
		this.phone = phone;
		this.email = email;
	}
	public UserInf() {
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
