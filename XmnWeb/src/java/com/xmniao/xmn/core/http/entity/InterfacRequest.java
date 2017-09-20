package com.xmniao.xmn.core.http.entity;

import java.io.Serializable;

public abstract class InterfacRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2469851506997511949L;
	protected String page = "1";// 当前页数
	protected String pageSize = "10";// 每页显示条数
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
