package com.xmniao.xmn.core.marketingmanagement.service;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.SellerAgioDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.SellerMarketingDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAgio;
import com.xmniao.xmn.core.businessman.entity.TSellerMarketing;
import com.xmniao.xmn.core.businessman.service.SellerMarketingService;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.marketingmanagement.dao.TActivityDao;
import com.xmniao.xmn.core.marketingmanagement.dao.TActivityManagermentDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;
import com.xmniao.xmn.core.marketingmanagement.entity.TGiveMoneyInfo;
import com.xmniao.xmn.core.marketingmanagement.entity.TIntegralRule;
import com.xmniao.xmn.core.util.NumberUtil;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.StringUtils;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TActivityService
 * 
 * 类描述：营销活动管理
 * 
 * 创建人： yang'xu
 * 
 * 创建时间：2015年01月14日10时15分24秒
 * 
 * Copyright©广东寻蜜鸟网络技术有限公司
 */
@Service
public class TActivityService extends BaseService<TActivity> {
	
	@Autowired
	public TActivityDao activityDao;
	@Autowired
	private SellerMarketingDao sellerMarketingDao;
	@Autowired
	private SellerMarketingService sellerMarketingService;
	
	@Autowired TActivityManagermentDao activityManagermentDao;
	@Autowired
	private TActivityRuleService tActivityRuleService;
	
	@Autowired
	private SellerService sellerService; 
	
