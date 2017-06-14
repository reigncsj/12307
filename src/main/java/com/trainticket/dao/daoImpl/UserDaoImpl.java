package com.trainticket.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.dao.UserDao;
import com.trainticket.exception.DBException;
import com.trainticket.model.LoginUser;
import com.trainticket.model.Order;
import com.trainticket.model.Passager;
import com.trainticket.model.User;

//与用户信息相关的数据库操作
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	//登陆操作 
	@Override
	public boolean login(LoginUser user) {
		boolean result=false;
		try{
			String sql="select count(*) from user where UName='"+user.getUserName()+"'and UPassword='"+user.getPassword()+"'";
			int num=template.queryForObject(sql,Integer.class);
			//判断账户信息是否正确
			if(num==0)
				return result;
			else
				return true;
		}catch(Exception e){
			return result;
		}
	}
	//获得用户的身份证号
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
	//获取乘车人真实姓名
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
	//获取账户下对应的所有常用乘车人
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
	@Override
	public boolean deletePassager(String username, String id) {
		try{
			String sql1="select UID from userinf where UName='"+username+"'";
			String num1=template.queryForObject(sql1,String.class);
			if(num1.equals(id))
				return false;
			else{
				String sql="delete from usual where UName="+username+" and UNo="+id;
				int num=template.update(sql);
				if(num==1)
					return false;
				else 
					return true;
			}
		}
		catch(Exception e){
			return false;
		}
	}
}
