package com.xmniao.xmn.core.businessman.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.SystemConstants;

public class TSellerIncomeFlow extends BaseEntity {

	private static final long serialVersionUID = 2841727353114854267L;

	private Long rowNo;//序号
	private Long bid;//订单号
	private String stringBid;
	private Date sdate;//下单时间
	
	private Date zdate;//支付时间
	
	private Integer sellerID;//商家编号
	private String sellerName;//商家名称
	private Integer xmerID;//寻蜜客Id
	private String xmerName;//寻蜜客姓名
	private String sellerAddress;//商家地址
	private String sellerPrincipalName;//负责人姓名
	private String principalPhoneNo;//负责人手机号
	private String commission;//订单佣金
	private Double money;//订单金额/元
	private String sellerAmount;//分成金额/元
	
	
	private String nname;//用户昵称
	private String paytype;//支付方式
	private String phoneid;//用户手机
	private Integer orderType;//订单类型  1:平台 2:商户 3:粉丝 4:套餐
	public Long getRowNo() {
		return rowNo;
	}
	public void setRowNo(Long rowNo) {
		this.rowNo = rowNo;
	}
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Integer getSellerID() {
		return sellerID;
	}
	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Integer getXmerID() {
		return xmerID;
	}
	public void setXmerID(Integer xmerID) {
		this.xmerID = xmerID;
	}
	public String getXmerName() {
		return xmerName;
	}
	public void setXmerName(String xmerName) {
		this.xmerName = xmerName;
	}
	public String getSellerAddress() {
		return sellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	public String getSellerPrincipalName() {
		return sellerPrincipalName;
	}
	public void setSellerPrincipalName(String sellerPrincipalName) {
		this.sellerPrincipalName = sellerPrincipalName;
	}
	public String getPrincipalPhoneNo() {
		return principalPhoneNo;
	}
	public void setPrincipalPhoneNo(String principalPhoneNo) {
		this.principalPhoneNo = principalPhoneNo;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getSellerAmount() {
		if(commission != null && !"".equals(commission)){
			JSONObject jsonObj = JSONObject.parseObject(commission);
			return jsonObj.get("seller_amount") + "";
		}
		return sellerAmount;
	}
	public void setSellerAmount(String sellerAmount) {
		this.sellerAmount = sellerAmount;
	}
	public String getStringBid() {
		return stringBid;
	}
	public void setStringBid(String stringBid) {
		this.stringBid = stringBid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	public String getPayTypeText(){
		return SystemConstants.getPayTypeText(paytype);
	}
	
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
		return zdate;
	}
	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}
	
	public String getZdateText(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(zdate);
	}
	@Override
	public String toString() {
		return "TSellerIncomeFlow [rowNo=" + rowNo + ", bid=" + bid
				+ ", stringBid=" + stringBid + ", sdate=" + sdate
				+ ", sellerID=" + sellerID + ", sellerName=" + sellerName
				+ ", xmerID=" + xmerID + ", xmerName=" + xmerName
				+ ", sellerAddress=" + sellerAddress + ", sellerPrincipalName="
				+ sellerPrincipalName + ", principalPhoneNo="
				+ principalPhoneNo + ", commission=" + commission + ", money="
				+ money + ", sellerAmount=" + sellerAmount + ", nname=" + nname
				+ ", paytype=" + paytype + ", phoneid=" + phoneid
				+ ", orderType=" + orderType + "]";
	}
	
}
