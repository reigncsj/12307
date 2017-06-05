package com.trainticket.bean;

public class timeResult {
	private int Daybetween;
	public timeResult() {
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public timeResult(int sameDay, int day) {
		this.Daybetween = sameDay;
		this.day = day;
	}
	public int getDaybetween() {
		return Daybetween;
	}

	public void setDaybetween(int daybetween) {
		Daybetween = daybetween;
	}
	private int day;
	
	
}
