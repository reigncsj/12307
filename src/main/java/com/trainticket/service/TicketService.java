package com.trainticket.service;

import java.util.List;

import com.trainticket.exception.ContentException;
import com.trainticket.model.QueryInf;
import com.trainticket.model.Ticket;
import com.trainticket.model.TransferInf;

import net.sf.json.JSONObject;

public interface TicketService {

	public JSONObject getTicket(QueryInf queryInf) ;
	public List<Ticket> getTicketInf(QueryInf queryInf) throws ContentException;
	public void getTicketPirce(Ticket t);
	public JSONObject getOneTicket(QueryInf queryInf) ;
}
