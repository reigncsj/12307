package com.trainticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.model.QueryInf;
import com.trainticket.model.TransferInf;
import com.trainticket.service.TrainInfService;
import com.trainticket.service.TransferInfService;
import com.trainticket.util.Configure;

@Controller
public class TrainInfController {
	@Autowired
	@Qualifier("transferInfService")
	private TransferInfService transferInfService;
	@Autowired
	@Qualifier("trainInfService")
	private TrainInfService trainInfService;
	
	//获取正晚点路径
	@RequestMapping(value="/queryLateInf",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryLateInf(String station,String date,String code){
		return trainInfService.getLateInf(new QueryInf(station,"",date,"",code)).toString();
	}
	
	//获取中转第一趟车的数据
	@RequestMapping(value="/queryFirstTransferInf",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryFirstTransferInf(String start,String transfer,String end,String date,int time)
	{
		return transferInfService.queryAllInf(new TransferInf(start,transfer,end,time,date)).toString();
	}
	//获取中转第二趟车的数据
	@RequestMapping(value="/querySecondTransferInf",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object querySecondTransferInf(String start, String end,String date,String arrive,int time)
	{
		return transferInfService.querySecondInf(new TransferInf(start,arrive,end,time,date)).toString();
	}
	
}
