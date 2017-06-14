package com.trainticket.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.dao.StationDao;
import com.trainticket.dao.TransferStationDao;
import com.trainticket.exception.DBException;
import com.trainticket.model.QueryInf;
import com.trainticket.service.TransferService;
import com.trainticket.util.Configure;
import com.trainticket.util.JsonFactory;

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
			return JsonFactory.getJSONObject("请求内容有误", Configure.CONTENTFAULTCODE);
		}
		setCityInf(q);
		setCityStationInf(q);
		if(isCommonCity(q)){
			return JsonFactory.getJSONObject("车站所在城市相同，中转不给予考虑", Configure.CITYSAMECODE);
		}
		try {
			List<String> l=transferStationDao.queryTransferStation(q);
			return JsonFactory.getJSONObject(Configure.DBTURECODE,l);
		} catch (DBException e) {
			return JsonFactory.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
		}
	}

	@Override
	public JSONObject queryAllTransferStation(QueryInf q) {
		if(q.getStart().equals(q.getEnd())){
			return JsonFactory.getJSONObject("请求内容有误", Configure.CONTENTFAULTCODE);
		}
		setCityInf(q);
		setCityStationInf(q);
		if(isCommonCity(q)){
			return JsonFactory.getJSONObject("车站所在城市相同，中转不给予考虑", Configure.CITYSAMECODE);
		}
		try {
			List<String> l=transferStationDao.queryAllTransferStation(q);
			return JsonFactory.getJSONObject(Configure.DBTURECODE,l);
		} catch (DBException e) {
			return JsonFactory.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
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
			return JsonFactory.getJSONObject("请求内容有误", Configure.CONTENTFAULTCODE);
		}
		setCityInf(q);
		if(isCommonCity(q)){
			return JsonFactory.getJSONObject("车站所在城市相同，中转不给予考虑", Configure.CITYSAMECODE);
		}
		try {
			List<String> l=transferStationDao.queryUsualTransferStation(q);
			return JsonFactory.getJSONObject(Configure.DBTURECODE,l);
		} catch (DBException e) {
			return JsonFactory.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
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
