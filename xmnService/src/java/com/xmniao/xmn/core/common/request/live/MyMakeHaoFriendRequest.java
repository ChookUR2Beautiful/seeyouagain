package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.Min;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.Constant;

public class MyMakeHaoFriendRequest extends BaseRequest{

	
	private static final long serialVersionUID = -5974523179294886554L;
	
	@Min(value=1,message="当前页数不能小于1")
	private int page = 1;
	
	@Min(value=1,message="每页显示数量不能小于1")
	private int pageSize = Constant.PAGE_LIMIT;
	
	private int source = 0;
	
	/**
	 * 有贡献uid
	 */
	private String uids ;
	
	/**
	 * source=2时候查询liver 排除前面多少条
	 */
	private int limitSize = 0;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

	public int getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}
	
	

}
