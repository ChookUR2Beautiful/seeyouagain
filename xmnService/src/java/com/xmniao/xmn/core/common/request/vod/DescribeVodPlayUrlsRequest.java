package com.xmniao.xmn.core.common.request.vod;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class DescribeVodPlayUrlsRequest extends BaseRequest{
	
	@NotNull(message="视频ID不能为空")
	private String fileid;

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	
	

}
