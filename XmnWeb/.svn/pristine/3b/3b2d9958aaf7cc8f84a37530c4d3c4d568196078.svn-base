/**
 * 文件名：TActivity.java
 * 
 * 创建日期:2015-01-14 10:03:36
 * 
 * Copyright © 寻蜜鸟网络科技有限公司
 */
package com.xmniao.xmn.core.marketingmanagement.entity;


import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TActivity
 * 
 * 类说明：营销活动管理
 * 
 * 创建人：yang'xu
 * 
 * 创建时间：2015-01-14 10：09：03
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 */
public class TPhoneBill extends BaseEntity {

	private static final long serialVersionUID = 4097740642012046107L;

	private Long pid; // '主键ID',
	private String bid; // '单号',
	private String code; // '电信订单编号',
	private String pcoler; // '颜色',
	private String pmodel; // '机型',
	private Integer pnum; // '数量',
	private Double price; // 单价
	private Double totalPrice; // '总金额',
	private Double depPrice; // 定金
	private String checkPhone; // '选中号码',
	private String getUser; // '收货人',
	private String getCardId; // '收货人身份证',
	private String getUserPhone; // '收货人手机号码',
	private Long provinceId; // '收货人省份ID',
	private String province; // '收货人省份',
	private Long cityId; // '收货人城市ID',
	private String city; // '收货人城市',
	private String address; // '收货人详细地址',
	private Integer pstatus; // '订单状态 0 待支付 1 已支付 10 已完成',
	private Date ptime; // '下单时间',
	private Long uid; // '下单用户ID',
	private String uphone; // '下单用户手机号码',
	private Date ftime; // '支付时间',
	private String payid; // '支付订单编号',
	private String number; // '支付流水号',

	private Date ptimeStart; // '下单时间搜索条件',
	private Date ptimeEnd; // '下单时间搜索条件',

	private Integer userNum; // 参与用户数
	private Integer orderNum; // 订单总数
	private Integer notSellNum; // 已定未售数量
	private Integer sellNum; // 已销售数量
	private Integer priceNum; // 已销售总金额
	private Integer notSellPriceNum; // 已定未售总金额
	private Double pay; // 已支付金额
	private Double notPay; // 未支付金额
	private Double allDepPrice; // 未支付定金总额
	private Double payDepPrice; // 已支付定金总额

	/**
	 * 创建一个新的实例 TPhoneBill.
	 * 
	 */
	public TPhoneBill() {
		super();
	}
	
	public String getPstatusText() {
		if(pstatus == null) return null;
		if(pstatus == 0) return "待支付";
		if(pstatus == 1) return "已支付";
		return null;
	}
	
	/**
	 * @return the notSellNum
	 */
	public Integer getNotSellNum() {
		return notSellNum;
	}

	/**
	 * @param notSellNum the notSellNum to set
	 */
	public void setNotSellNum(Integer notSellNum) {
		this.notSellNum = notSellNum;
	}

	/**
	 * @return the notSellPriceNum
	 */
	public Integer getNotSellPriceNum() {
		return notSellPriceNum;
	}

	/**
	 * @param notSellPriceNum the notSellPriceNum to set
	 */
	public void setNotSellPriceNum(Integer notSellPriceNum) {
		this.notSellPriceNum = notSellPriceNum;
	}

	/**
	 * @param pay the pay to set
	 */
	public void setPay(Double pay) {
		this.pay = pay;
	}

	/**
	 * @return the payDepPrice
	 */
	public Double getPayDepPrice() {
		return payDepPrice;
	}

	/**
	 * @param payDepPrice the payDepPrice to set
	 */
	public void setPayDepPrice(Double payDepPrice) {
		this.payDepPrice = payDepPrice;
	}

	/**
	 * @param notPay the notPay to set
	 */
	public void setNotPay(Double notPay) {
		this.notPay = notPay;
	}

	/**
	 * @param allDepPrice the allDepPrice to set
	 */
	public void setAllDepPrice(Double allDepPrice) {
		this.allDepPrice = allDepPrice;
	}
	

	/**
	 * @return the pay
	 */
	public Double getPay() {
		return pay;
	}

	/**
	 * @return the notPay
	 */
	public Double getNotPay() {
		return notPay;
	}

	/**
	 * @return the allDepPrice
	 */
	public Double getAllDepPrice() {
		return allDepPrice;
	}

	/**
	 * @return the userNum
	 */
	public Integer getUserNum() {
		return userNum;
	}

	/**
	 * @param userNum
	 *            the userNum to set
	 */
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	/**
	 * @return the orderNum
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 *            the orderNum to set
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the sellNum
	 */
	public Integer getSellNum() {
		return sellNum;
	}

	/**
	 * @param sellNum
	 *            the sellNum to set
	 */
	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	/**
	 * @return the priceNum
	 */
	public Integer getPriceNum() {
		return priceNum;
	}

	/**
	 * @param priceNum
	 *            the priceNum to set
	 */
	public void setPriceNum(Integer priceNum) {
		this.priceNum = priceNum;
	}


	/**
	 * @return the pid
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @param bid
	 *            the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the pcoler
	 */
	public String getPcoler() {
		return pcoler;
	}

