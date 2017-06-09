package com.trainticket.bean;

public class TimeResult {
	private int timeBetween;
	public TimeResult() {
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public TimeResult(int sameDay, int day) {
		this.timeBetween = sameDay;
		this.day = day;
	}
	public int getTimeBetween() {
		return timeBetween;
	}

	public void setTimeBetween(int timeBetween) {
		this.timeBetween = timeBetween;
	}
	private int day;
	
	
}
