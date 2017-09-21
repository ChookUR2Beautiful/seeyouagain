package com.xmniao.domain.live;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TVerExcitationReceive
 * 
 * 类描述： 会员发放奖励记录实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-26 下午3:26:08 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVerExcitationReceive implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6632822468715132603L;

	private Integer id;

    private String orderNo;

    private Integer uid;

    private String projectName;

    private Integer cid;

    private Byte num;

    private Byte type;

    private Double worth;

    private Integer status;

    private Date excitationDate;

    private Date receiveDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Byte getNum() {
        return num;
    }

    public void setNum(Byte num) {
        this.num = num;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Double getWorth() {
        return worth;
    }

    public void setWorth(Double worth) {
        this.worth = worth;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExcitationDate() {
        return excitationDate;
    }

    public void setExcitationDate(Date excitationDate) {
        this.excitationDate = excitationDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVerExcitationReceive [id=" + id + ", orderNo=" + orderNo
				+ ", uid=" + uid + ", projectName=" + projectName + ", cid="
				+ cid + ", num=" + num + ", type=" + type + ", worth=" + worth
				+ ", status=" + status + ", excitationDate=" + excitationDate
				+ ", receiveDate=" + receiveDate + "]";
	}
    
    
}