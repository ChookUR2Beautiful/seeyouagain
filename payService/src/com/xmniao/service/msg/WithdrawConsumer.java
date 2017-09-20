package com.xmniao.service.msg;

import java.util.Date;
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
import com.xmniao.common.OSUtil;
import com.xmniao.common.PayConstants;
import com.xmniao.entity.MQMsgConfig;
import com.xmniao.service.CommonService;
import com.xmniao.service.pay.WithdrawMoneyServiceImpl;
import com.alibaba.rocketmq.common.message.MessageExt;

public class WithdrawConsumer {

	/** 日志记录 */
	private final Logger log = Logger.getLogger(WithdrawConsumer.class);
	/** 消息服务 消费者 配置 */
	private Map<String, Object> configMaps = new HashMap<String, Object>();

	@Autowired
	private WithdrawMoneyServiceImpl withdrawMoneyServiceImpl;
	/** 公共服务 */
	@Autowired
	private CommonService commonService;
	/** 消息队列基本配置 */
	@Autowired
	private MQMsgConfig mqMsgConfig;
	/** 分账IP */
	@Autowired
	@Qualifier("IP_NUMBER")
	private String ledgerIP;
	/** 提现返回消息生产者 */
	@Autowired
	private WithdrawProducer withdrawProducer;

	public void init() throws MQClientException {

		log.info("消息消费者初始化成功...");

		/* 消费者组、消费者实例标识 */
		String consumer_ident = "";
		/* 主题 标识 */
		String topic = this.configMaps.get("topic") + "";

		/* 判断是否开启集群   得到标识*/
		if ("0".equals(mqMsgConfig.getCluster())) {
			consumer_ident = mqMsgConfig.getIdentityPay();
			topic += mqMsgConfig.getIdentityLedger();
		} else {
			consumer_ident = OSUtil.getLocalIP_();
			topic += ledgerIP.replace(".", "_");
		}

		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
		// 配置消费组
		consumer.setConsumerGroup(this.configMaps.get("consumerGroup")+ consumer_ident);
		// 配置地址
		consumer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		// 配置消费者
		consumer.setInstanceName(this.configMaps.get("consumerInstanceName")+ consumer_ident);
		// 配置监听主题、标签
		consumer.subscribe(topic + "", this.configMaps.get("subExpression")+ "");
		// 配置消费模式
		consumer.setConsumeFromWhere(mqMsgConfig.getConsumeFromWhere());
		consumer.setConsumeThreadMax(NumberUtils.toInt(this.configMaps.get("consumeThreadMax") + "", 1));
		consumer.setConsumeThreadMin(NumberUtils.toInt(this.configMaps.get("consumeThreadMin") + "", 1));

		log.info("提现消费者配置： "
		        + "  group:" + consumer.getConsumerGroup()
				+ "  instgance:" + consumer.getInstanceName() 
				+ "  topic:"+ topic);
		
		// 配置消息监听
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                //提现返回消息map
				Map<String, String> sendMap = new HashMap<String, String>();
				//提现结果map
				Map<String, String> resultMap = null;
				String sts = "";//提现返回状态
				try {
					MessageExt msg = msgs.get(0);
					int times = msg.getReconsumeTimes();
					//设置重消费时间级别     1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
					log.info("消息消费次数 ： "+(times+1));
					log.info("消息延迟级别 : "+5*(times+1));
					// 1m 6m 20m 2h 2h
					context.setDelayLevelWhenNextConsume(5*(times+1));
					
					// 消息内容
					String message = new String(msg.getBody(), "utf-8");
				
					log.info(new Date() +"queryId:"+msg.getQueueId()+",消息ID:"+msg.getMsgId()+ ",提现消息：" + message);
					//提现参数map
					Map<String, String> paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>() {});
                    //此消息的消费次数
				
                    // 提现打款入口
					try {
						resultMap = withdrawMoneyServiceImpl.headWithrawMoney(
								paramMap.get("orderNumber"),
								Integer.valueOf(paramMap.get("status")),
								Integer.valueOf(paramMap.get("userType")),
								paramMap.get("withdrawType"));
						
						
					} catch (Exception e) {
						resultMap = new HashMap<String,String>();
						resultMap.put("orderNumber", paramMap.get("orderNumber"));
						resultMap.put("userType", paramMap.get("userType"));
						resultMap.put("status", PayConstants.WITHDRAW_STATUS_ERROR);
						resultMap.put("msg", PayConstants.WITHDRAW_MSG_ERROR);
					}
					
					if(resultMap.get("status")!=null && resultMap.get("status").equals("WITHDRAW_STATUS_INVALID")){
						log.info("该提现申请是重复消费的申请提现消费，直接舍弃！");
					}else{
						String status = resultMap.get("status");
	                    // 属于程序错误、连接超时等问题   消息重消费
						if ((status.equals(PayConstants.WITHDRAW_STATUS_ERROR) && times < PayConstants.MESSAGE_RECONSUMER_TIMES)) {

							return ConsumeConcurrentlyStatus.RECONSUME_LATER;

						}
	                    // 次数大于等于5 并 提现还是失败  更改提现状态
						if(status.equals(PayConstants.WITHDRAW_STATUS_ERROR) && times>=PayConstants.MESSAGE_RECONSUMER_TIMES){
							sts = PayConstants.WITHDRAW_RETURN_STATUS_FAIL;
							 try {
								withdrawMoneyServiceImpl.updateWithdrawState(
										        paramMap.get("orderNumber"),
										        Integer.valueOf(PayConstants.WITHDRAW_STATUS_FAIL), 
										        Integer.valueOf(paramMap.get("userType")), 
										        paramMap.get("withdrawType"),
											    "",resultMap.get("msg"));
							} catch (Exception e) {
								log.error("更新提现记录状态异常",e);
							}
						}
						//数据库状态 跟 返回状态 不一致
	                    if(status.equals(PayConstants.WITHDRAW_STATUS_SUCCESS)){
	                    	sts = PayConstants.WITHDRAW_RETURN_STATUS_SUCCESS;
						}else if(status.equals(PayConstants.WITHDRAW_STATUS_FAIL)){
							sts = PayConstants.WITHDRAW_RETURN_STATUS_FAIL;
						}else if(status.equals(PayConstants.WITHDRAW_STATUS_PROCESS)){
							sts = PayConstants.WITHDRAW_RETURN_STATUS_PROCESS;
						}
						//发送提现返回消息
						try {
							sendMap.put("orderNumber", resultMap.get("orderNumber"));
							sendMap.put("status", sts);
							sendMap.put("message", resultMap.get("msg"));
							sendMap.put("platform_code", "");
							sendMap.put("userType", resultMap.get("userType"));
							withdrawProducer.withdrawSendMsg(sendMap);

						} catch (Exception e) {
							log.error("withdrawSendMsg 提现返回信息发送异常", e);
						}
					}


				} catch (Exception e) {
					log.error("提现出现异常", e);
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
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

	public WithdrawConsumer() throws MQClientException {
		log.info("创建提现消费者成功");
	}

	public WithdrawConsumer(Map<String, Object> configMaps) {
		super();
		this.configMaps = configMaps;
	}

}
