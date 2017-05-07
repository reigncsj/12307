package com.trainticket.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.dao.StationDao;
import com.trainticket.service.StationService;
import com.trainticket.util.Configure;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("stationService")
public class StationServiceImpl implements StationService {

	@Autowired
	@Qualifier("stationDao")
	private StationDao stationDao;
	 
	@Override
	public JSONObject getStationByName(String station) {
		JSONArray array = new JSONArray();
		try{
			List<String> al=stationDao.getStationByName(station);
			for(int i=1;i<=al.size();i++){
				array.add(al.get(i-1));
			}
			return getJSONObject(array,Configure.DBTURECODE);
		}catch(Exception e){
			return getJSONObject(array,Configure.DBFALSECODE);
		}
	}

	@Override
	public JSONObject getStationByPy(String py) {
		JSONArray array = new JSONArray();
		try{
			List<String> al=stationDao.getStationByPy(py);
			for(int i=1;i<=al.size();i++){
				array.add(al.get(i-1));
			}
			return getJSONObject(array,Configure.DBTURECODE);
		}catch(Exception e){
			return getJSONObject(array,Configure.DBFALSECODE);
		}
	}
	
	private JSONObject getJSONObject(JSONArray array,int code){
		JSONObject ob = new JSONObject();
		ob.put("code", code);
		ob.put("result", array);
		return ob;
	}

}
