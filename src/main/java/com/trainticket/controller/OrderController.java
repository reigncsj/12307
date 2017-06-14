package com.trainticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.model.OrderCommand;
import com.trainticket.service.OrderService;
import com.trainticket.util.MyDate;

import net.sf.json.JSONObject;

@Controller
public class OrderController {
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	//生成订单路径的控制器
	@RequestMapping(value="/order",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object buildOrderTest1(String tcode,String start,String end,String date,String username,String uid,String type,String code){
		OrderCommand o=new OrderCommand(tcode,start,end,date,username,uid,type,code); 
		return orderService.buildOrder(o).toString();
	}
	@RequestMapping(value="/twoOrder",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object buildOrderTest2(String tcode,String start,String end,String date,String username,String uid,String type,
			String code,String tcode2,String start2,String end2,String date2,String username2,String uid2,String type2,
			String code2){
		OrderCommand o=new OrderCommand(tcode,start,end,date,username,uid,type,code); 
		OrderCommand o2=new OrderCommand(tcode2,start2,end2,date2,username2,uid2,type2,code2);
		return orderService.buildOrder(o,o2).toString();
	}
	//测试生成订单路径的控制器
	@RequestMapping(value="/orderTest",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object buildOrderTest(){
		OrderCommand o=new OrderCommand("5u00000G380B","南昌","北京",MyDate.getTomorrow(),"123456abc","360201199601051528","ed","G38"); 
		return orderService.buildOrder(o).toString();
	}
	//获取用户下的所有订单的控制器
	@RequestMapping(value="/getOrderByName",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object getOrderByName(String name){
		return orderService.getOrderByName(name).toString();
	}
	//获取特定订单的控制器
	@RequestMapping(value="/getOrderByNo",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object getOrderByNo(String no){
		return orderService.getOrderByNo(no).toString();
	}
	//获取未支付订单的控制器
	@RequestMapping(value="/getUnPaidOrder",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object getUnPaidOrder(String username){
		return orderService.getUnPaidOrder(username).toString();
	}
	//获取已支付订单的控制器
	@RequestMapping(value="/getPaidOrder",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object getPaidOrder(String username){
		return orderService.getPaidOrder(username).toString();
	}
	//支付订单的控制器
	@RequestMapping(value="/payOrderByNo",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object payOrderByNo(String no){
		return orderService.payOrder(no).toString();
	}
}
