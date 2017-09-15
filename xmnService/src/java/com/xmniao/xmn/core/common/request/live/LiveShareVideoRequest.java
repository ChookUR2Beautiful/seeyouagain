/**
 * 2016年8月15日 下午3:35:45
 */
package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 *
 */
public class LiveShareVideoRequest extends BaseRequest {

	private static final long serialVersionUID = -4523246544787212553L;

	@NotNull(message = "精彩时刻回放视频ID不能为空")
	private Integer id;
	private Double longitude = 0D;
	private Double latitude = 0D;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		return "LiveShareVideoRequest{" +
				"id=" + id +
				", longitude=" + longitude +
				", latitude=" + latitude +
				'}';
	}
}
