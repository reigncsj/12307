package com.trainticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainticket.bean.Train;
import com.trainticket.service.ApiService;
import com.trainticket.service.ParseDataService;
import com.trainticket.service.StationService;
import com.trainticket.service.TicketService;

import net.sf.json.JSONArray;
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
		JSONObject object=apiService.queryByStationToStation(start, end);
		return object.toString();
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
		return ticketService.getTicket(start, end, date, "ADULT").toString();
	}
	
}
