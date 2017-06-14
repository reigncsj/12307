package com.trainticket.dao;

import java.util.List;

import com.trainticket.exception.ContentException;
import com.trainticket.model.QueryInf;
import com.trainticket.model.Train;

public interface InsertDao {
	public boolean insertData(List<Train> l);
	public void insertSelect(QueryInf q) throws ContentException;
}
