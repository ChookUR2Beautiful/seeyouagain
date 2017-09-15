package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: SwitchPositionRequest    
* @Description:切换位置请求参数  
* @author: liuzhihao   
* @date: 2016年11月23日 下午12:25:18
 */
public class SearchLocationRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="商圈名称不能为空")
	private String zoneName;//商圈名称
	
	@NotNull(message="城市id不能为空")
	private Integer cityId;//城市id 
	
	@NotNull(message="坐标的纬度不能为空")
	private Double lat;
	
	@NotNull(message="坐标的经度不能为空")
	private Double lon;

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

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
