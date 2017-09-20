package com.xmniao.xmn.core.live_anchor.entity;


import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveBroadcast
 * 
 * 类描述： 直播广播实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-24 下午2:49:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveBroadcast extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6592211875207297458L;

	private Integer id;//广播ID

    private Integer assignRoom;//是否指定房间  默认0 否，1 是

    private Integer recordId;//通告ID
    
    private String nickname;//主播昵称

    private String content;//内容

    private Integer playAmount;//播放次数

    private Integer immediate;//立即发送 默认0 否， 1 是

    private Date sendTime;//发送时间
    
    private String sendTimeStr;//发送时间,格式 yyyy-mm-dd hh:mi:ss

    private Integer sendStatus;//发送状态 默认	0未发送 ,	1已发送

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignRoom() {
        return assignRoom;
    }

    public void setAssignRoom(Integer assignRoom) {
        this.assignRoom = assignRoom;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
    
    

    /**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPlayAmount() {
        return playAmount;
    }

    public void setPlayAmount(Integer playAmount) {
        this.playAmount = playAmount;
    }

    public Integer getImmediate() {
        return immediate;
    }

    public void setImmediate(Integer immediate) {
        this.immediate = immediate;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    
    

    /**
	 * @return the sendTimeStr
	 */
	public String getSendTimeStr() {
		if(sendTime==null){
			return null;
		}
		return sendTimeStr = DateUtil.formatDate(sendTime, DateUtil.Y_M_D_HMS);
	}

	/**
	 * @param sendTimeStr the sendTimeStr to set
	 */
	public void setSendTimeStr(String sendTimeStr) {
		this.sendTimeStr = sendTimeStr;
	}

	public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveBroadcast [id=" + id + ", assignRoom=" + assignRoom
				+ ", recordId=" + recordId + ", nickname=" + nickname
				+ ", content=" + content + ", playAmount=" + playAmount
				+ ", immediate=" + immediate + ", sendTime=" + sendTime
				+ ", sendTimeStr=" + sendTimeStr + ", sendStatus=" + sendStatus
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
    
    
}