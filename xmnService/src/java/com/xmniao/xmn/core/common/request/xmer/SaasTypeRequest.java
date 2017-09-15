package com.xmniao.xmn.core.common.request.xmer;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

public class SaasTypeRequest extends BaseRequest{

	private String sUid;

	public String getsUid() {
		return sUid;
	}

	public void setsUid(String sUid) {
		this.sUid = sUid;
	}

	@Override
	public String toString() {
		return "SaasTypeRequest{" +
				"sUid='" + sUid + '\'' +
				'}';
	}
}
