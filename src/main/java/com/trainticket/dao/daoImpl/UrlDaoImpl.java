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

import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Ticket;
import com.trainticket.dao.UrlDao;

import net.sf.json.JSONObject;


@Repository("urlDao")
public class UrlDaoImpl implements UrlDao {
	@Override
	public JSONObject getTicket(QueryInf q){
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
		try{
			String url1="https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date="+q.getDate()+"&"
					+ "leftTicketDTO.from_station="+q.getStart()+"&leftTicketDTO.to_station="+q.getEnd()+"&purpose_codes="+
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
	public JSONObject getTicketPrice(Ticket t){
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
		try{
			String url1="https://kyfw.12306.cn/otn/leftTicket/queryTicketPrice?train_no="+t.getTcode()
					+ "&from_station_no="+t.getSno()+"&to_station_no="+t.getEno()+"&seat_types="+t.getSeat()+"&train_date="+t.getRiqi() ;  
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
