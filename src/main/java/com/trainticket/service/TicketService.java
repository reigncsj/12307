package com.trainticket.service;

import java.util.List;

import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Ticket;
import com.trainticket.bean.TransferInf;
import com.trainticket.exception.ContentException;

import net.sf.json.JSONObject;

public interface TicketService {

	public JSONObject getTicket(QueryInf queryInf) ;
	public List<Ticket> getTicketInf(QueryInf queryInf) throws ContentException;
	public void getTicketPirce(Ticket t);
	public JSONObject getOneTicket(QueryInf queryInf) ;
}
