package com.xmniao.service.refund;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.dao.refund.RefundOrderServiceDao;
import com.xmniao.domain.order.BillBean;
import com.xmniao.service.order.ModifyJointProfitDayCensusImpl;
import com.xmniao.service.order.OrderServiceImpl;
import com.xmniao.service.refund.census.JointDayCensus;
import com.xmniao.service.refund.census.MdySellerDayCensusImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.XmnOrderParam;
import com.xmniao.thrift.busine.refund.BusiSysOrderService;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.urs.dao.XmerDao;

/**
 * 提供给业务管理系统服务接口模块实现类
 * @author  fubin
 * @version  [版本号, 2015年9月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class BusiSysOrderServiceImpl implements BusiSysOrderService.Iface{

    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(RefundOrderServiceImpl.class);
    
    /**
     * 订单退款DAO层
     */
    @Autowired
    private RefundOrderServiceDao refundDao;
    
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 注入用户DAO
     */
    @Autowired
    private UrsDao ursDao;
    
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 向蜜客处理队列
     */
    private String mikeQueue;
    
    /**
     * 自动订单的redis队列KEY
     */
    private String zdQueueKey;
    
    /**
     * 即时监听队列
     */
    private String jtqueue_key;
    
    
    /**
     * 注入Ecache缓存
     */
 //   @Autowired
 //   private Cache dataCache;        
    
    /**
     * 注入更新合作商收益订单统计实现类
     */
    @Autowired
    private ModifyJointProfitDayCensusImpl mdyJointProfitDayCensusImpl;
    
    /**
     * 订单操作service
     */
    @Autowired
    private OrderServiceImpl orderServiceImpl;
    
    /**
     * 合作商统计
     */
    @Autowired
    private JointDayCensus jointDayCensus;
    
    /**
     * 商户日收益统计
     */
    @Autowired
    private MdySellerDayCensusImpl mdySellerDayCensusImpl2;
    
    @Autowired
    private OrderServiceImpl orderService;
    
    @Autowired
    private XmerDao xmerDao;
    
    @Autowired
    private UrsDao ursDaao;
    
    /**
     * 
     * 1 此接口供业务管理系统调用使用与用户由三方支付未及时回调，用户报障但商家已认可需恢复为正常单使用流程，接口名称payFailRecoverOrder,参数Map<String,String> paraMap，参数包含内容bid即订单号
     *
	 *	2 订单数据修改订单status为9，hstatus为11，isverify为1，ldate为当前系统时间，source为0
     *
	 *	3 如订单表中mike_type未0且消费商家与所属商家相同，需绑定向蜜客
     *
	 *	4 发送订单数据到分账队列
     *
	 *	5 删除订单退款表中改订单的退款记录
     *
	 *	6 如该订单未统计在商户日收益统计，合作商日统计，合作商日收益统计中需重新统计当天订单
     *
	 *	7 订单处理记录表（t_bill_record）新加一条订单处理记录，explains（处理说明）描述为“恢复报障退款单”
	 *
	 *	8 接口返回状态分为（成功，失败，已处理，三种状态接口调用后依业务逻辑返回对应的状态码）
     * 
     */
    @Transactional(readOnly=false,isolation=Isolation.DEFAULT,
    propagation=Propagation.REQUIRED,rollbackFor=FailureException.class)
	public Map<String, String> payFailRecoverOrder(Map<String, String> paraMap)
			throws FailureException, TException {
    	return payFailRecoverOrderNew(paraMap);
/*-------------------------    2017年1月5日 备份旧代码，启用新的恢复报障订单接口     Edit By ChenBo   ---------------------------*/
    	
//		log.info("payFailRecoverOrder begin  parameter data:"+paraMap);
//
//		Map<String, String> resMap = new HashMap<String, String>();
//
//        //根据订单号获取订单信息
//        Map<String, Object> resOrderMap = refundDao.queryOrderInfoForRefund(paraMap.get("bid"));
//        //订单信息为空
//        if (null == resOrderMap)
//        {
//            log.error("没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
//            resMap.put("bid", paraMap.get("bid"));
//            resMap.put("recode", ResponseState.ORDERNULL+"");
//            resMap.put("remark", "没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
//            return resMap;
//        }
//        
//        Integer status = Integer.parseInt(String.valueOf(resOrderMap.get("status")));
//        if(status == 9 ){
//            log.info("订单号为:【" + paraMap.get("bid") + "】订单已被处理");
//            resMap.put("bid", paraMap.get("bid"));
//            resMap.put("recode", ResponseState.ORDER_READY_MODIFY+"");
//            resMap.put("remark", "订单号为:【" + paraMap.get("bid") + "】订单已被处理");
//            return resMap;
//        }else if(status != 14){
//            log.info("订单号为:【" + paraMap.get("bid") + "】订单状态不符");
//            resMap.put("bid", paraMap.get("bid"));
//            resMap.put("recode", ResponseState.ORDERFAIL+"");
//            resMap.put("remark", "订单号为:【" + paraMap.get("bid") + "】订单状态不符");
//            return resMap;
//        }
//        
//        //查询优惠券与订单关系
//        List<Map<String, Object>> resListMap=refundDao.getOrderCouponList(paraMap.get("bid"));
//        for(Map<String, Object> qMap : resListMap)
//        {
//            int userStatus=Integer.valueOf(String.valueOf(qMap.get("user_status"))).intValue();
//            //获取的优惠券使用状态为未使用或者锁定，进行更新为已使用
//            if(userStatus==0 || userStatus==1)
//            {
//                //更新优惠券的用户使用状态
//                refundDao.mdyCouponUserStatus(qMap.get("cdid").toString());
//                break;
//            }else if(userStatus==2 && qMap.get("bid")!=paraMap.get("bid"))
//            {
//                //若优惠券状态为已使用且订单号不为请求的订单号
//                log.info("订单号为:【" + paraMap.get("bid") + "】的优惠券已使用");
//                resMap.put("bid", paraMap.get("bid"));
//                resMap.put("recode", ResponseState.COUPON_READY_USING+"");
//                resMap.put("remark", "订单号为:【" + paraMap.get("bid") + "】的优惠券已使用");
//                return resMap;
//            }
//        }
//        
//        try
//        {
//        	/*
//        	 * 1.是否首单，绑定商家
//        	 * 2.将优惠券标记为已使用
//        	 * 3.向用户赠送积分
//        	 * 4.向用户赠送优惠券
//        	 * 5.修改订单信息
//        	 * 6.插入订单记录
//        	 * 7.发短信，发推送
//        	 * 8.插分账
//        	 * 9.删退款记录
//        	 * 
//        	 */
//        	BillBean billBean = orderDao.getBillBean(paraMap.get("bid"));
//        	Map<String,String> uMap = new HashMap<String,String>();
//        	uMap.put("uid", billBean.getUid()+"");
//        	
//        	//绑定商家信息
//        	Map<String,Object> urs = ursDao.getUrsAscription(uMap);
//        	
//        	//是否首单信息
//            Map<String, String> resMikeMap = new HashMap<String, String>();
//            resMikeMap.put("mikeType", "2");
//            resMikeMap.put("bfirst", "0");
//            
//            //首单判断
//            log.info("消费会员【"+billBean.getUid()+"】当前的所属信息:"+urs);
//            if(urs.get("genussellerid") == null)
//            {           
//            	log.info("会员消费首单");
//                resMikeMap.put("mikeType", "2");
//                resMikeMap.put("bfirst", "1");
//            }      
//            
//            //准备最新订单信息
//            modifyBillBean(billBean,resMikeMap);
//            
//            log.info("更新后，订单信息："+billBean);
//            //更新订单信息
//            //mdyOrderInfoProcess2(billBean);
//            
//            //将优惠券标记为已使用
//            orderService.usedUserCoupon(billBean);
//            
//            //向用户赠送积分
//            orderService.consumerRebate(billBean);
//            
//            //向用户赠优惠券
//            //orderService.consumerCoupon(billBean);
//            
//            //向用户赠商家券
//            if(billBean.getCouponType()==null){
//            	orderService.consumerSellerCoupon(billBean);
//            }
//            
//            //向用户赠商家红包
//            orderService.consumerRedPacket(billBean);
//            
//            //向推荐人赠商家推荐人红包
//            orderService.consumerRecommendRedPacket(billBean);
//            
//            //使用商家消费满减活动
//            orderService.consumerFullreduction(billBean);
//            
//            //插入分账消息队列
//            orderService.pushLedgerRedis(billBean,zdQueueKey,1);
//            
//            if((urs.get("genussellerid") == null || "".equals(urs.get("genussellerid")))
//            		&& billBean.getGenussellerid() != null)
//            {
//                //消费绑定商家
//            	log.info("首单绑定商家");
//            	orderService.sellerMikeBuilding(billBean);
//            }
//            
//            //插入订单处理记录接口
//             /*2016-02-23 删除插入订单记录信息，减少INSERT语句 提高订单处理速度*/
//            //Map<String, String> reqMap = new HashMap<String, String>();
//            //reqMap.put("bid", request.getBid());
//            //reqMap.put("status", "1");
//            //reqMap.put("explains", "订单号" + request.getBid() + "支付订单（支付系统）支付成功");
//            //insertBillRecord(reqMap);
//            
//            // 发送短信处理方法   微信推送消息，此处不判断手机号码是否存在
//            //sendSms(billBean, request.getPhoneid());
//            // 推送类型,1为订单支付成功
//            //String orderPushType = "1";
//            
//            // 订单支付成功后,调用推送消息发送redis队列方法
//           //this.pushMsgRedisProcess2(billBean, orderPushType);
//            
//            // 更新订单接口流程操作完成后,返回订单号
//            //result = request.getBid();
//            
//            //long endTime = System.currentTimeMillis();
//            //log.info("更新订单接口总耗时:" + (endTime - starTime) + "ms");
//            
//            //发送订单流水记录消息
//            orderService.sendBillRecordMQ(paraMap.get("bid"));
//        	
//        	
//        	
//        	
//        	
//            //组装更新订单状态和订单佣金JSON
//            Map<String, String> reqMap = new HashMap<String, String>();
//            reqMap.put("bid", paraMap.get("bid"));
//            reqMap.put("status", "9");
//            reqMap.put("source", "0");
//            reqMap.put("hstatus", "11");
//            reqMap.put("isverify", "1");
//            reqMap.put("integral", String.valueOf(billBean.getIntegral()));
//            //分账时间 系统当前时间
//            reqMap.put("ldate", DateUtil.getCurrentTimeStr());
//            
//            reqMap.put("mikeType", "2");//绑定香蜜客，类型更新
//            reqMap.put("bfirst", "0");
//            //首单判断
//            if(urs.get("genussellerid") == null)
//            {           
//            	reqMap.put("bfirst", "1");
//            } 
//            
//            //更新订单状态
//            refundDao.modifyOrderInfo(reqMap);
//            
//            try {
//				//订单处理记录接口
//				Map<String, String> recordMap = new HashMap<String, String>();
//				recordMap.put("bid", paraMap.get("bid"));
//				recordMap.put("status", "4");
//				recordMap.put("explains", "恢复报障退款单");
//				recordMap.put("remarks", "恢复报障退款单");
//				orderServiceImpl.insertBillRecord(recordMap);
//			} catch (Exception e) {
//				log.error("添加订单处理记录出错", e);
//			}
//            
//            try {
//				//删除订单退款记录、
//				Map<String, String> deleMap = new HashMap<String, String>();
//				deleMap.put("bid", resOrderMap.get("bid").toString());
//				deleMap.put("sellerid", resOrderMap.get("sellerid").toString());
//				refundDao.deleteOrderRefundRecord(deleMap);
//			} catch (Exception e) {
//				log.error("删除退款记录出错", e);
//			}
//                 
//            //绑定向蜜客
//            //String sellerid = resOrderMap.get("sellerid") == null ? "" :String.valueOf(resOrderMap.get("sellerid"));
//            //String genussellerid = resOrderMap.get("genussellerid") == null ? "" :String.valueOf(resOrderMap.get("genussellerid"));
//            //String mike_type = resOrderMap.get("mike_type") == null ? "" :String.valueOf(resOrderMap.get("mike_type"));
//            //if(mike_type.equals("0") && sellerid.equals(genussellerid)){
//            //	orderServiceImpl.adjustMikeProcess(resOrderMap);
//            //}
//            
//            //下单时间
//            String zdate = String.valueOf(resOrderMap.get("zdate"));
//            String  tempdate = String.valueOf(zdate).substring(0,10);
////            
////            //判断商家分账类型为自动,当前无手动分账
////            //组装redis队列MAP
////            Map<String, String> zdQueueMap = new HashMap<String, String>();
////            zdQueueMap.put("type", "1");
////            zdQueueMap.put("order_number", resOrderMap.get("bid").toString());
////            //如果是当天的订单发自动队列即t+1,非当天发及时监听队列           
////            if(DateUtil.isCurrentTime(tempdate)){
////            	 zdQueueMap.put("ledger_type", "1");
////        	}else{
////        		zdQueueMap.put("ledger_type", String.valueOf(2));  
////        		zdQueueMap.put("pay_date", zdate);         		       		
////        	}
////            
////            //自动队列和订单号转换类型
////            String autoQueueMap = JSONObject.toJSONString(zdQueueMap);
////            double bidDbl = Double.valueOf(resOrderMap.get("bid").toString());
////            log.info("payFailRecoverOrder autoQueueMap:" + autoQueueMap);
////            
////            try
////            {
////                //放入redis缓存中,值为类型为订单+订单编号+商家分账类型为自动
////            	//如果是当天的订单发自动队列即t+1,非当天发及时监听队列        
////            	if(DateUtil.isCurrentTime(tempdate)){
////            		redisTemplate.opsForZSet().add(zdQueueKey,autoQueueMap,bidDbl);
////            	}else{
////            		redisTemplate.opsForList().leftPush(jtqueue_key,autoQueueMap);           		
////            	}
////            }
////            catch (RedisConnectionFailureException ex)
////            {
////                //若发送或者连接redis失败，则放入到本地缓存Ecache中
////                if(StringUtils.isNotBlank(autoQueueMap))
////                {
////                    Map<String,String> contentMap=new HashMap<String,String>();
////                    //如果是当天的订单发自动队列即t+1,非当天发及时监听队列  
////                    if(DateUtil.isCurrentTime(tempdate)){
////                    	contentMap.put("markKey", zdQueueKey);
////                	}else{
////                		contentMap.put("markKey", jtqueue_key);        		
////                	}                  
////                    contentMap.put("markContent", autoQueueMap);
////                    contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
////                    contentMap.put("addNumber","0");
////                    //失败数据放入Ecache缓存中
////                    orderServiceImpl.exceptionHandle(contentMap);                        
////                }
////            }
//            
//            //重新统计           
//            if(!DateUtil.isCurrentTime(tempdate)){
//            	//更新map订单信息
//            	resOrderMap.put("status", "9");
//                //商户日订单统计处理
//            	mdySellerDayCensusImpl2.modifySellerDayCensus(resOrderMap);
//            	
//            	
//                //合作商日订单统计处理
//                jointDayCensus.modifyJointDayCensus(paraMap.get("bid"));
//                
//                
//                //合作商收益订单统计处理
//                mdyJointProfitDayCensusImpl.modifyJointProfitDayCensus(resOrderMap,resOrderMap);
//                
//            }               
//            
//            resMap.put("recode", ResponseState.SUCCESS+"");
//            resMap.put("remark", "操作成功");
//            
//        }
//        catch (Exception e)
//        {
//            log.error("更新订单数据异常", e);           
//            resMap.put("recode", ResponseState.ELSEEROR+"");
//            resMap.put("remark", "操作失败");
//            throw new FailureException(ResponseState.ELSEEROR,"更新调单后的订单信息操作异常");
//        }       
//        
//		log.info("payFailRecoverOrder end  parameter data:"+paraMap);
//        
//		return resMap;
	}

	public Map<String, String> payFailRecoverOrderNew(Map<String, String> paraMap)
			throws FailureException, TException {
		
		log.info("payFailRecoverOrder begin  parameter data:"+paraMap);
		Map<String, String> resMap = new HashMap<String, String>();

        //根据订单号获取订单信息
        //Map<String, Object> resOrderMap = refundDao.queryOrderInfoForRefund(paraMap.get("bid"));
        BillBean billBean = orderDao.getBillBean(paraMap.get("bid"));
        //订单信息为空
        if (null == billBean)
        {
            log.error("没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("recode", ResponseState.ORDERNULL+"");
            resMap.put("remark", "没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
            return resMap;
        }
        
        Integer status = billBean.getStatus();
        if(status == 9 ){
            log.info("订单号为:【" + paraMap.get("bid") + "】订单已被处理");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("recode", ResponseState.ORDER_READY_MODIFY+"");
            resMap.put("remark", "订单号为:【" + paraMap.get("bid") + "】订单已被处理");
            return resMap;
        }else if(status != 14){
            log.info("订单号为:【" + paraMap.get("bid") + "】订单状态不符");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("recode", ResponseState.ORDERFAIL+"");
            resMap.put("remark", "订单号为:【" + paraMap.get("bid") + "】订单状态不符");
            return resMap;
        }
        
        //查询优惠券与订单关系
        List<Map<String, Object>> resListMap=refundDao.getOrderCouponList(paraMap.get("bid"));
        for(Map<String, Object> qMap : resListMap){
            int userStatus=Integer.valueOf(String.valueOf(qMap.get("user_status"))).intValue();
            //获取的优惠券使用状态为未使用或者锁定，进行更新为已使用
            if(userStatus==0 || userStatus==1){
                //更新优惠券的用户使用状态
                refundDao.mdyCouponUserStatus(qMap.get("cdid").toString());
                break;
            }else if(userStatus==2 && qMap.get("bid")!=paraMap.get("bid")){
                //若优惠券状态为已使用且订单号不为请求的订单号
                log.info("订单号为:【" + paraMap.get("bid") + "】的优惠券已使用");
                resMap.put("bid", paraMap.get("bid"));
                resMap.put("recode", ResponseState.COUPON_READY_USING+"");
                resMap.put("remark", "订单号为:【" + paraMap.get("bid") + "】的优惠券已使用");
                return resMap;
            }
        }
        
        try
        {
        	XmnOrderParam param = this.getXmnOrderParam(billBean);
        	orderService.updateXmnOrderInfo(param);
        	
            //更新订单为处理中
            Map<String, String> reqMap = new HashMap<String, String>();
            reqMap.put("bid", paraMap.get("bid"));
            reqMap.put("status", "9");
            reqMap.put("fzdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            refundDao.modifyOrderInfo(reqMap);
            
            try {
				//订单处理记录接口
				Map<String, String> recordMap = new HashMap<String, String>();
				recordMap.put("bid", paraMap.get("bid"));
				recordMap.put("status", "4");
				recordMap.put("explains", "恢复报障退款单");
				recordMap.put("remarks", "恢复报障退款单");
				orderServiceImpl.insertBillRecord(recordMap);
			} catch (Exception e) {
				log.error("添加订单处理记录出错", e);
			}
            
            try {
				//删除订单退款记录、
				Map<String, String> deleMap = new HashMap<String, String>();
				deleMap.put("bid", billBean.getBid().toString());
				deleMap.put("sellerid", billBean.getSellerid().toString());
				refundDao.deleteOrderRefundRecord(deleMap);
			} catch (Exception e) {
				log.error("删除退款记录出错", e);
			}
                 
            
            //下单时间
            String zdate = String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(billBean.getZdate()));
            String  tempdate = String.valueOf(zdate).substring(0,10);

            //重新统计           
            if(!DateUtil.isCurrentTime(tempdate)){
            	Map<String,Object> uMap = new HashMap<String,Object>();
            	uMap.put("zdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(billBean.getZdate()));
            	uMap.put("fdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            	uMap.put("sellerid", billBean.getSellerid()+"");
            	uMap.put("genussellerid", billBean.getGenussellerid()+"");
            	uMap.put("status", billBean.getStatus()+"");
            	uMap.put("jointid", billBean.getJointid()+"");
            	uMap.put("consume_jointid", billBean.getConsumeJointid()+"");
                //商户日订单统计处理
            	mdySellerDayCensusImpl2.modifySellerDayCensus(uMap);
                //合作商日订单统计处理
                jointDayCensus.modifyJointDayCensus(paraMap.get("bid"));
                //合作商收益订单统计处理
                mdyJointProfitDayCensusImpl.modifyJointProfitDayCensus(uMap,uMap);
            }               
            resMap.put("recode", ResponseState.SUCCESS+"");
            resMap.put("remark", "操作成功");
        }catch (Exception e){
            log.error("更新订单数据异常", e);           
            resMap.put("recode", ResponseState.ELSEEROR+"");
            resMap.put("remark", "操作失败");
            throw new FailureException(ResponseState.ELSEEROR,"更新调单后的订单信息操作异常");
        }       
		log.info("payFailRecoverOrder end  parameter data:"+paraMap);
		return resMap;
	}
	
	public XmnOrderParam getXmnOrderParam(BillBean billBean){

		XmnOrderParam param = new XmnOrderParam();
		param.setBid(billBean.getBid()+"");
		param.setStatus("1");
		param.setZdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(billBean.getZdate()));
		param.setUid(billBean.getUid()+"");
		param.setPhoneid(billBean.getPhoneid());
		param.setPayid(billBean.getPayid());
		param.setNumber(billBean.getNumber());
		param.setThirdUid(billBean.getThirdUid());
		param.setPaytype(billBean.getPaytype());
		param.setOrdertype("1");
		param.setMoney(billBean.getMoney()+"");
		param.setPreferential(billBean.getCalculateRealPreferentialAmount()+"");
		param.setPayamount(billBean.getCalculateRealPayAmount()+"");
		param.setIsbalance(billBean.getCalculateRealPayAmount().subtract(billBean.getPayment()).compareTo(BigDecimal.ZERO)>0?"1":"0");
		param.setSamount(billBean.getPayment()+"");
		param.setCommision(billBean.getCommision()+"");
		param.setProfit(billBean.getProfit()+"");
		param.setGiveMoney(billBean.getGiveMoney()+"");
		return param;
	}
	
	public String getMikeQueue() {
		return mikeQueue;
	}

	public String getZdQueueKey() {
		return zdQueueKey;
	}

	public void setMikeQueue(String mikeQueue) {
		this.mikeQueue = mikeQueue;
	}

	public void setZdQueueKey(String zdQueueKey) {
		this.zdQueueKey = zdQueueKey;
	}


	public String getJtqueue_key() {
		return jtqueue_key;
	}


	public void setJtqueue_key(String jtqueue_key) {
		this.jtqueue_key = jtqueue_key;
	}

    /**
     * 准备最新订单信息
     * @Title: modifyBillBean 
     * @Description:
     */
   void  modifyBillBean(BillBean billBean,Map<String,String> resMikeMap) throws FailureException{
       log.info("modifyBillBean start:" + billBean);
       try
       {
           billBean.setIntegral(billBean.getCalculateRealZInteger().doubleValue());//2016-7-12 Edit By CB 总金额-立减 //billBean.getPayment().doubleValue()
           billBean.setLedgerAmount(billBean.getCalculateRealLedgerAmount());
   		   /* 获取寻蜜客等级信息 */
   		   Integer xmerUid = billBean.getXmerUid();
   		   if(xmerUid != null){
	           	if(xmerUid==0){
	        		log.info("该订单记录商家寻蜜客的uid为无效ID，现将其设置为null");
	        		billBean.setXmerUid(null);
	        	}
   			   Map<String,Object> issuesMap = xmerDao.getXmerIssues(xmerUid);
   			   if(issuesMap == null){	//寻蜜客被解除
   				   billBean.setXmerUid(null);//2016年7月7日 若对应寻蜜客被解除，则将其默认为无寻蜜客
   			   }else{
	   			   orderService.getXmerName(issuesMap);
		           billBean.setOneLevelXmerId(issuesMap.get("oneLevelXmerId")==null?null:(Integer)issuesMap.get("oneLevelXmerId"));
		           billBean.setTwoLevelXmerId(issuesMap.get("twoLevelXmerId")==null?null:(Integer)issuesMap.get("twoLevelXmerId"));
		           billBean.setOneLevelXmerName(issuesMap.get("oneLevelXmerName")==null?null:(String)issuesMap.get("oneLevelXmerName"));
		           billBean.setTwoLevelXmerName(issuesMap.get("twoLevelXmerName")==null?null:(String)issuesMap.get("twoLevelXmerName"));
   			   }
   		   }   
   		   
   		 JSONObject json = JSON.parseObject(billBean.getCommission());  
   		 String str=json.getString("proportion");
   		 billBean.setProportion(str);
//           LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess2(billBean);
//           log.info("分账初始数据："+bean);
//           Map<String,Object> resMap = LedgerAlgorithmUtils.getLedgerMoney(bean);
//           
//           log.info("ledgerSystem resMap::" + resMap);
//           if(null != resMap && !resMap.isEmpty())
//           {
//           	JSONObject json=(JSONObject) JSONObject.toJSON(resMap);
//           	json.put("proportion", json.getString("proportion"));
//           	billBean.setCommission(json.getString("commission"));
//           	billBean.setProportion(json.getString("proportion"));
//           }
           
//           //若是自动分账
//           if(request.getLedgertype() == 1)
//           {
//        	   billBean.setHstatus(10);
//        	   billBean.setLdate(new Date());
//           }else{
//        	   billBean.setHstatus(0);
//           }
           
           if(null!=resMikeMap)
           {
        	   billBean.setMikeType(resMikeMap.containsKey("mikeType")?Byte.valueOf(resMikeMap.get("mikeType")):null);
        	   billBean.setBfirst(resMikeMap.containsKey("bfirst")?Integer.valueOf(resMikeMap.get("bfirst")):null);
           }
           
       }
       catch (Exception e)
       {
           log.error("准备订单数据异常", e);
           throw new FailureException(ResponseState.ELSEEROR, "准备订单数据异常");
       }
       log.info("modifyBillBean end......");
    }

}
