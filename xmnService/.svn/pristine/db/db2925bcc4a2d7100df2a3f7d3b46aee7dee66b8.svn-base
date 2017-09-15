package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 查询当前商家的鸟粉专享卡 请求参数
 */
public class CurrentSellerDebitcardRequest extends BaseRequest{

	private static final long serialVersionUID = -803606891048389883L;

	//商家id
	private Long sellerid;
	
	@NotNull(message="入口type不能为空")
	private Integer type;

	public Long getSellerid() {
		return sellerid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setSellerid(Long sellerid) {
		this.sellerid = sellerid;
	}

	@Override
	public String toString() {
		return "CurrentSellerDebitcardRequest [sellerid=" + sellerid + ", type=" + type + "]";
	}


}
