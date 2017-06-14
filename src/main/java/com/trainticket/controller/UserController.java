package com.trainticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.model.LoginUser;
import com.trainticket.model.ReigsterInf;
import com.trainticket.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	//登陆路径
	@RequestMapping(value="/login",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object login1(String userName,String password){
		LoginUser user=new LoginUser(userName,password);
		return userService.login(user).toString();
	}

	//注册路径
	@RequestMapping(value="/reigster",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object reigster(String username,String password,String truename,String phone,String email,String type,String idtype,String idcode){
		return userService.reigster(new ReigsterInf(username,password,truename,phone,email,type,idtype,idcode)).toString();
	}
	
	//获取常用乘车路径
	@RequestMapping(value="/passager",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object login(String username){
		return userService.getPassager(username).toString();
	}

	//插入常用联系人路径
	@RequestMapping(value="/insertPassager",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object reigster(String username,String truename,String type,String idtype,String idcode){
		return userService.insertPassager(new ReigsterInf(username,truename,type,idtype,idcode)).toString();
	}
}
