package com.trainticket.bean;

import java.util.List;

public class User {
	private String userName;
	private String password;
	private String trueName;
	public User(){
	}
	public User(String userName,String Password){
		this.userName=userName;
		this.password=Password;
	}
	
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
