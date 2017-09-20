package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSellerInternalInfo;

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
public interface SellerInternalInfoDao extends BaseDao<TSellerInternalInfo> {
	
	/**
	 * 批量关联商家
	 * @param list
	 */
	public void beachRelationSeller(List<TSellerInternalInfo> list);
	
	/**
	 * 根据消息id删除对应的关联关系
	 */
	public int deleteRelation(Long msgId);

}
