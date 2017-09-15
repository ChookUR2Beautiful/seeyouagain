package com.xmniao.xmn.core.live.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: MyDirectFriendsRequest    
* @Description:直接壕友列表请求类   
* @author: liuzhihao   
* @date: 2017年3月1日 下午5:58:11
 */
@SuppressWarnings("serial")
public class MyFriendsRequest extends BaseRequest{

	@NotNull(message="壕友类型不能为空")
	private Integer friendType;//壕友类型 0 直接好友 1 间接壕友
	
	@NotNull(message="分页页码不能为空")
	private Integer page;//分页页码

	public Integer getFriendType() {
		return friendType;
	}

	public void setFriendType(Integer friendType) {
		this.friendType = friendType;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "MyFriendsRequest [friendType=" + friendType + ", page=" + page + "]";
	}
	
}
