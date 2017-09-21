package com.xmniao.domain.manor;

import java.util.Date;

public class ManorFlowerRelation {
    private Integer id;

    private Byte type;

    private Date endTime;

    private Byte location;

    private Integer pid;

    private Integer leftNode;

    private Integer rightNode;

    private Integer zid;

    private Integer level;

    private Integer uid;

    private Integer fromUid;

    private Byte state;

    private String relationChain;
    private Integer delayDays;
    private Integer actualOperation;    // 实际种植的操作  1:种子 2:枯萎花朵 3:花朵


    public ManorFlowerRelation() {
        super();
    }

    public ManorFlowerRelation(Integer uid, Byte location, Integer fromUid) {
        this.location = location;
        this.uid = uid;
        this.fromUid = fromUid;
    }

    public ManorFlowerRelation(Integer uid, Byte location) {
        this.uid = uid;
        this.location = location;
    }

    public Integer getActualOperation() {
        return actualOperation;
    }

    public void setActualOperation(Integer actualOperation) {
        this.actualOperation = actualOperation;
    }

    public String getRelationChain() {
        return relationChain;
    }

    public void setRelationChain(String relationChain) {
        this.relationChain = relationChain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getLocation() {
        return location;
    }

    public void setLocation(Byte location) {
        this.location = location;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Integer leftNode) {
        this.leftNode = leftNode;
    }

    public Integer getRightNode() {
        return rightNode;
    }

    public void setRightNode(Integer rightNode) {
        this.rightNode = rightNode;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public Integer getDelayDays() {
        return delayDays;
    }
}