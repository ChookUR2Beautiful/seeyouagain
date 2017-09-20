package com.xmniao.xmn.core.businessman.entity;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 商家服务员推广配置实体类
 *@ClassName:TSpreadConfig
 *@Description:TODO
 *@author hls
 *@date:2016年2月25日下午8:47:30
 */
public class TSpreadConfig extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8671600430124748432L;
	private Integer id;//商家服务员推广配置ID
	private String sellerId;// 商家ID
	private String sellername;// 商家名称
	private Double startMoney;// 订单起始金额
	private Double endMoney;// 订单结束金额
	private Double money;//奖励金额
	private Integer status;//配置状态
	private String rdate;//'创建时间'
	private String udate;//'更新时间'
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public double getStartMoney() {
		return startMoney;
	}
	public void setStartMoney(double startMoney) {
		this.startMoney = startMoney;
	}
	public double getEndMoney() {
		return endMoney;
	}
	public void setEndMoney(double endMoney) {
		this.endMoney = endMoney;
	}
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setStartMoney(Double startMoney) {
		this.startMoney = startMoney;
	}
	public void setEndMoney(Double endMoney) {
		this.endMoney = endMoney;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}

}
