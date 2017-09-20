package com.xmniao.xmn.core.marketingmanagement.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMaterialOrderItems
 * 
 * 类描述：物料订单购买物料商品信息
 * 
 * 创建人： yhl
 * 
 * 创建时间：2016年7月15日13:47:56
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TMaterialOrderItems extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5041247698304638954L;

	private Long id;
	
	//'订单号'
	private String order_sn;
	
	//'物料ID'
	private Long material_id; 
	
	//'物料名称'
	private String material_name;
	
	//'购买单价'
	private BigDecimal price;
	
	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public Long getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(Long material_id) {
		this.material_id = material_id;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "TMaterialOrderItems [id=" + id + ", order_sn=" + order_sn
				+ ", material_id=" + material_id + ", material_name="
				+ material_name + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	
	
	
	
}
