package com.trainticket.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.Train;
import com.trainticket.dao.InsertDao;
import com.trainticket.dao.StationDao;
import com.trainticket.service.ParseDataService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("parseDataService")
public class ParseDataServiceImpl implements ParseDataService {
	@Autowired
	@Qualifier("insertDao")
	private InsertDao insertDao;

	@Override
	public List<Train> parseTrain(JSONObject jb,String id) {
		String code=jb.getString("retCode");
		List<Train> l=new ArrayList<Train>();
		if(!code.equals("200"))
			return l;
		else{
			JSONArray ja=jb.getJSONArray("result");
			for(int i=0;i<ja.size();i++){
				JSONObject j=(JSONObject) ja.getJSONObject(i);
				Train t=new Train();
				if(i==0){
					t.setOtherCode(id);
					t.setEndStationName(j.getString("endStationName"));
					t.setStartStationName(j.getString("startStationName"));
					t.setArriveTime(j.getString("arriveTime"));
					t.setStartTime(j.getString("startTime"));
					t.setStationName(j.getString("stationName"));
					t.setStationNo(j.getString("stationNo"));
					t.setStopoverTime(j.getString("stopoverTime"));
					t.setTrainClassName(j.getString("trainClassName"));
					t.setStationTrainCode(j.getString("stationTrainCode"));
				}
				else{
					t.setArriveTime(j.getString("arriveTime"));
					t.setStartTime(j.getString("startTime"));
					t.setStationName(j.getString("stationName"));
					t.setStationNo(j.getString("stationNo"));
					t.setStopoverTime(j.getString("stopoverTime"));
				}
				l.add(t);
			}
		}
		return l;
	}

	@Override
	public boolean insert(List<Train> l) {
		return insertDao.insertData(l);
	}

}
