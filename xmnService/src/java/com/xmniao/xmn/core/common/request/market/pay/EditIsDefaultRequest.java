package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: EditIsDefaultRequest    
* @Description:修改收货地址状态请求类  
* @author: liuzhihao   
* @date: 2016年12月21日 下午3:00:11
 */
@SuppressWarnings("serial")
public class EditIsDefaultRequest extends BaseRequest{

	@NotNull(message="收货地址ID不能为空")
	private Integer id;//收货地址ID
	
	@NotNull(message="收货地址状态不能为空")
	private Integer type;//收货地址状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EditIsDefaultRequest [id=" + id + ", type=" + type + "]";
	}


}
