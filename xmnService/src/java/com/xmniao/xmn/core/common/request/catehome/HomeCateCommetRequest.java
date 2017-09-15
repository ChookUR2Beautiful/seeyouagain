package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

@SuppressWarnings("serial")
public class HomeCateCommetRequest extends BaseRequest{
	

	@NotNull(message="城市ID不能为空")
	private Integer cityid;//城市ID	
	
	private  Integer pageSize=3;
	
	private Integer page=1;

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

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
	
}
