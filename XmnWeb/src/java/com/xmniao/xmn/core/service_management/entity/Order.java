package com.xmniao.xmn.core.service_management.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.SystemConstants;

public class Order extends BaseEntity {

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
	
	private Double moneyStart;//搜索查询条件
	private Double moneyEnd;//搜索查询条件
	private Double give_money;
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
	public Order() {
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
		this.phoneid = phoneid;
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
		return baseagio;
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


	public String getMikeAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("mike_amount");
		}
		return str;
	}
	public String getPlatformAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("platform_amount");
		}
		return str;
	}
	public String getBpartnerAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			String strBAmount=json.getString("bpartner_amount");
			String strCAmount=json.getString("cpartner_amount");
			//合作商佣金=所属合作商佣金+消费合作商佣金
			 BigDecimal bAmount = new BigDecimal(strBAmount); 
			 BigDecimal cAmount = new BigDecimal(strCAmount); 
			 BigDecimal totalAmount= bAmount.add(cAmount);
			 str=totalAmount.toString();
		}
		return str;
	}
	public String getSellerAmount(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("seller_amount");
		}
		return str;
	}
	
	public String getFeesMoney(){
		String str="";
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("fees_money");
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

}
