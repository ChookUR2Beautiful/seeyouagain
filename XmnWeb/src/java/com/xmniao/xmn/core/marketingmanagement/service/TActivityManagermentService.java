package com.xmniao.xmn.core.marketingmanagement.service;

import java.text.DecimalFormat;
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

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.SellerAgioDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.SellerMarketingDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAgio;
import com.xmniao.xmn.core.businessman.entity.TSellerMarketing;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.marketingmanagement.dao.TActivityDao;
import com.xmniao.xmn.core.marketingmanagement.dao.TActivityManagermentDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;
import com.xmniao.xmn.core.marketingmanagement.entity.TIntegralRule;
import com.xmniao.xmn.core.marketingmanagement.util.MarketConstants;
import com.xmniao.xmn.core.util.DateHelper;
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
 * 创建人：yingde'cao
 * 
 * 创建时间：2015年01月14日10时15分24秒
 * 
 * Copyright©广东寻蜜鸟网络技术有限公司
 */
@Service
public class TActivityManagermentService extends BaseService<TActivity> {
	
	@Autowired
	public TActivityManagermentDao tActivityManagermentDao;

	@Override
	protected BaseDao getBaseDao() {
		return tActivityManagermentDao;
	}
	
	@Autowired
	private SellerMarketingDao sellerMarketingDao;
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private TActivityDao tActivityDao;
	
	@Autowired
	private SellerService sellerService;

	@Autowired
	private SellerAgioDao sellerAgioDao;
	
	
	/**
	 * 获取商家列表信息
	 * 
	 * @param seller
	 * @return
	 */
	public Pageable<TSeller> getSellerInfoList(TSeller seller) {
		Pageable<TSeller> sellerInfoList = new Pageable<TSeller>(seller);
		// 商家列表内容
		sellerInfoList.setContent(sellerDao.getActivitySentList(seller));
		// 总条数
		sellerInfoList.setTotal(sellerDao.getActivitySentCount(seller));
		return sellerInfoList;
	}
	
	/**
	 * 根据活动ID获取TSellerAgio对象
	 * @param aid
	 * @return
	 */
	private TSellerAgio getTSellerAgio(Integer sellerid){
		return sellerAgioDao.getUsingCommonAgion(Long.valueOf(sellerid));
	}
	
