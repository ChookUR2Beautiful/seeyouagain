package com.xmn.saas.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.common.Area;


@Repository
public interface AreaDao {

	List<Area> findAreaByPid(int i);
	
	/**
	 * 
	 * 方法描述：根据主键获取区域名
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 下午5:04:01   
	 * @param areaId
	 * @return
	 */
	String getName(Integer areaId);
	
	/**
	 * 
	 * 方法描述：统计下级地址数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午6:07:20   
	 * @param cityId
	 * @return
	 */
	Integer countLastArea(Integer cityId);
	
	/**
	 * 
	 * @Description: 查询地区
	 * 
	 * @author xiaoxiong
	 * @date 2016年11月1日
	 */
	List<Area> selectAreaByidOrname(Map<String, Object> paramMap);
	
	String selectAreaById(int id);


}
