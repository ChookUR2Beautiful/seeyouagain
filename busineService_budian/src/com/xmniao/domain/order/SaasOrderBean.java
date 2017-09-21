package com.xmniao.domain.order;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：SaasOrderBean
 * 
 * 类描述： 寻蜜客签约套餐实体
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月3日 下午2:37:50
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class SaasOrderBean {


	private Integer id;

	private String ordersn;

	private Integer dpid;

	private Integer uid;

	private Integer nums;

	private BigDecimal amount;

	private Boolean status;

	private String reason;

	private Date sdate;

	private Date zdate;

	private Integer payType;

	private BigDecimal samount;

	private String payId;

	private String payCode;

	private BigDecimal agio;

	// 新增属性 add by hls
	private Integer stock;// 剩余套餐库存数量
	
	// 时间范围:开始和结束时间，用于搜索 add by lifeng
	private Date sDate;
	private Date eDate;
	
	private BigDecimal price;
	private Integer soldNums;
	private String parentid;//推荐人ID
	private String strStatus;//订单状态
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn == null ? null : ordersn.trim();
	}

	public Integer getDpid() {
		return dpid;
	}

	public void setDpid(Integer dpid) {
		this.dpid = dpid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
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

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getSamount() {
		return samount;
	}

	public void setSamount(BigDecimal samount) {
		this.samount = samount;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId == null ? null : payId.trim();
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode == null ? null : payCode.trim();
	}

	public BigDecimal getAgio() {
		return agio;
	}

	public void setAgio(BigDecimal agio) {
		this.agio = agio;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSoldNums() {
		return soldNums;
	}

	public void setSoldNums(Integer soldNums) {
		this.soldNums = soldNums;
	}
	

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getStrStatus() {
		if(status.equals(0)){
			strStatus = "未支付";
		} 
		if(status.equals(1)){
			strStatus = "支付成功";
		} 
		if(status.equals(2)){
			strStatus = "支付失败";
		}
		if(status.equals(3)){
			strStatus = "申请退款中";
		}
		if(status.equals(4)){
			strStatus = "退款成功";
		}
		if(status.equals(5)){
			strStatus = "退款申请不通过";
		}
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	@Override
	public String toString() {
		return "TSaasOrder [id=" + id + ", ordersn=" + ordersn + ", dpid="
				+ dpid + ", uid=" + uid + ", nums=" + nums + ", amount="
				+ amount + ", status=" + status + ", reason=" + reason
				+ ", sdate=" + sdate + ", zdate=" + zdate + ", payType="
				+ payType + ", samount=" + samount + ", payId=" + payId
				+ ", payCode=" + payCode + ", agio=" + agio + ", stock="
				+ stock + ", sDate=" + sDate + ", eDate=" + eDate + ", price="
				+ price + ", soldNums=" + soldNums + ", parentid=" + parentid
				+ "]";
	}
	
}