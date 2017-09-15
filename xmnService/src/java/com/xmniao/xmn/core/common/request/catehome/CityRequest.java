package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

@SuppressWarnings("serial")
public class CityRequest extends BaseRequest{
	
	@NotNull(message="纬度不能为空")
	private Double lon;//坐标--纬度
	
	@NotNull(message="经度不能为空")
	private Double lat;//坐标--经度

	@NotNull(message="城市ID不能为空")
	private Integer cityid;//城市ID

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	
}
