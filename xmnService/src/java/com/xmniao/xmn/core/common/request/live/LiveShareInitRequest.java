/**
 * 2016年10月11日 上午10:16:41
 */
package com.xmniao.xmn.core.common.request.live;
import net.sf.oval.constraint.NotNull;

/**
 * @项目名称：xmnService
 * @类名称：LiveShareInitRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月11日 上午10:16:41
 * @version
 */
public class LiveShareInitRequest {
	@NotNull(message="直播记录ID不能为空")
	private Long zhiboRecordId;
	private Double longitude = 0D;
	private Double latitude = 0D;
	private Integer zhiboType;
	
	public Integer getZhiboType() {
		return zhiboType;
	}
	public void setZhiboType(Integer zhiboType) {
		this.zhiboType = zhiboType;
	}
	public Long getZhiboRecordId() {
		return zhiboRecordId;
	}
	public void setZhiboRecordId(Long zhiboRecordId) {
		this.zhiboRecordId = zhiboRecordId;
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
		return "LiveShareInitRequest [zhiboRecordId=" + zhiboRecordId
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", zhiboType=" + zhiboType + "]";
	}
	
	
	
	
}
