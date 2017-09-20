package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLivePublicMessage
 * 
 * 类描述： 直播普通聊天消息记录表实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-5 上午11:35:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLivePublicMessage extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7635985707255507632L;

	private Integer id;//主键ID
	
	private Integer maxId;//返回消息列表的最大Id

    private Integer sendLiverId;//消息发送者直播用户ID
    
    private String nname;//用户昵称

    private String sendLiverUname;//消息发送者昵称

    private Integer liveRecordId;//通告ID

    private String messagerGroupNo;//消息群组号

    private Date messSendTime;//消息发送时间

    private Integer messageType;//消息类型  1 普通消息 2 弹幕消息

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private String messagerTxt;//消息内容

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    /**
	 * @return the maxId
	 */
	public Integer getMaxId() {
		return maxId;
	}

	/**
	 * @param maxId the maxId to set
	 */
	public void setMaxId(Integer maxId) {
		this.maxId = maxId;
	}

	public Integer getSendLiverId() {
        return sendLiverId;
    }

    public void setSendLiverId(Integer sendLiverId) {
        this.sendLiverId = sendLiverId;
    }

    
    /**
	 * @return the nname
	 */
	public String getNname() {
		return nname;
	}

	/**
	 * @param nname the nname to set
	 */
	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getSendLiverUname() {
        return sendLiverUname;
    }

    public void setSendLiverUname(String sendLiverUname) {
        this.sendLiverUname = sendLiverUname == null ? null : sendLiverUname.trim();
    }

    public Integer getLiveRecordId() {
        return liveRecordId;
    }

    public void setLiveRecordId(Integer liveRecordId) {
        this.liveRecordId = liveRecordId;
    }

    public String getMessagerGroupNo() {
        return messagerGroupNo;
    }

    public void setMessagerGroupNo(String messagerGroupNo) {
        this.messagerGroupNo = messagerGroupNo == null ? null : messagerGroupNo.trim();
    }

    public Date getMessSendTime() {
        return messSendTime;
    }

    public void setMessSendTime(Date messSendTime) {
        this.messSendTime = messSendTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
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

    public String getMessagerTxt() {
        return messagerTxt;
    }

    public void setMessagerTxt(String messagerTxt) {
        this.messagerTxt = messagerTxt == null ? null : messagerTxt.trim();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLivePublicMessage [id=" + id + ", maxId=" + maxId
				+ ", sendLiverId=" + sendLiverId + ", sendLiverUname="
				+ sendLiverUname + ", liveRecordId=" + liveRecordId
				+ ", messagerGroupNo=" + messagerGroupNo + ", messSendTime="
				+ messSendTime + ", messageType=" + messageType
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", messagerTxt=" + messagerTxt + "]";
	}
    
    
    
}