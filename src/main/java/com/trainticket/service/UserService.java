package com.trainticket.service;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.User;

import net.sf.json.JSONObject;

public interface UserService {

	JSONObject login(LoginUser user);

}
