package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.FastfdsConstant;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：BLiveAnchorImage
 *
 * 类描述：主播相册实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-18下午4:23:19
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BLiveAnchorImage extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6854404377607297348L;

	private Integer id;//相册id

    private Integer anchorId;//主播id
    
    private Integer imageType;//相册类型 1 主播  2 机器人

    private String anchorImage;//主播相册地址
    
    private String anchorImageStr;//主播相册地址绝对路径

    private Integer status;//状态,默认 1启用   0停用

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private String imageComment;//相册描述
    
    private Integer sort;//排序值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(Integer anchorId) {
        this.anchorId = anchorId;
    }
    
    

    /**
	 * @return the imageType
	 */
	public Integer getImageType() {
		return imageType;
	}

	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}

	public String getAnchorImage() {
        return anchorImage;
    }

    public void setAnchorImage(String anchorImage) {
        this.anchorImage = anchorImage == null ? null : anchorImage.trim();
    }
    
    

    /**
	 * @return the anchorImageStr
	 */
	public String getAnchorImageStr() {
		if(anchorImage==null){
			return null;
		}
		anchorImageStr=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP+anchorImage;
		return anchorImageStr;
	}

	/**
	 * @param anchorImageStr the anchorImageStr to set
	 */
	public void setAnchorImageStr(String anchorImageStr) {
		this.anchorImageStr = anchorImageStr;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getImageComment() {
        return imageComment;
    }

    public void setImageComment(String imageComment) {
        this.imageComment = imageComment == null ? null : imageComment.trim();
    }
    
    

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BLiveAnchorImage [id=" + id + ", anchorId=" + anchorId
				+ ", imageType=" + imageType + ", anchorImage=" + anchorImage
				+ ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", imageComment="
				+ imageComment + ", sort=" + sort + "]";
	}
    
    
}