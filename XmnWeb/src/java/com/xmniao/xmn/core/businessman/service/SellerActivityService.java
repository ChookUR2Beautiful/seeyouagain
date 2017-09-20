/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerActivityDao;
import com.xmniao.xmn.core.businessman.entity.FcouspointRecord;
import com.xmniao.xmn.core.businessman.entity.FreetryRecord;
import com.xmniao.xmn.core.businessman.entity.KillRecord;
import com.xmniao.xmn.core.businessman.entity.RoulleteRecord;
import com.xmniao.xmn.core.businessman.entity.SellerActivity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerActivityService
 * 
 * 类描述： 商家活动
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月9日 下午5:05:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class SellerActivityService extends BaseService<SellerActivity>{
	
	@Autowired
	private SellerActivityDao sActivityDao;
	
	@Override
	protected BaseDao<SellerActivity> getBaseDao() {
		return sActivityDao;
	}
	
	/**
	 * 
	 * 方法描述：免费尝新
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:34:21 <br/>
	 * @param fRecord
	 * @return
	 */
	public List<FreetryRecord> freeRecordList(FreetryRecord fRecord){
		return sActivityDao.freeRecordList(fRecord);
	}
	
	public Long countFreeRecord(FreetryRecord record){
		return sActivityDao.countFreeRecord(record);
	}
	
	
	/**
	 * 
	 * 方法描述：轮盘详情列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:33:21 <br/>
	 * @param fRecord
	 * @return
	 */
	public List<RoulleteRecord> roullRecordList(RoulleteRecord rouRecord){
		return sActivityDao.roullRecordList(rouRecord);
	}
	
	public Long countRoullRecord(RoulleteRecord rouRecord){
		return sActivityDao.countRoullRecord(rouRecord);
	}
	
	/**
	 * 
	 * 方法描述：秒杀
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:33:21 <br/>
	 * @param fRecord
	 * @return
	 */
	public List<KillRecord> killRecordList(KillRecord kRecord){
		return sActivityDao.killRecordList(kRecord);
	}
	
	public Long countKillRecord(KillRecord kRecord){
		return sActivityDao.countKillRecord(kRecord);
	}
	
	/**
	 * 
	 * 方法描述：集点
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:33:21 <br/>
	 * @param fRecord
	 * @return
	 */
	public List<FcouspointRecord> focusRecordList(FcouspointRecord fRecord){
		return sActivityDao.focusRecordList(fRecord);
	}
	
	public Long countFocusRecord(FcouspointRecord fRecord){
		return sActivityDao.countFocusRecord(fRecord);
	}
	/**
	 * 
	 * 方法描述：终止进行中的活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午4:24:56 <br/>
	 * @param sActivity
	 * @return
	 */
	public Integer shutdown(SellerActivity sActivity){
		return sActivityDao.shutdown(sActivity);
	}
}
