package com.xmniao.xmn.core.live.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LiveFansRank {
    private Long id;

    private Long parentId;

    private String rankName;

    private Long rewardLowest;

    private Long rewardHighest;

    private Integer referrerRatio;

    private Integer parentReferrerRatio;

    private String sendBean;

    private Integer referrerReward;

    private Integer consumeRatio;

    private Integer redPacketType;

    private BigDecimal redPacketLowest;

    private BigDecimal redPacketHighest;

    private Integer rankNo;

    private String createTime;

    private String updateTime;

    private String picUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName == null ? null : rankName.trim();
    }

    public Long getRewardLowest() {
        return rewardLowest;
    }

    public void setRewardLowest(Long rewardLowest) {
        this.rewardLowest = rewardLowest;
    }

    public Long getRewardHighest() {
        return rewardHighest;
    }

    public void setRewardHighest(Long rewardHighest) {
        this.rewardHighest = rewardHighest;
    }

    public Integer getReferrerRatio() {
        return referrerRatio;
    }

    public void setReferrerRatio(Integer referrerRatio) {
        this.referrerRatio = referrerRatio;
    }

    public Integer getParentReferrerRatio() {
        return parentReferrerRatio;
    }

    public void setParentReferrerRatio(Integer parentReferrerRatio) {
        this.parentReferrerRatio = parentReferrerRatio;
    }

    public String getSendBean() {
        return sendBean;
    }

    public void setSendBean(String sendBean) {
        this.sendBean = sendBean == null ? null : sendBean.trim();
    }

    public Integer getReferrerReward() {
        return referrerReward;
    }

    public void setReferrerReward(Integer referrerReward) {
        this.referrerReward = referrerReward;
    }

    public Integer getConsumeRatio() {
        return consumeRatio;
    }

    public void setConsumeRatio(Integer consumeRatio) {
        this.consumeRatio = consumeRatio;
    }

    public Integer getRedPacketType() {
        return redPacketType;
    }

    public void setRedPacketType(Integer redPacketType) {
        this.redPacketType = redPacketType;
    }

    public BigDecimal getRedPacketLowest() {
        return redPacketLowest;
    }

    public void setRedPacketLowest(BigDecimal redPacketLowest) {
        this.redPacketLowest = redPacketLowest;
    }

    public BigDecimal getRedPacketHighest() {
        return redPacketHighest;
    }

    public void setRedPacketHighest(BigDecimal redPacketHighest) {
        this.redPacketHighest = redPacketHighest;
    }

    public Integer getRankNo() {
        return rankNo;
    }

    public void setRankNo(Integer rankNo) {
        this.rankNo = rankNo;
    }

 

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }
}