package com.xmniao.xmn.core.common;

import com.xmniao.xmn.core.base.BaseResponse;

@SuppressWarnings("serial")
public class TResponse<T> extends BaseResponse{

	public TResponse(int state, String info) {
		super(state, info);
	}
	
	private Integer page;//分页
	
	private T response;

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "TResponse [page=" + page + ", response=" + response + "]";
	}
	
	
}
