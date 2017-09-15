package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：AnchorAnnunciateListRequest   
* 类描述：  通告列表请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月12日 上午11:14:21   
* @version    
*
 */
public class AnchorAnnunciateListRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853702828497857692L;

	//页码
	@NotNull(message="页码不能为空")
	private Integer page;
	
	//0:预告列表	1:通告列表	2:历史通告
	@NotNull(message="预告/通告类型不能为空")
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "AnchorAnnunciateListRequest [page=" + page + ", type=" + type
				+ "]";
	}
	
}
