package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCardSeller
 * 
 * 类描述： 商户/生鲜会员卡信息表实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月25日 下午9:04:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TCardSeller extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 主键ID
	 */
	private Integer id;

	/*
	 * 会员卡编号
	 */
    private Long noId;

    /*
     * 会员卡名称
     */
    private String cardName;

    /*
     * 会员卡类型 商家会员卡 生鲜会员卡
     */
    private Integer cardType;

    /*
     * 用户ID
     */
    private Integer uid;

    /*
     * 用户昵称
     */
    private String nname;

    /*
     * 用户手机号
     */
    private String phoneid;

    /*
     * 总店的商家编号
     */
    private Integer sellerid;

    /*
     * 总店的商家名称
     */
    private String sellername;

    /*
     * 该会员卡的余额
     */
    private BigDecimal amount;

    /*
     * 会员卡状态
     */
    private Integer cardStatus;

    /*
     * 该会员卡充值总额
     */
    private BigDecimal total;

    /*
     * 该会员卡的消费总额
     */
    private BigDecimal consumeTotal;

    /*
     * 会员卡密码
     */
    private String password;

    /*
     * 数据签名
     */
    private String sign;

    /*
     * 激活时间
     */
    private Date rdate;

    /*
     * 更新时间
     */
    private Date udate;
    
    /*
     * 门店的商家编号
     */
    private Integer msellerId;
    
    /*
     * 门店的商家名称
     */
    private String msellerName;

    /*
     * 查询条件最小余额区间
     */
    private BigDecimal minAmount;
    
    /*
     * 查询条件最大余额区间
     */
    private BigDecimal maxAmount;
    
    /*
     * 所有总充值到账金额
     */
    private BigDecimal totalAmount;
    
    /*
     * 所有总消费金额
     */
    private BigDecimal totalConsumeAmount;
    
    //所有总剩余金额
    private BigDecimal totalNoConsumeAmount;
    
    private String rdateStr;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNoId() {
		return noId;
	}

	public void setNoId(Long noId) {
		this.noId = noId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
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
		this.sellername = sellername;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getConsumeTotal() {
		return consumeTotal;
	}

	public void setConsumeTotal(BigDecimal consumeTotal) {
		this.consumeTotal = consumeTotal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rdateStr = (rdate==null)?null:df.format(rdate);
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public Integer getMsellerId() {
		return msellerId;
	}

	public void setMsellerId(Integer msellerId) {
		this.msellerId = msellerId;
	}

	public String getMsellerName() {
		return msellerName;
	}

	public void setMsellerName(String msellerName) {
		this.msellerName = msellerName;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalConsumeAmount() {
		return totalConsumeAmount;
	}

	public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
		this.totalConsumeAmount = totalConsumeAmount;
	}

	public BigDecimal getTotalNoConsumeAmount() {
		return totalNoConsumeAmount;
	}

	public void setTotalNoConsumeAmount(BigDecimal totalNoConsumeAmount) {
		this.totalNoConsumeAmount = totalNoConsumeAmount;
	}

	
	public String getRdateStr() {
		return rdateStr;
	}

	@Override
	public String toString() {
		return "TCardSeller [id=" + id + ", noId=" + noId + ", cardName="
				+ cardName + ", cardType=" + cardType + ", uid=" + uid
				+ ", nname=" + nname + ", phoneid=" + phoneid + ", sellerid="
				+ sellerid + ", sellername=" + sellername + ", amount="
				+ amount + ", cardStatus=" + cardStatus + ", total=" + total
				+ ", consumeTotal=" + consumeTotal + ", password=" + password
				+ ", sign=" + sign + ", rdate=" + rdate + ", udate=" + udate
				+ ", msellerId=" + msellerId + ", msellerName=" + msellerName
				+ ", minAmount=" + minAmount + ", maxAmount=" + maxAmount
				+ ", totalAmount=" + totalAmount + ", totalConsumeAmount="
				+ totalConsumeAmount + ", totalNoConsumeAmount="
				+ totalNoConsumeAmount + ", rdateStr=" + rdateStr + "]";
	}

}