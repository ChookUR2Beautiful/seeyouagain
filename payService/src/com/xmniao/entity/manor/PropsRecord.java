package com.xmniao.entity.manor;

/**
 * 道具操作记录表
 *
 * @author liyuanbo
 * @create 2017-05-31 16:00
 **/
public class PropsRecord {

    private long id;
    private String transNo; //交易号
    private String batchNo; //批次号
    private long uid; //操作用户ID
    private long propsId;//道具表ID
    private int type;//操作类型  1.增加 2.减少
    private int propsType;//道具类型
    private int channel;//出发途径
    private long commonUid ;//兑换的数量
    private double  commonNumber ;//兑换的额度
    private double num;//增加或者减少的数量
    private double qnum;//操作前道具的数量
    private double hnum;//操作道具后的数量
    private String createTime; //创建时间
    private String remark;//备注

    public long getId() {
        return id;
    }

    public String getTransNo() {
        return transNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public long getUid() {
        return uid;
    }

    public long getPropsId() {
        return propsId;
    }

    public int getType() {
        return type;
    }

    public int getPropsType() {
        return propsType;
    }

    public int getChannel() {
        return channel;
    }

    public double getNum() {
        return num;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setPropsId(long propsId) {
        this.propsId = propsId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPropsType(int propsType) {
        this.propsType = propsType;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setQnum(double qnum) {
        this.qnum = qnum;
    }

    public void setHnum(double hnum) {
        this.hnum = hnum;
    }

    public double getQnum() {
        return qnum;
    }

    public double getHnum() {
        return hnum;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public long getCommonUid() {
        return commonUid;
    }

    public double getCommonNumber() {
        return commonNumber;
    }

    public void setCommonUid(long commonUid) {
        this.commonUid = commonUid;
    }

    public void setCommonNumber(double commonNumber) {
        this.commonNumber = commonNumber;
    }

    @Override
    public String toString() {
        return "PropsRecord{" +
                "id=" + id +
                ", transNo='" + transNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", uid=" + uid +
                ", propsId=" + propsId +
                ", type=" + type +
                ", propsType=" + propsType +
                ", channel=" + channel +
                ", commonUid=" + commonUid +
                ", commonNumber=" + commonNumber +
                ", num=" + num +
                ", qnum=" + qnum +
                ", hnum=" + hnum +
                ", createTime='" + createTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
