package com.trainticket.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.Ticket;
import com.trainticket.dao.StationDao;
import com.trainticket.dao.UrlDao;
import com.trainticket.service.TicketService;
import com.trainticket.util.Configure;
import com.trainticket.util.MyDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
	@Autowired
	@Qualifier("urlDao")
	public UrlDao urlDao;
	
	@Autowired
	@Qualifier("stationDao")
	public StationDao stationDao;
	
	@Override
	public JSONObject getTicket(String start,String end,String date1,String purpose){

	     String dateString = MyDate.getTomorrow();
	     JSONObject jb =urlDao.getTicket(stationDao.getCode(start), stationDao.getCode(end), dateString, purpose);
	     if(jb!=null){
	    	 String status=jb.getString("status");
	    	 if(status.equals("false")){
	    		 jb=new JSONObject();
		    	 jb.put("retCode",Configure.ContentFaultCODE);
		    	 jb.put("result","非法请求");
		    	 return jb;
	    	 }
	    	 else{
	    		 return parseInf(jb,date1);
	    	 }
	     }
	     else{
	    	 jb=new JSONObject();
	    	 jb.put("retCode",Configure.DBFALSECODE );
	    	 jb.put("result","网络发生错误或服务器在维护");
	    	 return jb;
	     }
	
	
	}
	     
	private JSONObject parseInf(JSONObject jb,String date1){
		JSONObject jo=jb.getJSONObject("data");
		JSONObject map=jo.getJSONObject("map");
		
		 JSONArray result=jo.getJSONArray("result");
		 List<String> data=new ArrayList<String>();
		 for(int i=0;i<result.size();i++){
			 data.add(result.getString(i));
		 }
		 List<Ticket> l1=getInf(data,date1,map);
		 for(int i=0;i<l1.size();i++){
			 Ticket t1=l1.get(i);
			 JSONObject bb=urlDao.getTicketPrice(t1.getTcode(), t1.getRiqi(), t1.getSno()
					 ,t1.getEno(),t1.getSeat());
			 getPrice(t1,bb);
		 }
		 return getAllInf(l1);
	 }
	private String getName(JSONObject jo,String code){
		return jo.getString(code);
	}
	private void getPrice(Ticket t, JSONObject j1){
		if(j1!=null){
			JSONObject j=j1.getJSONObject("data");
			if(!j.isEmpty()){
				if(j.containsKey("A1"))
					t.setYzpj(j.getString("A1"));
				if(j.containsKey("A3"))
					t.setYwpj(j.getString("A3"));
				if(j.containsKey("A4"))
					t.setRwpj(j.getString("A4"));
				if(j.containsKey("A6"))
					t.setGwpj(j.getString("A6"));
				if(j.containsKey("A2"))
					t.setRzpj(j.getString("A2"));
				if(j.containsKey("WZ"))
					t.setWzpj(j.getString("WZ"));
				if(j.containsKey("A9"))
					t.setSwpj(j.getString("A9"));
				if(j.containsKey("M"))
					t.setYdpj(j.getString("M"));
				if(j.containsKey("O"))
					t.setEdpj(j.getString("O"));
				if(j.containsKey("P"))
					t.setTdpj(j.getString("P"));
				if(j.containsKey("MIN"))
					t.setQtpj(j.getString("MIN"));
			}
		}
	}
	private JSONObject getAllInf(List<Ticket> l){
		JSONObject jb=new JSONObject();
		JSONArray ja=new JSONArray();
		for(int i=0;i<l.size();i++){
			ja.add(l.get(i));
		}
		jb.put("result", ja);
		jb.put("retCode", Configure.ContentTrueCODE);
		return jb;
	}
	
	private List<Ticket> getInf(List<String> data,String date1,JSONObject jo){
		List<Ticket> l=new ArrayList<Ticket>();
		for(int i=0;i<data.size();i++){
			String[] d=data.get(i).split("\\|");
			int n=0;
			for(;n<d.length&&!d[n].equals("预订");n++);
			Ticket t=new Ticket();
			for(int k=0;(n+k)<d.length;k++){
				if(k==1){
					t.setTcode(d[n+k]);
				}
				if(k==2){
					t.setCode(d[n+k]);
				}
				if(k==5){
					t.setStart(getName(jo,d[n+k]));
				}
				if(k==6){
					t.setEnd(getName(jo,d[n+k]));
				}
				if(k==7){
					t.setStime(d[n+k]);
				}
				if(k==8){
					t.setEtime(d[n+k]);
				}
				if(k==9){
					t.setLishi(d[n+k]);
				}
				if(k==10){
					t.setYou(d[n+k]);
				}
				if(k==12){
					t.setRiqi(date1);
				}
				if(k==15){
					t.setSno(d[n+k]);
				}
				if(k==16){
					t.setEno(d[n+k]);
				}
				if(d.length-n-k==1){
					t.setSeat(d[n+k]);
				}
				if(d.length-n-k==3){
					t.setSw(d[n+k]);
				}
				if(d.length-n-k==4){
					t.setYd(d[n+k]);
				}
				if(d.length-n-k==5){
					t.setEd(d[n+k]);
				}
				if(d.length-n-k==6){
					t.setYz(d[n+k]);
				}
				if(d.length-n-k==7){
					t.setYw(d[n+k]);
				}
				if(d.length-n-k==9){
					t.setWz(d[n+k]);
				}
				if(d.length-n-k==10){
					t.setTd(d[n+k]);
				}
				if(d.length-n-k==11){
					t.setRz(d[n+k]);
				}
				if(d.length-n-k==12){
					t.setRw(d[n+k]);
				}
				if(d.length-n-k==13){
					t.setQt(d[n+k]);
				}
				if(d.length-n-k==14){
					t.setGw(d[n+k]);
				}
			}
			l.add(t);
		}
		return l;
	}
}
