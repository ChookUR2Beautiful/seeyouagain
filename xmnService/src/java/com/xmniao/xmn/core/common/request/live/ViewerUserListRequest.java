/**
 * 2016年8月15日 下午2:42:34
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：ViewerUserListRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 下午2:42:34
 * @version
 */
public class ViewerUserListRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6728158442203569694L;

	@NotNull(message="主播ID不能为空")
	private Long anchorId;//用户id
	
	@NotNull(message="直播记录ID不能为空")
	private Long liveRecordId;
	
	@NotNull(message="分页页码不能为空")
	private Integer page;//页数
	
	public Long getAnchorId() {
		return anchorId;
	}
	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}
	public Long getLiveRecordId() {
		return liveRecordId;
	}
	public void setLiveRecordId(Long liveRecordId) {
		this.liveRecordId = liveRecordId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "ViewerUserListRequest [anchorId=" + anchorId
				+ ", liveRecordId=" + liveRecordId + ", page=" + page + "]";
	}

}
