package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class AnchorManagerAddRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3049925812020069836L;
	
	@NotNull(message="主播ID不能为空")
	public Integer anchorId;
	
	@NotNull(message="观众ID不能为空")
	public String liverId;
	

	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	public String getLiverId() {
		return liverId;
	}

	public void setLiverId(String liverId) {
		this.liverId = liverId;
	}

	@Override
	public String toString() {
		return "AnchorManagerAddRequest [anchorId=" + anchorId + ", liverId="
				+ liverId + "]";
	}
}
