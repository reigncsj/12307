package com.trainticket.dao;

import com.trainticket.model.QueryInf;
import com.trainticket.model.Ticket;

import net.sf.json.JSONObject;

public interface UrlDao {

	JSONObject getTicket(QueryInf q);

	JSONObject getTicketPrice(Ticket t);
	String getLateInf(QueryInf q);

}
