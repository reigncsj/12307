package com.trainticket.bean;

import java.util.List;

public class QueryInf {

	private String start;
	private String end;
	private String date;
	private String type;
	private String transfer;
	private String startCity;
	private String endCity;
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	private int num;
	private List<String> startStation;
	private List<String> endStation;
	
	public List<String> getStartStation() {
		return startStation;
	}
	public void setStartStation(List<String> startStation) {
		this.startStation = startStation;
	}
	public List<String> getEndStation() {
		return endStation;
	}
	public void setEndStation(List<String> endStation) {
		this.endStation = endStation;
	}
	public QueryInf(String start, String end, String date, int num) {
		this.start = start;
		this.end = end;
		this.date = date;
		this.num = num;
	}
	public QueryInf(String start, String end, int num) {
		this.start = start;
		this.end = end;
		this.num = num;
	}
	public QueryInf(String start, String end) {
		this.start = start;
		this.end = end;
	}
	public QueryInf(String start, String end, String date) {
		this.start = start;
		this.end = end;
		this.date = date;
	}
	public QueryInf(String start, String end, String date, String type) {
		this.start = start;
		this.end = end;
		this.date = date;
		this.type = type;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
