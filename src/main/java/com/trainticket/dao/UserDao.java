package com.trainticket.dao;

import java.util.List;

import com.trainticket.exception.DBException;
import com.trainticket.model.LoginUser;
import com.trainticket.model.Passager;
import com.trainticket.model.User;

public interface UserDao {
	public boolean login(LoginUser user);
	public boolean deletePassager(String username,String id);
	public String getTrueName(String no) throws DBException;
	public String getPassagerId(String name) throws DBException;
	public List<Passager> getUsual(String username)  throws DBException;

}
