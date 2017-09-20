/**
 * 
 */
package com.xmniao.xmn.core.user_terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.user_terminal.dao.DealedAbnormalSellerDao;
import com.xmniao.xmn.core.user_terminal.entity.AbnormalSeller;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：DealedAbnormalService
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： chenjie
 * 
 * 创建时间：2016年8月6日上午11:23:01
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class DealedAbnormalService extends BaseService<AbnormalSeller>{

	@Autowired
	private DealedAbnormalSellerDao adSellerDao;
	@Override
	protected BaseDao getBaseDao() {
		return adSellerDao;
	}
	
	/**
	 * 
	 * 方法描述：查询已处理举报商家列表
	 * 创建人： huang'tao
	 * 创建时间：2016年8月5日下午3:12:12
	 * @param abnormalSeller
	 * @return
	 */
	public List<AbnormalSeller> getAbnormalSellerList(AbnormalSeller abnormalSeller){
		return adSellerDao.getAbnormalSellerList(abnormalSeller);
	}
	
	/**
	 * 
	 * 方法描述：查询符合条件的记录总数
	 * 创建人： huang'tao
	 * 创建时间：2016年8月5日下午3:13:30
	 * @param abnormalSeller
	 * @return
	 */
	public long getCount(AbnormalSeller abnormalSeller){
		return adSellerDao.getAbnormalSellerCount(abnormalSeller);
	}
	
	
	/**
	 * 
	 * 方法描述：根据id删除举报商家
	 * 创建人：chenjie
	 * 创建时间：2016年8月6日上午9:36:25
	 * @param id
	 */
	public void deleteInfoById(String id) throws Exception{
		adSellerDao.deleteInfoById(id);
	}
}
