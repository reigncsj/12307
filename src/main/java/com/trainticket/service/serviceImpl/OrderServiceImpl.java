package com.trainticket.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.dao.OrderDao;
import com.trainticket.dao.UserDao;
import com.trainticket.exception.ContentException;
import com.trainticket.exception.DBException;
import com.trainticket.model.Order;
import com.trainticket.model.OrderCommand;
import com.trainticket.model.QueryInf;
import com.trainticket.model.Ticket;
import com.trainticket.service.OrderService;
import com.trainticket.service.TicketService;
import com.trainticket.util.Configure;
import com.trainticket.util.JsonFactory;
import com.trainticket.util.MyDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	@Qualifier("ticketService")
	private TicketService ticketService;
	@Autowired
	@Qualifier("orderDao")
	private OrderDao orderDao;
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Override
	public JSONObject buildOrder(OrderCommand order) {
		List<Ticket> ticket=null;
		try {
			ticket = ticketService.getTicketInf(new QueryInf(order.getStart(),order.getEnd(),order.getDate()));
		} catch (ContentException e1) {
			return JsonFactory.getJSONObject("订单生成失败", Configure.CONTENTFAULTCODE);
		}
		Ticket t=getTicketInf(ticket,order);
		if(t==null){
			return JsonFactory.getJSONObject("订单生成失败", Configure.DBFALSECODE);
		}
		else{
			ticketService.getTicketPirce(t);
			String content= t.getInf(order.getLtype());
			if(content.equals("")||content.equals("无")){
				return JsonFactory.getJSONObject("该趟车已售完", Configure.NOTICKET);
			}
			if(content.equals("维护")){
				return JsonFactory.getJSONObject("系统维护中", Configure.DBFALSECODE);
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
				orderInf.setLtype(order.getLtype());
				orderInf.setLocation(location);
				orderInf.setCode(order.getCode());
				orderInf.setPaid("0");
				orderInf.setPrice(t.getInf(orderInf.getLtype()+"pj"));
				orderInf.setOrderNo(MyDate.getNow()+orderInf.getUcode().substring(13, 16)+(getRandomNum(10)-1)+(getRandomNum(10)-1));
				orderInf.setStartTime(t.getStime());
				orderInf.setEndTime(t.getEtime());
				orderInf.setLishi(t.getLishi());
				if(orderInf.getPrice().equals(""))
					return JsonFactory.getJSONObject("服务器忙，请稍后再试", Configure.DBFALSECODE);
				try {
					orderInf.setName(userDao.getTrueName(orderInf.getUcode()));
				} catch (DBException e1) {
				}
				try {
					orderDao.insertOrder(orderInf);
				} catch (DBException e) {
					return JsonFactory.getJSONObject("订单生成失败", Configure.DBFALSECODE);
				}
				return JsonFactory.getJSONObject(Configure.GETTICKET,orderInf);
			}
		}
	}
	@Override
	public JSONObject buildOrder(OrderCommand order, OrderCommand order2) {
		JSONObject jb=buildOrder(order);
		if(!jb.getString("retCode").equals("1"))
			return JsonFactory.getJSONObject("订单生成失败", Configure.DBFALSECODE);
		else{
			JSONObject content=jb.getJSONObject("result");
			JSONObject jb2=buildOrder(order2);
			if(!jb2.getString("retCode").equals("1")){
				orderDao.deleteOrder(content.getString("orderNo"), content.getString("username"));
				return JsonFactory.getJSONObject("订单生成失败", Configure.DBFALSECODE);
			}
			else{
				JSONObject content2=jb2.getJSONObject("result");
				JSONArray ja=new JSONArray();
				ja.add(content);
				ja.add(content2);
				return JsonFactory.getJSONObject(Configure.GETTICKET,ja);
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
			return JsonFactory.getJSONObject("",Configure.PAYOK);
		}catch(Exception e){
			return JsonFactory.getJSONObject("",Configure.PAYfALSE);
		}
	}

	@Override
	public JSONObject getOrderByName(String username) {
		try{
			return JsonFactory.getJSONObject(Configure.CONTENTTRUECODE,orderDao.getOrderByName(username));
		}catch(Exception e){
			return JsonFactory.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}

	@Override
	public JSONObject getOrderByNo(String no) {
		try{
			return JsonFactory.getJSONObject(Configure.CONTENTTRUECODE,orderDao.getOrderByNo(no));
		}catch(Exception e){
			return JsonFactory.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}

	@Override
	public JSONObject getUnPaidOrder(String name) {
		try{
			return JsonFactory.getJSONObject(Configure.CONTENTTRUECODE,orderDao.getUnPaidOrder(name));
		}catch(Exception e){
			return JsonFactory.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}

	@Override
	public JSONObject getPaidOrder(String name) {
		try{
			return JsonFactory.getJSONObject(Configure.CONTENTTRUECODE,orderDao.getPaidOrder(name));
		}catch(Exception e){
			return JsonFactory.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}

	
	
}
