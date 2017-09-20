package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.RelativeDateFormat;

public class ExperienceComment extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 商家编号
     */
    private Integer sellerid;

    /**
     * 商家名
     */
    private String sellername;

    /**
     * 商家标识 1 签约商家, 2 非签约商家
     */
    private Integer sellerType;

    /**
     * 会员编号
     */
    private Integer uid;

    /**
     * 会员标识 1 签约主播  2.非签约主播 3体验官
     */
    private Integer userType;

    /**
     * 通告类型1.商家2.活动
     */
    private Byte recordType;

    /**
     * 通告id
     */
    private Integer recordId;

    /**
     * 活动场次id
     */
    private Integer activityId;

    /**
     * 是否推荐，0不推荐1推荐
     */
    private Byte isRecommend;

    /**
     * 审核时间
     */
    private Date reviewTime;

    /**
     * 审核状态 0待审核 1审核通过 2审核拒绝 3停用
     */
    private Integer reviewState;

    /**
     * 拒绝原因（如果审核不通过必须有拒绝原因）
     */
    private String refuseRemark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 点评内容
     */
    private String content;
    
    private String reviewStateStr;
    
    private String isRecommendStr;
    
    private String liverName;
    
    private String liverPhone;
    
    private List<Integer> uids;
    
    private String createTimeStr;
    
    private List<CommentMedia> commentMedias;
    
    private String avatar;
    
    private String zoneName;
    
    private String address;
    
    private String phone;
    
    private String createTimeRelativeFormat;
    
    public String getCreateTimeRelativeFormat() {
    	if(createTime==null){
    		return "";
    	}else{
    		return RelativeDateFormat.format(createTime);
    	}
	}

	public void setCreateTimeRelativeFormat(String createTimeRelativeFormat) {
		this.createTimeRelativeFormat = createTimeRelativeFormat;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<CommentMedia> getCommentMedias() {
		return commentMedias;
	}

	public void setCommentMedias(List<CommentMedia> commentMedias) {
		this.commentMedias = commentMedias;
	}

	public String getCreateTimeStr() {
    	if(createTime==null){
    		return createTimeStr;
    	}
		return DateUtil.formatDate(createTime, DateUtil.Y_M_D_HMS);
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public List<Integer> getUids() {
		return uids;
	}

	public String getLiverPhone() {
		return liverPhone;
	}

	public void setLiverPhone(String liverPhone) {
		this.liverPhone = liverPhone;
	}

	public String getReviewStateStr() {
    	if(reviewState==null){
    		return reviewStateStr;
    	}
		switch (reviewState) {
		case 0:
			return "待审核";
		case 1:
			return "审核通过";
		case 2:
			return "审核拒绝";
		case 3:
			return "停用";
		default:
			break;
		}
		return reviewStateStr;
	}

	public void setReviewStateStr(String reviewStateStr) {
		this.reviewStateStr = reviewStateStr;
	}

	public String getIsRecommendStr() {
		if(isRecommend==null){
			return isRecommendStr;
		}
		switch (isRecommend) {
		case 0:
			return "不推荐";
		case 1:
			return "推荐";
		default:
			break;
		}
		return isRecommendStr;
	}

	public void setIsRecommendStr(String isRecommendStr) {
		this.isRecommendStr = isRecommendStr;
	}

	public String getLiverName() {
		return liverName;
	}

	public void setLiverName(String liverName) {
		this.liverName = liverName;
	}

	/**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 商家编号
     * @return sellerid 商家编号
     */
    public Integer getSellerid() {
        return sellerid;
    }

    /**
     * 商家编号
     * @param sellerid 商家编号
     */
    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    /**
     * 商家名
     * @return sellername 商家名
     */
    public String getSellername() {
        return sellername;
    }

    /**
     * 商家名
     * @param sellername 商家名
     */
    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    /**
     * 商家标识 1 签约商家, 2 非签约商家
     * @return seller_type 商家标识 1 签约商家, 2 非签约商家
     */
    public Integer getSellerType() {
        return sellerType;
    }

    /**
     * 商家标识 1 签约商家, 2 非签约商家
     * @param sellerType 商家标识 1 签约商家, 2 非签约商家
     */
    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
    }

    /**
     * 会员编号
     * @return uid 会员编号
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 会员编号
     * @param uid 会员编号
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 会员标识 1 签约主播  2.非签约主播 3体验官
     * @return user_type 会员标识 1 签约主播  2.非签约主播 3体验官
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 会员标识 1 签约主播  2.非签约主播 3体验官
     * @param userType 会员标识 1 签约主播  2.非签约主播 3体验官
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 通告类型1.商家2.活动
     * @return record_type 通告类型1.商家2.活动
     */
    public Byte getRecordType() {
        return recordType;
    }

    /**
     * 通告类型1.商家2.活动
     * @param recordType 通告类型1.商家2.活动
     */
    public void setRecordType(Byte recordType) {
        this.recordType = recordType;
    }

    /**
     * 通告id
     * @return record_id 通告id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * 通告id
     * @param recordId 通告id
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * 活动场次id
     * @return activity_id 活动场次id
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 活动场次id
     * @param activityId 活动场次id
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 是否推荐，0不推荐1推荐
     * @return is_recommend 是否推荐，0不推荐1推荐
     */
    public Byte getIsRecommend() {
        return isRecommend;
    }

    /**
     * 是否推荐，0不推荐1推荐
     * @param isRecommend 是否推荐，0不推荐1推荐
     */
    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 审核时间
     * @return review_time 审核时间
     */
    public Date getReviewTime() {
        return reviewTime;
    }

    /**
     * 审核时间
     * @param reviewTime 审核时间
     */
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    /**
     * 审核状态 0待审核 1审核通过 2审核拒绝 3停用
     * @return review_state 审核状态 0待审核 1审核通过 2审核拒绝 3停用
     */
    public Integer getReviewState() {
        return reviewState;
    }

    /**
     * 审核状态 0待审核 1审核通过 2审核拒绝 3停用
     * @param reviewState 审核状态 0待审核 1审核通过 2审核拒绝 3停用
     */
    public void setReviewState(Integer reviewState) {
        this.reviewState = reviewState;
    }

    /**
     * 拒绝原因（如果审核不通过必须有拒绝原因）
     * @return refuse_remark 拒绝原因（如果审核不通过必须有拒绝原因）
     */
    public String getRefuseRemark() {
        return refuseRemark;
    }

    /**
     * 拒绝原因（如果审核不通过必须有拒绝原因）
     * @param refuseRemark 拒绝原因（如果审核不通过必须有拒绝原因）
     */
    public void setRefuseRemark(String refuseRemark) {
        this.refuseRemark = refuseRemark == null ? null : refuseRemark.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 点评内容
     * @return content 点评内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 点评内容
     * @param content 点评内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public void setUids(List<Integer> uids) {
		this.uids = uids;
	}

}