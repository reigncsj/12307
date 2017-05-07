package com.trainticket.dao;

import java.util.List;

import com.trainticket.bean.Train;

public interface InsertDao {
	public boolean insertData(List<Train> l);
}
