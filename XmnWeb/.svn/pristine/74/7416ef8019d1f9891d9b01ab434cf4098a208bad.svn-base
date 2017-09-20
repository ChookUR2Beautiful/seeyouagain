package com.xmniao.xmn.core.cloud_design.entity;

import java.math.BigDecimal;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialAttrGroup
 * 
 * 类描述： 物料属性分组实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午7:45:13 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialAttrGroup extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8167456238125973869L;

	private Long id;//业务主键

    private Long materialId;//物料id

    private String materialAttrIds;//物料规格id,以","作分隔符'

    private String materialAttrVals;//属性值字符串，逗号分割，顺序与materialAttrIds对应
    
    private List<String> materialAttrValList;//属性值数组

    private BigDecimal amount;//加价金额

    private Integer sales;//已销售数量(已作废)
    
    private Integer sortVal;//排序值

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialAttrIds() {
        return materialAttrIds;
    }

    public void setMaterialAttrIds(String materialAttrIds) {
        this.materialAttrIds = materialAttrIds == null ? null : materialAttrIds.trim();
    }

    public String getMaterialAttrVals() {
        return materialAttrVals;
    }

    public void setMaterialAttrVals(String materialAttrVals) {
        this.materialAttrVals = materialAttrVals == null ? null : materialAttrVals.trim();
    }
    
    

    /**
	 * @return the materialAttrValList
	 */
	public List<String> getMaterialAttrValList() {
		return materialAttrValList;
	}

	/**
	 * @param materialAttrValList the materialAttrValList to set
	 */
	public void setMaterialAttrValList(List<String> materialAttrValList) {
		this.materialAttrValList = materialAttrValList;
	}

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

	/**
	 * @return the sortVal
	 */
	public Integer getSortVal() {
		return sortVal;
	}

	/**
	 * @param sortVal the sortVal to set
	 */
	public void setSortVal(Integer sortVal) {
		this.sortVal = sortVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialAttrGroup [id=" + id + ", materialId=" + materialId
				+ ", materialAttrIds=" + materialAttrIds
				+ ", materialAttrVals=" + materialAttrVals
				+ ", materialAttrValList=" + materialAttrValList + ", amount="
				+ amount + ", sales=" + sales + ", sortVal=" + sortVal + "]";
	}
    
    
}