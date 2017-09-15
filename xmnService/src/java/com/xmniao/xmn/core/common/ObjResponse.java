package com.xmniao.xmn.core.common;

import com.xmniao.xmn.core.base.BaseResponse;

public class ObjResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param state
	 * @param info
	 */
	public ObjResponse(int state, String info) {
		super(state, info);
	}
	
	/**
	 * 返回对象
	 */
	private Object response;


	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	
	
	
}
