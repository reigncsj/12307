package com.trainticket.service;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.ReigsterInf;
import com.trainticket.bean.User;

import net.sf.json.JSONObject;

public interface UserService {

	JSONObject login(LoginUser user);
	JSONObject reigster(ReigsterInf user);
	JSONObject getPassager(String username);
	JSONObject insertPassager(ReigsterInf user);
}
