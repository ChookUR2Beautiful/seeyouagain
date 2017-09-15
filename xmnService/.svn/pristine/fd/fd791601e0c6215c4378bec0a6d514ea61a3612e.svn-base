package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
/**
 * 
* @projectName: xmnService 
* @ClassName: UserDebitCardSellerRquest    
* @Description:与用户有关的商铺请求类  
* @author: wdh   
* 
 */
public class UserDebitCardSellerRquest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361706286577859183L;
	/**
	 * 经度
	 */
	@NotNull(message="用户经纬度不能为空")
	private Double longitude;
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
	 * 分类Id
	 */
	private Integer tradeid;
	/**
	 * 城市Id
	 */
	private Integer cityId;

	@Min(1)
	private Integer page =1;
	
	private Integer pageSize=20;
	
	private Integer uid;

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
	public Integer getZoneid() {
		return zoneid;
	}
	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}
	public Integer getTradeid() {
		return tradeid;
	}
	public void setTradeid(Integer tradeid) {
		this.tradeid = tradeid;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
