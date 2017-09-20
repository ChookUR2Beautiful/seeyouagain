package com.xmniao.service.msg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xmniao.common.PayConstants;
import com.xmniao.exception.RepetitionLedgerException;
import com.xmniao.service.LedgerService;
import com.xmniao.thrift.ledger.FailureException;

/**
 * 分账消息监听
 * 
 * @author YangJing
 *
 */
@Service
public class LedgerMessageListener implements MessageListenerConcurrently {

	/** 日志记录 */
	private final Logger log = Logger.getLogger(LedgerMessageListener.class);
	
	/** 分账返回消息生产者 */
	@Autowired
	private LedgerProducer ledgerProducer;
	
	/** 分账服务 */
	@Autowired
	private LedgerService ledgerService;

	/**
	 * 消费分账消息
	 */
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

		Map<String, String> resultMap = null;
		Map<String, String> paramMap = null;
		int times = 0;
		int errorState = 0;
		String errorMsg = "";
		
		try {
			// 休眠1000ms
			Thread.sleep(1000);

			// 设置重消费时间级别 5：1m
			context.setDelayLevelWhenNextConsume(5);
			// 消息bean主体
			MessageExt msg = msgs.get(0);
			String tag=msg.getTags();
				try{
				// 消息内容
				String message = new String(msg.getBody(), "utf-8");
				log.info(msg.getMsgId() + "    分账消息：" + message);
				// json 字符串转list
				paramMap = JSON.parseObject(message,
						new TypeReference<Map<String, String>>() {
						});
				// 获取已经重复处理的次数
				times = msg.getReconsumeTimes();
				resultMap= new HashMap<String,String>();
				if(tag.equals("saas_ledger_push") ){
					// saas分账
					paramMap.put("ledgerText", "saas_ledger");
					resultMap = ledgerService.doSaasLedger(paramMap);
				}else if(tag.equals("saas_ledger_prepush") ){
					// saas立即分账部分
					paramMap.put("ledgerText", "pre_saas_ledger");
					resultMap = ledgerService.doSaasLedger(paramMap);
				}else if(tag.equals("divide_pay")){
					// 分账
					resultMap = ledgerService.doXmnLedger(paramMap);
				}else if(tag.equals("incr_ledger_push")){
					resultMap = ledgerService.doMallLedger(paramMap);
					resultMap.put("dealType", paramMap.get("dealType"));
				}else if(tag.equals("package_ledger_push")){
					resultMap =ledgerService.doPackageLeger(paramMap);
				}
	
				if (resultMap == null) {
					log.info("程序异常，发起重试");
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				}
				
				if(resultMap != null){
					resultMap.put("bid", String.valueOf(paramMap.get("orderId")));
					log.info("bid:"+String.valueOf(paramMap.get("orderId")));
				}
				sendMsg(tag,resultMap);
	
			} catch (FailureException e) {
				log.error("分账出现异常", e);
	
				errorState = e.state;
				errorMsg = e.info;
	
				// 判断分账次数
				if (errorState == 3
						&& times < PayConstants.LEDGER_MESSAGE_RECONSUMER_TIMES) {
	
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				} else {
					// 超过重试次数返回失败消息
					Map<String, String> rMap = new HashMap<String, String>();
					rMap.put("bid", String.valueOf(paramMap.get("orderId")));
					rMap.put("code", String.valueOf(errorState));
					rMap.put("remark", errorMsg);
					if(tag.equals("incr_ledger_push")){
						resultMap.put("dealType", paramMap.get("dealType"));
					}
					sendMsg(tag,rMap);
				}
			} 
		}catch(RepetitionLedgerException e){
			log.error("订单重复分账:",e);
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		catch (Exception e) {
			log.error("分账出现异常", e);
			return ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}

		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
	
	/**
	 * 返回消息状态
	 * 
	 * @param resultMap
	 */
	private void sendMsg(String revMsgTag,Map<String, String> resultMap) {
		try {
			ledgerProducer.ledgerSendMsg(revMsgTag,resultMap);
		} catch (Exception e) {
			log.error("ledgerSendMsg 分账返回信息发送异常", e);
		}
	}

}
