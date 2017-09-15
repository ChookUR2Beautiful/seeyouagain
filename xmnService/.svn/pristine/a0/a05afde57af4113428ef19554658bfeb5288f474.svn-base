package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class ViewWechatRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
    
	@NotNull(message="被查看对象不能为空")
	private Integer solduid;
	
	@NotNull(message="支付积分不能为空")
	private Double integral;

	public Integer getSolduid() {
		return solduid;
	}

	public void setSolduid(Integer solduid) {
		this.solduid = solduid;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ViewWechatRequest [solduid=" + solduid
				+ ",integral="+integral+"]";
	}
	

}
