package com.xmniao.dao.xmer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.SaasOrderBean;
import com.xmniao.domain.order.SaasSoldOrderBean;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：SaasOrder
 * 
 * 类描述： Saas销售套餐   Saas签约套餐 DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年5月28日 上午11:06:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface SaasOrderDao {
	
	/*
	 * 获取寻蜜客套餐购买订单
	 */
	Map<String,Object> getSaasOrderById(String id);
	
	/*
	 * 修改寻蜜客套餐购买订单
	 */
	int updateSaasOrder(Map<String,String> paraMap);
	
	/*
	 * 获取商家签约订单
	 */
	Map<String,Object> getSaasSoldOrderById(String id);
	
	/*
	 * 修改商家签约订单
	 */
	int updateSaasSoldOrder(Map<String,String> paraMap);
	
	/*
	 * 获取需重新分账的saas签约订单
	 */
	List<SaasSoldOrderBean> getResplitSaasSoldOrder(Map<String,String> paramMap);
	
	/*
	 * 获取寻蜜客套餐购买订单，返回一个实体类
	 */
	SaasOrderBean getSaasOrderByOrdersn(String ordersn);
	
	/*
	 * 统计saas订单数
	 */
	long getCountSaasPackageOrder(Map<String,Object> saasMap);
	
	/**
	 * 
	 * 方法描述：根据寻蜜客类型获取寻蜜客订单
	 * 创建人：jianming  
	 * 创建时间：2017年5月10日 上午11:51:13   
	 * @param uid
	 * @param saasChannel
	 * @param saasOrdersn 
	 * @return
	 */
	Map<String, Object> getSaasOrderByUidType(@Param("uid")java.lang.Integer uid,@Param("saasChannel") java.lang.Integer saasChannel,@Param("saasOrdersn") String saasOrdersn);
	
	/**
	 * 
	 * 方法描述：更改库存
	 * 创建人：jianming  
	 * 创建时间：2017年5月10日 上午11:59:36   
	 * @param saasOrder
	 * @return
	 */
	Integer updateStock(Map<String, Object> saasOrder);
	
	/**
	 * 创建云脉网络分账通知数据
	 * @param map
	 * @return
	 */
	public int insertMaibaoLedgerNotify(Map<String,Object> map);
	

	 /**
	 * 方法描述：统计寻蜜客/V客 销售总数
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月12日下午4:18:31 <br/>
	 * @param uid
	 * @param saasChannel 
	 * @return
	 */
	Long sumStock(@Param("uid")Integer uid,@Param("saasChannel") Integer saasChannel);
	
	 /**
	 * 方法描述：根据V客uid查询其名下所有兑换成功的订单 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月22日下午5:08:52 <br/>
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getSaasOrderByUid(@Param("uid") Integer uid);
	
	/**
	 * 
	 * 方法描述：根据订单号获取脉宝分账记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16下午6:36:03 <br/>
	 * @param orderNo
	 * @return
	 */
	Map<String,Object> getMaibaoLedgerByorderNo(String orderNo);

	/**
	 * 
	 * 方法描述：统计用户获取的SAAS总数 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月9日下午5:15:29 <br/>
	 * @param uid
	 * @param saasChannel
	 * @return
	 */
	int sumSaasNumber(@Param("uid")Integer uid,@Param("saasChannel")Integer saasChannel);
	
	/**
	 * 
	 * 方法描述：插入Vke的SAAS兑换订单 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年8月9日下午8:48:38 <br/>
	 * @param map
	 * @return
	 */
	int insertVkeSaasOrder(Map<String,Object> map);
}
