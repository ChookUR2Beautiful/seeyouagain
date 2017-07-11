package com.xmn.saas.entity.activity;

import java.math.BigDecimal;

public class BargaindayAward extends AwardRelation{
    private Integer id;

    private Integer activityId;

    private Integer awardRelationId;

    private String awardRelationName;

    private BigDecimal basePrice;

    private BigDecimal salePrice;

    private String image;

    private Integer sellWeek;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getAwardRelationId() {
        return awardRelationId;
    }

    public void setAwardRelationId(Integer awardRelationId) {
        this.awardRelationId = awardRelationId;
    }

    public String getAwardRelationName() {
        return awardRelationName;
    }

    public void setAwardRelationName(String awardRelationName) {
        this.awardRelationName = awardRelationName == null ? null : awardRelationName.trim();
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getSellWeek() {
        return sellWeek;
    }

    public void setSellWeek(Integer sellWeek) {
        this.sellWeek = sellWeek;
    }
}