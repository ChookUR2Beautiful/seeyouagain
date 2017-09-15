package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 发布点评请求参数实体
 * @ClassName:CommentRequest
 * @Description:添加点评请求参数
 * @Author:xw
 * @Date:2017年5月15日下午3:16:20
 */
public class CommentRequest extends BaseRequest{

	private static final long serialVersionUID = -6688398479827488956L;
	
	@NotNull(message="入口类型不能为空")
	private Integer type;	//点评入口类型,1主播，2美食体验官
	
	@NotNull(message="点评内容不能为空")
	private String content;	//点评内容
	
	private Integer sellerId;	//商家id
	
	private String sellerName;	//商家名称
	
	private Integer recordId;	//直播记录id
		
	private Integer activityId;	//体验官活动场次id
	
	private Integer sellerType;	//商家标识 1 签约商家, 2 非签约商家
	
	private String picUrls;		//图片url，多张图片用  ‘，’隔开
	
	private String videoUrl;	//视频url

	private Integer recordType;	//直播通告类型 1关联商家,2关联活动
	
	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Override
	public String toString() {
		return "CommentRequest [type=" + type + ",sellerType=" + sellerType + ", content=" + content + ", sellerId=" + sellerId + ", sellerName="
				+ sellerName + ", recordId=" + recordId + ", activityId=" + activityId + ", sellerType=" + sellerType
				+ ", picUrls=" + picUrls + ", videoUrl=" + videoUrl +",Base:" +super.toString()+"]";
	}

	

}
