package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 用户消息请求实体类
 * @author yhl
 * 2016年8月10日17:52:11
 * */
public class MessageManagerInfoRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2563095152996950766L;
	
	@NotNull(message="用户ID不能为空")
	public Integer id;
	
	@NotNull(message="页码不能为空")
	public String page;

	public Integer getId() {
		return id;
	}

	public void setUid(Integer id) {
		this.id = id;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}



	
}
