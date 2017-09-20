package com.xmniao.service.msg;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.xmniao.common.OSUtil;
import com.xmniao.entity.MQMsgConfig;
import com.xmniao.service.LedgerService;

/**
 * 分账消息消费者
 * 
 * @author YangJing
 * 
 */
public class LedgerConsumer {

	/** 日志记录 */
	private final Logger log = Logger.getLogger(LedgerConsumer.class);
	/** 消息服务 消费者 配置 */
	private Map<String, Object> configMaps = new HashMap<String, Object>();

	@Autowired
	private LedgerService ledgerService;

	/** 消息队列基本配置 */
	@Autowired
	private MQMsgConfig mqMsgConfig;
	
	/** 分账消息监听 */
	@Autowired
	private LedgerMessageListener ledgerMessageListener;

	/** 分账IP */
	@Autowired
	@Qualifier("IP_NUMBER")
	private String ledgerIP;
	
	@Autowired
	@Qualifier("BUSINESS_IP_NUMBER")
	private String businessIP;
	
	/** 分账返回消息生产者 */
	@Autowired
	private LedgerProducer ledgerProducer;

	public void init() throws MQClientException {
		
		if ("0".equals(mqMsgConfig.getCluster())) {
			topicByIp(ledgerIP);
		}else{
			topicByIp(ledgerIP);
			topicByIp(businessIP);
		}
		
	}

	private void topicByIp(String ledgerIP2) throws MQClientException {
		log.info("消息消费者初始化成功...");

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
			topic += ledgerIP2.replace(".", "_");
		}

		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
		// 配置消费组
		consumer.setConsumerGroup(this.configMaps.get("consumerGroup")+consumer_ident+
				(businessIP.equals(ledgerIP2)?"_"+ledgerIP2.replace(".", "_"):""));
		// 配置地址
		consumer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		// 配置消费者
		consumer.setInstanceName(this.configMaps.get("consumerInstanceName")+consumer_ident);
		// 配置监听主题、标签
		consumer.subscribe(topic + "", this.configMaps.get("subExpression")
				+ "");
		// 配置消费模式
		consumer.setConsumeFromWhere(mqMsgConfig.getConsumeFromWhere());
		consumer.setConsumeThreadMax(NumberUtils.toInt(
				this.configMaps.get("consumeThreadMax") + "", 1));
		consumer.setConsumeThreadMin(NumberUtils.toInt(
				this.configMaps.get("consumeThreadMin") + "", 1));

		log.info("分账消费者配置： " + "  group:" + consumer.getConsumerGroup()
				+ "  instgance:" + consumer.getInstanceName() + "  topic:"
				+ topic + " tag:" + this.configMaps.get("subExpression"));
		// 配置消息监听
		consumer.registerMessageListener(ledgerMessageListener);

		//Consumer对象在使用之前必须要调用start初始化，初始化一次即可
		consumer.start();
	}

	public Map<String, Object> getConfigMaps() {
		return configMaps;
	}

	public void setConfigMaps(Map<String, Object> configMaps) {
		this.configMaps = configMaps;
	}

	public LedgerConsumer() throws MQClientException {
		log.info("创建分账服务消息消费者成功");
	}

	public LedgerConsumer(Map<String, Object> configMaps) {
		super();
		this.configMaps = configMaps;
	}
}
