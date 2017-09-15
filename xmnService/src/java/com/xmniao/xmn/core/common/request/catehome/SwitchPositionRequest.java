package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: SwitchPositionRequest    
* @Description:切换定位接口请求类   
* @author: liuzhihao   
* @date: 2016年11月25日 上午10:15:26
 */
public class SwitchPositionRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;

	@NotNull(message="坐标-经度不能为空")
	private Double lat;
	
	@NotNull(message="坐标-纬度不能为空")
	private Double lon;
	
	@NotNull(message="城市id 不能为空")
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
	
	
	
}
