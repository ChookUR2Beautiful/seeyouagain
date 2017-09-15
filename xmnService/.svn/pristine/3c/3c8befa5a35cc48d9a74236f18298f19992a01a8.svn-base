package com.xmniao.xmn.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
/**
 * 
* 项目名称：saasService   
* 类名称：HttpConnectionUtil   
* 类描述：Http请求工具类
* 创建人：liuzhihao   
* 创建时间：2016年3月29日 下午5:47:58   
* @version    
*
 */
public class HttpConnectionUtil {
	
	 private static final String SERVLET_POST = "POST" ;
     private static final String SERVLET_GET = "GET" ;
     /**
      * 数据准备
      * @param paramMap
      * @return
      */
	 private static String prepareParam(Map<String,Object> paramMap){
         StringBuffer sb = new StringBuffer();
         if(paramMap.isEmpty()){
             return "" ;
         }else{
             for(String key: paramMap.keySet()){
                 String value = (String)paramMap.get(key);
                 if(sb.length()<1){
                     sb.append(key).append("=").append(value);
                 }else{
                     sb.append("&").append(key).append("=").append(value);
                 }
             }
             return sb.toString();
         }
     }
	
	/**
	 * 模拟POST请求
	 * @param urlStr
	 * @param paramMap
	 * @throws Exception
	 */
	public static String  requestPost(String urlStr,Map<String,Object> paramMap ) throws Exception{
//        URL url = new URL(urlStr);
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod(SERVLET_POST);
        String paramStr = prepareParam(paramMap);
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        OutputStream os = conn.getOutputStream();    
//        os.write(paramStr.toString().getBytes("UTF-8"));    
//        os.close();        
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//        String line ;
//        String result ="";
//        while( (line =br.readLine()) != null ){
//            result +=line;
//        }
//        br.close();
        
        String url=urlStr+"?"+paramStr;
        String result= ClientCustomSSL.doGet(url);
        return result;
    }
	
	/**
	 * 模拟GET请求
	 * @param urlStr
	 * @param paramMap
	 * @throws Exception
	 */
    public static String  requestGet(String urlStr,Map<String,Object> paramMap ) throws Exception{
        String paramStr = prepareParam(paramMap);
        if(paramStr == null || paramStr.trim().length()<1){
           
        }else{
            urlStr +="?"+paramStr;
        }
        System.out.println(urlStr);
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(SERVLET_GET);
        conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");
        conn.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        String line ;
        String result ="";
        while( (line =br.readLine()) != null ){
            result +=line;
        }
        br.close();
        return result;
    }
    
    /**
     * 
    * @Title: doPost
    * @Description: 模拟post请求
    * @return String    返回类型
    * @author
    * @throws
     */
    public static String doPost(String url, String paramStr) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(10000).setConnectTimeout(10000)
				.setSocketTimeout(10000).build();
		CloseableHttpResponse response = null;
		String content = "";
		try {
			StringEntity s = new StringEntity(paramStr, "UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			post.setConfig(config);
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
    
    /**
     * 
    * @Title: doPost
    * @Description: 模拟post请求
    * @return String    返回类型
    * @author
    * @throws
     */
    public static String doPost(String url, JSONObject json) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String result = "";
		try {
			StringEntity s = new StringEntity(json.toString(),"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				result = EntityUtils.toString(entity);// 返回json格式：
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
    /**
     * 获取拼装参数后的url
     * @param paramMap
     * @param url
     * @return
     */
    
    public static String getUrl(Map<String, String> paramMap,String url){
        String requestParam = "";
        for (Entry<String, String> entry : paramMap.entrySet()) {
            requestParam += "&" + entry.getKey() + "=" + String.valueOf(entry.getValue());
        }
        requestParam = requestParam.substring(1,requestParam.length());
        String sendUrl = url + "?" + requestParam;
        return sendUrl;
    }

    
    
    public static void main(String[] args) throws Exception {
    	 String urlStr = "http://localhost:8081/sellerService/summaryinfo";
    	 Map<String, Object> paraMap =new HashMap<String, Object>();
		paraMap.put("sessiontoken", "00924b45546b10571729a15f24049400");
		paraMap.put("appversion", "1.0.1");
		paraMap.put("systemversion", "android");
		paraMap.put("apiversion", "55");
		paraMap.put("sellerid", "114");
 //      URLConnectionUtil.doGet(urlStr, map);
		System.out.println(HttpConnectionUtil.requestPost(urlStr, paraMap));
	}
}
