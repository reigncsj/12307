package com.trainticket.bean;

public class Passager {
	private String Name;
	private String Type;
	private String Utype;
	private String UCode;
	
	public Passager() {
	}
	public Passager(String Name, String Type, String Utype, String UCode) {
		this.Name = Name;
		this.Type = Type;
		this.Utype = Utype;
		this.UCode = UCode;
	}
	public Passager(String trueName, String type) {
		this.Name = trueName;
		this.Type = type;
	}
	public String getName() {
		return Name;
	}
	public void setName(String trueName) {
		this.Name = trueName;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public String getUType() {
		return Utype;
	}
	public void setUType(String iType) {
		this.Utype = iType;
	}
	public String getUCode() {
		return UCode;
	}
	public void setUCode(String idCode) {
		this.UCode = idCode;
	}

}
