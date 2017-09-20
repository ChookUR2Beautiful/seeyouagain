package com.xmn.designer.controller.api.v1.material.vo;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.xmn.designer.base.Request;
import com.xmn.designer.entity.material.Material;

public class MaterialListRequest extends Request{
    
    /**
     * 
     */
    private static final long serialVersionUID = 7663407272817611209L;

    private String minPrice;
    
    private String maxPrice;
    
    private String categoryId;
    
    private String tagId;
    
    private String attrId;
    
    @NotNull(message = "排序不能为空")
    private String orderType;
    
    
    @NotNull(message = "页数不能为空")
    private Integer page;
    
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;



    @Override
    public String toString() {
        return "MaterialListRequest [minPrice=" + minPrice + ", maxPrice=" + maxPrice
                + ", categoryId=" + categoryId + ", tagId=" + tagId + ", attrId=" + attrId
                + ", orderType=" + orderType + ", page=" + page + ", pageSize=" + pageSize + "]";
    }


    public String getMinPrice() {
        return minPrice;
    }
    

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Material convertToBean(){
        Material material = new Material();
        if(StringUtils.isNotBlank(this.getCategoryId())){
            material.setCategoryId(Long.valueOf(this.getCategoryId()));
        }
        if(StringUtils.isNotBlank(this.getTagId())){
            material.setTagId(Long.valueOf(this.getTagId()));
        }
        if(StringUtils.isNotBlank(this.getAttrId())){
            material.setAttrId(Long.valueOf(this.getAttrId()));
        }
        material.setOrderType(this.getOrderType());
        if(StringUtils.isNotBlank(this.getMaxPrice()) && StringUtils.isNotBlank(this.getMinPrice())){
            material.setMaxPrice(Integer.valueOf(this.getMaxPrice()));
            material.setMinPrice(Integer.valueOf(this.getMinPrice()));
        }
        material.setPageOffset((this.page - 1) * this.pageSize);
        material.setPageSize(this.pageSize);
        return material;
    }
    


}
