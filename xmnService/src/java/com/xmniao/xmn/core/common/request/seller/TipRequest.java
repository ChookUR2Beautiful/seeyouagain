package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
/**
 * 
* @projectName: xmnService 
* @ClassName: 提示框请求    
* @Description:与专享卡相关的类
* @author: wdh   
* 
 */
public class TipRequest extends BaseRequest{

	/**
	 * 
	 */
	
	@NotNull(message="type不能为空")
	private Integer type; //0活动提示，1协议提示

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	
	
	
}
