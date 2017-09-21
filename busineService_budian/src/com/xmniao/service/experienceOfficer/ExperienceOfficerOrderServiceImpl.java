/**
 * 
 */
package com.xmniao.service.experienceOfficer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.DateUtil;
import com.xmniao.dao.experienceOfficer.TExperienceOfficerOrderDao;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.experienceOfficer.ExperienceOfficerOrderService;
import com.xmniao.thrift.pay.ExperiencecardService;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：ExperienceOfficerOrderServiceImpl
 * 
 * 类描述： 美食体验官充值订单服务实现类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-8 下午4:56:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service("ExperienceOfficerOrderServiceImpl")
public class ExperienceOfficerOrderServiceImpl implements ExperienceOfficerOrderService.Iface {
	
	/**
	 * 初始化日志
	 */
	private Logger log=Logger.getLogger(ExperienceOfficerOrderServiceImpl.class);
	
	/**
	 * 注入美食体验卡订单服务
	 */
	@Autowired
	private TExperienceOfficerOrderDao experienceOfficerOrderDao;
	
	 /**
     * 注入支付服务IP地址
     */
	@Resource(name="transLedgerIP")
    private String transLedgerIP;
    
    /**
     * 注入支付服务端口号
     */
	@Resource(name="transLedgerPort")
    private int transLedgerPort;

