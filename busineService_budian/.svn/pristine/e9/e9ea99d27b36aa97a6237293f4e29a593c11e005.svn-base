package com.xmniao.service.manor;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.*;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xmniao.domain.manor.ManorFlowerMqBean;
import com.xmniao.domain.manor.ManorFlowerRelationRecord;
import com.xmniao.domain.manor.ManorInfoRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

import static com.xmniao.service.manor.ManorConstant.*;

/**
 *
 * Created by yang.qiang on 2017/6/14.
 */
public class ManorMqRegister {
    private final Logger logger = LoggerFactory.getLogger(ManorMqRegister.class);


    @Autowired
    private String manorBusineTopic;
    @Autowired
    private String manorPlantTag;
    @Autowired
    private String manorInsertInitNodeTag;
    @Autowired
    private String manorMigrateChainTag;
    @Autowired
    private String manorUpdateFlowerTypeTag;
    @Autowired
    private String manorUpdateLocationForManorInfoTag;

    @Autowired
    private String namesrvAddr;
    @Autowired
    private ManorMqConsumer manorMqConsumer;
    @Autowired
    private ManorLogService manorLogService;
    @Autowired
    private FlowerService flowerService;

    private String consumerGroup;
    @Autowired
    private ManorInfoService manorInfoService;

    public void init(){
        consumeInitNode();
        consumePlantMessage();
        consumeUpdateFlowerType();
        consumeMigrateChain();
        consumeUpdateLocationForManorInfo();
    }

    /** 消费迁移关系链 */
    private void consumeMigrateChain() {

        logger.info("注册[迁移关系链队列监听器]");

        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setConsumerGroup(consumerGroup+"_migrate");
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.subscribe(manorBusineTopic, manorMigrateChainTag);

            // 程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                    consumeOrderlyContext.setAutoCommit(true);
                    String messageBody = new String(list.get(0).getBody());
//                    ManorFlowerRelation flowerInfo = JSON.parseObject(messageBody, ManorFlowerRelation.class);

                    ManorFlowerMqBean flowerInfo = JSON.parseObject(messageBody, ManorFlowerMqBean.class);
                    HashMap<String, Object> logMap = new HashMap<>();
                    logMap.put("MQ消息体:",flowerInfo);
                    logger.info("迁移花朵关系链"+JSON.toJSONString(flowerInfo));

                    try {
//                        manorMqConsumer.migrateFlowerChain(flowerInfo);
                        flowerService.migrateBranch(flowerInfo.getParentUid(),flowerInfo.getLocation(),flowerInfo.getSubUid());
                    } catch (Exception e) {
                        logger.error("迁移花朵关系链失败!",e);

                        logMap.put("错误信息:",e);

                        // 记录失败日志
                        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                                flowerInfo.getTransNo(),MANOR_FLOWER_LOG_TYPE_MIGRATE,flowerInfo.getUid(),null,JSON.toJSONString(logMap),
                                MANOR_FLOWER_LOG_STATE_FAILURE
                        ));
                    }

                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            logger.error("注册监听器失败");
        }

    }

    /** 消费更新初始节点花朵类型 */
    private void consumeUpdateFlowerType() {
        logger.info("注册[更新初始界面花朵类型队列监听器]");

        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setConsumerGroup(consumerGroup+"_update");
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.subscribe(manorBusineTopic, manorUpdateFlowerTypeTag);

            // 程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                    consumeOrderlyContext.setAutoCommit(true);
                    String messageBody = new String(list.get(0).getBody());


//                    ManorFlowerRelation flowerInfo = JSON.parseObject(messageBody, ManorFlowerRelation.class);

                    ManorFlowerMqBean flowerInfo = JSON.parseObject(messageBody, ManorFlowerMqBean.class);
                    HashMap<String, Object> logMap = new HashMap<>();
                    logMap.put("MQ消息体:",flowerInfo);
                    logger.info("更新初始节点花类型"+JSON.toJSONString(flowerInfo));

                    try {
                        flowerService.updateFlowerTypeByUid(flowerInfo.getUid(),flowerInfo.getType());
//                        manorMqConsumer.updateInitNodeType(flowerInfo.getUid(),flowerInfo.getType());
                    } catch (Exception e) {
                        logger.error("更新初始节点类型失败!"+JSON.toJSONString(flowerInfo),e);



                        logMap.put("错误信息:",e);
                        // 记录失败日志
                        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                                flowerInfo.getTransNo(),MANOR_FLOWER_LOG_TYPE_UPDATE_TYPE,flowerInfo.getUid(),null,JSON.toJSONString(logMap),
                                MANOR_FLOWER_LOG_STATE_FAILURE
                        ));
