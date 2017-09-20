package com.xmniao.xmn.core.billmanagerment.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CelebrityOrder
 * 
 * 类描述： 名嘴食评
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月20日 下午3:28:51 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class CelebrityOrder extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3472827446946779301L;

	private Long id;

    private String orderNo;

    private Long sellerId;

    private Long celebrityId;

    private Date createTime;
    
    private Date sdate;
    
    private Date edate;

    private Integer status;

    private BigDecimal price;

    private Date arrivalTime;

    private Integer type;

    private Integer emphasis;

    private Integer crowd;

    private String demand;

    private String detailDemand;

    private Integer payStatus;

    private Integer payType;

    private Long versionLock;

    private String thirdSerial;

    private Date payTime;
    
    private String sellername;//商家名称
    
    private String fullname;//法人名称
    
    private String phoneid;//法人手机号
    
    private String name;//网红姓名
    
    private String phone;//网红手机号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(Long celebrityId) {
        this.celebrityId = celebrityId;
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm")
    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(Integer emphasis) {
        this.emphasis = emphasis;
    }

    public Integer getCrowd() {
        return crowd;
    }

    public void setCrowd(Integer crowd) {
        this.crowd = crowd;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand == null ? null : demand.trim();
    }

    public String getDetailDemand() {
        return detailDemand;
    }

    public void setDetailDemand(String detailDemand) {
        this.detailDemand = detailDemand == null ? null : detailDemand.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getVersionLock() {
        return versionLock;
    }

    public void setVersionLock(Long versionLock) {
        this.versionLock = versionLock;
    }

    public String getThirdSerial() {
        return thirdSerial;
    }

    public void setThirdSerial(String thirdSerial) {
        this.thirdSerial = thirdSerial == null ? null : thirdSerial.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "CelebrityOrder [id=" + id + ", orderNo=" + orderNo
				+ ", sellerId=" + sellerId + ", celebrityId=" + celebrityId
				+ ", createTime=" + createTime + ", sdate=" + sdate
				+ ", edate=" + edate + ", status=" + status + ", price="
				+ price + ", arrivalTime=" + arrivalTime + ", type=" + type
				+ ", emphasis=" + emphasis + ", crowd=" + crowd + ", demand="
				+ demand + ", detailDemand=" + detailDemand + ", payStatus="
				+ payStatus + ", payType=" + payType + ", versionLock="
				+ versionLock + ", thirdSerial=" + thirdSerial + ", payTime="
				+ payTime + ", sellername=" + sellername + ", fullname="
				+ fullname + ", phoneid=" + phoneid + ", name=" + name
				+ ", phone=" + phone + "]";
	}
    
}