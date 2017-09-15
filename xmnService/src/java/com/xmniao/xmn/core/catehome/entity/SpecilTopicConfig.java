package com.xmniao.xmn.core.catehome.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: SpecilTopicConfig    
* @Description:专题详情实体类   
* @author: liuzhihao   
* @date: 2017年2月17日 上午11:46:46
 */
@SuppressWarnings("serial")
public class SpecilTopicConfig implements Serializable{
	
    private Integer id;//专题ID

    private String title;//专题标题

    private String description;//专题描述

    private String videoUrl;//专题视频

    private Integer topicType;//专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品

    private Date updateTime;//专题更新时间

    private String content;//专题内容
    
    private Integer isNationwide;//是否全国推荐
    
    private Integer provinceId;//省ID
    
    private Integer cityId;//市ID
    
    private Integer homeSort;//是否首页排序

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Integer getIsNationwide() {
		return isNationwide;
	}

	public void setIsNationwide(Integer isNationwide) {
		this.isNationwide = isNationwide;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getHomeSort() {
		return homeSort;
	}

	public void setHomeSort(Integer homeSort) {
		this.homeSort = homeSort;
	}

	@Override
	public String toString() {
		return "SpecilTopicConfig [id=" + id + ", title=" + title + ", description=" + description + ", videoUrl=" + videoUrl
			+ ", topicType=" + topicType + ", updateTime=" + updateTime + ", content=" + content + ", isNationwide=" + isNationwide
			+ ", provinceId=" + provinceId + ", cityId=" + cityId + ", homeSort=" + homeSort + "]";
	}
    
}