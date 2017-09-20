package com.sms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.sms.common.ConfigFileReader;
import com.sms.entity.smsSendObj;
import com.sms.entity.wxPushParam;


/**
 * 微信发送Service
 * @author Administrator
 *
 */
public class wxSendService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(wxSendService.class);
	
    /**
     * 注入redis处理
     */
    private StringRedisTemplate redisTemplate;
	
	/**
	 * 加载配置文件
	 */
	private ConfigFileReader appids;
	
	/**
	 * 微信access_token
	 */
	private String access_token = "";
	
	/**
	 * 微信access_token Redsi存储的KEY
	 */
	private String token_key = "access_token";
	
	public wxSendService() {
	}
	
	public wxSendService(StringRedisTemplate redisTemplate,ConfigFileReader appids) {
		this.redisTemplate = redisTemplate;
		this.appids = appids;
	}
	
	/**
	 * 从Redis获取微信 access_token
	 * @return
	 */
	public String getWxAccessToken(){
		log.info("从Redis获取access_token凭证开始...");
		try{
			access_token = redisTemplate.boundValueOps(token_key).get();
			if(access_token == null){
				//Redis access_token 为空则重新获取 存入 access_token
				HashMap<String, Object> map = getHttpAccessToken();
				if(map !=null && map.get("expires_in") == null){//说明从微信没有取到
					System.out.println("通过URL获取 access_token 失败！   "+map);
				}else if(map !=null && map.get("expires_in") != null){
					access_token = map.get("access_token")+"";
					redisTemplate.boundValueOps(token_key).set(access_token);//存储access_token到Redis
					
					Integer expire = Integer.valueOf(map.get("expires_in")+""); 
					redisTemplate.expire(token_key, expire-500, TimeUnit.SECONDS);//存储access_token 的时间为6800秒
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("从Redis获取access_token凭证结束...");
		return access_token;
	}
	
	/**
	 * 从微信平台 access_token
	 * @return
	 */
	public HashMap<String, Object> getHttpAccessToken(){
		log.info("从微信平台获取access_token凭证开始...");
		//获取微信 appid 及 appsecret
		String appid = String.valueOf(appids.p.get("appid"));
		String appSecret = String.valueOf(appids.p.get("appSecret"));
		
		HashMap<String, Object> map = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			//微信平台获取 access_token URL地址
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appSecret;
			HttpGet httpget = new HttpGet(url);
	        //设置请求超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
	        httpget.setConfig(requestConfig);
			HttpResponse response = httpclient.execute(httpget);//执行http请求
			
			if(response.getStatusLine().getStatusCode() == 200){
				HttpEntity resEntity = response.getEntity();
				String result = EntityUtils.toString(resEntity);
				map = JSON.parseObject(result, HashMap.class);
			}else{
				httpget.abort();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("从微信平台获取access_token凭证结束...");
		return map;
	}
	
	/**
	 * 发送微信推送消息
	 * @param type 类型
	 * @return
	 */
	public boolean sendWxPush(smsSendObj obj){
		//从Redis获取微信access_token
		String access_token = getWxAccessToken();
		//发送微信推送
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//组装参数
		wxPushParam param = new wxPushParam();
		param.setTouser(obj.getOpenid());//openid
		param.setBid(obj.getBid());//订单编号
		param.setSellername(obj.getSellername());//商户名称或商品名称
		param.setMoney(obj.getMoney());//支付金额
		param.setSdate(obj.getSdate());//支付时间
		param.setTitle(obj.getTitle());//通知标题
		param.setRemarks(obj.getRemarks());//通知备注
		
		String sendStr = "";
		if(obj.getType().equals("1")){//附近美食订单
			sendStr = param.ParamToJson();
		}else{//生鲜订单
			sendStr = param.FreshParamToJson();
		}
		
		//成功状态
		boolean tag = false;
		try {
			HttpPost httppost = new HttpPost(url);
			//设置请求头
	        httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
	        httppost.setHeader("Referer",url);
	        
	        StringEntity entity = new StringEntity(sendStr,"UTF-8");
	        httppost.setEntity(entity);
	        //设置请求超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
	        httppost.setConfig(requestConfig);
			HttpResponse response = httpclient.execute(httppost);//执行http请求
			
			if(response.getStatusLine().getStatusCode() == 200){
				//转换json为map返回
				HttpEntity resEntity = response.getEntity();
				String json = EntityUtils.toString(resEntity);
				
				HashMap<String, Object> map = JSON.parseObject(json, HashMap.class);
				if(map !=null && map.get("msgid") != null){
					tag = true;
					log.info("微信平台发送消息成功...   "+json);
				}else{
					log.info("微信平台发送消息失败...   "+json);
				}
			}else{
				httppost.abort();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tag;
	}

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public ConfigFileReader getAppids() {
		return appids;
	}

	public void setAppids(ConfigFileReader appids) {
		this.appids = appids;
	}
}
