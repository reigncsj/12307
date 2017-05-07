package com.trainticket.dao;

import net.sf.json.JSONObject;

public interface UrlDao {

	JSONObject getTicket(String scode, String ecode, String date, String purpose);

	JSONObject getTicketPrice(String code, String date, String s, String e, String seat);

}
