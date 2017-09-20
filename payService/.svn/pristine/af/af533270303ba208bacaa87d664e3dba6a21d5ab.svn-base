package com.xmniao.Template;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证码短信模板
 *
 * @auther LiYuanBo
 * @Create 2017/3/30
 */
public class Template {
    private String appid ="998899";
    private String text ;
    private List<String> phones =new ArrayList<>();

    public static String registTemplate(String message){
        return message;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setText(String text) {
        this.text = text;

    }



    public String getAppid() {
        return appid;
    }

    public String getText() {
        return text;
    }


    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void setPhones(String phone){
        this.phones.add(phone);
    }
    public void setPhones(String phone,String... paramPhones){
        this.phones.add(phone);
        for(String p : paramPhones){
             this.phones.add(p);
        }
    }
}
