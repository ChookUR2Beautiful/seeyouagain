package com.xmniao.xmn.core.market.entity.activity.indiana;

public class FreshActivityIndianaDduonum {
    private Long id;

    private Integer boutId;

    private String orderNo;

    private Integer uid;

    private String nname;

    private Integer robotId;

    private Integer type;

    private String createTime;

    private Integer count;

    private String phone;

    // 用户类型
    private Integer giveType ;
    // 真实用户id
    private Integer winnerRid;


    public Integer getGiveType() {
        return giveType;
    }

    public void setGiveType(Integer giveType) {
        this.giveType = giveType;
    }

    public Integer getWinnerRid() {
        return winnerRid;
    }

    public void setWinnerRid(Integer winnerRid) {
        this.winnerRid = winnerRid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBoutId() {
        return boutId;
    }

    public void setBoutId(Integer boutId) {
        this.boutId = boutId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public Integer getRobotId() {
        return robotId;
    }

    public void setRobotId(Integer robotId) {
        this.robotId = robotId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}