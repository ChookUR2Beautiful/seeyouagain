package com.xmniao.xmn.core.catehome.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: CateHomeActivityRequest    
* @Description:首页活动请求类   
* @author: liuzhihao   
* @date: 2017年2月21日 下午8:54:49
 */
@SuppressWarnings("serial")
public class CateHomeActivityRequest extends BaseRequest{

	@NotNull(message="坐标经度不能为空")
	private Double lat;
	
	@NotNull(message="坐标纬度不能为空")
	private Double lon;
	
	@NotNull(message="城市ID不能为空")
	private Integer cityId;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "CateHomeActivityRequest [lat=" + lat + ", lon=" + lon + ", cityId=" + cityId + "]";
	}
}
