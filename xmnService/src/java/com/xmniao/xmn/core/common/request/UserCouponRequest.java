package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: UserCouponRequest    
* @Description:用户优惠卷请求类   
* @author: liuzhihao   
* @date: 2016年11月26日 下午2:30:11
 */
@SuppressWarnings("serial")
public class UserCouponRequest extends BaseRequest{
	
	@NotNull(message="优惠卷类型不能为空")
	private Integer type;//优惠卷类型  0 商铺礼券 1 平台礼券  2粉丝卷
	
	@NotNull(message="页码不能为空")
	private Integer page;//页码
	
	private Double longitude;//用户经度
	
	private Double latitude;//用户纬度
	
	private Integer pageSize=8;//条数，默认8条
	
	@NotNull(message="优惠卷使用状态不能为空")
	private Integer status;//优惠卷使用状态 1 可以使用 0 不可以使用(包含过期和已使用的)

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "UserCouponRequest [type=" + type + ", page=" + page
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", pageSize=" + pageSize + ", status=" + status + "]";
	}
	
}
