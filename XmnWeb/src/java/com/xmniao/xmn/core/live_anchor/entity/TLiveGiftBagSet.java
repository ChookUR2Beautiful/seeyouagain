package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveGiftBagSet
 *
 * 类描述：礼物礼包对应关系实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-29下午5:54:51
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveGiftBagSet extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8227780398089536229L;

	private Integer id;//业务主键ID

    private Integer giftBagId;//礼包ID

    private Integer giftId;//礼物ID

    private Integer giftNums;//礼物数量

    private BigDecimal getPercent;//中奖概率

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    //=============礼物信息===========
    private String giftName;//礼物名称

    private Integer giftPrice;//礼物单价
    
    private Integer giftExperience;//礼物代表的经验值

    private String giftAvatar;//礼物头像
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGiftBagId() {
        return giftBagId;
    }

    public void setGiftBagId(Integer giftBagId) {
        this.giftBagId = giftBagId;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getGiftNums() {
        return giftNums;
    }

    public void setGiftNums(Integer giftNums) {
        this.giftNums = giftNums;
    }

    public BigDecimal getGetPercent() {
        return getPercent;
    }

    public void setGetPercent(BigDecimal getPercent) {
        this.getPercent = getPercent;
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

    
	/**
	 * @return the giftName
	 */
	public String getGiftName() {
		return giftName;
	}

	/**
	 * @param giftName the giftName to set
	 */
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	/**
	 * @return the giftPrice
	 */
	public Integer getGiftPrice() {
		return giftPrice;
	}

	/**
	 * @param giftPrice the giftPrice to set
	 */
	public void setGiftPrice(Integer giftPrice) {
		this.giftPrice = giftPrice;
	}

	/**
	 * @return the giftExperience
	 */
	public Integer getGiftExperience() {
		return giftExperience;
	}

	/**
	 * @param giftExperience the giftExperience to set
	 */
	public void setGiftExperience(Integer giftExperience) {
		this.giftExperience = giftExperience;
	}

	/**
	 * @return the giftAvatar
	 */
	public String getGiftAvatar() {
		return giftAvatar;
	}

	/**
	 * @param giftAvatar the giftAvatar to set
	 */
	public void setGiftAvatar(String giftAvatar) {
		this.giftAvatar = giftAvatar;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveGiftBagSet [id=" + id + ", giftBagId=" + giftBagId
				+ ", giftId=" + giftId + ", giftNums=" + giftNums
				+ ", getPercent=" + getPercent + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", giftName=" + giftName
				+ ", giftPrice=" + giftPrice + ", giftExperience="
				+ giftExperience + ", giftAvatar=" + giftAvatar + "]";
	}
    
    
}