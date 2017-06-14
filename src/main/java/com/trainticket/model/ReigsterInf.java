package com.trainticket.model;

//注册信息汇总
public class ReigsterInf {
	private String userName;//账户名
	private String password;//密码
	private String trueName;//真实姓名
	private String phone;//手机号
	private String email;//email
	private String type;//票种
	private String iType;//证件类型
	private String idCode;//身份证号
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
	
	public ReigsterInf(String userName, String trueName, String type, String iType, String idCode) {
		this.userName = userName;
		this.trueName = trueName;
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
