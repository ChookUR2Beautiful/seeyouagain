package com.xmniao.xmn.core.live_anchor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.dao.TLiveRechargeAndRedPacketDao;
import com.xmniao.xmn.core.live_anchor.entity.LiveRequestBean;
import com.xmniao.xmn.core.util.DateUtil;



/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveRechargeAndRedPacketService
 * 
 * 类描述： 直播充值和红包统计Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-31 上午11:27:59 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TLiveRechargeAndRedPacketService extends BaseService<Object>{
	
	private static final  String START_TIME= "2017-01-07";//默认统计开始时间
	
	/**
	 * 注入直播充值与红包统计Dao
	 */
	@Autowired
	private TLiveRechargeAndRedPacketDao rechargeAndRedPacketDao;
	
	/**
	 * 
	 * 方法描述：统计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-19下午6:31:23 <br/>
	 * @param map
	 * @return
	 */
	public Map<String,Object> getRechargeInfo(Map<String,Object> map){
		return rechargeAndRedPacketDao.getRechargeInfo(map);
	}
	
	
	/**
	 * 
	 * 方法描述：根据充值金额分组统计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-19下午6:31:23 <br/>
	 * @param requestBean
	 * @return
	 */
	public List<LiveRequestBean>  getRechargeGroupByPayment(LiveRequestBean requestBean){
		return rechargeAndRedPacketDao.getRechargeGroupByPayment(requestBean);
	}
	
	/**
	 * 
	 * 方法描述：根据充值金额分组统计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-19下午6:31:23 <br/>
	 * @param requestBean
	 * @return
	 */
	public long  getRechargeGroupByPaymentCount(LiveRequestBean requestBean){
		return rechargeAndRedPacketDao.getRechargeGroupByPaymentCount(requestBean);
	}
	
	/**
	 * 
	 * 方法描述：统计红包信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-19下午6:31:23 <br/>
	 * @param map
	 * @return
	 */
	public Map<String,Object> getRedPacketInfo(Map<String,Object> map){
		return rechargeAndRedPacketDao.getRedPacketInfo(map);
	}


	/**
	 * 方法描述：加载累计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午2:24:03 <br/>
	 * @param requestBean
	 * @return
	 */
	public Resultable loadRechargeTotal(LiveRequestBean requestBean) {
		Resultable result=new Resultable();
		try {
			Map<String,Object> totalRequest=new HashMap<String,Object>();
			totalRequest.put("startTime", START_TIME);
			Map<String, Object> rechargeTotalInfo = getRechargeInfo(totalRequest);
			Object col1 = rechargeTotalInfo.get("payment")==null?0:rechargeTotalInfo.get("payment");
			Object col2 =  rechargeTotalInfo.get("people")==null?0:rechargeTotalInfo.get("people");
			
			Map<String,Object> isOnLineRequest=new HashMap<String,Object>();
			isOnLineRequest.put("startTime", START_TIME);
			isOnLineRequest.put("isOnLine", "1");
			Map<String, Object> rechargeOnLineInfo = getRechargeInfo(isOnLineRequest);
			Object col3 = rechargeOnLineInfo.get("payment")==null?0:rechargeOnLineInfo.get("payment");
			Object col4 =  rechargeOnLineInfo.get("people")==null?0:rechargeOnLineInfo.get("people");
			
			Map<String,Object> offLineRequest=new HashMap<String,Object>();
			offLineRequest.put("startTime", START_TIME);
			offLineRequest.put("isOnLine", "0");
			Map<String, Object> rechargeOffLineInfo = getRechargeInfo(offLineRequest);
			Object col5 = rechargeOffLineInfo.get("payment")==null?0:rechargeOffLineInfo.get("payment");
			Object col6 =  rechargeOffLineInfo.get("people")==null?0:rechargeOffLineInfo.get("people");
			
			Map<String,Object> response=new HashMap<String,Object>();
			response.put("col1", col1);//充值总金额
			response.put("col2", col2);//充值总人数
			response.put("col3", col3);//线上充值总金额
			response.put("col4", col4);//线上充值总人数
			response.put("col5", col5);//线下充值总金额
			response.put("col6", col6);//线下充值总人数
			result.setSuccess(true);
			result.setData(response);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载累计充值信息失败!");
			this.log.error("加载累计充值信息失败:"+e.getMessage(), e);
		}
		
		return result;
	}


	/**
	 * 方法描述：加载指定时间累计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午3:17:40 <br/>
	 * @param requestBean
	 * @return
	 */
	public Resultable loadRechargeOfTime(LiveRequestBean requestBean) {
		Resultable result= new Resultable();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			String startTime = requestBean.getStartTime();
			String endTime = requestBean.getEndTime();
			if(StringUtils.isBlank(startTime)){
				map.put("startTime", DateUtil.formatDate(new Date(), DateUtil.Y_M_D));
			}else{
				map.put("startTime", startTime);
			}
			
			if(StringUtils.isBlank(endTime)){
				map.put("endTime", DateUtil.formatDate(new Date(), DateUtil.Y_M_D));
			}else{
				map.put("endTime", endTime);
			}
			
			Map<String, Object> rechargeTotalInfo = getRechargeInfo(map);
			Object col1 = rechargeTotalInfo.get("payment")==null?0:rechargeTotalInfo.get("payment");
			Object col2 =  rechargeTotalInfo.get("people")==null?0:rechargeTotalInfo.get("people");
			
			map.put("isOnLine", "1");
			Map<String, Object> rechargeOnLineInfo = getRechargeInfo(map);
			Object col3 = rechargeOnLineInfo.get("payment")==null?0:rechargeOnLineInfo.get("payment");
			Object col4 =  rechargeOnLineInfo.get("people")==null?0:rechargeOnLineInfo.get("people");
			
			map.put("isOnLine", "0");
			Map<String, Object> rechargeOffLineInfo = getRechargeInfo(map);
			Object col5 = rechargeOffLineInfo.get("payment")==null?0:rechargeOffLineInfo.get("payment");
			Object col6 =  rechargeOffLineInfo.get("people")==null?0:rechargeOffLineInfo.get("people");
			
			Map<String,Object> response=new HashMap<String,Object>();
			
			response.put("startTime", map.get("startTime"));
			response.put("endTime", map.get("endTime"));
			response.put("col1", col1);//充值总金额
			response.put("col2", col2);//充值总人数
			response.put("col3", col3);//线上充值总金额
			response.put("col4", col4);//线上充值总人数
			response.put("col5", col5);//线下充值总金额
			response.put("col6", col6);//线下充值总人数
			result.setSuccess(true);
			result.setData(response);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载指定时间累计充值信息失败!");
			this.log.error("加载指定时间累计充值信息:"+e.getMessage(),e);
		}
		
		return result;
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return null;
	}


	/**
	 * 方法描述：加载红包统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午4:07:37 <br/>
	 * @param requestBean
	 * @return
	 */
	public Resultable loadRedPacketTotal(LiveRequestBean requestBean) {
		Resultable result=new Resultable();
		try {
			Object col1=0;
			Object col2=0;
			Object col3=0;
			Object col4=0;
			Object col5=0;
			Object col6=0;
			Object col7=0;
			Object col8=0;
			Object col9=0;
			Map<String,Object> requestMap=new HashMap<String,Object>();
			requestMap.put("startTime", START_TIME);
			Map<String, Object> redPacketInfo = getRedPacketInfo(requestMap);
			if(redPacketInfo!=null){
				 col1 = redPacketInfo.get("ledgerAmount")==null?0:redPacketInfo.get("ledgerAmount");
				 col2 =  redPacketInfo.get("redPacketNum")==null?0:redPacketInfo.get("redPacketNum");
			}
			
			
			requestMap.put("countType", "1");//有效可领取
			Map<String, Object> redPacketInfo1 = getRedPacketInfo(requestMap);
			if(redPacketInfo1!=null){
				col3 = redPacketInfo1.get("ledgerAmount")==null?0:redPacketInfo1.get("ledgerAmount");
				col4 =  redPacketInfo1.get("realLedgerAmount")==null?0:redPacketInfo1.get("realLedgerAmount");
				col5 =  redPacketInfo1.get("redPacketNum")==null?0:redPacketInfo1.get("redPacketNum");
			}
			
			requestMap.put("countType", "0");//无人认领
			Map<String, Object> redPacketInfo0 = getRedPacketInfo(requestMap);
			if(redPacketInfo0!=null){
				col6 = redPacketInfo0.get("ledgerAmount")==null?0:redPacketInfo0.get("ledgerAmount");
				col7 =  redPacketInfo0.get("redPacketNum")==null?0:redPacketInfo0.get("redPacketNum");
			}
			
			requestMap.put("countType", "4");//限制认领
			Map<String, Object> redPacketInfo4 = getRedPacketInfo(requestMap);
			if(redPacketInfo4!=null){
				col8 = redPacketInfo4.get("ledgerAmount")==null?0:redPacketInfo4.get("ledgerAmount");
				col9 =  redPacketInfo4.get("redPacketNum")==null?0:redPacketInfo4.get("redPacketNum");
			}
			
			Map<String,Object> response=new HashMap<String,Object>();
			response.put("col1", col1);//实际发放总额
			response.put("col2", col2);//红包个数
			response.put("col3", col3);//有效可领取总额
			response.put("col4", col4);//有效实际领取总额
			response.put("col5", col5);//红包个数
			response.put("col6", col6);//无人认领总额
			response.put("col7", col7);//红包个数
			response.put("col8", col8);//限制认领总额
			response.put("col9", col9);//红包个数
			result.setSuccess(true);
			result.setData(response);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载红包统计信息失败 ！");
			this.log.error("加载红包统计信息失败:"+e.getMessage(), e);
		}
		
		return result;
	}


	/**
	 * 方法描述：加载指定时间区间累计红包统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午5:00:17 <br/>
	 * @param requestBean
	 * @return
	 */
	public Resultable loadRedPacketOfTime(LiveRequestBean requestBean) {
		Resultable result=new Resultable();
		try {
			Object col1=0;
			Object col2=0;
			Object col3=0;
			Object col4=0;
			Object col5=0;
			Object col6=0;
			Object col7=0;
			Object col8=0;
			Object col9=0;
			
			Map<String,Object> requestMap=new HashMap<String,Object>();
			String startTime = requestBean.getStartTime();
			String endTime = requestBean.getEndTime();
			if(StringUtils.isBlank(startTime)){
				requestMap.put("startTime", DateUtil.formatDate(new Date(), DateUtil.Y_M_D));
			}else{
				requestMap.put("startTime", startTime);
			}
			
			if(StringUtils.isBlank(endTime)){
				requestMap.put("endTime", DateUtil.formatDate(new Date(), DateUtil.Y_M_D));
			}else{
				requestMap.put("endTime",endTime);
			}
			
			Map<String, Object> redPacketInfo = getRedPacketInfo(requestMap);
			if(redPacketInfo!=null){
				 col1 = redPacketInfo.get("ledgerAmount")==null?0:redPacketInfo.get("ledgerAmount");
				 col2 =  redPacketInfo.get("redPacketNum")==null?0:redPacketInfo.get("redPacketNum");
			}
			
			
			requestMap.put("countType", "1");//有效可领取
			Map<String, Object> redPacketInfo1 = getRedPacketInfo(requestMap);
			if(redPacketInfo1!=null){
				col3 = redPacketInfo1.get("ledgerAmount")==null?0:redPacketInfo1.get("ledgerAmount");
				col4 =  redPacketInfo1.get("realLedgerAmount")==null?0:redPacketInfo1.get("realLedgerAmount");
				col5 =  redPacketInfo1.get("redPacketNum")==null?0:redPacketInfo1.get("redPacketNum");
			}
			
			requestMap.put("countType", "0");//无人认领
			Map<String, Object> redPacketInfo0 = getRedPacketInfo(requestMap);
			if(redPacketInfo0!=null){
				col6 = redPacketInfo0.get("ledgerAmount")==null?0:redPacketInfo0.get("ledgerAmount");
				col7 =  redPacketInfo0.get("redPacketNum")==null?0:redPacketInfo0.get("redPacketNum");
			}
			
			requestMap.put("countType", "4");//限制认领
			Map<String, Object> redPacketInfo4 = getRedPacketInfo(requestMap);
			if(redPacketInfo4!=null){
				col8 = redPacketInfo4.get("ledgerAmount")==null?0:redPacketInfo4.get("ledgerAmount");
				col9 =  redPacketInfo4.get("redPacketNum")==null?0:redPacketInfo4.get("redPacketNum");
			}
			
			Map<String,Object> response=new HashMap<String,Object>();
			response.put("startTime", requestMap.get("startTime"));
			response.put("endTime", requestMap.get("endTime"));
			response.put("col1", col1);//实际发放总额
			response.put("col2", col2);//红包个数
			response.put("col3", col3);//有效可领取总额
			response.put("col4", col4);//有效实际领取总额
			response.put("col5", col5);//红包个数
			response.put("col6", col6);//无人认领总额
			response.put("col7", col7);//红包个数
			response.put("col8", col8);//限制认领总额
			response.put("col9", col9);//红包个数
			result.setSuccess(true);
			result.setData(response);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载红包统计信息失败 ！");
			this.log.error("加载红包统计信息失败:"+e.getMessage(), e);
		}
		
		return result;
	}


	/**
	 * 方法描述：设置查询时间，为空默认今天 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午6:57:00 <br/>
	 * @param liveRequest
	 */
	public void setQueryTime(LiveRequestBean liveRequest) {
		String startTime = liveRequest.getStartTime();
		String endTime = liveRequest.getEndTime();
		
		if(StringUtils.isBlank(startTime)){
			liveRequest.setStartTime(DateUtil.formatDate(new Date(), DateUtil.Y_M_D));
		}
		
		if(StringUtils.isBlank(endTime)){
			liveRequest.setEndTime(DateUtils.formatDate(new Date(),DateUtil.Y_M_D));
		}
	}

}