//                        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                    }

                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            logger.error("注册监听器失败");
        }


    }

    /** 插入初始节点 */
    private void consumeInitNode() {
        logger.info("注册 [插入初始节点队列监听器]");

        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setConsumerGroup(consumerGroup+"_init");
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.subscribe(manorBusineTopic, manorInsertInitNodeTag);

            // 程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                    consumeOrderlyContext.setAutoCommit(true);
                    String messageBody = new String(list.get(0).getBody());
                    ManorFlowerMqBean flowerInfo = JSON.parseObject(messageBody, ManorFlowerMqBean.class);
                    logger.info("用户["+flowerInfo.getUid()+"]创建初始节点");
                    HashMap<String, Object> logMap = new HashMap<>();
                    logMap.put("MQ消息体",flowerInfo);

                    try {

                        flowerService.initBranchNode(flowerInfo.getUid());

//                        manorMqConsumer.insertInitNode(flowerInfo.getUid());
                    } catch (Exception e) {
                        logger.error("用户["+flowerInfo.getUid()+"]插入初始节点失败!",e);

                        logMap.put("错误信息:",e);

                        // 记录失败日志
                        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                                flowerInfo.getTransNo(),MANOR_FLOWER_LOG_TYPE_INIT_NODE,flowerInfo.getUid(),null,JSON.toJSONString(logMap),
                                MANOR_FLOWER_LOG_STATE_FAILURE
                        ));
                    }

                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            logger.error("注册监听器失败");
        }

    }


    /**
     * 消费种花消息
     */
    private void consumePlantMessage() {
        logger.info("初始化种花消息队列 监听器");
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setConsumerGroup(consumerGroup+"_plant");
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.subscribe(manorBusineTopic, manorPlantTag);
            // 程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            // 注册监听器
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                    MessageExt messageExt = list.get(0);
                    String messageBody = new String(messageExt.getBody());
                    logger.info("消费种植花朵mq topic="+manorBusineTopic+"  tag="+ manorPlantTag +" messageBody:" + messageBody);
                    ManorFlowerMqBean plantInfo = JSON.parseObject(messageBody, ManorFlowerMqBean.class);
                    HashMap<String, Object> logMap = new HashMap<>();
                    logMap.put("MQ消息体:",plantInfo);
                    try {

                        // 插入花朵关系链
                        flowerService.plantFlower(plantInfo.getUid(),plantInfo.getLocation(),plantInfo.getFlowers());

                    } catch (Exception e) {
                        logger.error("处理种植花朵MQ失败! messageBody:" + messageBody + " msgId:" + messageExt.getMsgId(), e);


                        logMap.put("错误信息:",e);
                        // 记录失败日志
                        manorLogService.logFlowerRelationRecord(new ManorFlowerRelationRecord(
                                plantInfo.getTransNo(),MANOR_FLOWER_LOG_TYPE_PLANT,plantInfo.getUid(),plantInfo.getFlowers().size(),JSON.toJSONString(logMap),
                                MANOR_FLOWER_LOG_STATE_FAILURE
                        ));


                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;

                    }
                    // 提交
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                }
            });


            consumer.start();

        } catch (Exception e) {
            logger.error("初始化种花消息队列 监听器失败",e);
        }
    }



    /** 消费更新初始节点花朵类型 */
    private void consumeUpdateLocationForManorInfo() {
        logger.info("注册[更新初始界面花朵类型队列监听器]");

        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
            consumer.setConsumerGroup(consumerGroup+"_updateLocation");
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.subscribe(manorBusineTopic, manorUpdateLocationForManorInfoTag);

            // 程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                    consumeOrderlyContext.setAutoCommit(true);
                    String messageBody = new String(list.get(0).getBody());

                    ManorFlowerMqBean mqInfo = JSON.parseObject(messageBody, ManorFlowerMqBean.class);
                    HashMap<String, Object> logMap = new HashMap<>();
                    logMap.put("MQ消息体:",mqInfo);
                    logger.info("更新初始节点花类型"+JSON.toJSONString(mqInfo));

                    try {

                        manorInfoService.updateLocationForBursRelation(mqInfo.getSubUid(),mqInfo.getLocation());
                    } catch (Exception e) {
                        logger.error("更新初始节点类型失败!"+JSON.toJSONString(mqInfo),e);

                        logMap.put("错误信息:",e);
                        // 记录失败日志
                        manorLogService.logManorInfoRecord(new ManorInfoRecord(null,
                                MANOR_INFO_OPERATE_TYPE_UPDATE_LOCATION,null,mqInfo.getSubUid(),
                                JSON.toJSONString(logMap)));
                    }

                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            logger.error("注册监听器失败");
        }


    }






    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }
}
