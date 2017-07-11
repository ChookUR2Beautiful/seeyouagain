/**
 * 
 */
package com.xmn.saas.service.activity;

import java.sql.SQLException;
import java.util.List;

import com.xmn.saas.controller.h5.coupon.vo.QueryCondition;
import com.xmn.saas.entity.activity.Fullreduction;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   满减活动接口定义
 * 创建人：huangk   
 * 创建时间：2016年9月29日 上午11:18:56      
 */
public interface FullreductionService {
	
	/** 
	 * 根据主键查询满减活动信息
	 * @param id 主键
	 * @return Fullreduction
	 */
	Fullreduction selectByPrimaryKey(Integer id);
	
	/** 
	 * 根据主键删除满减活动信息
	 * @param id 主键
	 */
    int deleteByPrimaryKey(Integer id);

    /** 
	 * 新增满减活动信息(直接新增)
	 * @param Fullreduction
	 */
    int insert(Fullreduction record);

    /** 
	 * 新增满减活动信息(字段为空判断)
	 * @param Fullreduction
	 */
    int insertSelective(Fullreduction record);

    /** 
	 * 更新满减活动信息(字段为空判断)
	 * @param Fullreduction
	 */
    int updateByPrimaryKeySelective(Fullreduction record);

    /** 
	 * 更新满减活动信息(直接更新)
	 * @param Fullreduction
	 */
    int updateByPrimaryKey(Fullreduction record);
    
    /**
   	 * 根据商户id以及活动状态查询商户当前满减列表信息
   	 * @param sellerId商户id
   	 * @param status 活动状态
   	 * @return List<Fullreduction>    返回类型
   	 * @throws SQLException
   	 */
   	List<Fullreduction> queryListBySerllIdAndDate(QueryCondition query) throws SQLException;
   	
   	/**
   	 * 
   	 * 方法描述：统计满减活动参与人数
   	 * 创建人：jianming  
   	 * 创建时间：2016年11月9日 上午10:55:18   
   	 * @param integer
   	 * @return
   	 */
	Integer countJoinNum(Integer integer);
}
