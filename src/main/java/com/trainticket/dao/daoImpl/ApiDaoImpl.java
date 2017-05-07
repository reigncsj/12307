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
	@Override
	public JSONObject queryByStationToStation(String start,String end){
		 	JSONObject jsonObject = null;
	        StringBuffer buffer = new StringBuffer();
	        try {
	        	String s=transtoutf8(start);
	        	String e=transtoutf8(end);
	        	String url1="http://apicloud.mob.com/train/tickets/queryByStationToStation?key=1ce8ef674fb8a&start="
	        			+s+"&end="+e;
	            URL url = new URL(url1);
	            // httpЭ�鴫��
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

	            httpUrlConn.setDoOutput(true);
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setUseCaches(false);
	            // ��������ʽ��GET/POST��
	            httpUrlConn.setRequestMethod("GET");

	            if ("GET".equalsIgnoreCase("GET"))
	                httpUrlConn.connect();
	            // �����ص�������ת�����ַ���
	            InputStream inputStream = httpUrlConn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	            String str = null;
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }
	            bufferedReader.close();
	            inputStreamReader.close();
	            // �ͷ���Դ
	            inputStream.close();
	            inputStream = null;
	            httpUrlConn.disconnect();
	            jsonObject = JSONObject.fromObject(buffer.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return jsonObject;
	}

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
	
	private String upper(String id){
		char a=id.charAt(0);
		if(Character.isLowerCase(a)){
			return id.substring(0, 1).toUpperCase()+id.substring(1);
		}
		return id;
	}

	@Override
	public JSONObject queryByTrainId(String id) {
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {

        	String url1="http://apicloud.mob.com/train/tickets/queryByTrainNo?key=1ce8ef674fb8a&trainno="
        			+upper(id);
            URL url = new URL(url1);
            // httpЭ�鴫��
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // ��������ʽ��GET/POST��
            httpUrlConn.setRequestMethod("GET");

            if ("GET".equalsIgnoreCase("GET"))
                httpUrlConn.connect();
            // �����ص�������ת�����ַ���
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // �ͷ���Դ
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
	}
}
