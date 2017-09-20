/**   
 * 文件名：BillRecord.java   
 *    
 * 日期：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.marketingmanagement.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TBillBargain
 * 
 * 类描述：爆品订单关系
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年06月12日12时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TBillBargain extends BaseEntity {
	private static final long serialVersionUID = -4755085546479738582L;
	private Integer boid;//主键id
	private Long bid;//订单bid
	private Integer bpid;//爆品ID
	private String pname;//爆品名称
	private BigDecimal price;//爆品价格
	private Integer num;//数量
	private Date sdate;//记录时间
	private String remark;//备注
	private Date startdate;
	private Date enddate;
	private BigDecimal pricelow; //价格区间 最低
	private BigDecimal pricetop; //价格区间 最高
	
	public Integer getBoid() {
		return boid;
	}
	public void setBoid(Integer boid) {
		this.boid = boid;
	}
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public Integer getBpid() {
		return bpid;
	}
	public void setBpid(Integer bpid) {
		this.bpid = bpid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public BigDecimal getPricelow() {
		return pricelow;
	}
	public void setPricelow(BigDecimal pricelow) {
		this.pricelow = pricelow;
	}
	public BigDecimal getPricetop() {
		return pricetop;
	}
	public void setPricetop(BigDecimal pricetop) {
		this.pricetop = pricetop;
	}
	
	
}
