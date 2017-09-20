package com.xmniao.xmn.core.businessman.dao;


import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerAccountDao
 * 
 * @类描述： 商家账号
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时42分53秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerAccountDao extends BaseDao<TSellerAccount>{
	/**
	 * 查询账号列表信息
	 * @return
	 */
	public List<TSellerAccount> getAccountList(TSellerAccount sellerAccount);
	/**
	 * 根据商家id删除账号信息
	 * @param objects
	 * @return
	 */
	public int deleteAccountInfoBySellerid (Object[] objects);
	
	public long getAcount(String account);
	
	
	/**
	 * 根据sellerid数组获取该商家的管理员账户
	 * @param sellerids
	 * @return
	 */
	public List<TSellerAccount> getAdminsBySeller(String[] sellerids);
	
	/**
	 * 校验连锁店帐号唯一性
	 * @param account
	 * @return
	 */
	public long checkMultipShopAccount(String account);
	
	/**
	 * 
	 * 方法描述：获取商家主账号
	 * 创建人： chenJie
	 * 创建时间：2016年9月5日上午10:58:50
	 * @param sellerId
	 * @return
	 */
	public Map<String,Object> getMainAccount(String sellerid);
}
