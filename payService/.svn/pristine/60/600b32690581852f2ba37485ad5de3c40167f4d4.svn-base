package com.xmniao.service.msg;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.mail.Email;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
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
import com.xmniao.common.MailSender;
import com.xmniao.common.OSUtil;
import com.xmniao.common.PayConstants;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilException;
import com.xmniao.dao.ErrorLogMapper;
import com.xmniao.entity.MQMsgConfig;
import com.xmniao.entity.MQMsgHeader;
import com.xmniao.service.WalletService;
import com.xmniao.thrift.ledger.FailureException;

/**
 * 修改用户钱包余额消息 消费者
 * @author ChenBo
 *
 */
public class UpdateBalanceConsumer {
	private final Logger log = Logger.getLogger(UpdateBalanceConsumer.class);
		
	@Autowired
	private UpdateBalanceCallbackProducer ubcProducer;

    /** 消息队列基本配置 */
	@Autowired
	private MQMsgConfig mqMsgConfig;
	
	/** 分账IP*/
	@Autowired 
	@Qualifier("IP_NUMBER")
	private String ledgerIP;
	
	/** 消息服务 消费者 配置    */
	private Map<String, Object> configMaps = new HashMap<String, Object>();
	
	@Autowired
	private ErrorLogMapper errorLogMapper;
	
	@Autowired
	private WalletService walletService;
	
