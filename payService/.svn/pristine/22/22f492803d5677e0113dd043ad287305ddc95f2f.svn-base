package com.xmniao.enums;

/**
 * 状态吗
 *
 * @author liyuanbo
 * @create 2017-06-01 12:38
 **/
public enum WalletStatusCodeEnum {
    SUCCESS_STATUS("10000","操作成功"),

    FAIL_ERROR_STATUS("20000","操作失败"),
    PARAM_ERROR_STATUS("20001", "参数错误"),
    LIVE_ZBALANCE_LOCK_STATUS("20002","直播钱包鸟币锁定"),
	ZBALANCE_LOCK_STATUS("20003","佣金或赠送余额锁定");

    private String status;
    private String name;

    private WalletStatusCodeEnum(String status, String name) {
        this.status = status;
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }
}