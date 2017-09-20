/**
 * 
 */
package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.GetRedPackageRecord;
import com.xmniao.xmn.core.businessman.entity.SellerRedPackage;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerRedPackageDao
 * 
 * 类描述： 商家红包
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月7日 上午11:28:45 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface SellerRedPackageDao extends BaseDao<SellerRedPackage>{
	
	/**
	 * 
	 * 方法描述：统计领取红包会员总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日上午11:56:24 <br/>
	 * @param sellerid
	 * @return
	 */
	Long countTotalUser(Long sellerid);
	
	/**
	 * 
	 * 方法描述：统计领取红包绑定会员总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月7日上午11:56:51 <br/>
	 * @param sellerid
	 * @return
	 */
	Long countLockUser(Long sellerid);
	
	/**
	 * 
	 * 方法描述：获取红包领取详细列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:27:17 <br/>
	 * @param sPackage
	 * @return
	 */
	List<GetRedPackageRecord> getRecordList(GetRedPackageRecord gRecord);
	
	/**
	 * 
	 * 方法描述：获取领取红包记录总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:49:56 <br/>
	 * @param gRecord
	 * @return
	 */
	Long countRecord(GetRedPackageRecord gRecord);
	
	/**
	 * 
	 * 方法描述：终止进行中的红包活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日下午2:49:03 <br/>
	 * @param sPackage
	 * @return
	 */
	Integer shutDownRedPackage(SellerRedPackage sPackage);
 }