	public void init() throws MQClientException{
		log.info("修改钱包余额消息消费者初始化成功...");
		
		log.info(this.configMaps.get("msgHeader"));
		@SuppressWarnings("unchecked")
		List<MQMsgHeader> headerList = (List<MQMsgHeader>) this.configMaps.get("msgHeader");
		for(MQMsgHeader header:headerList){
			log.info("topic:"+header.getTopic()+",header:"+header.getTags());
		}
		/* 消费者组、消费者实例标识 */
		String consumer_ident = "";
		
		/* 判断是否开启集群 */
		if("0".equals(mqMsgConfig.getCluster())){
			consumer_ident = mqMsgConfig.getIdentityPay();
			for(MQMsgHeader header:headerList){
				header.setTopic(header.getTopic()+mqMsgConfig.getIdentityLedger());
			}
		}else{
			consumer_ident = OSUtil.getLocalIP_();
			
			for(MQMsgHeader header:headerList){
				header.setTopic(header.getTopic()+ledgerIP.replace(".", "_"));
			}
		}
		
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
		//配置消费组
		consumer.setConsumerGroup(this.configMaps.get("consumerGroup") + consumer_ident);
		//配置地址
		consumer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		//配置消费者
		consumer.setInstanceName(this.configMaps.get("consumerInstanceName")+ consumer_ident);
        //配置监听主题、标签
		for(MQMsgHeader header:headerList){
			consumer.subscribe(header.getTopic(), header.getTags()+"");
		}

		//配置消费模式
		consumer.setConsumeFromWhere(mqMsgConfig.getConsumeFromWhere());
		consumer.setConsumeThreadMax(NumberUtils.toInt(this.configMaps.get("consumeThreadMax")+"",1));
		consumer.setConsumeThreadMin(NumberUtils.toInt(this.configMaps.get("consumeThreadMin")+"",1));
		
		StringBuffer msgHeader = new StringBuffer("");
		for(MQMsgHeader header:headerList){
			msgHeader.append("[topic:"+header.getTopic()+",tags:"+header.getTags()+"]");
		}
		log.info("修改钱包余额消费者配置： "
				+"  group:"+consumer.getConsumerGroup()
				+"  instgance:"+consumer.getInstanceName()
				+"  "+msgHeader.toString());
		
		//配置消息监听
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				
				try {
					
					// 休眠3000ms
					Thread.sleep(3000);
					
					//设置重消费时间级别           5：1m
					context.setDelayLevelWhenNextConsume(5);
					
					// 消息bean主体
					MessageExt msg = msgs.get(0); 
					// 消息内容
					String message = new String(msg.getBody(), "utf-8"); 
										
                    //json 字符串转list
					Map<String, String> paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>() {});

					log.info("支付系统-消费者 接受修改钱包余额消息：" + JSON.toJSONString(paramMap)+",TheardId:"+Thread.currentThread().getId());
					
					String errorInfo;
					// 处理修改钱包余额
					Map<String,String> rMap = new HashMap<String,String>();
					try{
						rMap = updateBalance(paramMap);
						errorInfo=rMap.get("msg");
					}catch (Exception e){
						log.error("发送补贴失败。。。",e);
						rMap.put("orderId", paramMap.get("orderId"));
						rMap.put("rType", paramMap.get("rType"));
						rMap.put("state", "-1");
						rMap.put("msg", e.getMessage());
						errorInfo="修改钱包余额失败\r\n"+UtilException.getExceptionInformation1(e);
					}
					
					//此消息的消费次数
					int times = msg.getReconsumeTimes();
					String status = rMap.get("state");
                    // 属于程序错误、连接超时等问题   消息重消费
					if (status.equals("-1") && times < PayConstants.MESSAGE_RECONSUMER_TIMES) {
						log.error("本次修改钱包金额不成功，这是第"+(times+1)+"次修改");
						
						 // 次数大于等于3 并 提现还是失败  更改提现状态
						if((times+1)>=PayConstants.MESSAGE_RECONSUMER_TIMES){
							log.error("重试了"+PayConstants.MESSAGE_RECONSUMER_TIMES+"次后，仍然执行不成功。");
						}else{
							return ConsumeConcurrentlyStatus.RECONSUME_LATER;
						}
					}
					if(status.equals("-1")){
						//将状态改为1
						rMap.put("sate", "1");
					}
					log.info("callBackMsg:"+JSON.toJSONString(rMap));
					if(msg.getTopic().contains(configMaps.get("HDtopic")+"") && msg.getTags().contains(configMaps.get("HDsubExpression")+"")){
						log.info("活动-平台发放补贴");
						//将错误信息保存到err表中。
						if(!rMap.get("state").equals("0")){
						Map<String,String> errorMap = new HashMap<String,String>();
						errorMap.put("paramStr", message);
						errorMap.put("errorLog", errorInfo);
						errorMap.put("errorDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						System.out.println("保存的错误日志："+errorMap);
						errorLogMapper.insertErrorLog(errorMap);
						
						//发送错误日志邮件件
						//sendErrorMail("订单:"+paramMap.get("orderId")+"补贴发放失败","错误信息："+errorMap.toString());
						}
					}else{
						log.info("补贴-补贴发放");
						
						//发送回调消息
						ubcProducer.sendMsg(rMap);
					}

				} catch (Exception e) {
					log.error("修改钱包余额出现异常", e);
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				} 
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 接收消息成功
			}
		});

		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 */
		consumer.start();

	}
	
	
	public Map<String, String> updateBalance(
			Map<String, String> paramMap) throws FailureException,
			TException {
		log.info("[updateBalance] paramMap:" + paramMap);

		int status = 0;
		String msg = "";

		// 返回参数
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("orderId", paramMap.get("orderId"));
		returnMap.put("rType", paramMap.get("rType"));
		
		String uId = paramMap.get("uId");

		Map<String, Object> walletMap = new HashMap<String, Object>();
		walletMap.put("account", paramMap.get("phoneNumber"));
		walletMap.put("uId", uId);
		walletMap.put("userType", paramMap.get("userType"));
		walletMap.put("amount", BigDecimal.valueOf(Double.valueOf(paramMap
				.get("amount") == null ? "0" : paramMap.get("amount"))));
		walletMap.put("balance", BigDecimal.valueOf(Double.valueOf(paramMap
				.get("balance") == null ? "0" : paramMap.get("balance"))));
		walletMap.put("commision", BigDecimal.valueOf(Double
				.valueOf(paramMap.get("commision") == null ? "0" : paramMap
						.get("commision"))));
		walletMap.put("zbalance", BigDecimal.valueOf(Double
				.valueOf(paramMap.get("zbalance") == null ? "0" : paramMap
						.get("zbalance"))));
		walletMap.put("integral", BigDecimal.valueOf(Double
				.valueOf(paramMap.get("integral") == null ? "0" : paramMap
						.get("integral"))));
		walletMap.put("seller_amount", BigDecimal.valueOf(Double
				.valueOf(paramMap.get("sellerAmount") == null ? "0"
						: paramMap.get("sellerAmount"))));
		walletMap.put("rtype", Integer.valueOf(paramMap.get("rType")));
		walletMap.put("remarks", paramMap.get("orderId"));
		if (paramMap.get("remark") != null) {
			walletMap.put("description", paramMap.get("remark"));
		}
		if((walletMap.get("amount")!=null && ((BigDecimal)walletMap.get("amount")).compareTo(BigDecimal.ZERO)!=0)
			|| (walletMap.get("balance")!=null&& ((BigDecimal)walletMap.get("balance")).compareTo(BigDecimal.ZERO)!=0)
			|| (walletMap.get("commison")!=null &&((BigDecimal)walletMap.get("commison")).compareTo(BigDecimal.ZERO)!=0)
			|| (walletMap.get("seller_amount")!=null && ((BigDecimal)walletMap.get("seller_amount")).compareTo(BigDecimal.ZERO)!=0)
			|| (walletMap.get("zbalance")!=null && ((BigDecimal)walletMap.get("zbalance")).compareTo(BigDecimal.ZERO)!=0)){
			log.error("疑似问题记录，walletMap:"+walletMap);
			returnMap.put("code", StateCodeUtil.PR_SUCCESS);
			returnMap.put("state", "0");
			returnMap.put("msg", msg);
			return returnMap;
		}
		Map<String, String> resultMap = walletService
				.updateWalletAmount(walletMap);

		if (resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)) {
			status = 0;
			msg = "修改成功";
		}else if (resultMap.get("code").equals(
				StateCodeUtil.PR_REFUND_FAIL)) {
			status = -1;
			msg = resultMap.get("Msg");
			log.error("修改失败原因:"+msg);
		} else {
			status = 1;
			msg = resultMap.get("Msg");
			log.error("修改失败原因:"+msg);
		}

		returnMap.put("state", status + "");
		returnMap.put("msg", msg);
		return returnMap;
	}


	public UpdateBalanceConsumer(Map<String, Object> configMaps) {
		super();
		this.configMaps = configMaps;
	}


	public UpdateBalanceConsumer() {
		super();
	}
	
	private static int sendErrorMail(String subject,String msg){
		try{
		Email email = new MailSender().getSimpleEmail();
		email.setSubject(subject);
		email.setMsg(msg);
		email.send();
		}
		catch(Exception e){
			//log.error("发送邮件失败",e);
			System.out.println("发送邮件失败");
		}
		return 0;
	}
	
}
