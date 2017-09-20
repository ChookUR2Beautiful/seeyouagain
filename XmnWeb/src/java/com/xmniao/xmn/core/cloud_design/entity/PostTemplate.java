/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PostTemplage
 * 
 * 类描述： 运费模板
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月17日 下午5:03:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class PostTemplate extends BaseEntity{

	
	private static final long serialVersionUID = -9160551623259808552L;
	
	private Long id;//模板id
	
	private String templateName;//模板名称
	
	private Integer status;//状态

	private List<PostCondition> conditions;//配送地
	
	private Long supplierId;//供应商id
	
	private String supplierName;//供应商姓名
	
	private String type;//类型 计重量类型：002，计件类型：001
	
	private String cityStr;
	
	private String cityNoStr;
	
	private Date createTime;//创建时间
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public List<PostCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<PostCondition> conditions) {
		this.conditions = conditions;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	
	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getCityStr() {
		List<String> list = new ArrayList<>();
		for (PostCondition condition  : conditions) {
			list.add(condition.getDeliveryCity());
		}
		return list.toString();
	}

	public void setCityStr(String cityStr) {
		this.cityStr = cityStr;
	}

	public String getCityNoStr() {
		List<String> list = new ArrayList<>();
		for (PostCondition condition  : conditions) {
			list.add(condition.getDeliveryNo());
		}
		return list.toString();
	}

	public void setCityNoStr(String cityNoStr) {
		this.cityNoStr = cityNoStr;
	}
	
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "PostTemplate [id=" + id + ", templateName=" + templateName
				+ ", status=" + status + ", conditions=" + conditions
				+ ", supplierId=" + supplierId + ", supplierName="
				+ supplierName + ", type=" + type + ", cityStr=" + cityStr
				+ ", cityNoStr=" + cityNoStr + ", createTime=" + createTime
				+ "]";
	}

}
