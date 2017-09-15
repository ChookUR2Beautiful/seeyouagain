/**
 * 2016年8月11日 下午3:35:45
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：BlackAnchorRequest
 * @类描述：黑名单列表请求参数
 * @创建人： yeyu
 * @创建时间 2016年8月11日 下午3:35:45
 * @version
 */
public class BlackAnchorRequest extends BaseRequest {

	@NotNull(message="客户ID不能为空")
	private String uid;//用户id
	@NotNull(message="分页页码不能为空")
	private Integer page;//页数
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	
}
