package com.xmniao.xmn.core.common.request.xmer;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * 折扣
 */
public class XmerDiscountRequest extends BaseRequest{

	private static final long serialVersionUID = -3515952480165124368L;

	private Integer type = 1;  // 1为签约店铺折扣列表

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "XmerDiscountRequest{" +
				"type=" + type +
				'}';
	}
}
