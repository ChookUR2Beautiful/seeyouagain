package com.xmniao.service.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.xmniao.common.PayConstants;
import com.xmniao.dao.ExpensesMpper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.entity.MQMsgConfig;
import com.xmniao.service.WalletService;
import com.xmniao.service.WithdrawService;

/**
 * 平台退款(打款失败退款到余额)
 * 消息队列
 * @author Administrator
 *
 */
public class ReturnWithdrawalsConsumer {
	
	 //初始日志类
	private final Logger log = Logger.getLogger(ReturnWithdrawalsConsumer.class);
	
	private String produceKey;
	
	@Autowired
	private ReturnWithdrawalsProducer rwProducer;

    /** 消息队列基本配置 */
	@Autowired
	private MQMsgConfig mqMsgConfig;
	
	/** 分账IP*/
	@Autowired 
	@Qualifier("IP_NUMBER")
	private String ledgerIP;
	
	@Autowired
	private WithdrawService withdrawService;
	
	/** 消息服务 消费者 配置    */
	private Map<String, Object> configMaps = new HashMap<String, Object>();
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	public UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;
	
	@Autowired
	private ExpensesMpper expensesMpper;
		
	public void init() throws MQClientException{
		log.info("平台退款消息消费者初始化成功...");
		
		
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
		consumer.subscribe(topic + "",this.configMaps.get("subExpression") + "");
		//配置消费模式
		consumer.setConsumeFromWhere(mqMsgConfig.getConsumeFromWhere());
		consumer.setConsumeThreadMax(NumberUtils.toInt(this.configMaps.get("consumeThreadMax")+"",1));
		consumer.setConsumeThreadMin(NumberUtils.toInt(this.configMaps.get("consumeThreadMin")+"",1));
		log.info("平台退款消费者配置： "
				+"  group:"+consumer.getConsumerGroup()
				+"  instgance:"+consumer.getInstanceName()
				+"  topic:"+topic
				+"  tags:"+this.configMaps.get("subExpression"));
		
		//配置消息监听
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				
				try {
					//设置重消费时间级别           5：1m
					context.setDelayLevelWhenNextConsume(5);
					// 消息bean主体
					MessageExt msg = msgs.get(0); 
					// 消息内容
					String message = new String(msg.getBody(), "utf-8"); 
                    //json 字符串转list
					Map<String, String> paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>() {});

					log.info("支付系统-消费者 接受平台退款/提现退回消息：" + JSON.toJSONString(paramMap)+",TheardId:"+Thread.currentThread().getId());
					
					//此消息的消费次数
					int times = msg.getReconsumeTimes();
					// 处理平台退款
					Map<String,String> rMap = new HashMap<String,String>();
					
					String type = paramMap.get("type");
					if(type==null || type.equals("1")){
						try{
							rMap = withdrawService.returnWithdrawals(paramMap);
						}catch (Exception e){
							log.error("平台退款失败。。。",e);
							rMap.put("wId", paramMap.get("wId"));
							rMap.put("wType", paramMap.get("wType"));
							rMap.put("state", "1");
							rMap.put("msg", e.getMessage());
						}
					}else if(type!=null && type.equals("2")){
						try{
							rMap = withdrawService.cancelWithdrawals(paramMap);
						}catch (Exception e){
							log.error("提现退回失败。。。",e);
							rMap.put("wId", paramMap.get("wId"));
							rMap.put("wType", paramMap.get("wType"));
							rMap.put("state", "1");
							rMap.put("msg", e.getMessage());
						}
					}
					
					String status = rMap.get("state");
                    // 属于修改钱包状态异常，重新消费
					if (status.equals("-1") && times < PayConstants.MESSAGE_RECONSUMER_TIMES) {
						log.error("平台退款未成功，这是第"+(times+1)+"次修改");
						
						 // 次数大于等于3 并 提现还是失败  更改提现状态
						if((times+1)>=PayConstants.MESSAGE_RECONSUMER_TIMES){
							log.error("重试了"+PayConstants.MESSAGE_RECONSUMER_TIMES+"次后，仍然执行不成功。。。");
						}else{
							return ConsumeConcurrentlyStatus.RECONSUME_LATER;
						}
					}
					if (status.equals("-1")){
						//将状态变为1
						rMap.put("state", "1");
					}
					rMap.put("type", type==null?"1":type);
					rwProducer.sendMsg(rMap);

				} catch (Exception e) {
					log.error("平台退款出现异常", e);
				} 
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 接收消息成功
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

	public ReturnWithdrawalsConsumer() throws MQClientException {
	}

	public ReturnWithdrawalsConsumer(Map<String, Object> configMaps) {
		super();
		this.configMaps = configMaps;
	}

	public String getProduceKey() {
		return produceKey;
	}

	public void setProduceKey(String produceKey) {
		this.produceKey = produceKey;
	}
	
}

