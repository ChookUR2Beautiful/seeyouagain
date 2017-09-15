package com.xmniao.xmn.core.order.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.order.entity.ExperienceOfficerOrder;
import com.xmniao.xmn.core.order.entity.ExperienceofficerConfig;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.springframework.stereotype.Repository;

/**
 * 项目描述：XmnService
 * API描述：订单操作
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version
 * */
@Repository
public interface ExperienceConfigDao {
	/**
	 * 查询体验卡详情
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public ExperienceofficerConfig queryExprienceInfoById(Map<Object, Object>  params);
	
	/**
	 * 查询体验卡详情
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<ExperienceofficerConfig> queryExprienceList(Map<Object, Object>  params);
	
	/**
	 * 新增美食体验卡信息
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public int inserExperienceOrder(Map<Object, Object>  params);
	
	/**
	 * 新增美食体验卡信息
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public ExperienceOfficerOrder queryExperienceOrderDetail(Map<Object, Object>  params);

	/**
	 * 美食体验官订单列表
	 * @param params
	 * @return
	 */
	@DataSource("joint")
	List<ExperienceOfficerOrder> queryExperienceOrderList(Map<Object, Object>  params);

	
	
}
