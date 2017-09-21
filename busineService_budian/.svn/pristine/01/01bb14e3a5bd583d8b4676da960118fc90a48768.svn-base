package com.xmniao.service.ledger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.order.BillBargainDao;
import com.xmniao.dao.order.BillFreshSubDao;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.dao.xmer.SaasOrderDao;
import com.xmniao.domain.live.LiveLedgerRecord;
import com.xmniao.domain.order.BillBargain;
import com.xmniao.domain.order.BillFreshSub;
import com.xmniao.service.live.LiveOrderServiceImpl;
import com.xmniao.service.manor.ManorEarningService;
import com.xmniao.service.order.OrderServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xmniao.service.xmer.SaasOrderServiceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 分账消费者实现处理类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LedgerConsumerImpl
{
    /**
     * 初始日志类
     */
    private Logger log = LoggerFactory.getLogger(LedgerConsumerImpl.class);
    
    /**
     * 注入消费订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 注入SAAS订单DAO层
     */
    @Autowired
    private SaasOrderDao saasOrderDao;
    
    /**
     * 注入特价菜订单DAO层
     */
    @Autowired
    private BillBargainDao billBargainDao;
    
    /**
     * 注入生鲜订单DAO层
     */
    @Autowired
    private BillFreshSubDao billFreshSubDao;
    
    /**
     * 注入消费者连接
     */
    private DefaultMQPushConsumer ledgerConsumConnection;
    
    /**
     * 注入分账消费者主题Topic
     */
    private String ledgerTopic;
    
    /**
     * 注入分账消费者标签Tags
     */
    private String ledgerTags;
    
    /**
     * 注入分账消费者主题Topic
     */
    private String liveLedgerReturnbackTopic;
    
    /**
     * 注入分账消费者主题Topic
     */
    private String liveRecommendLedgerReturnbackTags;
    
    /**
     * 注入分账消费者主题Topic
     */
    private String liveDividendsLedgerReturnbackTags;
    
    /**
     * 注入分账消费者主题Topic
     */
    private String liveDividendsLedgerV2ReturnbackTags;
    
    /**
     * 注入分账消费者主题Topic
     */
    private String liveDividendsLedgerV3ReturnbackTags;
    
    /**
     * 注入黄金庄园消费者主题Topic
     */
    private String manorPayTopic;
    private String maibaoTopic = "ec_abutment_test";
    
    private String maibaoTags = "ec_saas_order_test";
    
    /**
     * 注入黄金庄园花蜜消费者主题Tags
     */
    private String manorNectarReturnbackTag;
    
    /**
     * 注入黄金庄园赠送花蜜消费者主题Tags
     */
    private String manorGiftNectarReturnbackTag;    
    
    /**
     * 注入订单服务模块实现处理类
     */
    private OrderServiceImpl orderServiceImpl;
    
    @Autowired
    private LiveOrderServiceImpl liveOrderService;
    
    @Autowired
    private ManorEarningService manorEarningService;
    
    @Autowired
    private SaasOrderServiceImpl saasOrderService;
    
    
    // 1 消费分账 3 SAAS分账  4.积分商城分账 
    private final String XMN_ORDER="1";
    private final String SAAS_ORDER="3";
    private final String MALL_ORDER="4";
    private final String OFFLINE_ORDER="5";
    
    /**
     * 消费者监听初始化方法
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void init()
    {
        try
        {
            //处理消费者消息
            DefaultMQPushConsumer ledgerConsumer = new DefaultMQPushConsumer();
            
            ledgerConsumer.setConsumerGroup(ledgerConsumConnection.getConsumerGroup());
            ledgerConsumer.setNamesrvAddr(ledgerConsumConnection.getNamesrvAddr());
            ledgerConsumer.setInstanceName(ledgerConsumConnection.getInstanceName());
            ledgerConsumer.subscribe(maibaoTopic, maibaoTags);
            ledgerConsumer.subscribe(ledgerTopic, ledgerTags);
            ledgerConsumer.subscribe(liveLedgerReturnbackTopic, liveRecommendLedgerReturnbackTags+" || "+liveDividendsLedgerReturnbackTags+"|| "+liveDividendsLedgerV2ReturnbackTags+" || "+liveDividendsLedgerV3ReturnbackTags);
            ledgerConsumer.subscribe(manorPayTopic, manorNectarReturnbackTag +" || "+manorGiftNectarReturnbackTag);
            ledgerConsumer.setConsumeFromWhere(ledgerConsumConnection.getConsumeFromWhere());
            ledgerConsumer.registerMessageListener(new MessageListenerConcurrently(){
                @Override
                // msgs中只收集同一个topic，同一个tag，并且key相同的message  
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context)
                {
                    
                    MessageExt msg = msgs.get(0);
                    log.info(Thread.currentThread().getName()+"接收到msg{topic:"+msg.getTopic()+",tags:"+msg.getTags()+",keys:"+msg.getKeys()+",body:"+new String(msg.getBody())+",msgId:"+new String(msg.getMsgId())+"}");
                    if (msg.getTopic().equals(ledgerTopic) && msg.getTags().equals(ledgerTags))
                    {
                        log.info("ledgerConsumer Topic::" + msg.getTopic());
                        log.info("ledgerConsumer Keys::" + msg.getKeys());
                        log.info("ledgerConsumer Tags::" + msg.getTags());
                        //获取到消息内容
                        String reBody = new String(msg.getBody());
                        log.info("接收到的信息："+reBody);
                        //转换为JSONObject
                        JSONObject reJsonBody = JSONObject.parseObject(reBody);
                        try{
                        // orderType:订单类型     1 消费分账 3 SAAS分账 4 销售分账  
                        if(XMN_ORDER.equals(reJsonBody.getString("orderType"))){
                        	//根据获取消息内容中的订单号查询订单信息
                            Map<String, Object> resOrderMap = orderDao.selectBillAll(reJsonBody.getString("orderid"));
                            
                            Map<String, String> processReqMap = new HashMap<String, String>();
                            //当传递的后台处理状态为其它状态时,照样进行更新为MAP中的数据
                            processReqMap.put("bid",reJsonBody.getString("orderid"));
                            processReqMap.put("hstatus",reJsonBody.getString("hstatus"));
                            
                            if ("9".equals(reJsonBody.getString("hstatus")))
                            {
                            	if(resOrderMap.get("hstatus")!=null && (int)resOrderMap.get("hstatus") ==9){
                            		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                            	}
                                processReqMap.put("status", "2");
                                processReqMap.put("isaccount", "1");
                                
                                processReqMap.put("fdate", DateUtil.getCurrentTimeStr());
                                
                                //调用合作商员工提成记录处理方法
                                percentageRecordProcess(resOrderMap);
                                
                                //订单记录处理接口
                                Map<String, String> reqMap = new HashMap<String, String>();
                                reqMap.put("bid", reJsonBody.getString("orderid"));
                                reqMap.put("status", "8");
                                reqMap.put("explains","订单号" + reJsonBody.getString("orderid") + "已分账（分账系统）成功");
                                orderServiceImpl.insertBillRecord(reqMap);
                                
                                //推送消息类型为2,即分账成功
                                String ledgerPushType = "2";
                                //分账成功,调用推送消息发redis队列处理方法
                                orderServiceImpl.pushMsgRedisProcess(resOrderMap,ledgerPushType);
                            }else{
                            	if(resOrderMap.get("hstatus") != null && (int)resOrderMap.get("hstatus") ==9){
                            		log.info("已分账成功订单不再修改分账状态");
                            		log.info("订单分账回调消息，处理成功!");
                            		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                            	}
                            }
                            orderDao.modifyOrderProcess(processReqMap);
                        }else if(SAAS_ORDER.equals(reJsonBody.getString("orderType"))){

                        	//根据获取消息内容中的订单号查询订单信息
                            Map<String, Object> resOrderMap = saasOrderDao.getSaasSoldOrderById(reJsonBody.getString("orderid"));
                            
                            Map<String, String> processReqMap = new HashMap<String, String>();
                            //当传递的后台处理状态为其它状态时,照样进行更新为MAP中的数据
                            processReqMap.put("ordersn",reJsonBody.getString("orderid"));
                            processReqMap.put("hstatus",reJsonBody.getString("hstatus"));
                            processReqMap.put("udate", DateUtil.getCurrentTimeStr());
                            if ("9".equals(reJsonBody.getString("hstatus")))
                            {
                                //processReqMap.put("status", "2");
                                //processReqMap.put("isaccount", "1");
                                
                               
                                
                                //订单记录处理接口
                                //Map<String, String> reqMap = new HashMap<String, String>();
                                //reqMap.put("bid", reJsonBody.getString("orderid"));
                                //reqMap.put("status", "8");
                                //reqMap.put("explains","订单号" + reJsonBody.getString("orderid") + "已分账（分账系统）成功");
                                //orderServiceImpl.insertBillRecord(reqMap);
                                
                                //推送消息类型为2,即分账成功
                                //String ledgerPushType = "2";
                                //分账成功,调用推送消息发redis队列处理方法
                                //orderServiceImpl.pushMsgRedisProcess(resOrderMap,ledgerPushType);
                            }else{
                            	if(resOrderMap.get("hstatus") != null && (int)resOrderMap.get("hstatus") ==9){
                            		log.info("已分账成功订单不再修改分账状态");
                            		log.info("订单分账回调消息，处理成功!");
                            		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                            	}
                            }
                            saasOrderDao.updateSaasSoldOrder(processReqMap);          
                        	
                        }else if(MALL_ORDER.equals(reJsonBody.getString("orderType"))){
                          //  if(reJsonBody.getString("dealType").equals("1")){//线上积分商品订单
                            	
                            	
                            	//根据获取消息内容中的订单号查询订单信息
                        		BillFreshSub subBill = billFreshSubDao.getSubBillInfo(reJsonBody.getString("orderid"));
                                
                                BillFreshSub mdyBillFresh = new BillFreshSub();
                               
                                //当传递的后台处理状态为其它状态时,照样进行更新为MAP中的数据
                                mdyBillFresh.setHstatus(Integer.parseInt(reJsonBody.getString("hstatus")));
                                mdyBillFresh.setSubOrderSn(reJsonBody.getString("orderid"));
                                mdyBillFresh.setModifyTime(new Date());
                                if ("9".equals(reJsonBody.getString("hstatus")))
                                {
//                                    processReqMap.put("status", "2");
//                                    processReqMap.put("isaccount", "1");
                                    
                                    //SimpleDateFormat bfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                  //  String fdate = bfmt.format(new Date());
//                                    processReqMap.put("fdate", fdate);
                                    
                                    //调用合作商员工提成记录处理方法
//                                    percentageRecordProcess(resOrderMap);
                                    
                                    //订单记录处理接口
//                                    Map<String, String> reqMap = new HashMap<String, String>();
//                                    reqMap.put("bid", reJsonBody.getString("orderid"));
//                                    reqMap.put("status", "8");
//                                    reqMap.put("explains","订单号" + reJsonBody.getString("orderid") + "已分账（分账系统）成功");
//                                    orderServiceImpl.insertBillRecord(reqMap);
                                    
                                    //推送消息类型为2,即分账成功
                                    String ledgerPushType = "2";
                                    //分账成功,调用推送消息发redis队列处理方法
                                    //orderServiceImpl.pushMsgRedisProcess(resOrderMap,ledgerPushType);
                                }else{
                                	if(subBill.getHstatus() != null && subBill.getHstatus() ==9){
                                		log.info("已分账成功订单不再修改分账状态");
                                		log.info("订单分账回调消息，处理成功!");
                                		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                                	}
                                }
                                
                                billFreshSubDao.modifySubBillInfo(mdyBillFresh);
                                
                            } 
                        else if(OFFLINE_ORDER.equals(reJsonBody.getString("orderType"))){//线下积分商品订单
                                BillBargain mdyBillBargain = new BillBargain();
                               BillBargain billBargain = billBargainDao.getByBid(Long.parseLong(reJsonBody.getString("orderid")));
                                //当传递的后台处理状态为其它状态时,照样进行更新为MAP中的数据
                                mdyBillBargain.setHstatus(Integer.parseInt(reJsonBody.getString("hstatus")));
                                mdyBillBargain.setBid(Long.valueOf(reJsonBody.getString("orderid")));
                                
                                if ("9".equals(reJsonBody.getString("hstatus")))
                                {
//                                    processReqMap.put("status", "2");
//                                    processReqMap.put("isaccount", "1");
                                    
                                    SimpleDateFormat bfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String fdate = bfmt.format(new Date());
//                                    processReqMap.put("fdate", fdate);
                                    
                                    //调用合作商员工提成记录处理方法
//                                    percentageRecordProcess(resOrderMap);
                                    
                                    //订单记录处理接口
//                                    Map<String, String> reqMap = new HashMap<String, String>();
//                                    reqMap.put("bid", reJsonBody.getString("orderid"));
//                                    reqMap.put("status", "8");
//                                    reqMap.put("explains","订单号" + reJsonBody.getString("orderid") + "已分账（分账系统）成功");
//                                    orderServiceImpl.insertBillRecord(reqMap);
                                    
                                    //推送消息类型为2,即分账成功
                                    String ledgerPushType = "2";
                                    //分账成功,调用推送消息发redis队列处理方法
                                    //orderServiceImpl.pushMsgRedisProcess(resOrderMap,ledgerPushType);
                                }else{
                                	if(billBargain.getHstatus() != null && billBargain.getHstatus() ==9){
                                		log.info("已分账成功订单不再修改分账状态");
                                		log.info("订单分账回调消息，处理成功!");
                                		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                                	}
                                }
                                
                               int a = billBargainDao.modifyBillBargainInfo(mdyBillBargain);
                                log.info("线下积分订单【"+mdyBillBargain.getBid()+"】商品分账结束!"+a);
                            }

                            
                        	log.info("订单分账回调消息，处理成功!");
                        }catch(Exception e){
                        	log.error("处理分账回调异常！",e);
                        }
                        
                    }else if(msg.getTopic().equals(liveLedgerReturnbackTopic)){
                        log.info("ledgerConsumer Topic::" + msg.getTopic());
                        log.info("ledgerConsumer Keys::" + msg.getKeys());
                        log.info("ledgerConsumer Tags::" + msg.getTags());
                        //获取到消息内容
                        String reBody = new String(msg.getBody());
                        log.info("接收到直播分账返回信息："+reBody);
                        
                        try{
                             //转换为JSONObject
                             JSONObject reJsonBody = JSONObject.parseObject(reBody);
                             String recordid = reJsonBody.getString("id");
                             String status = reJsonBody.getString("status");//0 成功
                             if(StringUtils.isNotBlank(recordid) && StringUtils.isNotBlank(status)){
                            	 LiveLedgerRecord liveLedgerRecord = new LiveLedgerRecord();
                                 liveLedgerRecord.setId(Integer.parseInt(recordid));
                                 liveLedgerRecord.setStatus(Integer.parseInt(status)==0?2:3);
                                 liveOrderService.updateLiveLedgerRecord(liveLedgerRecord);
                             }else{
                            	 log.info("无效消息，舍弃！");
                             }
                        	 
                        }catch(Exception e){
                        	log.error("处理直播分账回调异常！",e);
                        }
                    }else if(msg.getTopic().equals(manorPayTopic)){
                        log.info("ledgerConsumer Topic::" + msg.getTopic());
                        log.info("ledgerConsumer Keys::" + msg.getKeys());
                        log.info("ledgerConsumer Tags::" + msg.getTags());
                        //获取到消息内容
                        String reBody = new String(msg.getBody());
                        log.info("接收到直播分账返回信息："+reBody);
                        
                        try{
                        	//转换为JSONObject
                            JSONObject reJsonBody = JSONObject.parseObject(reBody);
//                        	if(msg.getTags().equals(manorNectarReturnbackTag)){
//                        		String transNo = reJsonBody.getString("transNo");
//                                String status = reJsonBody.getString("status");//0 成功
//                                String description = reJsonBody.getString("description");
//                                if(StringUtils.isNotBlank(transNo) && StringUtils.isNotBlank(status)){
//                                	manorEarningService.updateEarningStatus(transNo,Integer.parseInt(status),description);
//                                }else{
//                               	 log.info("无效消息，舍弃！");
//                                }
//                        	}else if(msg.getTags().equals(manorGiftNectarReturnbackTag)){
//                        		String transNo = reJsonBody.getString("transNo");
//                                String uid = reJsonBody.getString("uid");//0 成功
//                                String number = reJsonBody.getString("number");
//                                if(StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(number)){
//                                	manorEarningService.updateManorCount(transNo,Integer.parseInt(uid),Integer.parseInt(number));
//                                }else{
//                               	 log.info("无效消息，舍弃！");
//                                }
//                        	}
                        	 
                        }catch(Exception e){
                        	log.error("处理黄金庄园回调消费异常！",e);
                        }
                    }else if(msg.getTopic().equals(maibaoTopic)){
                        log.info("ledgerConsumer Topic::" + msg.getTopic());
                        log.info("ledgerConsumer Keys::" + msg.getKeys());
                        log.info("ledgerConsumer Tags::" + msg.getTags());
                        //获取到消息内容
                        String reBody = new String(msg.getBody());
                        log.info("接收到EC报单请求："+reBody);
                        
                        try{
                             //转换为JSONObject
//                             ECAbutment ecAbutment = JSON.parseObject(reBody, ECAbutment.class);
//                             JSONObject os = null;
                        	 Map<String,String> reqMap = JSONObject.parseObject(reBody, new TypeReference<Map<String, String>>(){});
                             reqMap.put("rtype", "3");
                             reqMap.put("status","1");
                             reqMap.put("payType","10000001");
                             reqMap.put("payId","");
                             reqMap.put("payCode","");
                             reqMap.put("zdate",reqMap.get("createTime"));
                             reqMap.put("bid",reqMap.get("ordersn"));
                        	 saasOrderService.modifyXmerOrderInfo(reqMap);
                        	 
                        }catch(Exception e){
                        	log.error("处理EC报单消息异常！",e);
                        	return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            ledgerConsumer.start();
            //ledgerConsumer.subscribe(ledgerTopic, ledgerTags);
            log.info("分账消息【"+ledgerConsumer.getSubscription()+"】"+ledgerTopic+"-" +ledgerTags
            		+";"+liveLedgerReturnbackTopic+"-"+(liveRecommendLedgerReturnbackTags+" || "+liveDividendsLedgerReturnbackTags+"|| "+liveDividendsLedgerV2ReturnbackTags));

        }
        catch (MQClientException e)
        {
            log.error("ledgerConsumerProcess MQClientException::", e);
        }
        catch (Exception ex)
        {
            log.error("ledgerConsumerProcess Exception::", ex);
        }
    }
    
    /**
     * 合作商员工提成记录处理
     * @param paramMap [订单信息]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void percentageRecordProcess(Map<String, Object> paramMap)
    {
        log.info("addPercentageRecord paramMap start::" + paramMap);
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        try
        {
            //获取订单佣金,并转换为JSONObject对象
            JSONObject resObject = JSONObject.parseObject(paramMap.get("commission").toString());
            //获取合作商业务员信息的请求参数
            Map<String, Object> staffReqMap = new HashMap<String, Object>();
            staffReqMap.put("sellerId", paramMap.get("sellerid"));
            staffReqMap.put("genussellerId", paramMap.get("genussellerid"));
            staffReqMap.put("jointId", paramMap.get("jointid"));
            staffReqMap.put("consumeJointId", paramMap.get("consume_jointid"));
            //获取合作商的业务员信息
            Map<String, Object> staffIdMap = orderDao.selectSalesmanId(staffReqMap);
            //判断所属合作商是否为空
            if(paramMap.containsKey("jointid"))
            {
                //所属合作商ID
                String jointId = paramMap.get("jointid").toString();
                //获取所属合作商员工分账比例
                String scale = orderDao.queryPercentageInfo(jointId);
                //判断所属合作商员工的分账比例是否为空
                if(StringUtils.isNotEmpty(scale))
                {
                    Map<String, Object> beLongMap = new HashMap<String, Object>();
                    //所属合作商和员工
                    beLongMap.put("jointid", jointId);
                    beLongMap.put("staffid", staffIdMap.get("salesmanId"));
                    beLongMap.put("sellerid", paramMap.get("genussellerid"));
                    beLongMap.put("sellername", paramMap.get("genusname"));
                    beLongMap.put("payment",resObject.getBigDecimal("memberJointMoney"));
                    beLongMap.put("scale", scale);
                    //所属合作商佣金+所属合作商业务员收益
                    BigDecimal memberBpartnerAmount = resObject.getBigDecimal("bpartner_amount")
                            .add(resObject.getBigDecimal("memberJointMoney"));
                    beLongMap.put("bpartnerAmount", memberBpartnerAmount);
                    beLongMap.put("bid", paramMap.get("bid"));
                    beLongMap.put("money", paramMap.get("money"));
                    //ADD所属合作商和员工信息
                    resList.add(beLongMap);
                }
            }
            
            //判断消费合作商和所属合作商是否为空
            if (paramMap.containsKey("consume_jointid") && paramMap.containsKey("jointid"))
            {
                //判断所属合作商ID和消费合作商ID是否相同,若不相同,则将消费合作商和员工信息ADD到list集合中
                if(!paramMap.get("jointid").equals(paramMap.get("consume_jointid")))
                {
                    //消费合作商ID
                    String consumeJointId = paramMap.get("consume_jointid").toString();
                    //获取消费合作商员工分账比例
                    String consumeScale = orderDao.queryPercentageInfo(consumeJointId);
                    //判断所属合作商员工的分账比例是否为空
                    if(StringUtils.isNotEmpty(consumeScale))
                    {
                        Map<String, Object> consumMap = new HashMap<String, Object>();
                        //消费合作商和员工
                        consumMap.put("jointid", consumeJointId);
                        consumMap.put("staffid", staffIdMap.get("consumeId"));
                        consumMap.put("sellerid", paramMap.get("sellerid"));
                        consumMap.put("sellername", paramMap.get("sellername"));
                        consumMap.put("payment",resObject.getBigDecimal("consumeJointidMoney"));
                        consumMap.put("scale", consumeScale);
                        //消费合作商佣金+消费合作商业务员收益
                        BigDecimal consumeBpartnerAmount = resObject.getBigDecimal("cpartner_amount")
                                                 .add(resObject.getBigDecimal("consumeJointidMoney"));
                        consumMap.put("bpartnerAmount", consumeBpartnerAmount);
                        consumMap.put("bid", paramMap.get("bid"));
                        consumMap.put("money", paramMap.get("money"));
                        //ADD消费合作商和员工
                        resList.add(consumMap);
                    }
                }
            }
            
            for (Map<String, Object> listMap : resList)
            {
                Map<String, Object> percentageMap = new HashMap<String, Object>();
                percentageMap.put("jointId", listMap.get("jointid"));
                percentageMap.put("staffId", listMap.get("staffid"));
                percentageMap.put("sellerId", listMap.get("sellerid"));
                percentageMap.put("sellerName", listMap.get("sellername"));
                percentageMap.put("payment", listMap.get("payment"));
                percentageMap.put("scale", listMap.get("scale"));
                percentageMap.put("bid", listMap.get("bid"));
                percentageMap.put("money", listMap.get("money"));
                percentageMap.put("bpartnerAmount",listMap.get("bpartnerAmount"));
                SimpleDateFormat sdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                percentageMap.put("sdate",sdateFormat.format(new Date()));
                //调用DAO层的添加合作商员工提成记录接口，并返回插入的记录行数
                orderDao.addPercentageRecord(percentageMap);
            }
        }
        catch (Exception e)
        {
            log.error("添加合作商员工提成记录异常", e);
        }
        log.info("addPercentageRecord end......");
    }
    
    public DefaultMQPushConsumer getLedgerConsumConnection()
    {
        return ledgerConsumConnection;
    }
    
    public void setLedgerConsumConnection(
            DefaultMQPushConsumer ledgerConsumConnection)
    {
        this.ledgerConsumConnection = ledgerConsumConnection;
    }
    
    public String getLedgerTopic()
    {
        return ledgerTopic;
    }
    
    public void setLedgerTopic(String ledgerTopic)
    {
        this.ledgerTopic = ledgerTopic;
    }
    
    public String getLedgerTags()
    {
        return ledgerTags;
    }
    
    public void setLedgerTags(String ledgerTags)
    {
        this.ledgerTags = ledgerTags;
    }
    
    public OrderServiceImpl getOrderServiceImpl()
    {
        return orderServiceImpl;
    }
    
    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl)
    {
        this.orderServiceImpl = orderServiceImpl;
    }

	public String getLiveLedgerReturnbackTopic() {
		return liveLedgerReturnbackTopic;
	}

	public void setLiveLedgerReturnbackTopic(String liveLedgerReturnbackTopic) {
		this.liveLedgerReturnbackTopic = liveLedgerReturnbackTopic;
	}

	public String getLiveRecommendLedgerReturnbackTags() {
		return liveRecommendLedgerReturnbackTags;
	}

	public void setLiveRecommendLedgerReturnbackTags(
			String liveRecommendLedgerReturnbackTags) {
		this.liveRecommendLedgerReturnbackTags = liveRecommendLedgerReturnbackTags;
	}

	public String getLiveDividendsLedgerReturnbackTags() {
		return liveDividendsLedgerReturnbackTags;
	}

	public void setLiveDividendsLedgerReturnbackTags(
			String liveDividendsLedgerReturnbackTags) {
		this.liveDividendsLedgerReturnbackTags = liveDividendsLedgerReturnbackTags;
	}

	public String getLiveDividendsLedgerV2ReturnbackTags() {
		return liveDividendsLedgerV2ReturnbackTags;
	}

	public void setLiveDividendsLedgerV2ReturnbackTags(
			String liveDividendsLedgerV2ReturnbackTags) {
		this.liveDividendsLedgerV2ReturnbackTags = liveDividendsLedgerV2ReturnbackTags;
	}

	public String getLiveDividendsLedgerV3ReturnbackTags() {
		return liveDividendsLedgerV3ReturnbackTags;
	}

	public void setLiveDividendsLedgerV3ReturnbackTags(
			String liveDividendsLedgerV3ReturnbackTags) {
		this.liveDividendsLedgerV3ReturnbackTags = liveDividendsLedgerV3ReturnbackTags;
	}

	public String getManorPayTopic() {
		return manorPayTopic;
	}

	public void setManorPayTopic(String manorPayTopic) {
		this.manorPayTopic = manorPayTopic;
	}

	public String getManorNectarReturnbackTag() {
		return manorNectarReturnbackTag;
	}

	public void setManorNectarReturnbackTag(String manorNectarReturnbackTag) {
		this.manorNectarReturnbackTag = manorNectarReturnbackTag;
	}

    public void setManorGiftNectarReturnbackTag(String manorGiftNectarReturnbackTag) {
        this.manorGiftNectarReturnbackTag = manorGiftNectarReturnbackTag;
    }
}
