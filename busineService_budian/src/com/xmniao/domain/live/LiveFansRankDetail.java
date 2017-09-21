package com.xmniao.domain.live;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiveFansRankDetail
 * 
 * 类描述： 粉丝分账等级详情
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年2月25日 下午2:05:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class LiveFansRankDetail {
    private Integer id;

    private Long rankId;

    private Date effectiveDate;

    private Integer referrerRatio;

    private Integer parentReferrerRatio;

    private String sendBean;

    private Integer referrerReward;

    private Integer consumeRatio;

    private Integer redPacketType;

    private Integer privateRedPacketCashRatio;

    private Integer privateRedPacketCoinRatio;

    private Integer publicRedPacketCashRatio;

    private Integer publicRedPacketCoinRatio;

    private Integer objectOriented;

    private Integer referrerLedgerType;
    
    private RankRedPacketDetail rankRedPacketDetail; 
    
    private List<RankRedPacketDetail> redPacketList;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
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

    public Integer getPrivateRedPacketCashRatio() {
        return privateRedPacketCashRatio;
    }

    public void setPrivateRedPacketCashRatio(Integer privateRedPacketCashRatio) {
        this.privateRedPacketCashRatio = privateRedPacketCashRatio;
    }

    public Integer getPrivateRedPacketCoinRatio() {
        return privateRedPacketCoinRatio;
    }

    public void setPrivateRedPacketCoinRatio(Integer privateRedPacketCoinRatio) {
        this.privateRedPacketCoinRatio = privateRedPacketCoinRatio;
    }

    public Integer getPublicRedPacketCashRatio() {
        return publicRedPacketCashRatio;
    }

    public void setPublicRedPacketCashRatio(Integer publicRedPacketCashRatio) {
        this.publicRedPacketCashRatio = publicRedPacketCashRatio;
    }

    public Integer getPublicRedPacketCoinRatio() {
        return publicRedPacketCoinRatio;
    }

    public void setPublicRedPacketCoinRatio(Integer publicRedPacketCoinRatio) {
        this.publicRedPacketCoinRatio = publicRedPacketCoinRatio;
    }

    public Integer getObjectOriented() {
        return objectOriented;
    }

    public void setObjectOriented(Integer objectOriented) {
        this.objectOriented = objectOriented;
    }

	public RankRedPacketDetail getRankRedPacketDetail() {
		return rankRedPacketDetail;
	}

	public void setRankRedPacketDetail(RankRedPacketDetail rankRedPacketDetail) {
		this.rankRedPacketDetail = rankRedPacketDetail;
	}

	public List<RankRedPacketDetail> getRedPacketList() {
		return redPacketList;
	}

	public void setRedPacketList(List<RankRedPacketDetail> redPacketList) {
		this.redPacketList = redPacketList;
	}

	public Integer getReferrerLedgerType() {
		return referrerLedgerType;
	}

	public void setReferrerLedgerType(Integer referrerLedgerType) {
		this.referrerLedgerType = referrerLedgerType;
	}

	@Override
	public String toString() {
		return "LiveFansRankDetail [id=" + id + ", rankId=" + rankId
				+ ", effectiveDate=" + effectiveDate + ", referrerRatio="
				+ referrerRatio + ", parentReferrerRatio="
				+ parentReferrerRatio + ", sendBean=" + sendBean
				+ ", referrerReward=" + referrerReward + ", consumeRatio="
				+ consumeRatio + ", redPacketType=" + redPacketType
				+ ", privateRedPacketCashRatio=" + privateRedPacketCashRatio
				+ ", privateRedPacketCoinRatio=" + privateRedPacketCoinRatio
				+ ", publicRedPacketCashRatio=" + publicRedPacketCashRatio
				+ ", publicRedPacketCoinRatio=" + publicRedPacketCoinRatio
				+ ", objectOriented=" + objectOriented
				+ ", rankRedPacketDetail=" + rankRedPacketDetail
				+ ", redPacketList=" + redPacketList + "]";
	}

    
}