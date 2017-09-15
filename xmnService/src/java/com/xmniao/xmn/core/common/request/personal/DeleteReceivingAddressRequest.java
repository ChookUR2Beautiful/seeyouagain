package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：DeleteReceivingAddressRequest   
* 类描述：   删除收货地址请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月15日 上午9:57:02   
* @version    
*
 */
public class DeleteReceivingAddressRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5539563852029962849L;
	
	//编辑的收货地址id
	@NotNull(message="收货地址id不能为空")
	private Integer receivingAddressId;

	public Integer getReceivingAddressId() {
		return receivingAddressId;
	}

	public void setReceivingAddressId(Integer receivingAddressId) {
		this.receivingAddressId = receivingAddressId;
	}

	@Override
	public String toString() {
		return "DeleteReceivingAddressRequest [receivingAddressId="
				+ receivingAddressId + "]";
	}
	
}
