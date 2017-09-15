package com.xmniao.xmn.core.common.rocketmq.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;

/**
 * 实例化消息生产实例
 * @author Administrator
 *
 */
public class ProducerFactory {
	
	private static Logger log= LoggerFactory.getLogger(ProducerFactory.class);
	
    /**
     * 缓存topic producer
     */
    public static Map<String,DefaultMQProducer> topicMap = new HashMap<String,DefaultMQProducer>();

	private ProducerFactory(){};
	
	public static ProducerFactory getProducerSingleten(){
		return CreateProducer.producerInstance;
	}
	
	static class CreateProducer{
		private static ProducerFactory producerInstance = new ProducerFactory();
	}
	
	/**
	 * 初始化producer
	 */
	 public static DefaultMQProducer init(TopicInfo topicInfo){
		 DefaultMQProducer producer = new DefaultMQProducer(topicInfo.getTopicGroup());
	     producer.setNamesrvAddr(topicInfo.getNamesrvAddr());
	     try {
			producer.start();
			topicMap.put(topicInfo.getTopicName(), producer);
		} catch (MQClientException e) {
			log.error("启动producer topicInfo="+topicInfo.toString()+"异常:"+e.toString());
		}
	     return producer;
	 }
	
	/**
	 * 获取producer
	 * @param topicInfo
	 */
	public static DefaultMQProducer getProducer(TopicInfo topicInfo){
		if(topicMap==null||topicMap.get(topicInfo.getTopicName())==null){
			init(topicInfo);
		}
		return topicMap.get(topicInfo.getTopicName());
	}
}
