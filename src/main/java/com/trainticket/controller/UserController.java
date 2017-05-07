package com.trainticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.bean.LoginUser;
import com.trainticket.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public Object login1(String name,String password){
		LoginUser user=new LoginUser(name,password);
		return userService.login(user).toString();
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(@ModelAttribute("login")LoginUser user){
		return userService.login(user).toString();
	}

}
