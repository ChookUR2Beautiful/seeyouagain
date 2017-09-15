package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * 店铺流水提成明细
 */
public class StoreInfoRequest extends BaseRequest {


	private static final long serialVersionUID = 5053556434258147768L;

	@NotNull(message="商铺ID不能为空")
	private Integer sellerId; //商铺ID

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public String toString() {
		return "StoreInfoRequest{" +
				"sellerId=" + sellerId +
				'}';
	}
}
