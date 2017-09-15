package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;


/**
 * 购买粉丝抵用券请求参数
 * */
public class LiveRecordFansCouponRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959037552617374030L;
	
	
	//页码
	@NotNull(message="页码不能为空")
	private Integer page;
	
	private Integer uid ;
	
	@NotNull(message = "主播会员ID不能为空")
	private Integer anchorUid ;
	
	//表示是直播间里弹出的小窗口 还是 弹出的列表
	@NotNull(message = "获取位置不能为空")
	private Integer position ;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getAnchorUid() {
		return anchorUid;
	}

	public void setAnchorUid(Integer anchorUid) {
		this.anchorUid = anchorUid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "LiveRecordFansCouponRequest [page=" + page + ", uid=" + uid
				+ ", anchorUid=" + anchorUid + ", position=" + position + "]";
	}

	
}
