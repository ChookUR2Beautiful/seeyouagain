package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;


/**
 * 
*      
* 类名称：PlacePayRequest   
* 类描述：代付请求参数   
* 创建人：xiaoxiong   
* 创建时间：2016年8月31日 上午10:48:33   
* 修改人：xiaoxiong   
* 修改时间：2016年8月31日 上午10:48:33   
* 修改备注：   
* @version    
*
 */
public class PlacePayRequest {
		
	@NotNull(message="套餐ID不能为空")
	private Integer id;
	
	@NotNull(message="代付手机号码不能为空")
	private String placeTel;//代付手机号码

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaceTel() {
		return placeTel;
	}

	public void setPlaceTel(String placeTel) {
		this.placeTel = placeTel;
	}
	
	
	
	
}
