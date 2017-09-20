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

public class UpdateBalanceCallbackProducer {
	/** 日志记录 */
	private final Logger log = Logger.getLogger(UpdateBalanceCallbackProducer.class);
	/** 修改余额回调消息生产者 */
	private DefaultMQProducer producer;
	
	/** 修改余额回调消息生产主题 */
	private String ubtopic;
	/** 修改余额回调消息生产标签 */
	private String ubtags;
	/** 修改钱包余额回调消息生产实例 */
	private String ubInstance;
	/** 消息*/
	private Message msg;

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
		ubtopic += producer_ident;
		
		/* 生产者组名 */
		producer.setProducerGroup(mqMsgConfig.getProducerGroup()+producer_ident);
		/* 生产者实例名 */
		producer.setInstanceName(ubInstance+producer_ident);  
		/* 消息服务器地址 */
		producer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		try {
			producer.start();
		} catch (MQClientException e) {
		  log.error("修改钱包余额回调消息生产者初始化异常", e);
		}
		
        log.info("修改钱包余额回调消息生产配置：   " 
        		+"   group:"+producer.getProducerGroup()
        		+"   instance:"+producer.getInstanceName()
        		+"   topic:"+ubtopic
        		+"   tag:"+ubtags
        		);
		msg = new Message();
		// 消息
		msg.setTopic(ubtopic);
		msg.setTags(ubtags);
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
		msg.setKeys(paramMap.get("orderId"));
		msg.setBody(body.getBytes("UTF-8"));
        //发送消息
		SendResult sendResult = producer.send(msg);
		log.info("成功发送修改钱包余额回调消息:" + body);
		log.info(sendResult);
	}
	
	/**
	 * 注销生产者
	 */
	public void shutdown(){
		log.info("修改钱包余额回调消息生产者被注销...");
		producer.shutdown();
	}



	public String getUbtopic() {
		return ubtopic;
	}



	public void setUbtopic(String ubtopic) {
		this.ubtopic = ubtopic;
	}



	public String getUbtags() {
		return ubtags;
	}



	public String getUbInstance() {
		return ubInstance;
	}



	public void setUbInstance(String ubInstance) {
		this.ubInstance = ubInstance;
	}



	public void setUbtags(String ubtags) {
		this.ubtags = ubtags;
	}
	
	public static void main(String[] args){
		for(int i=0;i<3;i++){
		initProduce();
		}
	}

	public static void initProduce(){
		
		DefaultMQProducer producer = new DefaultMQProducer();

		/* 消费者组、消费者实例标识 */
		String producer_ident = "";
		String ubtopic = "Producer_ABC_";
		String ubtags ="Tags_ABC_";
		/* 主题  标识 */
		
		/* 判断是否开启集群 */
		producer_ident = OSUtil.getLocalIP_();
		ubtopic += producer_ident;
		
		/* 生产者组名 */
		producer.setProducerGroup("GROUP"+producer_ident);
		/* 生产者实例名 */
		producer.setInstanceName("Instance"+producer_ident);  
		/* 消息服务器地址 */
		producer.setNamesrvAddr("192.168.50.123:9876");
		try {
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
		
       System.out.println("修改钱包余额回调消息生产配置：   " 
       		+"   group:"+producer.getProducerGroup()
       		//+"   instance:"+producer.getInstanceName()
       		+"   topic:"+ubtopic
       		+"   tag:"+ubtags
       		);
       Message msg = new Message();
		// 消息
		msg.setTopic(ubtopic);
		msg.setTags(ubtags);
		
	}
}
