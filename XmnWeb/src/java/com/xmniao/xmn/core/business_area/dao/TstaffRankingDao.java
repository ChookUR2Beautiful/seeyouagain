package com.xmniao.xmn.core.business_area.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_area.entity.BusinessArea;
import com.xmniao.xmn.core.business_area.entity.TstaffRanking;


/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TstaffRankingDao
 * 
 * @类描述：员工排行
 * 
 * @创建人：zhou'dekun
 * 
 * @创建时间：2014年12月5日 下午11:56:43
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface TstaffRankingDao extends BaseDao<TstaffRanking>{
	
	public  List<TstaffRanking>   getSignedInfo(TstaffRanking tstaffRanking);//签约数信息
	
	public  Long   getSignedInfoCount(TstaffRanking tstaffRanking);//签约数 条数
	
    public  List<TstaffRanking>   getInitSignIn(TstaffRanking tstaffRanking);//签到数信息
	
	public  Long   getInitSignInCount(TstaffRanking tstaffRanking);//签到数 条数
	
    public  List<TstaffRanking>   getBusinesses(TstaffRanking tstaffRanking);//商家信息
	
	public  Long   getBusinessesCount(TstaffRanking tstaffRanking);//商家条数
	
    public  List<TstaffRanking>   getExamineBusinesses(TstaffRanking tstaffRanking);//审核中商家信息
	
	public  Long   getExamineBusinessesCount(TstaffRanking tstaffRanking);//审核中商家条数
	
    public  List<TstaffRanking>   getNoPassBusinesses(TstaffRanking tstaffRanking);//未通过商家信息
	
	public  Long   getNoPassBusinessesCount(TstaffRanking tstaffRanking);//未通过商家条数
	
}
