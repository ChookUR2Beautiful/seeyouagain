package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

public class TLiveSalaryFail {
    /**
     * ID
     */
    private Integer id;

    /**
     * 主播用户id
     */
    private Integer anchorId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 统计月份
     */
    private String countTime;

    /**
     * 失败信息
     */
    private String msg;

    /**
     * 是否处理 0:未处理 1:已处理
     */
    private Integer isDispose;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    
    private String name;
    
    private String phone;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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
     * 主播用户id
     * @return anchor_id 主播用户id
     */
    public Integer getAnchorId() {
        return anchorId;
    }

    /**
     * 主播用户id
     * @param anchorId 主播用户id
     */
    public void setAnchorId(Integer anchorId) {
        this.anchorId = anchorId;
    }

    /**
     * 用户id
     * @return uid 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 用户id
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 统计月份
     * @return count_time 统计月份
     */
    public String getCountTime() {
        return countTime;
    }

    /**
     * 统计月份
     * @param countTime 统计月份
     */
    public void setCountTime(String countTime) {
        this.countTime = countTime == null ? null : countTime.trim();
    }

    /**
     * 失败信息
     * @return msg 失败信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 失败信息
     * @param msg 失败信息
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    /**
     * 是否处理 0:未处理 1:已处理
     * @return is_dispose 是否处理 0:未处理 1:已处理
     */
    public Integer getIsDispose() {
        return isDispose;
    }

    /**
     * 是否处理 0:未处理 1:已处理
     * @param isDispose 是否处理 0:未处理 1:已处理
     */
    public void setIsDispose(Integer isDispose) {
        this.isDispose = isDispose;
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

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}