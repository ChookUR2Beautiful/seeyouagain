package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.xmer.entity.SaasPackage;

/**
 * 
 * 项目名称： xmnService
 * 类名称： SaasPackageDao
 * 类描述：押金(SAAS)套餐列表接口dao
 * 创建人： lifeng
 * 创建时间： 2016年5月19日下午3:11:03
 * @version
 */
@Repository
public interface SaasPackageDao {

	/**
	 * 
	 * @Title:
	 * @Description: 
	 * @return Map<Object,Object>
	 * @param paramMap
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySaasPackage(Map<Object,Object> paramMap);
	/**
	 * @Title:selectSaasPackage
	 * @Description:根据套餐id查询套餐详情
	 * @param id
	 * @return SaasPackage
	 * @throw
	 */
	@DataSource("joint")
	public SaasPackage selectSaasPackage(Integer id);
	
}
