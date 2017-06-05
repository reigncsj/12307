package com.trainticket.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.QueryInf;
import com.trainticket.dao.StationDao;
import com.trainticket.dao.TransferStationDao;
import com.trainticket.exception.DBException;
import com.trainticket.service.TransferService;
import com.trainticket.util.Configure;
import com.trainticket.util.JsonUtil;

import net.sf.json.JSONObject;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

	@Autowired
	@Qualifier("stationDao")
	public StationDao stationDao;
	@Autowired
	@Qualifier("transferStationDao")
	public TransferStationDao transferStationDao;
	@Override
	public JSONObject querySomeTransferStation(QueryInf q) {
		if(q.getStart().equals(q.getEnd())){
			return JsonUtil.getJSONObject("", Configure.CONTENTFAULTCODE);
		}
		setCityInf(q);
		setCityStationInf(q);
		if(isCommonCity(q)){
			return JsonUtil.getJSONObject("", Configure.CITYSAMECODE);
		}
		try {
			List<String> l=transferStationDao.queryTransferStation(q);
			return JsonUtil.getJSONObject(Configure.DBTURECODE,l);
		} catch (DBException e) {
			return JsonUtil.getJSONObject("", Configure.DBFALSECODE);
		}
	}

	@Override
	public JSONObject queryAllTransferStation(QueryInf q) {
		if(q.getStart().equals(q.getEnd())){
			return JsonUtil.getJSONObject("", Configure.CONTENTFAULTCODE);
		}
		setCityInf(q);
		setCityStationInf(q);
		if(isCommonCity(q)){
			return JsonUtil.getJSONObject("", Configure.CITYSAMECODE);
		}
		try {
			List<String> l=transferStationDao.queryAllTransferStation(q);
			return JsonUtil.getJSONObject(Configure.DBTURECODE,l);
		} catch (DBException e) {
			return JsonUtil.getJSONObject("", Configure.DBFALSECODE);
		}
	}
	
	private boolean isCommonCity(QueryInf q){
		List<String> l=q.getStartStation();
		if(l==null)
			return false;
		else{
			for(int i=0;i<l.size();i++){
				if(l.get(i).equals(q.getEnd()))
					return true;
			}
			return false;
		}
	}

	@Override
	public JSONObject queryUsualTransferStation(QueryInf q) {
		if(q.getStart().equals(q.getEnd())){
			return JsonUtil.getJSONObject("", Configure.CONTENTFAULTCODE);
		}
		setCityInf(q);
		if(isCommonCity(q)){
			return JsonUtil.getJSONObject("", Configure.CITYSAMECODE);
		}
		try {
			List<String> l=transferStationDao.queryUsualTransferStation(q);
			return JsonUtil.getJSONObject(Configure.DBTURECODE,l);
		} catch (DBException e) {
			return JsonUtil.getJSONObject("", Configure.DBFALSECODE);
		}
	}
	
	private void setCityInf(QueryInf q){
		q.setStartCity(stationDao.getCity(q.getStart()));
		q.setEndCity(stationDao.getCity(q.getEnd()));
	}
	private void setCityStationInf(QueryInf q){
		q.setStartStation(stationDao.getCommonCityStation(q.getStart()));
		q.setEndStation(stationDao.getCommonCityStation(q.getEnd()));

	}
}
