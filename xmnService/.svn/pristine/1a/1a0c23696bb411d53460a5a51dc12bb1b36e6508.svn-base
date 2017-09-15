package com.xmniao.xmn.core.verification.entity;


import java.io.Serializable;

public class VerifyResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2314166718654359718L;

	private long bid;  //订单号
	
	private String sdate;  //下单时间
	
	private int  codeid; //要验证的消费验证号
	
	private double money;  //消费金额
	
	private String nname; //消费者用户名
	/**
	 * 0 待支付   1 已支付 2 已返  3 已消费(验证)
	 * 4 退款中(未消费  未分账之前都可以申请退款)
	 * 5 已退款(退款成功)
	 * 6 已取消(表示未支付前，手动取消订单)
	 * 7 已取消(表示未支付前，自动取消订单)
	 */
	private int status; //订单状态
	
	private String sellername; //经办人用户名
	
	private String ydate; //验证时间
	
	private int  isverify;  //是否已经验证过   数据库中的
 	
	private int  bfirst;  //是否是首单
	
	private int  isfirstverify;   //是否为验证过带到前台显示   1是    0否
	
	private String phoneid;  //手机号码
	
	private String ldate;   //商户分账时间
	
	private int ismember;  //是否是会员
	
	private double platform;  //平台收入
	
	private double rebate;  //用户返利
	
	private double business;  //商户收入

	private double operating;  //营业收入

	private double joint;  ///合作商收入
	
	private String commission; //订单佣金
	
	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public int getCodeid() {
		return codeid;
	}

	public void setCodeid(int codeid) {
		this.codeid = codeid;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getYdate() {
		return ydate;
	}

	public void setYdate(String ydate) {
		this.ydate = ydate;
	}
	
	public int getIsfirstverify() {
		return isfirstverify;
	}

	public void setIsfirstverify(int isfirstverify) {
		this.isfirstverify = isfirstverify;
	}

	public int getIsverify() {
		return isverify;
	}

	public void setIsverify(int isverify) {
		this.isverify = isverify;
	}
	
	/**
	 * @return the phoneid
	 */
	public String getPhoneid() {
		return phoneid;
	}

	/**
	 * @param phoneid the phoneid to set
	 */
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	/**
	 * @return the bfirst
	 */
	public int getBfirst() {
		return bfirst;
	}

	/**
	 * @param bfirst the bfirst to set
	 */
	public void setBfirst(int bfirst) {
		this.bfirst = bfirst;
	}

	/**
	 * @return the ldate
	 */
	public String getLdate() {
		return ldate;
	}

	/**
	 * @param ldate the ldate to set
	 */
	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	/**
	 * @return the ismember
	 */
	public int getIsmember() {
		return ismember;
	}

	/**
	 * @param ismember the ismember to set
	 */
	public void setIsmember(int ismember) {
		this.ismember = ismember;
	}

	/**
	 * @return the platform
	 */
	public double getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(double platform) {
		this.platform = platform;
	}

	/**
	 * @return the rebate
	 */
	public double getRebate() {
		return rebate;
	}

	/**
	 * @param rebate the rebate to set
	 */
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}

	/**
	 * @return the business
	 */
	public double getBusiness() {
		return business;
	}

	/**
	 * @param business the business to set
	 */
	public void setBusiness(double business) {
		this.business = business;
	}

	/**
	 * @return the operating
	 */
	public double getOperating() {
		return operating;
	}

	/**
	 * @param operating the operating to set
	 */
	public void setOperating(double operating) {
		this.operating = operating;
	}

	/**
	 * @return the joint
	 */
	public double getJoint() {
		return joint;
	}

	/**
	 * @param joint the joint to set
	 */
	public void setJoint(double joint) {
		this.joint = joint;
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
}
