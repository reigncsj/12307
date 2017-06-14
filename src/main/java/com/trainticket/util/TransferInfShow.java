package com.trainticket.util;

import java.util.ArrayList;
import java.util.List;

import com.trainticket.bean.Ticket;
import com.trainticket.bean.TimeResult;
import com.trainticket.bean.TransferInf;

public class TransferInfShow {
    public static List<Ticket> getMeetInf(Ticket t, List<Ticket> result,List<Ticket> result2, TransferInf ti){
        List<Ticket> list=new ArrayList<Ticket>();
        try {
            int start=getStart(t.getEtime(),result,ti.getTime(),0);
            int end=getEnd(result2);
            if(start==-1){
            	start=getStart(t.getEtime(),result2,ti.getTime(),1);
            	if(start!=-1){
            		for(;start<result2.size();start++){
            			if(t.getEnd().equals(result2.get(start).getStart())||ti.getTime()>=120)
            				list.add(result2.get(start));
            		}
            	}
            	return list;
            }
            else{
            	for(;start<result.size();start++){
            		if(t.getEnd().equals(result.get(start).getStart())||ti.getTime()>=120)
            			list.add(result.get(start));
        		}
            	for(int i=0;i<=end;i++){
            		if(t.getEnd().equals(result2.get(i).getStart())||ti.getTime()>=120)
            			list.add(result2.get(i));
            	}
            }  	
            return list;
        }catch(Exception e){
            return list;
        }
    }

    public static List<Ticket> getMeetTypeInf(List<Ticket> result, int i){
        List<Ticket> list=new ArrayList<Ticket>();
        try {
            String mark=null;
            if(i==1)
                return result;
            else if(i==2){
                for(int j=0;j<result.size();j++){
                    if(result.get(j).getCode().startsWith("G")||result.get(j).getCode().startsWith("D")||result.get(j).getCode().startsWith("C"))
                        list.add(result.get(j));
                }
            }
            else{
                for(int j=0;j<result.size();j++){
                    if(!result.get(j).getCode().startsWith("G")&&!result.get(j).getCode().startsWith("D")&&!result.get(j).getCode().startsWith("C"))
                        list.add(result.get(j));
                }
            }
            return list;
        }catch(Exception e){
            return list;
        }
    }



    private static int getStart(String arrive,List<Ticket> result,int timeBetween,int day){
        for(int i=0;i<result.size();i++){
        	TimeResult t=MyDate.timeBetween(arrive,result.get(i).getStime());
            if(t.getTimeBetween()>timeBetween&&t.getDay()==day)
                return i;
        }
        return -1;
    }

    private static int getEnd(List<Ticket> result){
        for(int i=0;i<result.size();i++){
            if(MyDate.largeThanTime(result.get(i).getStime()))
                return i-1;
        }
        return result.size();
    }

}

