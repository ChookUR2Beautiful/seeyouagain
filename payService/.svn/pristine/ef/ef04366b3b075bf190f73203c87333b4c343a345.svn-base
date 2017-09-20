package com.xmniao.enums;

/**
 * 道具消费渠道（这里的消费是增加和减少）
 *
 * @author liyuanbo
 * @create 2017-05-31 16:59
 **/
public enum PropsChannelEnum {
    BUY_FLOWER_SEEDLING(1, "购买花苗"),
    GIVE(2,"赠送"),
    GROW_FLOWERS(3,"种花"),
    ACTIVE_MANOR(5, "激活庄园"),
    RENEW_MANOR(6, "续费庄园"),
    SEND_REDPACKAGE(8,"发送红包"),
    GET_NECTARY(9,"领取蜜罐"),
    GIVE_NECTARY(10,"赠送蜜罐"),
    EXCHANGE_VOUCHER(11,"兑换抵用券"),
    EXCHANGE_MONEY_REDPACKAGE(12,"出售蜜罐"),
    GET_REDPACKAGE(13,"领取红包"),
    GET_GIVE(14,"领取赠送的道具"),
    EVERY_DAY_EARNINGS(15,"每日收益"),
    RECOMMEND_REWARDS(17,"推荐奖励"),
    RETURN_REDPACKAGE(18,"退回红包"),
    RECHARGE_CONVERSION(19,"充值转换");


    private int channel;
    private String remark;

    private PropsChannelEnum(int channel, String remark) {
        this.channel = channel;
        this.remark = remark;
    }

    public static String getName(int channel){
        for(PropsChannelEnum propsChannelEnum : PropsChannelEnum.values()){
            if(propsChannelEnum.getChannel() == channel){
                return propsChannelEnum.getRemark();
            }
        }
        return null;
    }

    public static int getChannel(int channel){
        for(PropsChannelEnum propsChannelEnum : PropsChannelEnum.values()){
            if(propsChannelEnum.getChannel() == channel){
                return propsChannelEnum.getChannel();
            }
        }
        return 0;
    }


    public int getChannel() {
        return channel;
    }

    public String getRemark() {
        return remark;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
