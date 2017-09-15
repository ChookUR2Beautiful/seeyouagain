package com.xmniao.xmn.core.catehome.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: SpecilTopicRelation    
* @Description:专题与外部建立联系关系实体   
* @author: liuzhihao   
* @date: 2017年2月17日 上午11:47:31
 */
@SuppressWarnings("serial")
public class SpecilTopicRelation implements Serializable{
	
    private Integer id;//专题关系表ID

    private Integer fid;//专题ID

    private Integer topicType;//专题类型

    private String subId;//专题外部联系关联ID

    private Integer sort;//排序

    private Date updateTime;//关系表更新时间

    private String subName;//关联名

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

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId == null ? null : subId.trim();
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }
}