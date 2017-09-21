package com.xmniao.enums;

/**
 * 状态吗
 *
 * @author liyuanbo
 * @create 2017-06-01 12:38
 **/
public enum BusinessStatusCodeEnum {
    SUCCESS_STATUS("10000","操作成功"),

    FAIL_ERROR_STATUS("20000","操作失败"),
    PARAM_ERROR_STATUS("20001", "参数错误"),
    AMONUT_NOT_ENOUGH_STATUS("20002","余额不足"),
    REPEATEDLY_COMMIT_STATUS("20003","重复提交"),
    REPEATEDLY_GIVE_STAUTS("20004","重复领取"),
    SYSTEM_ERROR_STATUS("20005","系统发生异常"),
    EXPRIE_TIME_STATUS("20006","时间已过期"),
    NOT_FOUND_STATUS("20007","数据不存在"),
    GIVE_REDPACKAGE_FINISH_STATUS("20008","红包已经领取完毕"),
    SYTEM_CONFING_STATUS("20009","系统配置错误"),
    SUN_REPERTORY_FULL("20010","用户仓库已满"),
    SUN_REPERTORY_NOT_FULL("20011","用户没有溢出数量"),
    REPEATEDLY_ACTIVE_MANOR("20012","用户不能重复激活庄园"),
    WALLET_LOCK("20013","当前用户的钱包被锁定"),
    PARAM_NOT_MATCH("20014","数据参数不匹配"),
    NOT_ACTIVE_MANOR("20015","用户没有激活庄园"),
    ACCOUNT_LIMIT_CONSUM("20016","当前用户的鸟币已被限制"),
    ACCOUNT_BIRD_LOCK("20017","当前用户的你被锁定,不能进行操作");

    private String status;
    private String name;

    private BusinessStatusCodeEnum(String status, String name) {
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