	/**
	 * 
	 * 方法描述：更新美食体验官充值订单状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-8下午5:02:21 <br/>
	 */
	@Override
	@Transactional(rollbackFor={Exception.class})
	public ResponseData updateExperienceOfficerOrder(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新美食体验官充值订单:paraMap="+paraMap);
		
		ResponseData responseData = new ResponseData();
		
		try {
			//验证订单号
			if(StringUtils.isBlank(paraMap.get("orderNo"))){
				log.error("订单号为空");
				responseData.setState(2);
				responseData.setMsg("订单号为空");
				return responseData;
			}
			
			//验证支付状态
			if(StringUtils.isBlank(paraMap.get("payState"))){
				log.error("支付状态为空");
				responseData.setState(2);
				responseData.setMsg("支付状态为空");
				return responseData;
			}
			
			//验证支付状态
			if(StringUtils.isNotBlank(paraMap.get("payState"))){
				String payState = paraMap.get("payState");
				boolean result = "1".equals(payState)||"2".equals(payState)||"3".equals(payState);
				if(!result){
					log.error("支付状态传入值错误");
					responseData.setState(2);
					responseData.setMsg("支付状态传入值错误");
					return responseData;
				}
			}
			
			String orderNo = paraMap.get("orderNo");
			Map<String, Object> experienceOrder = experienceOfficerOrderDao.selectByOrderNo(orderNo);
			if(experienceOrder==null || experienceOrder.isEmpty()){
				log.error("未查询到该订单orderNo:"+orderNo);
				responseData.setState(1);
				responseData.setMsg("未查询到该订单");
				return responseData;
			}
			
			//验证订单状态
			if((Integer)experienceOrder.get("payState")!=0){
				log.error("订单已经更新成功，请勿重复提交：订单状态："+experienceOrder.get("payState"));
				responseData.setState(2);
				responseData.setMsg("订单已经更新成功，请勿重复提交");
				return responseData;
			}
			
			//验单,防止篡改数据
			if(!verifyOrder(paraMap, experienceOrder)){
				log.error("订单验证失败");
				responseData.setState(1);
				responseData.setMsg("订单验证失败");
				return responseData;
			}
			
			String payState = paraMap.get("payState");//支付状态 1成功 2失败 3取消支付
			String payType = paraMap.get("payType");//支付方式
			
			Map<String,Object> experienceOrderParam=new HashMap<String,Object>();
			experienceOrderParam.put("payState", payState);
			experienceOrderParam.put("payTime", new Date());
			experienceOrderParam.put("orderNo", orderNo);
			experienceOrderParam.put("payType", payType);
			experienceOrderParam.put("version", experienceOrder.get("version"));
			
			int count = experienceOfficerOrderDao.update(experienceOrderParam);
			
			if(count<1){
				this.log.error("更新记录数："+count);
				throw new FailureException(1, "更新订单状态失败，orderNo:" + paraMap.get("orderNo"));
			}
			
			if("1".equals(payState) && count>0){
				/**
				 * 调用支付服务更新用户美食体验卡信息
				 */
				updateExperienceCard(experienceOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("更新美食体验卡订单失败：", e);
			throw new FailureException(1, "更新订单状态异常，orderNo:" + paraMap.get("orderNo"));
		}
		
		responseData.setState(0);
		responseData.setMsg("更新成功");
		return responseData;
	}

	  /**
	 * 方法描述：调用支付服务更新用户美食体验卡信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-9下午2:10:46 <br/>
	 * @param paraMap
	 * @throws FailureException 
	 */
	private void updateExperienceCard(Map<String, Object> paraMap) throws FailureException {
		com.xmniao.thrift.pay.ResponseData responseData =new com.xmniao.thrift.pay.ResponseData();
		
		String orderNo = paraMap.get("orderNo").toString();//订单编号
		String uid = paraMap.get("uid").toString();//会员编号
		String days = paraMap.get("days").toString();//有效天数
		int addDay=new Integer(days);
		String dueDate = DateUtil.getSpecifiedDate(addDay);//截止日期
		String nums = paraMap.get("nums").toString();//可体验次数
		
		//组装调用支付服务参数
		Map <String,String> updateCardMap=new HashMap<String,String>();
		updateCardMap.put("uid", uid);
		updateCardMap.put("dueDate", dueDate);
		updateCardMap.put("stock", nums);
		updateCardMap.put("source", orderNo);
		
		TTransport transport = null;
		try {
			
			//调用分账服务的IP和端口号
			transport = new TSocket(transLedgerIP, transLedgerPort);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			//分账服务的综合服务接口模块
			TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "ExperiencecardService");
			ExperiencecardService.Client client = new ExperiencecardService.Client(orderProtocol);
			//打开端口,开始调用
			transport.open();
			
			responseData = client.createExperiencecard(updateCardMap);
			
			
		} catch (TTransportException e) {
			e.printStackTrace();
			this.log.error("调用业务服务 updateExperienceCard step1 异常:"+e.getMessage(), e);
		} catch (com.xmniao.thrift.pay.FailureException e) {
			e.printStackTrace();
			this.log.error("调用支付服务ExperiencecardService——> createExperiencecard接口异常:"+e.getMessage(), e);
		} catch (TException e) {
			e.printStackTrace();
			this.log.error("调用业务服务 updateExperienceCard step2 异常:"+e.getMessage(), e);
		}finally{
			if(transport!=null){
				transport.close();
			}
		}
		
		int state = responseData.getState();
		if (state != 0) {
			log.error("开通美食体验卡失败:" + responseData.toString());
			throw new FailureException(1, "更新订单状态失败orderNo:" + orderNo);
		}
	}

	/**
	 * 方法描述：验证订单 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-8下午6:03:55 <br/>
	 * @param paraMap
	 * @param experienceOrder
	 * @return
	 */
	private boolean verifyOrder(Map<String, String> paraMap,
			Map<String, Object> experienceOrder) {
		boolean result = true;
		if(!paraMap.get("uid").equals(experienceOrder.get("uid").toString())){
			result=false;
			log.error("订单用户身份未验证通过");
			return result;
		}
		
		String orderNo = paraMap.get("orderNo");//订单编号
		BigDecimal amount = new BigDecimal(paraMap.get("amount"));//订单总金额
		BigDecimal liveCoin = new BigDecimal(paraMap.get("liveCoin"));//鸟粉卡支付鸟币
		BigDecimal walletAmount = new BigDecimal(paraMap.get("walletAmount"));//钱包余额支付金额
		BigDecimal samount = new BigDecimal(paraMap.get("samount"));//第三方支付金额
		BigDecimal amountSum = liveCoin.add(walletAmount).add(samount);//订单总金额计算结果
		
		BigDecimal experienceAmount = new BigDecimal(experienceOrder.get("amount").toString());//数据库保存的订单总金额
		
		if(amount.compareTo(amountSum)!=0 || experienceAmount.compareTo(amountSum)!=0){
			result=false;
			log.error("验单 ：传入订单("+orderNo+")总金额,订单总金额计算结果与数据库匹配——>"+amount+":"+amountSum+":"+experienceAmount+" :"+result);
			return result;
		}
		
		
		return result;
	}
	
}
