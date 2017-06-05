package com.trainticket.dao;

import java.util.List;

import com.trainticket.bean.QueryInf;
import com.trainticket.exception.DBException;

public interface TransferStationDao {
	public List<String> queryTransferStation(QueryInf q) throws DBException;
	public List<String> queryAllTransferStation(QueryInf q) throws DBException;
	public List<String> queryUsualTransferStation(QueryInf q) throws DBException;
}
