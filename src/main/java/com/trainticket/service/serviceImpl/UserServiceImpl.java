package com.trainticket.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.ReigsterInf;
import com.trainticket.bean.User;
import com.trainticket.dao.ReigsterDao;
import com.trainticket.dao.UserDao;
import com.trainticket.service.UserService;
import com.trainticket.util.Configure;

import net.sf.json.JSONObject;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	@Qualifier("reigsterDao")
	private ReigsterDao reigsterDao;
	
	
	@Override
	public JSONObject login(LoginUser user){
		boolean result=userDao.login(user);
		if(!result){
			return getJSONObject(Configure.LOGINFALSECODE,new User()); 
		}
		else{
			User user1=new User();
			user1.setUserName(user.getUserName());
			try{
				int num=userDao.getPassagerNo(user.getUserName());
				String trueName=userDao.getTrueName(num);
				user1.setTrueName(trueName);
				return getJSONObject(Configure.LOGINTRUECODE,user1);
			}catch(Exception e){
				return getJSONObject(Configure.DBFALSECODE,new User());
			}
		}
	}
	@Override
	public JSONObject reigster(ReigsterInf user){
		try{
			if(reigsterDao.userNameExist(user.getUserName())){
				return getJSONObject(Configure.NAMEEXISTCODE,new User());
			}
			else if(reigsterDao.passagerExist(user.getIdCode())){
				return getJSONObject(Configure.USEREXISTCODE,new User());
			}
			else{
				reigsterDao.reigster(user);
				return getJSONObject(Configure.REIGSTERTRUECODE,new User());
			}
		}catch(Exception e){
			return getJSONObject(Configure.DBFALSECODE,new User());
		}
	}
	
	private JSONObject getJSONObject(String code ,User user){
		JSONObject js=new JSONObject();
		js.put("retCode", code);
		js.put("result", user);
		return js;
	}
}
