package com.xmniao.xmn.core.market.entity.activity.spike;

import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;

import java.util.Date;
import java.util.List;

public class FreshSpikeActivity {
    private Long id;

    private String title;

    private Date startTime;

    private Date endTime;

    private String image;

    private Integer state;

    private Date updateTime;

    private Date createTime;

    // 秒杀活动场次
    private List<FreshActivityCommon> sessions;

    public List<FreshActivityCommon> getSessions() {
        return sessions;
    }

    public void setSessions(List<FreshActivityCommon> sessions) {
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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