package com.trainticket.service;

import com.trainticket.model.LoginUser;
import com.trainticket.model.ReigsterInf;
import com.trainticket.model.User;

import net.sf.json.JSONObject;

public interface UserService {

	JSONObject login(LoginUser user);
	JSONObject reigster(ReigsterInf user);
	JSONObject getPassager(String username);
	JSONObject insertPassager(ReigsterInf user);
	JSONObject deletePassager(ReigsterInf user);
}
