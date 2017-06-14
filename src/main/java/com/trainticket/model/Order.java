package com.trainticket.model;

//附加的订单信息
public class Order extends OrderCommand {
	private String orderNo;//订单号
	private String price;//票价
	private String paid;//支付状态
	private String location;//座位号
	private String startTime;//出发时间
	private String endTime;//到达时间
	private String lishi;//历时
	private String name;//乘车人姓名
	
	//以下是各种属性的构造方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Order() {
		super();
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLishi() {
		return lishi;
	}
	public void setLishi(String lishi) {
		this.lishi = lishi;
	}
	
}
