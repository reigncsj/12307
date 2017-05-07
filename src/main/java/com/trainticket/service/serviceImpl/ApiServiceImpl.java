package com.trainticket.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.dao.ApiDao;
import com.trainticket.service.ApiService;

import net.sf.json.JSONObject;

@Service("apiService")
public class ApiServiceImpl implements ApiService {

	@Autowired
	@Qualifier("apiDao")
	private ApiDao apiDao;
	@Override
	public JSONObject queryByStationToStation(String start, String end) {
		// TODO Auto-generated method stub
		return apiDao.queryByStationToStation(start, end);
	}
	@Override
	public JSONObject queryByTrainId(String id) {
		// TODO Auto-generated method stub
		return apiDao.queryByTrainId(id);
	}
	
}
