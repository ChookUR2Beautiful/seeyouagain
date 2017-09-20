package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class TSellerNotice extends BaseEntity {
	private Integer id;// 主键ID
	private String remark;//商家须知描述
	private Integer STATUS;//是否有效 0：未生效，1：已生效
	private String statusText;//是否有效 0：未生效，1：已生效
	private String creator;//创建人
	private Date  dateCreated;//创建时间
	private Date dateCreatedStart;//创建时间开始查询点
	private Date dateCreatedEnd;//创建时间结束查询点
	private String updator;//修改人
	private Date dateUpdated;//修改时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String getStatusText() {
		if(STATUS==null) return "";
		if(STATUS==1){
			return "已生效";
		}else{
			return "未生效";
		}
		
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public Date getDateCreatedStart() {
		return dateCreatedStart;
	}
	public void setDateCreatedStart(Date dateCreatedStart) {
		this.dateCreatedStart = dateCreatedStart;
	}
	public Date getDateCreatedEnd() {
		return dateCreatedEnd;
	}
	public void setDateCreatedEnd(Date dateCreatedEnd) {
		this.dateCreatedEnd = dateCreatedEnd;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	
}
