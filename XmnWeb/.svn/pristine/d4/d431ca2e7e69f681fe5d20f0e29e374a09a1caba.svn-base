/**   
 * 文件名：Refund.java   
 *    
 * 日期：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.billmanagerment.entity;

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
 * 类名称：Refund
 * 
 * 类描述：订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class Refund extends BaseEntity {

	private static final long serialVersionUID = 4364092189325331321L;
	
	private Integer id;
	private Long bid;
	private Integer sellerid;
	private String sellername;
	private String apply;
	private String cancel;
	private String qppeal;
	private String processing;
	private Integer status;
	private Integer billStatus;
	private Integer billSource;
	private String strStatus;
	private int[]  intstatus;
	private Double profit;//返利支付金额
	private Double giveMoney;//赠送支付金额
	private Double cuser;//优惠券支付总金额
	private Double commision;//佣金支付金额
	private Double payment;//需支付金额
	private Double flatAgio;//平台补贴占比
	private Double flatMoney;//平台补贴金额
	private Integer isactivity;//是否参与活动
	private String activityConent;//活动描述
	private Date sdate;
	private Date qdate;//申诉处理时间
	private Date cdate;
	private Date zdate;//支付时间
	private Date zdateStart;
	private Date zdateEnd;
	private Date adate;
	private String remarks;
	private Date sdateStart;
	private Date sdateEnd;
	private Date quitDate;//退款时间
	private Date quitDateStart;
	private Date quitDateEnd;
	private Double money;
	private String codeid;
	private String nname;
	private String phoneid;
	private Date orderDate;
	private String paytype;//支付类型
	private String payid; //(流水号)'支付ID，支付接口产生 用于查询支付记录使用',
	private String isPresidentHandle;//总裁办标志字符串
	private Date pdate;//总裁办处理时间
	private String activityRest;//派奖结果描述
	
	/**
	 * 新增字段 
	 * hls
	 * 2016年07月05日下午11:18:20
	 */
	private Double reduction;//减免金额
	private Double refuMoney;//退款金额 = 订单金额 - 减免金额

	public Double getRefuMoney() {
		BigDecimal b1 = new BigDecimal(getMoney().toString());
		BigDecimal b2 = new BigDecimal(getReduction().toString());
		refuMoney = b1.subtract(b2).doubleValue();
		return refuMoney;
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



	public void setRefuMoney(Double refuMoney) {
		this.refuMoney = refuMoney;
	}
    
	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}
	
	public Integer getBillSource() {
		return billSource;
	}

	public void setBillSource(Integer billSource) {
		this.billSource = billSource;
	}

	public String getActivityRest() {
		return activityRest;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
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

	public Double getFlatMoney() {
		return flatMoney;
	}

	public void setFlatMoney(Double flatMoney) {
		this.flatMoney = flatMoney;
	}

	public Integer getIsactivity() {
		return isactivity;
	}

	public void setIsactivity(Integer isactivity) {
		this.isactivity = isactivity;
	}


	public String getActivityText(){
		if(null==isactivity){
			return "-";
		}
		switch (isactivity) {
		case 0:
			return "未派";
		case 1:
			return "已派";
		case 2:
			return "不用派";
		default:
			return "-";
		}
	}
	public String getActivityConent() {
		return activityConent;
	}

	public void setActivityConent(String activityConent) {
		this.activityConent = activityConent;
	}

	public void setActivityRest(String activityRest) {
		this.activityRest = activityRest;
	}
	
	public int[] getIntstatus() {
		return intstatus;
	}

	public void setIntstatus(int[] intstatus) {
		this.intstatus = intstatus;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getIsPresidentHandle() {
		return isPresidentHandle;
	}

	public void setIsPresidentHandle(String isPresidentHandle) {
		this.isPresidentHandle = isPresidentHandle;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
		return zdate;
	}

	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}

	/**
	 * 创建一个新的实例 Refund.
	 * 
	 */
	public Refund() {
		super();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	

	public Date getQuitDateStart() {
		return quitDateStart;
	}

	public void setQuitDateStart(Date quitDateStart) {
		this.quitDateStart = quitDateStart;
	}

	public Date getQuitDateEnd() {
		return quitDateEnd;
	}

	public void setQuitDateEnd(Date quitDateEnd) {
		this.quitDateEnd = quitDateEnd;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
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

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public String getQppeal() {
		return qppeal;
	}

	public void setQppeal(String qppeal) {
		this.qppeal = qppeal;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public Integer getStatus() {
		return status;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getAdate() {
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public String getStatusText(){
		if(status == null) return "";
		switch (status) {
		case 0:
			return "退款待处理";
		case 1:
			return "商家同意退款";
		case 2:
			return "用户取消退款";
		case 3:
			return "商家申诉中";
		case 5:
			return "驳回退款申请";
		case 6:
			return "传送给总裁办";
		case 7:
			return "商家申诉失败";			
		case 8:
			return "平台退款处理中";
		case 9:
			return "平台退款成功";
		case 10:
			return "平台退款失败";
		case 11:
			return "平台同意退款";
		case 12:
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
	public Date getQdate() {
		return qdate;
	}

	public void setQdate(Date qdate) {
		this.qdate = qdate;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}
	
	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	public String getPayTypeText(){
		return SystemConstants.getPayTypeText(paytype);
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getZdateStr(){
		String str ="";
		if(this.zdate  != null){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			 str=df.format(this.zdate);
		}
		return str;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}


	public Double getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(Double giveMoney) {
		this.giveMoney = giveMoney;
	}

	public Double getCuser() {
		return cuser;
	}

	public void setCuser(Double cuser) {
		this.cuser = cuser;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}
}
