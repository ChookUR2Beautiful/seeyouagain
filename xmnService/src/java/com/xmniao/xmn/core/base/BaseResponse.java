package com.xmniao.xmn.core.base;

import java.io.Serializable;

public class BaseResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int state; //返回状态
	
	private String info; //返回信息
	
	public BaseResponse(){}
	
	public BaseResponse(int state, String info) {
		super();
		this.state = state;
		this.info = info;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
