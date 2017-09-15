package com.xmniao.xmn.core.match.request;

import com.xmniao.xmn.core.base.BaseRequest;

public class StarMatchPageRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1184543483091597380L;
	
	
	private String ruid; //分享链接uid

	private String areaVersion;//地区版本号
	public String getRuid() {
		return ruid;
	}
	public void setRuid(String ruid) {
		this.ruid = ruid;
	}
	public String getAreaVersion() {
		return areaVersion;
	}
	public void setAreaVersion(String areaVersion) {
		this.areaVersion = areaVersion;
	}
	

	
}
