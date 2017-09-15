package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import io.netty.handler.codec.MessageToByteEncoder;
import net.sf.oval.constraint.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class LiveWonderfulUpdateRequest extends BaseRequest{


	private static final long serialVersionUID = -8935635786130293803L;

	@NotNull(message = "精彩时刻视频Id不能为空")
	private Integer videoId;  //
	@NotNull(message = "视频验证token不能为空")
	private String videoToken;

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getVideoToken() {
		return videoToken;
	}

	public void setVideoToken(String videoToken) {
		this.videoToken = videoToken;
	}

	@Override
	public String toString() {
		return "LiveWonderfulUpdateRequest{" +
				"videoId=" + videoId +
				", videoToken='" + videoToken + '\'' +
				'}';
	}
}
