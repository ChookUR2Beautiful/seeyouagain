package com.xmniao.xmn.core.market.entity.pay;

import java.math.BigDecimal;

/**
 * 订单购买上返回参数
* @projectName: xmnService 
* @ClassName: ProductResponse    
* @Description:   
* @author: liuzhihao   
* @date: 2017年1月11日 下午12:57:12
 */
public class ProductResponse {

	private Integer codeId;//商品唯一标识
	
	private String pname;//商品名称
	
	private String goodsName;//商品活动名称
	
	private String breviary;//商品缩略图
	
	private BigDecimal price;//商品价格
	
	private BigDecimal amount;//商品加价
	
	private BigDecimal integral;//商品积分
	
	private BigDecimal freight;//运费
	
	//购买 数量
    private Integer num;
    
    //规格id组合
    private String attrIds;  
    
    //规格值组合
    private String attrVals;
    
    private Integer activityId;//商品ID
    
    private BigDecimal totalPrice;//总价格
    
    private BigDecimal totalIntegral;//总积分

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getAttrIds() {
		return attrIds;
	}

	public void setAttrIds(String attrIds) {
		this.attrIds = attrIds;
	}

	public String getAttrVals() {
		return attrVals;
	}

	public void setAttrVals(String attrVals) {
		this.attrVals = attrVals;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBreviary() {
		return breviary;
	}

	public void setBreviary(String breviary) {
		this.breviary = breviary;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(BigDecimal totalIntegral) {
		this.totalIntegral = totalIntegral;
	}
	
	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	@Override
	public String toString() {
		return "ProductResponse [codeId=" + codeId + ", pname=" + pname + ", goodsName=" + goodsName + ", breviary=" + breviary
			+ ", price=" + price + ", amount=" + amount + ", integral=" + integral + ", freight=" + freight + ", num=" + num
			+ ", attrIds=" + attrIds + ", attrVals=" + attrVals + ", activityId=" + activityId + ", totalPrice=" + totalPrice
			+ ", totalIntegral=" + totalIntegral + "]";
	}

	
    
}
