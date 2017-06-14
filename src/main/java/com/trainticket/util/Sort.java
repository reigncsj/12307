package com.trainticket.util;

import java.util.List;

import com.trainticket.model.Ticket;

public class Sort {
	int num=0;
	public static int[] sortByArrive(List<Ticket> ticket){
		try{
			int[] no=new int[ticket.size()];
			int[] arrive=new int[ticket.size()];
			initNo(no);
			setArrive(arrive,ticket);
			sort(no,arrive);
			return no;
		}catch(Exception e){
			return null;
		}
	}
	public static int[] sortByLishi(List<Ticket> ticket){
		try{
			int[] no=new int[ticket.size()];
			int[] lishi=new int[ticket.size()];
			initNo(no);
			setLishi(lishi,ticket);
			sort(no,lishi);
			return no;
		}catch(Exception e){
			return null;
		}
	}
	private static void initNo(int[] no){
		for(int i=0;i<no.length;i++){
			no[1]=i;
		}
	}
	private static void setLishi(int[] lishi,List<Ticket> ticket){
		for(int i=0;i<ticket.size();i++){
			lishi[i]=MyDate.getMinuteNum(ticket.get(i).getLishi());
		}
	}
	private static void setArrive(int[] Arrive,List<Ticket> ticket){
		for(int i=0;i<ticket.size();i++){
			Arrive[i]=MyDate.getMinuteNum(ticket.get(i).getStart())+MyDate.getMinuteNum(ticket.get(i).getLishi());
		}
	}
	private static void sort(int[] i1,int[] i2){
		if(i1.length==i2.length&&i1.length>0){
			for(int i=0;i<i1.length-1;i++){
				for(int n=i;i<i1.length-1;n++){
					if(i2[i1[n]]>i2[i1[n+1]]){
						int j=i1[n+1];
						i1[n+1]=i1[n];
						i1[n]=j;
					}
				}
			}
		}
	}
}
