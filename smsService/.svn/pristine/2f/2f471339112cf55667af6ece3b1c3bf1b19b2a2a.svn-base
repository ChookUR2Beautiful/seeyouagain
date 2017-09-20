package com.sms.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sms.common.ErrorCode;
import com.sms.common.utilClass;
import com.sms.entity.smsSendObj;

/**
 * 短信发送服务
 * @author douk
 *
 */
public class smsSendInterfaceImp implements smsSendInterface {
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(smsSendInterfaceImp.class);
	
	/**
	 * 随机选择短信发送通道
	 */
	@Override
	public int smsRandomChannel(String msg, String[] ph) {
		int key = (int)(Math.random()*3);
		int result = 0;
		switch (key) {
		case 0:
			result = MwPlatformSend(msg,ph);
			if(result > ErrorCode.SUCCESS){
				result = smsZucpSend(msg,ph);
			}
			break;
		case 1:
			result = smsZucpSend(msg,ph);
			if(result > ErrorCode.SUCCESS){
				result = MwPlatformSend(msg,ph);
			}
			break;
		case 2:
//			result = MwPlatformSend(msg,ph);
//			if(result > ErrorCode.SUCCESS){
//				result = smsZucpSend(msg,ph);
//			}
			result = smsPlatformSend(msg,ph);
			if(result > ErrorCode.SUCCESS){
				result = MwPlatformSend(msg,ph);
			}
			break;
		default:
			result = MwPlatformSend(msg,ph);
			break;
		}
		return result;
	}
	
