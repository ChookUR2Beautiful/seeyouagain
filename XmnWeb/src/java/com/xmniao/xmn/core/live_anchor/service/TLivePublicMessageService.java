/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.TLivePublicMessageDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLivePublicMessage;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLivePublicMessageService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-5 下午2:31:41 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLivePublicMessageService extends BaseService<TLivePublicMessage> {

	/**
	 * 注入直播公共消息服务
	 */
	@Autowired 
	private TLivePublicMessageDao livePublicMessageDao;
	
	/**
	 * 注入主播用户服务
	 */
	@Autowired 
	private BLiverDao liverDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return livePublicMessageDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-7下午5:02:36 <br/>
	 * @param publicMsg
	 * @return
	 */
	public List<TLivePublicMessage> getListInfo(TLivePublicMessage publicMsg) {
		List<Integer> liverIds=new ArrayList<Integer>();
		List<BLiver> liverList =new ArrayList<BLiver>();
		List<TLivePublicMessage> list = getList(publicMsg);
		for(TLivePublicMessage message:list){
			liverIds.add(message.getSendLiverId());
		}
		if(liverIds!=null && liverIds.size()>0){
			liverList = liverDao.selectLiverInfoByIds(liverIds.toArray());
		}
		for(TLivePublicMessage message:list){
			for(BLiver liver:liverList){
				if(message.getSendLiverId().compareTo(liver.getId())==0){
					message.setNname(liver.getNickname());
				}
			}
		}
		return list;
	}

}
