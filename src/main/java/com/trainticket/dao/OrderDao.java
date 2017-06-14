package com.trainticket.dao;

import java.util.List;

import com.trainticket.exception.DBException;
import com.trainticket.model.Order;
import com.trainticket.model.Passager;

import net.sf.json.JSONObject;

public interface OrderDao {
	public void insertOrder(Order o) throws DBException;
	public List<Order> getOrderByName(String username)throws DBException;
	public Order getOrderByNo(String orderno)throws DBException;
	public void setOrderPaid(String no)throws DBException;
	public List<Order> getUnPaidOrder(String name) throws DBException;
	public List<Order> getPaidOrder(String name) throws DBException;
	public void deleteOrder(String no,String username);
}
