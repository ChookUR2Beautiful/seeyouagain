package com.xmniao.service.msg.manor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xmniao.common.OSUtil;
import com.xmniao.entity.MQMsgConfig;
import com.xmniao.service.impl.RedisLockService;
import com.xmniao.service.impl.manor.ManorMQConsumeService;

/**
 * 庄园分账消息消费者
 * 
 * @author ChenBo
 * 
 */

public class ManorConsumer {

	/** 日志记录 */
	private final Logger log = Logger.getLogger(ManorConsumer.class);
	/** 消息服务 消费者 配置 */
	private Map<String, Object> configMaps = new HashMap<String, Object>();

	@Autowired
	private ManorMQConsumeService manorMQConsumeService;
	
	@Autowired
	private RedisLockService redisLockService;
	
	/** 消息队列基本配置 */
	@Autowired
	private MQMsgConfig mqMsgConfig;
	
	/** 分账IP */
	@Autowired
	@Qualifier("BUSINESS_IP_NUMBER")
	private String busineServiceIP;

	private String manorKey = "payService:manor:uid_";
	
	public void init() throws MQClientException {

		log.info("庄园消费者初始化成功...");

		/* 消费者组、消费者实例标识 */
		String consumer_ident = "";
		/* 主题 标识 */
		String topic = this.configMaps.get("topic") + "";

		/* 判断是否开启集群 */
		if ("0".equals(mqMsgConfig.getCluster())) {
			consumer_ident = mqMsgConfig.getIdentityPay();
			topic += mqMsgConfig.getIdentityLedger();
		} else {
			consumer_ident = OSUtil.getLocalIP_();
			topic += busineServiceIP.replace(".", "_");
		}

		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
		// 配置消费组
		consumer.setConsumerGroup(this.configMaps.get("consumerGroup")
				+ consumer_ident);
		// 配置地址
		consumer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		// 配置消费者
		consumer.setInstanceName(this.configMaps.get("consumerInstanceName")
				+ consumer_ident+"_"+OSUtil.getPid());
		// 配置监听主题、标签
		consumer.subscribe(topic + "", this.configMaps.get("subExpression")
				+ "");
		// 配置消费模式
		consumer.setConsumeFromWhere(mqMsgConfig.getConsumeFromWhere());
		consumer.setConsumeThreadMax(NumberUtils.toInt(
				this.configMaps.get("consumeThreadMax") + "", 20));
		consumer.setConsumeThreadMin(NumberUtils.toInt(
				this.configMaps.get("consumeThreadMin") + "", 5));

		log.info("庄园消费者配置： " + "  group:" + consumer.getConsumerGroup()
				+ "  instgance:" + consumer.getInstanceName() + "  topic:"
				+ topic + " tag:" + this.configMaps.get("subExpression"));
		// 配置消息监听
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

				Map<String, String> paramMap = null;
				// 设置重消费时间级别 5：1m
				context.setDelayLevelWhenNextConsume(5);
				// 消息bean主体
				MessageExt msg = msgs.get(0);

				// 消息内容
				String message = new String(msg.getBody());
				log.info(msg.getMsgId() + "    庄园消息：" + message);
				// json 字符串转list
				paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>() {});
				
				String tag=msg.getTags();
				boolean islock =false;
				String key = manorKey+paramMap.get("uid");
				try{
					islock = redisLockService.getLock(manorKey+paramMap.get("uid"), 3600000);
					if(islock){
						if(tag.equals("manor_energy_tag") ){
							// 累计能量
							return manorMQConsumeService.consumeEnergyMQ(msg);
						}else if(tag.equals("manor_nectar_tag") ){
							//累计花蜜
							return manorMQConsumeService.consumeDailyNectarMQ(msg);
						}
					}else{
						log.error("等待超时。。。");
						throw new Exception("等待锁超时");
					}
				} catch (Exception e) {
					log.error("消费出现异常", e);
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				}finally{
					if(islock){
						redisLockService.releaseLock(key);
					}
				} 
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		//Consumer对象在使用之前必须要调用start初始化，初始化一次即可
		consumer.start();
	}

	public Map<String, Object> getConfigMaps() {
		return configMaps;
	}

	public void setConfigMaps(Map<String, Object> configMaps) {
		this.configMaps = configMaps;
	}

	public ManorConsumer() throws MQClientException {
	}

	public ManorConsumer(Map<String, Object> configMaps) {
		super();
		this.configMaps = configMaps;
	}
}
