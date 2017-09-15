package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;


/**
 * 直播间广告
 */
public class LiveRoomAdRequest extends BaseRequest{

	private static final long serialVersionUID = 6187582344411607843L;

	private Integer cityId;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "LiveRoomAdRequest{" +
				"cityId=" + cityId +
				'}';
	}
}
