package com.xmniao.domain.order;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasSoldOrder
 * 
 * 类描述： 商家签约SAAS订单表
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月13日 上午11:39:59 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class SaasSoldOrderBean {
	
	private Integer id;

    private String ordersn;

    private Integer sellerid;

    private String sellername;

    private Integer uid;

    private BigDecimal amount;

    private Integer status;

    private String reason;

    private Integer payType;

    private Date zdate;

    private Date createDate;

    private BigDecimal samount;

    private String payId;

    private String payCode;
    
    private String saasOrdersn;
    
    private BigDecimal agio;
    /*
     * add by lifeng
     */
    private Integer hstatus;//分账状态
    private Date udate;//最后修改时间
    private String commission;//分账明细
    private String xmerBackMoney;//SAAS返回押金金额
    private String xmniaoMoney;//分账后剩余金额（平台剩余金额）
    private String xmerMoney;//寻蜜客分账金额
    private String oneLevelXmerMoney;//一级寻蜜客分账金额
    private String twoLevelXmerMoney;//二级寻蜜客分账金额
	private String sellerAreaMoney;//经销商分账金额
	// 开始时间和结束时间，用于条件查询
    private Date sDate;
    private Date eDate;
    //
    private String saasChannel;  //saas购买渠道 1常规购买 2 脉客购买 3 V客兑换 4 主播接受V客赠送
    
    private String fromOrdersn;  //V客赠送套餐
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdersn() {
        return ordersn;
    }

    public void setOrdersn(String ordersn) {
        this.ordersn = ordersn == null ? null : ordersn.trim();
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
        return zdate;
    }

    public void setZdate(Date zdate) {
        this.zdate = zdate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getSamount() {
        return samount;
    }

    public void setSamount(BigDecimal samount) {
        this.samount = samount;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }
    
	public String getSaasOrdersn() {
		return saasOrdersn;
	}

	public void setSaasOrdersn(String saasOrdersn) {
		this.saasOrdersn = saasOrdersn;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public String getXmerBackMoney() {
		String str = xmerBackMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("xmerBackMoney");
		}
		return str;
	}

	public void setXmerBackMoney(String xmerBackMoney) {
		this.xmerBackMoney = xmerBackMoney;
	}

	public String getXmniaoMoney() {
		String str = xmniaoMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("xmniaoMoney");
		}
		return str;
	}

	public void setXmniaoMoney(String xmniaoMoney) {
		this.xmniaoMoney = xmniaoMoney;
	}

	public String getXmerMoney() {
		String str = xmerMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("xmerMoney");
		}
		return str;
	}

	public void setXmerMoney(String xmerMoney) {
		this.xmerMoney = xmerMoney;
	}

	public String getOneLevelXmerMoney() {
		String str = oneLevelXmerMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("oneLevelXmerMoney");
		}
		return str;
	}

	public void setOneLevelXmerMoney(String oneLevelXmerMoney) {
		this.oneLevelXmerMoney = oneLevelXmerMoney;
	}

	public String getTwoLevelXmerMoney() {
		String str = twoLevelXmerMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("twoLevelXmerMoney");
		}
		return str;
	}

	public void setTwoLevelXmerMoney(String twoLevelXmerMoney) {
		this.twoLevelXmerMoney = twoLevelXmerMoney;
	}

	public String getSellerAreaMoney() {
		String str = sellerAreaMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("sellerAreaMoney");
		}
		return str;
	}

	public void setSellerAreaMoney(String sellerAreaMoney) {
		this.sellerAreaMoney = sellerAreaMoney;
	}

	public Integer getHstatus() {
		return hstatus;
	}

	public void setHstatus(Integer hstatus) {
		this.hstatus = hstatus;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public String getSaasChannel() {
		return saasChannel;
	}

	public void setSaasChannel(String saasChannel) {
		this.saasChannel = saasChannel;
	}

	public String getFromOrdersn() {
		return fromOrdersn;
	}

	public void setFromOrdersn(String fromOrdersn) {
		this.fromOrdersn = fromOrdersn;
	}

	public BigDecimal getAgio() {
		return agio;
	}

	public void setAgio(BigDecimal agio) {
		this.agio = agio;
	}

	@Override
	public String toString() {
		return "SaasSoldOrderBean [id=" + id + ", ordersn=" + ordersn
				+ ", sellerid=" + sellerid + ", sellername=" + sellername
				+ ", uid=" + uid + ", amount=" + amount + ", status=" + status
				+ ", reason=" + reason + ", payType=" + payType + ", zdate="
				+ zdate + ", createDate=" + createDate + ", samount=" + samount
				+ ", payId=" + payId + ", payCode=" + payCode
				+ ", saasOrdersn=" + saasOrdersn + ", agio=" + agio
				+ ", hstatus=" + hstatus + ", udate=" + udate + ", commission="
				+ commission + ", xmerBackMoney=" + xmerBackMoney
				+ ", xmniaoMoney=" + xmniaoMoney + ", xmerMoney=" + xmerMoney
				+ ", oneLevelXmerMoney=" + oneLevelXmerMoney
				+ ", twoLevelXmerMoney=" + twoLevelXmerMoney
				+ ", sellerAreaMoney=" + sellerAreaMoney + ", sDate=" + sDate
				+ ", eDate=" + eDate + ", saasChannel=" + saasChannel
				+ ", fromOrdersn=" + fromOrdersn + "]";
	}


	

}