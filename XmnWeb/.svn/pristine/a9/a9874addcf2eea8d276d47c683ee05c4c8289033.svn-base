package com.xmniao.xmn.core.business_cooperation.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称： XmnWeb 
 * 类名称： TJointSaasLedger.java 
 * 类描述：经销商旗下商家签约上线后分账记录 
 * 创建人： lifeng
 * 创建时间： 2016年6月29日下午5:23:56 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public class TJointSaasLedger extends BaseEntity {

	private static final long serialVersionUID = 5137843730120606958L;

	private Integer id;
	private Integer jointid;// 合作商ID
	private Integer sellerid;// 商家ID
	private Integer xmerUid;// 商家所属寻蜜客UID
	private String saasBid;// 商家签约订单
	private BigDecimal commison;// 分账金额
	private Date date;// 分账时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJointid() {
		return jointid;
	}

	public void setJointid(Integer jointid) {
		this.jointid = jointid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getXmerUid() {
		return xmerUid;
	}

	public void setXmerUid(Integer xmerUid) {
		this.xmerUid = xmerUid;
	}

	public String getSaasBid() {
		return saasBid;
	}

	public void setSaasBid(String saasBid) {
		this.saasBid = saasBid;
	}

	public BigDecimal getCommison() {
		return commison;
	}

	public void setCommison(BigDecimal commison) {
		this.commison = commison;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TJointSaasLedger [id=" + id + ", jointid=" + jointid
				+ ", sellerid=" + sellerid + ", xmerUid=" + xmerUid
				+ ", saasBid=" + saasBid + ", commison=" + commison + ", date="
				+ date + "]";
	}

}
