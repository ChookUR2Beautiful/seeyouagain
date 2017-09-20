package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.SystemConstants;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCouponOrder
 * 
 * 类描述： 直播券订单
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-3 下午4:05:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TCouponOrder extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6442513164191683048L;

	private Long id;//业务主键

    private String orderSn;//订单号

    private Integer anchorCid;//通告粉丝券ID

    private Integer cidNum;//粉丝券数量

    private Long uid;//用户id

    private String nname;//用户呢称

    private String phoneid;//用户手机号
    
    private String userInfo;//会员信息,格式：李嘉诚(18945621598)

    private Integer recordId;//直播通告ID
    
    private Long sellerid;//商户id
    
    private String sellername;//商家名称
    
    private Integer anchorId;//主播Id
    
    private String anchorName;//主播昵称

    private Integer status;//0 待支付 1 已支付 2 支付失败 3 取消支付  4 退款成功
    
    private String statusStr;//订单状态

    private String payid;//支付ID，支付接口产生 用于查询支付记录使用

    private String paymentType;//支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:公众号支付，1000000：钱包支付

    private String thirdUid;//第三方支付帐号

    private String thirdSerial;//第三方支付流水号

    private BigDecimal totalAmount;//订单总金额
    
    private BigDecimal realAmount;//实际支付总额

    private BigDecimal cash;//第三方支付总额

    private BigDecimal balance;//余额支付金额

    private BigDecimal commision;//佣金支付金额

    private BigDecimal zbalance;//赠送支付金额

    private Integer beans;//鸟币支付金额

    private BigDecimal integral;//积分支付总额

    private BigDecimal cuser;//预售抵用券抵用金额

    private Integer cdid;//支付使用的预售抵用券ID

    private BigDecimal returnIntegral;//赠送积分金额

    private BigDecimal retrunCouponAmount;//赠送预售抵用券总面额

    private Date createTime;//创建时间
    
    private String createTimeStr;//创建时间

    private Date modifyTime;//更新时间
    
    private String modifyTimeStr;//更新时间

    private Integer version;//版本号，用于乐观锁控制

    private Integer orderSource;//订单来源 1 ios 2 android 3 微信
    
    private String buySource;//购买来源：1、通过主播购买，2、通过商户购买，3、通过预告购买
    
    private String buySourceStr;
    
    private String orderSourceStr;//订单来源Str
    
    private String cid;//粉丝券ID
    
    private String cname;//粉丝券名称
    
    private String cnameInfo;//粉丝券信息,格式：A券(星巴克学院店)
    
    private String userStatus;//使用状态
    
    private String userStatusStr;//使用状态Str
    
    private Date  userTime;//使用时间
    
    private Date lockTime;//锁定时间
    
    private String userTimeStr;//使用时间字符串
    
    private String lockTimeStr;//锁定时间字符串
    
    private String eUid;//企业级Uid

    private String uidRelationChain;//会员关系链，规则:每一级用'',''隔开，包含自身，每一级均为11位uid的字符串，不足11位在uid前以''0''填充。如当前uid为112,其关系链为0000001000,0000002000,00000000112'
    
    private String paymentTypeStr; //支付方式输出
    
    private List<String> juniorUids;//下级uid集合
    
    //=========粉丝券使用，分账详情
    private String accountStatus;//分账状态
    
    public String getPaymentTypeStr() {
		return SystemConstants.getPayTypeText(paymentType);
	}

	public void setPaymentTypeStr(String paymentTypeStr) {
		this.paymentTypeStr = paymentTypeStr;
	}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Integer getAnchorCid() {
        return anchorCid;
    }

    public void setAnchorCid(Integer anchorCid) {
        this.anchorCid = anchorCid;
    }

    public Integer getCidNum() {
        return cidNum;
    }

    public void setCidNum(Integer cidNum) {
        this.cidNum = cidNum;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid == null ? null : phoneid.trim();
    }
    
    

    /**
	 * @return the userInfo
	 */
	public String getUserInfo() {
		StringBuffer userInfoSb=new StringBuffer();
		if(nname!=null){
			userInfoSb.append(nname);
		}
		
		if(phoneid!=null){
			userInfoSb.append("<br/>(").append(phoneid).append(")");
		}
		return userInfo=userInfoSb.toString();
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }
    
    

    /**
	 * @return the sellername
	 */
	public String getSellername() {
		return sellername;
	}

	/**
	 * @param sellername the sellername to set
	 */
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	/**
	 * @return the anchorId
	 */
	public Integer getAnchorId() {
		return anchorId;
	}

	/**
	 * @param anchorId the anchorId to set
	 */
	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	/**
	 * @return the anchorName
	 */
	public String getAnchorName() {
		return anchorName;
	}

	/**
	 * @param anchorName the anchorName to set
	 */
	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

    /**
     * 0 待支付 1 已支付 2 支付失败 3 取消支付  4 退款成功
	 * @return the statusStr
	 */
	public String getStatusStr() {
		if(status==null){
			return null;
		}
		switch (status) {
		case 0:
			statusStr="待支付";
			break;
		case 1:
			statusStr="已支付";
			break;
		case 2:
			statusStr="支付失败";
			break;
		case 3:
			statusStr="取消支付";
			break;
		case 4:
			statusStr="退款成功";
			break;
		default:
			statusStr="待支付";
			break;
		}
		return statusStr;
	}

	/**
	 * @param statusStr the statusStr to set
	 */
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getThirdUid() {
        return thirdUid;
    }

    public void setThirdUid(String thirdUid) {
        this.thirdUid = thirdUid == null ? null : thirdUid.trim();
    }

    public String getThirdSerial() {
        return thirdSerial;
    }

    public void setThirdSerial(String thirdSerial) {
        this.thirdSerial = thirdSerial == null ? null : thirdSerial.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    

    /**
	 * @return the realAmount
	 */
	public BigDecimal getRealAmount() {
		if(totalAmount!=null && cuser!=null){
			realAmount=totalAmount.subtract(cuser);
		}
		return realAmount;
	}

	/**
	 * @param realAmount the realAmount to set
	 */
	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public BigDecimal getZbalance() {
        return zbalance;
    }

    public void setZbalance(BigDecimal zbalance) {
        this.zbalance = zbalance;
    }

    public Integer getBeans() {
        return beans;
    }

    public void setBeans(Integer beans) {
        this.beans = beans;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getCuser() {
        return cuser;
    }

    public void setCuser(BigDecimal cuser) {
        this.cuser = cuser;
    }

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public BigDecimal getReturnIntegral() {
        return returnIntegral;
    }

    public void setReturnIntegral(BigDecimal returnIntegral) {
        this.returnIntegral = returnIntegral;
    }

    public BigDecimal getRetrunCouponAmount() {
        return retrunCouponAmount;
    }

    public void setRetrunCouponAmount(BigDecimal retrunCouponAmount) {
        this.retrunCouponAmount = retrunCouponAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }
    
    

	/**
	 * @return the buySource
	 */
	public String getBuySource() {
		return buySource;
	}

	/**
	 * @param buySource the buySource to set
	 */
	public void setBuySource(String buySource) {
		this.buySource = buySource;
	}

	/**
	 * 购买来源：1、通过主播购买，2、通过商户购买，3、通过预告购买
	 * @return the buySourceStr
	 */
	public String getBuySourceStr() {
		if(buySource==null){
			return null;
		}
		switch (buySource) {
		case "1":
			buySourceStr="通过主播购买";
			break;
		case "2":
			buySourceStr="通过商户购买";
			break;
		case "3":
			buySourceStr="通过预告购买";
			break;
		default:
			buySourceStr="通过主播购买";
			break;
		}
		
		return buySourceStr;
	}

	/**
	 * @param buySourceStr the buySourceStr to set
	 */
	public void setBuySourceStr(String buySourceStr) {
		this.buySourceStr = buySourceStr;
	}

	/**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		if(createTime==null){
			return null;
		}
		return createTimeStr=DateUtil.formatDate(createTime, DateUtil.Y_M_D_HMS);
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	/**
	 * @return the modifyTimeStr
	 */
	public String getModifyTimeStr() {
		if(modifyTime==null){
			return null;
		}
		return modifyTimeStr=DateUtil.formatDate(modifyTime, DateUtil.Y_M_D_HMS);
	}

	/**
	 * @param modifyTimeStr the modifyTimeStr to set
	 */
	public void setModifyTimeStr(String modifyTimeStr) {
		this.modifyTimeStr = modifyTimeStr;
	}
	
	

	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	

	/**
	 * @return the cnameInfo
	 */
	public String getCnameInfo() {
		StringBuffer cnameInfoSb=new StringBuffer();
		if(cname!=null){
			cnameInfoSb.append(cname);
		}
		
		if(sellername!=null){
			cnameInfoSb.append("<br/>(").append(sellername).append(")");
		}
		return cnameInfo=cnameInfoSb.toString();
	}

	/**
	 * @param cnameInfo the cnameInfo to set
	 */
	public void setCnameInfo(String cnameInfo) {
		this.cnameInfo = cnameInfo;
	}

	/**
	 * @return the userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the userTime
	 */
	public Date getUserTime() {
		return userTime;
	}

	/**
	 * @param userTime the userTime to set
	 */
	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}

	/**
	 * @return the lockTime
	 */
	public Date getLockTime() {
		return lockTime;
	}

	/**
	 * @param lockTime the lockTime to set
	 */
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	/**
	 * @return the userTimeStr
	 */
	public String getUserTimeStr() {
		if(userTime==null){
			return null;
		}
		return userTimeStr=DateUtil.formatDate(userTime, DateUtil.Y_M_D_HMS);
	}

	/**
	 * @param userTimeStr the userTimeStr to set
	 */
	public void setUserTimeStr(String userTimeStr) {
		this.userTimeStr = userTimeStr;
	}

	/**
	 * @return the lockTimeStr
	 */
	public String getLockTimeStr() {
		if(lockTime==null){
			return null;
		}
		return lockTimeStr=DateUtil.formatDate(lockTime, DateUtil.Y_M_D_HMS);
	}

	/**
	 * @param lockTimeStr the lockTimeStr to set
	 */
	public void setLockTimeStr(String lockTimeStr) {
		this.lockTimeStr = lockTimeStr;
	}
	
	

	/**
	 * 订单来源 1 ios 2 android 3 微信
	 * @return the orderSourceStr
	 */
	public String getOrderSourceStr() {
		if(orderSource==null){
			return null;
		}
		switch (orderSource) {
		case 1:
			orderSourceStr="ios";
			break;
		case 2:
			orderSourceStr="android";
			break;
		case 3:
			orderSourceStr="微信";
			break;
		default:
			break;
		}
		return orderSourceStr;
	}

	/**
	 * @param orderSourceStr the orderSourceStr to set
	 */
	public void setOrderSourceStr(String orderSourceStr) {
		this.orderSourceStr = orderSourceStr;
	}

	/**
	 * 使用状态，0未使用，1锁定，2已使用
	 * @return the userStatusStr
	 */
	public String getUserStatusStr() {
		if(userStatus==null){
			return null;
		}
		switch (userStatus) {
		case "0":
			userStatusStr="未使用";
			break;
		case "1":
			userStatusStr="锁定";
			break;
		case "2":
			userStatusStr="已使用";
			break;
		default:
			userStatusStr="未使用";
			break;
		}
		return userStatusStr;
	}

	/**
	 * @param userStatusStr the userStatusStr to set
	 */
	public void setUserStatusStr(String userStatusStr) {
		this.userStatusStr = userStatusStr;
	}

	
	
	/**
	 * @return the eUid
	 */
	public String geteUid() {
		return eUid;
	}

	/**
	 * @param eUid the eUid to set
	 */
	public void seteUid(String eUid) {
		this.eUid = eUid;
	}

	/**
	 * @return the uidRelationChain
	 */
	public String getUidRelationChain() {
		return uidRelationChain;
	}

	/**
	 * @param uidRelationChain the uidRelationChain to set
	 */
	public void setUidRelationChain(String uidRelationChain) {
		this.uidRelationChain = uidRelationChain;
	}
	
	

	/**
	 * @return the juniorUids
	 */
	public List<String> getJuniorUids() {
		return juniorUids;
	}

	/**
	 * @param juniorUids the juniorUids to set
	 */
	public void setJuniorUids(List<String> juniorUids) {
		this.juniorUids = juniorUids;
	}

	
	/**
	 * @return the accountStatus
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/**
	 * @param accountStatus the accountStatus to set
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TCouponOrder [id=" + id + ", orderSn=" + orderSn
				+ ", anchorCid=" + anchorCid + ", cidNum=" + cidNum + ", uid="
				+ uid + ", nname=" + nname + ", phoneid=" + phoneid
				+ ", userInfo=" + userInfo + ", recordId=" + recordId
				+ ", sellerid=" + sellerid + ", sellername=" + sellername
				+ ", anchorId=" + anchorId + ", anchorName=" + anchorName
				+ ", status=" + status + ", statusStr=" + statusStr
				+ ", payid=" + payid + ", paymentType=" + paymentType
				+ ", thirdUid=" + thirdUid + ", thirdSerial=" + thirdSerial
				+ ", totalAmount=" + totalAmount + ", realAmount=" + realAmount
				+ ", cash=" + cash + ", balance=" + balance + ", commision="
				+ commision + ", zbalance=" + zbalance + ", beans=" + beans
				+ ", integral=" + integral + ", cuser=" + cuser + ", cdid="
				+ cdid + ", returnIntegral=" + returnIntegral
				+ ", retrunCouponAmount=" + retrunCouponAmount
				+ ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", modifyTime=" + modifyTime
				+ ", modifyTimeStr=" + modifyTimeStr + ", version=" + version
				+ ", orderSource=" + orderSource + ", buySource=" + buySource
				+ ", buySourceStr=" + buySourceStr + ", orderSourceStr="
				+ orderSourceStr + ", cid=" + cid + ", cname=" + cname
				+ ", cnameInfo=" + cnameInfo + ", userStatus=" + userStatus
				+ ", userStatusStr=" + userStatusStr + ", userTime=" + userTime
				+ ", lockTime=" + lockTime + ", userTimeStr=" + userTimeStr
				+ ", lockTimeStr=" + lockTimeStr + ", eUid=" + eUid
				+ ", uidRelationChain=" + uidRelationChain
				+ ", paymentTypeStr=" + paymentTypeStr + ", juniorUids="
				+ juniorUids + ", accountStatus=" + accountStatus + "]";
	}
    
    
}