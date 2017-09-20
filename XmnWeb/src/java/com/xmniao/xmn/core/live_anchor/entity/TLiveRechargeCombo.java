package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.NumberUtil;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRechargeCombo
 * 
 * 类描述： 直播鸟币充值套餐实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-8-31 下午6:01:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveRechargeCombo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 239694589539671510L;

	private Integer id;//充值套餐ID

    private BigDecimal rechAmount;//充值现金金额

    private Integer rechNormCoin;//标准兑换鸟币数

    private Integer rechFreeCoin;//充值赠送鸟币数

    private Integer rechRealCoin;//实际获得鸟币数

    private Integer rechargeType;//充值类型 ： 1 IOS  2 Android/wachat
    
    private String rechargeTypeStr;//充值类型 

    private Integer status;//套餐状态 默认1 ： 1有效  2 无效 

    private Date updateDate;//更新时间

    private String productId;//IOS 内购的 product_id
    
    private Integer fansRankId;//粉丝级别id
    
    private String fansRankName;//粉丝级别名称
    
    private Long rechargeNums;//累计充值人次
    
    private String recharges;//粉丝级别关联充值套餐金额
    
    private Integer isPublicComo;//是否是储值卡套餐
    
    private Integer comboSource;//苹果App类型 ：0寻蜜鸟，1鸟人科技
    
    private String objectOriented;//绑定类型 1.VIP 2.商家 3.直销
    
    private List<String> objectOrienteds;//绑定类型数组
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRechAmount() {
        return rechAmount=new BigDecimal(NumberUtil.getNumberFixedpoint(rechAmount, 0));
    }

    public void setRechAmount(BigDecimal rechAmount) {
        this.rechAmount = rechAmount;
    }

    public Integer getRechNormCoin() {
        return rechNormCoin;
    }

    public void setRechNormCoin(Integer rechNormCoin) {
        this.rechNormCoin = rechNormCoin;
    }

    public Integer getRechFreeCoin() {
        return rechFreeCoin;
    }

    public void setRechFreeCoin(Integer rechFreeCoin) {
        this.rechFreeCoin = rechFreeCoin;
    }

    public Integer getRechRealCoin() {
        return rechRealCoin = rechNormCoin + rechFreeCoin;
    }

    public void setRechRealCoin(Integer rechRealCoin) {
        this.rechRealCoin = rechRealCoin;
    }

    public Integer getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(Integer rechargeType) {
        this.rechargeType = rechargeType;
    }
    
    

    /**
     * 充值类型 ： 1 IOS , 2 Android/wachat , 3 PC充值（鸟人VIP）, 4 PC充值（鸟人GD）
	 * @return the rechargeTypeStr
	 */
	public String getRechargeTypeStr() {
		if(rechargeType==null){
			return null;
		}
		switch (rechargeType) {
		case 1:
			rechargeTypeStr="苹果";
			break;
		case 2:
			rechargeTypeStr="安卓/微信";
			break;
		case 3:
			rechargeTypeStr="PC充值（鸟人VIP）";
			break;
		case 4:
			rechargeTypeStr="PC充值（鸟人GD）";
			break;

		default:
			rechargeTypeStr="苹果";
			break;
		}
		return rechargeTypeStr;
	}

	/**
	 * @param rechargeTypeStr the rechargeTypeStr to set
	 */
	public void setRechargeTypeStr(String rechargeTypeStr) {
		this.rechargeTypeStr = rechargeTypeStr;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }
    
    
    

	/**
	 * @return the fansRankId
	 */
	public Integer getFansRankId() {
		return fansRankId;
	}

	/**
	 * @param fansRankId the fansRankId to set
	 */
	public void setFansRankId(Integer fansRankId) {
		this.fansRankId = fansRankId;
	}

	/**
	 * @return the fansRankName
	 */
	public String getFansRankName() {
		return fansRankName;
	}

	/**
	 * @param fansRankName the fansRankName to set
	 */
	public void setFansRankName(String fansRankName) {
		this.fansRankName = fansRankName;
	}

	/**
	 * @return the rechargeNums
	 */
	public Long getRechargeNums() {
		return rechargeNums;
	}

	/**
	 * @param rechargeNums the rechargeNums to set
	 */
	public void setRechargeNums(Long rechargeNums) {
		this.rechargeNums = rechargeNums;
	}

	
	/**
	 * @return the recharges
	 */
	public String getRecharges() {
		return recharges;
	}

	/**
	 * @param recharges the recharges to set
	 */
	public void setRecharges(String recharges) {
		this.recharges = recharges;
	}

	public Integer getIsPublicComo() {
		return isPublicComo;
	}

	public void setIsPublicComo(Integer isPublicComo) {
		this.isPublicComo = isPublicComo;
	}
	
	

	/**
	 * @return the comboSource
	 */
	public Integer getComboSource() {
		return comboSource;
	}

	/**
	 * @param comboSource the comboSource to set
	 */
	public void setComboSource(Integer comboSource) {
		this.comboSource = comboSource;
	}

	/**
	 * @return the objectOriented
	 */
	public String getObjectOriented() {
		return objectOriented;
	}

	/**
	 * @param objectOriented the objectOriented to set
	 */
	public void setObjectOriented(String objectOriented) {
		this.objectOriented = objectOriented;
		if(StringUtils.isNotBlank(objectOriented)){
			this.objectOrienteds=Arrays.asList(objectOriented.split(","));
		}
	}

	
	/**
	 * @return the objectOrienteds
	 */
	public List<String> getObjectOrienteds() {
		return objectOrienteds;
	}

	/**
	 * @param objectOrienteds the objectOrienteds to set
	 */
	public void setObjectOrienteds(List<String> objectOrienteds) {
		this.objectOrienteds = objectOrienteds;
	}

	@Override
	public String toString() {
		return "TLiveRechargeCombo [id=" + id + ", rechAmount=" + rechAmount
				+ ", rechNormCoin=" + rechNormCoin + ", rechFreeCoin="
				+ rechFreeCoin + ", rechRealCoin=" + rechRealCoin
				+ ", rechargeType=" + rechargeType + ", rechargeTypeStr="
				+ rechargeTypeStr + ", status=" + status + ", updateDate="
				+ updateDate + ", productId=" + productId + ", fansRankId="
				+ fansRankId + ", fansRankName=" + fansRankName
				+ ", rechargeNums=" + rechargeNums + ", recharges=" + recharges
				+ ", isPublicComo=" + isPublicComo + "]";
	}

}