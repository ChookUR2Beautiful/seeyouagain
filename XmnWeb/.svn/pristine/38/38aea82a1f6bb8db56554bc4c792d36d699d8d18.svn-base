package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TicketsDetail extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * t_fashion_tickets主键
     */
    private Integer fid;

    /**
     * t_fashion_tickets_seat主键
     */
    private Integer sid;

    /**
     * 桌号/排号
     */
    private Integer zoneNo;

    /**
     * 座位号
     */
    private Integer seatNo;

    /**
     * 销售状态 0.未售出 1.售出锁定 2.已售出 3.拒绝出售
     */
    private Integer sellStatus;

    /**
     * 售出锁定时间
     */
    private Date lockTime;

    /**
     * 使用状态 0.未使用 1.已使用 3.已取消
     */
    private Integer useStatus;

    /**
     * 使用时间
     */
    private Date useTime;

    /**
     * 归属UID
     */
    private Integer uid;

    /**
     * 购买订单号
     */
    private String orderNo;
    
    /**
     * 停止使用日期
     */
    private Date useEndTime;
    
    private String orderStatusStr;
    
    private String seatName;

    public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public String getOrderStatusStr() {
    	try {
			if(useStatus==1){
				return "已验证";
			}else if(new Date().getTime()>useEndTime.getTime()){
				return "已过期";
			}else {
				return "未验证";
			}
		} catch (Exception e) {
			return "数据有误";
		}
	}

	public void setOrderStatusStr(String orderStatusStr) {
		this.orderStatusStr = orderStatusStr;
	}

	public Date getUseEndTime() {
		return useEndTime;
	}

	public void setUseEndTime(Date useEndTime) {
		this.useEndTime = useEndTime;
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
     * t_fashion_tickets主键
     * @return fid t_fashion_tickets主键
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * t_fashion_tickets主键
     * @param fid t_fashion_tickets主键
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * t_fashion_tickets_seat主键
     * @return sid t_fashion_tickets_seat主键
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * t_fashion_tickets_seat主键
     * @param sid t_fashion_tickets_seat主键
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 桌号/排号
     * @return zone_no 桌号/排号
     */
    public Integer getZoneNo() {
        return zoneNo;
    }

    /**
     * 桌号/排号
     * @param zoneNo 桌号/排号
     */
    public void setZoneNo(Integer zoneNo) {
        this.zoneNo = zoneNo;
    }

    /**
     * 座位号
     * @return seat_no 座位号
     */
    public Integer getSeatNo() {
        return seatNo;
    }

    /**
     * 座位号
     * @param seatNo 座位号
     */
    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    /**
     * 销售状态 0.未售出 1.售出锁定 2.已售出 3.拒绝出售
     * @return sell_status 销售状态 0.未售出 1.售出锁定 2.已售出 3.拒绝出售
     */
    public Integer getSellStatus() {
        return sellStatus;
    }

    /**
     * 销售状态 0.未售出 1.售出锁定 2.已售出 3.拒绝出售
     * @param sellStatus 销售状态 0.未售出 1.售出锁定 2.已售出 3.拒绝出售
     */
    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    /**
     * 售出锁定时间
     * @return lock_time 售出锁定时间
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * 售出锁定时间
     * @param lockTime 售出锁定时间
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 使用状态 0.未使用 1.已使用 3.已取消
     * @return use_status 使用状态 0.未使用 1.已使用 3.已取消
     */
    public Integer getUseStatus() {
        return useStatus;
    }

    /**
     * 使用状态 0.未使用 1.已使用 3.已取消
     * @param useStatus 使用状态 0.未使用 1.已使用 3.已取消
     */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * 使用时间
     * @return use_time 使用时间
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * 使用时间
     * @param useTime 使用时间
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * 归属UID
     * @return uid 归属UID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 归属UID
     * @param uid 归属UID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 购买订单号
     * @return order_no 购买订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 购买订单号
     * @param orderNo 购买订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }
}