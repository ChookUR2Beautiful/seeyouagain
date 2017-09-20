package com.xmniao.xmn.core.business_area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_area.dao.TstaffRankingDao;
import com.xmniao.xmn.core.business_area.entity.TstaffRanking;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TstaffRankingService
 * 
 * 类描述：员工排行
 * 
 * 创建人： zhou' dekun
 * 
 * 创建时间：2014年12月5日 下午11:56:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * 
 */
@Service
public class TstaffRankingService extends BaseService<TstaffRanking> {

	@Autowired
	private  TstaffRankingDao tstaffRankingDao;
	
	public  List<TstaffRanking>   getSignedInfo(TstaffRanking tstaffRanking){//签约商家
		
		return tstaffRankingDao.getSignedInfo(tstaffRanking);
	}
    public  Long   getSignedInfoCount(TstaffRanking tstaffRanking){
		
		return tstaffRankingDao.getSignedInfoCount(tstaffRanking);
	}
	
	public  List<TstaffRanking>   getInitSignIn(TstaffRanking tstaffRanking){//签到商家
		
		return tstaffRankingDao.getInitSignIn(tstaffRanking);
	}
    public  Long   getInitSignInCount(TstaffRanking tstaffRanking){
		
		return tstaffRankingDao.getInitSignInCount(tstaffRanking);
	}
    
	public  List<TstaffRanking>   getBusinesses(TstaffRanking tstaffRanking){//商家信息
		
		return tstaffRankingDao.getBusinesses(tstaffRanking);
	}
    public  Long   getBusinessesCount(TstaffRanking tstaffRanking){
		
		return tstaffRankingDao.getBusinessesCount(tstaffRanking);
	}
    public  List<TstaffRanking>   getExamineBusinesses(TstaffRanking tstaffRanking){//审核中商家信息
		
		return tstaffRankingDao.getExamineBusinesses(tstaffRanking);
	}
    public  Long   getExamineBusinessesCount(TstaffRanking tstaffRanking){
		
		return tstaffRankingDao.getExamineBusinessesCount(tstaffRanking);
	}
    public  List<TstaffRanking>   getNoPassBusinesses(TstaffRanking tstaffRanking){//未通过审核商家信息
		
		return tstaffRankingDao.getNoPassBusinesses(tstaffRanking);
	}
    public  Long   getNoPassBusinessesCount(TstaffRanking tstaffRanking){
		
		return tstaffRankingDao.getNoPassBusinessesCount(tstaffRanking);
	}
	@Override
	protected BaseDao getBaseDao() {
		return tstaffRankingDao;
	}
	
	
}
