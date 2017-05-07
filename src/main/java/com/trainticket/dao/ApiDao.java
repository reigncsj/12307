package com.trainticket.dao;

import net.sf.json.JSONObject;

public interface ApiDao {
	public JSONObject queryByStationToStation(String start,String end);
	public JSONObject queryByTrainId(String id);
}
