package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRecordTagConf
 * 
 * 类描述： 直播通告标签配置表实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-7 上午11:26:55 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveRecordTagConf extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3626955071455791574L;

	private Long id;//主键ID

    private Integer recordId;//通告ID

    private Integer classifyId;//分类ID

    private Integer tagId;//标签ID

    private String tagName;//标签名称

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveRecordTagConf [id=" + id + ", recordId=" + recordId
				+ ", classifyId=" + classifyId + ", tagId=" + tagId
				+ ", tagName=" + tagName + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
    
    
}