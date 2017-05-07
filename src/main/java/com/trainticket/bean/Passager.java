package com.trainticket.bean;

public class Passager {
	private String trueName;
	private String type;
	private String iType;
	private String idCode;
	
	public Passager() {
	}
	public Passager(String trueName, String type, String iType, String idCode) {
		this.trueName = trueName;
		this.type = type;
		this.iType = iType;
		this.idCode = idCode;
	}
	public Passager(String trueName, String type) {
		this.trueName = trueName;
		this.type = type;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
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
