/**   
 * 文件名：TOrderinvoice.java   
 *    
 * 日期：2014年11月17日18时23分50秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TOrderinvoice
 * 
 * 类描述：商家申请发票
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月17日18时23分50秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TOrderinvoice extends BaseEntity  {
	
	private static final long serialVersionUID = -7996804881068224228L;

	private Long id;// ID(自增)
	private String sellerName;// 商家名称
	private String invoice;// 发票抬头
	private Date applyDate;// 申请时间
	private Date joinDate;// 加入时间
	private String sellerUsername;// 商家账号
	private String sellerAddress;// 商家地址
	private String sellerTelephone;// 咨询电话
	private String regionId;// 所属区域
	private Double appliedAmount;// 已申请金额
	private Double availableAmount;// 可申请金额
	private Double applyAmount;// 申请金额
	private Long operationId;// 操作员ID
	private String operationName;// 操作员名称
	private String opinion;// 意见
	private Date updateDate;// 修改时间
	private String note;// 备注
	
	private Date applyDateStart;//申请时间(搜索)
	private Date applyDateEnd;	//申请时间(搜索)
	private Double applyAmountSt;// 申请金额(搜索)
	private Double applyAmountEd;// 申请金额(搜索)
	
	//2016/4/18 add by hls
	private Integer status;//处理状态
	private String strStatus;//处理状态(用于页面显示)
	private String taxid;//税务登记号
	
	
	
	public String getStrStatus() {
		if(status == 0) return "未处理";
		if(status == 1) return "已处理";
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTaxid() {
		return taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public Double getApplyAmountSt() {
		return applyAmountSt;
	}

	public void setApplyAmountSt(Double applyAmountSt) {
		this.applyAmountSt = applyAmountSt;
	}

	public Double getApplyAmountEd() {
		return applyAmountEd;
	}

	public void setApplyAmountEd(Double applyAmountEd) {
		this.applyAmountEd = applyAmountEd;
	}

	/**   
	 * 创建一个新的实例 TOrderinvoice.   
	 *      
	 */
	public TOrderinvoice() {
		super();
	}
	
	public TOrderinvoice(Long id) {
		this.id = id;
	}

	
	/**
	 * id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * sellerName
	 * 
	 * @return the sellerName
	 */
	public String getSellerName() {
		return sellerName;
	}

	/**
	 * @param sellerName
	 *            the sellerName to set
	 */
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	/**
	 * invoice
	 * 
	 * @return the invoice
	 */
	public String getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice
	 *            the invoice to set
	 */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	/**
	 * applyDate
	 * 
	 * @return the applyDate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * @param applyDate
	 *            the applyDate to set
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	/**
	 * joinDate
	 * 
	 * @return the joinDate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * @param joinDate
	 *            the joinDate to set
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	/**
	 * sellerUsername
	 * 
	 * @return the sellerUsername
	 */
	public String getSellerUsername() {
		return sellerUsername;
	}

	/**
	 * @param sellerUsername
	 *            the sellerUsername to set
	 */
	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}
	
	/**
	 * sellerAddress
	 * 
	 * @return the sellerAddress
	 */
	public String getSellerAddress() {
		return sellerAddress;
	}

	/**
	 * @param sellerAddress
	 *            the sellerAddress to set
	 */
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	
	/**
	 * sellerTelephone
	 * 
	 * @return the sellerTelephone
	 */
	public String getSellerTelephone() {
		return sellerTelephone;
	}

	/**
	 * @param sellerTelephone
	 *            the sellerTelephone to set
	 */
	public void setSellerTelephone(String sellerTelephone) {
		this.sellerTelephone = sellerTelephone;
	}
	
	/**
	 * regionId
	 * 
	 * @return the regionId
	 */
	public String getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId
	 *            the regionId to set
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	/**
	 * appliedAmount
	 * 
	 * @return the appliedAmount
	 */
	public Double getAppliedAmount() {
		return appliedAmount;
	}

	/**
	 * @param appliedAmount
	 *            the appliedAmount to set
	 */
	public void setAppliedAmount(Double appliedAmount) {
		this.appliedAmount = appliedAmount;
	}
	
	/**
	 * availableAmount
	 * 
	 * @return the availableAmount
	 */
	public Double getAvailableAmount() {
		return availableAmount;
	}

	/**
	 * @param availableAmount
	 *            the availableAmount to set
	 */
	public void setAvailableAmount(Double availableAmount) {
		this.availableAmount = availableAmount;
	}
	
	/**
	 * applyAmount
	 * 
	 * @return the applyAmount
	 */
	public Double getApplyAmount() {
		return applyAmount;
	}

	/**
	 * @param applyAmount
	 *            the applyAmount to set
	 */
	public void setApplyAmount(Double applyAmount) {
		this.applyAmount = applyAmount;
	}
	
	/**
	 * operationId
	 * 
	 * @return the operationId
	 */
	public Long getOperationId() {
		return operationId;
	}

	/**
	 * @param operationId
	 *            the operationId to set
	 */
	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	
	/**
	 * operationName
	 * 
	 * @return the operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * @param operationName
	 *            the operationName to set
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	/**
	 * opinion
	 * 
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion
	 *            the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	/**
	 * updateDate
	 * 
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * note
	 * 
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	public Date getApplyDateStart() {
		return applyDateStart;
	}

	public void setApplyDateStart(Date applyDateStart) {
		this.applyDateStart = applyDateStart;
	}

	public Date getApplyDateEnd() {
		return applyDateEnd;
	}

	public void setApplyDateEnd(Date applyDateEnd) {
		this.applyDateEnd = applyDateEnd;
	}

	@Override
	public String toString() {
		return "TOrderinvoice [id=" + id + ", sellerName=" + sellerName
				+ ", invoice=" + invoice + ", applyDate=" + applyDate
				+ ", joinDate=" + joinDate + ", sellerUsername="
				+ sellerUsername + ", sellerAddress=" + sellerAddress
				+ ", sellerTelephone=" + sellerTelephone + ", regionId="
				+ regionId + ", appliedAmount=" + appliedAmount
				+ ", availableAmount=" + availableAmount + ", applyAmount="
				+ applyAmount + ", operationId=" + operationId
				+ ", operationName=" + operationName + ", opinion=" + opinion
				+ ", updateDate=" + updateDate + ", note=" + note
				+ ", applyDateStart=" + applyDateStart + ", applyDateEnd="
				+ applyDateEnd + ", applyAmountSt=" + applyAmountSt
				+ ", applyAmountEd=" + applyAmountEd + "]";
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	/*@Override
	public String toString() {
		return "TOrderinvoice [id=" + id + ", sellerName=" + sellerName + ", invoice=" + invoice + ", applyDate=" + applyDate + ", joinDate=" + joinDate + ", sellerUsername=" + sellerUsername + ", sellerAddress=" + sellerAddress + ", sellerTelephone=" + sellerTelephone + ", regionId=" + regionId + ", appliedAmount=" + appliedAmount + ", availableAmount=" + availableAmount + ", applyAmount=" + applyAmount + ", operationId=" + operationId + ", operationName=" + operationName + ", opinion=" + opinion + ", updateDate=" + updateDate + ", note=" + note + ", ]";
	}*/

	
}
