package com.xmniao.xmn.core.business_statistics.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.FastfdsConstant;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographPage
 * 
 * 类描述： 微图助力模板页面实体
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午3:22:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TMicrographPage extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5291483301882283818L;

	private Integer id;//主键

    private Integer templateId;//模板主键

    private Integer page;//页数(第几页)

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private String backgroundImage;//底图
    
    private String backgroundImageStr;//地图(带服务器路径)
    
    private Integer status;//状态 0:正常 1:删除
    
    private Integer width;//页面宽度(单位px)
    
    private Integer height;//页面高度(单位px)
    
    private Integer moduleSize;//模块总数
    
    private List<TMicrographModule> source;//页面模块

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
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

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage == null ? null : backgroundImage.trim();
    }
    
    

    /**
	 * @return the backgroundImageStr
	 */
	public String getBackgroundImageStr() {
		return backgroundImageStr = FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP+backgroundImage;
	}

	/**
	 * @param backgroundImageStr the backgroundImageStr to set
	 */
	public void setBackgroundImageStr(String backgroundImageStr) {
		this.backgroundImageStr = backgroundImageStr;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return the moduleSize
	 */
	public Integer getModuleSize() {
		return moduleSize;
	}

	/**
	 * @param moduleSize the moduleSize to set
	 */
	public void setModuleSize(Integer moduleSize) {
		this.moduleSize = moduleSize;
	}

	
	/**
	 * @return the source
	 */
	public List<TMicrographModule> getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(List<TMicrographModule> source) {
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TMicrographPage [id=" + id + ", templateId=" + templateId
				+ ", page=" + page + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", backgroundImage="
				+ backgroundImage + ", backgroundImageStr="
				+ backgroundImageStr + ", status=" + status + ", moduleSize="
				+ moduleSize + ", source=" + source + "]";
	}
    
    
}