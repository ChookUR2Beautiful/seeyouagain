/**
 * 2016年8月25日 下午2:24:01
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：RelieveblackRequst
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月25日 下午2:24:01
 * @version
 */
public class RelieveblackRequst extends BaseRequest {
	/**
	 *long
	 */
	private static final long serialVersionUID = 8014063998474432473L;
	//客户ID
	@NotNull(message="直播客户ID不能为空")
	private  Integer liverId;
	@NotNull(message="操作类型不能为空")
	private Integer type;
	public Integer getLiverId() {
		return liverId;
	}
	public void setLiverId(Integer liverId) {
		this.liverId = liverId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "RelieveblackRequst [liverId=" + liverId + ", type=" + type
				+ "]";
	}
	
	
	
	

	
	
	
}
