/**
 * 
 */
package com.xmniao.xmn.core.xmermanagerment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.xmermanagerment.dao.BXmerWalletRecordDao;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWalletRecord;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BXmerWalletRecordService
 * 
 * 类描述： 寻蜜客钱包交易记录服务
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-9 下午2:30:54 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class BXmerWalletRecordService extends BaseService<BXmerWalletRecord> {

	@Autowired
	private BXmerWalletRecordDao xmerWalletRecordDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return xmerWalletRecordDao;
	}
	
	/**
	 * 
	 * 方法描述：根据ID获取寻蜜客钱包记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午6:26:42
	 * @param id
	 * @return
	 */
    public BXmerWalletRecord selectById(Integer id){
		return xmerWalletRecordDao.selectById(id);
	}
    
    
    /**
	 * 
	 * 方法描述：根据寻蜜客钱包ID获取寻蜜客钱包记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午6:26:42
	 * @param id
	 * @return
	 */
    public List<BXmerWalletRecord> getList(BXmerWalletRecord xmerWalletRecord){
    	return xmerWalletRecordDao.getList(xmerWalletRecord);
    }
    
    
    /**
   	 * 
   	 * 方法描述：统计寻蜜客钱包记录数
   	 * 创建人：  huang'tao
   	 * 创建时间：2016-9-8下午6:26:42
   	 * @param id
   	 * @return
   	 */
    public Long count(BXmerWalletRecord xmerWalletRecord){
    	return xmerWalletRecordDao.count(xmerWalletRecord);
    }
	
	/**
	 * 
	 * 方法描述：根据寻蜜客钱包ID获取寻蜜客钱包记录列表
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午6:27:35
	 * @param xids
	 * @return
	 */
    public List<BXmerWalletRecord> getListByXids(Object[] xids){
		return xmerWalletRecordDao.getListByXids(xids);
	}

}
