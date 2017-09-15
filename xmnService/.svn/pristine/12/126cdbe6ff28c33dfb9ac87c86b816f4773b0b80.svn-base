package com.xmniao.xmn.core.common.request.seller;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmniao.xmn.core.base.BaseRequest;



public class SellerNearListRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@Min(value=1,message="页码最小为1")
	private int page=1;
	
	/**
	 * 每页显示数量
	 */
	private int pageSize=10;
	/**
	 * 经度
	 */
	@NotNull(message="用户经纬度不能为空")
	private Double longitude;
	/**
	 * 附近多少千米
	 */
	private int range = 1;
	/**
	 * 纬度
	 */
	@NotNull(message="用户经纬度不能为空")
	private Double latitude;
	
	/**
	 * 商圈编号
	 */
	private Integer zoneid;
	/**
	 * 一级类别编号
	 */
	private Integer category;
	/**
	 * 二级类别编号
	 */
	private Integer genre;
	
	/**
	 * 0不返回直播信息 1返回直播信息
	 */
	private int type = 0;
	
	private String cityId; //城市Id
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getZoneid() {
		return zoneid;
	}
	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
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
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
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
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "SellerNearListRequest [page=" + page + ", pageSize=" + pageSize
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
	
			
	
}
