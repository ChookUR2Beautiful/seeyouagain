/**   
 * 文件名：TVideo.java   
 *    
 * 日期：2014年11月19日10时36分55秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.business_cooperation.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TVideo
 * 
 * 类描述：视频讲解
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时36分55秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVideo extends BaseEntity {

	private static final long serialVersionUID = -7848791879967474608L;
	private Integer vid;// 视频ID
	private String videoname;// 视频名称
	private String introduce;// 视频介绍
	private String vurl;// 视频URL
	private String format;// 视频格式mp4|rmvb
	private String duration;// 时长
	private Integer number;// 下载次数
	private Integer status;// 默认1=启用|0=停用
	private Long size;// 视频大小XXX字节

	public String getStatusText(){
		if(status == null) return null;
		if(status == 1) return "启用";
		if(status == 0) return "停用";
		return null;
	}
	
	/**
	 * 创建一个新的实例 TVideo.
	 * 
	 */
	public TVideo() {
		super();
	}

	public TVideo(Integer vid) {
		this.vid = vid;
	}

	/**
	 * vid
	 * 
	 * @return the vid
	 */
	public Integer getVid() {
		return vid;
	}

	/**
	 * @param vid
	 *            the vid to set
	 */
	public void setVid(Integer vid) {
		this.vid = vid;
	}

	/**
	 * videoname
	 * 
	 * @return the videoname
	 */
	public String getVideoname() {
		return videoname;
	}

	/**
	 * @param videoname
	 *            the videoname to set
	 */
	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	/**
	 * introduce
	 * 
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce
	 *            the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * vurl
	 * 
	 * @return the vurl
	 */
	public String getVurl() {
		return vurl;
	}

	/**
	 * @param vurl
	 *            the vurl to set
	 */
	public void setVurl(String vurl) {
		this.vurl = vurl;
	}

	/**
	 * format
	 * 
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * duration
	 * 
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * number
	 * 
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * size
	 * 
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVideo [vid=" + vid + ", videoname=" + videoname + ", introduce=" + introduce + ", vurl=" + vurl + ", format=" + format + ", duration=" + duration + ", number=" + number + ", status=" + status + ", size=" + size + ", ]";
	}
}
