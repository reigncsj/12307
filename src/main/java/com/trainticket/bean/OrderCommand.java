package com.trainticket.bean;

public class OrderCommand {
	private String tCode;
	private String start;
	private String end;
	private String date;
	private String username;
	private String ucode;
	private String ltype;
	private String code;
	public OrderCommand() {
	}

	
	public String gettCode() {
		return tCode;
	}


	public void settCode(String tCode) {
		this.tCode = tCode;
	}


	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}


	public String getEnd() {
		return end;
	}


	public void setEnd(String end) {
		this.end = end;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUcode() {
		return ucode;
	}


	public void setUcode(String ucode) {
		this.ucode = ucode;
	}


	public String getLtype() {
		return ltype;
	}


	public void setLtype(String ltype) {
		this.ltype = ltype;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public OrderCommand(String tCode, String tStart, String tEnd, String oDate, String username, String ucode,
			String ltype, String code) {
		this.tCode = tCode;
		this.start = tStart;
		this.end = tEnd;
		this.date = oDate;
		this.username = username;
		this.ucode = ucode;
		this.ltype = ltype;
		this.code = code;
	}
	
	
}
