package com.trainticket.bean;
//乘车人完整信息
public class Passager {
	private String Name;//真实姓名
	private String Type;//票种
	private String Utype;//证件种类
	private String UCode;//身份证号
	
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
