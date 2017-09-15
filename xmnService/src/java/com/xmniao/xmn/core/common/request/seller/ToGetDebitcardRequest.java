package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class ToGetDebitcardRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405572830837850172L;
	
	
	@NotNull(message="直播记录id不能为空")
	private Integer recordid;
	
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	@Override
	public String toString() {
		return "ToGetDebitcardRequest [recordid=" + recordid + "," + super.toString() +"]";
	}
	
	
}
