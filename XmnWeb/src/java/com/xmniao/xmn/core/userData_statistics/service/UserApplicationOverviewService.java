package com.xmniao.xmn.core.userData_statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.userData_statistics.dao.UserApplicationOverviewDao;
import com.xmniao.xmn.core.userData_statistics.entity.TUserApplicationOverview;
import com.xmniao.xmn.core.userData_statistics.entity.UserDateRequestWhere;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.DateHelper;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：UserApplicationOverviewService
 * 
 * 类描述：用户统计 应用概括 业务处理类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-23 10:33:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class UserApplicationOverviewService extends AbstractServiceHandel<TUserApplicationOverview>{

	@Autowired UserApplicationOverviewDao userApplicationOverviewDao;
	
	
	private String url;
	private String requestUrl = "/user/queryUser.html";
	private String param;
	private UserDateRequestWhere userWhere;
	
	
	

	public UserApplicationOverviewService() {
		this.url = PropertiesUtil.readValue("http.user.url")+requestUrl;
		this.userWhere = new UserDateRequestWhere();
		this.userWhere.setStadate(DateHelper.getOtherDayByDay(2));
		this.userWhere.setEnddate(DateHelper.getOtherDayByDay(1));
		this.param = userWhere.getWhere();
	}
	
	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public String getParam() {
		return this.param;
	}
	
	@Override
	public Class<TUserApplicationOverview> getClzz() {
		return TUserApplicationOverview.class;
	}
	
	@Override
	protected void setValue(TUserApplicationOverview main,TUserApplicationOverview slave) {
		main.setTradingTotal(slave.getTradingTotal());
	}
	
	
	public List<TUserApplicationOverview> getList() throws Exception{
		//查询接口信息
		Reuqest();
		
		//本地订单交易量信息列表
		TUserApplicationOverview applicationOverview =new TUserApplicationOverview();
		applicationOverview.setStartCensusDate(DateHelper.getOtherDayByDay(2));
		applicationOverview.setEndCensusDate(DateHelper.getOtherDayByDay(1));
		List<TUserApplicationOverview> applicationOverviews =  userApplicationOverviewDao.getList(applicationOverview);
		//合并
		mergeList(getDatas(),applicationOverviews);
		return getDatas();
	}

	/**
	 * 实现父类方法 建立具体实体
	 */
	@Override
	protected TUserApplicationOverview createFillData() {
		return new TUserApplicationOverview();
	}
	

	
	



	
	

}
