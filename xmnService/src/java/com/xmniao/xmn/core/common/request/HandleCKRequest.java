package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class HandleCKRequest extends BaseRequest{
	
	@NotNull(message="申请ID不能为空")
	private Integer id;
	

	@NotNull(message="发送者ID不能为空")
	private Integer sendid;
	@NotNull(message="结果不能为空")
	private Integer result;
	
	private Integer uid;
	
	

	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getSendid() {
		return sendid;
	}
	public void setSendid(Integer sendid) {
		this.sendid = sendid;
	}
	
	
	
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
