package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LiveFansRank {
    private Long id;

    private Long parentId;

    private String rankName;

    private Long rewardLowest;

    private Long rewardHighest;

//    private Integer referrerRatio;//无效
//
//    private Integer parentReferrerRatio;//无效
//
//    private String sendBean;//无效
//
//    private Integer referrerReward;//无效
//
//    private Integer consumeRatio;//无效
//
//    private BigDecimal redPacketLowest;//无效
//
//    private BigDecimal redPacketMidLower;//无效
//
//    private BigDecimal redPacketMidHigher;//无效
//    
//    private BigDecimal redPacketHighest;//无效

    private Integer rankNo;

    private Date createTime;

    private Date updateTime;
    
    private BigDecimal payment;	//充值金额

    private Integer rankType;	//等级类型 1.壕赚 2.V客
    
    private BigDecimal vkeRatio;	//V客主播分成比例
    
    private Integer saasNumber;		//SAAS资格数量
    
    private Integer anchorNumber;	//主播资格数量
    /**
     * 等级下的所有详情信息
     */
    private List<LiveFansRankDetail> detailList;
        
    /**
     * 等级下的当前生效详情信息
     */
    private LiveFansRankDetail liveFansRankDetail;
    
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
    
//    @Deprecated
//    public Integer getReferrerRatio() {
//        return referrerRatio;
//    }
//
//    public void setReferrerRatio(Integer referrerRatio) {
//        this.referrerRatio = referrerRatio;
//    }
//
//    @Deprecated
//    public Integer getParentReferrerRatio() {
//        return parentReferrerRatio;
//    }
//
//    public void setParentReferrerRatio(Integer parentReferrerRatio) {
//        this.parentReferrerRatio = parentReferrerRatio;
//    }
//
//    @Deprecated
//    public String getSendBean() {
//        return sendBean;
//    }
//
//    public void setSendBean(String sendBean) {
//        this.sendBean = sendBean == null ? null : sendBean.trim();
//    }
//
//    @Deprecated
//    public Integer getReferrerReward() {
//        return referrerReward;
//    }
//
//    public void setReferrerReward(Integer referrerReward) {
//        this.referrerReward = referrerReward;
//    }
//
//    @Deprecated
//    public Integer getConsumeRatio() {
//        return consumeRatio;
//    }
//
//    public void setConsumeRatio(Integer consumeRatio) {
//        this.consumeRatio = consumeRatio;
//    }
//
//    @Deprecated
//    public BigDecimal getRedPacketLowest() {
//        return redPacketLowest;
//    }
//
//    public void setRedPacketLowest(BigDecimal redPacketLowest) {
//        this.redPacketLowest = redPacketLowest;
//    }
//
//    @Deprecated
//    public BigDecimal getRedPacketHighest() {
//        return redPacketHighest;
//    }
//
//    public void setRedPacketHighest(BigDecimal redPacketHighest) {
//        this.redPacketHighest = redPacketHighest;
//    }

    public Integer getRankNo() {
        return rankNo;
    }

    public void setRankNo(Integer rankNo) {
        this.rankNo = rankNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

//    @Deprecated
//	public BigDecimal getRedPacketMidLower() {
//		return redPacketMidLower;
//	}
//
//	public void setRedPacketMidLower(BigDecimal redPacketMidLower) {
//		this.redPacketMidLower = redPacketMidLower;
//	}
//
//	@Deprecated
//	public BigDecimal getRedPacketMidHigher() {
//		return redPacketMidHigher;
//	}
//
//	public void setRedPacketMidHigher(BigDecimal redPacketMidHigher) {
//		this.redPacketMidHigher = redPacketMidHigher;
//	}

	
	public List<LiveFansRankDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<LiveFansRankDetail> detailList) {
		this.detailList = detailList;
	}

	public LiveFansRankDetail getLiveFansRankDetail() {
		return liveFansRankDetail;
	}

	public void setLiveFansRankDetail(LiveFansRankDetail liveFansRankDetail) {
		this.liveFansRankDetail = liveFansRankDetail;
	}
	
	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public Integer getRankType() {
		return rankType;
	}

	public void setRankType(Integer rankType) {
		this.rankType = rankType;
	}

	public BigDecimal getVkeRatio() {
		return vkeRatio;
	}

	public void setVkeRatio(BigDecimal vkeRatio) {
		this.vkeRatio = vkeRatio;
	}

	public Integer getSaasNumber() {
		return saasNumber;
	}

	public void setSaasNumber(Integer saasNumber) {
		this.saasNumber = saasNumber;
	}

	public Integer getAnchorNumber() {
		return anchorNumber;
	}

	public void setAnchorNumber(Integer anchorNumber) {
		this.anchorNumber = anchorNumber;
	}

	@Override
	public String toString() {
		return "LiveFansRank [id=" + id + ", rankName=" + rankName
				+ ", rewardLowest=" + rewardLowest + ", rewardHighest="
				+ rewardHighest + ", rankNo=" + rankNo + ", payment=" + payment
				+ ", rankType=" + rankType + ", detailList=" + detailList
				+ ", liveFansRankDetail=" + liveFansRankDetail + "]";
	}

	public LiveFansRank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LiveFansRank(Long id) {
		super();
		this.id = id;
	}

	public LiveFansRank(BigDecimal payment,Integer rankType) {
		super();
		this.payment = payment;
		this.rankType =  rankType;
	}
    
    
}