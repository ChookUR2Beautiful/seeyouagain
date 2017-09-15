package com.xmniao.xmn.core.integral.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.xmniao.xmn.core.market.entity.pay.ProductBill;



/**
 * 
* 类名称：IntegralBillListResponse   
* 类描述：   积分订单列表响应实体类
* 创建人：xiaoxiong   
* 创建时间：2016年11月25日 上午10:33:07
 */
public class IntegralBillListResponse {
	
	/**
	 * 订单号
	 */
	private String bid;
	/**
	 * 订单金额
	 */
	private String money;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 
	 */
	private String sdate;
	/**
	 * 1主订单 2子订单
	 */
	private int type;
	
	/**
	 * 订单商品
	 */
	private List<ProductBill> subSet;
	/**
	 * 运费
	 */
	private BigDecimal freight;
	
	/**
	 * 积分
	 */
	private BigDecimal integral;
	/**
	 * 描述
	 */
	private String statusDesc;
	
	public String getStatusDesc() {
		if(type==1)
		{
			switch (status) {
			case 0:
				statusDesc="购物.待付款";
				break;
			case 2:
				statusDesc="购物.取消订单";
				break;
			}
		}else
		{
			switch (status) {
			case 0:
				statusDesc="购物.待发货";
				break;
			case 1:
				statusDesc="购物.已发货";
				break;
			case 2:
			statusDesc="购物.取消订单";
				break;
			case 3:
				statusDesc="购物.退款中";
				break;
			case 4:
				statusDesc="购物.退款失败";
				break;
			case 5:
				statusDesc="购物.已退款";
				break;
			case 6:
				statusDesc="购物.订单完成";
				break;
			}
		}
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public List<ProductBill> getSubSet() {
		return subSet;
	}
	public void setSubSet(List<ProductBill> subSet) {
		this.subSet = subSet;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public BigDecimal getFreight() {
		return freight.setScale(2, RoundingMode.HALF_UP);
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public BigDecimal getIntegral() {
		return integral.setScale(2, RoundingMode.HALF_UP);
	}
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
	
	
}
