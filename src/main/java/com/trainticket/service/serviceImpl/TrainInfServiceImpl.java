package com.trainticket.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.QueryInf;
import com.trainticket.dao.UrlDao;
import com.trainticket.service.TrainInfService;
import com.trainticket.util.Configure;
import com.trainticket.util.JsonUtil;

import net.sf.json.JSONObject;

@Service("trainInfService")
public class TrainInfServiceImpl implements TrainInfService {

	@Autowired
	@Qualifier("urlDao")
	private UrlDao urlDao;
	@Override
	public JSONObject getLateInf(QueryInf q) {
		// TODO Auto-generated method stub
		return JsonUtil.getJSONObject(urlDao.getLateInf(q), Configure.CONTENTTRUECODE);
	}

}
