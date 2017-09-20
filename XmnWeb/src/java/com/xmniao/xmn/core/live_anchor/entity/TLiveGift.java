package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveGift
 *
 * 类描述：直播礼物实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-17下午2:07:19
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveGift extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1267784398161870246L;

	private Integer id;//直播礼物id

    private String giftName;//礼物名称

    private Integer giftType;//普通礼物类型  1 积分礼物  2 鸟豆礼物  3 积分卡

    private Integer giftPrice;//礼物单价
    
    private String giftPriceStr;//礼物价格(积分/鸟豆)

    private Integer giftExperience;//礼物代表的经验值

    private String giftAvatar;//礼物头像
    
    private Integer freeStatus;//是否参与免费赠送状态：  1 是  0 否 
    
    private Integer isGiftPacks;//大礼包：1 是 0 否
    
    private Integer status;//是否启用  1 启用  0 未启用
    
    private Integer isRadio;//是否发送平台广播 默认 0 否 1 是

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    private Integer giftBagId;//礼包ID
    
    private String isSeries;//礼物连击，默认001 不可连击,002可连击
    
    private Integer sortVal;//礼物排序值，1-99之间的整数，值越小排序越靠前
    
    private Integer giftKind;//礼物类型
    
    private BigDecimal freight;//默认运费
    
    private List<TLiveGiftDetail> giftDetailList;//礼物详细信息
    
    private String category;//商户行业分类
    
    private String categoryName;//商户行业分类名称
    
    private String genre;//二级类别
    
    private String genreName;//二级类别名称
    
    private Long productNum;//关联商品数量
    
    private String comboCategoryName;//关联套餐分类名称
    
    private String msgPriority;//IM消息的优先等级 high:高,适用用大礼物，normal:正常

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName == null ? null : giftName.trim();
    }

    public Integer getGiftType() {
        return giftType;
    }

    public void setGiftType(Integer giftType) {
        this.giftType = giftType;
    }

    public Integer getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(Integer giftPrice) {
        this.giftPrice = giftPrice;
    }

    
    /**
	 * @return the giftPriceStr
	 */
	public String getGiftPriceStr() {
		if(giftType==null){
			return null;
		}
		return giftPriceStr=giftPrice+(giftType==1?"积分":"鸟豆");
	}

	/**
	 * @param giftPriceStr the giftPriceStr to set
	 */
	public void setGiftPriceStr(String giftPriceStr) {
		this.giftPriceStr = giftPriceStr;
	}

	public Integer getGiftExperience() {
        return giftExperience;
    }

    public void setGiftExperience(Integer giftExperience) {
        this.giftExperience = giftExperience;
    }

    public String getGiftAvatar() {
        return giftAvatar;
    }

    public void setGiftAvatar(String giftAvatar) {
        this.giftAvatar = giftAvatar == null ? null : giftAvatar.trim();
    }
    
    

    /**
	 * @return the freeStatus
	 */
	public Integer getFreeStatus() {
		return freeStatus;
	}

	/**
	 * @param freeStatus the freeStatus to set
	 */
	public void setFreeStatus(Integer freeStatus) {
		this.freeStatus = freeStatus;
	}

	
	/**
	 * @return the isGiftPacks
	 */
	public Integer getIsGiftPacks() {
		return isGiftPacks;
	}

	/**
	 * @param isGiftPacks the isGiftPacks to set
	 */
	public void setIsGiftPacks(Integer isGiftPacks) {
		this.isGiftPacks = isGiftPacks;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

	/**
	 * @return the isRadio
	 */
	public Integer getIsRadio() {
		return isRadio;
	}

	/**
	 * @param isRadio the isRadio to set
	 */
	public void setIsRadio(Integer isRadio) {
		this.isRadio = isRadio;
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
	 * @return the giftBagId
	 */
	public Integer getGiftBagId() {
		return giftBagId;
	}

	/**
	 * @param giftBagId the giftBagId to set
	 */
	public void setGiftBagId(Integer giftBagId) {
		this.giftBagId = giftBagId;
	}

	
	/**
	 * @return the isSeries
	 */
	public String getIsSeries() {
		return isSeries;
	}

	/**
	 * @param isSeries the isSeries to set
	 */
	public void setIsSeries(String isSeries) {
		this.isSeries = isSeries;
	}
	
	

	/**
	 * @return the sortVal
	 */
	public Integer getSortVal() {
		return sortVal;
	}

	/**
	 * @param sortVal the sortVal to set
	 */
	public void setSortVal(Integer sortVal) {
		this.sortVal = sortVal;
	}
	
	

	/**
	 * @return the giftKind
	 */
	public Integer getGiftKind() {
		return giftKind;
	}

	/**
	 * @param giftKind the giftKind to set
	 */
	public void setGiftKind(Integer giftKind) {
		this.giftKind = giftKind;
	}

	/**
	 * @return the freight
	 */
	public BigDecimal getFreight() {
		return freight;
	}

	/**
	 * @param freight the freight to set
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	
	

	/**
	 * @return the giftDetailList
	 */
	public List<TLiveGiftDetail> getGiftDetailList() {
		return giftDetailList;
	}

	/**
	 * @param giftDetailList the giftDetailList to set
	 */
	public void setGiftDetailList(List<TLiveGiftDetail> giftDetailList) {
		this.giftDetailList = giftDetailList;
	}
	
	

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	

	/**
	 * @return the productNum
	 */
	public Long getProductNum() {
		return productNum;
	}

	/**
	 * @param productNum the productNum to set
	 */
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	
	

	/**
	 * @return the comboCategoryName
	 */
	public String getComboCategoryName() {
		return comboCategoryName;
	}

	/**
	 * @param comboCategoryName the comboCategoryName to set
	 */
	public void setComboCategoryName(String comboCategoryName) {
		this.comboCategoryName = comboCategoryName;
	}
	
	

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	

	/**
	 * @return the msgPriority
	 */
	public String getMsgPriority() {
		return msgPriority;
	}

	/**
	 * @param msgPriority the msgPriority to set
	 */
	public void setMsgPriority(String msgPriority) {
		this.msgPriority = msgPriority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveGift [id=" + id + ", giftName=" + giftName + ", giftType="
				+ giftType + ", giftPrice=" + giftPrice + ", giftPriceStr="
				+ giftPriceStr + ", giftExperience=" + giftExperience
				+ ", giftAvatar=" + giftAvatar + ", freeStatus=" + freeStatus
				+ ", isGiftPacks=" + isGiftPacks + ", status=" + status
				+ ", isRadio=" + isRadio + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", giftBagId=" + giftBagId
				+ ", isSeries=" + isSeries + ", sortVal=" + sortVal
				+ ", giftKind=" + giftKind + ", freight=" + freight
				+ ", giftDetailList=" + giftDetailList + ", genre=" + genre
				+ "]";
	}
    
    
}