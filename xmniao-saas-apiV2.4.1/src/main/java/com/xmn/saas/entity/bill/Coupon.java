package com.xmn.saas.entity.bill;


/**
 * 
*      
* 类名称：Coupon   
* 类描述：   优惠券
* 创建人：xiaoxiong   
* 创建时间：2016年10月21日 下午5:47:34     
*
 */
public class Coupon {
	
	/**
	 * 优惠券ID
	 */
	private int cuid;
	/**
	 * 面额
	 */
	private double denomination;
	
	/**
	 * 验证码
	 */
	private String serialNo;
	
	/**
	 * 使用金额
	 */
	private double useMoney;
	
	/**
	 * 有效开始时间
	 */
	private String sdate;
	
	/**
	 * 有效结束时间
	 */
	private String edate;
	
	/**
	 * 获取时间
	 */
	private String gdate;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 优惠卷名称
	 */
	private String cname;
	
	/**
	 * 优惠卷描述
	 */
	private String description;
	
	/**
	 * 获取方式
	 */
	private String getWay;
	
	/**
	 * 商家名称
	 */
	private String sellerName;
	
	/**
	 * 使用状态
	 */
	private int useStatus;
	
	
	
	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getGetWay() {
		return getWay;
	}

	public void setGetWay(String getWay) {
		this.getWay = getWay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 验证类型 1美食  2爆品 3商家赠品券
	 */
	private int type=2;
	
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGdate() {
		return gdate;
	}

	public void setGdate(String gdate) {
		this.gdate = gdate;
	}

	public double getDenomination() {
		return denomination;
	}

	public void setDenomination(double denomination) {
		this.denomination = denomination;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public double getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(double useMoney) {
		this.useMoney = useMoney;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getCuid() {
		return cuid;
	}

	public void setCuid(int cuid) {
		this.cuid = cuid;
	}

}
