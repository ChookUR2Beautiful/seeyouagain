package com.xmniao.xmn.core.push;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 文件名：RequestDataHandle.java</br></br>
 * 功能描述：封装HttpJson报文处理</br>
 * Copyright (C) 2014 呙垒西个人   版权所有。
 * @author 呙垒西
 * @version 1.0</br>
 * 2014-06-24
 */
public class HttpJsonDataHandle {
	
	
	/**处理返回JSONArray返回JSONObject*/
	public static JSONObject jsonHandle(String str){
		JSONObject object = JSONObject.parseObject(str);
		String sobj = object.getString("data");
		JSONArray arr = JSONArray.parseArray(sobj);
		JSONObject arrobj = new JSONObject();
		if (arr.size()!=0) {
			for (int i = 0; i < arr.size(); i++) {
				arrobj = JSONObject.parseObject(arr.get(i).toString());
			}
			return arrobj;
		}else {
			return null;
		}
	}
	
	/**失败*/
	public static String errorRespones(){
		JSONObject ob = new JSONObject();
		ob.put("data", new JSONArray());
		ob.put("error", ErrorMessage.ERROR.getError());
		ob.put("msg", ErrorMessage.ERROR.getMsg());
		ob.put("time", getTimes());
		return ob.toString();
	}
	
	/**自定义失败提示*/
	public static String errorRespones1(String source,String msg){
		JSONObject ob = new JSONObject();
		ob.put("data", new JSONArray());
		ob.put("source", source);
		ob.put("error", ErrorMessage.ERROR.getError());
		ob.put("msg", msg);
		ob.put("time", getTimes());
		return ob.toString();
	}
	/**自定义失败提示2*/
	public static String errorRespones2(String source,Integer error,String msg){
		JSONObject ob = new JSONObject();
		ob.put("data", new JSONArray());
		ob.put("error", error);
		ob.put("source", source);
		ob.put("msg", msg);
		ob.put("time", getTimes());
		return ob.toString();
	}
	
	/**成功*/
	@SuppressWarnings("rawtypes")
	public static String successRespones(List<?> list,JSONObject jsonObject){
		if(list == null)
			list = new ArrayList();
		JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(list));
		JSONObject json = new JSONObject();
		json.put("data", jsonArray);
		json.put("error", ErrorMessage.SUCCESS.getError());
		json.put("msg", ErrorMessage.SUCCESS.getMsg());
		json.put("time", getTimes());
		return json.toString();
	}
	
	/**成功1*/
	public static String successRespones1(String source){
		JSONObject json = new JSONObject();
		json.put("data", new JSONObject());
		json.put("error", 0);
		json.put("msg", ErrorMessage.SUCCESS.getMsg());
		json.put("time", getTimes());
		json.put("source", source);
		return json.toString();
	}
	
	/**成功2*/
	public static String successRespones2(JSONObject obj,String method){
		JSONObject json = new JSONObject();
		json.put("data", obj);
		json.put("error", 0);
		json.put("msg", ErrorMessage.SUCCESS.getMsg());
		json.put("time", getTimes());
		json.put("method", method);
		return json.toString();
	}
	
	
	/**
	 * 获取时间戳
	 * @return
	 */
	public static Long getTimes(){
		return System.currentTimeMillis();
	}
	
	
//	public static String errorRespones2(String method ,Integer error,String msg){
//		JSONObject ob = new JSONObject();
//		ob.put("data", new JSONArray());
//		ob.put("error", error);
//		ob.put("msg", msg);
//		ob.put("method", method);
//		ob.put("time", getTimes());
//		return ob.toString();
//	}
	
	
	/**
	 * 获取jsonObject报文data数据
	 * @param json
	 * @return
	 */
	public static JSONObject getDataJson(JSONObject json){
		String dataString = json.getString("data");
		JSONObject jsondata = JSONObject.parseObject(dataString);
		return jsondata;
	}
	/**
	 * 获取jsonObject报文data数据去除[]
	 * @param json
	 * @return
	 */
	public static JSONObject getDataJsons(JSONObject json){
		String dataString = json.getString("data");
		String datajson = jsonarrayHandle(dataString);
		JSONObject jsondata = JSONObject.parseObject(datajson);
		return jsondata;
	}
	
	public static String getDataJsons2(JSONObject json){
		String dataString = json.getString("data");
		String datajson = jsonarrayHandle(dataString);
		String jsonobj = "{'data:'"+datajson+"}";
		return jsonobj;
	}
	
	public static JSONArray getDataJsonArray(String strjson){
		JSONObject o = JSONObject.parseObject(strjson);
		JSONArray array = JSONArray.parseArray(o.getString("data").toString());
		return array;
	}
	
	/**
	 * 生产1-10的随机数
	 * @return
	 */
	public static int getMathRandom(){
		int number = new Random().nextInt(10) + 1;
		return number;
	}

	/**
	 * 截取jsonArray[]
	 * @param jsonarray
	 * @return
	 */
	public static String jsonarrayHandle(String jsonarray){
		String str = jsonarray.toString();
		String sta = str.replace("[", "");
		String sta1 = sta.replace("]", "");
		return sta1.trim();
	}
	/**验证经度纬度是否为空*/
	public static boolean checkLoctionInfo(JSONObject data){
		if(data.containsKey("x")){//   当前位置的经度
			 if(data.containsKey("y")){//   当前位置的纬度
					return true;
				}else{
					return false;
				}
		}else{
			return false;
		}
	}
	/**
	 * 判断此字符串是否可以被转为数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		  
	        try {
	            new BigDecimal(str);
	            return true;
	        } catch (Exception e) {
	            return false;
	       }
	  }
//	
//	/** 比较器*/
//	public static Comparator<CoContent> CompareContent(){
//		Comparator<CoContent> byLocationDistance = new Comparator<CoContent>() {
//			public int compare(final CoContent p1, final CoContent p2) {
//				return  (int)(Double.valueOf(p1.getMeters())-Double.valueOf(p2.getMeters()));
//			}
//		};
//		return byLocationDistance;
//	}
//	
	public static double DecimalOne (Double sum,Integer bit){
		double db=0.0;
		if (bit==1) {
			DecimalFormat df = new DecimalFormat("0.0");
		    db = Double.valueOf(df.format(sum));
		}
		if (bit==2) {
			DecimalFormat df = new DecimalFormat("0.00");
		    db = Double.valueOf(df.format(sum));
		}
		if (bit==3) {
			DecimalFormat df = new DecimalFormat("0.000");
		    db = Double.valueOf(df.format(sum));
		}
		if (bit==4) {
			DecimalFormat df = new DecimalFormat("0.0000");
		    db = Double.valueOf(df.format(sum));
		}
		return db;
	}
}
