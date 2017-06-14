package com.trainticket.bean;

//中转条件汇总
public class TransferInf {
	private String start;//出发地
	private String transfer;//中转地
	private	String end;//到达地
	private int time=60;//间隔最小时间
	private String date;//日期
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private int firstType=1;//第一趟等级
	private int secondType=1;//第二趟等级
	private int midNight=1;//是否午夜中转
	private int sameStation=0;//同站中转
	public TransferInf(String start, String transfer, String end) {
		this.start = start;
		this.transfer = transfer;
		this.end = end;
	}
	
	public TransferInf(String start, String transfer, String end, int time, String date) {
		this.start = start;
		this.transfer = transfer;
		this.end = end;
		this.time = time;
		this.date = date;
	}
	public TransferInf(String start, String transfer, String end, int time, int firstType, int secondType,
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
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
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
