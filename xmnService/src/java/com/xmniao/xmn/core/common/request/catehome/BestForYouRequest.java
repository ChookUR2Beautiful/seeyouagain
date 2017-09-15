package com.xmniao.xmn.core.common.request.catehome;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

@SuppressWarnings("serial")
public class BestForYouRequest extends BaseRequest{
	
	@NotNull(message="纬度不能为空")
	private Double lon;//坐标--纬度
	
	@NotNull(message="经度不能为空")
	private Double lat;//坐标--经度

	@NotNull(message="城市ID不能为空")
	private Integer cityid;//城市ID
	
	
	private Integer zoneid;//商圈id
	
	private String sellerlabel;//店铺标签
	
	private  Integer pageSize=5;
	
	private Integer page=1;
	
	private Integer sellerTopicId;//店铺标签
	
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

	public String getSellerlabel() {
		return sellerlabel;
	}

	public void setSellerlabel(String sellerlabel) {
		this.sellerlabel = sellerlabel;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSellerTopicId() {
		return sellerTopicId;
	}

	public void setSellerTopicId(Integer sellerTopicId) {
		this.sellerTopicId = sellerTopicId;
	}		
	
}
