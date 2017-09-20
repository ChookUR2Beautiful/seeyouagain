package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerOrder;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerUser;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TExperienceofficerOrderDao {
	int add(TExperienceofficerOrder experienceofficerOrder);
	
	List<TExperienceofficerOrder> getList(TExperienceofficerOrder user);
	
	long count(TExperienceofficerOrder order);
	
	/* 获取会员体验卡购买信息 */
	List<TExperienceofficerUser> getExperienceofficerUserList(TExperienceofficerUser user);
	
	/* 获取单条会员体验卡购买信息 */
	TExperienceofficerUser getExperienceofficerUser(TExperienceofficerUser user);
	
	long countExperienceofficerUser(TExperienceofficerUser user);
	
	@DataSource("burs")
	List<Map<String,Object>> getUrs(Map<String,Object> map);
}