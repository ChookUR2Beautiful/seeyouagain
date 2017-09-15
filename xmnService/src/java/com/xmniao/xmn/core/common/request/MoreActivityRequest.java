package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

public class MoreActivityRequest extends BaseRequest{
	
	private Integer Id;	//主键
	
	private Integer type=0;//类型
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	

}
