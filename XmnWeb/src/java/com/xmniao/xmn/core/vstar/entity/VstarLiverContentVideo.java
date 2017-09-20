package com.xmniao.xmn.core.vstar.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class VstarLiverContentVideo extends BaseEntity{
    /**
     * id
     */
    private Long id;

    /**
     * 图文教学ID(t_vstar_live_content)
     */
    private Long contentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 视频截图
     */
    private String videoImg;

    /**
     * 时长
     */
    private Integer duration;

    /**
     * 排序值
     */
    private Integer sortVal;

    /**
     *鸟币金额
     */
    private BigDecimal zbalance;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 试看时间(分钟)
     */
    private Integer experienceTime;

    /**
     * 有效状态，1有效，2无效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * id
     * @return id id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 图文教学ID(t_vstar_live_content)
     * @return content_id 图文教学ID(t_vstar_live_content)
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * 图文教学ID(t_vstar_live_content)
     * @param contentId 图文教学ID(t_vstar_live_content)
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 视频地址
     * @return video_url 视频地址
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 视频地址
     * @param videoUrl 视频地址
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    /**
     * 视频截图
     * @return video_img 视频截图
     */
    public String getVideoImg() {
        return videoImg;
    }

    /**
     * 视频截图
     * @param videoImg 视频截图
     */
    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg == null ? null : videoImg.trim();
    }

    /**
     * 时长
     * @return duration 时长
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 时长
     * @param duration 时长
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 排序值
     * @return sort_val 排序值
     */
    public Integer getSortVal() {
        return sortVal;
    }

    /**
     * 排序值
     * @param sortVal 排序值
     */
    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public BigDecimal getZbalance() {
		return zbalance;
	}

	public void setZbalance(BigDecimal zbalance) {
		this.zbalance = zbalance;
	}

	/**
     * 金额
     * @return amount 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 试看时间(分钟)
     * @return experience_time 试看时间(分钟)
     */
    public Integer getExperienceTime() {
        return experienceTime;
    }

    /**
     * 试看时间(分钟)
     * @param experienceTime 试看时间(分钟)
     */
    public void setExperienceTime(Integer experienceTime) {
        this.experienceTime = experienceTime;
    }

    /**
     * 有效状态，1有效，2无效
     * @return status 有效状态，1有效，2无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 有效状态，1有效，2无效
     * @param status 有效状态，1有效，2无效
     */
    public void setStatus(Integer status) {
        this.status = status;
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