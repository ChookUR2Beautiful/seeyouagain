package com.xmniao.xmn.core.xmermanagerment.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BXmerWalletRecord
 * 
 * 类描述： 寻蜜客钱包记录实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-8 下午6:12:44 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BXmerWalletRecord extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 655753439489858398L;

	private Integer id;//记录ID

    private Integer xid;//寻蜜客钱包ID

    private Integer rtype;//0 寻蜜鸟分账 1 转出  2 saas分账 3 寻蜜鸟分账返回 4 saas分账返回
    
    private String rtypeStr;

    private BigDecimal profit;//收益金额

    private BigDecimal qprofit;//分账前收益余额

    private BigDecimal hprofit;//分账后收益余额

    private Date sdate;//记录时间
    
    private String sdateStr;//

    private String remark;//单号

    private String description;//描述
    
    /**
     * 寻蜜客钱包交易记录统计列
     */
    private BigDecimal commision;//佣金
    
    private BigDecimal balance;//流水
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    public Integer getRtype() {
        return rtype;
    }

    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }
    
    

    /**
     * 0 寻蜜鸟分账 1 转出  2 saas分账 3 寻蜜鸟分账返回 4 saas分账返回
	 * @return the rtypeStr
	 */
	public String getRtypeStr() {
		switch (rtype) {
		case 0:
			rtypeStr="寻蜜鸟分账";
			break;
		case 1:
			rtypeStr="转出";
			break;
		case 2:
			rtypeStr="saas分账";
			break;
		case 3:
			rtypeStr="寻蜜鸟分账返回";
			break;
		case 4:
			rtypeStr="saas分账返回";
			break;

		default:
			break;
		}
		return rtypeStr;
	}

	/**
	 * @param rtypeStr the rtypeStr to set
	 */
	public void setRtypeStr(String rtypeStr) {
		this.rtypeStr = rtypeStr;
	}

	/**
	 * @return the sdateStr
	 */
	public String getSdateStr() {
		return sdateStr=DateUtil.formatDate(DateUtil.Y_M_D_HMS,sdate);
	}

	/**
	 * @param sdateStr the sdateStr to set
	 */
	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getQprofit() {
        return qprofit;
    }

    public void setQprofit(BigDecimal qprofit) {
        this.qprofit = qprofit;
    }

    public BigDecimal getHprofit() {
        return hprofit;
    }

    public void setHprofit(BigDecimal hprofit) {
        this.hprofit = hprofit;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    

	/**
	 * @return the commision
	 */
	public BigDecimal getCommision() {
		return commision;
	}

	/**
	 * @param commision the commision to set
	 */
	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BXmerWalletRecord [id=" + id + ", xid=" + xid + ", rtype="
				+ rtype + ", rtypeStr=" + rtypeStr + ", profit=" + profit
				+ ", qprofit=" + qprofit + ", hprofit=" + hprofit + ", sdate="
				+ sdate + ", sdateStr=" + sdateStr + ", remark=" + remark
				+ ", description=" + description + ", commision=" + commision
				+ ", balance=" + balance + "]";
	}
    
    
}