/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TLivePayOrderDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.live_anchor.entity.TLivePayOrder;
import com.xmniao.xmn.core.reward_dividends.dao.TLivePrivilegeDao;
import com.xmniao.xmn.core.reward_dividends.entity.TLivePrivilege;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.exception.FailureException;
import com.xmniao.xmn.core.thrift.service.liveService.LiveOrderService;
import com.xmniao.xmn.core.thrift.service.liveService.ResponseData;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLivePayOrderService
 * 
 * 类描述： 
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-1 下午4:38:11 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class TLivePayOrderService extends BaseService<TLivePayOrder> {

	/**
	 * 注入直播支付订单服务
	 */
	@Autowired
	private TLivePayOrderDao livePayOrderDao;
	
	/**
	 * 注入寻蜜鸟用户服务
	 */
	@Autowired
	private BursService bursService;
	
	/**
	 * 注入企业推荐人服务
	 */
	@Autowired
	private TLiveReferrerService referrerService;
	
	/**
	 * 注入直播订单服务
	 */
	@Resource(name="liveOrderServiceClient")
	private ThriftClientProxy liveOrderServiceClient;
	
	@Autowired
	private BLiveFansRankService liveFansRankService;
	
	
	@Autowired
    private TLivePrivilegeDao livePrivilegeDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return livePayOrderDao;
	}
	
	/**
	 * 
	 * 方法描述：获取包含寻蜜鸟用户信息的直播支付订单列表
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-2上午11:30:45
	 * @param livePayOrder
	 * @return
	 */
	public List<TLivePayOrder> getListContainUrsInfo(TLivePayOrder livePayOrder){
		List<TLivePayOrder> livePayOrderList=new ArrayList<TLivePayOrder>();
		List<Object> uids=new ArrayList<Object>();
		
		//从企业级推荐人列表进入
		String eUid = livePayOrder.geteUid();//当前企业用户的uid
		if(StringUtils.isNotBlank(eUid)){
			List<String> juniorUidList = referrerService.getJuniorUidList(eUid);
			if(juniorUidList!=null && juniorUidList.size()>0){
				livePayOrder.setJuniorUids(juniorUidList);
			}
		}
		
		
		List<TLivePayOrder> list = getList(livePayOrder);
		if(list!=null && list.size()>0){
			for(TLivePayOrder order:list){
				uids.add(order.getUid());
			}
			
			List<Object> uidList = new ArrayList<>(new HashSet<>(uids));//去除重复uid
			List<Burs> ursList = bursService.getUrsListByUids(uidList.toArray());
			if(ursList!=null && ursList.size()>0){
				for(TLivePayOrder order:list){
					for(Burs urs:ursList){
						if(urs.getUid()!=null&&order.getUid()!=null){
							if(urs.getUid().compareTo(order.getUid())==0){
								order.setNname(urs.getNname());
								order.setPhone(urs.getPhone());
								break;
							}
						}
						
					}
					livePayOrderList.add(order);
				}
			}
			
		}
		
		return livePayOrderList;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-4上午9:45:43 <br/>
	 * @param livePayOrder
	 */
	public ResponseData saveInfo(TLivePayOrder livePayOrder) {
		ResponseData responseData= new ResponseData();
		livePayOrder.setCreateTime(new Date());
		String orderNo="05"+com.xmniao.xmn.core.util.StringUtils.generateOrderNo();
		livePayOrder.setOrderNo(orderNo);
		int count = livePayOrderDao.addReturnId(livePayOrder);
		if(count>0){
			Map<String,String> paramMap=new HashMap<String,String>();
			paramMap.put("bid", livePayOrder.getOrderNo());
			paramMap.put("status", "1");//支付状态 0 支付失败 1 支付成功
			paramMap.put("payType", livePayOrder.getPayType());
			paramMap.put("payId", "");
			paramMap.put("payCode", "");
			paramMap.put("zdate", DateUtil.formatDate(new Date(), DateUtil.Y_M_D_HMS));
			responseData = updateLiveOrder(paramMap);
			
			if(responseData ==null ){
				
			}
		}
		return responseData;
	}
	
	/**
	 * 更新直播订单
	 * @param request
	 * @return
	 * @throws FailureException
	 * @throws TException
	 */
	public ResponseData updateLiveOrder(Map<String,String> paramMap) {
		LiveOrderService.Client client = (LiveOrderService.Client)(liveOrderServiceClient.getClient());//业务服务
		ResponseData responseData = null;
		try {
			responseData = client.updateLiveOrder(paramMap);
		} catch (Exception e) {
			this.log.debug("更新直播订单失败");
		} finally {
			liveOrderServiceClient.returnCon();
		}
		return responseData;
	}

	/**
	 * 方法描述：设置直播支付订单查询参数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-15下午3:17:37 <br/>
	 * @param livePayOrder
	 */
	public void setLivePayOrderRequest(TLivePayOrder livePayOrder) {
		if(StringUtils.isNotBlank(livePayOrder.getPhone())){
			Burs burs = new Burs();
			burs.setPhone(livePayOrder.getPhone());
			Burs urs = bursService.getUrs(burs);
			if(urs==null){
				livePayOrder.setUid(-1);
			}else{
				livePayOrder.setUid(urs.getUid());
			}
		}
	}
	
	
	/**
	 * 方法描述：查询订单奖励方案信息 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月16日上午11:18:51 <br/>
	 * @param id
	 * @return
	 */
	public TLivePayOrder searchLivePayOrderInfo(Long id){
		TLivePayOrder livePayOrder = livePayOrderDao.getObject(id);
		if (livePayOrder != null){
			//查询用户信息
			if(livePayOrder.getUid()!=null){
				Burs bean = new Burs();
				bean.setUid(livePayOrder.getUid());
				Burs urs = bursService.getUrs(bean);
				if (urs.getUid()!=null){
					if(urs.getUid().compareTo(livePayOrder.getUid())==0){
						livePayOrder.setNname(urs.getNname());
						livePayOrder.setPhone(urs.getPhone());
					}
				}
			}
			//查询已奖励期数
			if(livePayOrder.getOrderNo()!=null){
				TLivePrivilege livePrivilege = livePrivilegeDao.getLivePrivilegeByOrderNo(livePayOrder.getOrderNo());
				if (livePrivilege != null){
					if (livePrivilege.getCurPeriodExcitation() != null){//已返还期数
						livePayOrder.setCurPeriodExcitation(livePrivilege.getCurPeriodExcitation());
					}
					if (livePrivilege.getPeriodExcitation() != null){//返还总期数
						livePayOrder.setPeriodExcitation(livePrivilege.getPeriodExcitation());
					}
					//查询等级信息
					if (livePrivilege.getLedgerLevel()!= null){
						livePayOrder.setLedgerLevel(livePrivilege.getLedgerLevel());
						BLiveFansRank liveFansRank = liveFansRankService.getFansRank(livePrivilege.getLedgerLevel().longValue());
						if (liveFansRank != null) 
							livePayOrder.setLedgerLevelDesc(liveFansRank.getRankName());
					}
				}
			}
		}
		
		return livePayOrder;
	}
	
	/**
	 * 方法描述：更改V客充值渠道奖励方案 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月15日下午6:32:21 <br/>
	 * @param livePayOrder
	 * @return
	 */
	public ResponseData updateLivePayOrderExcitationProject(TLivePayOrder livePayOrder) {
		ResponseData responseData = new ResponseData();
		TLivePrivilege livePrivilege = livePrivilegeDao.getLivePrivilegeByOrderNo(livePayOrder.getOrderNo());
		if (livePrivilege != null) {
			//更新打赏分红奖励
			if (livePayOrder.getExcitationProject() != null) {  
				//奖励方案
				String excitationProject = livePayOrder.getExcitationProject();
				
				TLivePrivilege bean = new TLivePrivilege();
				bean.setId(livePrivilege.getId());
				//总期数
				if ("A".equals(excitationProject)){
					bean.setPeriodExcitation(12);
				}else if ("B".equals(excitationProject)){
					bean.setPeriodExcitation(1);
				}
				//V客已奖励期数(仅对V客渠道充值订单有效)
				bean.setCurPeriodExcitation(livePayOrder.getCurPeriodExcitation()==null ? 1 : livePayOrder.getCurPeriodExcitation());  
				livePrivilegeDao.updateByPrimaryKeySelective(bean);

				//更改配置方案
				TLivePayOrder LivePayOrderInfo = new TLivePayOrder();
				LivePayOrderInfo.setId(livePayOrder.getId());
				LivePayOrderInfo.setExcitationProject(excitationProject);
				int count = livePayOrderDao.update(LivePayOrderInfo);
				if (count > 0) {
					responseData.setState(0);
				}
			}
            /* 记录日志 */
            String[] loggerInfo = {"订单编号", livePayOrder.getOrderNo().toString(), "奖励方案",
            		livePayOrder.getExcitationProject(), "商家充值订单奖励方案配置修改", "修改"};
            /* 同步MongoDB */
            fireLoginEvent(loggerInfo, 1);
		}else {
			//未获取到打赏分红奖励信息
			responseData.setState(1);
		}
		return responseData;
	}
	

}
