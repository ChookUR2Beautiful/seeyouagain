package com.xmniao.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.common.OrderSnGenerator;

public class MallSubOrderBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String orderSn;
	private String subOrderSn;
	private Long supplierId;
	private Long uid;
	private BigDecimal totalAmount;
	private BigDecimal preferential;
	private BigDecimal cashAmount;
	private BigDecimal balanceAmount;
	private BigDecimal coinAmount;
	private BigDecimal integralAmount;
	private BigDecimal freight;
	private Integer status;
	private Date createTime;
	private Date modifyTime;

	private String address;
	private String mobile;
	private String consignee;
	
	
	private Integer rid;
	private Integer wareNum;
	private Integer expTid;
	
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
		this.orderSn = orderSn;
	}

	public String getSubOrderSn() {
		return subOrderSn;
	}

	public void setSubOrderSn(String subOrderSn) {
		this.subOrderSn = subOrderSn;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPreferential() {
		return preferential;
	}

	public void setPreferential(BigDecimal preferential) {
		this.preferential = preferential;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getIntegralAmount() {
		return integralAmount;
	}

	public void setIntegralAmount(BigDecimal integralAmount) {
		this.integralAmount = integralAmount;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	private List<MallOrderProductBean> products;

	public List<MallOrderProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<MallOrderProductBean> products) {
		this.products = products;
	}
	
	public BigDecimal getTotalProductFreight(){
		BigDecimal total = new BigDecimal("0");
		
		for(MallOrderProductBean product : this.products){
			total = total.add(product.getFreight());
		}
		
		return total;
	}
	
	public Integer getTotalWareNum(){
		Integer total = 0;
		
		for(MallOrderProductBean product : this.products){
			total = total+product.getWareNum();
		}
		
		return total;
	}
	
	public BigDecimal getTotalProductPrice(){
		BigDecimal total = new BigDecimal("0");
		
		for(MallOrderProductBean product : this.products){
			total = total.add(product.getTotalPrice());
		}
		
		return total;
	}
	
	public BigDecimal getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(BigDecimal coinAmount) {
		this.coinAmount = coinAmount;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getWareNum() {
		return wareNum;
	}

	public void setWareNum(Integer wareNum) {
		this.wareNum = wareNum;
	}

	public Integer getExpTid() {
		return expTid;
	}

	public void setExpTid(Integer expTid) {
		this.expTid = expTid;
	}

	public void init(Long supplier,List<MallOrderProductBean> products,MallOrderBean order){
		BigDecimal zero = new BigDecimal("0");
		this.supplierId = supplier;
		this.uid = Long.valueOf(order.getUid());
		this.orderSn = String.valueOf(order.getBid());
		this.subOrderSn = OrderSnGenerator.generate();
		this.status = 0;
		this.createTime = new Date();
		this.modifyTime = this.createTime;
		this.address = order.getAddress();
		this.mobile = order.getMobile();
		this.consignee = order.getUsername() != null ? order.getUsername() : "匿名" ;
		
		this.products = products;
		
		this.freight = this.getTotalProductFreight();
		this.totalAmount = this.getTotalProductPrice();
		
		this.cashAmount = zero;
		this.balanceAmount = zero;
		this.coinAmount = zero;
		this.integralAmount = zero;
		this.preferential = zero;
		
		this.rid=order.getRid();
		this.expTid = products.get(0).getExpTid();
		this.wareNum = getTotalWareNum();
	}
	
}
