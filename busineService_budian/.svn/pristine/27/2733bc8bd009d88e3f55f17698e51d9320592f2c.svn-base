package com.xmniao.domain.ledger;

/**
 * 分账算法参数bean
 * @author  HuangXiaobin
 * @version  [版本号, 2016年05月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LedgerNewBean {

	private double  orderMoney;					/* 订单金额 */
	private double  userMoney;					/* 用户支付金额 */
	
	private double  ledgerRatio = 0.05d;			/* 分账上限比例 ,默认：5% */
	
	private Integer sellerId;					/* 消费商户ID */
	private double  baseagio;					/* 商户折扣 */
	
	private Integer genusSellerId; 				/* 所属商户ID */
	private double  sellerRatio = 0.01d;			/* 商户店外收益比例，默认：20%*ledgerRation */
	
	private Integer mikeId;
	private Integer mikeType=2;					/* 寻蜜客类型: 1.个人寻蜜客  2.中脉寻蜜客  */
	private double  mikeRatio = 0.01d;			/* 寻蜜客分账比例,默认：20%*ledgerRation*/
	
	private Integer parentMikeId;				/* 上级寻蜜客ID */
	private double  parentMikeRatio = 0.005d;	/* 上级寻蜜客分账比例,默认为：10%*ledgerRation*/
	
	private Integer topMikeId;					/* 上上级寻蜜客ID*/
	private double  topMikeRatio = 0.005d;		/* 上上级寻蜜客分账比例,默认：10%*ledgerRation*/
	
	private Integer consumeJointid;				/* 经销商ID（原消费区域合作商 ） */
	private double  consumeJointRatio = 0.005d;	/* 经销商分账比例,默认 10%*ledgerRation */
	
	private boolean breduction=true;
	
	private Integer ledgerMode=0;		/* 订单分账模式 0 正常立减(正常分账) 1不给立减(不给分账)  2不给立减(只给消费商家分账) */

	private double sellerExtraMoney=0;	/* 商家额外收益-不参与分账 */
	
	private double feeRatio = 0;		/* 平台收取手续费  */
	
	private double realPayMent;//实付金额（脉宝参与分账部分）
	
	public double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(double orderMoney) {
		this.orderMoney = orderMoney;
	}

	public double getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(double userMoney) {
		this.userMoney = userMoney;
	}


	public double getLedgerRatio() {
		return ledgerRatio;
	}

	public void setLedgerRatio(double ledgerRatio) {
		this.ledgerRatio = ledgerRatio;
	}

	public Integer getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getGenusSellerId() {
		return genusSellerId;
	}

	public void setGenusSellerId(Integer genusSellerId) {
		this.genusSellerId = genusSellerId;
	}

	public double getBaseagio() {
		return baseagio;
	}
	
	public void setBaseagio(double baseagio) {
		this.baseagio = baseagio;
	}
	
	public double getSellerRatio() {
		return sellerRatio;
	}
	
	public void setSellerRatio(double sellerRatio) {
		this.sellerRatio = sellerRatio;
	}
	
	
	public Integer getMikeType() {
		return mikeType;
	}
	
	public void setMikeType(Integer mikeType) {
		this.mikeType = mikeType;
	}
	public double getMikeRatio() {
		return mikeRatio;
	}
	
	public void setMikeRatio(double mikeRatio) {
		this.mikeRatio = mikeRatio;
	}

	public Integer getParentMikeId() {
		return parentMikeId;
	}

	public void setParentMikeId(Integer parentMikeId) {
		this.parentMikeId = parentMikeId;
	}

	public double getParentMikeRatio() {
		return parentMikeRatio;
	}

	public void setParentMikeRatio(double parentMikeRatio) {
		this.parentMikeRatio = parentMikeRatio;
	}

	public Integer getTopMikeId() {
		return topMikeId;
	}

	public void setTopMikeId(Integer topMikeId) {
		this.topMikeId = topMikeId;
	}

	public double getTopMikeRatio() {
		return topMikeRatio;
	}

	public void setTopMikeRatio(double topMikeRatio) {
		this.topMikeRatio = topMikeRatio;
	}

	public Integer getConsumeJointid() {
		return consumeJointid;
	}
	
	public void setConsumeJointid(Integer consumeJointid) {
		this.consumeJointid = consumeJointid;
	}
	
	public double getConsumeJointRatio() {
		return consumeJointRatio;
	}
	
	public void setConsumeJointRatio(double consumeJointRatio) {
		this.consumeJointRatio = consumeJointRatio;
	}

	public Integer getMikeId() {
		return mikeId;
	}

	public void setMikeId(Integer mikeId) {
		this.mikeId = mikeId;
	}


	public Integer getLedgerMode() {
		return ledgerMode;
	}

	public void setLedgerMode(Integer ledgerMode) {
		this.ledgerMode = ledgerMode;
	}

	
	public double getSellerExtraMoney() {
		return sellerExtraMoney;
	}

	public void setSellerExtraMoney(double sellerExtraMoney) {
		this.sellerExtraMoney = sellerExtraMoney;
	}

	public boolean isBreduction() {
		return breduction;
	}

	public void setBreduction(boolean breduction) {
		this.breduction = breduction;
	}

	public double getFeeRatio() {
		return feeRatio;
	}

	public void setFeeRatio(double feeRatio) {
		this.feeRatio = feeRatio;
	}
	
	

	/**
	 * @return the realPayMent
	 */
	public double getRealPayMent() {
		return realPayMent;
	}

	/**
	 * @param realPayMent the realPayMent to set
	 */
	public void setRealPayMent(double realPayMent) {
		this.realPayMent = realPayMent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LedgerNewBean [orderMoney=" + orderMoney + ", userMoney="
				+ userMoney + ", ledgerRatio=" + ledgerRatio + ", sellerId="
				+ sellerId + ", baseagio=" + baseagio + ", genusSellerId="
				+ genusSellerId + ", sellerRatio=" + sellerRatio + ", mikeId="
				+ mikeId + ", mikeType=" + mikeType + ", mikeRatio="
				+ mikeRatio + ", parentMikeId=" + parentMikeId
				+ ", parentMikeRatio=" + parentMikeRatio + ", topMikeId="
				+ topMikeId + ", topMikeRatio=" + topMikeRatio
				+ ", consumeJointid=" + consumeJointid + ", consumeJointRatio="
				+ consumeJointRatio + ", breduction=" + breduction
				+ ", ledgerMode=" + ledgerMode + ", sellerExtraMoney="
				+ sellerExtraMoney + ", feeRatio=" + feeRatio
				+ ", realPayMent=" + realPayMent + "]";
	}

}
