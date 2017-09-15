package com.xmniao.xmn.core.push;

import com.alibaba.fastjson.JSONObject;
/**
 * 文件名：CommonHandle.java</br></br>
 * 功能描述：报文验证类</br>
 * Copyright (C) 2014 呙垒西个人   版权所有。
 * @author 呙垒西
 * @version 1.0</br>
 * 2014-12-10
 */
public class CommonHandle {
	
	public static String common(JSONObject json){
			if (json.containsKey("data")==false) {
				return HttpJsonDataHandle.errorRespones2(
						json.getString("source"),ErrorMessage.ERROR1001.getError(),
						ErrorMessage.ERROR1001.getMsg());
			}  
			if (json.containsKey("source")==false) {
				return HttpJsonDataHandle.errorRespones2(
						json.getString("source"),ErrorMessage.ERROR1002.getError(),
						ErrorMessage.ERROR1002.getMsg());
			}
			if (json.getString("data").length()<=0) {
				return HttpJsonDataHandle.errorRespones2(
						json.getString("source"),ErrorMessage.ERROR1004.getError(),
						ErrorMessage.ERROR1003.getMsg());
			}
			if (json.getString("source").length()<=0) {
				return HttpJsonDataHandle.errorRespones2(
						json.getString("source"),ErrorMessage.ERROR1005.getError(),
						ErrorMessage.ERROR1004.getMsg());
			}
			if (!json.getString("source").equalsIgnoreCase("ios")
					&&!json.getString("source").equals("android")) {
				return HttpJsonDataHandle.errorRespones2(
						json.getString("source"),ErrorMessage.ERROR1005.getError(),
						ErrorMessage.ERROR1005.getMsg());
			}
			return null;
		}
	
	/**验证向android推送消息参数是否为空*/
	public static String commonAndroid(JSONObject data){
		if (data.containsKey("accessid")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1006.getError(),
					ErrorMessage.ERROR1006.getMsg());
		}
		if (data.containsKey("secretkey")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1007.getError(),
					ErrorMessage.ERROR1007.getMsg());
		}
		if (data.containsKey("devicetoken")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1008.getError(),
					ErrorMessage.ERROR1008.getMsg());
		}
		if (data.containsKey("title")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1009.getError(),
					ErrorMessage.ERROR1009.getMsg());
		}
		if (data.containsKey("msg")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1010.getError(),
					ErrorMessage.ERROR1010.getMsg());
		}
		if (data.getString("accessid").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1011.getError(),
					ErrorMessage.ERROR1011.getMsg());
		}
		if (data.getString("secretkey").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1012.getError(),
					ErrorMessage.ERROR1012.getMsg());
		}
		if (data.getString("devicetoken").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1013.getError(),
					ErrorMessage.ERROR1013.getMsg());
		}
		if (data.getString("title").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1014.getError(),
					ErrorMessage.ERROR1014.getMsg());
		}
		if (data.getString("msg").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.ANDROID,ErrorMessage.ERROR1015.getError(),
					ErrorMessage.ERROR1015.getMsg());
		}
		return null;
	}

	/**验证向ios推送消息参数是否为空*/
	public static String commonIOS(JSONObject data){
		if (data.containsKey("accessid")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1006.getError(),
					ErrorMessage.ERROR1006.getMsg());
		}
		if (data.containsKey("secretkey")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1007.getError(),
					ErrorMessage.ERROR1007.getMsg());
		}
		if (data.containsKey("devicetoken")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1008.getError(),
					ErrorMessage.ERROR1008.getMsg());
		}
		if (data.containsKey("alert")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1016.getError(),
					ErrorMessage.ERROR1016.getMsg());
		}
		if (data.containsKey("sound")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1017.getError(),
					ErrorMessage.ERROR1017.getMsg());
		}
		if (data.containsKey("category")) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1018.getError(),
					ErrorMessage.ERROR1018.getMsg());
		}
		if (data.getString("alert").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1019.getError(),
					ErrorMessage.ERROR1019.getMsg());
		}
		if (data.getString("sound").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1020.getError(),
					ErrorMessage.ERROR1020.getMsg());
		}
		if (data.getString("category").length()<=0) {
			return HttpJsonDataHandle.errorRespones2(
					Constant.IOS,ErrorMessage.ERROR1021.getError(),
					ErrorMessage.ERROR1021.getMsg());
		}
		return null;
	}
}
