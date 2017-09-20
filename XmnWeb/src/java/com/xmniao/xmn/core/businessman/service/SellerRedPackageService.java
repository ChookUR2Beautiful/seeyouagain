/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerRedPackageDao;
import com.xmniao.xmn.core.businessman.entity.GetRedPackageRecord;
import com.xmniao.xmn.core.businessman.entity.SellerRedPackage;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerRedPackageService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月7日 下午1:43:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class SellerRedPackageService extends BaseService<SellerRedPackage>{
	
	@Autowired
	private SellerRedPackageDao sellerRedPackageDao;
	
	@Override
	protected BaseDao<SellerRedPackage> getBaseDao() {
		return sellerRedPackageDao;
	}
	
	/**
	 * 获取商户发放红包列表
	 */
	public List<SellerRedPackage> getList(SellerRedPackage sPackage){
		List<SellerRedPackage> packages = new ArrayList<>();
		List<SellerRedPackage> list = sellerRedPackageDao.getList(sPackage);
		for (SellerRedPackage redPackage : list) {
			if( null !=redPackage.getStatus() && 1 != redPackage.getStatus()){//活动未开始或未支付
				Long total = sellerRedPackageDao.countTotalUser(redPackage.getId());//领取红包总会员数
				redPackage.setTotalVip(total);
				Long lock = sellerRedPackageDao.countLockUser(redPackage.getId());//领取红包绑定会员总数
				redPackage.setLockVip(lock);
			}
			packages.add(redPackage);
		}
		return packages;
	}
	
	/**
	 * 
	 * 方法描述：获取红包领取详情列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:52:31 <br/>
	 * @param gRecord
	 * @return
	 */
	public List<GetRedPackageRecord> getRecordList(GetRedPackageRecord gRecord){
		return sellerRedPackageDao.getRecordList(gRecord);
	}
	
	/**
	 * 
	 * 方法描述：统计总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日上午10:53:27 <br/>
	 * @param gRecord
	 * @return
	 */
	public Long countRecord(GetRedPackageRecord gRecord){
		return sellerRedPackageDao.countRecord(gRecord);
	}
	
	/**
	 * 
	 * 方法描述：终止进行中的红包活动
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日下午2:48:18 <br/>
	 * @param sPackage
	 * @return
	 */
	public Integer shutDownRedPackage(SellerRedPackage sPackage){
		return sellerRedPackageDao.shutDownRedPackage(sPackage);
	}
}
