package com.xmniao.xmn.core.billmanagerment.entity;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class AdjustApply extends BaseEntity {
	private Integer adid;// 调单申请id
	private Long sellerid;// 申请调单商家编号
	private Long bid;// 订单号
	private String phoneid;// 用户手机
	private Date sdate;// 申请时间
	private Date startSdate;// 申请开始时间
	private Date endSdate;// 申请结束时间
	
	private String money;// 调单备注
	private String remark;// 调单备注
	private Long uid;// 消费用户名id
	private Date pdate;// 调单处理时间
	private Long handleuser;// 处理人
	private String reason;// 驳回原因
	private String sellername;// 申请商家
	private Integer handlestatu;// 调单状态:1处理中，2驳回，3成功，4取消
	private Integer status;// 订单状态
	private Integer hstatus;// 分账状态
	private String handlestatuText;// 调单状态:1处理中，2驳回，3成功，4取消
	private String statusText;// 订单状态
	private String hstatusText;// 分账状态
	private String activityRest;//派奖结果描述
		
		
		
	public String getActivityRest() {
		return activityRest;
	}

	public void setActivityRest(String activityRest) {
	    this.activityRest = activityRest;
	}
	
	
	

	public String getHandlestatuText() {
		if(handlestatu==null) return "";
		if(handlestatu==1) return "处理中";
		if(handlestatu==2) return "驳回";
		if(handlestatu==3) return "成功";
		if(handlestatu==4) return "取消";
		return ""; 
	}
	public String getStatusText() {
		if(status==null) return "";
		if(status==0) return "待支付";
		if(status==1) return "待返现";
		if(status==2) return "已返现(已分账)";
		if(status==3) return "已消费";
		if(status==4) return "退款中";
		if(status==5) return "已退款";
		if(status==6) return "待返现，取消退款";
		if(status==7) return "退款中，已申诉";
		if(status==8) return "待返现，驳回申请退款";
		if(status==9) return "返利中";
		if(status==10) return "申诉失败";
		if(status==11) return "平台退款处理中";
		if(status==12) return "平台退款失败";
		if(status==13) return "商家同意退款";
		if(status==14) return "平台同意退款";
		
		return ""; 
	}
	public String getHstatusText() {
		if(hstatus==null) return "";
		if(hstatus==0) return "未分账";
		if(hstatus==1) return "结算通过";
		if(hstatus==2) return "结算未通过";
		if(hstatus==3) return "结算争议";
		if(hstatus==4) return "核算通过";
		if(hstatus==5) return "核算不通过";
		if(hstatus==6) return "核算争议";
		if(hstatus==7) return "财务通过";
		if(hstatus==8) return "财务不通过";
		if(hstatus==9) return "已分账";
		if(hstatus==10) return "自动分账处理中";
		if(hstatus==11) return "商家分账处理中";
		return ""; 
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getHstatus() {
		return hstatus;
	}

	public void setHstatus(Integer hstatus) {
		this.hstatus = hstatus;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getStartSdate() {
		return startSdate;
	}

	public void setStartSdate(Date startSdate) {
		this.startSdate = startSdate;
	}

	public Date getEndSdate() {
		return endSdate;
	}

	public void setEndSdate(Date endSdate) {
		this.endSdate = endSdate;
	}

	public Integer getAdid() {
		return adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	public Long getSellerid() {
		return sellerid;
	}

	public void setSellerid(Long sellerid) {
		this.sellerid = sellerid;
	}

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public Integer getHandlestatu() {
		return handlestatu;
	}

	public void setHandlestatu(Integer handlestatu) {
		this.handlestatu = handlestatu;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Long getHandleuser() {
		return handleuser;
	}

	public void setHandleuser(Long handleuser) {
		this.handleuser = handleuser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
