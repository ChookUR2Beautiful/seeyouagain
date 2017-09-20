package com.xmniao.xmn.core.live_anchor.dao;

import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveAnchorVideo;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveAnchorVideoDao
 * 
 * 类描述：直播精彩视频Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-8 下午8:24:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveAnchorVideoDao extends BaseDao<TLiveAnchorVideo>{

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月1日下午1:50:24 <br/>
	 * @param anchorVideo
	 */
	void updateBatch(TLiveAnchorVideo anchorVideo);

	
}