package com.xmniao.dao.live;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface LiveGivedGiftDao {

    /**
     * 
     * 方法描述：统计日常会员打赏信息
     * 创建人： ChenBo
     * 创建时间：2016年12月29日
     * @return Map<String,Object>
     */
    List<Map<String,Object>> dailyConsumeCountList(Map<String,Object> paraMap);
    
    /**
     * 
     * 方法描述：查询24小时内 已经结束的直播记录列表  做主播鸟蛋 补偿操作
     * 创建人：jianming  
     * 创建时间：2017年4月14日 下午6:23:47   
     * @return
     */
	List<Map<String, Object>> queryLiveRecordByEnd();


	
	List<Map<String, Object>> queryLiveGivedGiftByAdvancedStatus(Map<Object, Object> viewRecordMap);

	void modifyBatchLiveGivedGiftById(List<Map<Object, Object>> preGivedGiftInfos);

	 /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月31日下午3:46:24 <br/>
	 * @return
	 */
	List<Map<String, Object>> getLoseGifts();

	 /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月31日下午3:56:27 <br/>
	 * @param integer
	 */
	void updateBackState(Integer integer);

	 /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月31日下午3:56:31 <br/>
	 * @param integer
	 * @param object
	 */
	void updateIncomeEggNums(@Param("liveRecordId")Integer liveRecordId,@Param("percentamount") Double percentamount);
}