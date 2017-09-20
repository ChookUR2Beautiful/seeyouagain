/**
 * 
 */
package com.xmniao.xmn.core.xmermanagerment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.xmermanagerment.dao.BXmerWalletDao;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWallet;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BXmerWalletService
 * 
 * 类描述： BXmerWallet
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-8 下午5:43:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class BXmerWalletService extends BaseService<BXmerWallet> {
	
	/**
	 * 注入寻蜜客钱包服务
	 */
	@Autowired
	private BXmerWalletDao xmerWalletDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return xmerWalletDao;
	}
	
    public BXmerWallet selectById(Integer id){
    	return xmerWalletDao.selectById(id);
    }
    
	/**
	 * 
	 * 方法描述：根据寻蜜鸟会员ID数据查询寻蜜客钱包列表
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午5:41:12
	 * @param uids
	 * @return
	 */
    public List<BXmerWallet> getListByUids(Object[] uids){
    	return xmerWalletDao.getListByUids(uids);
    }

}
