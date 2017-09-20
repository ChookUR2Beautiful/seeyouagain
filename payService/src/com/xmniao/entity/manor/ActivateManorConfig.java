package com.xmniao.entity.manor;

/**
 * 激活庄园配置
 * @author liyuanbo
 * @create 2017-06-01 10:20
 **/
public class ActivateManorConfig {


    private int id ;
    private int type;//收益类型
    private int number;//道具数量

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
