package com.xmniao.service.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xmniao.common.OSUtil;
import com.xmniao.entity.MQMsgConfig;
import com.xmniao.service.TipService;

public class TipPushConsumer {

	/** 日志记录    */
	private final Logger log = Logger.getLogger(TipPushConsumer.class);
	/** 消息服务 消费者 配置    */
	private Map<String, Object> configMaps = new HashMap<String, Object>();
	/** 打赏服务   */
	@Autowired
	private TipService tipService;
	
	@Autowired
	private MQMsgConfig mqMsgConfig;
	
	@Autowired 
	@Qualifier("IP_NUMBER")
	private String ledgerIP;
	

	public void init() throws MQClientException {
		
		log.info("消息消费者初始化成功...");
		/* 消费者组、消费者实例标识 */
		String consumer_ident = "";
		/* 主题  标识 */
		String topic = this.configMaps.get("topic")+"";
		
		/* 判断是否开启集群 */
		if("0".equals(mqMsgConfig.getCluster())){
			consumer_ident = mqMsgConfig.getIdentityPay();
			topic += mqMsgConfig.getIdentityLedger();
		}else{
			consumer_ident = OSUtil.getLocalIP_();
			topic += ledgerIP.replace(".", "_");
		}
		
		
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
		//配置消费组
		consumer.setConsumerGroup(this.configMaps.get("consumerGroup") + consumer_ident);
		//配置地址
		consumer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		//配置消费者
		consumer.setInstanceName(this.configMaps.get("consumerInstanceName")+ consumer_ident);
        //配置监听主题、标签
		consumer.subscribe(topic,this.configMaps.get("subExpression") + "");
		//配置消费模式
		consumer.setConsumeFromWhere(mqMsgConfig.getConsumeFromWhere());
		consumer.setConsumeThreadMax(NumberUtils.toInt(this.configMaps.get("consumeThreadMax")+"",1));
		consumer.setConsumeThreadMin(NumberUtils.toInt(this.configMaps.get("consumeThreadMin")+"",1));
		
		log.info("打赏消费者配置： "
		+"  group:"+consumer.getConsumerGroup()
		+"  instgance:"+consumer.getInstanceName()
		+"  topic:"+topic);
		
		//配置消息监听
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			@SuppressWarnings("finally")
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				
				log.info("msgs:   "+msgs);
				try {
					// 消息bean主体
					MessageExt msg = msgs.get(0); 
					// 消息内容
					String message = new String(msg.getBody(), "utf-8"); 
					log.info("接受打赏消息：" + message);
					Map<String, String> paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>() {});

					// 处理打赏
					tipService.tip(paramMap);

				} catch (Exception e) {
					log.error("打赏出现异常", e);
				} finally {
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 接收消息成功
				}
			}
		});

		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 */
		consumer.start();
	}

	public Map<String, Object> getConfigMaps() {
		return configMaps;
	}

	public void setConfigMaps(Map<String, Object> configMaps) {
		this.configMaps = configMaps;
	}

	public TipPushConsumer() throws MQClientException {
		log.info("创建分账服务消息消费者成功");
	}

	public TipPushConsumer(Map<String, Object> configMaps) {
		super();
		this.configMaps = configMaps;
	}

}
