package com.xmniao.xmn.core.live.request;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: MyIndirectlyFriendsRequest    
* @Description:我的间接壕友请求类   
* @author: liuzhihao   
* @date: 2017年3月1日 下午6:23:34
 */
@SuppressWarnings("serial")
public class MyIndirectlyFriendsRequest extends MyFriendsRequest{

	@NotNull(message="我的直接壕友ID不能为空")
	private String fuid;//我的直接壕友UID

	public String getFuid() {
		return fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	@Override
	public String toString() {
		return "MyIndirectlyFriendsRequest [fuid=" + fuid + "]";
	}
	
}