	@Autowired
	private SellerAgioDao sellerAgioDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return activityDao;
	}
	@Autowired
	private SellerDao sellerDao;
	/**
	 * 获取商家列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public boolean getType(TActivity tActivity) {
		// 查询在同一城市类关键字是否已经存在
		Long exist = activityDao.getType(tActivity);
		if (exist > 0) {
			return false;
		}
		return true;
	}
	
	public boolean getAid(TActivity tActivity) {
		String aname = activityDao.getAid(tActivity);
		Long exist = activityDao.getType(tActivity);
		if (!tActivity.getType().equals(aname)) {
			if (exist > 0) {
				return false;
			}
		}else if(exist > 1){
			return false;
		}
		return true;
	}
	
	public TActivity getObjectType(Long type){
		 return activityDao.getObjectType(type);
	}
	
	/**
	 * 商家上线时 查询指定活动 关联商家 
	 * @return
	 */
	public Integer getSpecifiedActivity(){
		return activityDao.getSpecifiedActivity();
	}	
	
	/**
	 * @TODO 活动管理，添加商户逻辑
	 * @param sellerMarketing
	 * @param resultable
	 * 
	 * Refactoring by wangzhimin 2015/08/20
	 */
	public void addSellerMarkerings(TSellerMarketing sellerMarketing,Resultable resultable) {
		
		StringBuffer sb=new StringBuffer();
		String sellerids = sellerMarketing.getSellerids();
		String[] ids = parseSellerIds(sellerids);
		
		if(null != ids){
			
			Integer aid = sellerMarketing.getAid();  //活动ID号，用于获取属于该活动的所有活动规则和活动时间
			
			List<TSellerMarketing> ListtSellerMarketings = new ArrayList<TSellerMarketing>();
			List<TSeller> ListSellers = new ArrayList<TSeller>();
			List<TSellerAgio> ListTSellerAgios=new ArrayList<TSellerAgio>();
			Map<String,Date> mapdate=new HashMap<String,Date>();  //保存拼装好的时间，最终时间格式为：yyyy-MM-dd HH:mm:ss
			
			Integer id = null;  //商家ID
			List<TActivityRule> tActivityRules=null;
			
			this.putMapdate(mapdate,Long.parseLong(String.valueOf(aid)));//活动时间拼装
			
			String doType = sellerMarketing.getDoType();
			
			for(String sellerid : ids){ //遍历商家id
				id = Integer.parseInt(sellerid);
				
				Long  count = sellerMarketingService.getActivtyListCount(this.getTSellerMarketing(sellerMarketing, mapdate, id, null));// 查询是不是有重复设置活动
				
				tActivityRules=activityManagermentDao.getActivityRuleList(Long.parseLong(String.valueOf(aid)));//折扣补贴设置
				TActivityRule tActivityRule=null;
				double giveMoney=0;
			    if(null==tActivityRules|tActivityRules.size()>0){
			    	tActivityRule = tActivityRules.get(0);
					giveMoney = Double.parseDouble(tActivityRule.getGiveMoney());
			    }
				if(count<1){ //所选商家在同一活动类型下不重复
					ListtSellerMarketings.add(this.getTSellerMarketing(sellerMarketing, mapdate, id, count));
					if(null != doType&&(!"youhuiquan".equals(doType))){
						int tActivityRulesSize = tActivityRules.size();
						if("discount".equals(doType) && 1 == tActivityRulesSize){  //折扣补贴活动需要同步seller表中的折扣
							ListTSellerAgios.add(this.getTSellerAgio(tActivityRule, giveMoney, id, doType));
						}else if("commission".equals(doType) && 1 == tActivityRulesSize){  //佣金补贴活动需要同步seller表中的佣金补贴比例
							ListTSellerAgios.add(this.getTSellerAgio(tActivityRule, giveMoney, id, doType));
						}
					}
					ListSellers.add(this.getTSeller(count, id, doType, giveMoney));
				}else{
					sb.append(this.getTSeller(count, id, doType, giveMoney).getSellername()+"、");
				}
			}
			if(ListtSellerMarketings.size() > 0){
				doAddOrUpdate(ListtSellerMarketings, ListSellers, ListTSellerAgios);
    			doFireLoginEvent(sellerids);    			
    			this.getResultable(resultable, true, true, null);  //活动与商家关联成功(同一类型下的活动所选商家没有重复)
            }else{  
    			this.getResultable(resultable, true, false, sb);  //活动与商家关联失败(同一类型下的活动所选商家重复)
            }
		}else{
			this.getResultable(resultable, false, false, null);  //没有选择商家
		}
	}
	
	/**
	 * 解析商家ID为字符串数组返回
	 * @param sellerMarketing
	 * @return
	 */
	private String[] parseSellerIds(String sellerIds){
		return StringUtils.paresToArray(sellerIds, ",");
	}

	/**
	 * 活动与商家关联成功，写入日志信息
	 * @param sellerids
	 */
	private void doFireLoginEvent(String sellerids) {
		String[]sellerMarketingInfo = {"商家编号",sellerids,"添加活动","添加"}; 
		fireLoginEvent(sellerMarketingInfo,1);
	}

	/**
	 * 进行批量插入和更新操作
	 * @param ListtSellerMarketings
	 * @param ListSellers
	 * @param ListTSellerAgios
	 * @author wangzhimin
	 */
	private void doAddOrUpdate(List<TSellerMarketing> ListtSellerMarketings,
			List<TSeller> ListSellers, List<TSellerAgio> ListTSellerAgios) {
		
		sellerMarketingDao.addBatch(ListtSellerMarketings);//批量增加
		
		Map<String,Object> mongoMap = new HashMap<String,Object>();
		for(TSeller s:ListSellers){//更新商家信息
			sellerDao.update(s);
			
			if(s.getYledger()!=null){
				mongoMap.put("yledger", NumberUtil.getDouble4Fixedpoint(s.getYledger()));
			}
			if(s.getFlatAgio()!=null){
				mongoMap.put("flat_agio", NumberUtil.getDouble4Fixedpoint(s.getFlatAgio()));
			}
			if(s.getRatio()!=null){
				mongoMap.put("ratio", NumberUtil.getDouble4Fixedpoint(s.getRatio()));
			}
			sellerService.updateMongo(s.getSellerid(),mongoMap);
		}
		
		for(TSellerAgio ts:ListTSellerAgios){//更新商家折扣信息
			sellerAgioDao.updatebyselleridAndstatusAndtype(ts);
		}
	}
	
	/**
	 * 设置操作提示信息
	 * @param resultable
	 * @param flag   是否选择了商家
	 * @param isSuccess    活动与商家是否关联成功(相同类型活动下不重复添加某个商家)
	 * @param stringBuffer  商家名称
	 * @author wangzhimin
	 */
	private void getResultable(Resultable resultable, boolean flag, boolean isSuccess, StringBuffer stringBuffer){
		if(flag){
			if(isSuccess){
				resultable.setMsg("添加成功");
    			resultable.setSuccess(true);
			}else{
				String mg = stringBuffer.toString();
				mg="重复参加该类型活动的商家："+mg.substring(0,mg.lastIndexOf("、"))+"，请修改后保存！";
            	resultable.setMsg("商家重复参加该类型活动，请更正信息！");
            	resultable.setData(mg);
            	resultable.setSuccess(false);
			}
		}else{
			resultable.setMsg("所选商家为空,请更正信息！");
			resultable.setData("");
        	resultable.setSuccess(false);
		}
	}
	
	/**
	 * 构造TSellerAgio对象并返回
	 * @param tActivityRule
	 * @param id
	 * @param doType
	 * @return
	 * @author wangzhimin
	 * @param giveMoney 
	 */
	private TSellerAgio getTSellerAgio(TActivityRule tActivityRule, double giveMoney, Integer id,
			String doType) {
		TSellerAgio tSellerAgio = new TSellerAgio();
		if("discount".equals(doType)){
			tSellerAgio.setSellerid(id);
			tSellerAgio.setFlatAgio(giveMoney);//平台补贴折扣
			tSellerAgio.setType(1);
			tSellerAgio.setSdate(new Date());
			tSellerAgio.setStatus(1);
		}else if("commission".equals(doType)){
			tSellerAgio.setSellerid(id);
			tSellerAgio.setRatio(giveMoney);   //佣金补贴比例
			tSellerAgio.setType(1);
			tSellerAgio.setSdate(new Date());
			tSellerAgio.setStatus(1);
		}
		return tSellerAgio;
	}

	/**
	 * 构造TSeller对象并返回
	 * @param count
	 * @param id
	 * @param doType
	 * @param giveMoney
	 * @return
	 * @author wangzhimin
	 */
	private TSeller getTSeller(Long count, Integer id, String doType, double giveMoney){
		TSeller seller = new TSeller();
		seller.setSellerid(id);
		if(count < 1){
			if("discount".equals(doType)){
				seller.setUdate(new Date());
				seller.setFlatAgio(giveMoney);
				seller.setYledger(sellerAgioDao.getUsingCommonAgion(Long.valueOf(id)).getYledger() + giveMoney);
			}else if("commission".equals(doType)){
				seller.setUdate(new Date());
				seller.setRatio(giveMoney);
			}else if("youhuiquan".equals(doType)){
				seller=sellerService.getObject(seller);
			}
		}else{
			seller=sellerService.getObject(seller);
		}
		return seller;
	}
	
	/**
	 * 构造TSellerMarketing对象并返回
	 * @param tSellerMarketing
	 * @param mapdate
	 * @param id
	 * @return
	 * @author wangzhimin
	 */
	private TSellerMarketing getTSellerMarketing(TSellerMarketing tSellerMarketing, Map<String, Date> mapdate, Integer id, Long count){
		TSellerMarketing marketing = new TSellerMarketing();
		marketing.setSellerid(id);
		marketing.setAid(tSellerMarketing.getAid());
		marketing.setActivityType(tSellerMarketing.getActivityType()); 
		if(null != count && count < 1){
			marketing.setSdate(mapdate.get("sdate"));
			marketing.setEdate(mapdate.get("edate"));
			marketing.setIsattend(0);
			marketing.setRdate(new Date());
		}
		return marketing;
	}
	
	
	
	
	/**
	 * @author dong'jietao 
	 * @date 2015年5月29日 下午6:06:16
	 * @TODO 日期和时间拼装
	 * @param map
	 * @param aid
	 */
   public void putMapdate(Map<String,Date> map,Long aid){//日期和时间拼装
	   TActivity tActivity=activityDao.getObject(Long.parseLong(String.valueOf(aid)));
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String sdate=tActivity.getStartDate();
	   String sdatetime=tActivity.getStartTime();
	   if(sdatetime==null||"".equals(sdatetime)){
		   sdatetime="00:00:00";
	   }
	   String edate=tActivity.getEndDate();
	   String edatetime=tActivity.getEndTime();
	   if(edatetime==null||"".equals(edatetime)){
		   sdatetime="00:00:00";
	   }
	   try {
		map.put("sdate", sdf.parse(sdate+" "+sdatetime));
		map.put("edate", sdf.parse(edate+" "+edatetime));
	} catch (ParseException e) {
		e.printStackTrace();
	}  
   }
	/**
	 * 查询折扣补贴列表
	 * @param activity
	 * @return
	 */
	public List<TActivity> getDiscountList(TActivity activity){
		return activityDao.getDiscountList(activity);
	}
	
	
	/**
	 * 查询折扣补贴列表总数
	 * @param activity
	 * @return
	 */
	public Long getDiscountListCount(TActivity activity){
		return activityDao.getDiscountListCount(activity);
	}
	
	
	/**
	 * 查询折扣补贴给出金额明细
	 * @param giveMoneyInfo
	 * @return
	 */
	public List<TGiveMoneyInfo> getDiscountGiveMoneyList(TGiveMoneyInfo giveMoneyInfo){
		return activityDao.getDiscountGiveMoneyList(giveMoneyInfo);
	}
	
	/**
	 * 查询折扣补贴给出金额明细总数
	 * @param giveMoneyInfo
	 * @return
	 * 
	 * update by wangzhimin 2015/08/28 12:16:30
	 */
	public Long getDiscountGiveMoneyListCount(TGiveMoneyInfo giveMoneyInfo){
		//return activityDao.getDiscountGiveMoneyListCount(giveMoneyInfo);
		return activityDao.getDiscountGiveMoneyListCountNumber(giveMoneyInfo);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean  addDiscountActivity(TActivity activity, HttpServletRequest request){
		try{
			activity.setType(5);
			activityDao.add(activity);
			String[] s={"营销活动-折扣补贴",activity.getAid().toString(),"新增"};
			fireLoginEvent(s,1);
			TActivityRule rule =  new TActivityRule();
			rule.setAid(activity.getAid());
			rule.setGiveMoney(String.valueOf(new BigDecimal(activity.getNgiveMoney().toString()).divide(new BigDecimal(100)).doubleValue()));
			rule.setMinMoeny(String.valueOf(activity.getMinMoeny()));
			rule.setMaxMoeny(String.valueOf(activity.getMaxMoeny()));
			Date date = new Date();
			rule.setAddTime(date);
			rule.setUpdateTime(date);
			String userName = ResultUtil.getCurrentUser(request).getUsername();
			rule.setAddUser(userName);
			rule.setUpdateUser(userName);
			activityManagermentDao.addTActivityRule(rule);
//			String[] rulestr={"营销活动-折扣补贴编号",activity.getAid().toString(),"新增营销-折扣补贴规则","新增"};
//			fireLoginEvent(rulestr,1);
			return true;
		}catch(Exception e){
			String[] s={"营销活动-折扣补贴",activity.getAname(),"新增"};
			fireLoginEvent(s,0);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean  updateDiscountActivity(TActivity activity, HttpServletRequest request){
		
		try{
			activity.setType(null);
			activityDao.update(activity);
			activityDao.updateDataByAid(activity);
			String[] s={"营销活动-折扣补贴编号",activity.getAid().toString(),"修改","修改"};
			fireLoginEvent(s,1);
			TActivityRule rule =  new TActivityRule();
			rule.setAid(activity.getAid());
			rule.setGiveMoney(String.valueOf(new BigDecimal(activity.getNgiveMoney().toString()).divide(new BigDecimal(100)).doubleValue()));
			rule.setMinMoeny(String.valueOf(activity.getMinMoeny()));
			rule.setMaxMoeny(String.valueOf(activity.getMaxMoeny()));
			Date date = new Date();
			rule.setUpdateTime(date);
			rule.setUpdateUser(ResultUtil.getCurrentUser(request).getUsername());
			activityManagermentDao.updateDiscountActivity(rule);
//			String[] rulestr={"营销活动-折扣补贴编号",activity.getAid().toString(),"修改营销-折扣补贴规则","修改"};
//			fireLoginEvent(rulestr,1);
			return true;
		}catch(Exception e){
			String[] s={"营销活动",activity.getAname(),"新增"};
			fireLoginEvent(s,0);
		}
		return false;
	}
	
	
	/**
	 * 根据编号查活动询信息
	 * @param id
	 * @return
	 */
	public TActivity getDiscountInfo(String id){
		return activityDao.getDiscountInfo(id);
	}
	
	/**
	 * 根据活动id取得活动和活动规则
	 * @param aid
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public TActivity getActivityAndRuls(Long aid){
		TActivity activity = null;
		List<TActivityRule> activityRules = null;
		if(aid!=null){
			activity = activityDao.getObject(aid);
			activityRules = activityManagermentDao.getActivityRuleList(aid);
			if(activity!=null){
				activity.settActivityRule(activityRules);
			}
		}
		return activity;
	}
	/**
	 * 根据活动id取得活动和积分活动规则
	 * @param aid
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public TActivity getActivityAndIntegralRuls(Long aid){
		TActivity activity = null;
		List<TIntegralRule> tIntegralRule = null;
		if(aid!=null){
			activity = activityDao.getObject(aid);
			tIntegralRule = activityManagermentDao.getIntegralRuleList(aid);
			String grade = tIntegralRule.get(0).getGrade();
			if(activity!=null){
				activity.settIntegralRule(tIntegralRule);
				activity.setGrade(grade);
			}
		}
		return activity;
	}
	/**
	 * 取得活动和积分活动规则中未添加的商家类别，用于显示在添加页面商家类别选项
	 * @param aid
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<TIntegralRule> getIntegralRulsPrade(){
		List<TIntegralRule> tIntegralRule = null;
		tIntegralRule = activityManagermentDao.getIntegralRulsPrade();
		return tIntegralRule;
	}

	
	
	/**
	 * 查询佣金补贴列表
	 * @param activity
	 * @return
	 */
	public List<TActivity> getCommissionList(TActivity activity) {
		return activityDao.getCommissionList(activity);
	}

	/**
	 * 查询佣金补贴列表总数
	 * @param activity
	 * @return
	 */
	public Long getCommissionCount(TActivity activity) {
		return activityDao.getCommissionCount(activity);
	}

	/**
	 * 添加佣金补贴
	 * @param request 
	 * @param tActivity
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addCommissionActivity(TActivity activity, HttpServletRequest request) {
		try{
			activity.setType(4);  //佣金补贴
			activityDao.add(activity);
			String[] s={"营销活动-佣金补贴",activity.getAid().toString(),"新增"};
			fireLoginEvent(s,1);
			TActivityRule rule =  new TActivityRule();
			rule.setAid(activity.getAid());
			rule.setGiveMoney(String.valueOf(new BigDecimal(activity.getNgiveMoney().toString()).divide(new BigDecimal(100)).doubleValue()));
			Date date = new Date();
			rule.setAddTime(date);
			rule.setUpdateTime(date);
			String userName = ResultUtil.getCurrentUser(request).getUsername();
			rule.setAddUser(userName);
			rule.setUpdateUser(userName);
			activityManagermentDao.addTActivityRule(rule);
//			String[] rulestr={"营销活动-佣金补贴编号",activity.getAid().toString(),"设置佣金补贴率","设置"};
//			fireLoginEvent(rulestr,1);
			return true;
		}catch(Exception e){
			String[] s={"营销活动-佣金补贴",activity.getAname(),"新增"};
			fireLoginEvent(s,0);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

	/**
	 * 根据编号查询佣金补贴活动信息
	 * @param id
	 * @return
	 */
	public TActivity getCommissionInfo(String aid) {
		return activityDao.getCommissionInfo(aid);
	}

	/**
	 * 修改佣金补贴活动信息
	 * @param request 
	 * @param tActivity
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateCommissionActivity(TActivity activity, HttpServletRequest request) {
		try{
			activity.setType(null);
			activityDao.update(activity);
			activityDao.updateDataByAid(activity);
			String[] s={"营销活动-佣金补贴编号",activity.getAid().toString(),"修改","修改"};
			fireLoginEvent(s,1);
			TActivityRule rule =  new TActivityRule();
			rule.setAid(activity.getAid());
			rule.setGiveMoney(String.valueOf(new BigDecimal(activity.getNgiveMoney().toString()).divide(new BigDecimal(100)).doubleValue()));
			//rule.setMinMoeny(String.valueOf(activity.getMinMoeny()));
			//rule.setMaxMoeny(String.valueOf(activity.getMaxMoeny()));
			rule.setUpdateTime(new Date());
			rule.setUpdateUser(ResultUtil.getCurrentUser(request).getUsername());
			activityManagermentDao.updateDiscountActivity(rule);
//			String[] rulestr={"营销活动-佣金补贴编号",activity.getAid().toString(),"修改营销-佣金补贴规则","修改"};
//			fireLoginEvent(rulestr,1);
			return true;
		}catch(Exception e){
			String[] s={"营销活动",activity.getAname(),"新增"};
			fireLoginEvent(s,0);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

	/**
	 * 查询佣金补贴给出金额明细
	 * @param giveMoneyInfo
	 * @return
	 */
	public List<TGiveMoneyInfo> getCommissionGiveMoneyList(TGiveMoneyInfo giveMoneyInfo) {
		return activityDao.getCommissionGiveMoneyList(giveMoneyInfo);
	}

	/**
	 * 查询佣金补贴给出金额明细总数
	 * @param giveMoneyInfo
	 * @return
	 */
	public Long getCommissionGiveMoneyListCount(TGiveMoneyInfo giveMoneyInfo) {
		return activityDao.getCommissionGiveMoneyListCount(giveMoneyInfo);
	}
}
