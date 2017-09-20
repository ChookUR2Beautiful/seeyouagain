/**
 * 
 */
package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.FcouspointRecord;
import com.xmniao.xmn.core.businessman.entity.FreetryRecord;
import com.xmniao.xmn.core.businessman.entity.KillRecord;
import com.xmniao.xmn.core.businessman.entity.RoulleteRecord;
import com.xmniao.xmn.core.businessman.entity.SellerActivity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerActivity
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月9日 下午4:44:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface SellerActivityDao extends BaseDao<SellerActivity>{
	
	/**
	 * 
	 * 方法描述：轮盘详情列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:33:21 <br/>
	 * @param fRecord
	 * @return
	 */
	List<RoulleteRecord> roullRecordList(RoulleteRecord rouRecord);
	
	Long countRoullRecord(RoulleteRecord rouRecord);
	
	/**
	 * 
	 * 方法描述：免费尝新
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:33:21 <br/>
	 * @param fRecord
	 * @return
	 */
	List<FreetryRecord> freeRecordList(FreetryRecord fRecord);
	
	Long countFreeRecord(FreetryRecord record);
	
	
	/**
	 * 
	 * 方法描述：秒杀
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:33:21 <br/>
	 * @param fRecord
	 * @return
	 */
	List<KillRecord> killRecordList(KillRecord kRecord);
	
	Long countKillRecord(KillRecord kRecord);
	
	
	/**
	 * 
	 * 方法描述：集点
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午2:33:21 <br/>
	 * @param fRecord
	 * @return
	 */
	List<FcouspointRecord> focusRecordList(FcouspointRecord fRecord);
	
	Long countFocusRecord(FcouspointRecord fRecord);
	
	/**
	 * 
	 * 方法描述：终止进行中的活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月10日下午4:23:54 <br/>
	 * @param sActivity
	 * @return
	 */
	Integer shutdown(SellerActivity sActivity);
}