   /**
    * @author dong'jietao 
    * @date 2015年6月18日 下午4:30:40
    * @TODO 退出活动 公用逻辑
    * @param sellerMarketing
    * @param resultable
    */
	public void sellerExitActivity(TSellerMarketing sellerMarketing,Resultable resultable){
		Object[] sellerMarketings={sellerMarketing.getId()};
		
		int deleteNum=sellerMarketingDao.delete(sellerMarketings);
		TSeller tSeller = new TSeller();
		//中间表删除后  折扣补贴 改为0
		TSellerAgio tSellerAgio=new TSellerAgio();	
		
		String doType = sellerMarketing.getDoType();
		if(null != doType && "discount".equals(doType)){  //是折扣活动的时候才要重置flat_agio字段的值
			tSeller.setFlatAgio(0.0);		
			tSeller.setYledger(getTSellerAgio(sellerMarketing.getSellerid()).getYledger());
			tSellerAgio.setFlatAgio(0.0);
			tSeller.setSellerid(sellerMarketing.getSellerid());
			int sellerNum=sellerService.update(tSeller);
		}
		
		tSellerAgio.setSellerid(sellerMarketing.getSellerid());
		
		int sellerAgioNum=sellerAgioDao.updatebysellerid(tSellerAgio);
		
		if(deleteNum==1){
			Map<String,Object> mongoMap = new HashMap<String,Object>();
			mongoMap.put("yledger", NumberUtil.getDouble4Fixedpoint(tSeller.getYledger()));
			mongoMap.put("flat_agio", NumberUtil.getDouble4Fixedpoint(tSeller.getFlatAgio()));
			sellerService.updateMongo(sellerMarketing.getSellerid(),mongoMap);
			resultable.setMsg("操作成功！");
			resultable.setSuccess(true);
			this.log.info("商家退出活动成功");
		}else{
			resultable.setMsg("操作失败！");
			resultable.setSuccess(false);
			this.log.info("商家退出活动失败");
		}
		
		String[] s1={"商家活动编号",String.valueOf(sellerMarketing.getSellerid()),"退出活动","退出"};
		this.fireLoginEvent(s1,resultable.getSuccess()?1:0);
	}
	/**
	 * 添加活动
	 * @author yingde'cao
	 * @param tActivity
	 * @param request 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addActivity(TActivity tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		Long aid = null;
		try {
			tActivityDao.add(tActivity);
			aid = (long)tActivity.getAid();
			List<TActivityRule> list = tActivity.gettActivityRule();
			TActivityRule temp=null;
			Date date = new Date();
			if(!list.isEmpty()){
				for (int i = 0; i < list.size(); i++) {
					temp=list.get(i);
					String maxMoney=temp.getMaxMoeny();
					if(null==maxMoney || "".equals(maxMoney)){
						temp.setMaxMoeny("99999.99");
					}
					double max=Double.parseDouble(temp.getMaxMoeny());
					double min=Double.parseDouble(temp.getMinMoeny());
					if(max >= min || max == 0){
						temp.setAid(new Long(aid).intValue());
						temp.setAddTime(date);
						temp.setUpdateTime(date);
						String userName = ResultUtil.getCurrentUser(request).getUsername();
						temp.setAddUser(userName);
						temp.setUpdateUser(userName);
						tActivityManagermentDao.addTActivityRule(temp);
						this.log.info("添加成功");
						resultable = new Resultable(true, "操作成功");
					}else{
						String[] s1={"满赠活动编号",String.valueOf(tActivity.getAid()),"添加","添加"};
						fireLoginEvent(s1,0);
						resultable = new Resultable(false, "修改失败，请输入"+min+" -- 99999.99之间的数字！");
						this.log.error("消费区间最高消费不能低于最低消费！");
					}
				}
			}
		}catch (Exception e) {
			this.log.error("活动添加异常：", e);
		}
//		finally {
//			String[] s={"添加活动编号",aid.toString(),"成功","失败"};
//			super.fireLoginEvent(s,resultable.getSuccess()?1:0);
//		}
		return resultable;
	}
	/**
	 * @Title:addManZengJifenActivity
	 * @Description:添加满赠积分活动
	 * @param tActivity
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addManZengJifenActivity(TActivity tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		Long aid = null;
		try {
			String grade = tActivity.getGrade();//商家类别(用于添加到积分规则表 以及 组合作为活动名称)
			String aname = tActivity.getAname();
			if("1".equals(grade)){
				aname = "(A)" + aname;
			}else if("3".equals(grade)){
				aname = "(B)" + aname;
			}else{
				aname ="(C)"+ aname;
			}
			tActivity.setAname(aname);//(A/B/C)活动名称
			tActivityDao.add(tActivity);
			aid = (long)tActivity.getAid();
			List<TIntegralRule> list = tActivity.gettIntegralRule();//积分规则集合
			TIntegralRule temp=null;
			//获得当前系统时间作为创建时间
			String date = DateHelper.getDateFormatter();
			if(!list.isEmpty()){
				for (int i = 0; i < list.size(); i++) {
					temp=list.get(i);
					String endLadder=temp.getEndLadder();//积分结束阶梯
					
					if(null==endLadder || "".equals(endLadder)){
						temp.setEndLadder("99999.99");
					}
					double max=Double.parseDouble(temp.getEndLadder());
					double min=Double.parseDouble(temp.getStartLadder());
					if(max >= min || max == 0){
						temp.setAid(new Long(aid).intValue());
						temp.setRdate(date);
						temp.setGrade(grade);//商家类别用于添加到积分活动规则表
						tActivityManagermentDao.addJiFenTActivityRule(temp);
						this.log.info("添加成功");
						resultable = new Resultable(true, "操作成功");
					}else{
						String[] s1={"满赠活动(积分)编号",String.valueOf(tActivity.getAid()),"添加","添加"};
						fireLoginEvent(s1,0);
						resultable = new Resultable(false, "修改失败，请输入"+min+" -- 99999.99之间的数字！");
						this.log.error("消费区间最高消费不能低于最低消费！");
					}
				}
			}
		}catch (Exception e) {
			this.log.error("活动添加异常：", e);
		}
//		finally {
//			String[] s={"添加活动编号",aid.toString(),"成功","失败"};
//			super.fireLoginEvent(s,resultable.getSuccess()?1:0);
//		}
		return resultable;
	}
	
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月10日 下午2:45:19
	 * 描述：优惠券活动添加
	 * @param tActivity
	 * @return
	 */
	public Resultable addYouHuiQuanActivity(TActivity tActivity) {
		Resultable resultable = null;
		try {
			this.addYouHuiQuan(tActivity);
			resultable=this.addYouHuiQuanResInfo(tActivity);
		}catch (Exception e) {
			this.log.error("优惠券活动添加：", e);
			throw new ApplicationException("优惠券活动添加",e,new Object[]{tActivity},this.getInfoStr(tActivity));	
		}
		return resultable;
	}
	public void addYouHuiQuan(TActivity tActivity){
		try {
			tActivity.setType(6);
			tActivityDao.add(tActivity);
		} catch (Exception e) {
			this.log.error("优惠券活动添加：", e);
			throw new ApplicationException("优惠券活动添加",e,new Object[]{tActivity});
		}
	}
	public Resultable addYouHuiQuanResInfo(TActivity tActivity){
		super.fireLoginEvent(getInfoStr(tActivity),1);
		return new Resultable(true, "操作成功");
	}
	
