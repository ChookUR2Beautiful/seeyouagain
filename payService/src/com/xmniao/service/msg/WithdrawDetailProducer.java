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

public class WithdrawDetailProducer {
	
	/** 日志记录 */
	private final Logger log = Logger.getLogger(WithdrawDetailProducer.class);
	/** 提现明细消息生产者 */
	private DefaultMQProducer producer;
	/** 提现明细消息生产主题 */
	private String withdrawDetailtopic;
	/** 提现明细消息生产标签 */
	private String withdrawDetailtags;
	/** 消息*/
	private Message msg;
    /** rocketMQ 基本配置   */
	@Autowired
	private MQMsgConfig mqMsgConfig;
	
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
			producer_ident = OSUtil.getLocalIP_();
		}
		withdrawDetailtopic += producer_ident;
		
		/* 生产者组名 */
		producer.setProducerGroup(mqMsgConfig.getProducerGroup()+producer_ident);
		/* 生产者实例名 */
		producer.setInstanceName(mqMsgConfig.getProducerInstance()+"WithdrawDetail"+producer_ident);  
		/* 消息服务器地址 */
		producer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		try {
			producer.start();
		} catch (MQClientException e) {
		  log.error("提现明细生存者初始化异常", e);
		}
		
        log.info("提现明细返回消息生产配置：   "
        		+"   group:"+producer.getProducerGroup()
        		+"   instance:"+producer.getInstanceName()
        		+"   topic:"+withdrawDetailtopic
        		+"   tag:"+withdrawDetailtags);
     // 消息
		msg = new Message();
		msg.setTopic(withdrawDetailtopic);
		msg.setTags(withdrawDetailtags);
	}
	

	
	/**
	 * 发送提现返回消息
	 * @param paramMap
	 *           amount
	 *           uid  
	 *           description
	 *           sdate
	 */
	public void sendMsg(Map<String, String> paramMap)
			throws UnsupportedEncodingException, MQClientException,
			RemotingException, MQBrokerException, InterruptedException {
		
		String body = JSON.toJSONString(paramMap); // 消息主体
		msg.setKeys("tx_1_"+paramMap.get("orderNumber"));//key = tx_1_ + orderNumber
		msg.setBody(body.getBytes("UTF-8"));
        //发送消息
		SendResult sendResult = producer.send(msg);
		log.info("成功发送提现信息:" + body);
		log.info(sendResult);
	}
	
	/**
	 * 注销生产者
	 */
	public void shutdown(){
		log.info("提现明细生产者被注销...");
		producer.shutdown();
	}
	

	public DefaultMQProducer getProducer() {
		return producer;
	}



	public void setProducer(DefaultMQProducer producer) {
		this.producer = producer;
	}



	public Message getMsg() {
		return msg;
	}



	public void setMsg(Message msg) {
		this.msg = msg;
	}



	public String getwithdrawDetailtopic() {
		return withdrawDetailtopic;
	}



	public void setwithdrawDetailtopic(String withdrawDetailtopic) {
		this.withdrawDetailtopic = withdrawDetailtopic;
	}



	public String getwithdrawDetailtags() {
		return withdrawDetailtags;
	}



	public void setwithdrawDetailtags(String withdrawDetailtags) {
		this.withdrawDetailtags = withdrawDetailtags;
	}

}
