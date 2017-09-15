package com.xmniao.xmn.core.common.request.urs;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;


/**
 * 用户关注请求参数
*      
* 类名称：UrsCollectUrsInsertRequest   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年11月15日 上午10:04:48     
*
 */
public class UrsCollectUrsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 被关注用户
	 */
	@NotNull(message="关注用户不能为空")
	private Integer cuid;

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	@Override
	public String toString() {
		return "UrsCollectUrsInsertRequest [cuid=" + cuid + "]";
	}
	
	

}
