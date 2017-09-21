package com.xmniao.domain.order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：BillBean
 * 
 * 类描述： 订单实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月20日 上午10:59:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BillBean {
    private Long bid;//订单编号

    private Integer codeid;//订单消费码

    private Integer sellerid;//商家ID

    private String sellername;//商家名称

    private Integer consumeJointid;

    private String nname;//用户昵称

    private BigDecimal money;//消费总金额

    private BigDecimal profit;//收益支付金额

    private BigDecimal commision;

    private BigDecimal payment;//需支付金额

    private BigDecimal rebate;//本单可返利金额

    private Integer aid;//商家帐号ID

    private String fullname;//商家帐号姓名

    private String phoneid;//手机号码

    private Integer bfirst;//是否为首单，0不是首单，1是首单

    private Integer status;//订单状态

    private Date sdate;//下单时间

    private Date zdate;//支付时间

    private Date ydate;//验证时间

    private String number;//支付流水号

    private String paytype;//支付方式

    private Integer isaccount;//到账状态：0 未到账\n            1 已到账

    private Double baseagio;

    private Integer type;

    private Integer hstatus;//分账状态 0  初始状态  9 已分账

    private String consumeCorporate;

    private Integer genussellerid;

    private String genusname;

    private Integer jointid;

    private String corporate;

    private Byte mikeType;

    private Integer uid;//用户ID

    private String commission;

    private String area;

    private Integer zoneid;

    private String payid;

    private Integer isverify;

    private Date ldate;

    private Date fdate;

    private Date edate;

    private Byte commStatus;

    private Byte estimate;

    private Integer isVirtual;

    private Double flatAgio;

    private Byte entryStatus;

    private BigDecimal giveMoney;//赠送支付金额

    private BigDecimal flatMoney;//平台补贴金额

    private BigDecimal maxHistory;//历史最高的消费金额

    private BigDecimal cuser;//优惠券支付金额

    private BigDecimal cdenom;//优惠卷面额总数

    private String thirdUid;//第三方支付帐号

    private Byte normal;//是否为刷单\r\n0未判断\r\n1正常订单\r\n2异常订单

    private Byte isActivity;//订单是否参与活动，0未参与，1参与

    private BigDecimal prizeNum;//派奖金额

    private Integer source;

    private Byte phoneType;

    private String version;

    private Integer versionId;

    private Integer appSerial;

    private String imei;

    private Double ratio;//佣金补贴比例(商户补贴比例)

    private BigDecimal ratioMoney;//佣金补贴金额

    private Integer orderSource;//订单来源 ：默认为0     1票量网

    private Long noid;//使用会员卡支付时需填此编号

    private BigDecimal amount;//会员卡支付金额，商家会员卡可以和钱包余额组合支付

    private Double integral;//本单可返积分

    private Integer xmerUid;//寻蜜客会员编号

    private Integer mikeid;//寻蜜客ID

    private String mikename;//寻蜜客名称
    
    private BigDecimal reduction;//减免金额

    private Integer couponType;//优惠券类型 1:消费 2:商户 3:粉丝 4:套餐 5:全平台
    
    private String activityConent;//0 正常分账模式 1 不参与分账 2 仅商家参与分账
    
    
    private String rPhone;//推荐消费人手机号

    private Integer rUserId;//推荐消费人id
    
    private BigDecimal fullReduction;//商户满就减活动减免金额
    
    private Integer fullReductionId;//商户满就减金额活动Id，对应t_activity_fullreduction
    
    private Integer ledgerMode;
    
    private Integer liveLedger;//是否开启直播分账 0 不开启 1开启
    
    private Integer liveLedgerStyle;//直播分账形式 0 自动分账 1 手动分账
    
    private Integer liveLedgerMode;//直播分账模式 1 全额分账 2 仅粉丝券分账
    
    private Double liveLedgerRatio;//直播分账比例 如:0.6000
    
    /*
     * 上级寻蜜客 
     */
    private Integer oneLevelXmerId;
    
    /*
     * 上上级寻蜜客
     */
    private Integer twoLevelXmerId;
    
    private String oneLevelXmerName;
    
    private String twoLevelXmerName;
    
    private String proportion;// 分账比例 格式  会员,商家,寻蜜客,合作商(),平台（废弃）
    
    //可分账金额
    private BigDecimal ledgerAmount; 
    
    /* 订单支付未成功原因 */
    private Integer cannelStatus;
    
    /* 鸟币支付金额 */
    private BigDecimal liveCoin;

    /* 鸟币支付金额 */
    private BigDecimal liveCoinMoney;
    
    /*鸟币加价比*/
    private Double liveCoinRatio;
    
    /* 订单手续费 */
    private Double feeRatio;
    
    /* 商家专享鸟币 */
    private BigDecimal sellerCoin;
    
    private String uidMbEcno;//会员脉宝EC号
    
    private Integer saasChannel;//寻蜜客签约商家SAAS来源 null为非SAAS签约商户 1 常规SAAS签约 2脉客SAAS签约 3V客SAAS签约
    
    private BigDecimal realPayMent;//脉宝参与分账的实际支付金额
    
    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Integer getCodeid() {
        return codeid;
    }

    public void setCodeid(Integer codeid) {
        this.codeid = codeid;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getConsumeJointid() {
        return consumeJointid;
    }

    public void setConsumeJointid(Integer consumeJointid) {
        this.consumeJointid = consumeJointid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid == null ? null : phoneid.trim();
    }

    public Integer getBfirst() {
        return bfirst;
    }

    public void setBfirst(Integer bfirst) {
        this.bfirst = bfirst;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getZdate() {
        return zdate;
    }

    public void setZdate(Date zdate) {
        this.zdate = zdate;
    }

    public Date getYdate() {
		return ydate;
	}

	public void setYdate(Date ydate) {
		this.ydate = ydate;
	}

	public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Integer getIsaccount() {
        return isaccount;
    }

    public void setIsaccount(Integer isaccount) {
        this.isaccount = isaccount;
    }

    public Double getBaseagio() {
        return baseagio;
    }

    public void setBaseagio(Double baseagio) {
        this.baseagio = baseagio;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHstatus() {
        return hstatus;
    }

    public void setHstatus(Integer hstatus) {
        this.hstatus = hstatus;
    }

    public String getConsumeCorporate() {
        return consumeCorporate;
    }

    public void setConsumeCorporate(String consumeCorporate) {
        this.consumeCorporate = consumeCorporate == null ? null : consumeCorporate.trim();
    }

    public Integer getGenussellerid() {
        return genussellerid;
    }

    public void setGenussellerid(Integer genussellerid) {
        this.genussellerid = genussellerid;
    }

    public String getGenusname() {
        return genusname;
    }

    public void setGenusname(String genusname) {
        this.genusname = genusname == null ? null : genusname.trim();
    }

    public Integer getJointid() {
        return jointid;
    }

    public void setJointid(Integer jointid) {
        this.jointid = jointid;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate == null ? null : corporate.trim();
    }

    public Byte getMikeType() {
        return mikeType;
    }

    public void setMikeType(Byte mikeType) {
        this.mikeType = mikeType;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission == null ? null : commission.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getZoneid() {
        return zoneid;
    }

    public void setZoneid(Integer zoneid) {
        this.zoneid = zoneid;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }

    public Integer getIsverify() {
        return isverify;
    }

    public void setIsverify(Integer isverify) {
        this.isverify = isverify;
    }

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public Byte getCommStatus() {
        return commStatus;
    }

    public void setCommStatus(Byte commStatus) {
        this.commStatus = commStatus;
    }

    public Byte getEstimate() {
        return estimate;
    }

    public void setEstimate(Byte estimate) {
        this.estimate = estimate;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Double getFlatAgio() {
        return flatAgio;
    }

    public void setFlatAgio(Double flatAgio) {
        this.flatAgio = flatAgio;
    }

    public Byte getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Byte entryStatus) {
        this.entryStatus = entryStatus;
    }

    public BigDecimal getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }

    public BigDecimal getFlatMoney() {
        return flatMoney;
    }

    public void setFlatMoney(BigDecimal flatMoney) {
        this.flatMoney = flatMoney;
    }

    public BigDecimal getMaxHistory() {
        return maxHistory;
    }

    public void setMaxHistory(BigDecimal maxHistory) {
        this.maxHistory = maxHistory;
    }

    public BigDecimal getCuser() {
        return cuser;
    }

    public void setCuser(BigDecimal cuser) {
        this.cuser = cuser;
    }

    public BigDecimal getCdenom() {
        return cdenom;
    }

    public void setCdenom(BigDecimal cdenom) {
        this.cdenom = cdenom;
    }

    public String getThirdUid() {
        return thirdUid;
    }

    public void setThirdUid(String thirdUid) {
        this.thirdUid = thirdUid == null ? null : thirdUid.trim();
    }

    public Byte getNormal() {
        return normal;
    }

    public void setNormal(Byte normal) {
        this.normal = normal;
    }

    public Byte getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Byte isActivity) {
        this.isActivity = isActivity;
    }

    public BigDecimal getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(BigDecimal prizeNum) {
        this.prizeNum = prizeNum;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Byte getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Byte phoneType) {
        this.phoneType = phoneType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getAppSerial() {
        return appSerial;
    }

    public void setAppSerial(Integer appSerial) {
        this.appSerial = appSerial;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getRatioMoney() {
        return ratioMoney;
    }

    public void setRatioMoney(BigDecimal ratioMoney) {
        this.ratioMoney = ratioMoney;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Long getNoid() {
        return noid;
    }

    public void setNoid(Long noid) {
        this.noid = noid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Integer getXmerUid() {
        return xmerUid;
    }

    public void setXmerUid(Integer xmerUid) {
        this.xmerUid = xmerUid;
    }

    public Integer getMikeid() {
        return mikeid;
    }

    public void setMikeid(Integer mikeid) {
        this.mikeid = mikeid;
    }

    public String getMikename() {
        return mikename;
    }

    public void setMikename(String mikename) {
        this.mikename = mikename == null ? null : mikename.trim();
    }

	public Integer getOneLevelXmerId() {
		return oneLevelXmerId;
	}

	public void setOneLevelXmerId(Integer oneLevelXmerId) {
		this.oneLevelXmerId = oneLevelXmerId;
	}

	public Integer getTwoLevelXmerId() {
		return twoLevelXmerId;
	}

	public void setTwoLevelXmerId(Integer twoLevelXmerId) {
		this.twoLevelXmerId = twoLevelXmerId;
	}

	
	public String getOneLevelXmerName() {
		return oneLevelXmerName;
	}

	public void setOneLevelXmerName(String oneLevelXmerName) {
		this.oneLevelXmerName = oneLevelXmerName;
	}

	public String getTwoLevelXmerName() {
		return twoLevelXmerName;
	}

	public void setTwoLevelXmerName(String twoLevelXmerName) {
		this.twoLevelXmerName = twoLevelXmerName;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getActivityConent() {
		return activityConent;
	}

	public void setActivityConent(String activityConent) {
		this.activityConent = activityConent;
	}
	

	public BigDecimal getReduction() {
		return reduction;
	}

	public void setReduction(BigDecimal reduction) {
		this.reduction = reduction;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	
	public BigDecimal getLedgerAmount() {
		return ledgerAmount;
	}

	public void setLedgerAmount(BigDecimal ledgerAmount) {
		this.ledgerAmount = ledgerAmount;
	}
	
	public String getrPhone() {
		return rPhone;
	}

	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}

	public Integer getrUserId() {
		return rUserId;
	}

	public void setrUserId(Integer rUserId) {
		this.rUserId = rUserId;
	}

	
	public BigDecimal getFullReduction() {
		return fullReduction;
	}

	public void setFullReduction(BigDecimal fullReduction) {
		this.fullReduction = fullReduction;
	}

	public Integer getFullReductionId() {
		return fullReductionId;
	}

	public void setFullReductionId(Integer fullReductionId) {
		this.fullReductionId = fullReductionId;
	}

	public Integer getLedgerMode() {
		return ledgerMode==null?0:ledgerMode;
	}

	public void setLedgerMode(Integer ledgerMode) {
		this.ledgerMode = ledgerMode;
	}

	/**
	 * 计算应该赠送积分
	 * =订单总金额-立减-商家优惠券金额
	 * @Title: getCalculateRealZInteger 
	 * @Description:
	 */
	public BigDecimal getCalculateRealZInteger(){
//    	if(couponType!= null && (couponType==2||couponType==3)){
//    		return getZeroDecimal(getCalculateRealOrderAmount().subtract(cuser));
//    	}
//    	return getZeroDecimal(getCalculateRealOrderAmount());
		return BigDecimal.ZERO;	//2016年12月24日 新版规则不返积分
	}
	
	 /**
	 * 方法描述：计算脉宝参与分账的实际支付金额 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-12下午5:05:28 <br/>
	 * @return
	 */
	public BigDecimal getCalculateRealPayMent() {
		BigDecimal result=BigDecimal.ZERO;
		if(commision!=null){
			result=result.add(commision);
		}
		
		if(payment!=null){
			result=result.add(payment);
		}
		
		if(giveMoney!=null){
			result=result.add(giveMoney);
		}
		
		return result;
	}
	
	/**
	 * 计算应该参与分账的总金额
	 * =订单总金额-商家优惠券金额-商家自行满减金额
	 * @Title: getCalculateRealLedgerAmount 
	 * @Description:
	 */
	public BigDecimal getCalculateRealLedgerAmount(){
		BigDecimal allLdegerAmount = couponType==null?money
				:(couponType==2?money.subtract(cuser):money);
		allLdegerAmount = allLdegerAmount.subtract(fullReduction==null?BigDecimal.ZERO:fullReduction);
    	return  getZeroDecimal(allLdegerAmount);
	}
	
	/**
	 * 计算订单的实际金额
	 * =订单总金额-免减金额
	 * @Title: getCalculateRealOrderAmount 
	 * @Description:
	 */
	public BigDecimal getCalculateRealOrderAmount(){
    	//订单总金额-立减金额
		BigDecimal allReduction = BigDecimal.ZERO;
		allReduction=reduction==null?allReduction:allReduction.add(reduction);
		allReduction=fullReduction==null?allReduction:allReduction.add(fullReduction);
    	return getZeroDecimal(money.subtract(allReduction));
	}
	
	/**
	 * 计算实付金额:
	 * 用户实际支付金额（第三方+钱包）
     * =订单总额-实际优惠金额
	 * @Title: getCalculateRealPayAmount 
	 * @Description:
	 */
	public BigDecimal getCalculateRealPayAmount(){
    	return getZeroDecimal(money.subtract(getCalculateRealPreferentialAmount()));
	}
	
	/**
	 * 计算实付第三方金额:
	 * 用户实际支付金额（第三方+钱包）
     * =订单总额-实际优惠金额
	 * @Title: getCalculateRealPayAmount 
	 * @Description:
	 */
	public BigDecimal getCalculateThirdAmount(){
    	return getZeroDecimal(getCalculateRealPayAmount().subtract(profit).subtract(commision).subtract(giveMoney).subtract(liveCoinMoney==null?BigDecimal.ZERO:liveCoinMoney));
	}
	
	/**
	 * 计算订单实际优惠金额:
     * =平台立减+全体优惠券+商家立减
	 * @Title: getCalculateRealPayAmount 
	 * @Description:
	 */
	public BigDecimal getCalculateRealPreferentialAmount(){
    	BigDecimal realPreferentialAmount = BigDecimal.ZERO;
    	realPreferentialAmount=reduction==null?realPreferentialAmount:realPreferentialAmount.add(reduction);
    	realPreferentialAmount=fullReduction==null?realPreferentialAmount:realPreferentialAmount.add(fullReduction);
    	realPreferentialAmount = cuser==null?realPreferentialAmount:realPreferentialAmount.add(cuser);
    	return getZeroDecimal(realPreferentialAmount);
	}
	
	/*
	 * 保存订单数值为正数
	 */
	private BigDecimal getZeroDecimal(BigDecimal data){
		return data.compareTo(BigDecimal.ZERO)>0?data:BigDecimal.ZERO;
	}

	public Integer getCannelStatus() {
		return cannelStatus;
	}

	public void setCannelStatus(Integer cannelStatus) {
		this.cannelStatus = cannelStatus;
	}

	
	public Integer getLiveLedger() {
		return liveLedger;
	}

	public void setLiveLedger(Integer liveLedger) {
		this.liveLedger = liveLedger;
	}

	public Integer getLiveLedgerStyle() {
		return liveLedgerStyle;
	}

	public void setLiveLedgerStyle(Integer liveLedgerStyle) {
		this.liveLedgerStyle = liveLedgerStyle;
	}

	public Integer getLiveLedgerMode() {
		return liveLedgerMode;
	}

	public void setLiveLedgerMode(Integer liveLedgerMode) {
		this.liveLedgerMode = liveLedgerMode;
	}

	public Double getLiveLedgerRatio() {
		return liveLedgerRatio;
	}

	public void setLiveLedgerRatio(Double liveLedgerRatio) {
		this.liveLedgerRatio = liveLedgerRatio;
	}

	
	public BigDecimal getLiveCoin() {
		return liveCoin;
	}

	public void setLiveCoin(BigDecimal liveCoin) {
		this.liveCoin = liveCoin;
	}

	public Double getLiveCoinRatio() {
		return liveCoinRatio;
	}

	public void setLiveCoinRatio(Double liveCoinRatio) {
		this.liveCoinRatio = liveCoinRatio;
	}

	public BigDecimal getLiveCoinMoney() {
		return liveCoinMoney;
	}

	public void setLiveCoinMoney(BigDecimal liveCoinMoney) {
		this.liveCoinMoney = liveCoinMoney;
	}

	public Double getFeeRatio() {
		return feeRatio==null?0:feeRatio;
	}

	public void setFeeRatio(Double feeRatio) {
		this.feeRatio = feeRatio;
	}

	
	public BigDecimal getSellerCoin() {
		return sellerCoin;
	}

	public void setSellerCoin(BigDecimal sellerCoin) {
		this.sellerCoin = sellerCoin;
	}

	
	/**
	 * @return the uidMbEcno
	 */
	public String getUidMbEcno() {
		return uidMbEcno;
	}

	/**
	 * @param uidMbEcno the uidMbEcno to set
	 */
	public void setUidMbEcno(String uidMbEcno) {
		this.uidMbEcno = uidMbEcno;
	}

	/**
	 * @return the saasChannel
	 */
	public Integer getSaasChannel() {
		return saasChannel;
	}

	/**
	 * @param saasChannel the saasChannel to set
	 */
	public void setSaasChannel(Integer saasChannel) {
		this.saasChannel = saasChannel;
	}
	
	

	/**
	 * @return the realPayMent
	 */
	public BigDecimal getRealPayMent() {
		return realPayMent;
	}

	/**
	 * @param realPayMent the realPayMent to set
	 */
	public void setRealPayMent(BigDecimal realPayMent) {
		this.realPayMent = realPayMent;
	}

	@Override
	public String toString() {
		return "BillBean [bid=" + bid + ", codeid=" + codeid + ", sellerid="
				+ sellerid + ", sellername=" + sellername + ", consumeJointid="
				+ consumeJointid + ", nname=" + nname + ", money=" + money
				+ ", profit=" + profit + ", commision=" + commision
				+ ", payment=" + payment + ", rebate=" + rebate + ", aid="
				+ aid + ", fullname=" + fullname + ", phoneid=" + phoneid
				+ ", bfirst=" + bfirst + ", status=" + status + ", sdate="
				+ sdate + ", zdate=" + zdate + ", ydate=" + ydate + ", number="
				+ number + ", paytype=" + paytype + ", isaccount=" + isaccount
				+ ", baseagio=" + baseagio + ", type=" + type + ", hstatus="
				+ hstatus + ", consumeCorporate=" + consumeCorporate
				+ ", genussellerid=" + genussellerid + ", genusname="
				+ genusname + ", jointid=" + jointid + ", corporate="
				+ corporate + ", mikeType=" + mikeType + ", uid=" + uid
				+ ", commission=" + commission + ", area=" + area + ", zoneid="
				+ zoneid + ", payid=" + payid + ", isverify=" + isverify
				+ ", ldate=" + ldate + ", fdate=" + fdate + ", edate=" + edate
				+ ", commStatus=" + commStatus + ", estimate=" + estimate
				+ ", isVirtual=" + isVirtual + ", flatAgio=" + flatAgio
				+ ", entryStatus=" + entryStatus + ", giveMoney=" + giveMoney
				+ ", flatMoney=" + flatMoney + ", maxHistory=" + maxHistory
				+ ", cuser=" + cuser + ", cdenom=" + cdenom + ", thirdUid="
				+ thirdUid + ", normal=" + normal + ", isActivity="
				+ isActivity + ", prizeNum=" + prizeNum + ", source=" + source
				+ ", phoneType=" + phoneType + ", version=" + version
				+ ", versionId=" + versionId + ", appSerial=" + appSerial
				+ ", imei=" + imei + ", ratio=" + ratio + ", ratioMoney="
				+ ratioMoney + ", orderSource=" + orderSource + ", noid="
				+ noid + ", amount=" + amount + ", integral=" + integral
				+ ", xmerUid=" + xmerUid + ", mikeid=" + mikeid + ", mikename="
				+ mikename + ", reduction=" + reduction + ", couponType="
				+ couponType + ", activityConent=" + activityConent
				+ ", rPhone=" + rPhone + ", rUserId=" + rUserId
				+ ", fullReduction=" + fullReduction + ", fullReductionId="
				+ fullReductionId + ", ledgerMode=" + ledgerMode
				+ ", liveLedger=" + liveLedger + ", liveLedgerStyle="
				+ liveLedgerStyle + ", liveLedgerMode=" + liveLedgerMode
				+ ", liveLedgerRatio=" + liveLedgerRatio + ", oneLevelXmerId="
				+ oneLevelXmerId + ", twoLevelXmerId=" + twoLevelXmerId
				+ ", oneLevelXmerName=" + oneLevelXmerName
				+ ", twoLevelXmerName=" + twoLevelXmerName + ", proportion="
				+ proportion + ", ledgerAmount=" + ledgerAmount
				+ ", cannelStatus=" + cannelStatus + ", liveCoin=" + liveCoin
				+ ", liveCoinMoney=" + liveCoinMoney + ", liveCoinRatio="
				+ liveCoinRatio + "]";
	}


}