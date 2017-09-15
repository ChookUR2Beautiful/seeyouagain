package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

@SuppressWarnings("serial")
public class HomeMustBuyRequest extends BaseRequest{
	

	private Integer cityid;//城市ID	
	
	private Integer type;//分类(1:精选  2:潮玩  3:送礼   4:海淘),
	
	private  Integer pageSize=5;
	@Min(1)
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
