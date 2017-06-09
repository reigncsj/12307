package com.trainticket.service;

import com.trainticket.bean.TransferInf;

import net.sf.json.JSONObject;

public interface TransferInfService {
	public JSONObject queryAllInf(TransferInf q);
	public JSONObject queryTestInf(TransferInf q);
}
