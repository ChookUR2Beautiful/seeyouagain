package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: FansCoupon    
* @Description:粉丝卷实体   
* @author: liuzhihao   
* @date: 2017年2月17日 下午6:30:23
 */
@SuppressWarnings("serial")
public class FansCoupon implements Serializable{
	
    private Integer anchorCid;

    private Integer cid;

    private Integer uid;

    private Integer recordId;

    private Date startDate;

    private Date endDate;

    private String anchorRoomNo;

    private Integer sellerid;

    private Integer sendNum;

    private Integer stock;

    private Integer sendStatus;

    private Date sdate;

    private BigDecimal saleCouponRation;

    private BigDecimal useCouponRation;

    private Date soldOutTime;

    public Integer getAnchorCid() {
        return anchorCid;
    }

    public void setAnchorCid(Integer anchorCid) {
        this.anchorCid = anchorCid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAnchorRoomNo() {
        return anchorRoomNo;
    }

    public void setAnchorRoomNo(String anchorRoomNo) {
        this.anchorRoomNo = anchorRoomNo == null ? null : anchorRoomNo.trim();
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public BigDecimal getSaleCouponRation() {
        return saleCouponRation;
    }

    public void setSaleCouponRation(BigDecimal saleCouponRation) {
        this.saleCouponRation = saleCouponRation;
    }

    public BigDecimal getUseCouponRation() {
        return useCouponRation;
    }

    public void setUseCouponRation(BigDecimal useCouponRation) {
        this.useCouponRation = useCouponRation;
    }

    public Date getSoldOutTime() {
        return soldOutTime;
    }

    public void setSoldOutTime(Date soldOutTime) {
        this.soldOutTime = soldOutTime;
    }
}