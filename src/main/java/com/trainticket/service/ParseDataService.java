package com.trainticket.service;

import java.util.List;

import com.trainticket.bean.Train;

import net.sf.json.JSONObject;

public interface ParseDataService {
	public List<Train> parseTrain(JSONObject jb,String id);
	public boolean insert(List<Train> l);
}