	public  String[]  getInfoStr(TActivity tActivity){
		//添加到日志
		String word = tActivity.getAname();
		String str = "";
		if (word.length() <= MarketConstants.WORD_LENGTH){
			str = word;
		}else{
			str = word.substring(MarketConstants.RESULTNUM_INIT, MarketConstants.WORD_LENGTH)+"...";
		}
		return new String[]{"优惠券活动名称",str,"新增","新增"};
	}

	
	/**
	 * 修改活动
	 * @author yingde'cao
	 * @param tActivity
	 * @param request 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateActivity(TActivity tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		Date date = new Date();
		String userName = ResultUtil.getCurrentUser(request).getUsername();
		try {
			tActivity.setType(null);
			tActivityDao.update(tActivity);
			tActivityDao.updateDataByAid(tActivity);
			String[] s={"营销活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
			fireLoginEvent(s,1);
			String rids = tActivity.getDeleteList();
			if (!rids.isEmpty() && rids != "") {
				String rid = rids.substring(1, rids.length());
				String[] array=rid.split(",");
				tActivityManagermentDao.deleteActivityRule(array);
			}
			List<TActivityRule> list = tActivity.gettActivityRule();
			if(null!=list && list.size()>0){
				TActivityRule temp=null;
				for (int i = 0; i < list.size(); i++) {
					temp=list.get(i);
					if(null!=temp.getMinMoeny() && null!=temp.getGiveMoney()){
						Integer rid=temp.getRid();
						String maxMoney=temp.getMaxMoeny();
						if(null==maxMoney || "".equals(maxMoney)){
							temp.setMaxMoeny("99999.99");
						}
						double max=Double.parseDouble(temp.getMaxMoeny());
						double min=Double.parseDouble(temp.getMinMoeny());
						if(max >= min || max == 0){
							if(null != rid){
								temp.setUpdateTime(date);
								temp.setUpdateUser(userName);
								tActivityManagermentDao.updateActivityRule(temp);
							}else{
								temp.setAid(tActivity.getAid());
								temp.setAddTime(date);
								temp.setUpdateTime(date);
								temp.setAddUser(userName);
								temp.setUpdateUser(userName);
								tActivityManagermentDao.addTActivityRule(temp);
							}
							resultable = new Resultable(true, "修改成功");
							//String[] rules={"活动编号",String.valueOf(tActivity.getAid()),"修改活动规则","修改"};
							//fireLoginEvent(rules,1);
						}else{
							String[] s1={"活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
							fireLoginEvent(s1,0);
							resultable = new Resultable(false, "修改失败，请输入"+min+" -- 99999.99之间的数字！");
							this.log.error("消费区间最高消费不能低于最低消费！");
						}
					}else{
						return resultable = new Resultable(true, "修改成功");
					}
				}
			}
		
			}catch (Exception e) {
				String[] s={"活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
				fireLoginEvent(s,0);
				resultable = new Resultable(false, "修改失败");
				this.log.error("活动添加异常：", e);
			}
		return resultable;
	}
	/**
	 * @Title:updateJifenActivity
	 * @Description:修改满赠积分活动
	 * @param tActivity
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateJifenActivity(TActivity tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		//获得当前系统时间作为修改时间
		String date = DateHelper.getDateFormatter();
		String grade = tActivity.getGrade();//商家类别
		try {
			tActivity.setType(null);
			tActivityDao.update(tActivity);
			tActivityDao.updateDataByAid(tActivity);
			String[] s={"营销活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
			fireLoginEvent(s,1);
			String rids = tActivity.getDeleteList();
			if (!rids.isEmpty() && rids != "") {
				String rid = rids.substring(1, rids.length());
				String[] array=rid.split(",");
				//删除积分活动规则
				tActivityManagermentDao.deleteIntegralRule(array);
			}
			List<TIntegralRule> list = tActivity.gettIntegralRule();
			if(null!=list && list.size()>0){
				TIntegralRule temp=null;
				for (int i = 0; i < list.size(); i++) {
					temp=list.get(i);
					if(null!=temp.getStartLadder()){
						Integer rid=temp.getRid();
						String endLadder=temp.getEndLadder();
						if(null==endLadder || "".equals(endLadder)){
							temp.setEndLadder("99999.99");
						}
						double end=Double.parseDouble(temp.getEndLadder());
						double star=Double.parseDouble(temp.getStartLadder());
						if(end >= star || end == 0){
							if(null != rid){
								temp.setUdate(date);//更新时间
								temp.setGrade(grade);//商家类别
								tActivityManagermentDao.updateIntegraRule(temp);
							}else{
								temp.setAid(tActivity.getAid());
								temp.setRdate(date);//创建时间
								temp.setGrade(grade);//商家类别
								tActivityManagermentDao.addJiFenTActivityRule(temp);
							}
							resultable = new Resultable(true, "修改成功");
						}else{
							String[] s1={"活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
							fireLoginEvent(s1,0);
							resultable = new Resultable(false, "修改失败，请输入"+star+" -- 99999.99之间的数字！");
							this.log.error("消费区间最高消费不能低于最低消费！");
						}
					}else{
						return resultable = new Resultable(true, "修改成功");
					}
				}
			}
		
			}catch (Exception e) {
				String[] s={"活动编号",String.valueOf(tActivity.getAid()),"修改","修改"};
				fireLoginEvent(s,0);
				resultable = new Resultable(false, "修改失败");
				this.log.error("活动添加异常：", e);
			}
		return resultable;
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年10月10日 上午11:47:12
	 * 描述： 优惠全活动修改
	 * @param tActivity
	 * @param request
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateYouHuiQuanActivity(TActivity tActivity) {
		Resultable resultable = null;
		try {
            this.updateYouHuiQuan(tActivity);
		    resultable = this.updateYouHuiQuanResInfo(tActivity);
			}catch (Exception e) {
			    this.log.error("优惠券活动修改：", e);
				throw new ApplicationException("优惠券活动修改",e,new Object[]{tActivity},new String[]{"活动编号",String.valueOf(tActivity.getAid()),"修改","修改"});	
			}
		return resultable;
	}
	
	public void updateYouHuiQuan(TActivity tActivity){
		tActivityDao.update(tActivity);
		//tActivityDao.updateDataByAid(tActivity);
	}
	public Resultable updateYouHuiQuanResInfo(TActivity tActivity){
		String[] s={"优惠券活编号",String.valueOf(tActivity.getAid()),"修改","修改"};
		fireLoginEvent(s,1);
		return new Resultable(true, "修改成功");
	}
	
	
	/**
	 * 查询活动
	 * @author yingde'cao
	 * @param tActivity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TActivityRule> gettActivityRule(Long aid) {
		List<TActivityRule> list =null;
		try {
			list = tActivityManagermentDao.getActivityRuleList(aid);
		}catch (Exception e) {
			this.log.error("查询活动规则异常", e);
		}
		return list;
	}
	/**
	 * @Title:getiActivityRule
	 * @Description:查询积分活动规则
	 * @param aid
	 * @return List<TActivityRule>
	 * @throw
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TIntegralRule> getiActivityRule(Long aid) {
		List<TIntegralRule> list =null;
		try {
			list = tActivityManagermentDao.getIntegralRuleList(aid);
		}catch (Exception e) {
			this.log.error("查询活动规则异常", e);
		}
		return list;
	}
	
	/**
	 * 获取商家列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public boolean getType(TActivity tActivity) {
		// 查询在同一城市类关键字是否已经存在
		Long exist = tActivityManagermentDao.getType(tActivity);
		if (exist > 0) {
			return false;
		}
		return true;
	}
	
	public boolean getAid(TActivity tActivity) {
		String aname = tActivityManagermentDao.getAid(tActivity);
		Long exist = tActivityManagermentDao.getType(tActivity);
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
		 return tActivityManagermentDao.getObjectType(type);
	}
	
	/**
	 * 商家上线时 查询指定活动 关联商家 
	 * @return
	 */
	public Integer getSpecifiedActivity(){
		return tActivityManagermentDao.getSpecifiedActivity();
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void addSellerMarkerings(TSellerMarketing sellerMarketing) {
		String sellerids = sellerMarketing.getSellerids();
		if(StringUtils.hasLength(sellerids)){
			String[] ids = StringUtils.paresToArray(sellerids, ",");
			TSellerMarketing sm= null;
			TSeller seller = null;
			Integer id = null;
			Date date = new Date();
			Integer aid = sellerMarketing.getAid();
			Integer isattend = 0;//sellerMarketing.getIsattend();
			Date sdate = sellerMarketing.getSdate();
			Date edate = sellerMarketing.getEdate();
			List<TSellerMarketing> tSellerMarketings = new ArrayList<TSellerMarketing>(ids.length);
			for(String sellerid : ids){
				id = Integer.parseInt(sellerid);
				sm = new TSellerMarketing();
				sm.setAid(aid);
				sm.setSellerid(id);
				sm.setIsattend(isattend);
//				sm.setSdate(sdate);
//				sm.setEdate(edate);
				tSellerMarketings.add(sm);
				seller = new TSeller();
				seller.setSellerid(id);
				seller.setUdate(date);
				sellerDao.update(seller);
			}
			String[] sellerInfo = {"商家编号",sellerids,"更新修改时间","更新"};
			fireLoginEvent(sellerInfo);
			sellerMarketingDao.addBatch(tSellerMarketings);
			String[]sellerMarketingInfo = {"商家编号",sellerids,"批量添加活动","添加"}; 
			fireLoginEvent(sellerMarketingInfo);
		}
		
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月23日 下午4:37:37
	 * @TODO 更新参加营销活动状态
	 * @param sellerMarketing
	 * @param resultable
	 */
	public void updateSellerMarketingIsattend(TSellerMarketing sellerMarketing,Resultable resultable){
		sellerMarketing.setIsattend(sellerMarketing.getIsattend()==0?1:0);
	    Integer num=sellerMarketingDao.update(sellerMarketing);
	    if(num==1){
	    	resultable.setSuccess(true);
	    	resultable.setMsg("操作成功");
	    	String[] sellerInfo1 = {"商家编号",String.valueOf(sellerMarketing.getSellerid()),"参加活动状态更新","更新"};
			super.fireLoginEvent(sellerInfo1,1);
			String[] sellerInfo2 = {"活动编号",String.valueOf(sellerMarketing.getAid()),"参加活动状态更新","更新"};
	    	super.fireLoginEvent(sellerInfo2,1);
	    }else{
	    	resultable.setSuccess(false);
	    	resultable.setMsg("操作失败");
	    	String[] sellerInfo1 = {"商家编号",String.valueOf(sellerMarketing.getSellerid()),"参加活动状态更新","更新"};
			super.fireLoginEvent(sellerInfo1,0);
			String[] sellerInfo2 = {"活动编号",String.valueOf(sellerMarketing.getAid()),"参加活动状态更新","更新"};
	    	super.fireLoginEvent(sellerInfo2,0);
	    }
	}
	
	/**
	 * @author dong'jietao 
	 * @date 2015年6月12日 下午6:04:03
	 * @TODO 用户没有参与折扣补贴时，将t_seller和t_seller_agio表里的折扣补贴字段设为0
	 * @param sellerMarketing
	 * @param resultable
	 * @param tActivity
	 */	
	public void updateAgio(TSellerMarketing sellerMarketing,Resultable resultable){	
		Integer[] params = getCondition(sellerMarketing);  //下标 0：sellerid  1:isattend  2:aid
		boolean flag = resultable.getSuccess();
		if(flag){
			TSeller tSeller = new TSeller();
			if(MarketConstants.JOIN_ACTIVITY == params[1]){  //参与活动
				String giveMoney = getTActivityRule(params[2]).getGiveMoney();
				updateTSeller(params[0], params[1], giveMoney,tSeller);
				updateSellerAgio(params[0], params[1], giveMoney);	
			}
			if(MarketConstants.SUSPEND_ACTIVITY == params[1]){  //暂停活动
				updateTSeller(params[0], params[1], null,tSeller);
				updateSellerAgio(params[0], params[1], null);
			}
			
			if(tSeller.getSellerid() != null){
				Map<String,Object> mongoMap = new HashMap<String,Object>();
				mongoMap.put("yledger", NumberUtil.getDouble4Fixedpoint(tSeller.getYledger()));
				mongoMap.put("flat_agio", NumberUtil.getDouble4Fixedpoint(tSeller.getFlatAgio()));
				sellerService.updateMongo(params[0],mongoMap);  //根据 sellerid 更新 mongodb
			}else{
				sellerService.InsertOrUpdateMongo(params[0]);  //根据 sellerid 更新 mongodb
			}
		}
		String[] sellerInfo = {"商家编号",String.valueOf(params[0]),"平台折扣更新","更新"};
		super.fireLoginEvent(sellerInfo, flag ? 1 : 0);
	}
	
	
	private Integer[] getCondition(TSellerMarketing sellerMarketing){
		return new Integer[]{sellerMarketing.getSellerid(), sellerMarketing.getIsattend(), sellerMarketing.getAid()};
	}
	
	private int updateTSeller(Integer sellerid, Integer isattend, String giveMoney,TSeller tSeller){
		
		tSeller.setSellerid(sellerid);
		TSellerAgio tSellerAgio = getTSellerAgio(sellerid);
		if(0 == isattend){
			Double money = Double.parseDouble(giveMoney);
			tSeller.setFlatAgio(money);
			tSeller.setYledger(tSellerAgio.getYledger() + money);
		}else if(1 == isattend){
			tSeller.setFlatAgio(0.0);
			tSeller.setYledger(tSellerAgio.getYledger());
		}
		return sellerService.update(tSeller);
	}
	
	private int updateSellerAgio(Integer sellerid, Integer isattend, String giveMoney){
		TSellerAgio tSellerAgio = new TSellerAgio();
		tSellerAgio.setSellerid(sellerid);
		if(0 == isattend){
			tSellerAgio.setFlatAgio(Double.parseDouble(giveMoney));
		}else if(1 == isattend){
			tSellerAgio.setFlatAgio(0.0);
		}
		return sellerAgioDao.updatebysellerid(tSellerAgio);
	}
	
	private TActivityRule getTActivityRule(Integer aid){
		TActivityRule tActivityRule=new TActivityRule();
		tActivityRule.setAid(aid);
		tActivityRule=tActivityManagermentDao.getTActivityRule(tActivityRule);
		return tActivityRule;
	}
	
	
	/**
	 * 添加活动
	 * @author yingde'cao
	 * @param tActivity
	 * @param request 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addGuaguleActivity(TActivity tActivity, HttpServletRequest request) {
		Resultable resultable = null;
		Integer aid = null;
		try {
			tActivityDao.add(tActivity);
			aid = tActivity.getAid();
			List<TActivityRule> list = tActivity.getTactivityrule();
			TActivityRule temp=new TActivityRule();
			Date date= new Date();
			if(!list.isEmpty()){
				int listSize = list.size();
				if(1 == tActivity.getSetWay()){
					double Proportion = 0;
					for(int i = 0; i < listSize; i++){
						Proportion += Double.parseDouble(list.get(i).getGiveMoney());
					}
					if(100 != Proportion){
						return resultable = new Resultable(false, "操作失败,各奖项占比合计须为100%");
					}
				}
				String userName = ResultUtil.getCurrentUser(request).getUsername();
				for(int i = 0; i < listSize; i++){
					temp=list.get(i);
					temp.setAddUser(userName);
					temp.setUpdateUser(userName);
					temp.setAid(aid);
					temp.setAddTime(date);
					temp.setUpdateTime(date);
					tActivityManagermentDao.addTActivityRule(temp);
				}
			}
			/*if(!list.isEmpty()){
				double Proportion = 0;
				for (int i = 0; i < list.size(); i++) {					
					Proportion += Double.parseDouble(list.get(i).getGiveMoney());
					temp=list.get(i);
System.err.println(temp.getGiveMoney() + "============");					
					temp.setAid(aid);
					temp.setAddTime(date);
					temp.setUpdateTime(date);
				//}
					if(tActivity.getSetWay() == 1){
						if (Proportion != 100) {
							return resultable = new Resultable(false, "操作失败,各奖项占比合计须为100%");
						}
					}
					tActivityManagermentDao.addTActivityRule(temp);
				}
			}*/else{
				temp.setUpdateTime(date);
				tActivityManagermentDao.updateActivityRule(temp);
			}
			
			resultable = new Resultable(true, "操作成功");
		}catch (Exception e) {
			this.log.error("活动添加异常：", e);
			resultable = new Resultable(false, "操作失败");
		}finally {
			String[] s={"营销活动-刮刮卡",aid.toString(),"新增"};
			//String[] rulestr={"活动编号",aid.toString(),"添加抽奖规则","新增"};
			fireLoginEvent(s,resultable.getSuccess()?1:0);
			//fireLoginEvent(rulestr,resultable.getSuccess()?1:0);
		}
		return resultable;
	}
	
	
	 /**
	    * @author wangzhimin 
	    * @date 2015年8月13日 下午4:30:40
	    * @TODO 退出活动 佣金补贴逻辑
	    * @param sellerMarketing
	    * @param resultable
	    */
		public void commissionSellerExitActivity(TSellerMarketing sellerMarketing,Resultable resultable){
			Object[] sellerMarketings={sellerMarketing.getId()};
			int deleteNum=sellerMarketingDao.delete(sellerMarketings);
			TSeller tSeller = new TSeller();
			//中间表删除后  佣金补贴 改为0
			TSellerAgio tSellerAgio=new TSellerAgio();	
			tSeller.setSellerid(sellerMarketing.getSellerid());
			tSeller.setRatio(0.0);
			int sellerNum=sellerService.update(tSeller);
			tSellerAgio.setSellerid(sellerMarketing.getSellerid());
			tSellerAgio.setRatio(0.0);
			int sellerAgioNum=sellerAgioDao.updatebysellerid(tSellerAgio);
			
			if(deleteNum==1){
				Map<String,Object> mongoMap = new HashMap<String,Object>();
				mongoMap.put("ratio", NumberUtil.getDouble4Fixedpoint(tSeller.getRatio()));
				sellerService.updateMongo(sellerMarketing.getSellerid(),mongoMap);
				resultable.setMsg("操作成功！");
				resultable.setSuccess(true);
				this.log.info("商家退出佣金补贴活动成功");
			}else{
				resultable.setMsg("操作失败！");
				resultable.setSuccess(false);
				this.log.info("商家退出佣金补贴活动失败");
			}
			
			String[] s1={"商家活动编号",String.valueOf(sellerMarketing.getSellerid()),"退出佣金补贴活动","退出"};
			this.fireLoginEvent(s1,resultable.getSuccess()?1:0);
		}
		
