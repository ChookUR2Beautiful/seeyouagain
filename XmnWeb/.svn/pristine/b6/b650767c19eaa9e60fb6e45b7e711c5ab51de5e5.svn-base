package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSpread;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerSubsidyDao
 * 
 * @类描述： 商家(商户)
 * 
 * @创建人：cao'yingde
 * 
 * @创建时间：2015年06月11日15时22分21秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SpreadDao extends BaseDao<TSpread> {
	
	/**
	 * @Title:getListTospread
	 * @Description:获取商家列表(只查询已推广配置的商家)getListTospread
	 * @param seller
	 * @return List<TSeller>
	 * @throw
	 */
	@DataSource("slave")
	public List<TSeller> getListTospread(TSeller seller);
	@DataSource("slave")
	public Long countTospread(TSeller seller);
	/**
	 * @Title:getListToConfig
	 * @Description:获取商家列表（已上线已签约的商家并且未配置）
	 * @param seller
	 * @return List<TSeller>
	 * @throw
	 */
	@DataSource("slave")
	public List<TSeller> getListToConfig(TSeller seller);
	@DataSource("slave")
	public Long countToConfig(TSeller seller);
	
	/**
	 * @Title:vailStaff
	 * @Description:验证业务员账号是否存在
	 * @return Integer
	 * @throw
	 */
	@DataSource("slave")
	public Integer vailStaff(Long phoneid);
	
	/**
	 * 获取服务员推广账号的详情
	 */
	@DataSource("slave")
	public List<TSpread> getListDeail(TSpread tSpread);
	
	/**
	 * 根据商家帐号id删除商家服务员推广管理信息
	 */
	@DataSource("slave")
	public Integer deleteSpreadByAids(Object[] objects);
	
}
