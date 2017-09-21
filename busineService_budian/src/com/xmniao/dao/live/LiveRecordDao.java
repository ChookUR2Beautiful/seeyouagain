/**
 * 
 */
package com.xmniao.dao.live;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：busineService
 * 
 * 类名称：LiveRecordDao
 *
 * 创建人： ChenBo
 * 
 * 创建时间：2016年8月22日下午2:13:42
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface LiveRecordDao {
	/**
	 * 获取商家直播记录类型统计
	 * @Title: getSellerLiveRecordCount 
	 * @Description:
	 */
	List<Map<String,Object>> getSellerLiveRecordCount(Map<String,Object> paraMap);
	
	
	/**
	 * 获取有直播记录的商家统计
	 * @Title: getHasLiveRecordSellerList 
	 * @Description:
	 */
	List<Map<String,Object>> getHasLiveRecordSellerList(Map<String,Object> paraMap);
	
	/**
	 * 
	 * 方法描述：统计新时尚大赛选手信息直播场次和时长 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10上午11:14:20 <br/>
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getLiveInfoList(Map<String,Object> paraMap);
}
