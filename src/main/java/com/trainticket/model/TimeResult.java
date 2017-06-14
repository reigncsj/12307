package com.trainticket.model;

//时间间隔
public class TimeResult {
	private int timeBetween;//分钟差距
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
	private int day;//天数差距
	
	
}
