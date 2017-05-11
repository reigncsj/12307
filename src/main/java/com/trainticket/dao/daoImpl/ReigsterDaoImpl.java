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

	@Override
	public boolean userNameExist(String userName) throws DBException {
		try{
			String sql="select count(*) from user where UName='"+userName+"'";
			int num=template.queryForObject(sql,Integer.class);
			if(num==0)
				return false;
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
				int uno=getUno(code);
				String sql2="select count(*) from userinf where UNo="+uno;
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
			int no=getUno(r.getIdCode());
			insertUserInf(r,no);
			insertUsual(r.getUserName(),no);
			return true;
		}catch(Exception e){
			throw new DBException();
		}
	}
	
	private void insertUser(ReigsterInf r) throws Exception{
		String sql="insert into user values('"+r.getUserName()+"','"+r.getPassword()+"'";
		try{
			template.update(sql);
		}catch(Exception e){
			throw e;
		}
	}
	
	private void insertTrueUser(ReigsterInf r){
		String sql="insert into trueuserinf(Name,type,Utype,UCode) values("
				+"'"+r.getTrueName()+"','"+r.getType()+"','"+r.getiType()+"','"+r.getIdCode()+"'";
		try{
			template.update(sql);
		}catch(Exception e){
			
		}
	}
	private int getUno(String code) throws Exception{

		String sql="select UNo from trueuserinf where UCode='"+code+"'";
		try{
			int i=template.queryForObject(sql,Integer.class);
			return i;
		}catch(Exception e){
			throw e;
		}
	}
	
	private void insertUserInf(ReigsterInf r,int no) throws Exception{
		String sql="insert into userinf values('"+r.getUserName()+"',"+no+",'"+r.getPhone()+"','"+r.getEmail()+"')";
		try{
			template.update(sql);
		}catch(Exception e){
			throw e;
		}
	}
	
	private void insertUsual(String username,int no) throws Exception{
		String sql="insert into usual(UName,UNo) values('"+username+"',"+no+")";
		try{
			template.update(sql);
		}catch(Exception e){
			throw e;
		}
	}
	

}
