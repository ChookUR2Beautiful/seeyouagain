package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: RemoveCouponRequest    
* @Description:删除优惠卷请求参数   
* @author: liuzhihao   
* @date: 2016年11月26日 下午5:06:12
 */
@SuppressWarnings("serial")
public class RemoveCouponRequest extends BaseRequest{

	@NotNull(message="优惠卷类型不能为空")
	private Integer type;//优惠卷类型
	
	@NotNull(message="优惠卷id不能为空")
	private Integer id;//优惠卷id

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
