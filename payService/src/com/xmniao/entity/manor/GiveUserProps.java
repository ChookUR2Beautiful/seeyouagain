package com.xmniao.entity.manor;

/**
 * @author liyuanbo
 * @create 2017-07-03 20:03
 **/
public class GiveUserProps {
    private long id ;//交易ID
    private String transNo;//交易记录号
    private long uid ;//赠送的用户ID
    private long giveUid;//被赠送的用户ID
    private int status ;//状态 1.未领取 2.已领取
    private int coverValue ;//转换的记录
    private int number ;//赠送的数量
    private int propsType;//道具类型
    private String createTime ; //创建时间
    private String updateTime ; //更新时间

    public String getTransNo() {
        return transNo;
    }

    public long getUid() {
        return uid;
    }

    public long getGiveUid() {
        return giveUid;
    }

    public int getStatus() {
        return status;
    }

    public int getCoverValue() {
        return coverValue;
    }

    public int getNumber() {
        return number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setGiveUid(long giveUid) {
        this.giveUid = giveUid;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCoverValue(int coverValue) {
        this.coverValue = coverValue;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setPropsType(int propsType) {
        this.propsType = propsType;
    }

    public int getPropsType() {
        return propsType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GiveUserProps{" +
                "id=" + id +
                ", transNo='" + transNo + '\'' +
                ", uid=" + uid +
                ", giveUid=" + giveUid +
                ", status=" + status +
                ", coverValue=" + coverValue +
                ", number=" + number +
                ", propsType=" + propsType +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
