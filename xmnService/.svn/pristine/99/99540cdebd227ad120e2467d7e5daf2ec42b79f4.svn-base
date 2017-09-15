/**
 * 2016年5月19日下午2:49:02
 */
package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 *@ClassName:SaasOrderRequest
 *@Description:购买saas押金套餐创建订单接口请求参数
 *@author hls
 *@date:2016年5月19日下午2:49:02
 */
public class SaasOrderRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="押金套餐ID不能为空")
	private Integer id;//Saas押金套餐ID
	
	@NotNull(message="支付方式不能为空")
	private Integer type;//支付方式
	
	//邀请人的id
	private Integer parentid = 0;
	
	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

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
		return "SaasOrderRequest [id=" + id + ", type=" + type + ", parentid="
				+ parentid + "]";
	}

}
