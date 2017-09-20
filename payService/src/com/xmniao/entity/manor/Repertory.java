package com.xmniao.entity.manor;

/**
 * @author liyuanbo
 * @create 2017-06-04 16:48
 **/
public class Repertory {
    private long uid;//用户ID
    private double nectarCount;//一共的花蜜数量
    private int nectarToday;//今日收获的花蜜数量
    private int extendSunRepertory ;//用户自己扩展的阳光数量
    private double sunCount;//目前的阳光数量
    private int sunToday;//今日收获的阳光数量

    public long getUid() {
        return uid;
    }

    public double getNectarCount() {
        return nectarCount;
    }

    public double getNectarToday() {
        return nectarToday;
    }

    public int getExtendSunRepertory() {
        return extendSunRepertory;
    }

    public double getSunCount() {
        return sunCount;
    }

    public double getSunToday() {
        return sunToday;
    }

    public void setNectarToday(int nectarToday) {
        this.nectarToday = nectarToday;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setNectarCount(double nectarCount) {
        this.nectarCount = nectarCount;
    }

    public void setExtendSunRepertory(int extendSunRepertory) {
        this.extendSunRepertory = extendSunRepertory;
    }

    public void setSunCount(double sunCount) {
        this.sunCount = sunCount;
    }

    public void setSunToday(int sunToday) {
        this.sunToday = sunToday;
    }

    @Override
    public String toString() {
        return "Repertory{" +
                "uid=" + uid +
                ", nectarCount=" + nectarCount +
                ", nectarToday=" + nectarToday +
                ", extendSunRepertory=" + extendSunRepertory +
                ", sunCount=" + sunCount +
                ", sunToday=" + sunToday +
                '}';
    }
}
