package com.trainticket.dao;

import com.trainticket.bean.ReigsterInf;
import com.trainticket.exception.DBException;

public interface ReigsterDao {
	public boolean userNameExist(String userName) throws DBException;
	public boolean passagerExist(String code)throws DBException;
	public boolean reigster(ReigsterInf r)throws DBException;
	public boolean insertPassager(ReigsterInf r);
}
