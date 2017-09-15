package com.xmniao.xmn.core.seller.entity;

import java.util.Date;

/**
 * 
 * @ClassName:ExperienceCommentMedia
 * @Description:美食探店点评图片视频表
 * @Author:xw
 * @Date:2017年5月13日下午2:27:40
 */
public class ExperienceCommentMedia {

	private Integer id;
	private Integer commentId;		//评论编号
	private Integer mediaType;		//媒体标识 1 图片 2 视频
	private String mediaUrl;		//文件url
	private Integer sort;			//排列
	private Date createTime;		//创建时间
	
	//为了方便客户端， 回显图片url的同时 给一个相对路径，未修改的图片/视频 直接使用这个URL保存数据库
	private String url;			
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getMediaType() {
		return mediaType;
	}
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaUrl() {
		return mediaUrl;
	}
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
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
	@Override
	public String toString() {
		return "ExperienceCommentMedia [id=" + id + ", commentId=" + commentId
				+ ", mediaType=" + mediaType + ", mediaUrl=" + mediaUrl
				+ ", sort=" + sort + ", createTime=" + createTime + ", url="
				+ url + "]";
	}
	
	
}
