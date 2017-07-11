/**
 * 
 */
package com.xmn.saas.controller.h5.coupon.vo;

import java.math.BigDecimal;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   交易订单详细信息
 * 创建人：huangk   
 * 创建时间：2016年10月14日 下午12:13:04      
 */
public class OrderDetailResponse {
	
	private String userName;
	/*赠品券、现金抵用券交易订单属性*/
	private String awardTime;
	private String useTime;
	private Long orderId;//订单编号
	private String serialNo;
	private int isShare;
	private int isVerify;
	private String avatar ; 
	private int getWay;
	private Integer isBind;//是否绑定用户
	/*满减活动交易订单属性*/
	private String paytype;//支付方式
	private BigDecimal amount;//订单总金额
	private BigDecimal reduction;//减免金额
	private BigDecimal realPay;//实际支付金额
	private BigDecimal rebate;//分账金额
	private BigDecimal realIncome;//实际收益
	private int isaccount;//订单状态
	private String paytime;//交易时间
	private String codeid;//消费验证码
	private String status ;//分账状态
	
	
	
	public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getIsBind() {
        return isBind;
    }
    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public int getIsVerify() {
        return isVerify;
    }
    public void setIsVerify(int isVerify) {
        this.isVerify = isVerify;
    }
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAwardTime() {
		return awardTime;
	}
	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public int getIsShare() {
		return isShare;
	}
	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}
	public int getGetWay() {
		return getWay;
	}
	public void setGetWay(int getWay) {
		this.getWay = getWay;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getReduction() {
		return reduction;
	}
	public void setReduction(BigDecimal reduction) {
		this.reduction = reduction;
	}
	public BigDecimal getRealPay() {
		return realPay;
	}
	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}
	public BigDecimal getRebate() {
		return rebate;
	}
	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}
	public BigDecimal getRealIncome() {
		return realIncome;
	}
	public void setRealIncome(BigDecimal realIncome) {
		this.realIncome = realIncome;
	}
	public int getIsaccount() {
		return isaccount;
	}
	public void setIsaccount(int isaccount) {
		this.isaccount = isaccount;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getCodeid() {
		return codeid;
	}
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
}
