/**    
 * 文件名：ManorRelatedMapper.java    
 *    
 * 版本信息：    
 * 日期：2017年6月16日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.dao.manor;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.entity.manor.PropsRecord;

/**
 * 
 * 项目名称：payService_manor
 * 
 * 类名称：ManorRelatedMapper
 * 
 * 类描述： 庄园旁边辅助Mapper
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年6月16日 上午10:41:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface ManorRelatedMapper {

	/**
	 * 
	 * 方法描述：获取庄园的阳光流水记录
	 * 创建人： ChenBo
	 * 创建时间：2017年6月16日
	 * @param record
	 * @return List<PropsRecord>
	 */
	List<PropsRecord> getSunshineRecord(Map<String,String> map);
	
	/**
	 * 
	 * 方法描述：统计庄园的阳光流水记录
	 * 创建人： ChenBo
	 * 创建时间：2017年6月16日
	 * @param record
	 * @return long
	 */
	long countSunshineRecord(Map<String,String> map);
	
	/**
	 * 
	 * 方法描述：获取庄园的兑换记录
	 * 创建人： ChenBo
	 * 创建时间：2017年6月16日
	 * @param record
	 * @return List<PropsRecord>
	 */
	List<PropsRecord> getConvertRecord(Map<String,String> map);
	
	/**
	 * 
	 * 方法描述：统计庄园的兑换记录
	 * 创建人： ChenBo
	 * 创建时间：2017年6月16日
	 * @param record
	 * @return long
	 */
	long countConvertRecord(Map<String,String> map);
	
	/**
	 * 
	 * 方法描述：批量统计会员的花蜜库存
	 * 创建人： ChenBo
	 * 创建时间：2017年7月5日
	 * @param uidList
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> totalNectarNumberByUidsAndPropsType(@Param("uidList") List<Long> uidList);
	
	/**
	 * 
	 * 方法描述：批量统计会员的花蜜兑换收入
	 * 创建人： ChenBo
	 * 创建时间：2017年7月5日
	 * @param uidList
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> totalConvertCoin(@Param("uidList") List<Long> uidList);
}
