package com.xmniao.enums;

/**
 * 抵用券类型
 *
 * @author liyuanbo
 * @create 2017-06-13 1:11
 **/
public enum VoucherTypeEnum {

    VOUCHER_50(50,"50抵用券"),
    VOURCHER_100(100,"100抵用券");
    private int type;
    private String name;

    private VoucherTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getName(int type){
        for(VoucherTypeEnum t : VoucherTypeEnum.values()){
            if (t.type() == type){
               return t.name();
            }
        }
        return null;
    }

    public int type(){
        return type;
    }
}
