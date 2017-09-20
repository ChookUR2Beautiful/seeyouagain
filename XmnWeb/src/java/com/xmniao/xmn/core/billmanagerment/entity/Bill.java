/**   
 * 文件名：Bill.java   
 *    
 * 日期：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.billmanagerment.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.SystemConstants;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：Bill
 * 
 * 类描述：订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class Bill extends BaseEntity {

	private static final long serialVersionUID = -7035957035511214995L;
	private Long bid;
	private String stringBid;
	private Long codeid;
	private Integer sellerid;
	private String sellername;//消费商家名称
	private Integer consumejointid;
	private String consumecorporate;//消费商家所属合作商名称
	private Integer genussellerid;
	private String genusname;//所属商家名称
	private Integer jointid;
	private String corporate;//所属商家所属合作商名称
	private Integer miketype;
	private Integer uid;
	private String nname;
	private Double money;
	private Double sumMoney;//用户累计消费总金额
	private Double totalAmount;//订单总额（除待支付）
	private Double notpaytotalAmount;//订单总额（除待支付）
	private Double profit;
	private Double commision;
	private Double payment;
	private Double rebate;
	private Integer aid;
	private String fullname;
	private String phoneid;
	private Integer status;
	private int strStatus[];
	private Date sdate;
	private Date edate;//退款成功时间（非业务系统更新，由支付系统更新）
	private Date sdateStart;
	private Date sdateEnd;
	private Date zdateStart;
	private Date zdateEnd;
	private Date zdate;//下单时间
	private Date ydate;
	private String number;
	private String paytype;
	private Integer isaccount;
	private Double baseagio;
	private Integer hstatus;
	private String commission;
	private Integer type;
	private String area;
	private Integer isVirtual;//是否虚拟订单，默认为0 不是虚拟订单   1虚拟订单',
	private String payid; //(流水号)'支付ID，支付接口产生 用于查询支付记录使用',
	
	private Integer taareaid;//区域id
	private String tatitle;//区域名
	private Integer tcareaid;
	private String tctitle;
	private Integer tpareaid;
	private String tptitle;
	private String cityName;
	private String areaName;
	private String cityArea;
	private String city;
	private String province;
	private Integer isverify;//是否验证消费码 0 未验证 1已验证
	private String flag;//all 表示查询所有订单信息
	private String isFirst;//是否查询首单
	
	private String thirdUidSwitch;//刷单sql查询标志
	private String thirdUid;//第三方支付帐号
	
	
	/**
	 * 是否参与活动字段
	 */
	private Integer isActivity;  //是否参与活动   0：未参与    1：参与
	private String activityContent;   //参与活动的描述

	
	/**
	 * 爆品字段
	 */
	
