package com.xmniao.xmn.core.member.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberProvodedResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3316940639998085332L;
	private Long id; //编号
	private Long flowid; //流水账号（提现的id）
	private String date;//操作时间
	private BigDecimal balance;//账户余额
	private BigDecimal money;//提现金额
	private String usertype;//提现用户类型（1 商家，2 会员提现 ，3 向蜜客提现，4 合作商）
	private String username;//用户昵称
	private Long userid;//用户id
	private String state;//提现状态（1:处理中；2:已到账）
	private String source;//提现来源(1：从返利提现；2：从佣金提现)
	private String type;//提现类型（1提现到银行卡 2提现到第三方支付账户）
	private String account;//提现账号
	private String invoice;//发票号
	private String express;//快递公司名称
	private String expressid;//快递单号
	private String taking;//从营收可提现金额
	private String commission;//从佣金可提现金额
	private String opinion;//备注
	private String loginAccount;//提现方帐号
	
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	/**
	 * @return the taking
	 */
	public String getTaking() {
		return taking;
	}

	/**
	 * @param taking the taking to set
	 */
	public void setTaking(String taking) {
		this.taking = taking;
	}

	/**
	 * @return the commission
	 */
	public String getCommission() {
		return commission;
	}

	/**
	 * @param commission the commission to set
	 */
	public void setCommission(String commission) {
		this.commission = commission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getFlowid() {
		return flowid;
	}
	public void setFlowid(Long flowid) {
		this.flowid = flowid;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSource() {
		return source;
	}
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public void setSource(String source) {
		this.source = source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpressid() {
		return expressid;
	}
	public void setExpressid(String expressid) {
		this.expressid = expressid;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Override
	public String toString() {
		return "MemberProvodedResponse [id=" + id + ", flowid=" + flowid
				+ ", date=" + date + ", balance=" + balance + ", money="
				+ money + ", usertype=" + usertype + ", username=" + username
				+ ", state=" + state + ", source=" + source + ", type=" + type
				+ ", account=" + account + ", invoice=" + invoice
				+ ", express=" + express + ", expressid=" + expressid
				+ ", taking=" + taking + ", commission=" + commission
				+ ", opinion=" + opinion + "]";
	}
	

}
