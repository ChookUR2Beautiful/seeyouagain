package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 描述：购买鸟币套餐实体对象
 * @author yhl
 * 2016年8月29日19:50:43
 * */
public class LiveRechargeComboInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5011506943509481921L;

	
	//充值套餐ID
	private Integer id;
	
	//充值现金金额
	private BigDecimal rechAmount;
	
	//标准兑换鸟币数
	private Integer rechNormCoin;

	//充值赠送鸟币数
	private Integer rechFreeCoin;
	
	//实际获得鸟币数
	private Integer rechRealCoin;
	
	//充值类型 ： 1 IOS  2 Android
	private Integer rechargeType ;
	
	//套餐状态 默认1 ： 1有效  2 无效
	private Integer status;
	
	//更新时间
	private Date updateDate;
	
	//IOS 内购对应的标示
	private String productId;
	
	//粉丝卡等级名称
	private String fansRankName;

	private Integer fansRankId;
	//套餐对应各种什么客的类型
	private String objectOriented;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getRechAmount() {
		return rechAmount;
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
		return rechRealCoin;
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
		this.productId = productId;
	}

	public String getFansRankName() {
		return fansRankName;
	}

	public void setFansRankName(String fansRankName) {
		this.fansRankName = fansRankName;
	}

	public Integer getFansRankId() {
		return fansRankId;
	}

	public void setFansRankId(Integer fansRankId) {
		this.fansRankId = fansRankId;
	}

	public String getObjectOriented() {
		return objectOriented;
	}

	public void setObjectOriented(String objectOriented) {
		this.objectOriented = objectOriented;
	}

	@Override
	public String toString() {
		return "LiveRechargeComboInfo [id=" + id + ", rechAmount=" + rechAmount + ", rechNormCoin=" + rechNormCoin
				+ ", rechFreeCoin=" + rechFreeCoin + ", rechRealCoin=" + rechRealCoin + ", rechargeType=" + rechargeType
				+ ", status=" + status + ", updateDate=" + updateDate + ", productId=" + productId + ", fansRankName="
				+ fansRankName + ", fansRankId=" + fansRankId 
				+ objectOriented + "]";
	}

	

	
}