//去除字段爆品字段 add by hls
//	private String pname;//爆品名称
//	private BigDecimal price;//爆品价格
//	private Integer num;//爆品数量
//	
//	private Integer isBargain; //是否爆品  0不是 1 是
//	private String isBargainText;
	
	private Double moneyStart;//搜索查询条件
	private Double moneyEnd;//搜索查询条件
	private Double give_money;
	private Double flatAgio;//平台补贴占比 默认:0 平台做促销活动时此字段表示平台给用户的折扣补贴
	private BigDecimal flatMoney;//平台补贴金额
	DecimalFormat    def   = new DecimalFormat("######0"); 
	private Integer queryFlag;//查询标示
	private Double cuser;//优惠券支付总金额
	private Double cdenom;//优惠卷面额总数
	private String activityRest;//派奖结果描述
	private Integer zoneid;//商圈id
	private String zoneName;//商圈name
	private Integer normal;//是否为刷单   0未判断   1正常订单    2异常订单
	private String normalText;//是否为刷单   0未判断   1正常订单    2异常订单
	
	private String fakeTotalNumber;
	private String aidFullname;//推广服务员姓名 2016年4月19日 ChenBo
	private String aidAccount;//推广服务员账号 2016年4月19日 ChenBo
	private Integer aidUid;	  //推广服务员寻蜜鸟用户端APP账号 2016年4月20日 ChenBo
	//新增字段
	private Integer orderSource; //默认为0     1票量网 
	private Long noId; //使用会员卡支付时需填此编号
	private Double amount; //会员卡支付金额，商家会员卡可以和钱包余额组合支付
	private Double integral;//本单可返积分
	
	/**
	 * 新增字段 
	 * lifeng
	 * 2016年05月27日下午15:08:20
	 */
	private Long xmerID;//寻蜜客ID
	private String xmerName;//寻蜜客姓名
	private String xmerPhoneNo;//寻蜜客手机号
	private Integer xmerUid;//寻蜜客会员编号（uid）
	/**
	 * 新增字段 
	 * hls
	 * 2016年07月05日下午11:18:20
	 */
	private Double reduction;//减免金额
	
	private Integer ledgerMode;	//订单分账模式
	private Integer fullReductionId;
	private BigDecimal fullReduction;
	private Integer rUserId;
	private String rPhone;
	private Integer couponType;
	
	private Integer liveLedger;
	private Integer liveLedgerStyle;
	private Integer liveLedgerMode;
	private Double liveLedgerRatio;
	
	private Double walletAmount;	//钱包金额支付部分(不含积分)
	
	/**
	 * 新增字段
	 * cj
	 * 2016年12月20日下午2:37:25
	 */
	private BigDecimal liveCoin;//鸟币支付金额
	
	private Double liveCoinRatio;//鸟币抵用比
	
	private Integer liveLedgerStatus;//直播分账状态
	
	private BigDecimal sellerCoin;//商家专享鸟币支付金额
	
	private BigDecimal sellerTotalProfit;  //流水累计收益
	
	private BigDecimal fromSellerProfit;  //获得商户收益
	
	private Integer saasChannel;  //寻蜜客签约商家SAAS来源  null为非SAAS签约商户 1 常规SAAS签约 2脉客SAAS签约 3V客SAAS签约 4主播(V客赠送)SAAS签约
	
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

	public BigDecimal getSellerCoin() {
		return sellerCoin;
	}

	public void setSellerCoin(BigDecimal sellerCoin) {
		this.sellerCoin = sellerCoin;
	}
	
	public Integer getSaasChannel() {
		return saasChannel;
	}

	public void setSaasChannel(Integer saasChannel) {
		this.saasChannel = saasChannel;
	}

	public BigDecimal getSellerTotalProfit() {
		return sellerTotalProfit;
	}

	public void setSellerTotalProfit(BigDecimal sellerTotalProfit) {
		this.sellerTotalProfit = sellerTotalProfit;
	}

	public BigDecimal getFromSellerProfit() {
		return fromSellerProfit;
	}

	public void setFromSellerProfit(BigDecimal fromSellerProfit) {
		this.fromSellerProfit = fromSellerProfit;
	}

	public Double getReduction() {
		Double r = (double) 0;
		if(reduction == null || "".equals(reduction)){
			reduction = r;
		}
		return reduction;
	}



	public void setReduction(Double reduction) {
		this.reduction = reduction;
	}

	public String getNormalText() {
		if(normal==null) return "-";
		if(normal==0) return "未判断";
		if(normal==1) return "正常订单";
		if(normal==2) return "异常订单";
		return "-";
	}

	public Integer getNormal() {
		return normal;
	}

	public void setNormal(Integer normal) {
		this.normal = normal;
	}
	
	public String getFakeTotalNumber() {
		return fakeTotalNumber;
	}

	public void setFakeTotalNumber(String fakeTotalNumber) {
		this.fakeTotalNumber = fakeTotalNumber;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public String getActivityRest() {
		return activityRest;
	}

	public void setActivityRest(String activityRest) {
		this.activityRest = activityRest;
	}

	public String getThirdUidSwitch() {
		return thirdUidSwitch;
	}

	public void setThirdUidSwitch(String thirdUidSwitch) {
		this.thirdUidSwitch = thirdUidSwitch;
	}

	public String getThirdUid() {
		return thirdUid;
	}

	public void setThirdUid(String thirdUid) {
		this.thirdUid = thirdUid;
	}

	public Double getCuser() {
		return cuser;
	}

	public void setCuser(Double cuser) {
		this.cuser = cuser;
	}

	public Double getCdenom() {
		return cdenom;
	}

	public void setCdenom(Double cdenom) {
		this.cdenom = cdenom;
	}

	public Integer getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(Integer queryFlag) {
		this.queryFlag = queryFlag;
	}

	public BigDecimal getFlatMoney() {
		return flatMoney;
	}

	public void setFlatMoney(BigDecimal flatMoney) {
		this.flatMoney = flatMoney;
	}

	public String getFlatAgio() {
		if(flatAgio != null&&flatAgio!=0) {
			DecimalFormat  dft  = new DecimalFormat("######0.00"); 
			return dft.format(new BigDecimal(flatAgio).multiply(new BigDecimal(100)));
		}
		return "0";
	}
	public void setFlatAgio(Double flatAgio) {
		this.flatAgio = flatAgio;
	}
	

	public int[] getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(int[] strStatus) {
		this.strStatus = strStatus;
	}

	public Date getZdateStart() {
		return zdateStart;
	}

	public void setZdateStart(Date zdateStart) {
		this.zdateStart = zdateStart;
	}

	public Date getZdateEnd() {
		return zdateEnd;
	}

	public void setZdateEnd(Date zdateEnd) {
		this.zdateEnd = zdateEnd;
	}

	/**
	 * 创建一个新的实例 Bill.
	 * 
	 */
	public Bill() {
		super();
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
   
	
	public String getStringBid() {
		return stringBid;
	}

	public void setStringBid(String stringBid) {
		this.stringBid = stringBid;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}


	public Long getBid() {
		return bid;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getIsFirst() {
		return isFirst;
	}
	public String getIsFirstName() {
		String str="否";
		if(this.isFirst!=null&&this.isFirst.equals("1")){
			str="是";
		}
		return str;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}


	public Long getCodeid() {
		return codeid;
	}


	public void setCodeid(Long codeid) {
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
		this.sellername = sellername;
	}

	public Integer getConsumejointid() {
		return consumejointid;
	}
	
	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}

	public void setConsumejointid(Integer consumejointid) {
		this.consumejointid = consumejointid;
	}

	public String getConsumecorporate() {
		return consumecorporate;
	}

	public void setConsumecorporate(String consumecorporate) {
		this.consumecorporate = consumecorporate;
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
		this.genusname = genusname;
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
		this.corporate = corporate;
	}

	public Integer getMiketype() {
		return miketype;
	}

	public void setMiketype(Integer miketype) {
		this.miketype = miketype;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public Double getMoney() {
		Double m = (double) 0;
		if(money == null || "".equals(money)){
			money = m;
		}
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}
	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
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
		this.fullname = fullname;
	}

	public String getPhoneid() {
		return phoneid;
	}


	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid.trim();
	}


	public Integer getStatus() {
		return status;
	}


	public String getStatusText(){
		if(status == null) return "";
		switch (status) {
		case 0:
			return "待支付";
		case 1:
			return "待返现";
		case 2:
			return "已返现";
		case 3:
			return "已消费";
		case 4:
			return "退款中";
		case 5:
			return "已退款";
		case 6:
			return "待返现【取消退款】";
		case 7:
			return "退款中【已申诉】";
		case 8:
			return "待返现【驳回申请退款】";
		case 9:
			return "返利中";
		case 10:
			return "商家申诉失败";
		case 11:
			return "平台退款处理中";
		case 12:
			return "平台退款失败";
		case 13:
			return "商家同意退款";
		case 14:
			return "平台同意退款";
		case 15:
			return "刷单退款";
		default:
			return "";
		}
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
		return zdate;
	}

	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
		this.number = number;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public Integer getIsaccount() {
		return isaccount;
	}

	public void setIsaccount(Integer isaccount) {
		this.isaccount = isaccount;
	}

	public Double getBaseagio() {
		if(baseagio != null) {
			return Double.parseDouble(def.format(baseagio*100));
		}
			return 0.00;
	}
	public void setBaseagio(Double baseagio) {
		this.baseagio = baseagio;
	}

	public Integer getHstatus() {
		return hstatus;
	}
	public String getHstatusText(){
		if(hstatus == null) return "";
		switch (hstatus) {
		case 0:
			return "未分账";
		case 1:
			return "结算通过";
		case 2:
			return "结算未通过";
		case 3:
			return "结算争议";
		case 4:
			return "核算通过";
		case 5:
			return "核算不通过";
		case 6:
			return "核算争议";
		case 7:
			return "财务通过";
		case 8:
			return "财务不通过";
		case 9:
			return "已分账";
		case 10:
			return "分账处理中【触发自动分账】";
		case 11:
			return "分账处理中【自动转手动处理中】";
		default:
			return "";
		}
	}
	public String getMiketypeText(){
		if(miketype == null) return "";
		switch (miketype) {
		case 0:
			return "未绑定任何寻蜜客";
		case 1:
			return "用户寻蜜客";
		case 2:
			return "商家寻蜜客";
		case 3:
			return "用户寻蜜客(过期)";
		default:
			return "";
		}
	}
	public String getPayTypeText(){
		return SystemConstants.getPayTypeText(paytype);
	}
	
	public String getOrderDateStr(){
		String str ="";
		if(this.sdate  != null){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			 str=df.format(this.sdate);
		}
		return str;
	}
	public String getZdateStr(){
		String str ="";
		if(this.zdate  != null){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			 str=df.format(this.zdate);
		}
		return str;
	}
	public void setHstatus(Integer hstatus) {
		this.hstatus = hstatus;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 解析美食订单（t_bill）分账明细 add by hls
	 */
	//商户营收金额seller_amount
	public String getSellerAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("seller_amount");
		}
		return str;
	}
	//商户店外收益seller_money
	public String getSellermoney(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("seller_money");
		}
		return str;
	}
	//用户返利积分 userMoney
	public String getUserMoney(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("userMoney");
		}
		return str;
	}
	//寻蜜客应收分账金额mike_amount
	public String getMikeAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("mike_amount");
		}
		return str;
	}
	//寻蜜客上级分账金额parent_mike_amount
	public String getParentMikeAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("parent_mike_amount");
		}
		return str;
	}
	//寻蜜客上上级分账金额top_mike_amount
	public String getTopMikeAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("top_mike_amount");
		}
		return str;
	}
	//经销商分账金额cpartner_amount
	public String getCpartnerAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("cpartner_amount");
		}
		return str;
	}
	//商户店外收益比例 joint_bpartner
	public String getJointBpartner(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("joint_bpartner");
		}
		return str;
	}
	//平台应收分账金额platform_amount
	public String getPlatformAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("platform_amount");
		}
		return str;
	}
	//中脉分账金额other_amount
	public String getOtherAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("other_amount");
		}
		return str;
	}

	public Integer getIsVirtual() {
		return isVirtual;
	}


	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}


	public Integer getTaareaid() {
		return taareaid;
	}


	public void setTaareaid(Integer taareaid) {
		this.taareaid = taareaid;
	}


	public String getTatitle() {
		return tatitle;
	}


	public void setTatitle(String tatitle) {
		this.tatitle = tatitle;
	}


	public Integer getTcareaid() {
		return tcareaid;
	}


	public void setTcareaid(Integer tcareaid) {
		this.tcareaid = tcareaid;
	}


	public String getTctitle() {
		return tctitle;
	}


	public void setTctitle(String tctitle) {
		this.tctitle = tctitle;
	}


	public Integer getTpareaid() {
		return tpareaid;
	}


	public void setTpareaid(Integer tpareaid) {
		this.tpareaid = tpareaid;
	}


	public String getTptitle() {
		return tptitle;
	}


	public void setTptitle(String tptitle) {
		this.tptitle = tptitle;
	}
    

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityArea() {
		if(this.areaName==null){
			return null;
		}
		return this.cityName+"-"+this.areaName;
	}

	public void setCityArea(String cityArea) {
		this.cityArea = cityArea;
	}
    
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Double getNotpaytotalAmount() {
		return notpaytotalAmount;
	}


	public void setNotpaytotalAmount(Double notpaytotalAmount) {
		this.notpaytotalAmount = notpaytotalAmount;
	}


	public Integer getIsverify() {
		return isverify;
	}


	public void setIsverify(Integer isverify) {
		this.isverify = isverify;
	}
	
	public String getIsverifyText(){
		if(isverify == null) return "";
		switch (isverify) {//是否验证消费码 0 未验证 1已验证
		case 0:
			return "未验证";
		case 1:
			return "已验证";		
		default:
			return "";
		}
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public Double getMoneyStart() {
		return moneyStart;
	}

	public void setMoneyStart(Double moneyStart) {
		this.moneyStart = moneyStart;
	}

	public Double getMoneyEnd() {
		return moneyEnd;
	}

	public void setMoneyEnd(Double moneyEnd) {
		this.moneyEnd = moneyEnd;
	}
	
	public Double getGive_money() {
		return give_money;
	}

	public void setGive_money(Double give_money) {
		this.give_money = give_money;
	}

	public Integer getIsActivity() {
		return isActivity;
	}

	public String getIsActivityName() {
		String str="-";
		if(this.isActivity == null){
			
		}else{
		if(this.isActivity == 0){
			str="未派";
		}
		if(this.isActivity == 1){
			str="已派";
		}
		if(this.isActivity == 2){
			str="不用派";
		}}
		return str;
	}
	
	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}


	public Long getNoId() {
		return noId;
	}

	public void setNoId(Long noId) {
		this.noId = noId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public String getAidFullname() {
		return aidFullname;
	}

	public void setAidFullname(String aidFullname) {
		this.aidFullname = aidFullname;
	}

	public String getAidAccount() {
		return aidAccount;
	}

	public void setAidAccount(String aidAccount) {
		this.aidAccount = aidAccount;
	}

	public Integer getAidUid() {
		return aidUid;
	}

	public void setAidUid(Integer aidUid) {
		this.aidUid = aidUid;
	}

	/**
	 * @return the sumMoney
	 */
	public Double getSumMoney() {
		return sumMoney;
	}

	/**
	 * @param sumMoney the sumMoney to set
	 */
	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public Long getXmerID() {
		return xmerID;
	}

	public void setXmerID(Long xmerID) {
		this.xmerID = xmerID;
	}

	public String getXmerPhoneNo() {
		return xmerPhoneNo;
	}

	public void setXmerPhoneNo(String xmerPhoneNo) {
		this.xmerPhoneNo = xmerPhoneNo;
	}

	public String getXmerName() {
		return xmerName;
	}

	public void setXmerName(String xmerName) {
		this.xmerName = xmerName;
	}

	public Integer getXmerUid() {
		return xmerUid;
	}

	public void setXmerUid(Integer xmerUid) {
		this.xmerUid = xmerUid;
	}



	public Integer getLedgerMode() {
		return ledgerMode;
	}



	public void setLedgerMode(Integer ledgerMode) {
		this.ledgerMode = ledgerMode;
	}



	public Integer getFullReductionId() {
		return fullReductionId;
	}



	public void setFullReductionId(Integer fullReductionId) {
		this.fullReductionId = fullReductionId;
	}



	public BigDecimal getFullReduction() {
		return fullReduction;
	}



	public void setFullReduction(BigDecimal fullReduction) {
		this.fullReduction = fullReduction;
	}



	public Integer getrUserId() {
		return rUserId;
	}



	public void setrUserId(Integer rUserId) {
		this.rUserId = rUserId;
	}



	public String getRPhone() {
		return rPhone;
	}



	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}



	public Integer getCouponType() {
		return couponType;
	}



	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	
	public Double getWalletAmount(){
		walletAmount = (this.getProfit()==null?0:this.getProfit())+(this.getCommision()==null?0:this.getCommision())+(this.getGive_money()==null?0:this.getGive_money());
		return walletAmount;
	}
	
	public String getLedgerModeStr(){
		if(this.ledgerMode==null){
			return "-";
		}
		else if(this.ledgerMode==0){
			return "正常分账模式";
		}else if(this.ledgerMode==1){
			return "不参与分账模式";
		}else if(this.ledgerMode==2){
			return "仅商家参与分账模式";
		}else{
			return "-";
		}
	}
	
	public String getCouponTypeStr(){
		if(couponType==null){
			return "-";
		}
		else if(1 == couponType){
			return "消费优惠劵";
		}else if(2 == couponType){
			return "商家券";
		}else if(3 == couponType){
			return "粉丝券";
		}
		
		return "-";
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
	
	public String getLiveLedgerRatioStr() {
		if(this.liveLedgerRatio!=null){
			return liveLedgerRatio*100+"%";
		}
		return "-";
	}
	public String getLiveLedgerModeStr() {
		if(this.liveLedgerMode!=null){
			if(this.liveLedgerMode==1){
				return "全额分账";
			}else if(this.liveLedgerMode==2){
				return "仅对粉丝券分账";
			}
		}
		return "-";
	}
	public String getLiveLedgerStyleStr() {
		if(this.liveLedgerStyle!=null){
			if(liveLedgerStyle==0){
				return "自动分账";
			}else if(liveLedgerStyle==1){
				return "手动分账";
			}
		}
		return "-";
	}
	public String getLiveLedgerStr() {
		if(liveLedger!=null){
			if(liveLedger==0){
				return "关闭";
			}else if(liveLedger==1){
				return "开启";
			}
		}
		return "-";
	}

	/**
	 * 列表展示，该行是否禁止复选框可选
	 */
	public boolean isDisableCheck() {
		if(liveLedger!=null && liveLedgerStyle!=null && liveLedger==1 && liveLedgerStyle==1){
			if(status==null){
			}
			else if(status==6||status==8||status==10
			|| status==1||status==3){
				if(this.isverify==null || this.isverify==0){
					return false;
				}
			}
		}
		return true;
	}

	public Integer getLiveLedgerStatus() {
		return liveLedgerStatus;
	}

	public void setLiveLedgerStatus(Integer liveLedgerStatus) {
		this.liveLedgerStatus = liveLedgerStatus;
	}
	
	
}
