package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

@SuppressWarnings("serial")
public class AppUpdateRequest extends BaseRequest{
	
	@NotNull(message="检查类型不能为空")
	private Integer type;//检查类型:1 app启动检查更新2 设置点击更新

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AppUpdateRequest [type=" + type + "]";
	}
	
	
	
}
