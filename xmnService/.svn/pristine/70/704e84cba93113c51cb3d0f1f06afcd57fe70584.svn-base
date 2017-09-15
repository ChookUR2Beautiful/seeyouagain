package com.xmniao.xmn.core.common.rocketmq;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.service.MqConsmueSaasService;
import com.xmniao.xmn.core.live.service.MqConsmueRecordService;

/**
 * MQ消息队列监听
* @ClassName: Consumer
* @Description: 
* @author hkun
* @date 2016年9月3日 下午3:45:41
*
 */
public class ConsumerSaasServiceImpl {
	/**
    * 初始日志类
    */
   private Logger log = LoggerFactory.getLogger(ConsumerSaasServiceImpl.class);
   
   /**
    * 初始化生成消费连接
    */
   private DefaultMQPushConsumer consumerConnect;
   
   /**
    * 队列消费记录操作服务
    */
   @Autowired
   private MqConsmueSaasService mqConsmueSaasService;
   
   /**
    * 队列服务器地址
    */
   private String namesrvAddr;
   
   /**
    * 消息主题
    */
   private String topicName;
   
   /**
    * 消息所属tag
    */
   private String topicTag;
   
   /**
    * 消息所属group
    */
   private String consumerSaasGroup;
   
   /**
   * @Description: TODO(消息监听注册)
   * @return void    返回类型
   * @throws
    */
   public void init(){
	   log.info("Mq消息队列监听器ConsumerSaasServiceImpl开始注册初始化！");
	   consumerConnect = new DefaultMQPushConsumer();
       try {
       	consumerConnect.setNamesrvAddr(namesrvAddr);
       	consumerConnect.setConsumerGroup(consumerSaasGroup);
       	consumerConnect.subscribe(topicName,"userRegister_tag");
       	consumerConnect.setMessageModel(MessageModel.CLUSTERING);
       	consumerConnect.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
       	consumerConnect.registerMessageListener(new MessageListenerConcurrently() {  
               public ConsumeConcurrentlyStatus consumeMessage(  
                       List<MessageExt> list,  
                       ConsumeConcurrentlyContext Context) {  
            	   //开始消费
            	   Message msg = list.get(0);  
                   String message="";
                   try {
                	   message = new String(msg.getBody(),"UTF-8");
                   } catch (Exception e) {
                	   log.error("ConsumerServiceImpl message转换异常:{}", e.toString());
                	   e.printStackTrace();
                   }
                   log.info("ConsumerServiceImpl received message={},msgKey={}", message,msg.getKeys());
                   
                   BaseResponse response = new BaseResponse();
                   //指定消息主题、消息标签实现消息处理过滤
                   if(topicName.equals(msg.getTopic())){
                	   
                	   //用户注册 赠送各种福利
                	   if("userRegister_tag".equals(msg.getTags())){
	                       response = mqConsmueSaasService.pushNewUserRegisterGift(message);
	                       //没有查到消费记录则开始消费处理并新增消费记录
                	   }
                   }
                   return (ResponseCode.SUCCESS==response.getState())?ConsumeConcurrentlyStatus.CONSUME_SUCCESS:ConsumeConcurrentlyStatus.RECONSUME_LATER;  
               }  
           });
       	consumerConnect.start();
       }catch (MQClientException e){
    	   e.printStackTrace();
           log.error("ConsumerServiceImpl MQClientException:{}", e.toString());
       }catch (Exception ex){
    	   ex.printStackTrace();
           log.error("ConsumerServiceImpl Exception:{}", ex.toString());
       }
       log.info("Mq消息队列监听器ConsumerServiceImpl注册成功！");
   }
   
   /**
    * 关闭消费者
    */
   public void shutdown(){
   	if(consumerConnect!=null){
   		consumerConnect.shutdown();
   	}
   }
   
	public String getNamesrvAddr() {
		return namesrvAddr;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}
	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicTag() {
		return topicTag;
	}

	public void setTopicTag(String topicTag) {
		this.topicTag = topicTag;
	}

	public String getConsumerSaasGroup() {
		return consumerSaasGroup;
	}

	public void setConsumerSaasGroup(String consumerSaasGroup) {
		this.consumerSaasGroup = consumerSaasGroup;
	}   
	
}
