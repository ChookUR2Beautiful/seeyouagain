package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: XmnHomeRequest    
* @Description: 寻觅鸟首页请求参数   
* @author: liuzhihao   
* @date: 2016年11月9日 下午3:10:15
 */
public class LocationRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double lon = 113.344910002560D;//经度,默认商圈经度(天河去跑马场)

	private Double lat = 23.122681317744D;//纬度。默认商圈纬度(天河区跑马场)

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

	@Override
	public String toString() {
		return "XmnHomeRequest [lon=" + lon + ", lat=" + lat + "]";
	}
	
}
