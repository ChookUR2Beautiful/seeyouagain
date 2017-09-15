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
import com.xmniao.xmn.core.live.service.MqConsmueRecordService;

/**
 * MQ消息队列监听
* @ClassName: Consumer
* @Description: 
* @author hkun
* @date 2016年9月3日 下午3:45:41
*
 */
public class ConsumerServiceImpl {
	/**
    * 初始日志类
    */
   private Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);
   
   /**
    * 初始化生成消费连接
    */
   private DefaultMQPushConsumer consumerConnect;
   
   /**
    * 队列消费记录操作服务
    */
   @Autowired
   private MqConsmueRecordService mqConsmueRecordService;
   
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
   * @Description: TODO(消息监听注册)
   * @return void    返回类型
   * @throws
    */
   public void init(){
	   log.info("Mq消息队列监听器ConsumerServiceImpl开始注册初始化！");
	   consumerConnect = new DefaultMQPushConsumer();
       try {
       	consumerConnect.setNamesrvAddr(namesrvAddr);
       	consumerConnect.setConsumerGroup(consumerGroup);
       	consumerConnect.subscribe(topicName,"gift_tag||StartRoom_tag||send_birdEgg_tag");
       	consumerConnect.setMessageModel(MessageModel.CLUSTERING);
       	consumerConnect.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
       	consumerConnect.registerMessageListener(new MessageListenerConcurrently() {  
               public ConsumeConcurrentlyStatus consumeMessage( List<MessageExt> list, ConsumeConcurrentlyContext Context) {  
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
                	   
                	   if("gift_tag".equals(msg.getTags())){
	                       response = mqConsmueRecordService.updateConsumeRecord(message);
	                       //没有查到消费记录则开始消费处理并新增消费记录
                	   }
                	   if("StartRoom_tag".equals(msg.getTags())){//开启直播发送推送消息
                		   response=mqConsmueRecordService.PushLiveMessage(message);
                	   }
                	   if ("send_birdEgg_tag".equals(msg.getTags())) {//当天首次观看直播 送观众礼物
                		   log.info("----------------------------------------------------------------");
                		   response = mqConsmueRecordService.pushLiveSendBirdEgg(message);
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

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}   
	
//	public static void main(String[] args){
//		ApplicationContext context = new FileSystemXmlApplicationContext("D:/Workspaces/MyEclipse Professional 2014/xmnService/src/java/com/xmniao/xmn/resource/config/spring-context.xml");
//		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/com.xmniao.xmn.resource.config.spring-context.xml");
//		BaseResponse response = new BaseResponse();
//		String message = "{'uid':584609,'gift_id':2,'gift_type':2,'gift_price':10,'gift_bag_id':1}";
//        //指定消息主题、消息标签实现消息处理过滤
//		MqConsmueRecordService mqConsmueRecordService = (MqConsmueRecordService)context.getBean("mqConsmueRecordService");
//        response = mqConsmueRecordService.updateConsumeRecord(message);
//        System.out.println(response);
//	}
}
