package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SellerListRequest   
* 类描述：   店铺列表请求参数
* 创建人：yezhiyong   
* 创建时间：2016年6月22日 上午11:49:43   
* @version    
*
 */
public class SellerListRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;

	@NotNull(message="页码不能为空")
	private Integer page;//页码:默认1,
	
	@NotNull(message="店铺状态不能为空")
	// 3.6.0版本 type： 1，已上线列表  2，待上线列表   3，草稿箱列表
	private Integer type;//店铺状态:1.审核中; 2.不通过; 3.已签约(已上线); 4.未签约(存草稿)


	private String sUid;

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

	public String getsUid() {
		return sUid;
	}

	public void setsUid(String sUid) {
		this.sUid = sUid;
	}

	@Override
	public String toString() {
		return "SellerListRequest{" +
				"page=" + page +
				", type=" + type +
				", sUid='" + sUid + '\'' +
				'}';
	}
}
