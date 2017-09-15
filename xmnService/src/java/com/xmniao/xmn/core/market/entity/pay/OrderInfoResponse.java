package com.xmniao.xmn.core.market.entity.pay;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
* @projectName: xmnService 
* @ClassName: OrderInfoResponse    
* @Description:订单详情返回参数   
* @author: liuzhihao   
* @date: 2017年1月13日 下午12:43:30
 */
public class OrderInfoResponse {

	private Long bid;//订单ID
	
	private BigDecimal money;//订单总金额
	
	private BigDecimal cuser;//订单优惠卷面额
	
	private BigDecimal integral;//订单所需积分金额
	
	private BigDecimal freight;//订单所需运费
	
	private Integer status;//订单状态
	
	private String sdate;//订单下单时间
	
	private String zdate;//支付时间/关闭时间
	
	private String nname;//收件人姓名
	
	private String phoneid;//收件人联系电话
	
	private String address;//收件地址
	
	private Integer wareNum;//订单购买总数量
	
	private Integer remindExpress;//提醒发货状态 
	
	private Integer supplierId;//供应商ID
	
	private BigDecimal payPrice;//实付金额
	
	private Integer source;//数据来源
	
	private String sellerPhone;//供应商联系电话
	
	private String platformPhone;//客服电话       
	
	private String orderMark;//订单描述
	
	private Integer sendUid;//送礼人ID
	
	private String sendName;//送礼人名称
	
	private List<ProductResponse> products;

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getCuser() {
		return cuser;
	}

	public void setCuser(BigDecimal cuser) {
		this.cuser = cuser;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getZdate() {
		return zdate;
	}

	public void setZdate(String zdate) {
		this.zdate = zdate;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getWareNum() {
		return wareNum;
	}

	public void setWareNum(Integer wareNum) {
		this.wareNum = wareNum;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
	
	public Integer getRemindExpress() {
		return remindExpress;
	}

	public void setRemindExpress(Integer remindExpress) {
		this.remindExpress = remindExpress;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public String getPlatformPhone() {
		return platformPhone;
	}

	public void setPlatformPhone(String platformPhone) {
		this.platformPhone = platformPhone;
	}

	public String getOrderMark() {
		return orderMark;
	}

	public void setOrderMark(String orderMark) {
		this.orderMark = orderMark;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	
	public Integer getSendUid() {
		return sendUid;
	}

	public void setSendUid(Integer sendUid) {
		this.sendUid = sendUid;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	@Override
	public String toString() {
		return "OrderInfoResponse [bid=" + bid + ", money=" + money + ", cuser=" + cuser + ", integral=" + integral + ", freight="
			+ freight + ", status=" + status + ", sdate=" + sdate + ", zdate=" + zdate + ", nname=" + nname + ", phoneid=" + phoneid
			+ ", address=" + address + ", wareNum=" + wareNum + ", remindExpress=" + remindExpress + ", supplierId=" + supplierId
			+ ", payPrice=" + payPrice + ", source=" + source + ", sellerPhone=" + sellerPhone + ", platformPhone=" + platformPhone
			+ ", orderMark=" + orderMark + ", sendUid=" + sendUid + ", sendName=" + sendName + ", products=" + products + "]";
	}
	
}
