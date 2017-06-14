package com.trainticket.service;

import com.trainticket.model.QueryInf;

import net.sf.json.JSONObject;

public interface TrainInfService {
	public JSONObject getLateInf(QueryInf q);
}
