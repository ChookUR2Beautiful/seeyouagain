package com.xmniao.enums;

/**
 * 激活庄园类型
 *
 * @author liyuanbo
 * @create 2017-05-31 15:04
 **/
public enum ActiveManorEnum {
    ACTIVE(1, "激活庄园"),
    RENEW(2, "续租庄园");
    private int type;//操作类型
    private String remark; //备注

    private ActiveManorEnum(int type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public static String getRemark(int type){
        for(ActiveManorEnum activeManorEnum : ActiveManorEnum.values()){
            if(activeManorEnum.getType() == type){
                return activeManorEnum.getRemark();
            }
        }
        return null;
    }

    public static int getType(int type){
        for(ActiveManorEnum activeManorEnum : ActiveManorEnum.values()){
            if(activeManorEnum.getType() == type){
                return activeManorEnum.getType();
            }
        }
        return 0;
    }

    public int getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
