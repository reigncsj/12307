package com.trainticket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.trainticket.bean.timeResult;

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
	public static String getNow(){
		Date date=new Date();//取时间
	     Calendar calendar = new GregorianCalendar();
	     calendar.setTime(date);
	     date=calendar.getTime(); 
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

	     return  formatter.format(date);
	}
	public static String getDayAfter(int i){
		Date date=new Date();//取时间
	     Calendar calendar = new GregorianCalendar();
	     calendar.setTime(date);
	     calendar.add(calendar.DATE,i);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
	     date=calendar.getTime(); 
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	     return  formatter.format(date);
	}
	
	public static boolean largeThanTime(String min){
		if(getMinuteNum(min)<=600)
			return false;
		else
			return true;
		
	}
	public static int arriveDay(String start,String lishi){
		return (getMinuteNum(start)+getMinuteNum(lishi))/1440;
	}
	public static timeResult timeBetween(String first,String second){
		int f=getMinuteNum(first);
		int s=getMinuteNum(second);
		if(s>f){
			return new timeResult(0,s-f);
		}
		else{
			return new timeResult(1,1440+s-f);
		}
	}
	private static int getMinuteNum(String min){
		String[] inf=min.split(":");
		int hh=Integer.parseInt(inf[0]);
		int mm=Integer.parseInt(inf[1]);
		return hh*60+mm;
	}
}
