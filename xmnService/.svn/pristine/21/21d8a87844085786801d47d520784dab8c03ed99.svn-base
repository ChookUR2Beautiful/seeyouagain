/**
 * 2016年10月18日 下午2:18:24
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService
 * @类名称：LiveRecordVedioUrl
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月18日 下午2:18:24
 * @version
 */
public class LiveRecordVedioUrlRequest extends BaseRequest {
	/**
	 *long
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="直播记录ID不能为空")
	private Integer liveRecordId;
	@NotNull(message="直播记录视频流地址不能为空")
	private String vedioUrl;
	@NotNull(message="直播频道ID不能为空")
	private String channelId;
	public Integer getLiveRecordId() {
		return liveRecordId;
	}
	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}
	public String getVedioUrl() {
		return vedioUrl;
	}
	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	@Override
	public String toString() {
		return "LiveRecordVedioUrlRequest [liveRecordId=" + liveRecordId
				+ ", vedioUrl=" + vedioUrl + ", channelId=" + channelId + "]";
	}
	
	
}
