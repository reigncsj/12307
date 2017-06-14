package com.trainticket.model;

//联系信息
public class UserInf {
	private String phone;//手机
	private String email;//邮箱

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
