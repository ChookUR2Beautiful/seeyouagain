package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

public class TSpecialTopic  extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String description;

    private String videoUrl;

    private Integer topicType;
    
	private Date updateTime;

    private String content;
    
    /*************自定义字段开始*********************/
    private String topicTypeDesc;

	private String relationNum;
	
    private String picUrl;
    
	private List<TSpecialTopicRelation> specialTopicRelation;
	
	private String productJson;
	
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public String getProductJson() {
		return productJson;
	}

	public void setProductJson(String productJson) {
		this.productJson = productJson;
	}

	public List<TSpecialTopicRelation> getSpecialTopicRelation() {
		return specialTopicRelation;
	}

	public void setSpecialTopicRelation(
			List<TSpecialTopicRelation> specialTopicRelation) {
		this.specialTopicRelation = specialTopicRelation;
	}

    public String getTopicTypeDesc() {
  		return topicTypeDesc;
  	}

  	public void setTopicTypeDesc(String topicTypeDesc) {
  		this.topicTypeDesc = topicTypeDesc;
  	} 
  	
    public String getRelationNum() {
		return relationNum;
	}

	public void setRelationNum(String relationNum) {
		this.relationNum = relationNum;
	}
	/****自定义字段end*****/
	
	
	
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSpecialTopic [id=" + id + ", title=" + title
				+ ", description=" + description + ", videoUrl=" + videoUrl
				+ ", topicType=" + topicType + ", updateTime=" + updateTime
				+ ", content=" + content + ", topicTypeDesc=" + topicTypeDesc
				+ ", relationNum=" + relationNum + ", picUrl=" + picUrl
				+ ", specialTopicRelation=" + specialTopicRelation
				+ ", productJson=" + productJson + "]";
	}
    
    
}