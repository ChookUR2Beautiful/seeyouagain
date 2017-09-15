package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class SearchCommentSellerRequest extends BaseRequest{
	
	private static final long serialVersionUID = -7177385995943999050L;

	@NotNull(message="商圈编号不能为空")
	private Integer zoneid;
	
	private String keyword;

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchCommentSellerRequest [zoneid=" + zoneid + ", keyword=" + keyword + ",Base:" + super.toString() + "]";
	}
	
	
}
