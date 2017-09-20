package com.xmniao.entity.manor;

import java.io.Serializable;

/**
 * 统计用户的道具总数
 *
 * @author liyuanbo
 * @create 2017-06-01 13:40
 **/
public class PropsStatistics implements Serializable{
    private long uid;//用户ID
    private int type;//道具类型
    private int number;//道具总数

    public int getType() {
        return type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "PropsStatistics{" +
                "uid=" + uid +
                ", type=" + type +
                ", number=" + number +
                '}';
    }
}
