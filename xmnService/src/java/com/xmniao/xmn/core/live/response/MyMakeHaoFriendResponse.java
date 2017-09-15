package com.xmniao.xmn.core.live.response;

import java.util.List;

public class MyMakeHaoFriendResponse {
	
	private int limitSize=0;
	
	private int source=0;
	
	private int page;
	
	private String uids;
	
	private int isDate = 0;
	
	List<MyMakeHaoFriendBean> list;
	
	
	
	
	public int getIsDate() {
		return isDate;
	}

	public void setIsDate(int isDate) {
		this.isDate = isDate;
	}

	public List<MyMakeHaoFriendBean> getList() {
		return list;
	}

	public void setList(List<MyMakeHaoFriendBean> list) {
		this.list = list;
	}

	public int getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

	


	
}