		/**
		    * @author wangzhimin 
		    * @date 2015年8月17日 下午6:30:40
		    * @TODO 商家暂停佣金补贴逻辑
		    * @param sellerMarketing
		    * @param resultable
		    */
		public void updateCommissionAgio(TSellerMarketing sellerMarketing,Resultable resultable){	
			TActivityRule tActivityRule=new TActivityRule();
			tActivityRule.setAid(sellerMarketing.getAid());
			tActivityRule=tActivityManagermentDao.getTActivityRule(tActivityRule);
			int sellerNum=0;
			int sellerAgioNum=0;
			TSeller tSeller = new TSeller();
			if(resultable.getSuccess()&&sellerMarketing.getIsattend()==0){
				
				TSellerAgio tSellerAgio=new TSellerAgio();	
				tSeller.setSellerid(sellerMarketing.getSellerid());
				//tSeller.setFlatAgio(Double.parseDouble(tActivityRule.getGiveMoney()));
				tSeller.setRatio(Double.parseDouble(tActivityRule.getGiveMoney()));
				sellerNum=sellerService.update(tSeller);
				tSellerAgio.setSellerid(sellerMarketing.getSellerid());
				//tSellerAgio.setFlatAgio(Double.parseDouble(tActivityRule.getGiveMoney()));
				tSellerAgio.setRatio(Double.parseDouble(tActivityRule.getGiveMoney()));
				sellerAgioNum=sellerAgioDao.updatebysellerid(tSellerAgio);
				
			}
			if(resultable.getSuccess()&&sellerMarketing.getIsattend()==1){
				TSellerAgio tSellerAgio=new TSellerAgio();	
				tSeller.setSellerid(sellerMarketing.getSellerid());
				//tSeller.setFlatAgio(0.0);
				tSeller.setRatio(0.0);
				sellerNum=sellerService.update(tSeller);
				tSellerAgio.setSellerid(sellerMarketing.getSellerid());
				//tSellerAgio.setFlatAgio(0.0);
				tSellerAgio.setRatio(0.0);
				sellerAgioNum=sellerAgioDao.updatebysellerid(tSellerAgio);
			}
			
			if(resultable.getSuccess()&&sellerNum==1&&sellerAgioNum==1){
				if(tSeller.getSellerid()!=null){
					Map<String,Object> mongoMap = new HashMap<String,Object>();
					mongoMap.put("ratio", NumberUtil.getDouble4Fixedpoint(tSeller.getRatio()));
					sellerService.updateMongo(sellerMarketing.getSellerid(),mongoMap);
				}else{
					sellerService.InsertOrUpdateMongo(sellerMarketing.getSellerid());
				}
				String[] sellerInfo1 = {"商家编号",String.valueOf(sellerMarketing.getSellerid()),"佣金补贴更新","更新"};
				super.fireLoginEvent(sellerInfo1,1);
			}else{
				String[] sellerInfo1 = {"商家编号",String.valueOf(sellerMarketing.getSellerid()),"佣金补贴更新","更新"};
				super.fireLoginEvent(sellerInfo1,0);
			}
			
		}
}
