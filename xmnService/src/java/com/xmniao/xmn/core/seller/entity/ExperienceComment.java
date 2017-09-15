package com.xmniao.xmn.core.seller.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName:ExperienceComment
 * @Description:美食探店点评表 实体类
 * @Author:xw
 * @Date:2017年5月13日下午2:15:41
 */
public class ExperienceComment {

	private Integer id;				
	private Integer sellerId;		//商家编号
	private String sellerName;		//商家名
	private Integer sellerType;		//商家标识 1 签约商家, 2 非签约商家
	private Integer uid;			//会员编号
	private Integer userType;		//会员标识 1 签约主播  2.非签约主播 3体验官
	private Integer recordType;		//通告类型1.商家2.活动
	private Integer recordId;		//通告id
	private Integer activityId;		//活动场次id
	private Integer isRecommend;	//是否推荐，0不推荐1推荐
	private Date reviewTime;		//审核时间
	private Integer reviewState;	//审核状态 0待审核 1审核通过 2审核拒绝 3停用
	private String refuseRemark;	//拒绝原因（如果审核不通过必须有拒绝原因）
	private String content;			//点评内容
	private Date createTime;		//创建时间
	private Integer upvote;			//点赞数
	
	public Integer getUpvote() {
		return upvote;
	}

	public void setUpvote(Integer upvote) {
		this.upvote = upvote;
	}

	private List<ExperienceCommentMedia> mediaList;		//点评中的图片和视频

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getSellerType() {
		return sellerType;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
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

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Integer getReviewState() {
		return reviewState;
	}

	public void setReviewState(Integer reviewState) {
		this.reviewState = reviewState;
	}

	public String getRefuseRemark() {
		return refuseRemark;
	}

	public void setRefuseRemark(String refuseRemark) {
		this.refuseRemark = refuseRemark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<ExperienceCommentMedia> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<ExperienceCommentMedia> mediaList) {
		this.mediaList = mediaList;
	}

	@Override
	public String toString() {
		return "ExperienceComment [id=" + id + ", sellerId=" + sellerId + ", sellerName=" + sellerName + ", sellerType="
				+ sellerType + ", uid=" + uid + ", userType=" + userType + ", recordType=" + recordType + ", recordId="
				+ recordId + ", activityId=" + activityId + ", isRecommend=" + isRecommend + ", reviewTime="
				+ reviewTime + ", reviewState=" + reviewState + ", refuseRemark=" + refuseRemark + ", content="
				+ content + ", createTime=" + createTime + ", mediaList=" + mediaList + "]";
	}
	
	
}
