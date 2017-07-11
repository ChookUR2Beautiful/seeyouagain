/**
 * 
 */
package com.xmn.saas.service.activity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.xmn.saas.entity.activity.FullreductionRecord;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   满减活动领取记录服务
 * 创建人：huangk   
 * 创建时间：2016年9月29日 下午2:42:36      
 */

public interface FullreductionRecordService {
	
	/**
	 * 根据主键查询
	 * @param int
	 * @return FullreductionRecord    返回类型
	 * @throws
	 */
	FullreductionRecord queryById(int id);

	/**
	 * 根据满减活动id查询用户领取明细
	 * @param aid 满减活动id
	 * @return List<Map<String,Object>> 用户领取记录
	 */
	List<Map<String,Object>> queryListByAid(int aid) throws SQLException;
	
	/**
	 * @param aid 满减活动id
	 * @return BigDecimal 总的减免金额
	 * @throws SQLException
	 */
	BigDecimal countReductionAmountByAid(int aid);
	
	/**
	 * 统计活动总的参与人数
	 * @param aid 满减活动id
	 * @return int 总的参与人数
	 */
	int countJoinNumByAid(int aid);
	
	/**
	 * 统计活动总的参与人数
	 * @param aid 满减活动id
	 * @return int 新会员数
	 */
	int countNewuserByAid(int aid);
}
