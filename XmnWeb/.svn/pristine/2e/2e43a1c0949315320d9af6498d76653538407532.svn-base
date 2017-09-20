/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TLiveBroadcastDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveRecordDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveBroadcast;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.CryptDecryptUtil;
import com.xmniao.xmn.core.util.HttpUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveBroadcast
 * 
 * 类描述： 直播广播服务Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-24 下午3:11:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveBroadcastService extends BaseService<TLiveBroadcast> {
	
	private static final String SENDBORADCAST_URL = PropertiesUtil.readValue("http.foreshow.url") + "/live/web/liveRadio";//发送广播调用接口

	private static final String edKey = "xunminiaozhibo11";
	
	private static final String	edIv = "xmnlive1xmnlive1";
	
	/**
	 * 注入广播服务
	 */
	@Autowired
	private TLiveBroadcastDao broadcastDao;
	
	/**
	 * 注入通告服务
	 */
	@Autowired
	private TLiveRecordDao liveRecordDao;
	
	/**
	 * 
	 * 方法描述：根据主键删除广播 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-24下午2:59:16 <br/>
	 * @param id
	 * @return
	 */
    public int deleteById(Integer id){
    	return broadcastDao.deleteById(id);
    }

    /**
     * 
     * 方法描述：添加广播消息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-10-24下午2:58:53 <br/>
     * @param record
     * @return
     */
    public void add(TLiveBroadcast record){
    	 broadcastDao.add(record);
    }


    /**
     * 
     * 方法描述：根据主键查询广播消息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-10-24下午2:57:22 <br/>
     * @param id
     * @return
     */
    public TLiveBroadcast selectById(Integer id){
    	return broadcastDao.selectById(id);
    }

    /**
     * 
     * 方法描述：更新广播消息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-10-24下午2:55:57 <br/>
     * @param record
     * @return
     */
    public Integer update(TLiveBroadcast record){
    	return broadcastDao.update(record);
    }

	/**
	 * 方法描述：返回广播列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-24下午4:34:37 <br/>
	 * @param liveBroadcast
	 * @return
	 */
	public List<TLiveBroadcast> getList(TLiveBroadcast broadcast) {
		return broadcastDao.getList(broadcast);
	}
	
	/**
	 * 
	 * 方法描述：统计广播数量 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-24下午5:11:37 <br/>
	 * @param broadcast
	 * @return
	 */
	public Long count(TLiveBroadcast broadcast){
		return broadcastDao.count(broadcast);
	}

	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return broadcastDao;
	}

	/**
	 * 方法描述：获取正在直播的通告 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-25上午11:21:26 <br/>
	 * @param liveRecord
	 * @return
	 */
	public List<TLiveRecord> getLiveRecordList(TLiveRecord liveRecord) {
		return liveRecordDao.getListWithRowNum(liveRecord);
	}
	
	/**
	 * 
	 * 方法描述：调用发送广播接口 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-3下午2:25:15 <br/>
	 * @param broadcast
	 * @return
	 */
	public Object sendBroadcast(TLiveBroadcast broadcast){
		Map<String,Object> request=new HashMap<String,Object>();
		Map<String,Object> params=new HashMap<String,Object>();
		JSONObject response=null;
		try {
			params.put("radioId", broadcast.getId());
			String crypt = CryptDecryptUtil.crypt(JSON.toJSONString(params), Base64.getBase64(edKey), Base64.getBase64(edIv));
			request.put("data", crypt);
			response = HttpUtil.getInstance().postForObject(SENDBORADCAST_URL, request);
			this.log.info(JSON.toJSONString(params)+"-->"+response.toJSONString());
			String state = response.getString("state");
			if("100".equals(state)){
				broadcast.setSendStatus(1);
				broadcast.setUpdateTime(new Date());
				update(broadcast);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
