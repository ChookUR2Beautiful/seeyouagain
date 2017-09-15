package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SellerInfoRequest   
* 类描述：   查询商户信息请求参数
* 创建人：xiaoxiong   
* 创建时间：2016年5月23日 上午9:44:16   
* @version    
*
 */
public class SellerInfoRequest extends BaseRequest{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="商户ID不能为空")
	public Integer sellerid;

	public Integer sellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	
	
}
