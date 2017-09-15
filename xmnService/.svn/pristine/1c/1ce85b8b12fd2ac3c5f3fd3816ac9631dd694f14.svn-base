package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: BusinessRequest    
* @Description:商圈商铺请求类   
* @author: liuzhihao   
* @date: 2016年11月16日 下午9:01:30
 */
public class BusinessRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;

	@NotNull(message="area不能为空")
	private Integer area;//区域id 如 省id，市id，商圈id
	
	@NotNull(message="纬度不能为空")
	private Double lat;//纬度
	
	@NotNull(message="经度不能为空")
	private Double lon;//经度
	
	private Integer pageNo;//页数
	
	private Integer pageSize;//条数

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
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
