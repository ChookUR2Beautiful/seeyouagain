package com.xmn.saas.controller.api.v1.shop.vo;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.shop.Dishes;

/**
 * 
*      
* 类名称：DishesModifyRequest   
* 类描述：   推荐菜修改请求参数
* 创建人：xiaoxiong   
* 创建时间：2016年9月29日 下午1:57:55   
* 修改人：xiaoxiong   
* 修改时间：2016年9月29日 下午1:57:55   
* 修改备注：   
* @version    
*
 */
public class DishesModifyRequest extends Request{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * 推荐菜ID
	 */
	private Integer id;
	
	/**
	 * 图片
	 */
	private String url;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 价格
	 */
	private Double price;
	
	/**
	 * 描述
	 */
	private String remark;
	
	
	/**
	 * 状态
	 */
	private Integer datastatus;
	
	

	public Integer getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Integer datastatus) {
		this.datastatus = datastatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public Dishes converToDishes(){
		Dishes dishes=new Dishes();
		
		BeanUtils.copyProperties(this, dishes);
		
		return dishes;
	}
	

}
