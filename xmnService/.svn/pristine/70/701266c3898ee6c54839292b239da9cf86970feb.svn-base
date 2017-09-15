package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: UserSellerRequest    
* @Description:与用户有关的商铺请求类  
* @author: liuzhihao   
* @date: 2016年11月23日 下午4:59:37
 */
public class UserSellerRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="坐标经度不能为空")
	private Double lon;//坐标经度
	
	@NotNull(message="坐标纬度不能为空")
	private Double lat;//坐标纬度
	
	@NotNull(message="关于我的分类不能为空")
	private Integer type;//关于我的分类 1 消费 2 收藏 3 浏览 4口味相似
	
	@NotNull(message="类型不能为空")
	private Integer status;//类型 0 商铺 1 积分
	
	@NotNull(message="页码不能为空")
	private Integer page;//页码
	
	private Integer zoneid;//商圈ID
	
	private Integer tradeid;// 分类ID
	
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
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	
}
