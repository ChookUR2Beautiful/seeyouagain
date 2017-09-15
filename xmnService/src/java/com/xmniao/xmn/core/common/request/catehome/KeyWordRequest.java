package com.xmniao.xmn.core.common.request.catehome;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;


public class KeyWordRequest extends BaseRequest{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 关键字
	 */
	@NotNull(message="您好，请输入搜索关键字，谢谢！")
	private String keyWord;
	
	private int page=1;
	
	private int pageSize=20;
	
	//用户定位的区域ID:如 provinceid 省ID or cityid 市ID or areaid 区ID
	private Integer areaId;
	
	private Double latitude;
	
	private Double longitude;
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	
	
}
