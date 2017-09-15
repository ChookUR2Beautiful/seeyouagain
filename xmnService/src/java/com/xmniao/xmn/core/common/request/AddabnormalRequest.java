package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;


/**
 * 
* 项目名称：xmnService   
* 类名称：AddabnormalRequest   
* 类描述：    举报请求参数
* 创建人：xiaoxiong   
* 创建时间：2016年8月4日 下午3:09:23   
* @version    
*
 */
public class AddabnormalRequest extends BaseRequest{
	@NotNull(message="商户ID不能为空")
	private Integer sellerid;
	
	@NotNull(message="类型不能为空")
	private Integer type;
	
	private Integer uid;
	
	

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	
}
