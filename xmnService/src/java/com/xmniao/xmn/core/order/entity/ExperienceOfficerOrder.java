package com.xmniao.xmn.core.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
* @Description:体验卡订单返回参数相关类
* @author: yhl 
* @date 2017年5月9日 下午5:16:13 
*
 */
public class ExperienceOfficerOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3844046774080521555L;
	//
	private Integer id;
	//订单编号
	private String orderNo;
	//用户电话
	private String phone;
	//用户呢称
	private String nname;
	//是否免费
    private Integer isFree;
    //有效次数
    private Integer nums;
    //有效天数
    private Integer days;
    //uid
    private Integer uid;
    //支付类型
    private Integer payType;
    
  //支付类型
    private String payTypeStr;
    
    //支付流水号
    private String payid;
    //鸟币支付总额
    private BigDecimal liveCoin;
    //钱包支付金额
    private BigDecimal  walletAmount;
    //订单状态
    private Integer payState;
    //描述
    private String description;
    //版本号
    private Integer version;
    //订单总额
    private BigDecimal amount;
    //过期时间
    private String outDate;
    //创建时间
    private Date createTime;
    
    private String createTimeStr;
    //支付时间
    private Date payTime;
    
    private BigDecimal samount;
    
    //订单总额
    private BigDecimal payAmount;
    
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
		this.orderNo = orderNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public BigDecimal getSamount() {
		return samount;
	}

	public void setSamount(BigDecimal samount) {
		this.samount = samount;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public BigDecimal getLiveCoin() {
		return liveCoin;
	}

	public void setLiveCoin(BigDecimal liveCoin) {
		this.liveCoin = liveCoin;
	}

	public BigDecimal getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(BigDecimal walletAmount) {
		this.walletAmount = walletAmount;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayTypeStr() {
		if (payType == null) {
			return "免费领取";
		}
		if (payType == 1000000) {
			return "余额支付";
		}
		if (payType == 1000020) {
			return "鸟粉卡支付";
		}
		if (payType == 1000013 || payType == 1000003 ) {
			return "微信支付";
		}
		if (payType == 1000001 || payType == 1000014) {
			return "支付宝支付";
		}
		return "免费领取";
	}

	public void setPayTypeStr(String payTypeStr) {
		this.payTypeStr = payTypeStr;
	}

	public String getCreateTimeStr() {
		return DateUtil.format(createTime, DateUtil.defaultSimpleFormater);
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Override
	public String toString() {
		return "ExperienceOfficerOrder [id=" + id + ", orderNo=" + orderNo
				+ ", phone=" + phone + ", nname=" + nname + ", isFree="
				+ isFree + ", nums=" + nums + ", days=" + days + ", uid=" + uid
				+ ", payType=" + payType + ", payid=" + payid + ", liveCoin="
				+ liveCoin + ", walletAmount=" + walletAmount + ", payState="
				+ payState + ", description=" + description + ", version="
				+ version + ", amount=" + amount + ", outDate=" + outDate
				+ ", createTime=" + createTime + ", payTime=" + payTime + "]";
	}
	
}
