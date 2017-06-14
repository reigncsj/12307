package com.trainticket.service;

import com.trainticket.model.QueryInf;

import net.sf.json.JSONObject;

public interface TransferService {
	public JSONObject querySomeTransferStation(QueryInf q);
	public JSONObject queryAllTransferStation(QueryInf q);
	public JSONObject queryUsualTransferStation(QueryInf q);
}
