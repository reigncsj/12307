package com.trainticket.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface StationService {
	JSONObject getStationByName(String station);
	JSONObject getStationByPy(String py);
}
