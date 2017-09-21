package com.xmniao.domain.order;

import java.math.BigDecimal;
import java.util.Date;

public class BillBargain {
    private Integer boid;

    private Long bid;

    private Byte isbargain;

    private Integer bpid;

    private String pname;

    private BigDecimal price;

    private Integer num;

    private Date sdate;

    private String remark;

    private BigDecimal amount;

    private Double integral;

    private Integer status;

    private BigDecimal allAmount;

    private Integer bstatus;

    private Integer uid;

    private Integer sellerid;

    private String uname;

    private String phoneid;

    private String sellername;

    private Integer codeid;

    private String bids;
    
    private String commission;
    
    private Integer genussellerid;
    
    private String genusname;
    
    private Integer consumeJointid;
    
    private String comsumeJointname;
    
    private Integer hstatus;
    
    private Integer version;
    
    private String hstatusStr;
    
    private String paymentType;
    
    private String payid;
    
    private BigDecimal balance;
    
    private String thirdid;
    
    private String thirdUid;
    
    private Date payTime;
    
    private BigDecimal sellerPrice;
    public Integer getBoid() {
        return boid;
    }

    public void setBoid(Integer boid) {
        this.boid = boid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Byte getIsbargain() {
        return isbargain;
    }

    public void setIsbargain(Byte isbargain) {
        this.isbargain = isbargain;
    }

    public Integer getBpid() {
        return bpid;
    }

    public void setBpid(Integer bpid) {
        this.bpid = bpid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(BigDecimal allAmount) {
        this.allAmount = allAmount;
    }

    public Integer getBstatus() {
        return bstatus;
    }

    public void setBstatus(Integer bstatus) {
        this.bstatus = bstatus;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid == null ? null : phoneid.trim();
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getCodeid() {
        return codeid;
    }

    public void setCodeid(Integer codeid) {
        this.codeid = codeid;
    }

	public String getBids() {
		return bids;
	}

	public void setBids(String bids) {
		this.bids = bids;
	}

	
	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public Integer getGenussellerid() {
		return genussellerid;
	}

	public void setGenussellerid(Integer genussellerid) {
		this.genussellerid = genussellerid;
	}

	public String getGenusname() {
		return genusname;
	}

	public void setGenusname(String genusname) {
		this.genusname = genusname;
	}

	public Integer getConsumeJointid() {
		return consumeJointid;
	}

	public void setConsume_jointid(Integer consumeJointid) {
		this.consumeJointid = consumeJointid;
	}

	public String getComsumeJointname() {
		return comsumeJointname;
	}

	public void setComsumeJointname(String comsumeJointname) {
		this.comsumeJointname = comsumeJointname;
	}

	public Integer getHstatus() {
		return hstatus;
	}

	public void setHstatus(Integer hstatus) {
		this.hstatus = hstatus;
	}
	
	

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getHstatusStr() {
		return hstatusStr;
	}

	public void setHstatusStr(String hstatusStr) {
		this.hstatusStr = hstatusStr;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	
	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getThirdid() {
		return thirdid;
	}

	public void setThirdid(String thirdid) {
		this.thirdid = thirdid;
	}

	public String getThirdUid() {
		return thirdUid;
	}

	public void setThirdUid(String thirdUid) {
		this.thirdUid = thirdUid;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getSellerPrice() {
		return sellerPrice;
	}

	public void setSellerPrice(BigDecimal sellerPrice) {
		this.sellerPrice = sellerPrice;
	}

	public void setConsumeJointid(Integer consumeJointid) {
		this.consumeJointid = consumeJointid;
	}

	@Override
	public String toString() {
		return "BillBargain [boid=" + boid + ", bid=" + bid + ", isbargain="
				+ isbargain + ", bpid=" + bpid + ", pname=" + pname
				+ ", price=" + price + ", num=" + num + ", sdate=" + sdate
				+ ", remark=" + remark + ", amount=" + amount + ", integral="
				+ integral + ", status=" + status + ", allAmount=" + allAmount
				+ ", bstatus=" + bstatus + ", uid=" + uid + ", sellerid="
				+ sellerid + ", uname=" + uname + ", phoneid=" + phoneid
				+ ", sellername=" + sellername + ", codeid=" + codeid
				+ ", bids=" + bids + ", commission=" + commission
				+ ", genussellerid=" + genussellerid + ", genusname="
				+ genusname + ", consumeJointid=" + consumeJointid
				+ ", comsumeJointname=" + comsumeJointname + ", hstatus="
				+ hstatus + "]";
	}

}