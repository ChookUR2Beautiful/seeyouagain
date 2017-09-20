package com.xmn.designer.entity.material;

import java.math.BigDecimal;

public class OrderMaterial {
    private Long id;

    private Long materialId;

    private String orderNo;

    private Integer nums;

    private BigDecimal salePrice;

    private BigDecimal basePrice;

    private String title;

    private String remark;

    private String materialAttrGroupVal;

    private String type;

    // 商品分类名称
    private String categoryName;

    public String getCategoryName() {


        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMaterialAttrGroupVal() {
        return materialAttrGroupVal;
    }

    public void setMaterialAttrGroupVal(String materialAttrGroupVal) {
        this.materialAttrGroupVal = materialAttrGroupVal == null ? null : materialAttrGroupVal.trim();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    
}