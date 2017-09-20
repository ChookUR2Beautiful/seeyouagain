package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.xmniao.xmn.core.base.BaseEntity;

public class CommentMedia extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 评论编号
     */
    private Integer commentId;

    /**
     * 媒体标识 1 图片 2 视频 
     */
    private Integer mediaType;

    /**
     * 文件url
     */
    private String mediaUrl;

    /**
     * 排列
     */
    private Integer sort;
    
    
    private Integer videoType;

    public Integer getVideoType() {
    	if(StringUtils.isBlank(mediaUrl)){
    		return 1;
    	}
    	if(mediaUrl.endsWith("mp4")){
    		return 2;
    	}else
    		return 1;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}

	/**
     * 创建时间
     */
    private Date createTime;

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
     * 评论编号
     * @return comment_id 评论编号
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 评论编号
     * @param commentId 评论编号
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 媒体标识 1 图片 2 视频 
     * @return media_type 媒体标识 1 图片 2 视频 
     */
    public Integer getMediaType() {
        return mediaType;
    }

    /**
     * 媒体标识 1 图片 2 视频 
     * @param mediaType 媒体标识 1 图片 2 视频 
     */
    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 文件url
     * @return media_url 文件url
     */
    public String getMediaUrl() {
        return mediaUrl;
    }

    /**
     * 文件url
     * @param mediaUrl 文件url
     */
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl == null ? null : mediaUrl.trim();
    }

    /**
     * 排列
     * @return sort 排列
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排列
     * @param sort 排列
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}