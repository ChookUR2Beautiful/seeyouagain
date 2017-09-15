package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: LiveTrailerRequest    
* @Description:预告直播列表请求类   
* @author: liuzhihao   
* @date: 2016年11月29日 下午12:08:31
 */
@SuppressWarnings("serial")
public class LiveTrailerRequest extends BaseRequest{

	private Integer dateType=0;//时间类型,默认全部
	
	private Integer	relevantType;//相关类型
	
	private Double lon;//用户定位坐标-经度
	
	private Double lat;//用户定位坐标-纬度

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

	public Integer getRelevantType() {
		return relevantType;
	}

	public void setRelevantType(Integer relevantType) {
		this.relevantType = relevantType;
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
