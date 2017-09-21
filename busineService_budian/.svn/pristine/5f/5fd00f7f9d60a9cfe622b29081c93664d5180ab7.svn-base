package com.xmniao.dao.vstar;

import java.util.Map;

import com.xmniao.domain.vstar.TVstarRewardRecord;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TVstarRewardRecordDao
 * 
 * 类描述： 新时尚大赛推荐奖励发放记录
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-7-27 上午10:34:22 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarRewardRecordDao {
	/**
	 * 
	 * 方法描述：删除记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-27上午11:25:19 <br/>
	 * @param id
	 * @return
	 */
    int delete(Long id);

    /**
     * 
     * 方法描述：新增记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-7-27上午11:25:39 <br/>
     * @param record
     * @return
     */
    int add(TVstarRewardRecord record);

    /**
     * 
     * 方法描述：根据ID获取记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-7-27上午11:25:59 <br/>
     * @param id
     * @return
     */
    TVstarRewardRecord getObject(Long id);

    /**
     * 
     * 方法描述：更新记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-7-27上午11:27:05 <br/>
     * @param record
     * @return
     */
    int update(TVstarRewardRecord record);
    
    /**
     * 
     * 方法描述：根据条件查询单个记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-7-27上午11:27:36 <br/>
     * @param record
     * @return
     */
    TVstarRewardRecord selectByBean(TVstarRewardRecord record);
    
    /**
     * 
     * 方法描述：获取新时尚大赛推荐奖励配置 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-7-27上午11:24:50 <br/>
     * @param paramMap
     * @return
     */
    Map<String,Object> getVstarRewardConf(Map<String,Object> paramMap);

}