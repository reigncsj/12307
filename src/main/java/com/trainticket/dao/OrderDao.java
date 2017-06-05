package com.trainticket.dao;

import java.util.List;

import com.trainticket.bean.Order;
import com.trainticket.bean.Passager;
import com.trainticket.exception.DBException;

public interface OrderDao {
	public void insertOrder(Order o) throws DBException;
	public List<Order> getOrderByName(String username)throws DBException;
	public Order getOrderByNo(String orderno)throws DBException;
	public void setOrderPaid(String no)throws DBException;
}
