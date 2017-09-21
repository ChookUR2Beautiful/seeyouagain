package com.xmniao.domain.manor;

/**
 *
 * 黄金庄园操作Bean
 * Created by yang.qiang on 2017/6/6.
 */
public class ManorOperate {
    private Integer location;       // 线路位置 0:走  1:中  2:右
    private Integer quantity;       // 数量
    private Integer flowerType;     // 施肥类型 0:花苗；1:花朵
    private Integer giveUid;        // 花朵赠送人uid


    public ManorOperate() {
        super();
    }

    public ManorOperate(Integer location, Integer quantity, Integer flowerType) {
        this.location = location;
        this.quantity = quantity;
        this.flowerType = flowerType;
    }


    public Integer getGiveUid() {
        return giveUid;
    }

    public void setGiveUid(Integer giveUid) {
        this.giveUid = giveUid;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getFlowerType() {
        return flowerType;
    }

    public void setFlowerType(Integer flowerType) {
        this.flowerType = flowerType;
    }
}
