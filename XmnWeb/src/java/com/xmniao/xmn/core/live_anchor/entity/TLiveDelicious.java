package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TLiveDelicious extends BaseEntity{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7543993943928149711L;

	private Integer id;

    private String title;

    private Integer classifyId;

    private Integer tagId;

    private String tagName;
    
    private Integer rtype;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

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
		this.title = title;
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
		this.tagName = tagName;
	}

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	@Override
	public String toString() {
		return "TLiveDelicious [id=" + id + ", title=" + title
				+ ", classifyId=" + classifyId + ", tagId=" + tagId
				+ ", tagName=" + tagName + ", rtype=" + rtype + ", sort="
				+ sort + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}



    
    
}