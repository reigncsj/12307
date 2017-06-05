package com.trainticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.bean.OrderCommand;
import com.trainticket.service.OrderService;
import com.trainticket.util.MyDate;

import net.sf.json.JSONObject;

@Controller
public class OrderController {
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	@RequestMapping(value="/order",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object buildOrder(@ModelAttribute("order")OrderCommand order){
		return orderService.buildOrder(order).toString();
	}
	
	@RequestMapping(value="/order",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object buildOrderTest1(String tcode,String start,String end,String date,String username,String uid,String type,String code){
		OrderCommand o=new OrderCommand(tcode,start,end,MyDate.getTomorrow(),username,uid,type,code); 
		return orderService.buildOrder(o).toString();
	}
	@RequestMapping(value="/orderTest",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object buildOrderTest(){
		OrderCommand o=new OrderCommand("5u00000G380B","南昌","北京",MyDate.getTomorrow(),"123456abc","360201199601051528","ed","G38"); 
		return orderService.buildOrder(o).toString();
	}
	@RequestMapping(value="/getOrderByName",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object getOrderByName(String name){
		return orderService.getOrderByName(name).toString();
	}
	
	@RequestMapping(value="/getOrderByNo",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object getOrderByNo(String no){
		return orderService.getOrderByNo(no).toString();
	}
	@RequestMapping(value="/payByNo",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object payByNo(String no){
		return orderService.payOrder(no).toString();
	}
}
