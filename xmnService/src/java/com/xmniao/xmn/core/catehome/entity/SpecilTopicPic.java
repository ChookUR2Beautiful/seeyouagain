package com.xmniao.xmn.core.catehome.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: SpecilTopicPic    
* @Description:专题图片实体   
* @author: liuzhihao   
* @date: 2017年2月17日 上午11:47:09
 */
@SuppressWarnings("serial")
public class SpecilTopicPic implements Serializable{
	
    private Integer id;//专题图片ID

    private Integer fid;//专题ID

    private String picUrl;//专题图片地址

    private Integer sort;//排序

    private Date updateTime;//更新时间
    
    private String title;// 专题名称
    
    private String context; //专题描述
    
    private String specialContent; //专题内容
    
    private String linkUrl;//跳转地址
    
    private Integer sellerNum;//套餐数量
    
    private Integer location;//图片位置
    
    private Integer topicType;//专题类型
    
    private String specialRelation;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSpecialContent() {
		return specialContent;
	}

	public void setSpecialContent(String specialContent) {
		this.specialContent = specialContent;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(Integer sellerNum) {
		this.sellerNum = sellerNum;
	}	
	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getTopicType() {
		return topicType;
	}

	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}

	public String getSpecialRelation() {
		return specialRelation;
	}

	public void setSpecialRelation(String specialRelation) {
		this.specialRelation = specialRelation;
	}

	@Override
	public String toString() {
		return "SpecilTopicPic [id=" + id + ", fid=" + fid + ", picUrl=" + picUrl + ", sort=" + sort + ", updateTime="
				+ updateTime + ", title=" + title + ", context=" + context + ", specialContent=" + specialContent
				+ ", linkUrl=" + linkUrl + ", sellerNum=" + sellerNum + ", location=" + location + ", topicType="
				+ topicType + ", specialRelation=" + specialRelation + "]";
	}

	


	
    
}