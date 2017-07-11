package com.xmn.saas.controller.api.v1.shop.vo;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.shop.Dishes;

public class DishesCreateRequest extends Request{
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * 图片
	 */
	@NotNull(message="图片不能为空")
	private String url;
	
	/**
	 * 名称
	 */
	@NotNull(message="推荐菜名称不能为空")
	private String name;
	
	/**
	 * 价格
	 */
	@NotNull(message="商品价格不能为空")
	private Double price;
	
	/**
	 * 描述
	 */
	private String remark;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Dishes converToDishes(Integer sellerid){
		Dishes dishes=new Dishes();
		BeanUtils.copyProperties(this, dishes);
		dishes.setSellerid(sellerid);
		
		return dishes;
	}

}
