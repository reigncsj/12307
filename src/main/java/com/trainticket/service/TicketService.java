package com.trainticket.service;

import net.sf.json.JSONObject;

public interface TicketService {

	JSONObject getTicket(String start, String end, String date, String purpose);

}
