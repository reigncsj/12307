package com.trainticket.bean;

public class OrderCommand {
	private String tCode;
	private String tStart;
	private String tEnd;
	private String oDate;
	private String username;
	private String ucode;
	private String ltype;
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public OrderCommand() {
	}
	public String gettCode() {
		return tCode;
	}
	public String getUcode() {
		return ucode;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public void settCode(String tCode) {
		this.tCode = tCode;
	}
	public String getStart() {
		return tStart;
	}
	public void setStart(String start) {
		this.tStart = start;
	}
	public String getEnd() {
		return tEnd;
	}
	public void setEnd(String end) {
		this.tEnd = end;
	}
	public String getDate() {
		return oDate;
	}
	public void setDate(String date) {
		this.oDate = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getLtype() {
		return ltype;
	}
	public void setType(String type) {
		this.ltype = type;
	}
	public OrderCommand(String tCode, String tStart, String tEnd, String oDate, String username, String ucode,
			String ltype, String code) {
		this.tCode = tCode;
		this.tStart = tStart;
		this.tEnd = tEnd;
		this.oDate = oDate;
		this.username = username;
		this.ucode = ucode;
		this.ltype = ltype;
		this.code = code;
	}
	
	
}
