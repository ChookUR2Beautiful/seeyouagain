package com.xmniao.dao.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.seller.SellerBean;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：SellerDao
 * 
 * 类描述： 商家DAO层
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月13日 下午3:02:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface SellerDao {
	
	/**
	 * 
	 * @Title: getSellerInfo 
	 * @Description:根据商家编号，获取商家信息
	 */
	SellerBean getSellerInfo(Integer sellerid);
	
	/**
	 * 
	 * @Title: modifySellerInfo 
	 * @Description:更新商家信息
	 */
	int modifySellerInfo(SellerBean seller);
	
	/**
	 * 
	 * @Title: getSellerPicCount 
	 * @Description:获取商家是否上传了环境图
	 */
	int getSellerPicCount(Integer sellerid);
	
	/**
	 * 获取合作商信息
	 * @Title: getJointInfo 
	 * @Description:
	 */
	Map<String,Object> getJointInfo(Integer jointid);
	
	/**
	 * 获取商家区域信息
	 * @Title: getSellerArea 
	 * @Description:
	 */
	Map<String,String> getSellerArea(String areaId);
	
	/**
	 * 统计商家被收藏次数
	 * @Title: countSellerBySaved 
	 * @Description:
	 */
	long countSellerBySaved(Integer sellerid);
	
	/**
	 * 统计商家被收藏次数
	 * @Title: countSellerBySaved 
	 * @Description:
	 */
	List<Map<String,Object>> getSellerBySavedList(Map<String,Object> paraMap);
	
	/**
	 * 获取商家信息
	 * @Title: getSellerList 
	 * @Description:
	 */
	List<SellerBean> getSellerList(Map<String,Object> paraMap);
	
	/**
	 * 获取商家列表
	 * @Title: getBusinessList 
	 * @Description:
	 */
	List<Map<String,Object>> getBusinessList(Map<String,Object> paraMap);
	
	/**
	 * 获取分类列表
	 * @Title: getTradeList 
	 * @Description:
	 */
	List<Map<String,Object>> getTradeList(Map<String,Object> paraMap);	
	
	/**
	 * 获取商家账号手机号
	 * @Title: getSellerAccountPhone 
	 * @Description:
	 */
	List<String> getSellerAccountPhone(Integer sellerid);
	
	/**
	 * 
	 * 方法描述：查询没有商家详情信息的商家ID列表
	 * 创建人： ChenBo
	 * 创建时间：2017年2月20日
	 * @param map
	 * @return List<Integer>
	 */
	List<Integer> getSellerDetailNull(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：批量插入默认的商家详情记录信息
	 * 创建人： ChenBo
	 * 创建时间：2017年2月20日
	 * @param list
	 * @return int
	 */
	int insertSellerDetail(List<Integer> list);

	 /**
	 * 方法描述：修改商家状态
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月12日上午9:41:04 <br/>
	 * @param sellerid
	 * @param i
	 */
	int updateStatusBySellerId(@Param("sellerid")Integer sellerid, @Param("status")int status);
}
