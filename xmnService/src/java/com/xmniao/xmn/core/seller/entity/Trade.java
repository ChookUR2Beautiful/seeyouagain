package com.xmniao.xmn.core.seller.entity;

import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: Trade    
* @Description:商铺分类实体   
* @author: liuzhihao   
* @date: 2016年11月16日 下午2:56:16
 */
public class Trade {
    private Integer tid;

    private String tradename;

    private Integer pid;

    private Integer status;

    private Date sdate;

    private Integer number;

    private Integer orderNum;

    private String showBigImg;

    private String showSmallImg;

    private Boolean hot;

    private Byte type;

    private String url;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename == null ? null : tradename.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getShowBigImg() {
        return showBigImg;
    }

    public void setShowBigImg(String showBigImg) {
        this.showBigImg = showBigImg == null ? null : showBigImg.trim();
    }

    public String getShowSmallImg() {
        return showSmallImg;
    }

    public void setShowSmallImg(String showSmallImg) {
        this.showSmallImg = showSmallImg == null ? null : showSmallImg.trim();
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}