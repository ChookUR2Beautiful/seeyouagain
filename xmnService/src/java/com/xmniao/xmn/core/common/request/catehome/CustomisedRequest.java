package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: CustomisedRequest    
* @Description:专属定制接口请求类   
* @author: liuzhihao   
* @date: 2016年11月23日 下午4:11:01
 */
public class CustomisedRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message="城市id不能为空")
	private Integer cityId;//城市id
	
	@NotNull(message="坐标经度不能为空")
	private Double lon;//坐标经度
	
	@NotNull(message="坐标纬度不能为空")
	private Double lat;//坐标纬度

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
