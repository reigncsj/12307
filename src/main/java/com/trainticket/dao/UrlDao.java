package com.trainticket.dao;

import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Ticket;

import net.sf.json.JSONObject;

public interface UrlDao {

	JSONObject getTicket(QueryInf q);

	JSONObject getTicketPrice(Ticket t);

}
