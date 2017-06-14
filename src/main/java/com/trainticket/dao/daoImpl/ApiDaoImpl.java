package com.trainticket.dao.daoImpl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Repository;

import com.trainticket.dao.ApiDao;

import net.sf.json.JSONObject;

@Repository("apiDao")
public class ApiDaoImpl implements ApiDao {
	//向第三方api发送请求并获取站站之间的列车信息
	@Override
	public JSONObject queryByStationToStation(String start,String end){
		 	JSONObject jsonObject = null;
	        StringBuffer buffer = new StringBuffer();
	        try {
	        	//将中文车站转码
	        	String s=transtoutf8(start);
	        	String e=transtoutf8(end);
	        	//第三方路径
	        	String url1="http://apicloud.mob.com/train/tickets/queryByStationToStation?key=1ce8ef674fb8a&start="
	        			+s+"&end="+e;
	            URL url = new URL(url1);
	            //发送连接请求
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

	            httpUrlConn.setDoOutput(true);
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setUseCaches(false);
	            //确定请求方式
	            httpUrlConn.setRequestMethod("GET");

	            if ("GET".equalsIgnoreCase("GET"))
	                httpUrlConn.connect();
	            InputStream inputStream = httpUrlConn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	            //接收返回的信息
	            String str = null;
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }
	            bufferedReader.close();
	            inputStreamReader.close();
	            //连接关闭
	            inputStream.close();
	            inputStream = null;
	            httpUrlConn.disconnect();
	            //将字符转转化为json对象并返回
	            jsonObject = JSONObject.fromObject(buffer.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return jsonObject;
	}

	//转成UTF-8码
	private String transtoutf8(String q){
		 try {
			 String xmString = new String(q.toString().getBytes("UTF-8"));
			//String xmString = new String(q.toString().getBytes("GBK"));  
			return URLEncoder.encode(xmString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 return "";
	}
	//将车次的首字母大写
	private String upper(String id){
		char a=id.charAt(0);
		if(Character.isLowerCase(a)){
			return id.substring(0, 1).toUpperCase()+id.substring(1);
		}
		return id;
	}
	//向第三方api发送请求并获取特定的列车信息
	@Override
	public JSONObject queryByTrainId(String id) {
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
        	//第三方路径
        	String url1="http://apicloud.mob.com/train/tickets/queryByTrainNo?key=1ce8ef674fb8a&trainno="
        			+upper(id);
            URL url = new URL(url1);
            //发送连接请求
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            //确定请求方式
            httpUrlConn.setRequestMethod("GET");

            if ("GET".equalsIgnoreCase("GET"))
                httpUrlConn.connect();

            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
          //接收返回的信息
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 关闭连接
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            //将字符转转化为json对象并返回
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
	}
}
