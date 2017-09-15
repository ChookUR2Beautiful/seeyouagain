package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 *
 */
public class LiverInfoV2Request extends BaseRequest{

	private static final long serialVersionUID = -6995013016209543775L;

	private Integer uid;  // 用户uid
	private Integer anchorId;  // 主播id

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	@Override
	public String toString() {
		return "LiverInfoV2Request{" +
				"uid=" + uid +
				", anchorId=" + anchorId +
				'}';
	}
}
