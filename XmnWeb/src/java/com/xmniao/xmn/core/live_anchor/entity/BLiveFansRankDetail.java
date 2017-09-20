package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BLiveFansRankDetail
 * 
 * 类描述： 级别返还模式实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-24 下午5:58:37 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BLiveFansRankDetail extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6444611876192630304L;

	private Integer id;//主键ID

    private Integer rankId;//等级id
    
    private String rankName;//等级名称

    private Date effectiveDate;//生效日期
    
    private String effectiveDateStr;//生效日期Str

    private Integer referrerRatio;//直属推荐人充值分佣百分比

    private Integer parentReferrerRatio;//直属推荐人上级充值分佣百分比

    private String sendBean;//打赏送鸟币 ,001否 默认002是

    private Integer referrerReward;//奖励倍数(推荐+红包)

    private Integer consumeRatio;//鸟币消费抵消百分比

    private Integer redPacketType;//红包返还资格：1、打赏后才可以获得红包；2、充值后即可获得红包；3、不可获得红包。默认1

    private Integer privateRedPacketCashRatio;//内购中红包现金百分比

    private Integer privateRedPacketCoinRatio;//内购中红包鸟币百分比

    private Integer publicRedPacketCashRatio;//外购红包现金百分比

    private Integer publicRedPacketCoinRatio;//外购红包鸟币百分比

    private Integer objectOriented;//会员类型 1VIP 2 商家 3直销 4 营业厅会员
    
    private String objectOrientedVal;//会员类型 1VIP 2 商家 3直销 4 营业厅会员
    
    private BigDecimal conversionRate;//兑换获得比例
    
    private List<BRankRedPacketDetail>  privateRedPacketDetailList;//内购返还比例
    
    private List<BRankRedPacketDetail>  publicRedPacketDetailList;//外购返还比例
    
    private String privateConsumeZone;//打赏鸟豆区间
    
    private String privateCashZone;//余额红包返还比例
    
    private String privateCoinZone;//鸟币红包返还比例
    
    private String publicConsumeZone;//打赏鸟豆区间
    
    private String publicCashZone;//余额红包返还比例
    
    private String publicCoinZone;//鸟币红包返还比例
    
    private Integer referrerLedgerType;//推荐奖励类型：0.鸟币，1.现金

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }
    
    

    /**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    

    /**
	 * @return the effectiveDateStr
	 */
	public String getEffectiveDateStr() {
		if(effectiveDate==null){
			return null;
		}
		effectiveDateStr=DateUtil.formatDate(effectiveDate, DateUtil.Y_M_D);
		return effectiveDateStr;
	}

	/**
	 * @param effectiveDateStr the effectiveDateStr to set
	 */
	public void setEffectiveDateStr(String effectiveDateStr) {
		this.effectiveDateStr = effectiveDateStr;
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
    
    

	/**
	 * 会员类型 1VIP 2 商家 3直销 4营业厅会员
	 * @return the objectOrientedVal
	 */
	public String getObjectOrientedVal() {
		if(objectOriented==null){
			return null;
		}
		switch (objectOriented) {
		case 1:
			objectOrientedVal="VIP客户";
			break;
		case 2:
			objectOrientedVal="商家客户";
			break;
		case 3:
			objectOrientedVal="直销客户";
			break;
		case 4:
			objectOrientedVal="营业厅会员";
			break;
		default:
			objectOrientedVal="VIP客户";
			break;
		}
		return objectOrientedVal;
	}

	/**
	 * @param objectOrientedVal the objectOrientedVal to set
	 */
	public void setObjectOrientedVal(String objectOrientedVal) {
		this.objectOrientedVal = objectOrientedVal;
	}
	
	

	/**
	 * @return the conversionRate
	 */
	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	/**
	 * @param conversionRate the conversionRate to set
	 */
	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	/**
	 * @return the privateRedPacketDetailList
	 */
	public List<BRankRedPacketDetail> getPrivateRedPacketDetailList() {
		return privateRedPacketDetailList;
	}

	/**
	 * @param privateRedPacketDetailList the privateRedPacketDetailList to set
	 */
	public void setPrivateRedPacketDetailList(
			List<BRankRedPacketDetail> privateRedPacketDetailList) {
		this.privateRedPacketDetailList = privateRedPacketDetailList;
	}

	/**
	 * @return the publicRedPacketDetailList
	 */
	public List<BRankRedPacketDetail> getPublicRedPacketDetailList() {
		return publicRedPacketDetailList;
	}

	/**
	 * @param publicRedPacketDetailList the publicRedPacketDetailList to set
	 */
	public void setPublicRedPacketDetailList(
			List<BRankRedPacketDetail> publicRedPacketDetailList) {
		this.publicRedPacketDetailList = publicRedPacketDetailList;
	}

	
	
	
	/**
	 * @return the privateConsumeZone
	 */
	public String getPrivateConsumeZone() {
		return privateConsumeZone;
	}

	/**
	 * @param privateConsumeZone the privateConsumeZone to set
	 */
	public void setPrivateConsumeZone(String privateConsumeZone) {
		this.privateConsumeZone = privateConsumeZone;
	}

	/**
	 * @return the privateCashZone
	 */
	public String getPrivateCashZone() {
		return privateCashZone;
	}

	/**
	 * @param privateCashZone the privateCashZone to set
	 */
	public void setPrivateCashZone(String privateCashZone) {
		this.privateCashZone = privateCashZone;
	}

	/**
	 * @return the privateCoinZone
	 */
	public String getPrivateCoinZone() {
		return privateCoinZone;
	}

	/**
	 * @param privateCoinZone the privateCoinZone to set
	 */
	public void setPrivateCoinZone(String privateCoinZone) {
		this.privateCoinZone = privateCoinZone;
	}

	/**
	 * @return the publicConsumeZone
	 */
	public String getPublicConsumeZone() {
		return publicConsumeZone;
	}

	/**
	 * @param publicConsumeZone the publicConsumeZone to set
	 */
	public void setPublicConsumeZone(String publicConsumeZone) {
		this.publicConsumeZone = publicConsumeZone;
	}

	/**
	 * @return the publicCashZone
	 */
	public String getPublicCashZone() {
		return publicCashZone;
	}

	/**
	 * @param publicCashZone the publicCashZone to set
	 */
	public void setPublicCashZone(String publicCashZone) {
		this.publicCashZone = publicCashZone;
	}

	/**
	 * @return the publicCoinZone
	 */
	public String getPublicCoinZone() {
		return publicCoinZone;
	}

	/**
	 * @param publicCoinZone the publicCoinZone to set
	 */
	public void setPublicCoinZone(String publicCoinZone) {
		this.publicCoinZone = publicCoinZone;
	}
	
	

	/**
	 * @return the referrerLedgerType
	 */
	public Integer getReferrerLedgerType() {
		return referrerLedgerType;
	}

	/**
	 * @param referrerLedgerType the referrerLedgerType to set
	 */
	public void setReferrerLedgerType(Integer referrerLedgerType) {
		this.referrerLedgerType = referrerLedgerType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BLiveFansRankDetail [id=" + id + ", rankId=" + rankId
				+ ", rankName=" + rankName + ", effectiveDate=" + effectiveDate
				+ ", effectiveDateStr=" + effectiveDateStr + ", referrerRatio="
				+ referrerRatio + ", parentReferrerRatio="
				+ parentReferrerRatio + ", sendBean=" + sendBean
				+ ", referrerReward=" + referrerReward + ", consumeRatio="
				+ consumeRatio + ", redPacketType=" + redPacketType
				+ ", privateRedPacketCashRatio=" + privateRedPacketCashRatio
				+ ", privateRedPacketCoinRatio=" + privateRedPacketCoinRatio
				+ ", publicRedPacketCashRatio=" + publicRedPacketCashRatio
				+ ", publicRedPacketCoinRatio=" + publicRedPacketCoinRatio
				+ ", objectOriented=" + objectOriented + ", objectOrientedVal="
				+ objectOrientedVal + ", conversionRate=" + conversionRate
				+ ", privateRedPacketDetailList=" + privateRedPacketDetailList
				+ ", publicRedPacketDetailList=" + publicRedPacketDetailList
				+ ", privateConsumeZone=" + privateConsumeZone
				+ ", privateCashZone=" + privateCashZone + ", privateCoinZone="
				+ privateCoinZone + ", publicConsumeZone=" + publicConsumeZone
				+ ", publicCashZone=" + publicCashZone + ", publicCoinZone="
				+ publicCoinZone + ", referrerLedgerType=" + referrerLedgerType
				+ "]";
	}
    
    
}