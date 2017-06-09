package com.trainticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.bean.QueryInf;
import com.trainticket.service.ApiService;
import com.trainticket.service.ParseDataService;
import com.trainticket.service.StationService;
import com.trainticket.service.TicketService;
import com.trainticket.service.TransferService;
import com.trainticket.util.Configure;
import com.trainticket.util.MyDate;

import net.sf.json.JSONObject;

@Controller
public class TrainController {

	@Autowired
	@Qualifier("stationService")
	private StationService stationService;
	@Autowired
	@Qualifier("apiService")
	private ApiService apiService;
	@Autowired
	@Qualifier("parseDataService")
	private ParseDataService parseDataService;
	@Autowired
	@Qualifier("ticketService")
	private TicketService ticketService;
	@Autowired
	@Qualifier("transferService")
	private TransferService transferService;
	
	@RequestMapping(value="/station",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object station(String name){
		JSONObject array=stationService.getStationByName(name);
		return array.toString();
	}
	
	@RequestMapping(value="/stationpy",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object stationpy(String py){
		JSONObject array=stationService.getStationByPy(py);
		return array.toString();
	}
	
	@RequestMapping(value="/queryByStationToStation",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryByStationToStation(String start,String end){
		return ticketService.getTicket(new QueryInf(start, end, MyDate.getTomorrow(), "ADULT")).toString();
	}
	
	@RequestMapping(value="/queryByTrainId",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryByStationToStation(String id){
		JSONObject object=apiService.queryByTrainId(id);
		return object.toString();
	}
	
	@RequestMapping(value="/ticket",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryTicket(String start,String end,String date){
		return ticketService.getTicket(new QueryInf(start, end, date, "ADULT")).toString();
	}
	
	@RequestMapping(value="/oneTicket",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryOneTicket(String start,String end,String date,String code){
		return ticketService.getOneTicket(new QueryInf(start, end, date, "ADULT",code)).toString();
	}
	
	@RequestMapping(value="/querySomeTransferStation",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object querySomeTransferStation(String start,String end){
		return transferService.querySomeTransferStation(new QueryInf(start,end,Configure.TRANSFERNUM)).toString();
	}
	@RequestMapping(value="/queryAllTransferStation",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryAllTransferStation(String start,String end){
		return transferService.queryAllTransferStation(new QueryInf(start,end,Configure.TRANSFERNUM)).toString();
	}
	
	@RequestMapping(value="/queryUsualTransferStation",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryUsualTransferStation(String start,String end){
		return transferService.queryUsualTransferStation(new QueryInf(start,end,Configure.TRANSFERNUM)).toString();
	}
	
}
