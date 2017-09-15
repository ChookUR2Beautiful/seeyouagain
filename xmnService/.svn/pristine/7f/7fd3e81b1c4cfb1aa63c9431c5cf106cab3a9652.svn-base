package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: XmnHomeRequest    
* @Description: 寻蜜鸟首页请求参数   
* @author: liuzhihao   
* @date: 2016年11月10日 下午3:21:35
 */
public class XmnHomeRequest extends BaseRequest{
	
	private static final long serialVersionUID = 1L;

	@NotNull(message="商圈id不能为空")
	private Integer bid;//商圈id
	
	@NotNull(message="城市id不能为空")
	private Integer cityId;//城市id
	
	@NotNull(message="经度不能为空")
	private Double lon;//经度
	
	@NotNull(message="纬度不能为空")
	private Double lat;//纬度
	
	/** 设置默认值，可为空*/
	private Integer pageNo = 0;//页码
	
	private Integer pageSize = 8;//条数

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

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

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
