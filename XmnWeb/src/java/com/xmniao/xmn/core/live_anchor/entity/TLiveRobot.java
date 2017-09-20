package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveRobot
 *
 * 类描述：机器人实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-24下午7:42:09
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveRobot extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3424222688464899654L;

	private Integer id;//机器人编号

    private String robotName;//机器人昵称

    private String avatar;//机器人头像

    private Integer sex;//性别 1男,2女

    private Integer concernNums;//关注用户总数

    private Integer giveGiftsNums;//送出礼物总数

    private Integer rankNo;//用户当前等级数

    private Integer status;//是否启用  1 启用  0 未启用

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName == null ? null : robotName.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    
    
    /**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getConcernNums() {
        return concernNums;
    }

    public void setConcernNums(Integer concernNums) {
        this.concernNums = concernNums;
    }

    public Integer getGiveGiftsNums() {
        return giveGiftsNums;
    }

    public void setGiveGiftsNums(Integer giveGiftsNums) {
        this.giveGiftsNums = giveGiftsNums;
    }

    public Integer getRankNo() {
        return rankNo;
    }

    public void setRankNo(Integer rankNo) {
        this.rankNo = rankNo;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveRobot [id=" + id + ", robotName=" + robotName
				+ ", avatar=" + avatar + ", sex=" + sex + ", concernNums="
				+ concernNums + ", giveGiftsNums=" + giveGiftsNums
				+ ", rankNo=" + rankNo + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
    
    
}