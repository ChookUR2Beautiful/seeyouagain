package com.xmniao.xmn.core.live.service;

import java.util.List;

import com.xmniao.xmn.core.live.request.MyFriendsRequest;
import com.xmniao.xmn.core.live.request.MyIndirectlyFriendsRequest;
import com.xmniao.xmn.core.live.response.RichFriendResponse;

/**
 * 
* @projectName: xmnService 
* @ClassName: MyRichFriendsService    
* @Description:我的壕友接口   
* @author: liuzhihao   
* @date: 2017年3月1日 上午11:42:12
 */
public interface MyRichFriendService {

	/**
	 * 查看我的直接壕友
	 * @return
	 */
	public Object queryMyDirectFriends(MyFriendsRequest request);
	
	/**
	 * 查看我的间接壕友
	 * @return
	 */
	public Object queryMyIndirectlyFriends(MyIndirectlyFriendsRequest request);
	
	
	/**
	 * 
	 * 查询我的壕友
	 * @param uid  当前登录用户ID
	 * @param fuid 壕友ID 直接壕友ID 或者 间接壕友ID
	 * @param page
	 * @param pageSize
	 * @param friendType 壕友类型 0 直接壕友 1间接壕友
	 * @return
	 */
	public List<RichFriendResponse> findMyFriends(String uid,String fuid,Integer page,Integer pageSize,Integer friendType);
	
}
