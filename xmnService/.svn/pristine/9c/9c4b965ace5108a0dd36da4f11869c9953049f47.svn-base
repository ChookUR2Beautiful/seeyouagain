package com.xmniao.xmn.core.common.rocketmq;


import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.xmn.core.common.rocketmq.factory.ProducerFactory;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;

/**
 * 生产者，初始化MQProducer(初始化不依赖spring)
 */
@Component("producerServiceImpl")
public class ProducerServiceImpl {
	
	//日志
	private final Logger log = LoggerFactory.getLogger(ProducerServiceImpl.class);
	
    /**
     * 发送消息
     * @param TopicInfo 消息信息
     * @param T message 消息数据
     * @return
     * @throws InterruptedException 
     * @throws MQBrokerException 
     * @throws RemotingException 
     * @throws MQClientException 
     * @throws UnsupportedEncodingException 
     */
    public <T> SendResult send(TopicInfo topicInfo,T message) throws MQClientException, RemotingException, MQBrokerException, InterruptedException, UnsupportedEncodingException {
    	
    	
    	String key = UUID.randomUUID().toString();
    	byte[] dataMessage = ((String) message).getBytes("UTF-8");
    	Message mq_message = new Message(topicInfo.getTopicName(),topicInfo.getTopicTag(),key,dataMessage);
		ProducerFactory factory = ProducerFactory.getProducerSingleten();
		
		log.info("===========================消息发送至消息队列==================================");
		
		SendResult result = factory.getProducer(topicInfo).send(mq_message);
		log.info("Producer has sended message!key={},result={},message={}",new Object[]{key,result,mq_message});
		return result;
    }
}