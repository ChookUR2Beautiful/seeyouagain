package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.common.entity.City;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.fresh.entity.PostageTemplate;
import com.xmniao.xmn.core.fresh.entity.TPostageFreeRule;
import com.xmniao.xmn.core.fresh.entity.TPostageRule;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
 *@ClassName:PostageTemplateDao
 *@Description:运费模板Dao
 *@author hls
 *@date:2016年6月20日下午4:19:48
 */
public interface PostageTemplateDao extends BaseDao<PostageTemplate> {
    /**
     * @Title:getPostageTemplateList
     * @Description:查询运费模板列表
     * @param postageTemplate
     * @return List<PostageTemplate>
     * @throw
     */
	@DataSource("slave")
	public List<PostageTemplate> getPostageTemplateList(PostageTemplate postageTemplate);
	/**
	 * @Title:getPostageRuleList
	 * @Description:指定地区邮费列表
	 * @param postageTemplate
	 * @return List<TPostageRule>
	 * @throw
	 */
	@DataSource("slave")
	public List<PostageTemplate> getPostageRuleList(Object[] obj);
	/**
	 * @Title:getPostageTemplateCount
	 * @Description:查询运费模板列表总数
	 * @param postageTemplate
	 * @return Long
	 * @throw
	 */
	@DataSource("slave")
	public Long getPostageTemplateCount(PostageTemplate postageTemplate);
	/**
	 * @Title:updatePostageTemplate
	 * @Description:修改运费模板
	 * @param postageTemplate void
	 * @throw
	 */
	@DataSource("write")
	public void updatePostageTemplate(PostageTemplate postageTemplate);
	/**
	 * @Title:updatedefaultTemplateUdate
	 * @Description:每次更改操作或添加操作都更新默认模板的udate为最新时间，使之排在最前面
	 * @param postageTemplate void
	 * @throw
	 */
	@DataSource("write")
	public void updatedefaultTemplateUdate(PostageTemplate postageTemplate);
	/**
	 * @Title:updatePostageRule
	 * @Description:修改指定地区邮费
	 * @param postageTemplate void
	 * @throw
	 */void batchUpdate();
	@DataSource("write")
	public void updatePostageRule(List<TPostageRule> postageRuleList);
	/**
	 * @Title:updatePostageFreeRule
	 * @Description:修改指定条件包邮
	 * @param postageTemplate void
	 * @throw
	 */
	@DataSource("write")
	public void updatePostageFreeRule(List<TPostageFreeRule> postageFreeRuleList);
	
	/**
	 * @Title:getPostageTemplate
	 * @Description:根据模板ID查询模板详情（包含指定包邮地区和包邮条件）
	 * @param id
	 * @return PostageTemplate
	 * @throw
	 */
	@DataSource("slave")
	public PostageTemplate getPostageTemplate(Integer tid);
	/**
	 * @Title:getPostageFreeRuleList
	 * @Description:根据模板ID查询包邮条件列表
	 * @param tid
	 * @return List<TPostageFreeRule>
	 * @throw
	 */
	@DataSource("slave")
	public List<TPostageFreeRule> getPostageFreeRuleList(Integer tid);
	/**
	 * @Title:addPostageTemplateReturnId
	 * @Description:添加运费模板 并返回 插入id
	 * @param postageTemplate
	 * @return int
	 * @throw
	 */
	@DataSource("write")
	public int addPostageTemplateReturnId(PostageTemplate postageTemplate);
	/**
	 * @Title:addPostageTemplateReturnId
	 * @Description:批量添加指定地区邮费 并返回 插入id
	 * @param postageTemplate
	 * @return int
	 * @throw
	 */
	@DataSource("write")
	public int addTPostageRule(List<TPostageRule> postageRule);
	/**
	 * @Title:addPostageTemplateReturnId
	 * @Description:批量添加包邮条件 并返回 插入id
	 * @param postageTemplate
	 * @return int
	 * @throw
	 */
	@DataSource("write")
	public int addTPostageFreeRule(List<TPostageFreeRule> postageFreeRule);
	/**
	 * @Title:getPostageFreeRuleList
	 * @Description:根据模板ID查询指定地区邮费列表
	 * @param tid
	 * @return List<TPostageFreeRule>
	 * @throw
	 */
	@DataSource("slave")
	public List<TPostageRule> getCityPostageRuleList(Integer tid);
	/**
	 * @Title:deletePostageTemplate
	 * @Description:根据id删除运费模板
	 * @param tid
	 * @return int
	 * @throw
	 */
	public int delPostageTemplate (Integer tid);
	/**
	 * @Title:delCityPostageRuleList
	 * @Description:根据id删除指定地区邮费delPostageFreeRuleList
	 * @param tid int
	 * @throw
	 */
	public int delCityPostageRuleList(Integer tid);
	/**
	 * @Title:delPostageFreeRuleList
	 * @Description:根据id删除包邮条件
	 * @param tid
	 * @return int
	 * @throw
	 */
	public int delPostageFreeRuleList(Integer tid);
	/**
	 * @Title:getTAreaList
	 * @Description:区域初始化列表
	 * @return List<TArea>
	 * @throw
	 */
	//省
	@DataSource("slave")
	public List<TArea> getProvinceList();
	//市
	@DataSource("slave")
	public List<City> getCityList(Object[] obj);
	
	/**
	 * @Description: 查询所有城市的areaid
	 * @return:List<String>
	 * @author:lifeng
	 * @time:2016年7月14日下午3:15:26
	 */
	public List<String> getCityAreaIds();
	
	/**
	 * @Description: 查询地区的邮费
	 * @Param:tid
	 * @return:List<String>
	 * @author:lifeng
	 * @time:2016年7月15日下午1:42:47
	 */
	public List<TPostageRule> getPostageRuleListByTid(Integer tid);
}
