package com.xmniao.xmn.core.xmer.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.request.SendCodeRequest;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

@Service
public class SendCodeService {
	
	private final Logger log = Logger.getLogger(SendCodeService.class);
	
	/**
	 * 注入短信验证码URL
	 */
	@Autowired
	private String smsUrl;
	
	@Autowired
	private XmerDao xmerDao;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	/**
	 * 
	* @Title: sendCode
	* @Description: 发送短信
	* @return String    返回类型
	* @author liuzhihao
	* @throws
	 */
	public String sendCode(SendCodeRequest request) {
		log.info("sendSms start......");
		String result = null;
		try {
			if ("".equals(request.getPhone()))
				return null;
			if(smsUrl==null||smsUrl.equals(""))smsUrl = Constant.SMS_URL;	//防止注入失败,使用默认接口
			URL url = new URL(smsUrl + "smsSend"); // 发送短信 url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true); // post请求 需设置
			connection.setDoInput(true); // post请求 需设置
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); // 发送格式
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> list = new ArrayList<String>();
			list.add(request.getPhone());
			map.put("appid", Constant.APP_ID);
			map.put("text", request.getSendType());
			map.put("phones", list);
			out.write("p=" + JSON.toJSONString(map));
			out.flush();
			out.close();
			String sCurrentLine = "";
			String sTotalString = "";
			InputStream l_urlStream = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream, "utf-8"));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine;
			}
			result = sTotalString;
		} catch (Exception e) {
			log.error("发送短信异常", e);
		}
		log.info("sendSms end......");
		return result;
	}
	
	
	/**
	 * 
	* @Title: sendMassage
	* @Description: 发送短信信息
	* @return String    返回类型
	* @author liuzhihao
	* @throws
	 */
	public String sendMassage(Map<Object,Object> map){
		try {
			if (StringUtils.isEmpty(map.get("account").toString())){
				return null;
			}
			if(smsUrl==null ||smsUrl.equals(""))smsUrl = Constant.SMS_URL;	//防止注入失败,使用默认接口
			URL url = new URL(smsUrl + "smsSend"); // 发送短信 url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true); // post请求 需设置
			connection.setDoInput(true); // post请求 需设置
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); // 发送格式
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			Map<String, Object> massageMap = new HashMap<String, Object>();
			List<String> list = new ArrayList<String>();
			list.add(map.get("account").toString());
			massageMap.put("appid", Constant.APP_ID);
			massageMap.put("text",map.get("text"));
			massageMap.put("phones", list);
			out.write("p=" + JSON.toJSONString(massageMap));
			out.flush();
			out.close();
			String sCurrentLine = "";
			String sTotalString = "";
			InputStream l_urlStream = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream, "utf-8"));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine;
			}
			return sTotalString;
		} catch (Exception e) {
			log.error("发送短信异常", e);
			return "";
		}
	}
	
	/**
	 * @throws MalformedURLException 
	 * 
	* @Title: languageCode
	* @Description:语音验证码
	* @return String    返回类型
	* @author liuzhihao
	* @throws
	 */
	public String languageCode(String phone) throws MalformedURLException{
		try{
			//验证电话号码
			if(StringUtils.isEmpty(phone)){
				log.info("电话号码不能为空");
			}
			//从redis中获取验证码
			String codekey = phone+Constant.USER_XMER_KEY;
			String code =sessionTokenService.getStringForValue(codekey).toString();//验证码
			if(smsUrl==null ||smsUrl.equals(""))smsUrl = Constant.SMS_URL;	//防止注入失败,使用默认接口
			URL url = new URL(smsUrl+"smsMongateSend");//语音短信url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();//建立网络连接
			connection.setRequestMethod("POST");
			connection.setDoOutput(true); // post请求 需设置
			connection.setDoInput(true); // post请求 需设置
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); // 发送格式
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> list = new ArrayList<String>();
			list.add(phone);
			map.put("appid", Constant.APP_ID);//appid
			map.put("text", code);//验证码
			map.put("phones", list);//电话号码
			out.write("p=" + JSON.toJSONString(map));
			out.flush();
			out.close();
			String sCurrentLine = "";
			String sTotalString = "";
			InputStream l_urlStream = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream, "utf-8"));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine;
			}
			return sTotalString;
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取语音验证码异常");
			return "";
		}
	}
	
	/**
	 * 
	* @Title: checkPhone
	* @Description: 判断用户手机号码是否已注册
	* @return int    返回类型
	* @author liuzhihao
	* @throws
	 */
	public int checkPhone(String phone){
		int result = 0;
		try{
			result = xmerDao.checkPhone(phone);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
