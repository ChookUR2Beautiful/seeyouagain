package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class IndianaDduonum extends BaseEntity{
    /**
     * 夺宝号码
     */
    private Long id;

    /**
     * 夺宝期数id
     */
    private Integer boutId;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 用户昵称
     */
    private String nname;

    /**
     * 机器人ID
     */
    private Integer robotId;

    /**
     * 类型  0:用户  1:机器人
     */
    private Integer type;

    /**
     * 购买时间
     */
    private String createTime;
    
    private String phone;
    
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
     * 夺宝号码
     * @return id 夺宝号码
     */
    public Long getId() {
        return id;
    }

    /**
     * 夺宝号码
     * @param id 夺宝号码
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 夺宝期数id
     * @return bout_id 夺宝期数id
     */
    public Integer getBoutId() {
        return boutId;
    }

    /**
     * 夺宝期数id
     * @param boutId 夺宝期数id
     */
    public void setBoutId(Integer boutId) {
        this.boutId = boutId;
    }

    /**
     * 用户ID
     * @return uid 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 用户ID
     * @param uid 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 用户昵称
     * @return nname 用户昵称
     */
    public String getNname() {
        return nname;
    }

    /**
     * 用户昵称
     * @param nname 用户昵称
     */
    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    /**
     * 机器人ID
     * @return robot_id 机器人ID
     */
    public Integer getRobotId() {
        return robotId;
    }

    /**
     * 机器人ID
     * @param robotId 机器人ID
     */
    public void setRobotId(Integer robotId) {
        this.robotId = robotId;
    }

    /**
     * 类型  0:用户  1:机器人
     * @return type 类型  0:用户  1:机器人
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型  0:用户  1:机器人
     * @param type 类型  0:用户  1:机器人
     */
    public void setType(Integer type) {
        this.type = type;
    }

    
    
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 方法描述：预设中奖
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午6:38:58 <br/>
	 * @param dduonum
	 */
	public void setWinner(IndianaDduonum dduonum) {
		
	}
}