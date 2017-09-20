/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.dao.TLiveShareDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveShare;

/**
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveShareService
 * 
 * 类描述： 直播邀请分享Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-22 上午10:01:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveShareService extends BaseService<TLiveShare> {
	
	@Autowired
	private TLiveShareDao liveShareDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveShareDao;
	}

	/**
	 * 方法描述：批量更新状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-22上午10:22:29 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateBatch(String ids, String status) {
		// TODO Auto-generated method stub
		Resultable result=new Resultable();
		try {
			Map<String, Object> map =new HashMap<String,Object>();
			map.put("ids", ids.split(","));
			map.put("status", status);
			liveShareDao.updateBatch(map);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("执行TLiveShareService-->updateBatch方法异常:"+e.getMessage(), e);
		}
		return result;
	}

}
