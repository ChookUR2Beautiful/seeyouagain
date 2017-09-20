package com.xmniao.service.msg;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.OSUtil;
import com.xmniao.entity.MQMsgConfig;

public class ReturnWithdrawalsProducer {
	/** 日志记录 */
	private final Logger log = Logger.getLogger(ReturnWithdrawalsProducer.class);
	/** 平台退款回调消息生产者 */
	private DefaultMQProducer producer;
	
	/** 平台退款回调消息生产主题 */
	private String rwtopic;
	/** 平台退款回调消息生产标签 */
	private String rwtags;
	/** 消息*/
	private Message msg;

	/** 实例名 */
	private String rwInstance;

	@Autowired
	private MQMsgConfig mqMsgConfig;
	
	/**
	 * 初始化生产者、消息
	 */
	public void init(){
		
		 producer = new DefaultMQProducer();

		/* 消费者组、消费者实例标识 */
		String producer_ident = "";
		/* 主题  标识 */
		
		/* 判断是否开启集群 */
		if("0".equals(mqMsgConfig.getCluster())){
			producer_ident = mqMsgConfig.getIdentityPay();
		}else{
			producer_ident = OSUtil.getLocalIP_();
		}
		rwtopic += producer_ident;
		
		/* 生产者组名 */
		producer.setProducerGroup(mqMsgConfig.getProducerGroup()+producer_ident);
		/* 生产者实例名 */
		producer.setInstanceName(rwInstance+producer_ident);  
		/* 消息服务器地址 */
		producer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		try {
			producer.start();
		} catch (MQClientException e) {
		  log.error("平台退款回调消息生产者初始化异常", e);
		}
		
        log.info("平台退款回调消息生产配置：   " 
        		+"   group:"+producer.getProducerGroup()
        		+"   instance:"+producer.getInstanceName()
        		+"   topic:"+rwtopic
        		+"   tag:"+rwtags
        		);
		msg = new Message();
		// 消息
		msg.setTopic(rwtopic);
		msg.setTags(rwtags);
		
	}
	

	
	/**
	 * 发送平台退款回调消息
	 * @param paramMap
	 *           orderNumber
	 *           status
	 *           message   
	 *           platform_code
	 *           userType
	 */
	public void sendMsg(Map<String, String> paramMap)
			throws UnsupportedEncodingException, MQClientException,
			RemotingException, MQBrokerException, InterruptedException {

		String body = JSON.toJSONString(paramMap); // 消息主体
		//key = tx + orderid
		msg.setKeys("rw"+paramMap.get("wId"));
		msg.setBody(body.getBytes("UTF-8"));
        //发送消息
		SendResult sendResult = producer.send(msg);
		log.info("成功发送平台退款回调消息:" + body);
		log.info(sendResult);
	}
	
	/**
	 * 注销生产者
	 */
	public void shutdown(){
		log.info("平台退款回调消息生产者被注销...");
		producer.shutdown();
	}



	public String getRwtopic() {
		return rwtopic;
	}



	public void setRwtopic(String rwtopic) {
		this.rwtopic = rwtopic;
	}



	public String getRwtags() {
		return rwtags;
	}



	public void setRwtags(String rwtags) {
		this.rwtags = rwtags;
	}


	public void setRwInstance(String rwInstance) {
		this.rwInstance = rwInstance;
	}
	
	


}
