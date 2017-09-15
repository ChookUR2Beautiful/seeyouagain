package com.xmniao.xmn.core.common.request;



import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
 * @项目名称：saasService
 * @类名称：TypeRequest
 * @类描述：参数中只包含type,用此通用request
 * @创建人： zhangchangyuan
 * @创建时间 2016年4月7日 下午3:16:27
 * @version
 */
public class TypeRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;

	@NotNull(message="类型不能为空")
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TypeRequest [type=" + type + "]";
	}
	
	
}
