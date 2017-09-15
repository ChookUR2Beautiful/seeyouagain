package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PhoneRequest   
* 类描述：添加创客接口   
* 创建人：xiaoxiong   
* 创建时间：2016年5月24日 上午9:34:53   
* @version    
*
 */
public class PhoneRequest extends BaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="手机号码不能为空")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	

}
