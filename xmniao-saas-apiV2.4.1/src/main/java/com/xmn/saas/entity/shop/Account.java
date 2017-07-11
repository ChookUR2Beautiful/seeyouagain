package com.xmn.saas.entity.shop;


/**
*      
* 类名称：Account   
* 类描述：账户详情   
* 创建人：xiaoxiong   
* 创建时间：2016年10月12日 上午9:58:10     
*
 */
public class Account {
	/**
	 * 商家编号
	 */
	private int sellerid;
	/**
	 * 商家联系人
	 */
	private String name;
	/**
	 * 商家联系人手机号码
	 */
	private String phone;
	/**
	 * 注册时间
	 */
	private String sdate;
	/**
	 * 身份证号码
	 */
	private String carded;
	/**
	 * 是否实名
	 */
	private int status;
	/**
	 * 折扣
	 */
	private double agio;
	/**
	 * 提现限界
	 */
	private double limit;
	/**
	 * 超出提现多少钱一笔
	 */
	private double moreLimit;
	/**
	 * 以下多少钱一笔
	 */
	private double underLimit;
	
	/**
	 * 最后登入时间
	 */
	private String edate;

	/**
	 * 是否允许商家提现
	 */
	private int operating;
	
	
	
	
	public int getOperating() {
		return operating;
	}

	public void setOperating(int operating) {
		this.operating = operating;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getCarded() {
		return carded;
	}

	public void setCarded(String carded) {
		this.carded = carded;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getAgio() {
		return agio;
	}

	public void setAgio(double agio) {
		this.agio = agio;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public double getMoreLimit() {
		return moreLimit;
	}

	public void setMoreLimit(double moreLimit) {
		this.moreLimit = moreLimit;
	}

	public double getUnderLimit() {
		return underLimit;
	}

	public void setUnderLimit(double underLimit) {
		this.underLimit = underLimit;
	}
	
	
	

}
