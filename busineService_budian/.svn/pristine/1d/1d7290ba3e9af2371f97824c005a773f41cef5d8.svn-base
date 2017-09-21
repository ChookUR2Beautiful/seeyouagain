package com.xmniao.domain.ledger;


/**
 * 分账服务参数bean
 * @author  HuangXiaobin
 * @version  [版本号, 2014年11月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LedgerBean {

	/**
	 * 会员ID
	 */
	private Integer userId;

	/**
	 * 平台补助占比
	 */
	private double subsidy;
	
	/**
	 *  会员消费金额 
	 */
	private double consumeMoney;
	
	/**
	 * 会员卡消费金额
	 */
	private double cardMoney;

	/**
	 * 商户ID
	 */
	private Integer sellerId;

	/**
	 * 商户折扣
	 */
	private  double baseagio;

	/**
	 * 寻蜜客ID
	 */
	private Integer mikeId;

	/**
	 * 用户区域合作商ID
	 */
	private Integer memberJointId;
	
	/**
	 * 用户区域ID
	 */
	private String memberArea;

	/**
	 * 用户区域合作商业务员ID
	 */
	private Integer memberSalesmanId;

	/**
	 * 消费区域ID
	 */
	private String consumeArea;
	
	/**
	 * 消费区域合作商ID
	 */
	private Integer consumeJointid;

	/**
	 * 消费区域合作商业务员ID
	 */
	private Integer consumeSalesmanId;

	/**
	 * 平台（寻蜜鸟）
	 */
	private String xmniao;
	
	/**
	 * 用户区域合作商的员工分账比例（业务员分账比例）
	 */
	private double memberSalesmanRation;

	/**
	 * 消费区域合作商的员工分账比例（业务员分账比例）
	 */
	private double consumeSalesmanRation;
	
	/**
	 * 所属商户签约类型：0或null：合作商签约，1：平台帮助签约 2：个人向蜜客帮忙签约
	 */
	private String memberSigningType;

	/**
	 * 消费商户签约类型：0或null：合作商签约，1：平台帮助签约 2：个人向蜜客帮忙签约
	 */
	private String consumeSigningType;

	
	/**
	 * 支付方式
	 */
	private String payType;
	
	/**
	 * 是否平台承担支付手续费
	 */
	private String isPlatFeesAll;
	
	/**
	 * 平台收益比例，用于商户折扣为100的订单分账
	 */
	private double platRation;
	
	
	
	public LedgerBean() {super();}

	/**
	 * 分账计算类构造
	 * @param userId 会员ID
	 * @param consumeMoney 会员消费金额（订单金额）
	 * @param sellerId	商户ID
	 * @param baseagio	商户折扣
	 * @param mikeId	向蜜客ID
	 * @param memberJointId	会员所属合作商ID
	 * @param memberSalesmanId	会员所属合作商 的业务员ID
	 * @param consumeJointid	会员消费商户所属合作商ID
	 * @param consumeSalesmanId	会员消费商户所属合作商 的业务员ID
	 * @param memberSigningType 所属合作商是否总部签约(0或null:不是，1：是)
	 * @param consumeSigningType 消费合作商是否总部签约(0或null:不是，1：是)
	 * @param memberSalesmanRation 所属合作商 员工分账比例
	 * @param consumeSalesmanRation 消费合作商 员工分账比例
	 * @param payType	支付方式
	 */
	public LedgerBean(Integer userId, double consumeMoney, Integer sellerId,double baseagio,
			Integer mikeId,
			Integer memberJointId, Integer memberSalesmanId,
			Integer consumeJointid, Integer consumeSalesmanId,
			String memberSigningType,String consumeSigningType,
			double memberSalesmanRation,double consumeSalesmanRation,String payType) {
		super();
		this.userId = userId;
		this.consumeMoney = consumeMoney;
		this.sellerId = sellerId;
		this.baseagio = baseagio;
		this.mikeId = mikeId;
		this.memberJointId = memberJointId;
		this.memberSalesmanId = memberSalesmanId;
		this.consumeJointid = consumeJointid;
		this.consumeSalesmanId = consumeSalesmanId;
		this.memberSigningType = memberSigningType;
		this.consumeSigningType = consumeSigningType;
		this.payType = payType;
		this.memberSalesmanRation = memberSalesmanRation;
		this.consumeSalesmanRation = consumeSalesmanRation;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public double getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(double consumeMoney) {
		this.consumeMoney = consumeMoney;
	}
	
	public double getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(double cardMoney) {
		this.cardMoney = cardMoney;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public double getBaseagio() {
		return baseagio;
	}

	public void setBaseagio(double baseagio) {
		this.baseagio = baseagio;
	}

	public Integer getMikeId() {
		return mikeId;
	}

	public void setMikeId(Integer mikeId) {
		this.mikeId = mikeId;
	}

	public Integer getMemberJointId() {
		return memberJointId;
	}

	public void setMemberJointId(Integer memberJointId) {
		this.memberJointId = memberJointId;
	}

	public Integer getMemberSalesmanId() {
		return memberSalesmanId;
	}

	public void setMemberSalesmanId(Integer memberSalesmanId) {
		this.memberSalesmanId = memberSalesmanId;
	}

	public Integer getConsumeJointid() {
		return consumeJointid;
	}

	public void setConsumeJointid(Integer consumeJointid) {
		this.consumeJointid = consumeJointid;
	}

	public Integer getConsumeSalesmanId() {
		return consumeSalesmanId;
	}

	public void setConsumeSalesmanId(Integer consumeSalesmanId) {
		this.consumeSalesmanId = consumeSalesmanId;
	}

	public String getXmniao() {
		return xmniao;
	}

	public void setXmniao(String xmniao) {
		this.xmniao = xmniao;
	}

	public String getMemberSigningType() {
		return memberSigningType;
	}

	public void setMemberSigningType(String memberSigningType) {
		this.memberSigningType = memberSigningType;
	}

	public String getConsumeSigningType() {
		return consumeSigningType;
	}

	public void setConsumeSigningType(String consumeSigningType) {
		this.consumeSigningType = consumeSigningType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public double getMemberSalesmanRation() {
		return memberSalesmanRation;
	}

	public void setMemberSalesmanRation(double memberSalesmanRation) {
		this.memberSalesmanRation = memberSalesmanRation;
	}

	public double getConsumeSalesmanRation() {
		return consumeSalesmanRation;
	}

	public void setConsumeSalesmanRation(double consumeSalesmanRation) {
		this.consumeSalesmanRation = consumeSalesmanRation;
	}

	public double getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(double subsidy) {
		this.subsidy = subsidy;
	}

	public String getMemberArea() {
		return memberArea;
	}

	public void setMemberArea(String memberArea) {
		this.memberArea = memberArea;
	}

	public String getConsumeArea() {
		return consumeArea;
	}

	public void setConsumeArea(String consumeArea) {
		this.consumeArea = consumeArea;
	}

	public String getIsPlatFeesAll() {
		return isPlatFeesAll;
	}

	public void setIsPlatFeesAll(String isPlatFeesAll) {
		this.isPlatFeesAll = isPlatFeesAll;
	}

	public double getPlatRation() {
		return platRation;
	}

	public void setPlatRation(double platRation) {
		this.platRation = platRation;
	}

}
