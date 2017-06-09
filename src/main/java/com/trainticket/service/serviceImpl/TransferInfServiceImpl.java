package com.trainticket.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Ticket;
import com.trainticket.bean.TransferDetailInf;
import com.trainticket.bean.TransferInf;
import com.trainticket.dao.InsertDao;
import com.trainticket.dao.StationDao;
import com.trainticket.exception.ContentException;
import com.trainticket.service.TicketService;
import com.trainticket.service.TransferInfService;
import com.trainticket.util.Configure;
import com.trainticket.util.JsonUtil;
import com.trainticket.util.MyDate;
import com.trainticket.util.TransferInfShow;

import net.sf.json.JSONObject;
@Service("transferInfService")
public class TransferInfServiceImpl implements TransferInfService {
	@Autowired
	@Qualifier("insertDao")
	public InsertDao insertDao;
	@Autowired
	@Qualifier("stationDao")
	public StationDao stationDao;
	@Autowired
	@Qualifier("ticketService")
	private TicketService ticketService;
	
	@Override
	public JSONObject queryAllInf(TransferInf t) {
		QueryInf q=new QueryInf(t.getStart(),t.getEnd());
		q.setTransfer(t.getTransfer());
		QueryInf q1=new QueryInf(t.getStart(),t.getTransfer(),MyDate.getTomorrow(),"AUDIT");
		QueryInf q2=new QueryInf(t.getTransfer(),t.getEnd(),MyDate.getTomorrow(),"AUDIT");
		try {
			q.setStartCity(stationDao.getCity(q.getStart()));
			q.setEndCity(stationDao.getCity(q.getEnd()));
			insertDao.insertSelect(q);
			List<Ticket> t1=ticketService.getTicketInf(q1);
			List<Ticket> t2=ticketService.getTicketInf(q2);

			return JsonUtil.getJSONObject(Configure.CONTENTTRUECODE, t1,t2);
		} catch (ContentException e) {
			return JsonUtil.getJSONObject("请求内容有误", Configure.CONTENTFAULTCODE);
		} catch (Exception e) {
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
		} 

		
	}

	@Override
	public JSONObject queryTestInf(TransferInf t) {
		QueryInf q=new QueryInf(t.getStart(),t.getEnd());
		q.setTransfer(t.getTransfer());
		QueryInf q1=new QueryInf(t.getStart(),t.getTransfer(),MyDate.getTomorrow(),"AUDIT");
		QueryInf q2=new QueryInf(t.getTransfer(),t.getEnd(),MyDate.getTomorrow(),"AUDIT");
		try {
			q.setStartCity(stationDao.getCity(q.getStart()));
			q.setEndCity(stationDao.getCity(q.getEnd()));
			insertDao.insertSelect(q);
			List<Ticket> t1=ticketService.getTicketInf(q1);
			List<Ticket> t2=ticketService.getTicketInf(q2);
			List<Ticket> t3=TransferInfShow.getMeetInf(t1.get(0), t2, t);
			return JsonUtil.getJSONObject(Configure.CONTENTTRUECODE, t1.get(0),t3);
		} catch (ContentException e) {
			return JsonUtil.getJSONObject("请求内容有误", Configure.CONTENTFAULTCODE);
		} catch (Exception e) {
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
		} 

	}
	
}
