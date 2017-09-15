package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class TopicalRankRequest extends BaseRequest{
	private static final long serialVersionUID = 1L;

	@NotNull(message="页码不能为空")
	private Integer page;//页数

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	



}