	/**
	 * @param pcoler
	 *            the pcoler to set
	 */
	public void setPcoler(String pcoler) {
		this.pcoler = pcoler;
	}

	/**
	 * @return the pmodel
	 */
	public String getPmodel() {
		return pmodel;
	}

	/**
	 * @param pmodel
	 *            the pmodel to set
	 */
	public void setPmodel(String pmodel) {
		this.pmodel = pmodel;
	}

	/**
	 * @return the pnum
	 */
	public Integer getPnum() {
		return pnum;
	}

	/**
	 * @param pnum
	 *            the pnum to set
	 */
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the depPrice
	 */
	public Double getDepPrice() {
		return depPrice;
	}

	/**
	 * @param depPrice
	 *            the depPrice to set
	 */
	public void setDepPrice(Double depPrice) {
		this.depPrice = depPrice;
	}

	/**
	 * @return the checkPhone
	 */
	public String getCheckPhone() {
		return checkPhone;
	}

	/**
	 * @param checkPhone
	 *            the checkPhone to set
	 */
	public void setCheckPhone(String checkPhone) {
		this.checkPhone = checkPhone;
	}

	/**
	 * @return the getUser
	 */
	public String getGetUser() {
		return getUser;
	}

	/**
	 * @param getUser
	 *            the getUser to set
	 */
	public void setGetUser(String getUser) {
		this.getUser = getUser;
	}

	/**
	 * @return the getCardId
	 */
	public String getGetCardId() {
		return getCardId;
	}

	/**
	 * @param getCardId
	 *            the getCardId to set
	 */
	public void setGetCardId(String getCardId) {
		this.getCardId = getCardId;
	}

	/**
	 * @return the getUserPhone
	 */
	public String getGetUserPhone() {
		return getUserPhone;
	}

	/**
	 * @param getUserPhone
	 *            the getUserPhone to set
	 */
	public void setGetUserPhone(String getUserPhone) {
		this.getUserPhone = getUserPhone;
	}

	/**
	 * @return the provinceId
	 */
	public Long getProvinceId() {
		return provinceId;
	}

	/**
	 * @param provinceId
	 *            the provinceId to set
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the cityId
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the pstatus
	 */
	public Integer getPstatus() {
		return pstatus;
	}

	/**
	 * @param pstatus
	 *            the pstatus to set
	 */
	public void setPstatus(Integer pstatus) {
		this.pstatus = pstatus;
	}

	/**
	 * @return the ptime
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPtime() {
		return ptime;
	}

	/**
	 * @param ptime
	 *            the ptime to set
	 */
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * @return the uphone
	 */
	public String getUphone() {
		return uphone;
	}

	/**
	 * @param uphone
	 *            the uphone to set
	 */
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	/**
	 * @return the ftime
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getFtime() {
		return ftime;
	}

	/**
	 * @param ftime
	 *            the ftime to set
	 */
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}

	/**
	 * @return the payid
	 */
	public String getPayid() {
		return payid;
	}

	/**
	 * @param payid
	 *            the payid to set
	 */
	public void setPayid(String payid) {
		this.payid = payid;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the ptimeStart
	 */
	public Date getPtimeStart() {
		return ptimeStart;
	}

	/**
	 * @param ptimeStart
	 *            the ptimeStart to set
	 */
	public void setPtimeStart(Date ptimeStart) {
		this.ptimeStart = ptimeStart;
	}

	/**
	 * @return the ptimeEnd
	 */
	public Date getPtimeEnd() {
		return ptimeEnd;
	}

	/**
	 * @param ptimeEnd
	 *            the ptimeEnd to set
	 */
	public void setPtimeEnd(Date ptimeEnd) {
		this.ptimeEnd = ptimeEnd;
	}

	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TPhoneBill [pid=" + pid + ", bid=" + bid + ", code=" + code
				+ ", pcoler=" + pcoler + ", pmodel=" + pmodel + ", pnum="
				+ pnum + ", price=" + price + ", totalPrice=" + totalPrice
				+ ", depPrice=" + depPrice + ", checkPhone=" + checkPhone
				+ ", getUser=" + getUser + ", getCardId=" + getCardId
				+ ", getUserPhone=" + getUserPhone + ", provinceId="
				+ provinceId + ", province=" + province + ", cityId=" + cityId
				+ ", city=" + city + ", address=" + address + ", pstatus="
				+ pstatus + ", ptime=" + ptime + ", uid=" + uid + ", uphone="
				+ uphone + ", ftime=" + ftime + ", payid=" + payid
				+ ", number=" + number + ", ptimeStart=" + ptimeStart
				+ ", ptimeEnd=" + ptimeEnd + ", userNum=" + userNum
				+ ", orderNum=" + orderNum + ", notSellNum=" + notSellNum
				+ ", sellNum=" + sellNum + ", priceNum=" + priceNum
				+ ", notSellPriceNum=" + notSellPriceNum + ", pay=" + pay
				+ ", notPay=" + notPay + ", allDepPrice=" + allDepPrice
				+ ", payDepPrice=" + payDepPrice + "]";
	}
}
