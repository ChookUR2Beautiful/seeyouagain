package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSellerApply;
import com.xmniao.xmn.core.businessman.entity.TSellerPicApply;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerApplyDao
 * 
 * @类描述： 商户信息修改申请
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时50分01秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerApplyDao extends BaseDao<TSellerApply> {
	/**
	 * 根据商家id查询商家待修改信息
	 * 
	 * @param sellerid
	 * @return
	 */
	@DataSource("slave")
	public TSellerApply findSellerApplyById(TSellerApply sellerApply);
	
	/**
	 * 根据商家id删除商家申请修改信息
	 * @param objects
	 * @return
	 */
	public int deleteApplyBySellerid (Object[] objects);
	
	/**
	 * 批量跟新状态
	 */
	public Integer updateApplyStatus (TSellerApply sellerApply);
	/**
	 * 查询申请
	 * @param sellerApply
	 * @return
	 */
	@DataSource("slave")
	public List<TSellerApply> getsellerApplyList(TSellerApply sellerApply);
	/**
	 * @Title:getPicApplyList
	 * @Description:查询商家修改图片
	 * @param sellerApply
	 * @return List<TSellerPicApply>
	 * @throw
	 */
	@DataSource("slave")
	public List<TSellerPicApply> getPicApplyList(TSellerPicApply tp);
	
}
