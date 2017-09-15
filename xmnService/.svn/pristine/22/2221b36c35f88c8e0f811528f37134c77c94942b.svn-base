package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 直播 观众赠送礼物请求参数
 * @author yhl
 * 2016年8月10日17:52:11
 * */
public class GiftsInfoRequest extends BaseRequest{

	private static final long serialVersionUID = -2563095152996950766L;
	
	@NotNull(message="用户ID不能为空")
	public String uid;
	
	@NotNull(message="主播ID不能为空")
	public String anchorId;
	
	@NotNull(message="房间编号不能为空")
	public String roomNo;
	
	@NotNull(message="礼物ID不能为空")
	public String id;
	
	@NotNull(message="直播记录ID不能为空")
	public String liveRecordId;
	
	@NotNull(message="礼物是否免费标示不能为空")
	public Integer isFree = 1; //默认不免费
	
	@NotNull(message="礼物类型不能为空")
	public Integer giftType = 0; 
	
//	发礼物的来源:套餐或商品礼物时必填,
//	(填写从普通礼物列表点击进入套餐/商品时的ID)
	public Integer sourceId ;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(String anchorId) {
		this.anchorId = anchorId;
	}


	public String getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(String liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public Integer getGiftType() {
		return giftType;
	}

	public void setGiftType(Integer giftType) {
		this.giftType = giftType;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	
	@Override
	public String toString() {
		return "GiftsInfoRequest [uid=" + uid + ", anchorId=" + anchorId
				+ ", roomNo=" + roomNo + ", id=" + id + ", liveRecordId="
				+ liveRecordId + ", isFree=" + isFree + ", giftType="
				+ giftType + ", sourceId=" + sourceId + "]";
	}
	
}
