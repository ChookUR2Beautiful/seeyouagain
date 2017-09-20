package com.xmniao.enums;

/**
 * 道具类型
 *
 * @author liyuanbo
 * @create 2017-05-31 15:07
 **/
public enum PropsTypeEnum {
    FLOWER(1, "花朵"),
    NECTAR(2, "花蜜"),
    COMMON_ENERGY(3, "普通能量"),
    GOLD_ENERGY(4,"黄金能量");

    private int type;
    private String name;

    private PropsTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
}
