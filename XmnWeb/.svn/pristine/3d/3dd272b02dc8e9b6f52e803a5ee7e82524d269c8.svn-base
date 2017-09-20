/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BLiveAnchorStartImage
 * 
 * 类描述：
 * 
 * 创建人：jianming
 * 
 * 创建时间：2016年9月5日 上午11:24:35
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

public class BLiveAnchorStartImage extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4630766880651861193L;
	private Integer id; // 主键
	
	private Integer imageType; // 页面图(1.经营页面入口图 2.开启直播banner图)
	
	private String imageUrl; // 图片
	
	private String mediaUrl; // 链接地址
	
	private String stringPageImage; // 转化后的页面图
	
	private Integer status; //有效状态 ： 1有效  0 无效 ',
   
	private Date createTime;  //创建时间

	private Date updateTime;  //修改时间

	public String getStringPageImage() {
		if (imageType != null) {
			if (imageType == 1)
				return "经营页面入口图";
			if (imageType == 2)
				return "开启直播banner图";
		}
		return stringPageImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getImageType() {
		return imageType;
	}

	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl == null ? null : imageUrl.trim();
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl == null ? null : mediaUrl.trim();
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

}
