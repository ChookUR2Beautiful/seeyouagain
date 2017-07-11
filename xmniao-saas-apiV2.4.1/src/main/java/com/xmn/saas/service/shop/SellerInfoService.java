package com.xmn.saas.service.shop;

import com.xmn.saas.entity.shop.*;

import java.util.List;

/**
 * 
*      
* 类名称：SellerInfoService   
* 类描述：   商家资料
* 创建人：xiaoxiong   
* 创建时间：2016年9月24日 下午5:10:10   
* 修改人：xiaoxiong   
* 修改时间：2016年9月24日 下午5:10:10   
* 修改备注：   
* @version    
 */
public interface SellerInfoService {
	
	/**
	 * 
	 * @Description: 根据sellerid查询店铺基本信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	SellerInfo querySellerBySellerid(Integer sellerid);
	
	/**
	 * 
	 * @Description: 根据sellerid查询店铺详细信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	SellerDetailed querySellerDetailedBySellerId(Integer sellerid);
	
	/**
	 * 
	 * @Description: 查询商家坐标信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	SellerLandMark querySellerLandMarkBySellerid(Integer sellerid);
	/**
	 * 
	 * @Description: 查询商家的商圈信息
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	Business queryBusinessBySellerid(Integer sellerid);
	
	/**
	 * 
	 * @Description: 修改店铺资料
	 * @author xiaoxiong
	 * @date 2016年9月26日
	 * @version
	 */
	void save(SellerInfo sellerInfo);
	
	/**
	 * 
	 * @Description: 添加商家修改申请
	 * @author xiaoxiong
	 * @date 2016年9月27日
	 * @version
	 */
	void insertSellerApply(SellerApply sellerApply);
	
	/**
	 * 
	 * @Descriptio 查询是否有待审核的审核记录
	 * @author xiaoxiong
	 * @date 2016年9月27日
	 * @version
	 */
	int querySellerApplyCount(int type,Integer sellerid);
	
	/**
	 * 
	 * @Description: 查询审核表基础数据
	 * @author xiaoxiong
	 * @date 2016年9月27日
	 * @version
	 */
	SellerApply querySellerApplyBySellerid(int type, Integer sellerid);
	
	/**
	 * 
	 * @Description: 查询商家图片信息
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	List<SellerPic> querySellerPicBySellerId(Integer sellerid,Integer type);
	
	/**
	 * 
	 * @Description: 根据申请记录ID查询申请审核中的图片
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	List<SellerPic> querySellerPicApplyByid(Integer id);
	
	/**
	 * 
	 * @Description: 添加环境审核图片信息
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	void insertSellerApply(List<SellerPic> list,Integer sellerid);
	
	/**
	 * 
	 * @Description: 查询离商家最近的商圈
	 * @author xiaoxiong
	 * @date 2016年10月11日
	 */
	Business queryBusinessBylongAndlat(Double longitude, Double latitude);

	/**
	 * 
	 * @Description: 获取商家账户信息
	 * @author xiaoxiong
	 * @date 2016年10月12日
	 */
	Account queryAccount(int sellerid, int aid);
	
	/**
	 * 
	 * @Description: 删除图片
	 * @author xiaoxiong
	 * @date 2016年10月24日
	 */
	int deleteSellerPic(Integer sellerid,String url);
	
	/**
	 * 
	 * @Description: 删除审核中的图片
	 * @author xiaoxiong
	 * @date 2016年10月25日
	 */
	int deleteSellerPicApply(Integer sellerid, String url);


    List<LiveClassify> querySellerTag();

    List<LiveClassifyTag> queryTags();
}
