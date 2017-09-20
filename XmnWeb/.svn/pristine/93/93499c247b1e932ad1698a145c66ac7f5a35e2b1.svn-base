package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.SellerDetailedDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerDetailed;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerDetailedService
 * 
 * 类描述： 商家详情
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时46分32秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SellerDetailedService extends BaseService<TSellerDetailed> {

	@Autowired
	private SellerDetailedDao sellerDetailedDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return sellerDetailedDao;
	}

	/**
	 * 获取批量更新商家是否开启首次的初始化信息
	 * @param sellerDetailed
	 * @param modelAndView
	 * @author Zhang'zhiwen
	 */
	public void getBatchUpdateIsFirstInfo(TSellerDetailed sellerDetailed,ModelAndView modelAndView) {
		TSeller seller = new TSeller();
		seller.setArray(sellerDetailed.getSellerIds().split(","));
		List<TSeller> sellers = sellerDao.getSellerAndSellerDetailed(seller);
		modelAndView.addObject("sellers", sellers);
	}
	
	/**
	 * 批量更新商家是否开启首次
	 * 
	 * @param seller
	 * 
	 * @author Zhang'zhiwen
	 */
	public void batchUpdateIsFirst(TSeller seller){
		for(TSeller tempSeller:seller.getSellerList()){
			try{
				TSellerDetailed sellerDetailed = new TSellerDetailed();
				sellerDetailed.setSellerid(tempSeller.getSellerid());
				sellerDetailed.setIsFirst(tempSeller.getIsFirst());
				sellerDetailedDao.update(sellerDetailed);
				String[] s={"商家编号",tempSeller.getSellerid().toString(),"更新是否开启首次","更新是否开启首次"};
				this.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
			}catch(Exception e){
				log.error("更新异常",e);
				String[] s={"商家编号",tempSeller.getSellerid().toString(),"更新是否开启首次","更新是否开启首次"};
				this.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
				throw e;
			}
		}
	}
	public TSellerDetailed getSellerDetailed(Long sellerid){
		
		return sellerDetailedDao.getSellerDetailed(sellerid);
	}
}
