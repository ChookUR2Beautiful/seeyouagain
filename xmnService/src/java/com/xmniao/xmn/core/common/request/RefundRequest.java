package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：RefundRequest   
* 类描述：   退款请求参数
* 创建人：xiaoxiong   
* 创建时间：2016年6月20日 下午6:20:24   
* @version    
*
 */
public class RefundRequest extends BaseRequest{
	@NotNull(message="商家编号不能为空")
	private Integer sellerid; //商家编号
	@NotNull(message="订单编号")
	private Long bid; //订单编号
	@NotNull(message="商家名称")
	private String sellername; //商家名称
	@NotNull(message="发起放不能为空")
	private Integer source; //发起方 1：用户发起 2平台发起
	
	private String apply;//申请原因

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}
	
	

}
