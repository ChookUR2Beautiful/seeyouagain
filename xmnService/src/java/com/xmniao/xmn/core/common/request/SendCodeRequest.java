package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class SendCodeRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="手机号码不能为空")
	private String phone;//手机号码
	
	private Integer sendType;//发送类型
	
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	@Override
	public String toString() {
		return "SendMsgRequest [phone=" + phone + ", sendType=" + sendType
				+ "]";
	}

}
