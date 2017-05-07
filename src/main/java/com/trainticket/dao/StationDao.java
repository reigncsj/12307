package com.trainticket.dao;


import java.util.List;

import com.trainticket.exception.DBException;

public interface StationDao {
	public List<String> getStationByName(String station) throws DBException;
	public String getCode(String tname);
	public List<String> getStationByPy(String station) throws DBException;
	public String getNameByCode(String tname);
	
}
