package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;

public class TSellerPackageIssueRef extends BaseEntity{
    /**
	 * 套餐信息与抵用券信息关联关系
	 */
	private static final long serialVersionUID = 5301281364355362109L;

	private Integer id;

	/*套餐编号*/
    private Integer pid;

    /*抵用券编号*/
    private Integer giftCid;

    /*序号*/
    private Integer num;
    
    
    /*********自定义字段区域 开始***********/
    
    private BigDecimal denomination;//优惠劵面额
    
    private BigDecimal condition;//使用时最低金额，0表示无条件使用
    
    private String cname;
    
	private String couponDesc;
     
	public BigDecimal getDenomination() {
		return denomination;
	}

	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}

	public BigDecimal getCondition() {
		return condition;
	}

	public void setCondition(BigDecimal condition) {
		this.condition = condition;
	}
	
    public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

    public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	/***********自定义字段区域   结束***********/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getGiftCid() {
        return giftCid;
    }

    public void setGiftCid(Integer giftCid) {
        this.giftCid = giftCid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

	@Override
	public String toString() {
		return "TSellerPackageIssueRef [id=" + id + ", pid=" + pid
				+ ", giftCid=" + giftCid + ", num=" + num + ", denomination="
				+ denomination + ", condition=" + condition + ", cname="
				+ cname + ", couponDesc=" + couponDesc + "]";
	}


    
}