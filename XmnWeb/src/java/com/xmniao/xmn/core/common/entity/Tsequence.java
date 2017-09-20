package com.xmniao.xmn.core.common.entity;

import java.util.Date;


import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：Tsequence
 * 
 * 类描述： 业务逻辑序列表 实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月12日 下午5:17:23 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class Tsequence extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer numId;

    private Long startVal;

    private Long maxVal;

    private Long sid;

    private Date udate;

    private String remark;

    public Integer getId() {
        return id;
    }

	public Integer getNumId() {
		return numId;
	}

	public void setNumId(Integer numId) {
		this.numId = numId;
	}

	public Long getStartVal() {
		return startVal;
	}

	public void setStartVal(Long startVal) {
		this.startVal = startVal;
	}

	public Long getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Long maxVal) {
		this.maxVal = maxVal;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Tsequence [id=" + id + ", numId=" + numId + ", startVal="
				+ startVal + ", maxVal=" + maxVal + ", sid=" + sid + ", udate="
				+ udate + ", remark=" + remark + "]";
	}


}