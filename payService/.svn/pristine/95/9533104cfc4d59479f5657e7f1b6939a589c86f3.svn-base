/**    
 * 文件名：ManorMQConsumeService.java    
 *    
 * 版本信息：    
 * 日期：2017年6月21日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.impl.manor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.PayConstants;
import com.xmniao.enums.ResultCodeEnum;
import com.xmniao.service.PropsService;
import com.xmniao.service.msg.manor.ManorConsumer;
import com.xmniao.service.msg.manor.ManorProducer;
import com.xmniao.thrift.manor.Result;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 项目名称：payService_manor
 * 
 * 类名称：ManorMQConsumeService
 * 
 * 类描述： 黄金庄园消息消费服务
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年6月21日 下午2:38:31 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class ManorMQConsumeService {

	private final Logger log = Logger.getLogger(ManorConsumer.class);
	
	@Autowired
	private PropsService propsService;
	
	@Autowired
	private ManorProducer manorProducer;
	
	@Autowired
	private String manorPayTopic;
	
	@Autowired
	private String manorNectarReturnbackTag;
	
	public ConsumeConcurrentlyStatus consumeDailyNectarMQ(MessageExt msg){
		String message= new String(msg.getBody());
		Map<String,String>paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>() {});
		
		try{
			//累计花蜜
			Result result= propsService.receiveEvaryDayNectary(paramMap.get("transNo"),Integer.parseInt(paramMap.get("uid")), new Integer(paramMap.get("nectar")));
			if(result.getCode()!=ResultCodeEnum.SUCCESS.status() && !result.getStatusCode().equals("20004")){
				throw new Exception("给会员"+paramMap.get("uid")+"收入花蜜失败："+result.getMessage());
			}else{
				Map<String,String> bodyMap = new HashMap<>();
				bodyMap.put("transNo", paramMap.get("transNo"));
				bodyMap.put("status", "2");
				bodyMap.put("infoMsg", "");
				manorProducer.returnBackMsg(manorPayTopic,manorNectarReturnbackTag,bodyMap);
			}
		}catch(Exception e){
			log.error("消费【"+paramMap.get("uid")+"】花蜜收益消息异常",e);
			int  times = msg.getReconsumeTimes();
			if(times<PayConstants.MANOR_MESSAGE_RECONSUMER_TIMES){
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}else{
				log.error("该消息消费了【"+PayConstants.MANOR_MESSAGE_RECONSUMER_TIMES+"】后，仍消费失败，将从消息队列中移除");
				Map<String,String> bodyMap = new HashMap<>();
				bodyMap.put("transNo", paramMap.get("transNo"));
				bodyMap.put("status", "3");
				bodyMap.put("infoMsg", e.getMessage());
				try {
					manorProducer.returnBackMsg(manorPayTopic,manorNectarReturnbackTag,bodyMap);
				} catch (UnsupportedEncodingException | MQClientException
						| RemotingException | MQBrokerException
						| InterruptedException e1) {
					log.error("发送回调消息失败");
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
	
	public ConsumeConcurrentlyStatus consumeEnergyMQ(MessageExt msg){
		// 累计能量
		String message= new String(msg.getBody());
		Map<String,String>paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>() {});
		
		try{
			Result result= propsService.addUserEnergy(paramMap.get("transNo"),Integer.parseInt(paramMap.get("uid")), new Double(paramMap.get("energy")),Integer.parseInt(paramMap.get("type")));
			if(result.getCode()!=ResultCodeEnum.SUCCESS.status()){
				throw new Exception("给会员"+paramMap.get("uid")+"添加能量失败");
			}
		}catch(Exception e){
			int  times = msg.getReconsumeTimes();
			if(times<PayConstants.MANOR_MESSAGE_RECONSUMER_TIMES){
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}else{
				log.error("该消息消费了【"+PayConstants.MANOR_MESSAGE_RECONSUMER_TIMES+"】后，仍消费失败，将从消息队列中移除");
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
