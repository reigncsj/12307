package com.trainticket.model;

import java.util.List;

//用户信息
public class User {
	private String userName;//账号名
	public User(String userName) {
		this.userName = userName;
	}
	private String password;//密码
	private String trueName;//真实姓名
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
