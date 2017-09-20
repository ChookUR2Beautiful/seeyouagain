package com.xmn.designer.entity.customize;

public class OrderMaterialCustomizeCarouselTemp {
    private Long id;

    private Long orderMaterialId;

    private String picUrl;

    private String remark;

    private Integer orderVal;

    private String type;

    private String coordinate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderMaterialId() {
        return orderMaterialId;
    }

    public void setOrderMaterialId(Long orderMaterialId) {
        this.orderMaterialId = orderMaterialId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrderVal() {
        return orderVal;
    }

    public void setOrderVal(Integer orderVal) {
        this.orderVal = orderVal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }
}