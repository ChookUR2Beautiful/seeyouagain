package com.xmniao.xmn.core.base;

import java.io.Serializable;

/**
 * 
 * 项目名称：UzWeb
 * 
 * 类名称：Resultable
 * 
 * 类描述： 响应结果
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年8月14日 下午4:12:18
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class Resultable implements Serializable {

	private static final long serialVersionUID = 7032650742134406471L;

	private Boolean success;// 状态
	private String msg;// 消息
	private Object data;

	public Resultable() {
	}

	public Resultable(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Resultable(Boolean success, String msg, Object data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public Resultable(Object data) {
		this.success = true;
		this.msg = "";
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Resultable [success=" + success + ", msg=" + msg + ", data=" + data + "]";
	}
	
	public static Resultable success(String msg,Object object){
		return new  Resultable(true,msg,object);
	}
	
	public static Resultable success(String msg){
		return new  Resultable(true,msg);
	}
	
	public static Resultable success(){
		return new  Resultable(true,"操作成功");
	}
	
	public static Resultable defeat(String msg){
		return new  Resultable(false,msg);
	}
	
	public static Resultable defeat(){
		return new  Resultable(false,"操作失败");
	}
	
	public static Resultable defeat(String msg,Object object){
		return new  Resultable(false,msg,object);
	}

}
