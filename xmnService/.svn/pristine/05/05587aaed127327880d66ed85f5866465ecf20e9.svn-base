package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：购买鸟币套餐订单记录表
 * @author yhl
 * 2016-8-31 16:05:08
 * */
public class LivePayOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2852705085907272035L;

	//ID
	private Integer id;
	
	//订单编号
	private String order_no;
	
	//支付流水号
	private Long pay_no;
	
	//第三方交易号
	private String pay_code;
	
	//鸟币充值套餐ID
	private Integer combo_id;
	
	//寻蜜鸟会员ID
	private Integer uid;
	
	//充值支付金额
	private BigDecimal payment;
	
	//赠送赠送鸟币数
	private BigDecimal free_coin;
	
	//实际获得鸟币数
	private BigDecimal real_coin;
	
	//支付方式1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:公众号支付，1000000：钱包支付,1000004：AppStore支付
	private String pay_type;
	
	//充值支付状态 ：0 未支付; 1 已支付成功
	private Integer pay_state;
	
	//支付完成时间
	private Date pay_time;
	
	//创建时间
	private Date create_time;
	
	//修改时间
	private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public Long getPay_no() {
		return pay_no;
	}

	public void setPay_no(Long pay_no) {
		this.pay_no = pay_no;
	}

	public String getPay_code() {
		return pay_code;
	}

	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}

	public Integer getCombo_id() {
		return combo_id;
	}

	public void setCombo_id(Integer combo_id) {
		this.combo_id = combo_id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public BigDecimal getFree_coin() {
		return free_coin;
	}

	public void setFree_coin(BigDecimal free_coin) {
		this.free_coin = free_coin;
	}

	public BigDecimal getReal_coin() {
		return real_coin;
	}

	public void setReal_coin(BigDecimal real_coin) {
		this.real_coin = real_coin;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public Integer getPay_state() {
		return pay_state;
	}

	public void setPay_state(Integer pay_state) {
		this.pay_state = pay_state;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "LivePayOrderInfo [id=" + id + ", order_no=" + order_no
				+ ", pay_no=" + pay_no + ", pay_code=" + pay_code
				+ ", combo_id=" + combo_id + ", uid=" + uid + ", payment="
				+ payment + ", free_coin=" + free_coin + ", real_coin="
				+ real_coin + ", pay_type=" + pay_type + ", pay_state="
				+ pay_state + ", pay_time=" + pay_time + ", create_time="
				+ create_time + ", update_time=" + update_time + "]";
	}
	
	
	
	
}
