package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：NearbyStageRequest   
* 类描述：  附近驿站参数
* 创建人：xiaoxiong   
* 创建时间：2016年6月27日 上午9:47:47   
* @version    
*
 */
public class NearbyStageRequest extends BaseRequest{
	
	@NotNull(message="经度不能为空")
	private String longitude;
	@NotNull(message="纬度不能为空")
	private String latitude;
	
	
	private Long range=0l;	//设置附近多少米
	
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Long getRange() {
		return range;
	}
	public void setRange(Long range) {
		this.range = range;
	}
	
	
	

}
