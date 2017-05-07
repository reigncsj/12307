package com.trainticket.bean;

public class Train {

	private String arriveTime="";
	private String endStationName="";
	private String startStationName="";
	private String startTime="";
	private String stationName="";
	private String stationNo="";
	private String stationTrainCode="";
	private String stopoverTime="";
	private String trainClassName="";
	private String otherCode="";
	public void setArriveTime(String s){
		this.arriveTime=s;
	}
	public void setEndStationName(String s){
		this.endStationName=s;
	}
	public void setStartStationName(String s){
		this.startStationName=s;
	}
	public void setStartTime(String s){
		this.startTime=s;
	}
	public void setStationName(String s){
		this.stationName=s;
	}
	public void setStationNo(String s){
		this.stationNo=s;
	}
	public void setStopoverTime(String s){
		this.stopoverTime=s;
	}
	public void setTrainClassName(String s){
		this.trainClassName=s;
	}
	public void setStationTrainCode(String s){
		this.stationTrainCode=s;
	}
	public void setOtherCode(String s){
		this.otherCode=s;
	}
	
	public String getArriveTime(){
		return arriveTime;
	}
	public String getEndStationName(){
		return endStationName;
	}
	public String getStartStationName(){
		return startStationName;
	}
	public String getStartTime(){
		return startTime;
	}
	public String getStationName(){
		return stationName;
	}
	public String getStationNo(){
		return stationNo;
	}
	public String getStopoverTime(){
		return stopoverTime;
	}
	public String getTrainClassName(){
		return trainClassName;
	}
	public String getStationTrainCode(){
		return stationTrainCode;
	}
	public String getOtherCode(){
		return otherCode;
	}
}
