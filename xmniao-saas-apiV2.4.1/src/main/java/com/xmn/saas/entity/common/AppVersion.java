package com.xmn.saas.entity.common;

import java.util.Date;

public class AppVersion {
    private Integer id;

    // 2 商户版
    private Integer apptype;

    // 1 Android    2 Ios
    private Integer vtype;

    private String version;

    private String inside;

    private String url;

    private String content;

    private Integer status;

    private Date sdate;

    private Integer activeNo;

    private Integer mustUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    public Integer getVtype() {
        return vtype;
    }

    public void setVtype(Integer vtype) {
        this.vtype = vtype;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getInside() {
        return inside;
    }

    public void setInside(String inside) {
        this.inside = inside == null ? null : inside.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Integer getActiveNo() {
        return activeNo;
    }

    public void setActiveNo(Integer activeNo) {
        this.activeNo = activeNo;
    }

    public Integer getMustUpdate() {
        return mustUpdate;
    }

    public void setMustUpdate(Integer mustUpdate) {
        this.mustUpdate = mustUpdate;
    }
}