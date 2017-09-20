package com.xmniao.service.msg;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.entity.MQMsgConfig;
import com.xmniao.entity.SubsidyMsg;

public class SubsidyProducer {
	
	/** 日志记录 */
	private final Logger log = Logger.getLogger(SubsidyProducer.class);
	/** 发放补贴消息生产者 */
	private DefaultMQProducer producer;
	/** 发放补贴消息生产主题 */
	private String withdrawtopic;
	/** 发放补贴消息生产标签 */
	private String withdrawtags;
	/** 消息*/
	private Message msg;
    /** rocketMQ 基本配置   */
	@Autowired
	private MQMsgConfig mqMsgConfig;
	/** 分账IP */
	@Autowired 
	@Qualifier("IP_NUMBER")
	private String ledgerIP;
	
	/**
	 * 初始化生产者、消息
	 */
	public void init(){
		
		producer = new DefaultMQProducer();
		/* 消费者组、消费者实例标识 */
		String producer_ident = "";
		
		/* 判断是否开启集群   取标识*/
		if("0".equals(mqMsgConfig.getCluster())){
			producer_ident = mqMsgConfig.getIdentityPay();
		}else{
			producer_ident = ledgerIP.replace(".", "_");
		}
		withdrawtopic += producer_ident;
		
		/* 生产者组名 */
		producer.setProducerGroup(mqMsgConfig.getProducerGroup()+"Subsidy"+producer_ident);
		/* 生产者实例名 */
		producer.setInstanceName(mqMsgConfig.getProducerInstance()+"Subsidy"+producer_ident);  
		/* 消息服务器地址 */
		producer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		try {
			producer.start();
		} catch (MQClientException e) {
		  log.error("发放补贴消息生产者初始化异常", e);
		}
		
        log.info("发放补贴消息生产配置：   "
        		+"   group:"+producer.getProducerGroup()
        		+"   instance:"+producer.getInstanceName()
        		+"   topic:"+withdrawtopic
        		+"   tag:"+withdrawtags);
     // 消息
		msg = new Message();
		msg.setTopic(withdrawtopic);
		msg.setTags(withdrawtags);
	}
	

	
	/**
	 * 发送发放补贴消息
	 * @param paramMap
	 *           orderNumber
	 *           status
	 *           message   
	 *           platform_code
	 *           userType
	 */
	public void sendMsg(SubsidyMsg subsidyMsg)
			throws UnsupportedEncodingException, MQClientException,
			RemotingException, MQBrokerException, InterruptedException {
		
		String body = JSONObject.toJSONString(subsidyMsg);// 消息主体
		msg.setKeys("qb_rType_0_"+subsidyMsg.getOrderId());
		msg.setBody(body.getBytes("UTF-8"));
        //发送消息
		SendResult sendResult = producer.send(msg);
		log.info("成功发送发放补贴消息:" + body);
		log.info(sendResult);
	}
	
	/**
	 * 注销生产者
	 */
	public void shutdown(){
		log.info("发放补贴消息生产者被注销...");
		producer.shutdown();
	}
	
	public String getWithdrawtopic() {
		return withdrawtopic;
	}



	public void setWithdrawtopic(String withdrawtopic) {
		this.withdrawtopic = withdrawtopic;
	}



	public String getWithdrawtags() {
		return withdrawtags;
	}



	public void setWithdrawtags(String withdrawtags) {
		this.withdrawtags = withdrawtags;
	}

}
