package com.xmniao.xmn.core.userData_statistics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.userData_statistics.dao.MerberActionDao;
import com.xmniao.xmn.core.userData_statistics.entity.PUserDataContainer;
import com.xmniao.xmn.core.userData_statistics.entity.TMerberAction;
import com.xmniao.xmn.core.userData_statistics.entity.UserDateRequestWhere;
import com.xmniao.xmn.core.util.PropertiesUtil;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：MerberActionService
 * 
 * 类描述：用户统计 会员业务处理类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-23 10:33:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class MerberActionService extends AbstractServiceHandel<TMerberAction>{
	@Autowired MerberActionDao merberActionDao;
	
	Logger log = Logger.getLogger(getClass());
	
	private String url;
	private String requestUrl = "/user/queryUser.html";
	
	public MerberActionService() {
		this.url = PropertiesUtil.readValue("http.user.url")+requestUrl;
	}

	@Override
	public Class<TMerberAction> getClzz() {
		return TMerberAction.class;
	}

	@Override
	public String getUrl() {
		return this.url;
	}


	@Override
	protected void setValue(TMerberAction main, TMerberAction slave) {
		main.setTradingTotal(slave.getTradingTotal());
		main.setTradingVolume(slave.getTradingVolume());
	}
	
	public Map<String, Object> getList(TMerberAction merberAction){
		Map<String, Object> map = new HashMap<String, Object>(4);
		setRequestParam(merberAction);
		//请求接口数据
		try{
			Reuqest();
		} catch(Exception e){
			this.log.error("接口调用异常", e);
		}
		
		
		TMerberAction total =setTotal(merberAction);
		//获取本地数据
		List<TMerberAction> merberActions = merberActionDao.getList(merberAction);
		mergeList(getDatas(), merberActions);
		map.put("page", merberAction.getPage());
		//列表
		map.put("list", getDatas());
		//总计
		map.put("merberAction", total);
		//总条数
		map.put("total", getResponseData().getCount());
		return map;
	}
	/**
	 * 查询总计
	 * @param merberAction
	 */
	private TMerberAction setTotal(TMerberAction merberAction){
		TMerberAction total =merberActionDao.getCensusTotal(merberAction);
		if(null==total){
			total = new TMerberAction();
		}
		return setMerberActionValue(total);
	}
	/**
	 * 合并总计
	 * @param total
	 */
	private TMerberAction setMerberActionValue(TMerberAction total){
		PUserDataContainer<TMerberAction>.ResponseData<TMerberAction> r=getResponseData();
		total.setAdd_user(r.getAdd_users());
		total.setStart_no(r.getStart_nos());
		total.setUv(r.getUvs());
		total.setLogin_user(r.getLogin_users());
		//total.setTotal_user(r.get);
		return total;
	}
	
	/**
	 * 根据页面请求参数 拼装请求接口参数
	 * @param action
	 */
	private void setRequestParam(TMerberAction action){
		UserDateRequestWhere userWhere = new UserDateRequestWhere();
		userWhere.setStadate(action.getStartCensusDate());
		userWhere.setEnddate(action.getEndCensusDate());
		userWhere.setPage(action.getPage());
		userWhere.setPagesLength(action.getLimit());
		userWhere.setOrder(action.getOrder());
		setParam(userWhere.getWhere());
	}

	@Override
	protected TMerberAction createFillData() {
		return new TMerberAction();
	}
	
}
