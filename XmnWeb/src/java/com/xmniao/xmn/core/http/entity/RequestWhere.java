package com.xmniao.xmn.core.http.entity;

import java.io.Serializable;

public class RequestWhere implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2106972009933799260L;
	private String uid;// 用户ID
	
	
	public RequestWhere(){
		this(null);
	}
	
	public RequestWhere(String uid){
		this.uid=uid;
	}
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

}
