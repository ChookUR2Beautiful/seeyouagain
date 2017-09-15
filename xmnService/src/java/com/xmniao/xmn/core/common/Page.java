package com.xmniao.xmn.core.common;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: Page    
* @Description:分页查询条件请求类   
* @author: liuzhihao   
* @date: 2016年11月29日 下午7:25:02
 */
@SuppressWarnings("serial")
public class Page extends BaseRequest{

	private Integer dateType=0;//时间类型,默认全部
	
	private Integer	relevantType;//相关类型
	
	private Integer page;
	
	private Integer pageSize=0;
	
	private Double lon;//用户定位坐标-经度
	
	private Double lat;//用户定位坐标-纬度
	
	private Integer city;
	
	private Integer provice;
	
	private Integer area;
	
	private Integer zoneid;
	
	private String startTime;
	
	private String endTime;
	
	private Integer category;
	
	private Integer genre;
	
	private Integer is_pay;
	
	private Integer status;

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

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getProvice() {
		return provice;
	}

	public void setProvice(Integer provice) {
		this.provice = provice;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
	}

	public Integer getIs_pay() {
		return is_pay;
	}

	public void setIs_pay(Integer is_pay) {
		this.is_pay = is_pay;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
