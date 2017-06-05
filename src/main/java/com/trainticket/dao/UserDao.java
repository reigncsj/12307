package com.trainticket.dao;

import java.util.List;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.Passager;
import com.trainticket.bean.User;
import com.trainticket.exception.DBException;

public interface UserDao {
	public boolean login(LoginUser user);
	public String getTrueName(String no) throws DBException;
	public String getPassagerId(String name) throws DBException;
	public List<Passager> getUsual(String username)  throws DBException;

}
