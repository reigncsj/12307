package com.trainticket.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.Order;
import com.trainticket.bean.OrderCommand;
import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Ticket;
import com.trainticket.dao.OrderDao;
import com.trainticket.exception.ContentException;
import com.trainticket.exception.DBException;
import com.trainticket.service.OrderService;
import com.trainticket.service.TicketService;
import com.trainticket.util.Configure;
import com.trainticket.util.JsonUtil;
import com.trainticket.util.MyDate;

import net.sf.json.JSONObject;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	@Qualifier("ticketService")
	private TicketService ticketService;
	@Autowired
	@Qualifier("orderDao")
	private OrderDao orderDao;
	
	@Override
	public JSONObject buildOrder(OrderCommand order) {
		List<Ticket> ticket=null;
		try {
			ticket = ticketService.getTicketInf(new QueryInf(order.getStart(),order.getEnd(),order.getDate()));
		} catch (ContentException e1) {
			return JsonUtil.getJSONObject("日期不在预售期呢你", Configure.CONTENTFAULTCODE);
		}
		Ticket t=getTicketInf(ticket,order);
		if(t==null){
			return JsonUtil.getJSONObject("订单生成失败", Configure.DBFALSECODE);
		}
		else{
			ticketService.getTicketPirce(t);
			String content= t.getInf(order.getLtype());
			if(content.equals("")||content.equals("无")){
				return JsonUtil.getJSONObject("该趟车已售完", Configure.NOTICKET);
			}
			if(content.equals("维护")){
				return JsonUtil.getJSONObject("系统维护中", Configure.DBFALSECODE);
			}
			else{
				String location=buildLocation(order);
				Order orderInf=new Order();
				orderInf.setStart(order.getStart());
				orderInf.setEnd(order.getEnd());
				orderInf.setDate(order.getDate());
				orderInf.settCode(order.gettCode());
				orderInf.setUcode(order.getUcode());
				orderInf.setUsername(order.getUsername());
				orderInf.setType(order.getLtype());
				orderInf.setLocation(location);
				orderInf.setCode(order.getCode());
				orderInf.setPaid("0");
				orderInf.setPrice(t.getInf(orderInf.getLtype()+"pj"));
				orderInf.setOrderNo(MyDate.getNow()+orderInf.getUcode().substring(13, 16)+(getRandomNum(10)-1)+(getRandomNum(10)-1));
				orderInf.setStartTime(t.getStime());
				orderInf.setEndTime(t.getEtime());
				orderInf.setLishi(t.getLishi());
				try {
					orderDao.insertOrder(orderInf);
				} catch (DBException e) {
					return JsonUtil.getJSONObject("订单生成失败", Configure.DBFALSECODE);
				}
				return JsonUtil.getJSONObject(Configure.GETTICKET,orderInf);
			}
		}
	}
	
	private Ticket getTicketInf(List<Ticket> ticket,OrderCommand order){
		for(int i=0;i<ticket.size();i++){
			if(ticket.get(i).getTcode().equals(order.gettCode()))
				return ticket.get(i);
		}
		return null;
	}
	private int getRandomNum(int i){
		return 1+(int)(Math.random()*i);
	}
	private char getRandomChar(int i){
		char a='A';
		int j=getRandomNum(i);
		return (char)((int)a+j-1);
	}
	private char getRandomWPChar(int i){
		int j=getRandomNum(3-i);
		if(j==1)
			return '上';
		else if(j==2&&i==0)
			return '中';
		else
			return '下';
	}
	private String buildLocation(OrderCommand order){
		String content=order.getLtype();
		if(content.equals("sw"))
			return "1车"+getRandomNum(2)+getRandomChar(4)+"座";
		else if(content.equals("yd"))
			return "2车"+getRandomNum(15)+getRandomChar(5)+"座";
		else if(content.equals("ed"))
			return "5车"+getRandomNum(15)+getRandomChar(5)+"座";
		else if(content.equals("td"))
			return "7车"+getRandomNum(10)+getRandomChar(4)+"座";
		else if(content.equals("gw"))
			return "8车"+getRandomNum(22)+"铺";
		else if(content.equals("rw"))
			return "9车"+getRandomNum(22)+getRandomWPChar(1)+"铺";
		else if(content.equals("yw"))
			return "10车"+getRandomNum(22)+getRandomWPChar(0)+"铺";
		else if(content.equals("rz"))
			return "6车"+getRandomNum(75)+"座";
		else if(content.equals("yz"))
			return "3车"+getRandomNum(75)+"座"; 
		else if(content.equals("wz"))
			return "4车无座"; 
		else 
			return "8车"+getRandomNum(11)+"号软包"; 
	}

	@Override
	public JSONObject payOrder(String no) {

		try{
			orderDao.setOrderPaid(no);
			return JsonUtil.getJSONObject("",Configure.PAYOK);
		}catch(Exception e){
			return JsonUtil.getJSONObject("",Configure.PAYfALSE);
		}
	}

	@Override
	public JSONObject getOrderByName(String username) {
		try{
			return JsonUtil.getJSONObject(Configure.CONTENTTRUECODE,orderDao.getOrderByName(username));
		}catch(Exception e){
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}

	@Override
	public JSONObject getOrderByNo(String no) {
		try{
			return JsonUtil.getJSONObject(Configure.CONTENTTRUECODE,orderDao.getOrderByNo(no));
		}catch(Exception e){
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}
	
}
