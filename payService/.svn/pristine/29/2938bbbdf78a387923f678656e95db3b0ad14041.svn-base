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

public class WalletProducer {
	
	/** 日志记录 */
	private final Logger log = Logger.getLogger(WalletProducer.class);
	/** 提现消息生产者 */
	private DefaultMQProducer producer;
	/** 提现消息生产主题 */
	private String updateWallettopic;
	/** 提现消息生产标签 */
	private String updateWalletTags;
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
		updateWallettopic += producer_ident;
		
		/* 生产者组名 */
		producer.setProducerGroup(mqMsgConfig.getProducerGroup()+producer_ident+"_wu");
		/* 生产者实例名 */
		producer.setInstanceName(mqMsgConfig.getProducerInstance()+producer_ident);  
		/* 消息服务器地址 */
		producer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		try {
			producer.start();
		} catch (MQClientException e) {
		  log.error("更新钱包消息生产者初始化异常", e);
		}
		
        log.info("更新钱包消息生产配置：   "
        		+"   group:"+producer.getProducerGroup()
        		+"   instance:"+producer.getInstanceName()
        		+"   topic:"+updateWallettopic
        		+"   tag:"+updateWalletTags);
     // 消息
		msg = new Message();
		msg.setTopic(updateWallettopic);
//		msg.setTags(updateWalletTags);
	}
	

	
	/**
	 * 发送更新钱包返回消息
	 * @param paramMap
	 *           orderNumber
	 *           status
	 *           message   
	 *           platform_code
	 *           userType
	 */
	public void sendMsg(Map<String, String> paramMap,int flag)
			throws UnsupportedEncodingException, MQClientException,
			RemotingException, MQBrokerException, InterruptedException {
		
		String[] tags = updateWalletTags.split(",");
		
		msg.setTags(tags[flag]);//设置标签
		
		String body = JSON.toJSONString(paramMap); // 消息主体
		msg.setKeys("uw_1_"+paramMap.get("uid"));//key = tx_1_ + orderNumber
		msg.setBody(body.getBytes("UTF-8"));
        //发送消息
		SendResult sendResult = producer.send(msg);
		log.info("成功发送更新钱包返回信息:" + body);
		log.info(sendResult);
	}
	
	/**
	 * 注销生产者
	 */
	public void shutdown(){
		log.info("更新钱包生产者被注销...");
		producer.shutdown();
	}



	public String getUpdateWallettopic() {
		return updateWallettopic;
	}



	public void setUpdateWallettopic(String updateWallettopic) {
		this.updateWallettopic = updateWallettopic;
	}



	public String getUpdateWalletTags() {
		return updateWalletTags;
	}



	public void setUpdateWalletTags(String updateWalletTags) {
		this.updateWalletTags = updateWalletTags;
	}
	

}
