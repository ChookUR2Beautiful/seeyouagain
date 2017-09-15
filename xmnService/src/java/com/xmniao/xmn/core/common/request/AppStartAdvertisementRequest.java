package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

@SuppressWarnings("serial")
public class AppStartAdvertisementRequest extends BaseRequest{
	
	@NotNull(message="城市编码不能为空")
	private Integer cityId = 0;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "AppStartAdvertisementRequest [cityId=" + cityId + "]";
	}
	
}
