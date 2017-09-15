package com.xmniao.xmn.core.coupon.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: CouponCardInfoSubRequest    
* @Description:充值卡详情子列表请求类   
* @author: liuzhihao   
* @date: 2017年3月2日 下午6:44:58
 */
@SuppressWarnings("serial")
public class CouponCardInfoSubRequest extends BaseRequest{
	
	@NotNull(message="我的卡卷是否过期")
	private Integer isOverdue;//是否过期 0 过期 1 未过期
	
	@NotNull(message="店铺ID不能为空")
	private String sellerid;//店铺ID
	
	@NotNull(message="店铺ID不能为空")
	private Integer sellerType;//店铺类型 1.普通商家 2.连锁总店 3 区域代理

	@NotNull(message="坐标经度不能为空")
	private Double lon;
	
	@NotNull(message="坐标纬度不能为空")
	private Double lat;
	
	@NotNull(message="子列表类型不能为空")
	private Integer subType;//子列表类型 1 适合门店 2 用户充值卡消费记录 默认为1
	
	@NotNull(message="分页页码不能为空")
	private Integer page;//分页页码

	public Integer getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(Integer isOverdue) {
		this.isOverdue = isOverdue;
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
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

	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "CouponCardInfoSubRequest [isOverdue=" + isOverdue + ", sellerid=" + sellerid + ", sellerType=" + sellerType
			+ ", lon=" + lon + ", lat=" + lat + ", subType=" + subType + ", page=" + page + "]";
	}
	
}
