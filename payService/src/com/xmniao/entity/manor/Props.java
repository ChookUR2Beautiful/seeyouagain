package com.xmniao.entity.manor;

/**
 * 道具表
 *
 * @author liyuanbo
 * @create 2017-06-01 15:50
 **/
public class Props {

    private long id;
    private long uid;
    private double number;
    private int propsType;
    private int propsSource;
    private double nectarTotal ;//花蜜累计的总数
    private int flowerMonthType ; //花苗的套餐月数
    private String createTime;
    private String updateTime;

    public long getId() {
        return id;
    }

    public long getUid() {
        return uid;
    }

    public double getNumber() {
        return number;
    }

    public int getPropsType() {
        return propsType;
    }


    public int getPropsSource() {
        return propsSource;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public void setPropsType(int propsType) {
        this.propsType = propsType;
    }



    public void setPropsSource(int propsSource) {
        this.propsSource = propsSource;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setNectarTotal(double nectarTotal) {
        this.nectarTotal = nectarTotal;
    }

    public double getNectarTotal() {
        return nectarTotal;
    }

    public int getFlowerMonthType() {
        return flowerMonthType;
    }

    public void setFlowerMonthType(int flowerMonthType) {
        this.flowerMonthType = flowerMonthType;
    }

    @Override
    public String toString() {
        return "Props{" +
                "id=" + id +
                ", uid=" + uid +
                ", number=" + number +
                ", propsType=" + propsType +
                ", propsSource=" + propsSource +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
