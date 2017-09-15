package com.xmniao.xmn.core.push;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;
/**
 * 文件名：PushSingleDevice.java</br></br>
 * 功能描述：推送消息给单个设备</br>
 * Copyright (C) 2014 呙垒西个人   版权所有。
 * @author 呙垒西
 * @version 1.0</br>
 * 2014-12-10
 */
public class PushSingleDevice {
	
		private static final  String SECRETKEY="-1";
		private static final long ACCESSID=2100216713;
	
	
	/**
	 * 批量发送用户
	* @Title: AndroidAccountList
	* @Description: 
	* @return JSONObject    返回类型
	* @throws
	 */
//	public static JSONObject AndroidAccountList(Long accessid,String secretkey,int deviceType,String title,String msg,List<String> accountList){
//		XingeApp x = new XingeApp(accessid, secretkey);
//		JSONObject obj = x.pushAccountList(deviceType, accountList,message(title,msg,null));
//		return obj;
//	}
	/**
	 * 单个发送用户
	* @Title: AndroidAccountList
	* @Description: 
	* @return JSONObject    返回类型
	* @throws
	 */
	public static JSONObject AndroidAccount(int deviceType,String title,String msg,String account,String sendTime,int action_type,String activity){
		XingeApp x = new XingeApp(ACCESSID, SECRETKEY);
		JSONObject obj = x.pushSingleAccount(deviceType, account,message(title,msg,sendTime,action_type,activity));
		return obj;
	}
	
	/**
	 * 
	 * @param accessid 应用ID
	 * @param secretkey 密钥
	 * @param devicetoken 手机唯一身份识别码
	 * @param title 消息标题
	 * @param msg  消息内容
	 * @return
	 */
//	public static JSONObject Android(String devicetoken,String title,String msg,String sendTime){
//		XingeApp x = new XingeApp(ACCESSID, SECRETKEY);
//		JSONObject obj = x.pushSingleDevice(devicetoken, message(title,msg,null));
//		return obj;
//	}
	
	/**
	 * 
	 * @param accessid 应用ID
	 * @param secretkey 密钥
	 * @param devicetoken 手机唯一身份识别码
	 * @param alert 消息标题
	 * @param sound	通知声音
	 * @param category iOS类别
	 * @return
	 */
	public static JSONObject IOS(Long accessid,String secretkey,String devicetoken,String alert,String sound,String category){
		XingeApp x = new XingeApp(accessid, secretkey);
		JSONObject obj = x.pushSingleDevice(devicetoken, messageIOS(alert,sound,category), 1);
		return obj;
	}
	
	private static Message message(String title,String msg,String sendTime,int action_type,String activity){
		Message message = new Message();
		Style style = new Style(1);
		ClickAction action = new ClickAction();
		style = new Style(3,1,0,1,0);
//		action.setActionType(ClickAction.TYPE_URL);
		action.setActionType(action_type);
		action.setUrl(activity);
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put(Constant.KEY1, Constant.VALUE);
		custom.put(Constant.KEY2, Constant.V);
		message.setTitle(title);
		message.setContent(msg);
		message.setStyle(style);
		message.setAction(action);
		message.setCustom(custom);
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		if(sendTime!=null)
		message.setSendTime(sendTime);
		message.setType(Message.TYPE_NOTIFICATION);
		return message;
	}
	
	private static MessageIOS messageIOS(String alert,String sound,String category){
		MessageIOS message = new MessageIOS();
		message.setExpireTime(86400);
		message.setAlert(alert);
		message.setBadge(1);
		message.setSound(sound);
		message.setCategory(category);
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put(Constant.KEY1, Constant.VALUE);
		custom.put(Constant.KEY2, Constant.V);
		message.setCustom(custom);
		return message;
	}
}
