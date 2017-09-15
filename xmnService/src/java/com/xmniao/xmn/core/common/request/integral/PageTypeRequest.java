package com.xmniao.xmn.core.common.request.integral;

import net.sf.oval.constraint.Min;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.Constant;

public class PageTypeRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Min(value=1,message="每页显示数量不能小于1")
	private Integer pageSize = Constant.PAGE_LIMIT;
	
	@Min(value=1,message="页数不能小于1")
	private Integer page = 1;
	
	private Integer type = 0;

	private Integer uid;
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "PageTypeRequest [pageSize=" + pageSize + ", page=" + page
				+ ", type=" + type + ", uid=" + uid + "]";
	}


	
}
