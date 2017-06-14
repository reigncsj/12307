package com.trainticket.service;

import com.trainticket.model.TransferInf;

import net.sf.json.JSONObject;

public interface TransferInfService {
	public JSONObject queryAllInf(TransferInf q);
	public JSONObject querySecondInf(TransferInf q);
}
