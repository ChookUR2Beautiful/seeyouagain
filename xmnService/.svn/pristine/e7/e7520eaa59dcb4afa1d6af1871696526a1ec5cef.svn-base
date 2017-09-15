/**
 * 2016年8月11日 下午1:38:19
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.EggMoneyEntity;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：AnchorSendDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月11日 下午1:38:19
 * @version
 */
@Repository
public interface AnchorPersonDao {

	/**
	 * 
	* @Title: queryLiveAchorByUid
	* @Description: 根据uid查询主播基本信息
	* @return Map<Object,Object>
	 */
	@DataSource("burs")
	public Map<Object,Object> queryLiveAchorByUid(int uid);
	
	
	/**
	 * 
	* @Title: queryAnchorRecord
	* @Description: 根据用户uid及时间查询当月直播记录
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryAnchorRecord(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryEggMoneyNum
	* @Description: 查询我的鸟蛋今日，累计，可提现数量
	* @return Map<Object,Object>
	 */
	@DataSource("xmnpay")
	public Map<Object,Object> queryEggMoneyNum(int uid);
	/**
	 * 
	* @Title: queryEggMoneyDetail
	* @Description: 查询鸟蛋消费明细
	* @return List<EggMoneyEntity>
	 */
	@DataSource("xmnpay")
	public List<EggMoneyEntity>  queryEggMoneyDetail(Map<Object, Object> map);

}
