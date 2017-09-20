package com.xmniao.xmn.core.xmermanagerment.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWallet;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BXmerWalletMapper
 * 
 * 类描述： 
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-8 下午5:25:13 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BXmerWalletDao extends BaseDao<BXmerWallet>{
	
    
    /**
     * 
     * 方法描述：根据id查询寻蜜客钱包
     * 创建人：  huang'tao
     * 创建时间：2016-9-8下午5:29:24
     * @param id
     * @return
     */
	@DataSource(value="pay")
    BXmerWallet selectById(Integer id);
    
	/**
	 * 
	 * 方法描述：根据寻蜜鸟会员ID数据查询寻蜜客钱包列表
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午5:41:12
	 * @param uids
	 * @return
	 */
	@DataSource(value="pay")
    List<BXmerWallet> getListByUids(Object[] uids);
    
}