/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CommonMaterial
 * 
 * 类描述： 平面物料
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月5日 上午11:56:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class CommonMaterial {
	
	private Long id;//主键
	
	private String orderNo;//订单号
	
	private String title;//商品名称
	
	private String group;//规格
	
	private String remark;//描述
	
	private Integer nums;//数量
	
	private Date createTime;//创建时间
	
	private Date finishTime;//完成时间
	
	private String jsonString;
	
	private String mainColor;//主色调
	
	private String secColor;//副色调
	
	private String budget;//预算
	
	private String materialType;//物料类型
	
	private BigDecimal amount;//订单金额
	
	private BigDecimal freight;//运费
	
	private String type;//订单类型
	
	private List<MaterialGroup> mgroup;
	
	private Integer mgroupSize;
	
	private List<String> customizeUrls;
	
	private Integer urlSize;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getMainColor() {
		return mainColor;
	}

	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}

	public String getSecColor() {
		return secColor;
	}

	public void setSecColor(String secColor) {
		this.secColor = secColor;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public List<String> getCustomizeUrls() {
		return customizeUrls;
	}

	public void setCustomizeUrls(List<String> customizeUrls) {
		this.customizeUrls = customizeUrls;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public Integer getUrlSize() {
		if(customizeUrls != null){
			return customizeUrls.size();
		}else {
			return 0;
		}
	}

	public void setUrlSize(Integer urlSize) {
		this.urlSize = urlSize;
	}

	public String getRemark() {
		return remark;
	}

	public List<MaterialGroup> getMgroup() {
		return mgroup;
	}

	public void setMgroup(List<MaterialGroup> mgroup) {
		this.mgroup = mgroup;
	}

	public Integer getMgroupSize() {
		if(mgroup != null){
			return mgroup.size();
		}else {
			return 0;
		}
	}

	public void setMgroupSize(Integer mgroupSize) {
		this.mgroupSize = mgroupSize;
	}

	@Override
	public String toString() {
		return "CommonMaterial [id=" + id + ", orderNo=" + orderNo + ", title="
				+ title + ", group=" + group + ", remark=" + remark + ", nums="
				+ nums + ", createTime=" + createTime + ", finishTime="
				+ finishTime + ", jsonString=" + jsonString + ", mainColor="
				+ mainColor + ", secColor=" + secColor + ", budget=" + budget
				+ ", materialType=" + materialType + ", amount=" + amount
				+ ", freight=" + freight + ", type=" + type + ", mgroup="
				+ mgroup + ", customizeUrls=" + customizeUrls + ", urlSize="
				+ urlSize + "]";
	}

}
