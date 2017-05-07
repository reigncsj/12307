package com.trainticket.service;

import net.sf.json.JSONObject;

public interface ApiService {
	public JSONObject queryByStationToStation(String start,String end);
	public JSONObject queryByTrainId(String id);
}
