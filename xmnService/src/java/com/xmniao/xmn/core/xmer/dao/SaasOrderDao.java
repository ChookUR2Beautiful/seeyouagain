/**
 * 2016年5月19日下午3:23:26
 */
package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.xmer.entity.SaasOrder;
import com.xmniao.xmn.core.xmer.entity.SaasSignOrder;
import org.apache.ibatis.annotations.Param;

/**
 *@ClassName:SaasOrderDao
 *@Description:SAAS软件订单DAO
 *@author hls
 *@date:2016年5月19日下午3:23:26
 */
@Resource
public interface SaasOrderDao {
	/**
	 * @Title:addSaasOrder
	 * @Description:新增SaaS软件套餐订单
	 * @param so void
	 * @throw
	 */
	@DataSource("joint")
	public int addSaasOrder(SaasOrder so); 
	
	
	/**
	 * 
	* @Title: addSaasSignOrder
	* @Description: 创建寻蜜客签约商户订单
	* @return void
	 */
	@DataSource("joint")
	public void addSaasSignOrder(SaasSignOrder saasSignOrder);
	
	/**
	 * 
	* @Title: queryOrderByOrdersn
	* @Description: 查询此订单号是否存在
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryOrderByOrdersn(String orderid);
	
	/**
	 * 
	* @Title: queryOrderNums
	* @Description: 查询用户套餐剩余数量
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public Map<Object,Object> queryOrderNums(Integer uid);

	/**
	 * 查询用户套餐剩余数量
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryOrderNumsV2(Integer uid);

	
	/***
	 * 
	* @Title: updateOrderNums
	* @Description: 更新订单表
	* @return Integer    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public Integer updateOrderNums(Map<Object,Object>map);

	/**
	 * 
	* @Title: querySaasOrderInfoByOrdersn
	* @Description: 根据订单号查询用户购买saas套餐的信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> querySaasOrderInfoByOrdersn(String ordersn);

	/**
	 * 
	* @Title: modifyXmer
	* @Description:更新寻蜜客的头衔
	* @return void    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("burs")
	public void modifyXmer(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryXmerLevelList
	* @Description: 查询寻蜜客所有等级
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("burs")
	public List<Map<Object,Object>> queryXmerLevelList();

	/***
	 * 通过商铺名称，电话号码，详情地址判断是否存在重复商铺
	 */
	@DataSource("joint")
	public Integer checkSellerInfoIsRepeat(Map<Object, Object> checkSellerInfoMap);

	/**
	 * 
	* @Title: querySaasOrder
	* @Description: 查询签约订单的支付详情
	* @return Integer    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Integer querySaasOrder(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: modifySaasOrder
	* @Description: 寻蜜客签约店铺修改代付信息
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int modifySaasOrder(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: sumNumsAndStockByUid
	* @Description: 根据寻蜜客id查询购买的套餐总数跟库存数量
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> sumNumsAndStockByUid(Integer uid);
	
	/**
	 * 
	* @Title: getSignCount
	* @Description: 查询寻蜜客签约上线的店铺数量
	* @return int
	 */
	@DataSource("joint")
	public int getSignCount(Integer uid);
	
	/**
	 * 
	* @Title: querySaasOrderList
	* @Description: 查询saas套餐订单
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> querySaasOrderList(Integer uid);
	
	/**
	 * 
	* @Title: getXmerSaasNums
	* @Description: 查询一批寻蜜客的总签约套数
	* @return int
	 */
	@DataSource("joint")
	public Integer getXmerSaasNums(Map<String ,Object> params);

	/**
	 * 
	* @Title: getSaasAmount
	* @Description: 获取剩余saas押金金额
	* @return Double    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Double getSaasaStockAmount(String uid);

	/**
	 * 修改套餐为已退款 剩余套餐数量为0
	* @Title: udpateXmerOrderStatusAndsoldnums
	* @Description: 
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void udpateXmerOrderStatusAndsoldnums(String uid);
	
	@DataSource("joint")
	public void updateOrderOtherTel(Map<String, String> params);
	

	/**
	 * 
	 * @Description: 查询订单号
	 * @author xiaoxiong
	 * @date 2016年9月7日
	 * @version
	 */
	@DataSource("joint")
	public List<String> getXmerSaasOrdersns(Map<String, Object> params);

	/**
	 * 
	* @Title: updateSaasSoldOrder
	* @Description: 更新签约店铺订单的来源
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void updateSaasSoldOrder(SaasSignOrder saasSignOrder);


	@DataSource("joint")
	void updateNewSaasSoldOrder(Map<String, Object> params);

	// 删除saas订单通过店铺id
	@DataSource("joint")
	void deleteSaasOrder(@Param("sellerid") Integer sellerid);


//	查询一批用户签约的店铺
	@DataSource("joint")
	List<Map<Object, Object>> getSignSellerByUidList(Map<Object, Object> param);

	// 批量查询订单信息
	@DataSource("joint")
	List<Map<Object, Object>> querySaasOrderInfoByOrdersnList(Map<Object, Object> param);


}	
