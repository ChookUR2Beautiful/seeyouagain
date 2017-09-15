package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: RecommendRequest    
* @Description:推荐店铺请求参数类   
* @author: liuzhihao   
* @date: 2016年11月16日 上午10:09:27
 */
public class XmnHomeRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="纬度不能为空")
	private Double lon;//纬度
	
	@NotNull(message="经度不能为空")
	private Double lat;//经度
	
	private Integer cityid;//城市id
	
	private Integer zoneid;//商圈id

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

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}
	
}
