package com.xmniao.xmn.core.business_statistics.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographTemplate
 * 
 * 类描述： 微图助力模板实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 上午11:26:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TMicrographTemplate extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9122807675904357186L;

	private Integer id;//主键

    private Integer soldTimes;//使用次数

    private String title;//模板标题

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private Integer serialNo;//排序号

    private String coverImage;//封面图

    private Integer status;//状态 0:正常 1:删除
    
    private String templateTagVals;//关联标签(基础数据) 1_红色,2_西红柿
    
    private String relationTags;//关联标签(列表展示)
    
    private String relationTagIds;//关联标签Ids
    
    private List<TMicrographPage> pageList;//模板页面

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoldTimes() {
        return soldTimes;
    }

    public void setSoldTimes(Integer soldTimes) {
        this.soldTimes = soldTimes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

	/**
	 * @return the templateTagVals
	 */
	public String getTemplateTagVals() {
		return templateTagVals;
	}

	/**
	 * @param templateTagVals the templateTagVals to set
	 */
	public void setTemplateTagVals(String templateTagVals) {
		this.templateTagVals = templateTagVals;
	}

	/**
	 * @return the relationTags
	 */
	public String getRelationTags() {
		return relationTags;
	}

	/**
	 * @param relationTags the relationTags to set
	 */
	public void setRelationTags(String relationTags) {
		this.relationTags = relationTags;
	}
	
	

	/**
	 * @return the relationTagIds
	 */
	public String getRelationTagIds() {
		return relationTagIds;
	}

	/**
	 * @param relationTagIds the relationTagIds to set
	 */
	public void setRelationTagIds(String relationTagIds) {
		this.relationTagIds = relationTagIds;
	}
	
	

	/**
	 * @return the pageList
	 */
	public List<TMicrographPage> getPageList() {
		return pageList;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(List<TMicrographPage> pageList) {
		this.pageList = pageList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TMicrographTemplate [id=" + id + ", soldTimes=" + soldTimes
				+ ", title=" + title + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", serialNo=" + serialNo
				+ ", coverImage=" + coverImage + ", status=" + status
				+ ", templateTagVals=" + templateTagVals + ", relationTags="
				+ relationTags + ", relationTagIds=" + relationTagIds
				+ ", pageList=" + pageList + "]";
	}
    
    
}