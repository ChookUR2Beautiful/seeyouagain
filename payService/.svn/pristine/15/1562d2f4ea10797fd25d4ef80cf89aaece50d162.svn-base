package com.xmniao.enums;

/**
 * 红包状态
 *
 * @author liyuanbo
 * @create 2017-06-13 11:55
 **/
public enum RedPackagTypeStatusEnum {

    NOT(1, "未领完"),
    FINISH(2, "已领完"),
    RETURN(3, "已退回");
    private int status;
    private String name;

    private RedPackagTypeStatusEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public static String getName(int status) {
        for (RedPackagTypeStatusEnum redPackagTypeEnum : RedPackagTypeStatusEnum.values()) {
            if (redPackagTypeEnum.status == status) {
                return redPackagTypeEnum.getName();
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
