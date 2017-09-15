package com.xmniao.xmn.core.sellerPackage.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: RecomboRequest    
* @Description:套餐推荐请求类   
* @author: liuzhihao   
* @date: 2017年2月20日 下午3:54:22
 */
@SuppressWarnings("serial")
public class RecomboRequest extends BaseRequest{

	@NotNull(message="经度不能为空")
	private Double lat;
	
	@NotNull(message="纬度不能为空")
	private Double lon;
	
	@NotNull(message="城市ID不能为空")
	private Integer cityId;
	
	private Integer tradeId;//分类ID

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

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	@Override
	public String toString() {
		return "RecomboRequest [lat=" + lat + ", lon=" + lon + ", cityId=" + cityId + ", tradeId=" + tradeId + "]";
	}
	
}
