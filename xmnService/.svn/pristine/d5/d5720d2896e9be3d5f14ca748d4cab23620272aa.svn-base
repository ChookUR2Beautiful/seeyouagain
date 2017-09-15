package com.xmniao.xmn.core.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.util.DateUtil;


/**
 * 订单表返回值对象
 * @author yhl
 * @date 2016年6月20日11:04:54
 * */
public class FreshOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1934942963354424334L;
    
    private String bid; //订单ID
    
    private String  uid; //用户ID
    
	private int status ;//订单状态
    
	private BigDecimal money;//总额
	
	private BigDecimal integral;//积分付款总额
	
	private BigDecimal freight;//运费
	
	private BigDecimal payment;//第三方支付金额
	
	private Date sdate;//下单时间
	
	private Date zdate;//支付时间
	
	private String zdateStr; //支付时间字符串
	
	private String sdateStr; //下单时间字符串

	private String mobile;//收货人电话
	
	private String username;//收货名称
	
	private String address;//收货地址
	
	private BigDecimal realpayment;//实付金额
	
	private BigDecimal max_integral;//最大使用积分限制
	
	private int ordersource;
	
	private BigDecimal cdenom;//实付金额
	
	private BigDecimal cuser;//实付金额
	
	
	private  List<FreshOrderProduct> product ;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getZdate() {
		
		return zdate;
	}

	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getRealpayment() {
		return realpayment;
	}

	public void setRealpayment(BigDecimal realpayment) {
		this.realpayment = realpayment;
	}

	public BigDecimal getMax_integral() {
		return max_integral;
	}

	public void setMax_integral(BigDecimal max_integral) {
		this.max_integral = max_integral;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getOrdersource() {
		return ordersource;
	}

	public void setOrdersource(int ordersource) {
		this.ordersource = ordersource;
	}
	
	public String getZdateStr() {
		if (zdate==null) {
			return " ";
		}else {
			return DateUtil.format(zdate, DateUtil.defaultSimpleFormater);
		}
		
	}

	public void setZdateStr(String zdateStr) {
		this.zdateStr = zdateStr;
	}
	
	public String getSdateStr() {
		if (sdate==null) {
			return " ";
		}else {
			return DateUtil.format(sdate, DateUtil.defaultSimpleFormater);
		}
		
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	public List<FreshOrderProduct> getProduct() {
		return product;
	}

	public void setProduct(List<FreshOrderProduct> product) {
		this.product = product;
	}

	public BigDecimal getCdenom() {
		return cdenom;
	}

	public void setCdenom(BigDecimal cdenom) {
		this.cdenom = cdenom;
	}

	public BigDecimal getCuser() {
		return cuser;
	}

	public void setCuser(BigDecimal cuser) {
		this.cuser = cuser;
	}

	@Override
	public String toString() {
		return "FreshOrder [bid=" + bid + ", uid=" + uid + ", status=" + status
				+ ", money=" + money + ", integral=" + integral + ", freight="
				+ freight + ", payment=" + payment + ", sdate=" + sdate
				+ ", zdate=" + zdate + ", mobile=" + mobile + ", username="
				+ username + ", address=" + address + ", realpayment="
				+ realpayment + ", max_integral=" + max_integral
				+ ", ordersource=" + ordersource + "]";
	}



}
