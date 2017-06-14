package com.trainticket.dao.daoImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
import com.trainticket.model.QueryInf;
import com.trainticket.model.Ticket;

import net.sf.json.JSONObject;

//用来从12306相应的api获取数据
@Repository("urlDao")
public class UrlDaoImpl implements UrlDao {
	//获取票务数据
	@Override
	public JSONObject getTicket(QueryInf q){
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
		try{
			//生成对应的url路径
			String url1="https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date="+q.getDate()+"&"
					+ "leftTicketDTO.from_station="+q.getStart()+"&leftTicketDTO.to_station="+q.getEnd()+"&purpose_codes="+
					//purpose;
					"ADULT";
			//以https形式进行传输
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
            //确定传输的形式
            if ("GET".equalsIgnoreCase("GET"))
                httpUrlConn.connect();
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //获取返回的数据报文
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            //将获取的数据转化为Json对象
            jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(Exception e){
		}
		return jsonObject;
	}
	
	//获取特定一趟车对应的票价
	@Override
	public JSONObject getTicketPrice(Ticket t){
		JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
		try{
			//设置相应的路径
			String url1="https://kyfw.12306.cn/otn/leftTicket/queryTicketPrice?train_no="+t.getTcode()
					+ "&from_station_no="+t.getSno()+"&to_station_no="+t.getEno()+"&seat_types="+t.getSeat()+"&train_date="+t.getRiqi() ; 
			//以Https形式进行加密传输
			SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                     new java.security.SecureRandom());
			URL url = new URL(url1);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(sc.getSocketFactory());
            httpUrlConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            //确定传输类型
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("GET");

            if ("GET".equalsIgnoreCase("GET"))
                httpUrlConn.connect();
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //获取相应的报文
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            //将报文转化为json 对象
            jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(Exception e1){
		}
		return jsonObject;
	}
	//与https加密所需要的安全证书相关的方法
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

	    //转码方法，将中文转换为utf8编码
	    private String transtoutf8(String q){
			 
			 try {
				 //String xmString = new String(q.toString().getBytes("UTF-8"));
				String xmString = new String(q.toString().getBytes("GBK"));  
				return URLEncoder.encode(xmString, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			 return "";

		}
	    //获取晚点信息
		@Override
		public String getLateInf(QueryInf q) {
	        StringBuffer buffer = new StringBuffer();
	        try {
	        	//将中文转码
	        	String s=transtoutf8(q.getStart());
	        	//生成查询对应的字符串
	        	String e=s.replaceAll("%", "-");
	        	String url1="http://dynamic.12306.cn/mapping/kfxt/zwdcx/LCZWD/cx.jsp?"
	        			+ "cz="+s+"&cc="+q.getCode()+"&cxlx=0&rq="+q.getDate()+"&czEn="+e+"&yzm=1234";
	            URL url = new URL(url1);
	            // 以http方式传输
	            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

	            httpUrlConn.setDoOutput(true);
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setUseCaches(false);
	            // 以GET 方式传输
	            httpUrlConn.setRequestMethod("GET");

	            if ("GET".equalsIgnoreCase("GET"))
	                httpUrlConn.connect();
	            // 传输字符串设置为GBK
	            InputStream inputStream = httpUrlConn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	            //获取返回的字符串
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
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        //返回相应的字符串
	        return buffer.toString();
		}

}
