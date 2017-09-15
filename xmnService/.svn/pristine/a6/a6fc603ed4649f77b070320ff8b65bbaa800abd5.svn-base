/**
 * 2016年8月15日 下午2:42:34
 */
package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * 直播精选专题
 */
public class LiveTopicRequest extends BaseRequest {

	private static final long serialVersionUID = -1899791855263110885L;

	private String sessiontoken; //会话令牌
	private Double longitude;  //用户经度
	private Double latitude;  //用户纬度
	// 4.20版本只取1条数据
	private Integer page = 1;//页数
	private Integer pageSize = 10;

	@Override
	public String getSessiontoken() {
		return sessiontoken;
	}

	@Override
	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
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

	@Override
	public String toString() {
		return "LiveTopicRequest{" +
				"sessiontoken='" + sessiontoken + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", page=" + page +
				", pageSize=" + pageSize +
				'}';
	}
}
