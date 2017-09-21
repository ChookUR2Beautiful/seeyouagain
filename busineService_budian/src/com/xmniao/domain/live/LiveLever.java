package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;

public class LiveLever {
    /**
     * 主播级别ID
     */
    private Integer id;

    /**
     * 主播级别名称
     */
    private String levelName;

    /**
     * 级别薪酬
     */
    private BigDecimal levelIncome;

    /**
     * 浮动绩效
     */
    private BigDecimal floatPerformance;

    /**
     * 收入上限
     */
    private BigDecimal topIncome;

    /**
     * 送礼分成(%)
     */
    private BigDecimal giftAllot;

    /**
     * 完成率100%场次
     */
    private Integer percentComplete;

    /**
     * 完成率80%场次
     */
    private Integer percentComplete80;

    /**
     * 完成率60%场次
     */
    private Integer percentComplete60;

    /**
     * 完成率40%场次
     */
    private Integer percentComplete40;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 主播级别ID
     * @return id 主播级别ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主播级别ID
     * @param id 主播级别ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主播级别名称
     * @return level_name 主播级别名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 主播级别名称
     * @param levelName 主播级别名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    /**
     * 级别薪酬
     * @return level_income 级别薪酬
     */
    public BigDecimal getLevelIncome() {
        return levelIncome;
    }

    /**
     * 级别薪酬
     * @param levelIncome 级别薪酬
     */
    public void setLevelIncome(BigDecimal levelIncome) {
        this.levelIncome = levelIncome;
    }

    /**
     * 浮动绩效
     * @return float_performance 浮动绩效
     */
    public BigDecimal getFloatPerformance() {
        return floatPerformance;
    }

    /**
     * 浮动绩效
     * @param floatPerformance 浮动绩效
     */
    public void setFloatPerformance(BigDecimal floatPerformance) {
        this.floatPerformance = floatPerformance;
    }

    /**
     * 收入上限
     * @return top_income 收入上限
     */
    public BigDecimal getTopIncome() {
        return topIncome;
    }

    /**
     * 收入上限
     * @param topIncome 收入上限
     */
    public void setTopIncome(BigDecimal topIncome) {
        this.topIncome = topIncome;
    }

    /**
     * 送礼分成(%)
     * @return gift_allot 送礼分成(%)
     */
    public BigDecimal getGiftAllot() {
        return giftAllot;
    }

    /**
     * 送礼分成(%)
     * @param giftAllot 送礼分成(%)
     */
    public void setGiftAllot(BigDecimal giftAllot) {
        this.giftAllot = giftAllot;
    }

    /**
     * 完成率100%场次
     * @return percent_complete 完成率100%场次
     */
    public Integer getPercentComplete() {
        return percentComplete;
    }

    /**
     * 完成率100%场次
     * @param percentComplete 完成率100%场次
     */
    public void setPercentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
    }

    /**
     * 完成率80%场次
     * @return percent_complete_80 完成率80%场次
     */
    public Integer getPercentComplete80() {
        return percentComplete80;
    }

    /**
     * 完成率80%场次
     * @param percentComplete80 完成率80%场次
     */
    public void setPercentComplete80(Integer percentComplete80) {
        this.percentComplete80 = percentComplete80;
    }

    /**
     * 完成率60%场次
     * @return percent_complete_60 完成率60%场次
     */
    public Integer getPercentComplete60() {
        return percentComplete60;
    }

    /**
     * 完成率60%场次
     * @param percentComplete60 完成率60%场次
     */
    public void setPercentComplete60(Integer percentComplete60) {
        this.percentComplete60 = percentComplete60;
    }

    /**
     * 完成率40%场次
     * @return percent_complete_40 完成率40%场次
     */
    public Integer getPercentComplete40() {
        return percentComplete40;
    }

    /**
     * 完成率40%场次
     * @param percentComplete40 完成率40%场次
     */
    public void setPercentComplete40(Integer percentComplete40) {
        this.percentComplete40 = percentComplete40;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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