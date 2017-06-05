package com.trainticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.bean.TransferInf;

import com.trainticket.service.TransferInfService;
import com.trainticket.util.Configure;

@Controller
public class TrainInfController {
	@Autowired
	@Qualifier("transferInfService")
	private TransferInfService transferInfService;
	
	
	@RequestMapping(value="/queryTransferInf",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryFirstTransferInfTest(String start,String transfer,String end)
	{
		return transferInfService.queryAllInf(new TransferInf(start,transfer,end)).toString();
	}
	
	@RequestMapping(value="/queryTransferInf",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryFirstTransferInf(@ModelAttribute("inf")TransferInf inf){
		return transferInfService.queryAllInf(inf).toString();
	}
}
