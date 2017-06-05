package com.trainticket.bean;

public class TransferInf {
	private String start;
	private String transfer;
	private	String end;
	private String time;
	private int firstType=1;
	private int secondType=1;
	private int midNight=1;
	private int sameStation=0;
	private int betweenMinute=60;
	public int getBetweenMinute() {
		return betweenMinute;
	}
	public void setBetweenMinute(int betweenMinute) {
		this.betweenMinute = betweenMinute;
	}
	public TransferInf(String start, String transfer, String end) {
		this.start = start;
		this.transfer = transfer;
		this.end = end;
	}
	public TransferInf(String start, String transfer, String end, String time, int firstType, int secondType,
			int midNight, int sameStation) {
		this.start = start;
		this.transfer = transfer;
		this.end = end;
		this.time = time;
		this.firstType = firstType;
		this.secondType = secondType;
		this.midNight = midNight;
		this.sameStation = sameStation;
	}
	public int getSameStation() {
		return sameStation;
	}

	public void setSameStation(int sameStation) {
		this.sameStation = sameStation;
	}
	
	public TransferInf() {
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getFirstType() {
		return firstType;
	}
	public void setFirstType(int firstType) {
		this.firstType = firstType;
	}
	public int getSecondType() {
		return secondType;
	}
	public void setSecondType(int secondType) {
		this.secondType = secondType;
	}
	public int getMidNight() {
		return midNight;
	}
	public void setMidNight(int midNight) {
		this.midNight = midNight;
	}
}
