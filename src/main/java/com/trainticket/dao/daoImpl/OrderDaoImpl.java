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
public class OrderDaoImpl implements OrderDao {
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	@Override
	public void insertOrder(Order o) throws DBException {
		try{
			template.execute(getSql(o));
		}
		catch(Exception e){
			throw new DBException();
		}
	}
	
	private String getSql(Order o){
		String sql="insert into orderInf values('"+o.getOrderNo()+"','"+o.gettCode()+
				"','"+o.getStart()+"','"+o.getDate()+"','"+o.getEnd()+"','"+o.getUsername()+
				"','"+o.getUcode()+"','"+o.getPrice()+"','"+o.getLocation()+"',"+o.getPaid()+",'"+o.getLtype()+"',"
						+ "'"+o.getStartTime()+"','"+o.getEndTime()+"','"+o.getLishi()+"','"+o.getCode()+"');";
		return sql;
	}

	@Override
	public List<Order> getOrderByName(String username) throws DBException {
		try{
			String sql="select * from orderInf where Username = '"+username+"'";
			return (List<Order>)template.query(sql, new BeanPropertyRowMapper(Order.class));
		}
		catch(Exception e){
			throw new DBException();
		}
	}

	@Override
	public Order getOrderByNo(String orderno) throws DBException {
		try{
			
			String sql="select * from orderInf where OrderNo = "+orderno+"";
			List<Order> o=template.query(sql, new BeanPropertyRowMapper(Order.class));
			return o.get(0);
		}
		catch(Exception e){
			throw new DBException();
		}
	}

	
	@Override
	public void setOrderPaid(String no) throws DBException {
		try{
			String sql="update orderInf set paid=1 where orderNo="+no;
			int i=template.update(sql);
			if(i==0)
				throw new DBException();
		}
		catch(Exception e){
			throw new DBException();
		}
	}


}
