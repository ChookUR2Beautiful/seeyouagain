package com.xmniao.dao.live;

import com.xmniao.domain.live.TVerExcitationDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TVerExcitationDetailDao
 * 
 * 类描述： V客充值奖励方案详情DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-27 上午10:12:16 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVerExcitationDetailDao {


	/**
	 * 
	 * 方法描述：获取充值奖励方案详情列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-27上午10:20:03 <br/>
	 * @param excitationDetailReq
	 * @return
	 */
    List<TVerExcitationDetail> getList(TVerExcitationDetail excitationDetailReq);


	/**
	 * 根据等级id 查询方案A奖励细节
	 * @param rankId
	 * @return
	 */
	List<TVerExcitationDetail> queryExcitationDetailsForPlanA(@Param("rankId") Integer rankId);
}