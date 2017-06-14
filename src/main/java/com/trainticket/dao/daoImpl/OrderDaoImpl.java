package com.trainticket.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import com.trainticket.bean.Order;
import com.trainticket.bean.Passager;
import com.trainticket.dao.OrderDao;
import com.trainticket.exception.DBException;

@Repository("orderDao")
//对订单表进行的操作
public class OrderDaoImpl implements OrderDao {
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	//插入一个新的订单
	@Override
	public void insertOrder(Order o) throws DBException {
		try{
			//获取对应的sql语句并执行
			template.execute(getSql(o));
		}
		catch(Exception e){
			throw new DBException();
		}
	}
	//获取相应的sql语句
	private String getSql(Order o){
		String sql="insert into orderInf values('"+o.getOrderNo()+"','"+o.gettCode()+
				"','"+o.getStart()+"','"+o.getDate()+"','"+o.getEnd()+"','"+o.getUsername()+
				"','"+o.getUcode()+"','"+o.getPrice()+"','"+o.getLocation()+"',"+o.getPaid()+",'"+o.getLtype()+"',"
						+ "'"+o.getStartTime()+"','"+o.getEndTime()+"','"+o.getLishi()+"','"+o.getCode()+"');";
		return sql;
	}

	//通过用户名获得订单数据
	@Override
	public List<Order> getOrderByName(String username) throws DBException {
		try{
			String sql="select orderNo,tCode,start,date,end,username,orderInf.ucode,price,location,paid,ltype,startTime,endTime,lishi,code,name from orderInf,trueuserinf where Username = '"+username+"'"
					+ " and orderInf.UCode=trueuserinf.ucode Order BY date desc";
			//将返回的数据进行封装，生成一个 数列
			return (List<Order>)template.query(sql, new BeanPropertyRowMapper(Order.class));
		}
		catch(Exception e){
			//如果过程报错就抛出给处理
			throw new DBException();
		}
	}

	//通过订单号获得订单数据
	@Override
	public Order getOrderByNo(String orderno) throws DBException {
		try{
			String sql="select orderNo,tCode,start,date,end,username,orderInf.ucode,price,location,paid,ltype,startTime,endTime,lishi,code,name from orderInf,trueuserinf where OrderNo = "+orderno+""
					+ " and orderInf.UCode=trueuserinf.ucode";
			//将获取订单数据进行封装
			List<Order> o=template.query(sql, new BeanPropertyRowMapper(Order.class));
			return o.get(0);
		}
		catch(Exception e){
			throw new DBException();
		}
	}

	//修改订单的支付状态
	@Override
	public void setOrderPaid(String no) throws DBException {
		try{
			String sql="update orderInf set paid=1 where orderNo="+no;
			int i=template.update(sql);
			//判断是否有信息发生改变决定操作是否成功
			if(i==0)
				throw new DBException();
		}
		catch(Exception e){
			throw new DBException();
		}
	}

	//获取未支付的订单
	@Override
	public List<Order> getUnPaidOrder(String name) throws DBException {
		try{
			String sql="select orderNo,tCode,start,date,end,username,orderInf.ucode,price,location,paid,ltype,startTime,endTime,lishi,code,name from orderInf,trueuserinf where Username = '"+name+"' and paid = 0"
					+ " and orderInf.UCode=trueuserinf.ucode Order BY date desc";
			//将获取到的订单数据进行封装并生成一个数组返回
			return (List<Order>)template.query(sql, new BeanPropertyRowMapper(Order.class));
		}
		catch(Exception e){
			throw new DBException();
		}
	}
	//获取已支付的订单
	@Override
	public List<Order> getPaidOrder(String name) throws DBException {
		try{
			String sql="select orderNo,tCode,start,date,end,username,orderInf.ucode,price,location,paid,ltype,startTime,endTime,lishi,code,name from orderInf,trueuserinf where Username = '"+name+"' and paid = 1 "
					+ " and orderInf.UCode=trueuserinf.ucode Order BY date desc";
			//将获取到的订单数据进行封装并生成一个数组返回
			return (List<Order>)template.query(sql, new BeanPropertyRowMapper(Order.class));
		}
		catch(Exception e){
			throw new DBException();
		}
	}
	@Override
	public void deleteOrder(String no, String username) {
		try{
			String sql="delete from orderInf where orderNo="+no+" and username="+username ;
			//将获取到的订单数据进行封装并生成一个数组返回
			template.update(sql);
		}
		catch(Exception e){
		}
	}
}
