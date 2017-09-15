package com.xmniao.xmn.core.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


/**
 * 项目描述：XmnService
 * API描述：体验卡推荐活动
 * @author wdh
 * @version
 * */
@Repository
public interface ExperienceActivityDao {


	/**
	 * @param paraMap
	 * @return
	 */
	@DataSource("joint")
	public  List<Map<Object, Object>> findRecommendAcitivities(Map<Object,Object> paraMap);

	/**提醒我
	 * @param paraMap
	 * @return
	 */
	@DataSource("joint")
	public int insertExperiencePush(Map<Object, Object> paraMap);

	/**查询活动
	 * @param activityId
	 * @return
	 */
	@DataSource("joint")
	public Map<Object, Object> findExperenceActivityById(@Param("activityId")Integer activityId);

	/**根据活动id查询需要提醒的uid
	 * @param activityId
	 * @return
	 */
	@DataSource("joint")
	public List<String> findExperiencePushUid(Integer activityId);

	/**查询体验官参与活动信息
	 * @param paraMap
	 * @return
	 */
	@DataSource("joint")
	Map<Object, Object> findExperenceEntrollerRecord(Map<Object, Object> paraMap);

	/**根据活动id和uid查询是否提过我
	 * @param paraMap
	 * @return
	 */
	@DataSource("joint")
	public Integer  countExperenceActivityPushByUid(Map<Object, Object> paraMap);
	
}
