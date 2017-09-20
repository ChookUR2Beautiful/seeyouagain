package com.xmn.designer.controller.api.v1.vo;

import com.xmn.designer.base.Request;

/**
 * create 2016/11/14
 *
 * @author yangQiang
 */

public class TestRequest extends Request {

    private String name;
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
