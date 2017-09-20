package com.xmniao.xmn.core.manor.entity;

/**
 * 花朵统计Bean
 * Created by yang.qiang on 2017/6/21.
 */
public class ManorFlowerCount {

    private Integer count;
    private Integer location;
    private Integer type;
    private Integer perishType;        // 1 未枯萎花朵  2 即将枯萎  3 已枯萎


    public Integer getPerishType() {
        return perishType;
    }

    public void setPerishType(Integer perishType) {
        this.perishType = perishType;
    }

    public void addCount(int addNum){
        this.count += addNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

