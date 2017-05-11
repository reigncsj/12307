package com.trainticket.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
}
