/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TLiveFocusDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveFocus;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveFocusService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-9-4 上午11:47:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveFocusService extends BaseService<TLiveFocus> {

	/**
	 * 注入直播关注DAO
	 */
	@Autowired
	private TLiveFocusDao liveFocusDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveFocusDao;
	}
	
	/**
	 * 
	 * 方法描述：删除指定主播机器人粉丝<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-9-4上午11:49:45 <br/>
	 * @param endUid
	 * @return
	 */
	public  int deleteByEndUid(Integer endUid){
		return liveFocusDao.deleteByEndUid(endUid);
	}
	
	/**
	 * 
	 * 方法描述：随机获取一个用户信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-9-4下午2:21:11 <br/>
	 * @return
	 */
	public Map<String,Object> getRandomFans(){
		return liveFocusDao.getRandomFans();
	}
	

}
