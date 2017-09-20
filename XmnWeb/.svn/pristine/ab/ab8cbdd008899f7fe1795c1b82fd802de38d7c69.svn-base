package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class RecommendSpecial extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 专题描述
     */
    private String description;

    /**
     * 专题视频
     */
    private String videoUrl;

    /**
     * 专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
     */
    private Integer topicType;

    /**
     * 是否全国通用
     */
    private Integer isNationwide;

    /**
     * 适用省ID
     */
    private Integer provinceId;

    /**
     * 适用城市ID
     */
    private Integer cityId;

    /**
     * 首页推荐排序
     */
    private Integer homeSort;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 专题内容
     */
    private String content;
    
    private String provinceName;
    
    private String cityName;
    
    private String subName;
    
    private String topicTypeStr;

    public String getTopicTypeStr() {
    	if(topicType==null){
    		return null;
    	}
    	switch (topicType) {
		case 1:
			return "商户";
		case 2:
			return "连锁店";
		case 3:
			return "区域代理";
		case 4:
			return "主播";
		case 5:
			return "预告";
		case 6:
			return "粉丝券";
		case 7:
			return "套餐";
		case 8:
			return "商品";
		default:
			break;
		}
		return topicTypeStr;
	}

	public void setTopicTypeStr(String topicTypeStr) {
		this.topicTypeStr = topicTypeStr;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

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
     * 专题描述
     * @return description 专题描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 专题描述
     * @param description 专题描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 专题视频
     * @return video_url 专题视频
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 专题视频
     * @param videoUrl 专题视频
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    /**
     * 专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
     * @return topic_type 专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
     */
    public Integer getTopicType() {
        return topicType;
    }

    /**
     * 专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
     * @param topicType 专题类型 1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
     */
    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    /**
     * 是否全国通用
     * @return is_nationwide 是否全国通用
     */
    public Integer getIsNationwide() {
        return isNationwide;
    }

    /**
     * 是否全国通用
     * @param isNationwide 是否全国通用
     */
    public void setIsNationwide(Integer isNationwide) {
        this.isNationwide = isNationwide;
    }

    /**
     * 适用省ID
     * @return province_id 适用省ID
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 适用省ID
     * @param provinceId 适用省ID
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 适用城市ID
     * @return city_id 适用城市ID
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 适用城市ID
     * @param cityId 适用城市ID
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 首页推荐排序
     * @return home_sort 首页推荐排序
     */
    public Integer getHomeSort() {
        return homeSort;
    }

    /**
     * 首页推荐排序
     * @param homeSort 首页推荐排序
     */
    public void setHomeSort(Integer homeSort) {
        this.homeSort = homeSort;
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

    /**
     * 专题内容
     * @return content 专题内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 专题内容
     * @param content 专题内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}