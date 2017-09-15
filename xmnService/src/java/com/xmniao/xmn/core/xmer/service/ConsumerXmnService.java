package com.xmniao.xmn.core.xmer.service;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xmniao.xmn.core.verification.service.BillService;

/**
 * 分账消费者实现处理类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class ConsumerXmnService
{
//    /**
//     * 初始日志类
//     */
//    private Logger log = LoggerFactory.getLogger(ConsumerXmnService.class);
//    
//   
//    /**
//     * 注入消费者连接
//     */
//    @Autowired
//    private DefaultMQPushConsumer xmnConsumConnection;
//    
//    /**
//     * 订单支付成功消费者主题
//     */
//    @Autowired
//    private String topic_payorder;
//    
//    
//    /**
//     * 提现返回消费者主题
//     */
//    @Autowired
//    private String topic_callback;
//    
//    /**
//     * 注入 押金缴纳明细标签Tags
//     */
//    @Autowired
//    private String deposit_pay;
//    
//    /**
//     * 订单流水记录标签Tags
//     */
//    @Autowired
//    private String bill_record;
//    
//    /**
//     * 商品销售分账标签Tags
//     */
//    @Autowired
//    private String divide_order;
//    
//    /**
//     * 提现记录标签Tags
//     */
//    @Autowired
//    private String wallet_withdraw;
//    
//    
//    /**
//     * 注入 押金返还明细标签Tags
//     */
//    @Autowired
//    private String deposit_return;
//   
//    /**
//     * 订单相关Service
//     */
//    @Autowired
//    private BillService billService;
//    /**
//     * 提现相关
//     */
//    @Autowired
//    private WithDrawCashService withDrawCashService;
//    
//    /**
//     * 寻蜜客信息Service
//     */
//    @Autowired
//    private XmerInfoService xmerInfoService;
//    
//    
//    
//    /**
//     * 消费者监听初始化方法
//     * @return void [返回类型说明]
//     * @exception throws [违例类型] [违例说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @PostConstruct		//初始化时执行
//    public void init()
//    {
//        try
//        {
//            //处理消费者消息
//            DefaultMQPushConsumer ledgerConsumer = new DefaultMQPushConsumer();
//            
//            ledgerConsumer.setConsumerGroup(xmnConsumConnection.getConsumerGroup());
//            ledgerConsumer.setNamesrvAddr(xmnConsumConnection.getNamesrvAddr());
//            ledgerConsumer.setInstanceName(xmnConsumConnection.getInstanceName());
//            ledgerConsumer.subscribe(topic_payorder, deposit_pay+"||"+bill_record+"||"+divide_order);
//            ledgerConsumer.subscribe(topic_callback, wallet_withdraw+"||"+deposit_return);
//            ledgerConsumer.setConsumeFromWhere(xmnConsumConnection.getConsumeFromWhere());
//            ledgerConsumer.registerMessageListener(new MessageListenerConcurrently()
//            {
//                @Override
//                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context)
//                {
//                	try {
//                		MessageExt msg = msgs.get(0);
//                		
//                		
//                		
//                     /*   //购买套餐支付成功MQ消息
//                        if (msg.getTopic().equals(topic_payorder) && msg.getTags().equals(deposit_pay)){
//                        	depositListService.depositPayToRedis(new String(msg.getBody()));
//                        // deposit_pay 订单流水记录标签Tags
//                        }else if(msg.getTopic().equals(topic_payorder) && msg.getTags().equals(bill_record)){	
//                        	billService.setFlowRedis(new String(msg.getBody()));
//                        	//商品销售分账标签Tags
//                        }else if(msg.getTopic().equals(topic_payorder) && msg.getTags().equals(divide_order)){	
//                        	xmerInfoService.updateRedisXmerInfo(new String(msg.getBody()));
//                        	//提现记录标签Tags
//                        }else if(msg.getTopic().equals(topic_callback) && msg.getTags().equals(wallet_withdraw)){
//                        	withDrawCashService.setWithDrawCashRedis(new String(msg.getBody()));
//                        	//注入 押金返还明细标签Tags
//                        }else if(msg.getTopic().equals(topic_callback) && msg.getTags().equals(deposit_return)){	
//                        	try {
//    							depositListService.setDepositRedis(new String(msg.getBody()));
//    						} catch (ParseException e) {
//    							e.printStackTrace();
//    						}
//                        }*/
//					} catch (Exception e) {
//						e.printStackTrace();
//						log.error("RocketMQ消息处理失败:"+e.getMessage());
//					}
//                    
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//            });
//            ledgerConsumer.start();
//        }
//        catch (MQClientException e)
//        {
//            log.error("xmnConsumConnection MQClientException::", e);
//        }
//        catch (Exception ex)
//        {
//            log.error("xmnConsumConnection Exception::", ex);
//        }
//    }
    
    
}
