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
			return JsonUtil.getJSONObject(Configure.LOGINFALSECODE,"用户名不存在或密码错误"); 
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
				return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
			}
		}
	}
	@Override
	public JSONObject reigster(ReigsterInf user){
		try{
			if(reigsterDao.userNameExist(user.getUserName())){
				return JsonUtil.getJSONObject("用户名已存在",Configure.NAMEEXISTCODE);
			}
			else if(reigsterDao.passagerExist(user.getIdCode())){
				return JsonUtil.getJSONObject("该人已经被实名注册",Configure.USEREXISTCODE);
			}
			else{
				reigsterDao.reigster(user);
				return JsonUtil.getJSONObject("注册成功",Configure.REIGSTERTRUECODE);
			}
		}catch(Exception e){
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}
	@Override
	public JSONObject getPassager(String username) {
		try {
			
			return JsonUtil.getJSONObject(Configure.DBTURECODE,userDao.getUsual(username));
		} catch (DBException e) {
			return JsonUtil.getJSONObject("数据库发生错误，请及时反馈给我们",Configure.DBFALSECODE);
		}
	}
	@Override
	public JSONObject insertPassager(ReigsterInf user) {
		if(user.getIdCode().equals("")||user.getUserName().equals("")||user.getTrueName().equals(""))
			return JsonUtil.getJSONObject("乘客信息不全",Configure.DBFALSECODE);
		if(!reigsterDao.insertPassager(user))
			return JsonUtil.getJSONObject("添加失败",Configure.DBFALSECODE);
		return JsonUtil.getJSONObject("添加成功",Configure.CONTENTTRUECODE);
	}
}
