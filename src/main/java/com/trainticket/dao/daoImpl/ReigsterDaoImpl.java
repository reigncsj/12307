package com.trainticket.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.bean.ReigsterInf;
import com.trainticket.dao.ReigsterDao;
import com.trainticket.exception.DBException;

@Repository("reigsterDao")
public class ReigsterDaoImpl implements ReigsterDao{
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	//判断用户名是否已经存在
	@Override
	public boolean userNameExist(String userName) throws DBException {
		try{
			//获得拥有这个用户名的个数并判断是否为0
			String sql="select count(*) from user where UName='"+userName+"'";
			int num=template.queryForObject(sql,Integer.class);
			//是0说明不存在
			if(num==0)
				return false;
			//是1说明存在
			else
				return true;
		}catch(Exception e){
			throw new DBException();
		}
	}

	@Override
	public boolean passagerExist(String code) throws DBException {
		try{
			String sql="select count(*) from trueuserinf where UCode='"+code+"'";
			int num=template.queryForObject(sql,Integer.class);
			if(num==0)
				return false;
			else{
				String sql2="select count(*) from userinf where UId="+code;
				int num1=template.queryForObject(sql2,Integer.class);
				if(num1!=0)
					return true;
				else
					return false;
			}
		}catch(Exception e){
			throw new DBException();
		}
	}

	@Override
	public boolean reigster(ReigsterInf r) throws DBException {
		try{
			insertUser(r);
			insertTrueUser(r);
			insertUserInf(r);
			insertUsual(r);
			return true;
		}catch(Exception e){
			throw new DBException();
		}
	}
	
	private void insertUser(ReigsterInf r) throws Exception{
		String sql="insert into user values('"+r.getUserName()+"','"+r.getPassword()+"')";
		try{
			template.update(sql);
		}catch(Exception e){
			throw e;
		}
	}
	
	private void insertTrueUser(ReigsterInf r){
		String sql="insert into trueuserinf(Name,type,Utype,UCode) values("
				+"'"+r.getTrueName()+"','"+r.getType()+"','"+r.getiType()+"','"+r.getIdCode()+"')";
		try{
			template.update(sql);
		}catch(Exception e){
		}
	}
	
	private void insertUserInf(ReigsterInf r) throws Exception{
		String sql="insert into userinf values('"+r.getUserName()+"','"+r.getIdCode()+"','"+r.getPhone()+"','"+r.getEmail()+"')";
		try{
			template.update(sql);
		}catch(Exception e){
			throw e;
		}
	}
	
	private void insertUsual(ReigsterInf r) throws Exception{
		String sql="insert into usual(UName,UNo) values('"+r.getUserName()+"',"+r.getIdCode()+")";
		try{
			template.update(sql);
		}catch(Exception e){
		}
	}

	@Override
	public boolean insertPassager(ReigsterInf r) {
		try{
			String sql="select count(*) from trueuserinf where UCode='"+r.getIdCode()+"'";
			int num=template.queryForObject(sql,Integer.class);
			if(num==0)
				insertTrueUser(r);
			insertUsual(r);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	

}
