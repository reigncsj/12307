package com.trainticket.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Ticket;
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
		QueryInf q1=new QueryInf(t.getStart(),t.getTransfer(),t.getDate(),"AUDIT");
		try {
			q.setStartCity(stationDao.getCity(q.getStart()));
			q.setEndCity(stationDao.getCity(q.getEnd()));
			insertDao.insertSelect(q);
			List<Ticket> t1=ticketService.getTicketInf(q1);
			if(t1.size()==0)
				return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
			Ticket t3=t1.get(0);
			String date2=MyDate.getArriveDate(t3.getRiqi(), t3.getStime(), t3.getLishi());
			QueryInf q2=new QueryInf(t.getTransfer(),t.getEnd(),date2,"AUDIT");
			List<Ticket> t2=ticketService.getTicketInf(q2);
			QueryInf q3=new QueryInf(t.getTransfer(),t.getEnd(),MyDate.getDayAfter(1, date2),"AUDIT");
			List<Ticket> t5=ticketService.getTicketInf(q3);
			List<Ticket> t4=TransferInfShow.getMeetInf(t3, t2,t5, t);
			return JsonUtil.getJSONObject(Configure.CONTENTTRUECODE, t1,t4);
		} catch (ContentException e) {
			return JsonUtil.getJSONObject("请求内容有误", Configure.CONTENTFAULTCODE);
		} catch (Exception e) {
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
		} 
	}

	@Override
	public JSONObject querySecondInf(TransferInf t) {
		QueryInf q1=new QueryInf(t.getStart(),t.getEnd(),t.getDate(),"AUDIT");
		try {
			List<Ticket> t1=ticketService.getTicketInf(q1);
			if(t1.size()==0)
				return JsonUtil.getJSONObject("当天无可中转的车次", Configure.DBFALSECODE);
			QueryInf q3=new QueryInf(t.getStart(),t.getEnd(),MyDate.getDayAfter(1, t.getDate()),"AUDIT");
			List<Ticket> t5=ticketService.getTicketInf(q3);
			Ticket t3=new Ticket();
			t3.setEnd(t.getStart());
			t3.setEtime(t.getTransfer());
			List<Ticket> t4=TransferInfShow.getMeetInf(t3, t1,t5, t);
			return JsonUtil.getJSONObject(Configure.CONTENTTRUECODE,t4);
		} catch (ContentException e) {
			return JsonUtil.getJSONObject("请求内容有误", Configure.CONTENTFAULTCODE);
		} catch (Exception e) {
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们", Configure.DBFALSECODE);
		} 
	}

	
	
}
