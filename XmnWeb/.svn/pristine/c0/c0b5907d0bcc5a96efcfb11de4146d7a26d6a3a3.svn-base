/**   
 * 文件名：BillRecord.java   
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
import java.util.Date;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BillRecord
 * 
 * 类描述：订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BillRecord extends BaseEntity {

	
	private static final long serialVersionUID = -2250620358288132803L;
	private Integer id;
	private Long bid;
	private Integer status;
	private Integer aprearStatus;
	private String explains;
	private Date cdate;//处理时间
	private String remarks;
	private String nname;
	private Double money;
	private Long codeid;
	private String sellername;
	private Date orderDate;//下单时间
	private Date refundDate;//申请退款时间
	private Date qdate;//申请处理时间
	private Date qdateStart;//申请处理开始时间
	private Date qdateEnd;//申请处理结束时间
	private String cancel;//不退款原因
	private String processing;//申诉处理结果
	private String qppeal; 
	private String paytype;//支付类型
	private Date adate;//申诉时间
	private Date orderDateStart;
	private Date orderDateEnd;
	private Date zdate;//支付时间
	private Date zdateStart;
	private Date zdateEnd;
	private String payid; //(流水号)'支付ID，支付接口产生 用于查询支付记录使用',
	private String isPresidentHandle;
	private Date pdate;//总裁办处理时间

	private Double flatAgio;//平台补贴占比
	private Double flatMoney;//平台补贴金额
	private Integer isactivity;//是否参与活动
	private String activityConent;
	private Double profit;//返利支付金额
	private Double giveMoney;//赠送支付金额
	private Double payment;//需支付金额

    private String activityRest;//派奖结果描述

	
	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
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
	
	public String getActivityConent() {
		return activityConent;
	}

	public void setActivityConent(String activityConent) {
		this.activityConent = activityConent;
	}

	public String getActivityText(){
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


	
	
	public String getActivityRest() {
		return activityRest;
	}

	public void setActivityRest(String activityRest) {
		this.activityRest = activityRest;
	}
	

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
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

	/**
	 * 创建一个新的实例 BillRecord.
	 * 
	 */
	public BillRecord() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
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
	
	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public Date getQdateStart() {
		return qdateStart;
	}

	public void setQdateStart(Date qdateStart) {
		this.qdateStart = qdateStart;
	}

	public Date getQdateEnd() {
		return qdateEnd;
	}

	public void setQdateEnd(Date qdateEnd) {
		this.qdateEnd = qdateEnd;
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

	public Long getCodeid() {
		return codeid;
	}

	public void setCodeid(Long codeid) {
		this.codeid = codeid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQdate() {
		return qdate;
	}

	public void setQdate(Date qdate) {
		this.qdate = qdate;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	
	public Integer getAprearStatus() {
		return aprearStatus;
	}

	public void setAprearStatus(Integer aprearStatus) {
		this.aprearStatus = aprearStatus;
	}

	public String getAppealStatusText(){
		if(aprearStatus == null) return "";
		switch (aprearStatus) {
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
		default:
			return "";
		}
	}

	public String getQppeal() {
		return qppeal;
	}

	public void setQppeal(String qppeal) {
		this.qppeal = qppeal;
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

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getAdate() {
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public Date getOrderDateStart() {
		return orderDateStart;
	}

	public void setOrderDateStart(Date orderDateStart) {
		this.orderDateStart = orderDateStart;
	}

	public Date getOrderDateEnd() {
		return orderDateEnd;
	}

	public void setOrderDateEnd(Date orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
		return zdate;
	}

	public void setZdate(Date zdate) {
		this.zdate = zdate;
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
  
}
