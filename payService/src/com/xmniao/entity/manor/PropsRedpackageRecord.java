package com.xmniao.entity.manor;

/**
 * 红包领取记录表
 * @author liyuanbo
 * @create 2017-06-03 13:56
 **/
public class PropsRedpackageRecord {
    private long id;
    private long redpackageId;//发送红包表ID
    private long giveUid;//领取红包的用户ID
    private double amount;//领取的红包金额
    private String createTime ;//创建时间
    private String updateTime;//更新时间
    private int status;//状态

    public long getId() {
        return id;
    }

    public long getRedpackageId() {
        return redpackageId;
    }

    public long getGiveUid() {
        return giveUid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRedpackageId(long redpackageId) {
        this.redpackageId = redpackageId;
    }

    public void setGiveUid(long giveUid) {
        this.giveUid = giveUid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PropsRedpackageRecord{" +
                "id=" + id +
                ", redpackageId=" + redpackageId +
                ", giveUid=" + giveUid +
                ", amount=" + amount +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", status=" + status +
                '}';
    }
}
