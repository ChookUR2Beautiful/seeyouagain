package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class AnchorManagerInfoRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3049925812020069836L;
	
	@NotNull(message="主播ID不能为空")
	public Integer anchorId;
	
	@NotNull(message="页码不能为空")
	public Integer page;
	

	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "AnchorManagerInfoRequest [anchorId=" + anchorId + ", page="
				+ page + "]";
	}

}
