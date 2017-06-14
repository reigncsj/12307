package com.trainticket.bean;

//用来接收登录请求信息
public class LoginUser {
	private String userName;//用户名
	private String password;//用户密码
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
	public LoginUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
}
