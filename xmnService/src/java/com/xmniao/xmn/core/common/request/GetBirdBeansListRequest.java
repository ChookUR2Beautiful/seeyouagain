package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.Constant;

public class GetBirdBeansListRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Min(value=1,message="页数不能小于1")
	private Integer page = 1;//页数
	
	@Min(value=1,message="每页显示数量不能小于1")
	private Integer pageSize = Constant.PAGE_LIMIT;
	
	private String sdate;
	
	private String edate ;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	
}
