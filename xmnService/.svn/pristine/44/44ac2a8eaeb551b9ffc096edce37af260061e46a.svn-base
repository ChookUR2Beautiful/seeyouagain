/**
 * 2016年8月15日 下午3:35:45
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveShareRequest   
* 类描述：   直播分享请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月30日 下午2:40:10   
* @version    
*
 */
public class LiveShareRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8889501946862877314L;

	//直播记录id
	@NotNull(message="直播记录id不能为空")
	private Integer liveRecordId;
	
	private Double longitude = 0D;
	
	private Double latitude = 0D;
	
	public Integer getLiveRecordId() {
		return liveRecordId;
	}
	
	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
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
		return "LiveShareRequest [liveRecordId=" + liveRecordId
				+ ", longitude=" + longitude + ", latitude=" + latitude + ","+super.toString()+"]";
	}

}	
