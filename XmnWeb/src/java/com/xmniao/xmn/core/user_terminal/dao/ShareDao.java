package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.TShare;
import com.xmniao.xmn.core.user_terminal.entity.TShareRange;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：ShareDao
 * 
 * @类描述：分享信息
 * 
 * @创建人：cao'yingde
 * 
 * @创建时间：2015年06月26日20时07分10秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface ShareDao extends BaseDao<TShare> {
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void addTShareRange(TShareRange temp);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateTShareRange(TShareRange temp);
	
	
	List<TShareRange> getListByCid(Long sid);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAllBySid(Long sid);
	
	List<TShare> getListBySellerIdAndJoinSellerName(Long Sellerid);
}
