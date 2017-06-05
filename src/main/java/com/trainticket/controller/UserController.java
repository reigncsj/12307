package com.trainticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.bean.LoginUser;
import com.trainticket.bean.ReigsterInf;
import com.trainticket.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object login1(String userName,String password){
		LoginUser user=new LoginUser(userName,password);
		return userService.login(user).toString();
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object login(@ModelAttribute("login")LoginUser user){
		return userService.login(user).toString();
	}

	@RequestMapping(value="/reigster",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object login(@ModelAttribute("reigster")ReigsterInf inf){
		return "";
	}
	
	@RequestMapping(value="/passager",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object login(String username){
		return userService.getPassager(username).toString();
	}

}
