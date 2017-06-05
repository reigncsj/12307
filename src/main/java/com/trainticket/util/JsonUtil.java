package com.trainticket.util;

import java.util.List;

import com.trainticket.bean.Ticket;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static JSONObject getJSONObject(JSONArray array,String code){
		JSONObject ob = new JSONObject();
		ob.put("retCode", code);
		ob.put("result", array);
		return ob;
	}
	public static JSONObject getJSONObject(String code,JSONArray array){
		JSONObject ob = new JSONObject();
		ob.put("retCode", code);
		ob.put("result", array);
		return ob;
	}
	public static JSONObject getJSONObject(String code,Object array){
		JSONObject ob = new JSONObject();
		ob.put("retCode", code);
		ob.put("result", array);
		return ob;
	}
	public static JSONObject getJSONObject(String code,Object array,Object array1){
		JSONObject ob = new JSONObject();
		ob.put("retCode", code);
		ob.put("result1", array);
		ob.put("result2", array1);
		return ob;
	}
	public static JSONObject getJSONObject(String array,String code){
		JSONObject ob = new JSONObject();
		ob.put("retCode", code);
		ob.put("result", array);
		return ob;
	}
}
