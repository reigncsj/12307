package com.trainticket.service;

import com.trainticket.bean.OrderCommand;

import net.sf.json.JSONObject;

public interface OrderService {
	public JSONObject buildOrder(OrderCommand order);
	public JSONObject buildOrder(OrderCommand order,OrderCommand order2);
	public JSONObject payOrder(String no);
	public JSONObject getOrderByName(String username);
	public JSONObject getOrderByNo(String no);
	public JSONObject getUnPaidOrder(String name);
	public JSONObject getPaidOrder(String name);
}
