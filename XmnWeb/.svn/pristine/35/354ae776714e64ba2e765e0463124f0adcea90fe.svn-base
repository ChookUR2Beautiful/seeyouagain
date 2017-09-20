package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.FastfdsConstant;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRecordBanner
 * 
 * 类描述： 直播通告封面轮播图实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-1 下午8:23:20 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveRecordBanner extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3949466001091932431L;

	private Integer id;//业务主键

    private Integer recordId;//通告ID

    private String picUrl;//图片地址
    
    private String picUrlStr;//绝对路径图片地址

    private String type;//图片类型,默认001 通告封面轮播图

    private Date createTime;//创建时间

    private Integer sortVal;//排序值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    
    /**
	 * @return the picUrlStr
	 */
	public String getPicUrlStr() {
		if(picUrl==null){
			return null;
		}
		picUrlStr=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP+picUrl;
		return picUrlStr;
	}

	/**
	 * @param picUrlStr the picUrlStr to set
	 */
	public void setPicUrlStr(String picUrlStr) {
		this.picUrlStr = picUrlStr;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveRecordBanner [id=" + id + ", recordId=" + recordId
				+ ", picUrl=" + picUrl + ", type=" + type + ", createTime="
				+ createTime + ", sortVal=" + sortVal + "]";
	}
    
    
}