package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

@SuppressWarnings("serial")
public class FeedBackRequest extends BaseRequest{

	@NotNull(message="content不能为空")
	private String content;

	private String phone; 
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "MassageRequest [contents=" + content + "]";
	}
	
	
}
