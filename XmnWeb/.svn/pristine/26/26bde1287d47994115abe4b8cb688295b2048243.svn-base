package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.MSellerPic;
import com.xmniao.xmn.core.businessman.entity.TSellerPic;
import com.xmniao.xmn.core.businessman.entity.TSellerPicApply;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerPicDao
 * 
 * @类描述： 商家环境图片
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时56分48秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerPicDao extends BaseDao<TSellerPic> {

	/**
	 * 取得商家的logo
	 * 
	 * @param sellerId
	 * @return
	 */
	public TSellerPic getSellerLogo(Long sellerId);

	/**
	 * mongodb 商家logo对象信息
	 * 
	 * @param sellerId
	 * @return
	 */
	MSellerPic getMSellerLogo(Long sellerId);

	/**
	 * mongodb 商家封面图对象信息
	 * 
	 * @param sellerId
	 * @return
	 */
	MSellerPic getMSellerCover(Long sellerId);
	
	/**
	 * mongodb 商家环境对象信息
	 * 
	 * @param sellerId
	 * @return
	 */
	List<MSellerPic> getMSellerPics(Long sellerId);

	/**
	 * 取得商家图片.
	 * 
	 * @param sellerid
	 * @return
	 */
	public TSellerPic getSellerPic(Long sellerid);

	/**
	 * 删除不存在的环境图片图片
	 * 
	 * @param existSellerPicIds
	 * @param sellerId
	 */
	public void deleteEnviByPicidNotIn(TSellerPic picIdsAndSellerId);
	
	/**
	 * @Title:batchUpdate
	 * @Description:批量更新环境图片
	 * @param foodList void
	 * @throw
	 */
	void batchUpdate(List<TSellerPic> sellerPicList);
	/**
	 * @Title:batchAdd
	 * @Description:批量添加环境图片
	 * @param sellerPicList Integer
	 * @throw
	 */
	Integer batchAdd(List<TSellerPicApply> sellerPicList);
	/**
	 * @Title:deleteSellerPic
	 * @Description:删除某个商家的环境图
	 * @param sellerid
	 * @return int
	 * @throw
	 */
	public int deleteSellerPic (@Param("sellerid")Integer sellerid,@Param("types")String types);
}
