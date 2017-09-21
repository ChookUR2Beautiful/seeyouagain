package com.xmniao.domain.manor;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ManorInfo {
    private Integer id;

    private Integer uid;

    private Integer superUid;

    private Integer manorStatus;

    private Integer manorLevel;
    
    private Integer branchLocation;

    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date manorDeadline;

    private Integer activateCount;

    private Integer buildStatus;

    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date buildTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String phone;
    
    public ManorInfo() {
        super();
    }

    public Integer getBranchLocation() {
		return branchLocation;
	}

	public void setBranchLocation(Integer branchLocation) {
		this.branchLocation = branchLocation;
	}



	public Integer getActivateCount() {
        return activateCount;
    }

    public void setActivateCount(Integer activateCount) {
        this.activateCount = activateCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSuperUid() {
        return superUid;
    }

    public void setSuperUid(Integer superUid) {
        this.superUid = superUid;
    }

    public Integer getManorStatus() {
        return manorStatus;
    }

    public void setManorStatus(Integer manorStatus) {
        this.manorStatus = manorStatus;
    }

    public Integer getManorLevel() {
        return manorLevel;
    }

    public void setManorLevel(Integer manorLevel) {
        this.manorLevel = manorLevel;
    }

    public Date getManorDeadline() {
        return manorDeadline;
    }

    public void setManorDeadline(Date manorDeadline) {
        this.manorDeadline = manorDeadline;
    }

    public Integer getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(Integer buildStatus) {
        this.buildStatus = buildStatus;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    
}