package com.xmn.saas.dao.shop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.shop.Business;
import com.xmn.saas.entity.shop.SellerApply;
import com.xmn.saas.entity.shop.SellerDetailed;
import com.xmn.saas.entity.shop.SellerInfo;
import com.xmn.saas.entity.shop.SellerLandMark;
import com.xmn.saas.entity.shop.SellerPic;


@Repository
public interface SellerInfoDao {
	
	/**
	 * 
	 * @Description: 根据sellerid查询商家基本信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	SellerInfo querySellerBySellerId(Integer sellerid);
	
	/**
	 * 
	 * @Description: 根据sellerid查询店铺详细信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	SellerDetailed querySellerDetailedBySellerid(Integer sellerid);
	/**
	 * 
	 * @Description: 根据sellerid查询店铺坐标信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	SellerLandMark queryLandMarkBySellerid(Integer sellerid);
	
	/**
	 * 
	 * @Description: 查询圈名称
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	Business queryBusinessBySellerid(Integer zoneId);
	
	
	/**
	 * 
	 * @Description: 修改店铺基本资料
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	void saveSeller(SellerInfo sellerInfo);
	
	/**
	 * 
	 * @Description: 查询店铺详细资料
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	void saveSellerDetailed(SellerDetailed sellerDetailed);
	
	/**
	 * 
	 * @Description: 修改店铺坐标信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	void saveSellerLandMark(SellerLandMark sellerLandMark);
	
	
	/**
	 * 
	 * @Description: 商家修改资料提交申请
	 * @author xiaoxiong
	 * @date 2016年9月27日
	 * @version
	 */
	void insertSellerApply(SellerApply sellerApply);
	
	/**
	 * 
	 * @Description: 查询待审核的数量
	 * @author xiaoxiong
	 * @date 2016年9月27日
	 * @version
	 */
	int querySellerApplyCount(@Param("type") int type,@Param("sellerid") Integer sellerid);
	
	/**
	 * 查询待审核数据
	 * @Description: 
	 * @author xiaoxiong
	 * @date 2016年9月27日
	 * @version
	 */
	SellerApply querySellerApply(@Param("type") int type,@Param("sellerid") Integer sellerid);
	
	/**
	 * 
	 * @Description: 查询商家图片列表
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	List<SellerPic> querySellerPicBySellerId(@Param("sellerid")Integer sellerid,@Param("fileUrl")String fileUrl,@Param("type") Integer type);
	
	/**
	 * 
	 * @Description: 根据记录ID查询审核表中的图片
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	List<SellerPic> querySellerPicApplyByid(@Param("id")Integer id,@Param("fileUrl")String fileUrl);
	
	/**
	 * 
	 * @Description:添加审核图片
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	void inserSellerPicApply(SellerPic sellerPic);
	
	/**
	 * 
	 * @Description: 根据经纬度查询离商家最近的商圈
	 * @author xiaoxiong
	 * @date 2016年10月11日
	 */
	Business queryBusinessBylongAndlat(@Param("longitude")Double longitude,@Param("latitude")Double latitude);
	
	/**
	 * 
	 * @Description: 删除图片
	 * @author xiaoxiong
	 * @date 2016年10月24日
	 */
	int deleteSellerPic(Map<String,Object> map);
	
	/**
	 * 
	 * @Description: 删除审核中的图片
	 * @author xiaoxiong
	 * @date 2016年10月24日
	 */
	int deleteSellerPicApply(@Param("sellerid")Integer sellerid,@Param("url")String url);



}
