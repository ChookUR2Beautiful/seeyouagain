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
import com.xmniao.xmn.core.common.service.MqConsmueRobotService;

/**
 * MQ消息队列监听
* @ClassName: Consumer
* @Description: 
* @author hkun
* @date 2016年9月3日 下午3:45:41
*
 */
public class ConsumerRobotServiceImpl {
	/**
    * 初始日志类
    */
   private Logger log = LoggerFactory.getLogger(ConsumerRobotServiceImpl.class);
   
   /**
    * 初始化生成消费连接
    */
   private DefaultMQPushConsumer consumerConnect;
   
   /**
    * 队列消费记录操作服务
    */
   @Autowired
   private MqConsmueRobotService mqConsmueRobotService;
   
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
   private String consumerGroup;
   
   /**
   * @Description: (消息监听注册)
   * @return void    返回类型
   * @throws
    */
   public void init(){
	   log.info("Mq消息队列监听器ConsumerSaasServiceImpl开始注册初始化！");
	   consumerConnect = new DefaultMQPushConsumer();
       try {
       	consumerConnect.setNamesrvAddr(namesrvAddr);
       	consumerConnect.setConsumerGroup(consumerGroup);
       	consumerConnect.subscribe(topicName,"robotAction_tag||robotEntryRoomMsg_tag");
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
                	   log.error("ConsumerRobotServiceImpl message转换异常:{}", e.toString());
                	   e.printStackTrace();
                   }
                   log.info("ConsumerRobotServiceImpl received message={},msgKey={}", message,msg.getKeys());
                   
                   BaseResponse response = new BaseResponse();
                   //指定消息主题、消息标签实现消息处理过滤
                   if(topicName.equals(msg.getTopic())){
                	   
                	   if("robotAction_tag".equals(msg.getTags())){
                		   //机器人开始动作，发礼物，留言
	                       response = mqConsmueRobotService.robotAction(message);
                	   }
                	   if("robotEntryRoomMsg_tag".equals(msg.getTags())){
                		   //机器人进入直播间， 先提示进场，后行动
                		   response = mqConsmueRobotService.robotEntryRoomMsg(message);
                		   response = mqConsmueRobotService.robotAction(message);
                	   }
                   }
                   return (ResponseCode.SUCCESS==response.getState())?ConsumeConcurrentlyStatus.CONSUME_SUCCESS:ConsumeConcurrentlyStatus.RECONSUME_LATER;  
               }  
           });
       	consumerConnect.start();
       }catch (MQClientException e){
    	   e.printStackTrace();
           log.error("ConsumerRobotServiceImpl MQClientException:{}", e.toString());
       }catch (Exception ex){
    	   ex.printStackTrace();
           log.error("ConsumerRobotServiceImpl Exception:{}", ex.toString());
       }
       log.info("Mq消息队列监听器ConsumerRobotServiceImpl注册成功！");
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

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}   
	
}
