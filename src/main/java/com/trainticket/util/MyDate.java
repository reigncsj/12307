package com.trainticket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.trainticket.bean.Ticket;
import com.trainticket.bean.TimeResult;

public class MyDate {

	public static String getTomorrow(){
		Date date=new Date();//取时间
	     Calendar calendar = new GregorianCalendar();
	     calendar.setTime(date);
	     calendar.add(calendar.DATE,1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
	     date=calendar.getTime(); 
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	     return  formatter.format(date);
	}
	public static String getNowDate(){
		Date date=new Date();//取时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return  formatter.format(date);
	}

	public static String getNow(){
		Date date=new Date();//取时间
	     Calendar calendar = new GregorianCalendar();
	     calendar.setTime(date);
	     date=calendar.getTime(); 
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

	     return  formatter.format(date);
	}
	//获取距离某天后n天的日期
	public static String getDayAfter(int i,String date1){
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date fdate= myFormatter.parse(date1);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(fdate);
			calendar.add(calendar.DATE,i);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
			Date date2=calendar.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return  formatter.format(date2);
		}
		catch(Exception e){
			return date1;
		}
	}

	//是否超过9点钟,判断是否第二天到达
	public static boolean largeThanTime(String min){
		if(getMinuteNum(min)<=360)
			return false;
		else
			return true;
		
	}
	
	//获得到达日期
	public static String getArriveDate(String date,String start,String lishi){
		int between=arriveDay(start,lishi);
		return getDayAfter(between,date);
	}
	
	//根据开始时间和经过的时间得到到达日期（最小到天）
	public static int arriveDay(String start,String lishi){
		return (getMinuteNum(start)+getMinuteNum(lishi))/1440;
	}

	public static String getSecondTrainDay(Ticket t1, String date,Ticket t2){
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date fdate= myFormatter.parse(date);
			int i1=arriveDay(t1.getStime(),t1.getLishi());
			int i2=timeBetween(t1.getEtime(),t2.getStart()).getDay();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(fdate);
			calendar.add(calendar.DATE,i1+i2);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
			Date date2=calendar.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return  formatter.format(date2);
		}
		catch(Exception e){
			return date;
		}

	}

	//返回时间差（第一趟到达时间和第二趟开始出发时间）
	public static TimeResult timeBetween(String first,String second){
		int f=getMinuteNum(first);
		int s=getMinuteNum(second);
		if(s>f){
			return new TimeResult(s-f,0); //0代表相同一天
		}
		else{
			return new TimeResult(1440+s-f,1); //1代表不在同一天
		}
	}
	//（把从零点开始计算）字符串转换为分钟
	public static int getMinuteNum(String min){
		String[] inf=min.split(":");
		int hh=Integer.parseInt(inf[0]);
		int mm=Integer.parseInt(inf[1]);
		return hh*60+mm;
	}
}

