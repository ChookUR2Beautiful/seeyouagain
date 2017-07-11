package com.xmn.saas.entity.shop;

/**
 * 
*      
* 类名称：SellerDetailed   
* 类描述：   店铺详细信息
* 创建人：xiaoxiong   
* 创建时间：2016年9月26日 上午10:58:47   
* 修改人：xiaoxiong   
* 修改时间：2016年9月26日 上午10:58:47   
* 修改备注：   
* @version    
*
 */
public class SellerDetailed {
	
	
	/**
	 * 商家ID
	 */
	private Integer sellerid;
	/**
	 * 是否有wifi
	 */
	private Integer isWifi;
	/**
	 * 是否可以停车
	 */
	private Integer isParking;
	/**
	 * 地标
	 */
	private String landMark;
	/**
	 * 平均消费
	 */
	private Double consume;
	
	/**
	 * 是否允许总店提现
	 */
	private int operating;
	
	
	
	public int getOperating() {
		return operating;
	}
	public void setOperating(int operating) {
		this.operating = operating;
	}
	public Integer getIsParking() {
		return isParking;
	}
	public void setIsParking(int isParking) {
		this.isParking = isParking;
	}

	
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public void setIsParking(Integer isParking) {
		this.isParking = isParking;
	}
	public Integer getIsWifi() {
		return isWifi;
	}
	public void setIsWifi(Integer isWifi) {
		this.isWifi = isWifi;
	}
	
	
	
}
