package com.xmniao.xmn.core.market.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: MarketReceivAddressDao    
* @Description:用户收货地址dao   
* @author: liuzhihao   
* @date: 2016年12月20日 上午11:53:14
 */
@Repository
public interface MarketReceivAddressDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(ReceivingAddress record);

	@DataSource("joint")
    int insertSelective(ReceivingAddress record);

	@DataSource("joint")
    ReceivingAddress selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(ReceivingAddress record);

	@DataSource("joint")
    int updateByPrimaryKey(ReceivingAddress record);
	
	/**
	 * 通过用户ID查询所有的收货列表
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<ReceivingAddress> findAllByUid(Map<Object,Object> map);
	
	/**
	 * 通过用户ID查询收货地址信息
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	ReceivingAddress findOneByUid(Map<Object,Object> map);
	
	/**
	 * 统计用户收货地址数量
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int sumReceivingAddressCounts(Map<Object,Object> map);
	
	/**
	 * 查询所有非默认状态的收货地址信息
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<ReceivingAddress> findAllIsNotDefaultByUid(Map<Object,Object> map);
}