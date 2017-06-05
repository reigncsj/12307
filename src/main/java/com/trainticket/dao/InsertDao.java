package com.trainticket.dao;

import java.util.List;

import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Train;
import com.trainticket.exception.ContentException;

public interface InsertDao {
	public boolean insertData(List<Train> l);
	public void insertSelect(QueryInf q) throws ContentException;
}