	/**
	 * 单元科技平台发送接口
	 */
	@Override
	public int smsPlatformSend(String msg, String[] ph) {
		Document doc = null;
		String xml = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String tempp = Arrays.toString(ph);
		String phone = tempp.substring(1, tempp.length() -1);
		try {
			log.info("单元科技平台收到的内容："+ msg);
			String content = msg+"【寻蜜鸟】";
			//短信服务地址
			String url = "http://183.61.109.140:9801/CASServer/SmsAPI/SendMessage.jsp";
			HttpPost httppost = new HttpPost(url);
			//设置请求头
	        httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
	        httppost.setHeader("Referer",url);
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("userid","92100"));
	        nameValuePairs.add(new BasicNameValuePair("password","a123456"));
	        nameValuePairs.add(new BasicNameValuePair("destnumbers",phone));
	        nameValuePairs.add(new BasicNameValuePair("msg",content));
	        nameValuePairs.add(new BasicNameValuePair("sendtime",""));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));	  
			
			//设置请求超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
	        httppost.setConfig(requestConfig);
	        HttpResponse response = httpclient.execute(httppost);//执行http请求
	        
			//转换json为map返回
			HttpEntity resEntity = response.getEntity();
			xml = EntityUtils.toString(resEntity);
			//System.out.println(xml);		
			doc = DocumentHelper.parseText(xml);
			//XML解析
			int recode = Integer.parseInt(doc.getRootElement().attributeValue("return").trim());//状态码
			if(recode > 0 || recode < 0){
				log.error("单元科技短信发送失败 错误码:"+recode+" 内容："+msg+"  手机号: "+phone);
				return ErrorCode.FAILURE;
			}
			log.info("单元科技短信发送成功   返回编码:"+recode);
			return ErrorCode.SUCCESS;
		}catch (Exception e) {
			log.error("单元科技短信发送失败...  内容："+msg+"  手机号: "+phone,e);
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.error("单元科技短信发送失败...  内容："+msg+"  手机号: "+phone);
		return ErrorCode.FAILURE;
	
	}
	
	/**
	 * 创世漫道平台发送接口
	 */
	@Override
	public int smsZucpSend(String msg, String[] ph) {
		Document doc = null;
		String xml = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String tempp = Arrays.toString(ph);
		String phone = tempp.substring(1, tempp.length() -1);
		try {
			String content = msg+"【寻蜜鸟】";
			log.info("创世漫道短信平台收到的内容："+ msg);
			//短信服务地址
			String url = "http://sdk.entinfo.cn:8060/webservice.asmx/mt";
			HttpPost httppost = new HttpPost(url);
			//设置请求头
	        httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
	        httppost.setHeader("Host","sdk.entinfo.cn");
	        //httppost.setHeader("Content-Length",content.length()+"");
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("sn","DXX-WSS-100-06298"));
	        nameValuePairs.add(new BasicNameValuePair("pwd","BE151727F75BA15A74D79992CC9E840D"));
	        nameValuePairs.add(new BasicNameValuePair("mobile",phone));
	        //nameValuePairs.add(new BasicNameValuePair("content",new String(content.getBytes("GBK"))));
	        nameValuePairs.add(new BasicNameValuePair("content",content));
	        nameValuePairs.add(new BasicNameValuePair("ext",""));
	        nameValuePairs.add(new BasicNameValuePair("stime",""));
	        nameValuePairs.add(new BasicNameValuePair("rrid",""));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "GBK"));	
	        
	        //设置请求超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
	        httppost.setConfig(requestConfig);
			HttpResponse response = httpclient.execute(httppost);//执行http请求
			//转换json为map返回
			HttpEntity resEntity = response.getEntity();
			xml = EntityUtils.toString(resEntity);
			doc = DocumentHelper.parseText(xml);
			//XML解析
			long result = Long.parseLong(doc.getRootElement().getStringValue());
			if(result < 0){
				log.error("短信发送失败 错误码:"+result+" 内容："+msg+"  手机号: "+phone);
				return ErrorCode.FAILURE;
			}
			log.info("创世漫道短信发送成功   返回编码:"+result);
			return ErrorCode.SUCCESS;
		}catch (Exception e) {
			log.error("创世漫道短信发送失败...  内容："+msg+"  手机号: "+phone,e);
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ErrorCode.FAILURE;
	}
	
	/**
	 * 梦网平台发送接口
	 */
	@Override
	public int MwPlatformSend(String msg, String[] ph) {
		Document doc = null;
		String xml = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String tempp = Arrays.toString(ph);
		String msmNum = String.valueOf(ph.length);
		String phone = tempp.substring(1, tempp.length() -1).replaceAll(" ", "");//替换掉空格
		try {
			log.info("梦网平台收到的内容："+ msg);
			//短信服务地址
			String url = "http://61.145.229.29:9006/MWGate/wmgw.asmx/MongateCsSpSendSmsNew";
			
			HttpPost httppost = new HttpPost(url);
			//设置请求头
	        httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
	        httppost.setHeader("Referer",url);
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("userId","J02321"));
	        nameValuePairs.add(new BasicNameValuePair("password","556215"));
	        nameValuePairs.add(new BasicNameValuePair("pszMobis",phone));
	        nameValuePairs.add(new BasicNameValuePair("pszMsg",msg));
	        nameValuePairs.add(new BasicNameValuePair("iMobiCount",msmNum));
	        nameValuePairs.add(new BasicNameValuePair("pszSubPort",""));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
	        
	        //设置请求超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
	        httppost.setConfig(requestConfig);
			HttpResponse response = httpclient.execute(httppost);//执行http请求
			//转换json为map返回
			HttpEntity resEntity = response.getEntity();
			xml = EntityUtils.toString(resEntity);
			
			doc = DocumentHelper.parseText(xml);
			//XML解析
			long recode = Long.parseLong(doc.getRootElement().getStringValue().trim());//状态码
			
			if(recode >= -1 || recode >= -999){
				if(recode > 1){
					log.info("梦网平台短信发送成功   返回编码:"+recode);
					return ErrorCode.SUCCESS;
				}else{
					log.error("梦网平台短信发送失败 错误码:"+recode+" 内容："+msg+"  手机号: "+phone);
					return ErrorCode.FAILURE;
				}
			}else{
				if(recode < -10033){
					log.info("梦网平台短信发送成功   返回编码:"+recode);
					return ErrorCode.SUCCESS;
				}
				log.error("梦网平台短信发送失败 错误码:"+recode+" 内容："+msg+"  手机号: "+phone);
				return ErrorCode.FAILURE;
			}
		}catch (Exception e) {
			log.error("梦网平台短信发送失败...  内容："+msg+"  手机号: "+phone,e);
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ErrorCode.FAILURE;
	}
	
	/**
	 * 梦网语音短信发送接口
	 */
	@Override
	public int MwMongateSend(String msg, String ph) {
		Document doc = null;
		String xml = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			log.info("梦网语音短信收到的内容："+ msg);
			//短信服务地址
			String url = "http://61.145.229.28:5001/voiceprepose/MongateSendSubmit";
			
			HttpPost httppost = new HttpPost(url);
			//设置请求头
	        httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
	        httppost.setHeader("Referer",url);
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("userId","YY0029"));
	        nameValuePairs.add(new BasicNameValuePair("password","236987"));
	        nameValuePairs.add(new BasicNameValuePair("pszMobis",ph));
	        nameValuePairs.add(new BasicNameValuePair("pszMsg",msg));//验证码（4到8位字符 只能是数字或者字母不区分大小写）
	        nameValuePairs.add(new BasicNameValuePair("iMobiCount","1"));
	        nameValuePairs.add(new BasicNameValuePair("pszSubPort",""));
	        nameValuePairs.add(new BasicNameValuePair("MsgId",utilClass.getYearInfo()));
	        nameValuePairs.add(new BasicNameValuePair("PtTmplId","100033"));
	        nameValuePairs.add(new BasicNameValuePair("msgType","1"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
	        
	        //设置请求超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
	        httppost.setConfig(requestConfig);
			HttpResponse response = httpclient.execute(httppost);//执行http请求
			//转换json为map返回
			HttpEntity resEntity = response.getEntity();
			xml = EntityUtils.toString(resEntity);
			
			doc = DocumentHelper.parseText(xml);
			//XML解析
			String recode = String.valueOf(doc.getRootElement().getStringValue().trim());//状态码
			
			if(recode.length() <= 7){
				log.error("梦网语音短信发送失败 错误码:"+recode+" 内容："+msg+"  手机号: "+ph);
				return ErrorCode.FAILURE;
			}
			log.info("梦网语音短信发送成功   返回编码:"+recode);
			return ErrorCode.SUCCESS;
		}catch (Exception e) {
			log.error("梦网语音短信发送失败...  内容："+msg+"  手机号: "+ph,e);
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ErrorCode.FAILURE;
	}
	
	/**
	 * 网关发送短信
	 */
	@Override
	public int smsGatewaySend(String msg, String[] ph) {
		return 0;
	}
	
	/**
	 * 微信平台发送推送消息
	 */
	@Override
	public int WxPlatformSend(wxSendService wxService,smsSendObj obj) {
		boolean isResult = wxService.sendWxPush(obj);
		if(!isResult){
			wxService.sendWxPush(obj);
		}
		return 0;
	}

	public static void main(String args[]){
		
		smsSendInterfaceImp imp = new smsSendInterfaceImp();
		
		String[] str = {"17087797166"};
		imp.MwPlatformSend("您的验证码为878782221", str);
		
		//imp.smsPlatformSend("您的验证码为7888777", str);

//		
//		imp.MwMongateSend("大幅度", str);
		
//		long recode = -9102165995970707084L;  -1 小  -999 小
//		String recode = "MV:00011";  // 9102165995970707084L  >  -1  false        9102165995970707084L  >  -999  false
//		if(recode.length() <= 7){
//			System.out.println("错误！！！");
//		}else{
//			System.out.println("成功！！！");
//		}
		
//		byte[] aa = "123456".getBytes();
//		System.out.println(aa.length);
		
	}
}
