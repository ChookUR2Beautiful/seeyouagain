package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_zb   
* 类描述：  直播房间请求类
* 创建人：yhl   
* 创建时间：2016年8月12日 上午11:14:21   
 */
public class LiverRecommendRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853702828497857692L;

//	@NotNull(message="直播用户ID不能为空")
	private Integer uid;
	
	//经度
	private Double longitude = 0D;
	
	//纬度
	private Double latitude = 0D;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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
		return "LiverRecommendRequest [uid=" + uid + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}
	
	

}
