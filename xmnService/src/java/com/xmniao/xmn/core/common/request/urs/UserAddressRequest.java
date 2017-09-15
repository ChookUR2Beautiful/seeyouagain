package com.xmniao.xmn.core.common.request.urs;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;


/**
 * 收货地址请求参数
 * */
public class UserAddressRequest  extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2422636097790504867L;
	
	
	@NotNull(message = "是否查询默认收货地址不能为空")
	private Integer defaults = 0;


	public Integer getDefaults() {
		return defaults;
	}


	public void setDefaults(Integer defaults) {
		this.defaults = defaults;
	}


	@Override
	public String toString() {
		return "UserAddressRequest [defaults=" + defaults + "]";
	}

	
	
	
	
}
