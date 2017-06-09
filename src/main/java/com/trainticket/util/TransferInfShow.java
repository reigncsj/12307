package com.trainticket.util;

import java.util.ArrayList;
import java.util.List;

import com.trainticket.bean.Ticket;
import com.trainticket.bean.TimeResult;
import com.trainticket.bean.TransferInf;

public class TransferInfShow {
    public static List<Ticket> getMeetInf(Ticket t, List<Ticket> result, TransferInf ti){
        List<Ticket> list=new ArrayList<Ticket>();
        try {
            int start=getStart(t.getEtime(),result,ti.getBetweenMinute());
            int end=getEnd(result);
            int s=0;
            int e=0;
            if(start<=end){
                e=start-1;
                s=start;
            }
            else{
                s=start;
                e=end;
            }
            if(e>=s) {
                for (int i = s;i<e;i++) {
                    list.add(result.get(i));
                }
            }
            else{
                for(int i=s;i<result.size();i++){
                    list.add(result.get(i));
                }
                for(int j=0;j<=e;j++){
                    list.add(result.get(j));
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
            return result;
        }
    }



    private static int getStart(String arrive,List<Ticket> result,int timeBetween){
        for(int i=0;i<result.size();i++){
        	TimeResult t=MyDate.timeBetween(arrive,result.get(i).getStime());
            if(t.getTimeBetween()>timeBetween)
                return i;
        }
        return 0;
    }

    private static int getEnd(List<Ticket> result){
        for(int i=0;i<result.size();i++){
            if(MyDate.largeThanTime(result.get(i).getStime()))
                return i-1;
        }
        return result.size();
    }

}

