package com.trainticket.dao;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.User;
import com.trainticket.exception.DBException;

public interface UserDao {
	public boolean login(LoginUser user);
	public int getPassagerNo(String name) throws DBException;
	public String getTrueName(int no) throws DBException;
}
