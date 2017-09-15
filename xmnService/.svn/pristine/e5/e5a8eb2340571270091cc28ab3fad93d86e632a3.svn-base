package com.xmniao.xmn.core.common.request.sellerPackage;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;


/**
 * 购买套餐请求
 * */
public class LiveRoomPackageRecommendRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959037552617374030L;
	
	
	@NotNull(message="商家ID不能为空")
	private Integer selelrId = 0;
	
	//表示直播间里 0弹出的小窗口 1 弹出的列表
	@NotNull(message = "显示位置不能为空")
	private Integer position = 0;

	private Integer liveRecordId;  //直播记录Id
	
	private Integer page = 1;

	public Integer getSelelrId() {
		return selelrId;
	}

	public void setSelelrId(Integer selelrId) {
		this.selelrId = selelrId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(Integer liveRecordId) {
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
		return "LiveRoomPackageRecommendRequest{" +
				"selelrId=" + selelrId +
				", position=" + position +
				", liveRecordId=" + liveRecordId +
				", page=" + page +
				'}';
	}
}
