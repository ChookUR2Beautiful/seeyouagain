/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmniao.xmn.core.live_anchor.entity.TLiveFocus;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FansConfigureThread
 * 
 * 类描述： 粉丝配置自定义线程
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-9-4 上午11:01:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class FansConfigureThread implements Runnable {
	
	Logger log=LoggerFactory.getLogger(getClass());
	
	private TLiveFocusService liveFocusService;
	
	private Map<String,Object> params;

	
	/**
	 * @param liveFocusService
	 * @param params
	 */
	public FansConfigureThread(TLiveFocusService liveFocusService,
			Map<String, Object> params) {
		super();
		this.liveFocusService = liveFocusService;
		this.params = params;
	}


	@Override
	public void run() {
		this.log.info("批量插入机器人粉丝,params:"+params);
		
		List<TLiveFocus> liveFocusList= new ArrayList<TLiveFocus>();
		Integer liverEndId = new Integer(params.get("liverEndId").toString());
		Integer endUid = new Integer(params.get("endUid").toString());
		int fansNum = (int) params.get("fansNum");
		
		liveFocusService.deleteByEndUid(endUid);//清空历史机器人粉丝
		
		for(int i=0;i<fansNum;i++){
			Map<String, Object> randomFans = liveFocusService.getRandomFans();
			Integer liverStrId = (Integer) randomFans.get("id");
			Integer strUid = (Integer) randomFans.get("uid");
			TLiveFocus liveFocus= new TLiveFocus();
			liveFocus.setLiverStrId(liverStrId);
			liveFocus.setLiverEndId(liverEndId);
			liveFocus.setStrUid(strUid);
			liveFocus.setEndUid(endUid);
			liveFocus.setFansType((byte) 2);//1.正常粉丝 2.机器人
			liveFocusList.add(liveFocus);
		}
		
		if(liveFocusList!=null && liveFocusList.size()>0){
			liveFocusService.addBatch(liveFocusList);
		}
		
	}

}
