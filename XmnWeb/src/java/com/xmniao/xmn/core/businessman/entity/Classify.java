package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class Classify extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 分类类别 1.商家 2.主播 3.直播
     */
    private Integer classifyType;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 分类类别 1.商家 2.主播 3.直播
     * @return classify_type 分类类别 1.商家 2.主播 3.直播
     */
    public Integer getClassifyType() {
        return classifyType;
    }

    /**
     * 分类类别 1.商家 2.主播 3.直播
     * @param classifyType 分类类别 1.商家 2.主播 3.直播
     */
    public void setClassifyType(Integer classifyType) {
        this.classifyType = classifyType;
    }

    /**
     * 分类名称
     * @return classify_name 分类名称
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * 分类名称
     * @param classifyName 分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}