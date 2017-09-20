/**
 * 
 */
package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：KillBean
 * 
 * 类描述：秒杀活动json辅助类
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月20日 下午8:06:13
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

public class KillBean<T> {
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private Integer orderLimit;

	private String image;

	private String title;

	private List<JsonToGroupBean<T>> productJson;

	private Long spikeId; // 秒杀活动id

	public Long getSpikeId() {
		return spikeId;
	}

	public void setSpikeId(Long spikeId) {
		this.spikeId = spikeId;
	}

	public Integer getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<JsonToGroupBean<T>> getProductJson() {
		return productJson;
	}

	public void setProductJson(List<JsonToGroupBean<T>> productJson) {
		this.productJson = productJson;
	}

}
