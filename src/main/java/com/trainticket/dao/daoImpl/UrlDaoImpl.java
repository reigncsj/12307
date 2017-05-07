package com.trainticket.dao.daoImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.stereotype.Repository;

import com.trainticket.dao.UrlDao;

import net.sf.json.JSONObject;


@Repository("urlDao")
public class UrlDaoImpl implements UrlDao {
	@Override
	public JSONObject getTicket(String scode,String ecode,String date,String purpose){
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
		try{
			String url1="https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date="+date+"&"
					+ "leftTicketDTO.from_station="+scode+"&leftTicketDTO.to_station="+ecode+"&purpose_codes="+
					//purpose;
					"ADULT";
			  SSLContext sc = SSLContext.getInstance("SSL");
              sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                       new java.security.SecureRandom());

            URL url = new URL(url1);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(sc.getSocketFactory());
            httpUrlConn.setHostnameVerifier(new TrustAnyHostnameVerifier());

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("GET");

            if ("GET".equalsIgnoreCase("GET"))
                httpUrlConn.connect();
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(Exception e){
		}
		return jsonObject;
	}
	@Override
	public JSONObject getTicketPrice(String code,String date,String s,String e,String seat){
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
		try{
			String url1="https://kyfw.12306.cn/otn/leftTicket/queryTicketPrice?train_no="+code
					+ "&from_station_no="+s+"&to_station_no="+e+"&seat_types="+seat+"&train_date="+date ;  
			SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                     new java.security.SecureRandom());
			URL url = new URL(url1);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(sc.getSocketFactory());
            httpUrlConn.setHostnameVerifier(new TrustAnyHostnameVerifier());

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("GET");

            if ("GET".equalsIgnoreCase("GET"))
                httpUrlConn.connect();
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(Exception e1){
		}
		return jsonObject;
	}
	 private static class TrustAnyTrustManager implements X509TrustManager {
	        
	        public void checkClientTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public void checkServerTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[] {};
	        }
	    }
	 
	    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    }

}
