package com.xmniao.xmn.core.market.entity.home;

import java.util.Date;

public class FreshImage {
    private Long id;

    private Integer state;

    private Integer type;

    private Long typeId;

    private String title;

    private String imageUrl;

    private Integer jumpType;

    private Long jumpProductId;

    private String jumpUrl;

    private Integer sort;

    private Date updateTime;

    private Date createTime;

    private Integer jumpActivityId;

    public Integer getJumpActivityId() {
        return jumpActivityId;
    }

    public void setJumpActivityId(Integer jumpActivityId) {
        this.jumpActivityId = jumpActivityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getJumpType() {
        return jumpType;
    }

    public void setJumpType(Integer jumpType) {
        this.jumpType = jumpType;
    }

    public Long getJumpProductId() {
        return jumpProductId;
    }

    public void setJumpProductId(Long jumpProductId) {
        this.jumpProductId = jumpProductId;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl == null ? null : jumpUrl.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}