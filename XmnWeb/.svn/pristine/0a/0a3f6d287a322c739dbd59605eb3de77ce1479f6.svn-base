package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class GroupLevel extends BaseEntity{
    /**
     * 级别ID
     */
    private Long id;

    /**
     * 级别名称
     */
    private String levelName;

    /**
     * 上级id
     */
    private Long lastLevelId;

    /**
     * 最底绩效
     */
    private BigDecimal minPerformance;

    /**
     * 最高绩效
     */
    private BigDecimal maxPerformance;

    /**
     * 收入上限
     */
    private BigDecimal topIncome;

    /**
     * 奖励比例
     */
    private BigDecimal awardScale;

    /**
     * 统计周期 1:一周 2:一个月 3:一季度 4:一年
     */
    private Integer period;

    /**
     * 级别图片
     */
    private String levelPic;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    
    private String periodStr;
    
    private Integer level;
    
    public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPeriodStr() {
    	if(period==null){
    		return null;
    	}
    	switch (period) {
		case 1:
			return "一周";
		case 2:
			return "一个月";
		case 3:
			return "一季度";
		case 4:
			return "一年";
		default:
			break;
		}
		return periodStr;
	}

	public void setPeriodStr(String periodStr) {
		this.periodStr = periodStr;
	}

	/**
     * 级别ID
     * @return id 级别ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 级别ID
     * @param id 级别ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 级别名称
     * @return level_name 级别名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 级别名称
     * @param levelName 级别名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    /**
     * 上级id
     * @return last_level_id 上级id
     */
    public Long getLastLevelId() {
        return lastLevelId;
    }

    /**
     * 上级id
     * @param lastLevelId 上级id
     */
    public void setLastLevelId(Long lastLevelId) {
        this.lastLevelId = lastLevelId;
    }

    /**
     * 最底绩效
     * @return min_performance 最底绩效
     */
    public BigDecimal getMinPerformance() {
        return minPerformance;
    }

    /**
     * 最底绩效
     * @param minPerformance 最底绩效
     */
    public void setMinPerformance(BigDecimal minPerformance) {
        this.minPerformance = minPerformance;
    }

    /**
     * 最高绩效
     * @return max_performance 最高绩效
     */
    public BigDecimal getMaxPerformance() {
        return maxPerformance;
    }

    /**
     * 最高绩效
     * @param maxPerformance 最高绩效
     */
    public void setMaxPerformance(BigDecimal maxPerformance) {
        this.maxPerformance = maxPerformance;
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
     * 奖励比例
     * @return award_scale 奖励比例
     */
    public BigDecimal getAwardScale() {
        return awardScale;
    }

    /**
     * 奖励比例
     * @param awardScale 奖励比例
     */
    public void setAwardScale(BigDecimal awardScale) {
        this.awardScale = awardScale;
    }

    /**
     * 统计周期 1:一个月 2:一周 3:一季度 4:一年
     * @return period 统计周期 1:一个月 2:一周 3:一季度 4:一年
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 统计周期 1:一个月 2:一周 3:一季度 4:一年
     * @param period 统计周期 1:一个月 2:一周 3:一季度 4:一年
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * 级别图片
     * @return level_pic 级别图片
     */
    public String getLevelPic() {
        return levelPic;
    }

    /**
     * 级别图片
     * @param levelPic 级别图片
     */
    public void setLevelPic(String levelPic) {
        this.levelPic = levelPic == null ? null : levelPic.trim();
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