package com.trainticket.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.Order;
import com.trainticket.bean.Passager;
import com.trainticket.bean.User;
import com.trainticket.dao.UserDao;
import com.trainticket.exception.DBException;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	@Override
	public boolean login(LoginUser user) {
		boolean result=false;
		try{
			String sql="select count(*) from user where UName='"+user.getUserName()+"'and UPassword='"+user.getPassword()+"'";
			int num=template.queryForObject(sql,Integer.class);
			if(num==0)
				return result;
			else
				return true;
		}catch(Exception e){
			return result;
		}
	}

	@Override
	public String getPassagerId(String name) throws DBException {
		try{
			String sql="select UId from userinf where UName='"+name+"'";
			String num=template.queryForObject(sql,String.class);
			return num;
		}catch(Exception e){
			throw new DBException();
		}
	}

	@Override
	public String getTrueName(String no) throws DBException{
		try{
			String sql="select Name from trueuserinf where UCode="+no;
			String name=template.queryForObject(sql,String.class);
			return name;
		}catch(Exception e){
			throw new DBException();
		}
	}
	@Override
	public List<Passager> getUsual(String username) throws DBException {
		// TODO Auto-generated method stub
		try{
			String sql="select Name,Type,Utype,Ucode from trueuserinf where UCode in (select UNo from usual where UName = '"+username+"')";
			return (List<Passager>)template.query(sql, new BeanPropertyRowMapper(Passager.class));
		}
		catch(Exception e){
			throw new DBException();
		}
	}
}
