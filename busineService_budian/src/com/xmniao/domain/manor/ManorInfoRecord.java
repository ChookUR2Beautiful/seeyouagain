package com.xmniao.domain.manor;

import java.util.Date;

public class ManorInfoRecord {
    private Integer id;

    private String transNo;

    private Integer manorId;

    private Integer uid;

    private Integer oprateType;

    private String recordDetails;

    private Date createTime;

    public ManorInfoRecord() {
        super();
    }

    public ManorInfoRecord(String transNo, Integer oprateType, Integer manorId, Integer uid, String recordDetails) {
        this.transNo = transNo;
        this.manorId = manorId;
        this.uid = uid;
        this.oprateType = oprateType;
        this.recordDetails = recordDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public Integer getManorId() {
        return manorId;
    }

    public void setManorId(Integer manorId) {
        this.manorId = manorId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOprateType() {
        return oprateType;
    }

    public void setOprateType(Integer oprateType) {
        this.oprateType = oprateType;
    }

    public String getRecordDetails() {
        return recordDetails;
    }

    public void setRecordDetails(String recordDetails) {
        this.recordDetails = recordDetails == null ? null : recordDetails.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}