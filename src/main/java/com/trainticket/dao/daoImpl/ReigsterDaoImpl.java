package com.trainticket.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.dao.ReigsterDao;
import com.trainticket.exception.DBException;
import com.trainticket.model.ReigsterInf;

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

	//判断此身份是否被注册
	@Override
	public boolean passagerExist(String code) throws DBException {
		try{
			String sql="select count(*) from trueuserinf where UCode='"+code+"'";
			//按照用户身份证查找对应的信息是否存在
			int num=template.queryForObject(sql,Integer.class);
			//如果为0就没有
			if(num==0)
				return false;
			else{
				//从账号与身份证对应信息表里查找是否有对应的账号
				String sql2="select count(*) from userinf where UId="+code;
				int num1=template.queryForObject(sql2,Integer.class);
				//判断是否存在
				if(num1!=0)
					return true;
				else
					return false;
			}
		}catch(Exception e){
			throw new DBException();
		}
	}

	//注册账户
	@Override
	public boolean reigster(ReigsterInf r) throws DBException {
		try{
			insertUser(r);//插入到账户信息表里
			insertTrueUser(r);//插入真实用户信息
			insertUserInf(r);//插入账号与身份证对应信息
			insertUsual(r);//插入常用联系人
			return true;
		}catch(Exception e){
			throw new DBException();
		}
	}
	//插入到账户信息表里
	private void insertUser(ReigsterInf r) throws Exception{
		String sql="insert into user values('"+r.getUserName()+"','"+r.getPassword()+"')";
		try{
			template.update(sql);
		}catch(Exception e){
			throw e;
		}
	}
	//插入真实用户信息
	private void insertTrueUser(ReigsterInf r){
		String sql="insert into trueuserinf(Name,type,Utype,UCode) values("
				+"'"+r.getTrueName()+"','"+r.getType()+"','"+r.getiType()+"','"+r.getIdCode()+"')";
		try{
			template.update(sql);
		}catch(Exception e){
		}
	}
	//插入账号与身份证对应信息
	private void insertUserInf(ReigsterInf r) throws Exception{
		String sql="insert into userinf values('"+r.getUserName()+"','"+r.getIdCode()+"','"+r.getPhone()+"','"+r.getEmail()+"')";
		try{
			template.update(sql);
		}catch(Exception e){
			throw e;
		}
	}
	//插入常用联系人
	private void insertUsual(ReigsterInf r) throws Exception{
		String sql="insert into usual(UName,UNo) values('"+r.getUserName()+"',"+r.getIdCode()+")";
		try{
			template.update(sql);
		}catch(Exception e){
		}
	}

	//插入常用联系人信息
	@Override
	public boolean insertPassager(ReigsterInf r) {
		try{
			String sql="select count(*) from trueuserinf where UCode='"+r.getIdCode()+"'";
			int num=template.queryForObject(sql,Integer.class);
			//判断用户信息是否已经存在
			if(num==0)
				insertTrueUser(r);
			//将映射表进行修改
			insertUsual(r);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
