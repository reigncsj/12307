package com.trainticket.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.ReigsterInf;
import com.trainticket.bean.User;
import com.trainticket.dao.ReigsterDao;
import com.trainticket.dao.UserDao;
import com.trainticket.exception.DBException;
import com.trainticket.service.UserService;
import com.trainticket.util.Configure;
import com.trainticket.util.JsonUtil;

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
			return JsonUtil.getJSONObject(Configure.LOGINFALSECODE,""); 
		}
		else{
			User user1=new User();
			user1.setUserName(user.getUserName());
			try{
				String num=userDao.getPassagerId(user.getUserName());
				String trueName=userDao.getTrueName(num);
				user1.setTrueName(trueName);
				return JsonUtil.getJSONObject(Configure.LOGINTRUECODE,user1);
			}catch(Exception e){
				return JsonUtil.getJSONObject("",Configure.DBFALSECODE);
			}
		}
	}
	@Override
	public JSONObject reigster(ReigsterInf user){
		try{
			if(reigsterDao.userNameExist(user.getUserName())){
				return JsonUtil.getJSONObject("",Configure.NAMEEXISTCODE);
			}
			else if(reigsterDao.passagerExist(user.getIdCode())){
				return JsonUtil.getJSONObject("",Configure.USEREXISTCODE);
			}
			else{
				reigsterDao.reigster(user);
				return JsonUtil.getJSONObject("",Configure.REIGSTERTRUECODE);
			}
		}catch(Exception e){
			return JsonUtil.getJSONObject("",Configure.DBFALSECODE);
		}
	}
	@Override
	public JSONObject getPassager(String username) {
		try {
			
			return JsonUtil.getJSONObject(Configure.DBTURECODE,userDao.getUsual(username));
		} catch (DBException e) {
			return JsonUtil.getJSONObject("",Configure.DBFALSECODE);
		}
	}
}
