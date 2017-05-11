package com.trainticket.bean;

public class ReigsterInf {
	private String userName;
	private String password;
	private String trueName;
	private String phone;
	private String email;
	private String type;
	private String iType;
	private String idCode;
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
	public ReigsterInf(String userName, String password, String trueName, String phone, String email, String type,
			String iType, String idCode) {
		this.userName = userName;
		this.password = password;
		this.trueName = trueName;
		this.phone = phone;
		this.email = email;
		this.type = type;
		this.iType = iType;
		this.idCode = idCode;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getiType() {
		return iType;
	}
	public void setiType(String iType) {
		this.iType = iType;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
}
