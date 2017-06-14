package com.trainticket.bean;

//列车信息
public class Train {

	private String arriveTime="";//到达时间
	private String endStationName="";//到达站
	private String startStationName="";//出发站
	private String startTime="";//出发时间
	private String stationName="";//车站名称
	private String stationNo="";//车站序号
	private String stationTrainCode="";//列车代码
	private String stopoverTime="";//停车时间
	private String trainClassName="";//列车等级
	private String otherCode="";//其它代码
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
