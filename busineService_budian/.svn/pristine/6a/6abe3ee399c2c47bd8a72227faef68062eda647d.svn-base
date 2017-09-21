package com.xmniao.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.domain.http.PhpHttpPageable;



/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：HttpUtil
 * 
 * 类描述： 接口请求
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月17日 下午3:03:25
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class HttpUtil {

	private final Logger log = Logger.getLogger(HttpUtil.class);

	private static HttpUtil httpUtil;
	private int connectionTimeout =2000; //new Integer(PropertiesUtil.readValue("http.connectionTimeout")); // ConnectionTimeout
	private int timeout =30000; //new Integer(PropertiesUtil.readValue("http.timeout"));// Timeout
	private int size =2048; //new Integer(PropertiesUtil.readValue("http.size"));;// 8192

	private HttpUtil() {

	}

	public static HttpUtil getInstance() {
		if (null == httpUtil) {
			synchronized (HttpUtil.class) {
				if (null == httpUtil) {
					httpUtil = new HttpUtil();
				}
			}
		}
		return httpUtil;
	}

	
	public <T> PhpHttpPageable<T> phpPost(String url,Object request) throws Exception  {
		String strResult = postForString(url, request, true);
		if (null != strResult && !"".equals(strResult)) {
			return JSON.parseObject(strResult, PhpHttpPageable.class);
		} else {
			return null;
		}
	}
	
	/**
	 * http信息初始化
	 * @return
	 */
	private HttpClient initHttpClient(){
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, timeout * 1000);
		HttpConnectionParams.setSocketBufferSize(httpParams, size);
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		return httpClient;
	}
	
	
	/**
	 * http返回数据处理
	 * @param httpResponse
	 * @return
	 * @throws Exception
	 */
	private String responseHandler(HttpResponse httpResponse) throws Exception{
		if(httpResponse != null){
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String strResult = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
				log.info("strResult-->" + strResult);
				return strResult;
			}else if (httpResponse.getStatusLine().getStatusCode() == 404 || httpResponse.getStatusLine().getStatusCode() == 500) {
				log.info(httpResponse.getStatusLine().getStatusCode());
				throw new Exception();
			} else {
				log.info(httpResponse.getStatusLine().getStatusCode());
			}
		}
		return null;
	}
	
	
	
	/**
	 * http客户端发送post请求,参数为JSON格式
	 * @param url 地址
	 * @param param 参数
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public HttpResponse postJson(String url, Object param) throws ClientProtocolException, IOException{
		HttpClient httpClient =initHttpClient();
		HttpPost httpRequest = new HttpPost(url + "?");
		
		String paramJson = JsonUtil.toJSONString(param);
		log.info("paramJson==>"+url+"?p="+paramJson);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("p",paramJson));
		httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		return httpClient.execute(httpRequest);
	}



	/**
	 * http客户端发送post请求,参数为普通格式
	 * @param url 地址
	 * @param param 参数
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public HttpResponse post(String url, Map<String, String> param) throws ClientProtocolException, IOException{
		HttpClient httpClient =initHttpClient();
		
		HttpPost httpRequest = new HttpPost(url + "?");
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
        Set<String> keySet = param.keySet();
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, (String)param.get(key)));  
        }
		httpRequest.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		return httpClient.execute(httpRequest);
		
	}
	
	public HttpResponse get(String url) throws ClientProtocolException, IOException{
		HttpClient httpClient =initHttpClient();
		HttpGet httpRequest = new HttpGet(url);
		return httpClient.execute(httpRequest);
	}
	
	/**
	 * http客户端发送请求，已字符串形式返回数据
	 * @param url 地址
	 * @param param 参数
	 * @return
	 * @throws Exception
	 */
	public String postForString(String url, Object param, boolean postJson) throws Exception{
		HttpResponse httpResponse = null;
		if(postJson){
			httpResponse = postJson(url, param);
		}else{
			httpResponse = post(url, (Map)param);
		}
		return responseHandler(httpResponse);
	}

	
	/**
	 * http客户端发送请求，返回对象
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public JSONObject postForObject(String url, Object param) throws Exception{
		String res = postForString(url, param, false);
		return JSON.parseObject(res);
	}
	
	public JSONObject getForObject(String url) throws Exception{
		return JSON.parseObject(responseHandler(get(url)));
	}

	public Map getForMap(String url) throws Exception{
		return JSON.parseObject(responseHandler(get(url)), Map.class);
	}

	/**
	 * http客户端发送post请求,参数为普通格式
	 * @param url 地址
	 * @param param 参数
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public HttpResponse postJsonData(String url, String jsonStr) throws ClientProtocolException, IOException{
		HttpClient httpClient =initHttpClient();
		HttpPost httpRequest = new HttpPost(url);
		httpRequest.addHeader("Content-Type", "application/json; charset=UTF-8");
		JSONObject json = JSONObject.parseObject(jsonStr);
		StringEntity entity = new StringEntity(json.toString(),"utf-8");
		httpRequest.setEntity(entity);
		return httpClient.execute(httpRequest);
		
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpUtil httpUtil = new HttpUtil();
		Map<String,String> map = new HashMap<String,String>();
	//	map.put("name", "大侠");
		map.put("uId", "23323");
//		map.put("remark", "狼来了");
//		SubsidyMsg msg = new SubsidyMsg();
//		msg.setUId("23235");
//		msg.setRemark("大白名");
		HttpResponse res = httpUtil.postJsonData("http://127.0.0.1:8080/payService/test/json4?name=小兵", JSONObject.toJSONString(map));
		HttpEntity entity = res.getEntity();
		InputStream is =  entity.getContent();
//		Reader reader = new InputStreamReader(is);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));  
         StringBuffer sb = new StringBuffer("");  
         String line = "";  
         String NL = System.getProperty("line.separator");  
         while ((line = in.readLine()) != null) {  
             sb.append(line + NL);  
         }  
         in.close();  
        System.out.println("接收到返回值:"+sb);
	}
	
}
