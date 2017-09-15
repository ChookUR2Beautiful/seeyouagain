package com.xmniao.xmn.core.wealth.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：IncomeInfoDao   
* 类描述：   收入明细接口Dao
* 创建人：yezhiyong	   
* 创建时间：2016年5月27日 上午11:12:02   
* @version    
*
 */
@Repository
public interface IncomeInfoDao {

	/**
	 * 
	* @Title: queryAccountidByUid
	* @Description: 查询用户钱包id
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("xmnpay")
	public Integer queryAccountidByUid(Integer uid);

	/**
	 * 
	* @Title: sumIncome
	* @Description: 查询总收入
	* @return Double    返回类型
	* @throws
	 */
	@DataSource("xmnpay")
	public Double sumIncome(Integer uid);

	/**
	 * 
	* @Title: queryMikeAmountByuid
	* @Description: 查询未分账的收入明细
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryMikeAmountByuid(Map<Object, Object> incomeMap);

	/**
	 * 
	* @Title: querySaasMount
	* @Description: 查询saas未到账
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySaasMount(int parseInt);

}
