package com.xmniao.enums;

/**
 * 阳光道具来源
 *
 * @author liyuanbo
 * @create 2017-05-31 15:30
 **/
public enum PropsSourceEnum {
    FLOWER_SEEDING_GIVE(10001, "园又赠送"),
    FLOWER_SEEDING_OWN_BUY(10002, "自己购买"),
    FLOWER_SEEDIN_GIVE_OWN(10003, "激活庄园赠送自己花朵"),

    NECTAR_EVERY_DAY(20001, "每日收益"),

    COMMON_ENERGY_ADD(30001, "充值累计"),
    GOLD_ENERGY_ADD(40001,"黄金能量充值累计");

    private int sourceType; //来源类型
    private String remark;//来源备注


    private PropsSourceEnum(int sourceType, String remark) {
        this.sourceType = sourceType;
        this.remark = remark;

    }

    public static String getRemark(int type) {
        for (PropsSourceEnum sourceEnum : PropsSourceEnum.values()) {
            if (sourceEnum.getSourceType() == type) {
                return sourceEnum.getRemark();
            }
        }
        return null;
    }

    public int getSourceType() {
        return sourceType;
    }

    public String getRemark() {
        return remark;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }


}

