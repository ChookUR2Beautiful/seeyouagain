/**
 * 2016年10月21日 上午10:14:26
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService
 * @类名称：LiveTrackRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月21日 上午10:14:26
 * @version
 */
public class LiveTrackRequest extends BaseRequest {

	/**
	 *long
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="页码不能为空")
	private Integer page;
	@NotNull(message="主播ID不能为空")
	private Integer anchorId;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getAnchorId() {
		return anchorId;
	}
	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}
	@Override
	public String toString() {
		return "LiveTrackRequest [page=" + page + ", anchorId=" + anchorId
				+ "]";
	}

	
}
