package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 直播礼物信息类
 * @author yhl
 * 2016年8月10日17:23:49
 * */
public class LiveSelfGiftInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6650464273838935486L;
	
	// 直播礼物id
	private Long id ;
	
	//礼物id 
	private Long giftId;
	
	//观看直播的用户ID
	private Long liverId;
	
	//礼物个数
	private Integer giftNums;
	
	private Date createTime;
	
	private Date updateTime;
	
	//mq消息key标示 是否重复发送
	private String messageKey;
	
	public Long getGiftId() {
		return giftId;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLiverId() {
		return liverId;
	}

	public void setLiverId(Long liverId) {
		this.liverId = liverId;
	}

	public Integer getGiftNums() {
		return giftNums;
	}

	public void setGiftNums(Integer giftNums) {
		this.giftNums = giftNums;
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

	@Override
	public String toString() {
		return "LiveSelfGiftInfo [id=" + id + ", giftId=" + giftId
				+ ", liverId=" + liverId + ", giftNums=" + giftNums
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", messageKey=" + messageKey + "]";
	}

}
