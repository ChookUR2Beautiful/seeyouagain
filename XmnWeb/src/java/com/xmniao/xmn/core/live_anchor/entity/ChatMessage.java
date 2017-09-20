/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ChatMessage
 * 
 * 类描述： 直播聊天室消息实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-4 下午3:49:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class ChatMessage extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6000289412420138491L;
	
	private String liveRecordId;//直播ID
	
	private String phone;//用户手机
	
	private String text;//消息内容

	/**
	 * @return the liveRecordId
	 */
	public String getLiveRecordId() {
		return liveRecordId;
	}

	/**
	 * @param liveRecordId the liveRecordId to set
	 */
	public void setLiveRecordId(String liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChatMessage [liveRecordId=" + liveRecordId + ", phone=" + phone
				+ ", text=" + text + "]";
	}
	
	
}
