package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* 项目名称：xmnService   
* 类名称：IDRequest   
* 类描述： 参数中只包含id,用此通用request  
* 创建人：liuzhihao   
* 创建时间：2016年5月18日 下午3:08:25   
* @version    
*
 */
public class IDRequest extends BaseRequest{
	
	private static final long serialVersionUID = 1L;
	@NotNull(message="ID不能为空")
	private Integer id;
	
	private Double longitude;//经度
	
	private Double latitude;//纬度
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "IDRequest [id=" + id + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}
	
}
