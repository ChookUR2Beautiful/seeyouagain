package com.xmniao.service.order;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.ConversionTypeUtil;
import com.xmniao.common.DateUtil;
import com.xmniao.common.MapUtil;
import com.xmniao.common.NullU;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.coupon.CouponDao;
import com.xmniao.dao.coupon.RedPacketDao;
import com.xmniao.dao.coupon.SellerCouponDao;
import com.xmniao.dao.coupon.UserCouponDao;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.dao.sellerOrder.ActivityFcouspointsDao;
import com.xmniao.dao.sellerOrder.ActivityFreetryDao;
import com.xmniao.dao.sellerOrder.ActivityFullreductionDao;
import com.xmniao.dao.sellerOrder.ActivityRoulleteDao;
import com.xmniao.dao.sellerPackage.SellerPackageDao;
import com.xmniao.dao.xmer.SaasOrderDao;
import com.xmniao.domain.coupon.ActivityFcouspoints;
import com.xmniao.domain.coupon.ActivityFreetry;
import com.xmniao.domain.coupon.ActivityFullreduction;
import com.xmniao.domain.coupon.ActivityRoullete;
import com.xmniao.domain.coupon.CouponDetail;
import com.xmniao.domain.coupon.CouponRelation;
import com.xmniao.domain.coupon.RedPacket;
import com.xmniao.domain.coupon.RedPacketRecord;
import com.xmniao.domain.coupon.SellerCoupon;
import com.xmniao.domain.coupon.UserCoupon;
import com.xmniao.domain.ledger.LedgerMixBean;
import com.xmniao.domain.ledger.LedgerNewBean;
import com.xmniao.domain.ledger.SellerPackageLedger;
import com.xmniao.domain.order.BillBean;
import com.xmniao.domain.order.BillEntity;
import com.xmniao.domain.order.ModifyOrderInfoBean;
import com.xmniao.domain.order.OrdRecordBean;
import com.xmniao.domain.order.XmnWalletBean;
import com.xmniao.domain.seller.SellerBean;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.service.ledger.LedgerAlgorithmUtils;
import com.xmniao.service.ledger.LedgerServiceImpl;
import com.xmniao.service.live.SellerRechargeLiveOrder;
import com.xmniao.service.seller.OrderLedgerInfoService;
import com.xmniao.service.user.UserActionServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.common.UpdateOrderRequest;
import com.xmniao.thrift.busine.common.XmnOrderParam;
import com.xmniao.thrift.busine.common.XmnOrderParamV2;
import com.xmniao.thrift.busine.order.OrderService;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.urs.dao.UrsEarningsRelationDao;
import com.xmniao.urs.dao.XmerDao;

/**
 * 订单服务接口实现接口类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OrderServiceImpl implements OrderService.Iface
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(OrderServiceImpl.class);
	
	/**
	 *  风险识别时间订单数量
	 */
	private static final int WIND_CONTROL_AMOUNT_COUNT = 3;

	/**
	 * 风险识别时间范围
	 */
	private static final int WIND_CONTROL_AMOUNT = -5;

	/**
	 * 风险识别金额
	 */
    private static final double WIND_CONTROL_MONEY = 3000.00d;
    
    /**
     * 非付费商家交易手续费
     */
    private static final double FEE_RATIO = 0.02d;
    
    /**
     * ehcache缓存存储key
     */
    //private static final String FAULT_KEY ="faultKey";
       
    /**
     * 大转盘次数KEY
     */
    private static final String ROULLETE_KEY = "roullete_";
    
    /**
     * 免费尝新抽奖次数KEY
     */
    private static final String FREETRY_KEY = "freetry_";
    
    /**
     * 自动订单的redis队列KEY
     */
    private String zdQueueKey;
    
    /**
     * 手动订单的redis队列KEY
     */
    private String sdQueueKey;
    
    /**
     * 脉宝分账通知的redis队列KEY
     */
    private String maibaoLedgerQueueKey;
    
    /**
     * 向蜜客处理队列
     */
    private String mikeQueue;
    
    /**
     * 推送消息redis队列KEY
     */
    private String pushMsgQueue;
    
    /**
     * 发送短信redis队列KEY
     */
    private String smsQueue;
    
    /**
     * 订单发送短信内容(用户)
     */
    private String orderSmsContent;
    
    /**
     * 订单发送短信内容（商家）
     */
    private String sellerSmsContent;
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 注入通用服务接口DAO层
     */
    private CommonServiceImpl commonServiceImpl;
    
    /**
     * 注入分账系统服务接口DAO层
     */
    @Autowired
    private LedgerServiceImpl ledgerServiceImpl;
    
    @Autowired
    private UserActionServiceImpl userActionService;
    
    /*@Autowired
    private SellerOrderServiceImpl sellerOrderService;*/
    
    @Autowired
    private AdjustOrderServiceImpl adjustOrderServiceImpl;
    
    @Autowired
    private OrderLedgerInfoService orderLedgerInfoService;
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 注入Ecache缓存
     * 2016-11-16 暂时取消Ecache的使用
     */
    @Autowired
    private Cache dataCache;
    
    /**
     * 注入消息队列生产者
     */
   /* @Autowired
    private DefaultMQProducer producerConnection;*/
    
    /**
     * 注入用户DAO
     */
    @Autowired
    private UrsDao ursDao;
    
    @Autowired
    private XmerDao xmerDao;
    
    @Autowired
    private SellerCouponDao sellerCouponDao;
    
    @Autowired
    private UserCouponDao userCouponDao;
    
    @Autowired
    private RedPacketDao redPacketDao;
    
    /*@Autowired
    private RedPacketRecordDao redPacketRecordDao;*/
    
    @Autowired
    private SellerActivityServiceImpl redPacketService;
    
    @Autowired
    private ActivityFullreductionDao fullreductionDao;
    
    @Autowired
    private ActivityFreetryDao freetryDao;
    
    @Autowired
    private ActivityRoulleteDao roulleteDao;
    
    @Autowired
    private CouponDao couponDao;
    
    @Autowired
    private ActivityFcouspointsDao fcouspointsDao;
    
    @Autowired
    private SellerDao sellerDao;
    
    @Autowired
    private SellerPackageDao sellerPackageDao;
    
    @Autowired
    private SellerRechargeLiveOrder sellerRechargeOrderService;
    
    /**
     * 注入会员收益关系链DAO
     */
    @Autowired
	private UrsEarningsRelationDao ursRelationDao;
    
	/**
	 * 注入SaaS订单DAO
	 */
	@Autowired
	private SaasOrderDao saasOrderDao;
    
    @Resource(name="xmnWalletKey")
    private String xmnWalletKey;
    
    /*@Resource(name="userActionQueue")
    private String userActionQueue;*/
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private final BigDecimal ZERO = new BigDecimal("0.00");
    
    /**
     * 发送商家订单支付成功返回消息
     * @param bid
     * @throws FailureException 
     * @throws InterruptedException 
     * @throws MQBrokerException 
     * @throws RemotingException 
     * @throws MQClientException 
     */
    public void sendBillRecordMQ(String bid) throws FailureException, MQClientException, RemotingException, MQBrokerException, InterruptedException {}

    
	/**
     * 更新订单信息处理 2016年6月14日 分账规则2.0
     * @param resMikeMap [绑定向蜜客MAP请求参数]
     * @param resOrderMap [订单信息MAP参数]
     * @param request [支付系统传递请求参数]
     * @throws FailureException [参数说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void mdyOrderInfoProcess2(BillBean billBean)
            throws FailureException
    {
        log.info("mdyOrderInfoProcess start:" + billBean);
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //组装更新订单信息
            ModifyOrderInfoBean reqBean = new ModifyOrderInfoBean();
            reqBean.setBid(String.valueOf(billBean.getBid()));
            reqBean.setNumber(billBean.getNumber());
            reqBean.setPayid(billBean.getPayid());
            reqBean.setPaytype(billBean.getPaytype());
            reqBean.setPayment(billBean.getPayment());
            reqBean.setStatus(billBean.getStatus());
            reqBean.setCommision(billBean.getCommision());
            reqBean.setProfit(billBean.getProfit());
            reqBean.setGivemoney(billBean.getGiveMoney());
            reqBean.setThirduid(billBean.getThirdUid());
            reqBean.setZdate(dateFormat.format(billBean.getZdate()));
            reqBean.setCommission(billBean.getCommission());
            reqBean.setLiveCoin(billBean.getLiveCoin());
            reqBean.setLiveCoinMoney(billBean.getLiveCoinMoney());
            reqBean.setLiveCoinRatio(billBean.getLiveCoinRatio());
            reqBean.setSellerCoin(billBean.getSellerCoin());
            
            reqBean.setHstatus(billBean.getHstatus());
            reqBean.setLdate(billBean.getLdate()==null?null:dateFormat.format(billBean.getLdate()));
        
            reqBean.setMiketype(billBean.getMikeType());
            reqBean.setBfirst(billBean.getBfirst());
            reqBean.setIntegral(billBean.getIntegral());
            reqBean.setLedgerMode(billBean.getLedgerMode());
            
            reqBean.setParentXmerUid(billBean.getTwoLevelXmerId());//设置上级寻蜜客UID
            //更新订单信息
            orderDao.modifyOrderInfo(reqBean);
        }
        catch (Exception e)
        {
            log.error("修改订单数据异常", e);
            throw new FailureException(ResponseState.ELSEEROR, "修改订单数据异常");
        }
        log.info("mdyOrderInfoProcess end......");
    }
   
    /**
     * 推送消息发送redis队列处理方法
     * @param resOrderMap [查询出来的订单信息]
     * @param pushType [推动类型;1为订单支付成功,2为分账成功]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void pushMsgRedisProcess(Map<String,Object> resOrderMap,String pushType)
    {
        try
        {
        //组装推送消息发送redis队列的数据格式
        Map<String,String> pushMsgMap = new HashMap<String,String>();
        //推送消息类型;1为订单支付成功,2为分账成功
        pushMsgMap.put("type", pushType);
        //订单号
        pushMsgMap.put("bid", resOrderMap.get("bid").toString());
        //用户ID
        pushMsgMap.put("uid", resOrderMap.get("uid").toString());
        //用户手机号码
        pushMsgMap.put("phoneid", resOrderMap.get("phoneid")==null?"":resOrderMap.get("phoneid").toString());
        //商家ID
        pushMsgMap.put("sellerid", resOrderMap.get("sellerid").toString());
        //商家名称
        String sellername=resOrderMap.get("sellername").toString();
        sellername=sellername.replaceAll("&","");
        sellername=sellername.replaceAll("%", "");
        pushMsgMap.put("sellername", sellername);
        //消费金额
        pushMsgMap.put("money", resOrderMap.get("money").toString());
        //返利支付金额
        pushMsgMap.put("rebate", resOrderMap.get("rebate").toString());
        //平台补贴金额
        pushMsgMap.put("flat_money", resOrderMap.get("flat_money").toString());
        //写入redis时间
        pushMsgMap.put("sdate", resOrderMap.get("zdate").toString());
        //将MAP转换成JSON格式
        String pushMsgQueueMap = JSONObject.toJSONString(pushMsgMap);
        log.info("pushMsgRedisProcess sendRedis Key:"+pushMsgQueue+"===pushMsgRedisProcess sendRedis JSON:"+pushMsgQueueMap);

            //发送redis
            redisTemplate.opsForList().rightPush(pushMsgQueue,pushMsgQueueMap);
        }
        catch (RedisConnectionFailureException e)
        {
            //若抛出异常,则放入到本地缓存Ecache中
//            if(StringUtils.isNotBlank(pushMsgQueueMap))
//            {
//                Map<String,String> contentMap=new HashMap<String,String>();
//                contentMap.put("markKey", pushMsgQueue);
//                contentMap.put("markContent", pushMsgQueueMap);
//                contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
//                contentMap.put("addNumber","0");
//                //失败数据放入Ecache缓存中
//                exceptionHandle(contentMap);               
//            }
        }
        catch (Exception e)
        {
        	log.error("推送分账成功异常，",e);
            //若抛出异常,则放入到本地缓存Ecache中
//            if(StringUtils.isNotBlank(pushMsgQueueMap))
//            {
//                Map<String,String> contentMap=new HashMap<String,String>();
//                contentMap.put("markKey", pushMsgQueue);
//                contentMap.put("markContent", pushMsgQueueMap);
//                contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
//                contentMap.put("addNumber","0");
//                //失败数据放入Ecache缓存中
//                exceptionHandle(contentMap);               
//            }
        }
    }
 
    /**
     * 推送消息发送redis队列处理方法  2016年6月14日 新版本分账规则
     * @param resOrderMap [查询出来的订单信息]
     * @param pushType [推动类型;1为订单支付成功,2为分账成功]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void pushMsgRedisProcess2(Map<String,Object> resOrderMap,String pushType)
    {
//    	private Long bid;                    // 订单编号，12位长度。如：14 1015  111111。格式6位年月日加6为顺序数',		ledger:  id,                        
//    	private int uid;                     // 用户ID',													ledger:  memberid,                  
//    	private String nname;                // 用户昵称',													ledger:  membername,
//    	private int genussellerid;           // 所属商家ID',												ledger:  genussellerid,                       
//    	private String genusname;            // 所属商家名称',												ledger:  genusname,
//    	private int jointid;                 // 所属商家所属合作商ID',										ledger:  bpartnerid,                                
//    	private String corporate;            // 所属商家所属合作商名称',										ledger:  bpartnername, 
//    	private int sellerid;                // 消费商家ID',												ledger:  sellerid,                                 
//    	private String sellername;           // 消费商家名称',												ledger:  sellername, 
//    	private int consume_jointid;      	 // 消费商家所属合作商ID',										ledger:  cpartnerid,                        
//    	private String consume_corporate;    // 消费商家所属合作商名称',										ledger:  cpartnername,
//    	private int mikeid;				 	 // 寻蜜客ID',												ledger:  mikeid,          
//    	private String mikename;			 // 寻蜜客名称',												ledger:  mikename,
//    	private double baseagio;             // 下单折扣，下单时商家所执行的折扣',									ledger:  discount,             
//    	private String number;               // 支付流水号',												ledger:  paycode, 
//    	private String payid;                // 支付ID，支付接口产生 用于查询支付记录使用',							ledger:  payid,                   
//    	private int paytype;              	 // 支付方式',													ledger:  paytype, 
//    	private int area;                 	 // 所消费地区的编号 如南山区 的编号',									ledger:  areaid,                   
//    	private int mike_type;            	 // 向蜜客类型  0 未绑定任何向蜜客  1 用户向蜜客 2中脉寻蜜客     3用户向蜜客(过期)',	ledger:  miketype, 
//    	private double money;                // 消费总金额',												ledger:  expense,                 
//    	private double rebate;               // 本单可返利金额',												ledger:  rebate,
//    	private int is_virtual=1;            // 是否虚拟订单，默认为0 不是虚拟订单   1虚拟订单,							ledger:  isfictitious,       
//    	private double flat_agio;            // 平台补贴占比，默认为0  平台做促销活动时此字段表示平台给用户的折扣补贴',			ledger:  subsidy,             
//    	private double flat_money;           // 平台补贴金额												ledger:  subsidy_money,      
//    	private Date zdate;                	 // 支付时间                                                                         						 	ledger:  zdate,                   
//    	private double profit;               // 返利支付金额                                                                     						 	ledger:  profit,                 
//    	private double commision;            // 佣金支付金额                                                                     						 	ledger:  commision,           
//    	private double payment;              // 需支付金额                                                                       						 	ledger:  payment,               
//    	private double give_money;           // 赠送支付金额                                                                     						 	ledger:  givemoney,          
//    	private double cuser;                // 优惠券支付金额                                                                   						 	ledger:  cuser,                   
//    	private double cdenom;               // 优惠卷面额总数                                                                   						 	ledger:  cdenom,                 
//    	private double ratio;                // 佣金补贴比例(商户补贴比例)                                   	ledger:  ratio_subsidy,                     
//    	private double ratio_money;          // 佣金补贴金额                                                                     						 	ledger:  ratio_money                       
//    	private int status;				 	 // 订单状态                                                                        						 	ledger:        
//    	private int hstatus; 			 	 // 审核状态                                                                        						 	ledger:                            
//    	private String commission;           // 分账情况Json   											ledger:commisson																							ledger:  commission, 
    	
        //组装推送消息发送redis队列的数据格式
        Map<String,String> pushMsgMap = new HashMap<String,String>();
        //推送消息类型;1为订单支付成功,2为分账成功
        pushMsgMap.put("type", pushType);
        //订单号
        pushMsgMap.put("bid", resOrderMap.get("bid").toString());
        //用户ID
        pushMsgMap.put("uid", resOrderMap.get("uid").toString());
        //用户名
        pushMsgMap.put("nname", resOrderMap.get("nname").toString());
        //用户手机号码
        pushMsgMap.put("phoneid", resOrderMap.get("phoneid").toString());
        //商家ID
        pushMsgMap.put("sellerid", resOrderMap.get("sellerid").toString());
        //商家名称
        String sellername=resOrderMap.get("sellername").toString();
        sellername=sellername.replaceAll("&","");
        sellername=sellername.replaceAll("%", "");
        pushMsgMap.put("sellername", sellername);
        //消费金额
        pushMsgMap.put("money", resOrderMap.get("money").toString());
        //返利支付金额
        pushMsgMap.put("rebate", resOrderMap.get("rebate").toString());
        //平台补贴金额
        pushMsgMap.put("flat_money", resOrderMap.get("flat_money").toString());
        //写入redis时间
        pushMsgMap.put("sdate", resOrderMap.get("zdate").toString());
        //将MAP转换成JSON格式
        String pushMsgQueueMap = JSONObject.toJSONString(pushMsgMap);
        log.info("pushMsgRedisProcess sendRedis Key:"+pushMsgQueue+"===pushMsgRedisProcess sendRedis JSON:"+pushMsgQueueMap);
        try
        {
            //发送redis
            redisTemplate.opsForList().rightPush(pushMsgQueue,pushMsgQueueMap);
        }
        catch (RedisConnectionFailureException e)
        {
            //若抛出异常,则放入到本地缓存Ecache中
            if(StringUtils.isNotBlank(pushMsgQueueMap))
            {
                Map<String,String> contentMap=new HashMap<String,String>();
                contentMap.put("markKey", pushMsgQueue);
                contentMap.put("markContent", pushMsgQueueMap);
                contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
                contentMap.put("addNumber","0");
                //失败数据放入Ecache缓存中
                exceptionHandle(contentMap);               
            }
        }
    }
 
    /**
     * 分账系统订单查询
     * @param bid [订单编号ID]
     * @return Map<String, String> [返回参数]
     * @throws FailureException
     * @throws TException
     */
    @Override
    public Map<String, String> getOrderInfo(long bid) throws FailureException,
            TException
    {
        log.info("getOrderInfo start::" + bid);
        Map<String, String> result = new HashMap<String, String>();
        Integer windcontrol = 1;		/*  1：无风险的订单；  2：金额大于3000 的订单 ;3：5分钟内提交3次 的订单 ;4：商户折扣变化的订单 ;5：商户折扣  > 50%  订单金额 > 1000 */
        try
        {
        	/* 判断该订单时候处在退款中   如果还在退款中这不能操作*/
        	Integer rstauts = orderDao.getRefund(bid);
        	if(rstauts != null){
        		result.put("rstauts", rstauts+"");
        		if(rstauts ==2 || rstauts == 5){
        			log.info("getOrderInfo 用户已取消退款，状态为:" + rstauts);
        		}else{
	        		log.info("getOrderInfo 订单申请退款处理中，状态为:" + rstauts);    
	        		return result;
        		}
        	}
        	
            Map<String, Object> data = orderDao.getOrderInfo(bid);
            if(data == null){
            	data = new HashMap<String, Object>();
            	result.put("bstauts", "1");
            	return result;
            }else{
            	//更新订单状态为11 (表示商户已经触发分账操作)
            	HashMap<String, String> map = new HashMap<String, String>();
            	map.put("bid", bid+"");
            	map.put("hstatus", 11+"");
            	map.put("status", 9+"");
            	orderDao.modifyCommission(map);
            	
            	//计算风险识别
            	Double expense = NumberUtils.toDouble(data.get("expense")+"");			/* 订单金额*/
            	Double corrbaseagio = NumberUtils.toDouble(data.get("discount")+"");	/* 订单折扣*/
            	if( expense >= WIND_CONTROL_MONEY){		/* 查过3000的订单进入风控*/
            		log.debug("订单金额大于3000：订单实际金额："+data.get("expense"));
            		windcontrol = 2;
            	}else{            		
            		if(corrbaseagio < 0.5 && expense > 1000.00){
            			log.debug("商户折扣  > 50%  订单金额 > 1000，折扣："+corrbaseagio+"，订单金额"+expense);
            			windcontrol = 5;		/* 商户折扣  > 50%  订单金额 > 1000*/
            		}else{            		
	            		Date enddate = (Date)data.get("zdate");		/* 该订单的时间为截止时间*/
	            		Date startdate = DateUtils.addMinutes(enddate, WIND_CONTROL_AMOUNT);
	            		Map<String,Object> parammap = new HashMap<String,Object>();
	            		parammap.put("sellerid", data.get("sellerid"));
	            		parammap.put("uid", data.get("memberid"));
	            		parammap.put("startdate", startdate);
	            		parammap.put("enddate", enddate);
	            		Integer count = orderDao.findBillBySelloruser(parammap);
	            		log.debug("查询该订单的5分钟之内的订单，起始时间为："+DateUtil.dateFormat1(startdate)+";终止时间为："+DateUtil.dateFormat1(enddate)+"；查询结果条数："+count);
	            		if(NullU.nvl(count) > 0 && count > WIND_CONTROL_AMOUNT_COUNT){
	            			windcontrol = 3;						/* 5分钟内提交3次 的订单*/
	            		}else{										/*于上一笔订单折扣发生变化*/
	            			Double baseagio = orderDao.findPreviousBillBySell(parammap);
	            			if(NullU.nvl(baseagio) > 0){		            			
		            			log.debug("查询该订单的折扣是否变化，改订单折扣："+corrbaseagio+";上一笔订单折扣："+baseagio);
		            			if(!baseagio.equals(corrbaseagio)){
		            				windcontrol = 4;				/* 商户折扣变化的订单*/
		            			}
	            			}
	            		}
            		}
            	}
            	//查询优惠劵信息
            	List<Map<String,Object>> coupons  = orderDao.getOrderCoupon(bid);
            	result.put("coupons", JSON.toJSONString(coupons));
            }
            
            result.putAll(MapUtil.formatMapStr(data));
            result.put("windcontrol", windcontrol+"");	/* 风险标识*/
            /*  获取消费商家信息 */
            Map<String,String> sellerMap = orderDao.findSellerByid(NumberUtils.toLong(result.get("sellerid")));
            sellerMap = sellerMap == null ? new HashMap<String, String>() : sellerMap;
            result.put("sellerindustry", NullU.nvl(sellerMap.get("typename")));				/* 消费商家行业*/
            result.put("sellerarea", NullU.nvl(sellerMap.get("area")));						/* 消费商家区域id*/
            
            Long sellerarea = NumberUtils.toLong(sellerMap.get("area"));
            String sellerareaname = orderDao.findAreaByid(sellerarea);				/* 消费区域名称*/
            result.put("sellerareaname", NullU.nvl(sellerareaname));
            
            Integer miketype = NumberUtils.toInt(result.get("miketype"));
            Long userarea = 0l;					/* 向密客区域id*/
            String userareaname="无区域";				/* 向密客名称*/
            if(miketype == 2){		//商家向密客
            	 Map<String,String> sellerHcMap = orderDao.findSellerByid(NumberUtils.toLong(result.get("mikeid")));	//获取商家向密客信息
            	 sellerHcMap = sellerHcMap == null ? new HashMap<String, String>() : sellerHcMap;
            	 userarea = NumberUtils.toLong(sellerHcMap.get("area"));
                 userareaname = orderDao.findAreaByid(userarea);				/* 消费区域名称*/
            }else if(miketype == 1 || miketype == 3){					//用户向密客
            	Map<String,Object> userHcMap = orderDao.findAreaOrHc(NumberUtils.toLong(result.get("mikeid")));	//获取商家向密客信息
            	userHcMap = userHcMap == null ? new HashMap<String, Object>() : userHcMap;
            	userarea =  NumberUtils.toLong(userHcMap.get("area")+"");
            	userareaname = userHcMap.get("title")+"";
            }
            //Long consumeJointid = NumberUtils.toLong(data.get("cpartnerid")+"");		/* 消费合作商id*/
            //Long jointid = NumberUtils.toLong(data.get("bpartnerid")+"");				/* 所属合作商id*/
            result.put("userarea", userarea+"");
            result.put("userareaname", NullU.nvl(userareaname));
            result.put("isinterregional", userarea.equals(sellerarea) ? "0" : "1");	//是否跨区交易(0:否 1：是)
            
            //查询商户信息
            Map<String,Object> reSellerMap=orderDao.querySellerInfos(data.get("sellerid").toString());
            if(null!=reSellerMap)
            {
                //获取平台扣款比例
                if(reSellerMap.containsKey("debit") && null!=reSellerMap.get("debit"))
                {
                    result.put("debit", String.valueOf(reSellerMap.get("debit")));
                }
            }
        }
        catch (Exception e)
        {
        	log.info("查询订单错误,订单id:"+bid);
            log.error("查询订单错误,订单id"+bid+"错误："+e.getMessage());
            throw new FailureException(0, "系统错误!"+e.getMessage());
        }
        log.debug("查询订单结果:" + result);
        log.info("getOrderInfo end::" + result);
        return result;
    }
    
    /**
     * 更新订单流程接口
     * @param param [请求参数]
     * @return resCode [是否成功标识]
     * @throws FailureException [异常返回参数]
     * @throws TException
     */
    @Override
    public Map<String, String> modifyOrderProcess(Map<String, String> paraMap)
            throws FailureException
    {
        Map<String, String> resCodeMap = new HashMap<String, String>();
        log.info("modifyOrderProcess start paramMap::" + paraMap);
        long starTime = System.currentTimeMillis();
        Iterator<Entry<String, String>> iterator = paraMap.entrySet().iterator();
        List<String> resBidList = new ArrayList<String>();
       while(iterator.hasNext())
       {
           Map.Entry<String, String> mapEntry = iterator.next();
           String bid = mapEntry.getKey();
           
           //调用DAO层的验证订单是否存在方法
           int resOrderCount = orderDao.valideBillByBid(Long.valueOf(bid));
           if(resOrderCount==0)
           {
               resCodeMap.put("bid", bid);
               resCodeMap.put("resCode", "107");
               resCodeMap.put("resMsg", "没有获取到订单号为:【" + bid+ "】订单");
               log.error("没有获取到订单号为:【" + bid + "】订单");
               return resCodeMap;
           }else
           {
               resBidList.add(bid);
           }
       }
        //调用批量检查订单信息
        resCodeMap = checkOrderProcess(resBidList,paraMap);
        long endTime = System.currentTimeMillis();
        log.info("执行批量订单流程处理总耗时:"+(endTime-starTime)+"ms");
        log.info("modifyOrderProcess end......");
        return resCodeMap;
    }
    
    /**
     * 批量订单验证方法
     * @param resBidList [订单号LIST集合]
     * @param paraMap [请求参数MAP集合]
     * @return Map<String,String> [返回resCode]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> checkOrderProcess(List<String> resBidList,Map<String, String> paraMap)
    {
        Map<String, String> resCodeMap = new HashMap<String,String>();
        //调用批量查询订单信息接口
        List<Map<String, Object>> resListMap = orderDao.batchQueryBillAll(resBidList);
        //循环遍历LIST集合进行数据校验
        for (Map<String, Object> resOrderMap : resListMap)
        {
            //订单号
            String bid = resOrderMap.get("bid").toString();
            //后台处理状态
            String hstatus = resOrderMap.get("hstatus").toString();
            //订单状态
            String status = resOrderMap.get("status").toString();
            //判断订单是否已分账
            if ("2".equals(status) && "9".equals(hstatus))
            {
                resCodeMap.put("bid", bid);
                resCodeMap.put("resCode", "108");
                resCodeMap.put("resMsg", "订单号为:【" + bid + "】已分账");
                log.error("订单号为:【" + bid + "】已分账");
                return resCodeMap;
            }
        }
        //批量更新订单流程业务逻辑处理方法
        resCodeMap = batchOrderBusiProcess(paraMap, resListMap);
        return resCodeMap;
    }
    
    /**
     * 批量更新订单流程业务逻辑处理方法
     * @param paraMap [请求参数的MAP结婚]
     * @param resListMap [查询订单信息LIST集合]
     * @return Map<String,String> [返回resCode]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> batchOrderBusiProcess(Map<String, String> paraMap,List<Map<String, Object>> resListMap)
    {
        Map<String, String> resCodeMap = new HashMap<String, String>();
        Map<String, String> processReqMap = new HashMap<String, String>();
        //循环遍历校验通过的订单信息
        for (Map<String, Object> resOrderMap : resListMap)
        {
            try
            {
                String bid = resOrderMap.get("bid").toString();
                String hstatus = paraMap.get(bid);
                
                //当传递的后台处理状态为其它状态时,照样进行更新为MAP中的数据
                processReqMap.put("bid", bid);
                processReqMap.put("hstatus", hstatus);
                
                //当业务系统的后台处理状态为9已分账状态时,同时更新订单状态为2已分账,是否到账标识更新为已到账
                if ("9".equals(hstatus))
                {
                    processReqMap.put("bid", bid);
                    processReqMap.put("hstatus", "9");
                    processReqMap.put("status", "2");
                    processReqMap.put("isaccount", "1");
                    
                    SimpleDateFormat bfmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fdate = bfmt.format(new Date());
                    processReqMap.put("fdate", fdate);
                    
                    //调用增加合作商员工提成记录方法
                    this.addPercentageRecord(resOrderMap);
                    
                    //订单处理记录接口
                    Map<String, String> reqMap = new HashMap<String, String>();
                    reqMap.put("bid", bid);
                    reqMap.put("status", "8");
                    reqMap.put("explains", "订单号"+bid+"已分账（分账系统）成功");
                    this.insertBillRecord(reqMap);
                    
                    //获取订单信息
                    Map<String,Object> reOrderMap = orderDao.selectBillAll(bid);
                    //推送消息类型为2,即分账成功
                    String ledgerPushType="2";
                    //分账成功,调用推送消息发redis队列处理方法
                    this.pushMsgRedisProcess(reOrderMap,ledgerPushType);
                }
                orderDao.modifyOrderProcess(processReqMap);
                //若更新成功，则返回状态码为0，否则返回状态码为1
                resCodeMap.put("bid", "");
                resCodeMap.put("resCode", "0");
                resCodeMap.put("resMsg", "处理成功");
            }
            catch (FailureException e)
            {
                resCodeMap.put("bid", resOrderMap.get("bid").toString());
                resCodeMap.put("resCode", "2");
                resCodeMap.put("resMsg", "批量更新订单流程的业务处理出现异常");
                log.error("批量更新订单流程的业务处理出现异常",e);
                return resCodeMap;
            }
        }
        return resCodeMap;
    }


    /**
     * 订单退款更新状态接口
     * @param paraMap [请求参数][订单编号,订单状态,订单退款时间]
     * @return Map<String, String> [返回订单编号]
     * @throws FailureException [异常返回参数]
     * @throws TException [异常返回参数]
     */
    @Override
    public Map<String, String> refundOrderInfo(Map<String, String> paraMap)
            throws FailureException, TException
    {
        log.info("refundOrderInfo start::" + paraMap);
        try
        {
            Map<String, String> refundMap = new HashMap<String, String>();
            //订单号
            refundMap.put("bid", paraMap.get("bid"));
            SimpleDateFormat edateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            refundMap.put("edate", edateFormat.format(new Date()));
            //若退款状态为9(即为平台退款成功),则更新订单状态为5(已退款(退款成功))
            //更新订单退款申请为9(即为平台退款成功),更新退款时间
            if (paraMap.get("refundStatus").equals("9"))
            {
                //订单退款申请状态
                refundMap.put("refundStatus", "9");
                //订单状态
                refundMap.put("orderStatus", "5");
                
                //订单退款成功后,进行插入到订单处理记录
                if(paraMap.get("refundStatus").equals("9"))
                {
                    //订单处理记录接口
                    Map<String,String> reqMap = new HashMap<String,String>();
                    reqMap.put("bid", paraMap.get("bid"));
                    reqMap.put("status","5");
                    reqMap.put("explains", "订单号"+paraMap.get("bid")+"退款（支付服务）成功");
                    this.insertBillRecord(reqMap);
                }
                
                //查询已使用的优惠券与订单关系
                List<Map<String, Object>> resListMap=orderDao.getOrderCouponList(paraMap.get("bid"));
                //若查询的优惠券只有一条已使用，则进行更新为未使用；反之，则不无需更新
                if(resListMap.size()==1 && resListMap.get(0).containsKey("bid") && 
                   resListMap.get(0).get("bid").toString().equals(paraMap.get("bid")))
                {
                    //根据优惠券序列码修改用户使用状态
                    orderDao.mdyCouponUserStatus(resListMap.get(0).get("cdid").toString());
                }
                
                BillBean billBean = orderDao.getBillBean(paraMap.get("bid"));
                log.info("获取的订单信息："+billBean);
                //收回赠送的商家优惠券
                getBackUserCoupon(billBean);
                //将已使用的商家优惠券更新为未使用
                recoverUserCoupon(billBean);
            }
            
            //若退款状态为10(即为平台退款失败),则更新订单状态为12(平台退款失败)
            //更新订单退款申请状态为10(即为平台退款失败),更新退款申请失败原因
            else if (paraMap.get("refundStatus").equals("10"))
            {
                //订单退款申请状态
                refundMap.put("refundStatus", "10");
                //订单退款申请失败原因
                refundMap.put("remarks", paraMap.get("remarks"));
                //订单退款状态
                refundMap.put("orderStatus", "12");
            }
            //调用DAO层的订单退款更新状态接口
            orderDao.refundOrderInfo(refundMap);
        }
        catch (Exception e)
        {
            log.error("订单退款异常", e);
            throw new FailureException(ResponseState.REFUNDFAIL, "订单退款失败");
        }
        log.info("refundOrderInfo end::" + paraMap);
        return paraMap;
    }
    
    /**
     * 增加合作商员工提成记录
     * @param paramMap [请求参数]
     * @param ledgerBean [分账系统的实体Bean]
     * @param bid [订单编号]
     * @return boolean [返回是否插入成功]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void addPercentageRecord(Map<String, Object> paramMap) throws FailureException
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
            throw new FailureException(ResponseState.ORDERFAIL, "添加合作商员工提成记录失败");
        }
        log.info("addPercentageRecord end......");
    }
    
    
    /**
     * 验证订单是否存在
     * 提供给分账系统调用验证
     * @param bid [订单号]
     * @return boolean [返回boolean型]
     * @throws FailureException [异常返回说明]
     * @throws TException [异常返回说明]
     */
    @Override
    public boolean valideBill(long bid) throws FailureException, TException
    {
        try
        {
            return orderDao.valideBillByBid(bid)>=1;
        }
        catch (Exception e)
        {
            log.error("验证订单异常",e);
            throw new FailureException(ResponseState.ORDERNULL, "验证订单出错");
        }
    }
    
    private boolean verifyOrderParam(XmnOrderParamV2 orderParam,BillBean billBean){
    	
    	//订单钱包支付部分
    	if(!orderParam.getUid().equals(billBean.getUid()+"")){
    		log.info("订单用户没对应");
    		return false;
    	}
    	if(billBean.getMoney().compareTo(new BigDecimal(orderParam.getMoney()))!=0){
    		log.info("订单总金额没对应");
    		return false;
    	}
    	if(billBean.getCalculateRealPreferentialAmount().compareTo(new BigDecimal(orderParam.getPreferential()))!=0){
    		log.info("订单优惠金额没对应");
    		return false;
    	}
    	BigDecimal allAmount=(new BigDecimal(orderParam.getPreferential())).add(new BigDecimal(orderParam.getPayamount()));
    	if(allAmount.compareTo(new BigDecimal(orderParam.getMoney()))!=0){
    		log.info("订单金额核对没对应");
    		return false;
    	}
    	
    	BigDecimal realPayAmount;
    	if(new BigDecimal(orderParam.getLiveCoin()).compareTo(BigDecimal.ZERO)>0//鸟币支付 订单应付 =（鸟币支付 + 现金支付/基数*商家折扣）* 基数 / 折扣
    			||new BigDecimal(orderParam.getSellerCoin()).compareTo(BigDecimal.ZERO)>0){
    		BigDecimal cash = new BigDecimal(orderParam.getSamount())
			.add(new BigDecimal(orderParam.getGiveMoney()))
			.add(new BigDecimal(orderParam.getCommision()))
			.add(new BigDecimal(orderParam.getProfit()));//现金支付部分
    		realPayAmount = (
    				cash.divide(new BigDecimal(orderParam.getBase()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(orderParam.getDiscounts()))//现金支付/基数*商家折扣
    				.add(new BigDecimal(orderParam.getLiveCoin())
    				.add(new BigDecimal(orderParam.getSellerCoin())))//+鸟币支付
    						).multiply(new BigDecimal(orderParam.getBase())).divide(new BigDecimal(orderParam.getDiscounts()),2,BigDecimal.ROUND_HALF_UP);//* 基数 / 折扣
    		BigDecimal subtractAmount = realPayAmount.subtract(new BigDecimal(orderParam.getPayamount())).abs();
    		/*   因支付传递过来的金额核对常出现1分钱的偏差，为保证订单通过，允许其有1分钱的偏差      */
        	if(subtractAmount.compareTo(new BigDecimal("0.01"))>0){
        		log.info("订单实付金额核对没对应:"+realPayAmount+" - "+orderParam.getPayamount());
        		return false;
        	}
    	}else{
    		realPayAmount = (new BigDecimal(orderParam.getSamount()))
        			.add(new BigDecimal(orderParam.getGiveMoney()))
        			.add(new BigDecimal(orderParam.getCommision()))
        			.add(new BigDecimal(orderParam.getProfit()))
        			.add(new BigDecimal(orderParam.getLiveCoinArrivedMoney()));
        	if(realPayAmount.compareTo(new BigDecimal(orderParam.getPayamount()))!=0){
        		log.info("订单实付金额核对没对应:"+realPayAmount+" - "+orderParam.getPayamount());
        		return false;
        	}
    	}
    

    	
    	BigDecimal walletAmount = (new BigDecimal(orderParam.getGiveMoney()))
    			.add(new BigDecimal(orderParam.getCommision()))
    			.add(new BigDecimal(orderParam.getProfit()))
    			.add(new BigDecimal(orderParam.getLiveCoinArrivedMoney()));
    	if(orderParam.getIsbalance().equals("1")&& decimalCompareZero(walletAmount.toString())<=0){
    		log.info("订单钱包金额核对没对应");
    		return false;
    	}
    	
		if(orderParam.getPaytype().equals("1000000")
			&& StringUtils.isBlank(orderParam.getPayid())){
			if(billBean.getCalculateRealPayAmount().compareTo(ZERO)==0){
			}else{
				log.info("使用钱包余额支付需提供支付流水号");
	    		return false;
			}
	    }
    	return true;
    }
    /**
     * 查询商家的累积消费流水总额接口
     * @param sellerId [消费商家ID]
     * @return resWaterTotal [商家的累积消费流水总额]
     * @throws FailureException
     * @throws TException
     */
    @Override
    public double querySellerWaterTotal(String sellerId)
            throws FailureException, TException
    {
        log.info("querySellerWaterTotal start sellerid:" + sellerId);
        double resWaterTotal = 0;
        try
        {
            if (StringUtils.isNotEmpty(sellerId))
            {
                //调用DAO层的查询商家的累积消费流水总额接口
                resWaterTotal = orderDao.querySellerWaterTotal(sellerId);
            }
        }
        catch (Exception e)
        {
            log.error("查询商家的累积消费流水总额异常", e);
            throw new FailureException(ResponseState.ORDERFAIL,"查询商家的累积消费流水总额出错");
        }
        log.info("querySellerWaterTotal end resWaterTotal:" + resWaterTotal);
        return resWaterTotal;
    }
    
    /**
     * 获取调单前的订单信息
     * @param paraMap paraMap [查询的请求参数]
     * @return Map [查询结果MAP]
     * @throws FailureException
     * @throws TException
     */
    @Override
    public Map<String, String> queryAdjBeforeOrderInfo(Map<String, String> paraMap) 
            throws FailureException, TException
    {
    	return adjustOrderServiceImpl.queryAdjBeforeOrderInfo(paraMap);
    }
    
    /**
     * 查询调单后的订单信息
     * @param paraMap [查询的请求参数]
     * @return Map [查询结果MAP]
     * @throws FailureException
     * @throws TException
     */
    @Override
    public Map<String, String> queryAdjustOrderInfo(Map<String, String> paraMap)
            throws FailureException, TException
    {
    	return adjustOrderServiceImpl.queryAdjustOrderInfo(paraMap);
    }
    

    /**
     * 更新调单的订单信息
     * @param paraMap [查询的请求参数]
     * @return Map [查询结果MAP]
     * @throws FailureException
     * @throws TException
     */
    @Override
    @Transactional(readOnly=false,isolation=Isolation.DEFAULT,
    propagation=Propagation.REQUIRED,rollbackFor=FailureException.class)
    public Map<String, String> modifyAdjustOrderInfo(Map<String, String> paraMap)
            throws FailureException, TException
    {
    	return adjustOrderServiceImpl.modifyAdjustOrderInfo(paraMap);
    }
 
    
    /**
     * 订单处理记录方法
     * @param reqMap [请求参数MAP]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertBillRecord(Map<String,String> reqMap)
    {
        log.info("inserBillRecord param start:"+reqMap);
        try
        {
            OrdRecordBean reqBean = new OrdRecordBean();
            //订单号
            reqBean.setBid(reqMap.get("bid"));
            //订单处理状态
            reqBean.setStatus(Integer.valueOf(reqMap.get("status")));
            //订单处理说明
            reqBean.setExplains(reqMap.get("explains"));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            reqBean.setCdate(dateFormat.format(new Date()));
            //开始插入订单处理记录表
            orderDao.insertBillRecord(reqBean);
        }
        catch (Exception e)
        {
            log.error("订单处理记录异常",e);
        }
        log.info("inserBillRecord end");
    }
    
        
    /**
     * 
    * @Title: exceptionHandle
    * @Description: 发送redis出错把数据存入ehcache再处理
    * @return void    返回类型
    * @throws
     */
    public void exceptionHandle(Map<String,String> contentMap){
//    	//失败数据放入Ecache缓存中
//    	if(null!=contentMap){
//    		List<Map<String,String>> cl = null;
//    		/**
//    		 * 在多线程环境下，读和写的操作是分离的，这里可能存在多个线程往list写入数据，
//    		 * 缓存中的线程在进行读和修改操作，容易出现java.util.ConcurrentModificationException
//    		 * 因此可以使用CopyOnWriteArrayList 来解决此问题
//    		 */
//    		//Vector vt = null; //考虑到多线程使用Vector
//            if(null!=dataCache.get(FAULT_KEY)){
//            	Element element =dataCache.get(FAULT_KEY);
//            	cl = (CopyOnWriteArrayList<Map<String,String>>) element.getObjectValue();
//            }
//            if(null==cl)
//            {
//            	cl = new CopyOnWriteArrayList<Map<String,String>>();
//            	cl.add(contentMap);
//                //放入Ecache缓存中
//                Element addCache = new Element(FAULT_KEY, (Serializable) cl);
//                dataCache.put(addCache);
//                log.info("add cache content:"+contentMap.toString());
//            }else
//            {
//            	cl.add(contentMap);
//                log.info("add cache content:"+contentMap.toString());
//            }
//    	}	
    }
        
	
    /**
     * 更新订单信息服务接口
     * @param request [更新订单请求参数]
     * @return bid [返回订单编号ID]
     * @throws FailureException [异常返回参数]
     * @throws TException
     */
    @Override
    public String updateOrderInfo(UpdateOrderRequest request)
            throws FailureException, TException
    {
        String result = "";
        log.info("updateOrderInfo start::" + request);
        long starTime = System.currentTimeMillis();
        /*
         * 分账系统的分账类型与支付系统的分账类型的关系
         * 分账系统 1是手动分账 2是自动分账 
         * request中分账类型  1是自动分账 2是手动
         */
//        int ledgerType = request.getLedgertype();
       

        // 更新该订单之前,先获取该条订单是否存在
        BillBean billBean = orderDao.getBillBean(request.getBid());
        log.info("获取的订单信息："+billBean);
        // 若有该订单信息,则再进行下步操作
        if (null == billBean)
        {
            log.error("没有获取到订单号为:【" + request.getBid() + "】订单信息");
            throw new FailureException(ResponseState.ORDERNULL, "没有获取到订单号为:"+ request.getBid() + "订单信息");
        }
        
        // 订单状态
        int status = billBean.getStatus();
        // 若订单状态未更新为1待返现,则进行后面业务操作,反之，则无需再进行后面业务操作
        if (status >= 1)
        {
            log.error("该订单【" + request.getBid() + "】已重复支付");
            throw new FailureException(ResponseState.ORDER_READY_MODIFY,"该订单已重复支付");
        }
        
        try
        {
       	
        	Map<String,String> uMap = new HashMap<String,String>();
        	uMap.put("uid", billBean.getUid()+"");
        	
        	//绑定商家信息
        	Map<String,Object> urs = ursDao.getUrsAscription(uMap);
        	
        	//是否首单信息
            Map<String, String> resMikeMap = new HashMap<String, String>();
            resMikeMap.put("mikeType", "2");
            resMikeMap.put("bfirst", "0");
            
            //首单判断
            log.info("消费会员【"+billBean.getUid()+"】当前的所属信息:"+urs);
            if(urs.get("genussellerid") == null || Integer.parseInt(urs.get("genussellerid")+"")==0)
            {           
            	log.info("会员消费首单");
                resMikeMap.put("mikeType", "2");
                resMikeMap.put("bfirst", "1");
            }      
            
            SellerBean seller = sellerDao.getSellerInfo(billBean.getSellerid());
            
            //准备最新订单信息
            modifyBillBean(billBean,request,resMikeMap);
            
            log.info("更新后，订单信息："+billBean);

            //将优惠券标记为已使用
            usedUserCoupon(billBean);
            
            //更新订单信息
            mdyOrderInfoProcess2(billBean);
            
            //向用户赠送积分
            consumerRebate(billBean);
            
            //向用户赠优惠券
            //consumerCoupon(billBean);
            
            //向用户赠商家券
            //if(billBean.getCouponType()==null){
            	consumerSellerCoupon(billBean);
            //}
            
            //向用户赠商家红包
            consumerRedPacket(billBean);
            
            //向推荐人赠商家推荐人红包
            consumerRecommendRedPacket(billBean);
            
            //使用商家消费满减活动
            consumerFullreduction(billBean);
            
            //商家免费尝新活动
            consumerFreetry(billBean);
            
            //商家免费尝新活动
            consumerRoullete(billBean);
            
            //商家聚点活动
            consumerFcouspoints(billBean);
            
            //向主播送鸟蛋活动
            consumerAnchorLedger(billBean);
            
            //插入分账消息队列
            pushLedgerRedis(billBean,zdQueueKey,request.getLedgertype());

            if((urs.get("genussellerid") == null || "".equals(urs.get("genussellerid")))
            		&& billBean.getGenussellerid() != null)
            {
                //消费绑定商家
            	log.info("首单绑定商家");
            	sellerMikeBuilding(billBean);
            }
            
            //插入订单处理记录接口
             /*2016-02-23 删除插入订单记录信息，减少INSERT语句 提高订单处理速度*/
            Map<String, String> reqMap = new HashMap<String, String>();
            reqMap.put("bid", request.getBid());
            reqMap.put("status", "1");
            reqMap.put("explains", "订单号" + request.getBid() + "支付订单（支付系统）支付成功");
            insertBillRecord(reqMap);
            
            // 发送短信处理方法   微信推送消息，此处不判断手机号码是否存在
            if(request.getPayid()==0L && billBean.getPayid()!=null){
         	   //后门方式更新已支付的订单状态，不给商家发送信息
            }else{
	            if(isCurrentOrder(billBean.getZdate())){
	            	sendSms(billBean, request.getPhoneid());
	            }
            }
            
            // 推送类型,1为订单支付成功
            String orderPushType = "1";
            
            // 订单支付成功后,调用推送消息发送redis队列方法
           this.pushMsgRedisProcess2(billBean, orderPushType);
            
           // 发送短信至商家
           if(request.getPayid()==0L && billBean.getPayid()!=null){
        	   //后门方式更新已支付的订单状态，不给商家发送信息
           }else{
        	   if(seller != null){
                   JSONObject json = JSON.parseObject(billBean.getCommission());  
                   String str=json.getString("seller_amount");
            	   sendSmsForSeller(billBean.getSellerid(),billBean.getBid()+"",str); 
               }
           }
           updateUserActionQueue(billBean);
            // 更新订单接口流程操作完成后,返回订单号
            result = request.getBid();
            
            long endTime = System.currentTimeMillis();
            log.info("更新订单接口总耗时:" + (endTime - starTime) + "ms");
            
            //发送订单流水记录消息
            sendBillRecordMQ(result);
        }
        catch (Exception ex)
        {
            log.error("更新订单接口服务异常", ex);
            throw new FailureException(ResponseState.ORDERFAIL, "更新订单接口服务异常");
        }
        log.info("updateOrderInfo end......");
        return result;
    }
    

    /**
     * 获取订单的分账信息
     * @Title: getLedgerBill 
     * @Description:
     */
	private BillEntity getLedgerBill(BillBean billBean){
		BillEntity ledgerBill = new BillEntity();
		
		ledgerBill.setArea(Integer.valueOf(billBean.getArea()));
		ledgerBill.setBaseagio(billBean.getBaseagio());
		ledgerBill.setBid(billBean.getBid());
		ledgerBill.setCdenom(billBean.getCdenom().doubleValue());
		ledgerBill.setCommision(billBean.getCommision().doubleValue());
		ledgerBill.setCommission(billBean.getCommission());
		ledgerBill.setConsume_corporate(billBean.getConsumeCorporate());
		ledgerBill.setConsume_jointid(billBean.getConsumeJointid()==null?0:billBean.getConsumeJointid());	//消费合作商
		ledgerBill.setCorporate(billBean.getCorporate());
		ledgerBill.setCuser((billBean.getCuser()).doubleValue());
		ledgerBill.setFlat_agio(billBean.getFlatAgio().doubleValue());
		ledgerBill.setFlat_money(billBean.getFlatMoney().doubleValue());
		ledgerBill.setGenusname(billBean.getGenusname());
		ledgerBill.setGenussellerid(billBean.getGenussellerid());
		ledgerBill.setGive_money(billBean.getGiveMoney().doubleValue());
		ledgerBill.setHstatus(billBean.getHstatus());
		ledgerBill.setIs_virtual(billBean.getIsVirtual());
		ledgerBill.setJointid(billBean.getJointid()==null?0:billBean.getJointid());		//所属合作商
		ledgerBill.setMike_type(billBean.getMikeType());
		ledgerBill.setMikeid(billBean.getXmerUid());
		ledgerBill.setMikename(billBean.getMikename());
		ledgerBill.setMoney(billBean.getMoney().doubleValue());
		ledgerBill.setNname(billBean.getNname());
		ledgerBill.setNumber(billBean.getNumber());
		ledgerBill.setPayid(billBean.getPayid());
		ledgerBill.setPayment(billBean.getPayment().doubleValue());
		ledgerBill.setPaytype(Integer.parseInt(billBean.getPaytype()));
		ledgerBill.setParentMikeId(billBean.getTwoLevelXmerId());
		ledgerBill.setParentMikeName(billBean.getTwoLevelXmerName());
		ledgerBill.setTopMikeId(billBean.getOneLevelXmerId());
		ledgerBill.setTopMikeName(billBean.getOneLevelXmerName());
		ledgerBill.setProfit(billBean.getProfit().doubleValue());
		ledgerBill.setRatio(billBean.getRatio());
		ledgerBill.setRatio_money(billBean.getRatioMoney().doubleValue());
		ledgerBill.setRebate(billBean.getRebate().doubleValue());
		ledgerBill.setSellerid(billBean.getSellerid());
		ledgerBill.setSellername(billBean.getSellername());
		ledgerBill.setStatus(billBean.getStatus());
		ledgerBill.setUid(billBean.getUid());
		ledgerBill.setZdate(billBean.getZdate());
		ledgerBill.setProportion(billBean.getProportion());
		ledgerBill.setLedger_money(billBean.getLedgerAmount().doubleValue());
		ledgerBill.setWindcontrol(this.getWindcontrol(billBean));//风控
		ledgerBill.setcType(billBean.getCouponType()==null?0:billBean.getCouponType());
		ledgerBill.setUid_mb_ecno(billBean.getUidMbEcno());
		Integer saasChannel = billBean.getSaasChannel();
		ledgerBill.setSaas_channel(saasChannel==null?null:saasChannel.toString());
		return ledgerBill;
	}
	
    /**
     * 商家向蜜客处理,首单消费绑定商家
     * @param request [请求参数]
     * @throws FailureException [异常返回参数]
     * @param resOrderMap [订单信息MAP]
     * @return void [返回类型说明]
     */
    public Map<String,String> sellerMikeBuilding(BillBean billBean)
    {
        Map<String, String> paraMap = new HashMap<String, String>();
        
        log.info("sellerMikeProcess start:" + billBean);
        try
        {
            paraMap.put("bid", billBean.getBid().toString());
            paraMap.put("genussellerid", String.valueOf(billBean.getGenussellerid()));
            String genusname = billBean.getGenusname().replaceAll("&","");
            paraMap.put("genusname", genusname);
            paraMap.put("jointid", billBean.getJointid()==null?null:String.valueOf(billBean.getJointid()));
            paraMap.put("corporate", billBean.getCorporate());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String attachTime = dateFormat.format(new Date());
            paraMap.put("attach_time", attachTime);
            paraMap.put("uid", String.valueOf(billBean.getUid()));
            paraMap.put("addNum", "0");
            paraMap.put("mike_type", "2");
            /*
             * 2016年6月6日 改业务服务直接修改用户所属信息
             */
            int count = ursDao.updateUrsInfo(paraMap);
            if(count != 0){
            	log.info(billBean.getUid()+"本次消费是首单消费，现对其绑定所属商家信息");
            	try{
            		//删除用户缓存，用户登录接口写入
            		 redisTemplate.delete("USERID_" + billBean.getUid());
            	}catch (Exception e){
            		 log.error("删除用户信息缓存失败，异常信息：{"+ e.getMessage() +"},UID: {"+ billBean.getUid() +"}");
            	}
            }    
        } catch (Exception e){
            log.error("商家向蜜客处理异常", e);
        }
        
        log.info("sellerMikeProcess end......");
        return paraMap;
    }
    
    /**
     * 准备最新订单信息
     * @Title: modifyBillBean 
     * @Description:
     */
   void  modifyBillBean(BillBean billBean,UpdateOrderRequest request,Map<String,String> resMikeMap) throws FailureException{
       log.info("modifyBillBean start:" + billBean);
       try
       {
    	   if(request.getPayid()==0L && billBean.getPayid()!=null){
    	   }else{
	           billBean.setNumber(request.getNumber());
	           billBean.setPayid(String.valueOf(request.getPayid()));
	           billBean.setPaytype(request.getPaytype());
	           billBean.setPayment(ConversionTypeUtil.conversionToBigDecimal(request.getSamount()));
	           billBean.setCommision(ConversionTypeUtil.conversionToBigDecimal(request.getCommision()));
	           billBean.setProfit(ConversionTypeUtil.conversionToBigDecimal(request.getProfit()));
	           billBean.setGiveMoney(ConversionTypeUtil.conversionToBigDecimal(request.getGiveMoney()));
    	   }
           billBean.setStatus(1);
                      
           billBean.setZdate(billBean.getZdate()==null?new Date():billBean.getZdate());
           billBean.setIntegral(billBean.getCalculateRealZInteger().doubleValue());
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
	   			   getXmerName(issuesMap);
		           billBean.setOneLevelXmerId(issuesMap.get("oneLevelXmerId")==null?null:(Integer)issuesMap.get("oneLevelXmerId"));
		           billBean.setTwoLevelXmerId(issuesMap.get("twoLevelXmerId")==null?null:(Integer)issuesMap.get("twoLevelXmerId"));
		           billBean.setOneLevelXmerName(issuesMap.get("oneLevelXmerName")==null?null:(String)issuesMap.get("oneLevelXmerName"));
		           billBean.setTwoLevelXmerName(issuesMap.get("twoLevelXmerName")==null?null:(String)issuesMap.get("twoLevelXmerName"));
   			   }
   		   }   
           if(null!=resMikeMap)
           {
        	   billBean.setMikeType(resMikeMap.containsKey("mikeType")?Byte.valueOf(resMikeMap.get("mikeType")):null);
        	   billBean.setBfirst(resMikeMap.containsKey("bfirst")?Integer.valueOf(resMikeMap.get("bfirst")):null);
           }
           billBean.setLdate(new Date());

           calculateLedger(billBean);
           if(billBean.getLiveLedger()!=null && billBean.getLiveLedger()==1){
        	   request.setLedgertype(1);//若采用直播分账，则普通分账状态全置为1
           }
       }
       catch (Exception e)
       {
           log.error("准备订单数据异常", e);
           throw new FailureException(ResponseState.ELSEEROR, "准备订单数据异常");
       }
       log.info("modifyBillBean end......");
    }
   
    /**
     * 插入消费订单的分账队列
     * @Title: pushLedgerRedis 
     * @Description:
     */
    public void pushLedgerRedis(BillBean billBean,String ledgerQuery,int ledgerType){
    	if(billBean.getHstatus()!=10){
    		log.info("该商家订单不参与分账");
    		return ;
    	}
    	BillEntity ledgerBill = new BillEntity();
        try
        {
            //放入redis缓存中,值为类型为订单+订单编号+商家分账类型为自动
        	ledgerBill = this.getLedgerBill(billBean);
            //redisTemplate.opsForList().leftPush(zdQueueKey, JSONObject.toJSONString(ledgerBill));	
            
            //自动队列和订单号转换类型
            double bidDbl = Double.valueOf(billBean.getBid()+Math.random());
            JSONObject  json = (JSONObject) JSONObject.toJSON(ledgerBill);
            json.put("type", "1");
            json.put("ledger_type", ledgerType+"");	//1手动 2自动 分账
            json.put("zdate", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            log.info("写入自动分账队列名："+ledgerQuery+",写入内容："+json);
            redisTemplate.opsForZSet().add(ledgerQuery,json.toJSONString(), bidDbl);
        }
        catch (RedisConnectionFailureException ex)
        {
            //若发送或者连接redis失败，则放入到本地缓存Ecache中
            if(ledgerBill !=null)
            {
                Map<String,String> contentMap=new HashMap<String,String>();
                contentMap.put("markKey", zdQueueKey);
//                contentMap.put("markContent", autoQueueMap);
                contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
                contentMap.put("addNumber","0");
                //失败数据放入Ecache缓存中
                exceptionHandle(contentMap);                        
            }
        }
    }
    
    public void getXmerName(Map<String,Object> issuesMap){
    	Set<String> keys = issuesMap.keySet();
   		if(keys.size()>1){
   			String[] array = keys.toArray(new String[]{});
   			List<String> ids = new ArrayList<String>();
   			for(String name:array){
   				ids.add(issuesMap.get(name).toString());
   			}
   			List<Map<String,Object>> nameList = xmerDao.getXmerName(ids.toArray(new String[]{}));
	   		Iterator<String> it =  keys.iterator();
	   		Map<String,Object> nMap = new HashMap<String,Object>();
	   		while(it.hasNext()){
	   			String key1=it.next();
	   			for(Map<String,Object> nameMap:nameList){
	   				if(nameMap.get("uid").toString().equals(issuesMap.get(key1).toString())){
	   					String nameKey = key1.replace("Id", "Name");
	   					nMap.put(nameKey, nameMap.get("name"));
	   				}
	   			}
	   		}
	   		issuesMap.putAll(nMap);
   		}
    }
    
    
    /**
     * 发送短信处理
     * @param resOrderMap [订单信息MAP]
     * @param phoneid [手机号码]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void sendSms(BillBean billBean,String phoneid)
    {
        try
        {
            //组装短信发送
            Map<String,String> sendSmsMap = new HashMap<String,String>();
            //手机号码  或  微信openid
            sendSmsMap.put("phoneid", billBean.getPhoneid());
            //订单编号
            sendSmsMap.put("bid", String.valueOf(billBean.getBid()));
            
            //支付时间,交易时间
            sendSmsMap.put("sdate", dateFormat.format(new Date()));
            //消费总金额
            sendSmsMap.put("money", String.valueOf(billBean.getMoney()));
            //商户名称
            sendSmsMap.put("sellername", billBean.getSellername());
            
            String resellerid = orderSmsContent.replace("{0}",billBean.getSellername()==null?"":billBean.getSellername());
            String remoney = resellerid.replace("{1}", billBean.getMoney()==null?"":String.valueOf(billBean.getMoney()));
            double rebateMoney = Double.valueOf(billBean.getRebate().doubleValue());
            double flatMoney = Double.valueOf(billBean.getFlatMoney().doubleValue());
            String activityContent=String.valueOf(billBean.getActivityConent());
            //用正则表达式检查是否包含的有数字
            Matcher m = Pattern.compile(".*\\d+.*").matcher(activityContent.trim());
            
            //返利金额=本单可返利金额+平台补贴金额
            double orderRebate = rebateMoney + flatMoney;
            //保留两位小数
            DecimalFormat df = new DecimalFormat("######0.00");
            
            String smsContent="";
            //若返利为0
            if(orderRebate==0)
            {
                //若不包含数字，则短信内容不需要活动描述，反之，则短信内容需要活动描述
                if(m.matches()==false)
                {
                    smsContent=remoney.replace(",{2}","");
                    //短信内容
                    sendSmsMap.put("smscontent", smsContent);
                }else
                {
                    smsContent=remoney.replace("{2}", "商家参加"+activityContent+"返利活动,如符合条件,返利次日到账");
                    //短信内容
                    sendSmsMap.put("smscontent", smsContent);
                }
            }else
            {
                smsContent = remoney.replace("{2}","返利"+String.valueOf(df.format(orderRebate))+"元");
                //若不包含数字，则短信内容不需要活动描述，反之，则短信内容需要活动描述
                if(m.matches()==false)
                {
                    //短信内容
                    sendSmsMap.put("smscontent", smsContent+",返利次日到账");
                }else
                {
                    smsContent=smsContent+",商家参加"+activityContent+"返利活动,如符合条件,返利次日到账";
                    //短信内容
                    sendSmsMap.put("smscontent", smsContent);
                }
            }
            
            String smsJson = JSONObject.toJSONString(sendSmsMap);
            smsJson=smsJson.replaceAll("&", "");
            smsJson=smsJson.replaceAll("%","");
            log.info("SendSms Redis Key:" + smsQueue + "==Send Sms JSON:" + smsJson);
            try
            {
                //将短信发送放到redis队列中去
                redisTemplate.opsForList().rightPush(smsQueue, smsJson);
            }
            catch (RedisConnectionFailureException e)
            {
                log.error("sendSms RedisConnection Failure:");
                //若放到redis队列失败，则放入本地缓存Ecache中
                if(StringUtils.isNotBlank(smsJson))
                {
                    Map<String,String> contentMap=new HashMap<String,String>();
                    contentMap.put("markKey", smsQueue);
                    contentMap.put("markContent", smsJson);
                    contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
                    contentMap.put("addNumber","0");
                    //失败数据放入Ecache缓存中
                    exceptionHandle(contentMap);
                }
            }
        }
        catch (Exception e)
        {
            log.error("发送短信出现异常",e);
        }
    }
    
 
    /**
     * 下发商家营收短信
     * sellerid 商家编号
     * data[]   短信模板中的内容，按模板字段顺序填充
     * @Title: sendSmsForSeller 
     * @Description:
     */
    public void sendSmsForSeller(int sellerid,String ...data)
    {
        try
        {
        	//给设置了接收短信的商家下发消费通知短信
        	List<String> phoneList = sellerDao.getSellerAccountPhone(sellerid);
        	if(phoneList.size()==0){
        		log.info("该商家的所有账号均设定为不下发营收短信");
        		return;
        	}
        	StringBuffer phoneSB = new StringBuffer();
        	for(int i=0;i<phoneList.size();i++){
        		phoneSB.append(phoneList.get(i));
        		if(i!=(phoneList.size()-1)){
        			phoneSB.append(",");
        		}
        	}
        	
            String bid=data[0];
			String str=data[1];
			double sellerAmount = Double.parseDouble(str);	
			if(sellerAmount==0){
				log.info("商家营收分账为0，将不下发通知短信");
				return;
			}
			
            //组装短信发送
            Map<String,String> sendSmsMap = new HashMap<String,String>();
            //手机号码
            sendSmsMap.put("phoneid", phoneSB.toString());
            //订单编号
            sendSmsMap.put("bid", bid);
            String resellerid = sellerSmsContent.replace("{0}",bid);
            String remoney = resellerid.replace("{1}", str);
            sendSmsMap.put("smscontent", remoney);
            
            String smsJson = JSONObject.toJSONString(sendSmsMap);
            smsJson=smsJson.replaceAll("&", "");
            smsJson=smsJson.replaceAll("%","");
            log.info("SendSms Redis Key:" + smsQueue + "==Send Sms JSON:" + smsJson);
            try
            {
                //将短信发送放到redis队列中去
                redisTemplate.opsForList().rightPush(smsQueue, smsJson);
            }
            catch (RedisConnectionFailureException e)
            {
                log.error("sendSms RedisConnection Failure:");
                //若放到redis队列失败，则放入本地缓存Ecache中
                if(StringUtils.isNotBlank(smsJson))
                {
                    Map<String,String> contentMap=new HashMap<String,String>();
                    contentMap.put("markKey", smsQueue);
                    contentMap.put("markContent", smsJson);
                    contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
                    contentMap.put("addNumber","0");
                    //失败数据放入Ecache缓存中
                    exceptionHandle(contentMap);
                }
            }
        }
        catch (Exception e)
        {
            log.error("发送短信出现异常",e);
        }
    
    }

    

    /**
     * 用户消费返利(赠积分)，赠送额度=实际支付额度
     * @Title: consumerRebate 
     * @Description:
     */
    public void consumerRebate(BillBean billBean){
        log.info("消费订单开始赠送积分");
        String description = "消费即返积分";
        try
        {
        	if(new BigDecimal(billBean.getIntegral()).compareTo(ZERO)>0){
        		XmnWalletBean xmnWalletBean = new XmnWalletBean();
        		xmnWalletBean.setIntegral(String.valueOf(billBean.getIntegral()));
        		xmnWalletBean.setuId(billBean.getUid()+"");
        		xmnWalletBean.setUserType("1");
        		xmnWalletBean.setrType("4");
        		xmnWalletBean.setOrderId(String.valueOf(billBean.getBid()));
        		xmnWalletBean.setRemark(description);
        		xmnWalletBean.setDescription(description);
        		xmnWalletBean.setReturnMode("0");
        		xmnWalletBean.setOption("0");
        		this.insertXmnWalletRedis(xmnWalletBean);
//	            Map<String, String> walletMap = new HashMap<String, String>();
//	            //用户ID
//	            walletMap.put("uId", billBean.getUid()+"");
//	            //用户类型为1,即为用户
//	            walletMap.put("userType", "1");
//	            //修改的余额类型为4,即为赠送
//	            walletMap.put("rType", "4");
//	            //赠送的金额
//	            walletMap.put("integral", String.valueOf(billBean.getIntegral()));
//	            //订单号
//	            walletMap.put("orderId", String.valueOf(billBean.getBid()));
//	            //描述
//	            walletMap.put("remark", String.valueOf(billBean.getBid())+"");
//	            //赠送说明
//	            walletMap.put("description", description);
//	            log.info("walletMap:" + walletMap);
//	            //调用分账系统支付服务的修改钱包余额接口
//	            int resFlag = commonServiceImpl.modifyWalletBalance(walletMap);
//	            log.info("walletBalance resFlag:" + resFlag);	
        	}else{
        		log.info("该订单无实付金额，不赠送积分");
        	}
        }catch (Exception e)
        {
            log.error("消费立赠活动处理异常", e);
        }
        log.info("消费订单完成赠送积分");
    
    }
    
    /**
     * 消费送优惠券
     * @Title: consumerRebate 
     * @Description:
     */
    public void consumerCoupon(BillBean billBean){
        log.info("消费订单开始送商家优惠券");
        try
        {  	
        	/*
        	 * 1.获取当前消费商家可发放的优惠券
        	 * 2.判断该订单是否具备发放条件
        	 * 3.获取当前消费会员是否已获取该优惠券
        	 * 4.给当前消费会员发放优惠券
        	 * 5.更新商家优惠券发放信息
        	 */
        	SellerCoupon selSellerCoupon = new SellerCoupon();
        	selSellerCoupon.setSellerid(billBean.getSellerid());
        	selSellerCoupon.setEndDate(new Date());
        	selSellerCoupon.setSendStatus(1);
        	selSellerCoupon.setSendType(3);
        	List<SellerCoupon> sellerCouponList = sellerCouponDao.getSellerCouponList(selSellerCoupon);
        	if(sellerCouponList.size() < 1 || sellerCouponList == null){
        		log.info("该商户没有可派发的优惠券");
        	}else{
        		//判断该订单是否具备发放条件
        		List<UserCoupon> userCouponList = new ArrayList<UserCoupon>();
        		for(SellerCoupon sellerCoupon : sellerCouponList){
        			//当前时间大于等于优惠券的开始时间才可发放
        			if(DateUtil.compare_date(new Date(), sellerCoupon.getStartDate())>=0){
        				//当消费金额大于等于优惠券消费限额才可发放
	        			if(billBean.getMoney().compareTo(sellerCoupon.getLimitAmount()) >=0){
	        				UserCoupon selUserCoupon = new UserCoupon();
	    	        		selUserCoupon.setCid(sellerCoupon.getCid());
	    	        		selUserCoupon.setUid(billBean.getUid());
	    	        		int count = userCouponDao.getUserCouponCout(selUserCoupon);
	    	        		if(count == 0){	//没有发放过的才能继续发放
	    	        			Integer sendNum = sellerCoupon.getSendNum(); //已发送数量		
	    	        			Integer maxSendNum = sellerCoupon.getMaximum();	//最大发送数量
	    	        			//当最大发送数量大于已发送数量时，可继续发送
	    	        			if(maxSendNum>sendNum){
	    	        				//更新优惠卷发送数量
		    	        			SellerCoupon mdySellerCoupon = new SellerCoupon();
		    	        			mdySellerCoupon.setSendStatus(1);	//发送中
	    	        				//如果剩余最后一张，则修改商家优惠券为已发送完状态
	    	        				if(maxSendNum==(sendNum+1)){
	    	        					mdySellerCoupon.setSendStatus(2); //已发送
	    	        				}
		    	        			mdySellerCoupon.setCid(sellerCoupon.getCid());
		    	        			mdySellerCoupon.setSendNum(sendNum+1);	
		    	        			sellerCouponDao.mdfSellerCoupon(mdySellerCoupon);
		    	        			//给用户添加一张优惠卷
		    	        			UserCoupon userCoupon = new UserCoupon();
		    	        			userCoupon.setBid(billBean.getBid());
		    	        			userCoupon.setCid(sellerCoupon.getCid());
		    	        			userCoupon.setDenomination(sellerCoupon.getDenomination());
		    	        			userCoupon.setGetWay(3);//消费赠送
		    	        			userCoupon.setGetTime(new Date());
		    	        			userCoupon.setUid(billBean.getUid());
		    	        			userCoupon.setPhone(billBean.getPhoneid());
		    	        			userCoupon.setUseStatus(0);
		    	        			userCoupon.setStartDate(sellerCoupon.getStartDate());
		    	        			userCoupon.setEndDate(sellerCoupon.getEndDate());
		    	        			userCoupon.setUseMoney(sellerCoupon.getConditions()); 
		    	        			userCouponList.add(userCoupon);
	    	        			}
	    	        		}
	        			}
        			}
        		}
        		if(userCouponList!=null && userCouponList.size()>0){
        			userCouponDao.addUserCouponList(userCouponList);//添加优惠卷
        		}
        	}
        }catch (Exception e)
        {
            log.error("消费送商家优惠券活动处理异常", e);
        }
        log.info("消费订单完成送商家优惠券");
    
    }
    
    /**
     * 消费送商家券（新）
     * @Title: consumerRebate 
     * @Description:
     */
    public void consumerSellerCoupon(BillBean billBean){
        log.info("消费订单开始送商家优惠券");
        try{
        	SellerCoupon sc = new SellerCoupon();
        	sc.setLimitAmount(billBean.getCalculateRealPayAmount());
        	sc.setStatus(0);
        	//sc.setSendType(3);
        	sc.setSendStatus(1);
        	String awardConditions="1";
        	if(billBean.getBfirst()==1){
        		awardConditions="1,2";
        	}
        	sc.setAwardConditions(awardConditions);
        	sc.setNowDate(new Date());
        	sc.setHasSurplus(true);
        	sc.setSellerid(billBean.getSellerid());
        	List<SellerCoupon> scList = sellerCouponDao.getSellerCouponList2(sc);
        	for(SellerCoupon sellerCoupon:scList){
        		try{
        			redPacketService.insertSellerCoupon(sellerCoupon, billBean);
        		}catch(Exception e){
        			log.error("发放商家券异常",e);
        		}
        	}
        }catch(Exception e){
        	log.error("使用商家券异常",e);
        }
        
        log.info("消费订单完成送商家优惠券");
    
    }
    

    /**
     * 消费满赠红包
     * @Title: consumerRedPacket 
     * @Description:
     */
    public void consumerRedPacket(BillBean billBean){
        log.info("消费订单开始送商家消费满赠红包");
        try
        {  	
        	/*
        	 * 1.获取当前消费商家可发放的优惠券
        	 * 2.判断该订单是否具备发放条件
        	 * 3.获取当前消费会员是否已获取该优惠券
        	 * 4.给当前消费会员发放优惠券
        	 * 5.更新商家优惠券发放信息
        	 */

        	Date date = billBean.getZdate()==null?new Date():billBean.getZdate();
        	RedPacket redPacket = new RedPacket();
        	redPacket.setSellerid(billBean.getSellerid());
        	redPacket.setPayStatus(2);	//商家已支付红包款项
        	redPacket.setRedpacketType(2);//消费满赠红包
        	redPacket.setStatus(1);		//正在派送中
        	redPacket.setReceiveCondition(billBean.getCalculateRealPayAmount());
        	redPacket.setHasSurplus(true);
        	redPacket.setNowDate(date);
        	List<RedPacket> RedPacketList = redPacketDao.getRedPacketList(redPacket);
        	if(RedPacketList.size() < 1 || RedPacketList == null){
        		log.info("该商户没有可派发的消费满赠红包");
        	}else{
        		for(RedPacket sellerRedPacket:RedPacketList){
        			try{
        				String insertId = redPacketService.insertRedPacket(sellerRedPacket,billBean);
        				XmnWalletBean xmnWallet = new XmnWalletBean();
        				xmnWallet.setuId(billBean.getUid().toString());
        				xmnWallet.setUserType("1");
        				xmnWallet.setrType("3");
        				xmnWallet.setZbalance(sellerRedPacket.getSingleAmount().toString());
        				xmnWallet.setOrderId(insertId);
        				xmnWallet.setRemark("商家消费满赠红包");
        				xmnWallet.setOption("0");
        				xmnWallet.setReturnMode("1");
        				insertXmnWalletRedis(xmnWallet);
        			}catch(Exception e){
        				log.error("派发消费满赠红包【"+sellerRedPacket.getId()+"】失败",e);
        			}
        		}
        	}
        }catch (Exception e)
        {
            log.error("消费送商家满赠红包活动处理异常", e);
        }
        log.info("消费订单完成送商家满赠红包");
    }
    
    /**
     * 推荐人红包
     * @Title: consumerRecommendRedPacket 
     * @Description:
     */
    public void consumerRecommendRedPacket(BillBean billBean){

        log.info("消费订单开始送商家推荐人红包");
        try
        {  	
        	
        	/*
        	 * 1.获取当前消费商家可发放的优惠券
        	 * 2.判断该订单是否具备发放条件
        	 * 3.获取当前消费会员是否已获取该优惠券
        	 * 4.给当前消费会员发放优惠券
        	 * 5.更新商家优惠券发放信息
        	 */
        	if(billBean.getrUserId()==null || billBean.getrUserId()==0){
        		log.info("消费订单没有推荐人");
        		return ;
        	}else{
	        	Date date = billBean.getZdate()==null?new Date():billBean.getZdate();
	        	RedPacket redPacket = new RedPacket();
	        	redPacket.setSellerid(billBean.getSellerid());
	        	redPacket.setPayStatus(2);	//商家已支付红包款项
	        	redPacket.setRedpacketType(3);//消费满赠红包
	        	redPacket.setStatus(1);		//正在派送中
	//        	redPacket.setReceiveCondition(this.getRealPayAmount(billBean));
	        	redPacket.setHasSurplus(true);
	        	redPacket.setNowDate(date);
	        	List<RedPacket> RedPacketList = redPacketDao.getRecommendRedPacketList(redPacket);
	        	if(RedPacketList.size() < 1 || RedPacketList == null){
	        		log.info("该商户没有可派发的推荐人红包");
	        	}else{
	        		for(RedPacket sellerRedPacket:RedPacketList){
	        			try{
	        				RedPacketRecord record = redPacketService.insertRecommendRedPacket(sellerRedPacket,billBean);
	        				XmnWalletBean xmnWallet = new XmnWalletBean();
	        				xmnWallet.setuId(billBean.getrUserId().toString());
	        				xmnWallet.setUserType("1");
	        				xmnWallet.setrType("3");
	        				xmnWallet.setZbalance(record.getDenomination().toString());
	        				xmnWallet.setOrderId(record.getId()+"");
	        				xmnWallet.setRemark("商家推荐人红包");
	        				xmnWallet.setOption("0");
	        				xmnWallet.setReturnMode("1");
	        				insertXmnWalletRedis(xmnWallet);
	        			}catch(Exception e){
	        				log.error("派发的推荐人红包【"+sellerRedPacket.getId()+"】失败",e);
	        			}
	        		}
	        	}
        	}
        }catch (Exception e)
        {
            log.error("消费送推荐人红包活动处理异常", e);
        }
        log.info("消费订单完成送推荐人红包");
    }
        
    /**
     * 推荐人红包
     * @Title: consumerRecommendRedPacket 
     * @Description:
     */
    public void consumerFullreduction(BillBean billBean){
        log.info("消费订单开始消费满减记录");
        try
        {  	
        	if(billBean.getFullReductionId()==null || billBean.getFullReduction().compareTo(BigDecimal.ZERO)<=0){
        		log.info("该订单没有参与消费满减活动");
        	}else{

        		ActivityFullreduction fullreduction =fullreductionDao.getFullreductionInfo(billBean.getFullReductionId());
//	        	if(fullreduction.getStatus()==0 && fullreduction.){
//	        		log.info("该活动已停止");
//	        	}
        		redPacketService.insertFullreduction(fullreduction,billBean);
        	}
        }catch (Exception e)
        {
            log.error("消费满减异常", e);
        }
        log.info("消费订单完成消费满减记录");
    }
    
    /**
     * 免费尝新
     * @Title: consumerFreetry 
     * @Description:
     */
    public void consumerFreetry(BillBean billBean){
        log.info("消费订单开始扫描免费尝新活动");
        try
        {  	
    		ActivityFreetry aft = new ActivityFreetry();
    		aft.setSellerid(billBean.getSellerid());
    		aft.setStatus(0);
    		aft.setSetCondition(3);
    		aft.setNowDate(billBean.getZdate());
    		List<ActivityFreetry> list =freetryDao.getActivityFreetryList(aft);
    		if(list.size()==0){
    			log.info("该商家没有参与的免费尝新活动");
    		}else{
    			for(ActivityFreetry activityFreetry:list){
    				activityFreetry.getEndDate();
    				Long time = (activityFreetry.getEndDate().getTime()-(new Date()).getTime())/1000;
    				if(time>0){
    					redPacketService.insertSellerFreetryRedis(FREETRY_KEY+activityFreetry.getId(), billBean.getUid(), time);
    				}
    			}
    		}
        }catch (Exception e)
        {
            log.error("免费尝新活动异常", e);
        }
        log.info("消费订单完成扫描免费尝新活动");
    }
    
    /**
     * 大转盘
     * @Title: consumerRoullete 
     * @Description:
     */
    public void consumerRoullete(BillBean billBean){
        log.info("消费订单开始扫描大转盘活动");
        try
        {  	
    		ActivityRoullete aft = new ActivityRoullete();
    		aft.setSellerid(billBean.getSellerid());
    		aft.setStatus(0);
    		aft.setSetCondition(3);
    		aft.setNowDate(billBean.getZdate());
    		List<ActivityRoullete> list =roulleteDao.getActivityRoulleteList(aft);
    		if(list.size()==0){
    			log.info("该商家没有参与的大转盘活动");
    		}else{
    			for(ActivityRoullete activityroullete:list){
    				activityroullete.getEndDate();
    				Long time = (activityroullete.getEndDate().getTime()-(new Date()).getTime())/1000;
    				if(time>0){
    					redPacketService.insertSellerFreetryRedis(ROULLETE_KEY+activityroullete.getId(), billBean.getUid(), time);
    				}
    			}
    		}
        }catch (Exception e)
        {
            log.error("大转盘活动异常", e);
        }
        log.info("消费订单完成扫描大转盘活动");
    }
    
    
    /**
     * 聚点活动
     * @Title: consumerFcouspoints 
     * @Description:
     */
    public void consumerFcouspoints(BillBean billBean){
        log.info("消费订单开始扫描聚点活动活动");
        try
        {  	
        	ActivityFcouspoints afs = new ActivityFcouspoints();
        	afs.setSellerid(billBean.getSellerid());
        	afs.setStatus(0);
        	afs.setFullPrice(billBean.getCalculateRealPayAmount());
        	afs.setNowDate(billBean.getZdate());
    		List<ActivityFcouspoints> list =fcouspointsDao.getActivityFcouspointsList(afs);
    		if(list.size()==0){
    			
    			
    			log.info("该商家没有参与的扫描聚点活动");
    		}else{
    			for(ActivityFcouspoints activityFcouspoints:list){
   					redPacketService.insertSellerFcouspoints(activityFcouspoints,billBean);
    			}
    		}
        }catch (Exception e)
        {
            log.error("聚点活动异常", e);
        }
        log.info("消费订单完成扫描聚点活动活动");
    }
    
    /**
     * 使用粉丝券给主播分账
     * @Title: consumerRecommendRedPacket 
     * @Description:
     */
    public void consumerAnchorLedger(BillBean billBean){
        log.info("使用粉丝券给主播分账活动");
    	if(billBean.getCouponType()!=null && billBean.getCouponType()==3 
        		&& billBean.getCuser()!=null && billBean.getCuser().compareTo(BigDecimal.ZERO)>0){
    		log.info("该订单使用了粉丝券");
            try
            {  	
        		List<CouponRelation> crList = couponDao.getCouponRelation(billBean.getBid());
        		CouponRelation cr = crList.get(0);
        		if(cr==null){
        			log.error("找不到对应粉丝券的使用记录");
        		}else if(cr.getCtype()==1){
    	    		CouponDetail couponDetail = couponDao.getCouponDetail(cr.getCdid());
    	    		if(couponDetail != null){
    	    			Map<String, Object> couponAnchorRef = couponDao.getCouponAnchorRef(couponDetail.getAnchorCid().toString());
    	    			if (couponAnchorRef != null) {
							Map<String,String> walletMap = new HashMap<>();
							walletMap.put("uid",couponAnchorRef.get("uid").toString());
//							BigDecimal balance = couponDetail.getDenomination().multiply(new BigDecimal(10.00));
							walletMap.put("balance",couponDetail.getDenomination().toString());
							walletMap.put("rtype","10");
							walletMap.put("percent",couponAnchorRef.get("use_coupon_ration")+"");
							walletMap.put("remarks",couponDetail.getOrderNo());
							walletMap.put("description",couponAnchorRef.get("use_coupon_ration")+"");
							boolean result = commonServiceImpl.updateLiveWallet(walletMap);
							if (result) {
								log.info("使用粉丝券，返利成功");
								return;
							}
						}
    	    		}
    	    		log.error("订单："+cr.getCdid()+",给主播分账失败");
    	    		return;
        		}else {
					log.error("未找到关联订单："+cr.getCdid()+"的粉丝券记录");
				}
            }catch (Exception e)
            {
                log.error("给主播返鸟蛋异常", e);
            }
    	}else{
    		log.info("该订单没有使用粉丝券");
    	}

    }
    
    /**
     * 更新用户消费行为统计数据
     * @Title: updateUserActionQueue 
     * @Description:
     */
    public void updateUserActionQueue(BillBean billBean){
    	try{
    		Map<String,Object> paraMap = new HashMap<String,Object>();
    		paraMap.put("uid", billBean.getUid());
    		Set<Integer> uidSet = ursDao.getAnchorUidList(paraMap);
    		Map<String,String> actionMap = new HashMap<String,String>();
    		actionMap.put("uid", billBean.getUid()+"");
    		actionMap.put("xmntype", uidSet.contains(billBean.getUid())?"2":"1");	//主播OR普通用户
    		actionMap.put("operate", "2");
    		actionMap.put("actiontype", "1");
    		actionMap.put("lastTime", DateUtil.dateFormat1(billBean.getZdate()));
    		actionMap.put("sellerid", billBean.getSellerid()+"");
			//userActionService.insertXmnActionRedis(userActionQueue,actionMap);
    		
    		Map<String,String> actionMap2 = new HashMap<String,String>();
    		actionMap2.put("uid", billBean.getUid()+"");
    		actionMap2.put("xmntype", uidSet.contains(billBean.getUid())?"2":"1");	//主播OR普通用户
    		actionMap2.put("operate", "2");
    		actionMap2.put("lastTime", DateUtil.dateFormat1(billBean.getZdate()));
			actionMap2.put("actiontype", "2");
			actionMap2.put("zoneid", billBean.getZoneid()+"");
			//userActionService.insertXmnActionRedis(userActionQueue,actionMap);
			Map<String,String> actionMap3 = new HashMap<String,String>();
			SellerBean sellerBean = sellerDao.getSellerInfo(billBean.getSellerid());
    		actionMap3.put("uid", billBean.getUid()+"");
    		actionMap3.put("xmntype", uidSet.contains(billBean.getUid())?"2":"1");	//主播OR普通用户
    		actionMap3.put("operate", "2");
    		actionMap3.put("lastTime", DateUtil.dateFormat1(billBean.getZdate()));
			actionMap3.put("actiontype", "3");
			actionMap3.put("category",sellerBean.getCategory());
			actionMap3.put("genre", sellerBean.getGenre());
			//userActionService.insertXmnActionRedis(userActionQueue,actionMap);
			List<Map<String,String>> actionList = new ArrayList<Map<String,String>>();
			actionList.add(actionMap);
			actionList.add(actionMap2);
			actionList.add(actionMap3);
			userActionService.userActionService(actionList);
    	}catch(Exception e){
    		log.error("更新用户消费行为统计信息异常",e);
    	}
    }
    
    /**
     * 推送消息发送redis队列处理方法
     * @param resOrderMap [查询出来的订单信息]
     * @param pushType [推动类型;1为订单支付成功,2为分账成功]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void pushMsgRedisProcess2(BillBean billBean,String pushType)
    {
        //组装推送消息发送redis队列的数据格式
        Map<String,String> pushMsgMap = new HashMap<String,String>();
        //推送消息类型;1为订单支付成功,2为分账成功
        pushMsgMap.put("type", pushType);
        //订单号
        pushMsgMap.put("bid", String.valueOf(billBean.getBid()));
        //用户ID
        pushMsgMap.put("uid", String.valueOf(billBean.getUid()));
        //用户手机号码
        pushMsgMap.put("phoneid", String.valueOf(billBean.getPhoneid()));
        //商家ID
        pushMsgMap.put("sellerid", String.valueOf(billBean.getSellerid()));
        //商家名称
        String sellername=billBean.getSellername();
        sellername=sellername.replaceAll("&","");
        sellername=sellername.replaceAll("%", "");
        pushMsgMap.put("sellername", sellername);
        //消费金额
        pushMsgMap.put("money", String.valueOf(billBean.getMoney()));
        //返利支付金额
        pushMsgMap.put("rebate", String.valueOf(billBean.getRebate()));
        //平台补贴金额
        pushMsgMap.put("flat_money", String.valueOf(billBean.getFlatMoney()));
        //写入redis时间
        pushMsgMap.put("sdate", dateFormat.format(billBean.getZdate()));
        //将MAP转换成JSON格式
        String pushMsgQueueMap = JSONObject.toJSONString(pushMsgMap);
        log.info("pushMsgRedisProcess sendRedis Key:"+pushMsgQueue+"===pushMsgRedisProcess sendRedis JSON:"+pushMsgQueueMap);
        try
        {
            //发送redis
            redisTemplate.opsForList().rightPush(pushMsgQueue,pushMsgQueueMap);
        }
        catch (RedisConnectionFailureException e)
        {
            //若抛出异常,则放入到本地缓存Ecache中
            if(StringUtils.isNotBlank(pushMsgQueueMap))
            {
                Map<String,String> contentMap=new HashMap<String,String>();
                contentMap.put("markKey", pushMsgQueue);
                contentMap.put("markContent", pushMsgQueueMap);
                contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
                contentMap.put("addNumber","0");
                //失败数据放入Ecache缓存中
                exceptionHandle(contentMap);               
            }
        }
    }
    
    /**
     * 更新优惠券使用情况
     * @param billBean 订单信息
     * @param stauts 要更新的状态 0 未使用  1 已锁定 2 已使用
     * @Title: usedUserCoupon 
     * @Description:
     */
    public void usedUserCoupon(BillBean billBean) throws Exception{
    	//1.获取当前订单是否使用了商家优惠券
    	//2.获取该用户所使用的商家优惠券
    	//3.将该商家优惠券的使用状态修改为"已使用"/"未使用"
    	if(billBean.getCouponType() != null && billBean.getCuser()!= null && billBean.getCuser().compareTo(BigDecimal.ZERO)>0){//已使用优惠券消费
    		Integer couponType = billBean.getCouponType();
    		if(couponType==4){
    			this.usedSellerPackage(billBean);
    		}else{
    		log.info("该用户本次消费使用了优惠券");
    		List<CouponRelation> crList = couponDao.getCouponRelation(billBean.getBid());
    		if(crList==null || crList.size()==0){
    			log.error("找不到对应优惠券的修改记录");
    			throw new Exception("找不到对应优惠券的修改记录");
    		}
    		for(CouponRelation cr:crList){
    		if(cr.getCtype()==1||cr.getCtype()==3||cr.getCtype()==5){
	    		CouponDetail couponDetail = couponDao.getCouponDetail(cr.getCdid());
	    		log.info("本单【"+billBean.getBid()+"】消费所使用的平台系列优惠券信息，优惠券ID:"+couponDetail.getCdid());
	    		int status = billBean.getStatus()>0?2:0;
	    		if(couponDetail.getUserStatus()==1){//用户使用的商家优惠券已锁定
	    			log.info("该优惠券已锁定");
	    			if(status==0){
	    				couponDetail.setUserStatus((byte)status);
		    			couponDetail.setLockTime(null);
		    			couponDao.updateCouponDetail(couponDetail);
		    			log.info("该优惠券还原成功");
	    			}else if(status==2){
		    			couponDetail.setUserStatus((byte)status);
		    			couponDetail.setUserTime(new Date());
		    			couponDao.updateCouponDetail(couponDetail);
	//	    			SellerCoupon sc = new SellerCoupon();
	//	    			sc.setCid(userCoupon.getCid());
	//	    			sellerCouponDao.useSellerCoupon(sc);
		    			log.info("该优惠券消费成功");
	    			}
	    		}else{
	    			log.info("该优惠券不是锁定状态");
	    			 if(status==2){
			    			couponDetail.setUserStatus((byte)status);
			    			couponDetail.setUserTime(new Date());
			    			couponDao.updateCouponDetail(couponDetail);
		//	    			SellerCoupon sc = new SellerCoupon();
		//	    			sc.setCid(userCoupon.getCid());
		//	    			sellerCouponDao.useSellerCoupon(sc);
			    			log.info("该优惠券消费成功");
		    			}
	    		}
    		}else if(cr.getCtype()==2){
    			UserCoupon uc = new UserCoupon();
    			uc.setCuid(cr.getCdid());
	    		UserCoupon userCoupon = userCouponDao.getUserCouponInfo2(uc);
	    		log.info("本单【"+billBean.getBid()+"】消费所使用的商家系列优惠券信息，优惠券ID:"+userCoupon.getCid());
	    		int status = billBean.getStatus()>0?2:0;
	    		if(userCoupon.getUseStatus()==1){//用户使用的商家优惠券已锁定
	    			log.info("该优惠券已锁定");
	    			if(status==0){
	    				UserCoupon modifyUserCoupon = new UserCoupon();
		    			modifyUserCoupon.setCuid(userCoupon.getCuid());
		    			modifyUserCoupon.setUseStatus(status);
		    			modifyUserCoupon.setLockTime(null);
		    			userCouponDao.modifyUserCoupon(modifyUserCoupon);
		    			log.info("该优惠券还原成功");
	    			}
	    			else if(status==2){
	    				UserCoupon modifyUserCoupon = new UserCoupon();
		    			modifyUserCoupon.setCuid(userCoupon.getCuid());
		    			modifyUserCoupon.setUseStatus(status);
		    			modifyUserCoupon.setUseTime(new Date());
		    			userCouponDao.modifyUserCoupon(modifyUserCoupon);
		    			log.info("该优惠券消费成功");
	    			}
	    			

	    		}else{
	    			log.info("该优惠券不是锁定状态");
	    		}
    			SellerCoupon sc = new SellerCoupon();
    			sc.setCid(userCoupon.getCid());
    			sellerCouponDao.useSellerCoupon(sc);
    			log.info("该优惠券消费成功");
    		}
    		}
    	}
    	}
    }
  
    /**
     * 更新商家套餐券使用情况
     * @param billBean 订单信息
     * @param stauts 要更新的状态 0 未使用  1 已锁定 2 已使用
     * @Title: usedUserCoupon 
     * @Description:
     */
    public void usedSellerPackage(BillBean billBean) throws Exception{
    	//1.获取当前订单是否使用了商家优惠券
    	//2.获取该用户所使用的商家优惠券
    	//3.将该商家优惠券的使用状态修改为"已使用"/"未使用"

    		log.info("该用户本次消费使用了套餐券");
    		if(billBean.getStatus()>0){
    			Map<String,Object> reqMap = new HashMap<>();
        		reqMap.put("bid", billBean.getBid());
        		Map<String,Object> grantMap = sellerPackageDao.getSellerPackageGrantByConsume(reqMap);
        		if(grantMap==null){
        			log.error("找不到对应套餐券的记录");
        			throw new Exception("找不到对应优惠券的修改记录");
        		}
        		if((int)grantMap.get("uid")==billBean.getUid()){
        			log.info("找 到了。。。");
        			Map<String,Object> uMap = new HashMap<>();
        			uMap.put("id", grantMap.get("id"));
        			uMap.put("userStatus", 2);
        			uMap.put("userTime", new Date());
        			sellerPackageDao.useSellerPackageGrant(uMap);
        		}
    		}
    }
  
    public String getZdQueueKey()
    {
        return zdQueueKey;
    }

    public void setZdQueueKey(String zdQueueKey)
    {
        this.zdQueueKey = zdQueueKey;
    }

    public String getSdQueueKey()
    {
        return sdQueueKey;
    }

    public void setSdQueueKey(String sdQueueKey)
    {
        this.sdQueueKey = sdQueueKey;
    }

    
    /**
	 * @return the maibaoLedgerQueueKey
	 */
	public String getMaibaoLedgerQueueKey() {
		return maibaoLedgerQueueKey;
	}


	/**
	 * @param maibaoLedgerQueueKey the maibaoLedgerQueueKey to set
	 */
	public void setMaibaoLedgerQueueKey(String maibaoLedgerQueueKey) {
		this.maibaoLedgerQueueKey = maibaoLedgerQueueKey;
	}


	public CommonServiceImpl getCommonServiceImpl()
    {
        return commonServiceImpl;
    }

    public void setCommonServiceImpl(CommonServiceImpl commonServiceImpl)
    {
        this.commonServiceImpl = commonServiceImpl;
    }

    public String getMikeQueue()
    {
        return mikeQueue;
    }

    public void setMikeQueue(String mikeQueue)
    {
        this.mikeQueue = mikeQueue;
    }
    
    public String getOrderSmsContent()
    {
        return orderSmsContent;
    }

    public void setOrderSmsContent(String orderSmsContent)
    {
        this.orderSmsContent = orderSmsContent;
        System.out.println(orderSmsContent);
    }

    public String getPushMsgQueue()
    {
        return pushMsgQueue;
    }

    public void setPushMsgQueue(String pushMsgQueue)
    {
        this.pushMsgQueue = pushMsgQueue;
    }

    public String getSmsQueue()
    {
        return smsQueue;
    }

    public void setSmsQueue(String smsQueue)
    {
        this.smsQueue = smsQueue;
    }
    public Cache getDataCache()
    {
        return dataCache;
    }

    public void setDataCache(Cache dataCache)
    {
        this.dataCache = dataCache;
    }

    
    public String getSellerSmsContent() {
		return sellerSmsContent;
	}

	public void setSellerSmsContent(String sellerSmsContent) {
		this.sellerSmsContent = sellerSmsContent;
	}

	/**
     * 商家手动验单，写队列
     */
	@Transactional
	@Override
	public Map<String, String> handleLedger(Map<String, String> paraMap)
			throws FailureException, TException {
		Map<String,String> resultMap = new HashMap<String,String>();
		log.info("商家手动验单，开始写入分账队列，订单信息："+paraMap);
		try{
			String bid = paraMap.get("bid");
			String source = paraMap.get("source")==null?"":paraMap.get("source");
			if(StringUtils.isNotBlank(bid)){
		        BillBean billBean = orderDao.getBillBean(bid);
		        if(billBean!=null){
		        	if(billBean.getStatus()==2){
		        	   resultMap.put("bid", bid);
			           resultMap.put("recode", "100");
			           resultMap.put("remark", "");
			           return resultMap;
		        	}
		        	/* 仅对平台来的手动分账进行判断，商户端自己验证  */
		        	if(source.equals("0")  &&  billBean.getIsverify()==1){
		        		log.error("该类型订单已经手动分账操作");
						 resultMap.put("bid", bid);
				         resultMap.put("recode", "106");
				         resultMap.put("remark", "该【"+bid+"】订单已经手动分账操作");	
				         throw new FailureException(106,"该【"+bid+"】订单已经手动分账操作");
		        	}
		        	if(billBean.getLedgerMode()==1){
		        		 log.error("该类型订单不允许分账");
						 resultMap.put("bid", bid);
				         resultMap.put("recode", "106");
				         resultMap.put("remark", "该【"+bid+"】订单不允许分账");	
				         throw new FailureException(106,"该【"+bid+"】订单不允许分账");
		        	}
		        	if(billBean.getLiveLedger()!=null&&billBean.getLiveLedger()==1&&!source.equals("0")){
		        		 log.error("该类型订单不允许分账");
						 resultMap.put("bid", bid);
				         resultMap.put("recode", "106");
				         resultMap.put("remark", "该【"+bid+"】订单不允许分账");	
				         throw new FailureException(106,"该【"+bid+"】订单不允许分账");
		        	}
		        	
	    		   /* 获取寻蜜客等级信息 */
	    		   //Integer xmerUid = billBean.getXmerUid();
	    		   //String proportion = billBean.getCommission();
	    		   JSONObject json = JSON.parseObject(billBean.getCommission());  
	   				String str=json.getString("proportion");
	              	billBean.setProportion(str);;
	              	billBean.setLedgerAmount(billBean.getCalculateRealLedgerAmount());
	    		  /* if(xmerUid != null){
	    			   if(xmerUid == 0){	//寻蜜客被解除
	    				   billBean.setXmerUid(null);//2016年7月7日 若对应寻蜜客被解除，则将其默认为无寻蜜客
	    			   }
	    			   Map<String,Object> issuesMap = xmerDao.getXmerIssues(xmerUid);
	    			   if(issuesMap == null){	//寻蜜客被解除
	    				   billBean.setXmerUid(null);//2016年7月7日 若对应寻蜜客被解除，则将其默认为无寻蜜客
	    			   }else{
	 	   			   getXmerName(issuesMap);
	 		           billBean.setOneLevelXmerId(issuesMap.get("oneLevelXmerId")==null?null:(Integer)issuesMap.get("oneLevelXmerId"));
	 		           billBean.setTwoLevelXmerId(issuesMap.get("twoLevelXmerId")==null?null:(Integer)issuesMap.get("twoLevelXmerId"));
	 		           billBean.setOneLevelXmerName(issuesMap.get("oneLevelXmerName")==null?null:(String)issuesMap.get("oneLevelXmerName"));
	 		           billBean.setTwoLevelXmerName(issuesMap.get("twoLevelXmerName")==null?null:(String)issuesMap.get("twoLevelXmerName"));
	    			   }
	    		   } */ 
	              
	              	//设置寻蜜客相关信息 (消费订单的分账队列用到)
	               setBillBeanOfXmerInfo(billBean);
	               
	               /* 仅对平台来的手动分账进行判断，商户端自己验证  */
		        	if(source!=null && source.equals("0")){
			            ModifyOrderInfoBean reqBean = new ModifyOrderInfoBean();
			            reqBean.setBid(String.valueOf(billBean.getBid()));
			            reqBean.setIsverify(1);
			            reqBean.setParentXmerUid(billBean.getTwoLevelXmerId());//设置上级寻蜜客UID
			            //更新订单信息
			            orderDao.modifyOrderInfo(reqBean);
		        	}
	               
	               
	              	
	    		   billBean.setLedgerAmount(billBean.getCalculateRealLedgerAmount());   
		            //插入分账消息队列
	    		   pushLedgerRedisList(billBean,sdQueueKey,2);
		           
		           resultMap.put("bid", bid);
		           resultMap.put("recode", "100");
		           resultMap.put("remark", "");
		        }else{
					 log.error("找不到【"+bid+"】订单");
					 resultMap.put("bid", bid);
			         resultMap.put("recode", "103");
			         resultMap.put("remark", "找不到【"+bid+"】订单");	
			         throw new FailureException(103,"找不到【"+bid+"】订单");
		        }
			}else{
				log.error("bid不能为空");
				 resultMap.put("bid", "");
		         resultMap.put("recode", "101");
		         resultMap.put("remark", "bid不能为空");
		         throw new FailureException(101,"找不到【"+bid+"】订单");
			}
			
		}catch(Exception e){
			log.error("商家手动验单失败",e);
			resultMap.put("bid", paraMap.get("bid")==null?"":paraMap.get("bid"));
	        resultMap.put("recode", "102");
	        resultMap.put("remark", "商家手动验单失败");
	        throw new FailureException(102,"商家手动验单失败");
		}
		log.info("商家手动验单，写入分账队列结束:"+resultMap);
		return resultMap;
	}

    /**
     * 插入消费订单的分账队列
     * @Title: pushLedgerRedis 
     * @Description:
     */
    public void pushLedgerRedisList(BillBean billBean,String ledgerQuery,int ledgerType){
    	BillEntity ledgerBill = new BillEntity();
        try
        {
            //放入redis缓存中,值为类型为订单+订单编号+商家分账类型为自动
        	ledgerBill = this.getLedgerBill(billBean);
            
            //自动队列和订单号转换类型
            JSONObject  json = (JSONObject) JSONObject.toJSON(ledgerBill);
            json.put("type", "1");
            json.put("ledger_type", ledgerType+"");	//1手动 2自动 分账
            json.put("zdate", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            log.info("写入手动分账队列名："+ledgerQuery+",写入内容："+json);
            redisTemplate.opsForList().leftPush(ledgerQuery, json.toJSONString());
        }
        catch (RedisConnectionFailureException ex)
        {
            //若发送或者连接redis失败，则放入到本地缓存Ecache中
            if(ledgerBill !=null)
            {
                Map<String,String> contentMap=new HashMap<String,String>();
                contentMap.put("markKey", zdQueueKey);
//                contentMap.put("markContent", autoQueueMap);
                contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
                contentMap.put("addNumber","0");
                //失败数据放入Ecache缓存中
                exceptionHandle(contentMap);                        
            }
        }
    }
    
    /*
     * 风控
     */
    private int getWindcontrol(BillBean billBean){
    	if(billBean.getMoney().compareTo(new BigDecimal(5000))>0){
    		return 2;
    	}
    	return 1;
    }
    
    /*
     * 扣回赠送的商家优惠券
     */
    public void getBackUserCoupon(BillBean billBean){
    	List<UserCoupon> couponList = userCouponDao.getUserCouponList(billBean.getBid());
    	if(couponList == null || couponList.size()==0){
    		log.info("该订单没有赠送商家优惠券");
    		return ;
    	}
    	for(UserCoupon coupon:couponList){
    		if (coupon.getUseStatus() ==1) {
				userCouponDao.delectUserCoupon(coupon);
			}else{
				log.info("该优惠券已被使用，无法收回");
			}
    	}
    }
    
    /*
     * 恢复已使用的商家优惠券
     */
    public void recoverUserCoupon(BillBean billBean){
    	if(billBean.getCouponType() != null && billBean.getCouponType()==2){//已使用商家优惠券消费
    		log.info("该用户本次消费使用了商家优惠券");
    		UserCoupon userCoupon = userCouponDao.getUserCouponInfo(billBean.getBid());
    		log.info("本单【"+billBean.getBid()+"】消费所使用的优惠券信息，优惠券ID:"+userCoupon.getCid());
    		if(userCoupon.getUseStatus()==2){//用户使用的商家优惠券已锁定
    			log.info("该优惠券已被使用");
    			UserCoupon modifyUserCoupon = new UserCoupon();
    			modifyUserCoupon.setCuid(userCoupon.getCuid());
    			modifyUserCoupon.setUseStatus(0);
    			modifyUserCoupon.setUseTime(new Date());
    			userCouponDao.modifyUserCoupon(modifyUserCoupon);
    			log.info("该优惠券消费成功");
    		}
    	}
    }

    /* 是否历史订单 */
    public  boolean isCurrentOrder(Date date){
    	String orderTime =dateFormat.format(date).substring(0,10);
    	String nowTime = dateFormat.format(new Date()).substring(0,10);
    	System.out.println(orderTime+","+nowTime);
    	if(orderTime.compareTo(nowTime)<0){
    		return false;
    	}
    	return true;
    }
    
    /**
     * 更新钱包金额队列
     */
    public void insertXmnWalletRedis(XmnWalletBean xmnWallet){
    	Map<String,String> walletMap = new HashMap<String,String>();
    	walletMap.put("uId",xmnWallet.getuId());
    	walletMap.put("userType",xmnWallet.getUserType());
    	walletMap.put("option",xmnWallet.getOption());
    	walletMap.put("phoneNumber",xmnWallet.getPhoneNumber());
    	walletMap.put("amount",xmnWallet.getAmount());
    	walletMap.put("balance",xmnWallet.getBalance());
    	walletMap.put("commision",xmnWallet.getCommision());
    	walletMap.put("zbalance",xmnWallet.getZbalance());
    	walletMap.put("integral",xmnWallet.getIntegral());
    	walletMap.put("sellerAmount",xmnWallet.getSellerAmount());
    	walletMap.put("orderId",xmnWallet.getOrderId());
    	walletMap.put("remark",xmnWallet.getRemark());
    	//walletMap.put("description",xmnWallet.getDescription());
    	walletMap.put("rType",xmnWallet.getrType());
    	walletMap.put("returnMode",xmnWallet.getReturnMode());
    	redisTemplate.opsForList().leftPush(xmnWalletKey, JSONObject.toJSONString(walletMap));
    	log.debug("写入更新钱包Redis队列成功，"+xmnWalletKey);
    }
    
	private int decimalCompareZero(String amount){
		return (new BigDecimal(amount)).compareTo(ZERO);
	}


	private void preVerifyOrderParam(XmnOrderParamV2 orderParam) throws Exception{
		//try{
		if(orderParam.getLiveCoinArrivedMoney()==null && orderParam.getLiveCoin()==null && orderParam.getSellerCoin()==null){
			orderParam.setLiveCoinArrivedMoney("0.00");
		}
		if(orderParam.getLiveCoin()==null){
			orderParam.setLiveCoin("0.00");
		}
		if(orderParam.getSellerCoin()==null){
			orderParam.setSellerCoin("0.00");
		}
		if(orderParam.getLiveCoinRatio()==null){
			orderParam.setLiveCoinRatio("0.00");
		}
		if(orderParam.getBase()==null){
			orderParam.setBase("0.00");
		}
		
			log.info("更新寻蜜鸟订单接口："+orderParam);
			if(StringUtils.isBlank(orderParam.getBid())
				||StringUtils.isBlank(orderParam.getStatus())
				||StringUtils.isBlank(orderParam.getUid())
				||StringUtils.isBlank(orderParam.getPaytype())
				||StringUtils.isBlank(orderParam.getOrdertype())
				){
				log.info("传入参数不完整");
				throw new Exception("传入参数不完整");
			}
			
			if(!orderParam.getStatus().matches("^[1-3]$")){
				log.info("传入状态不正常");
				throw new Exception("传入状态不正常");
			}
			if(orderParam.getStatus().equals("1")){
				if(StringUtils.isBlank(orderParam.getIsbalance())
				|| decimalCompareZero(orderParam.getMoney())<=0
				|| decimalCompareZero(orderParam.getPreferential())<0
				|| decimalCompareZero(orderParam.getPayamount())<0
				|| decimalCompareZero(orderParam.getSamount())<0
				|| decimalCompareZero(orderParam.getCommision())<0
				|| decimalCompareZero(orderParam.getProfit())<0
				|| decimalCompareZero(orderParam.getGiveMoney())<0
				|| decimalCompareZero(orderParam.getLiveCoin())<0){
					log.info("传入参数异常");
					throw new Exception("传入参数异常");
				}
				if(!orderParam.getPaytype().equals("1000000")
				 && !orderParam.getPaytype().equals("1000015")//鸟币支付
				 && !orderParam.getPaytype().equals("1000011")//鸟币支付
				 && StringUtils.isBlank(orderParam.getNumber())){
					log.info("非寻蜜鸟支付方式，请提供第三方交易单号");
					throw new Exception("非寻蜜鸟支付方式，请提供第三方交易单号");
				}
			}
		//}catch(Exception e){
		//	log.error("校验数据异常",e);
		//	throw new FailureException(107,e.getMessage());
		//}
	}
	/**
	 * 新版更新寻蜜鸟订单状态接口
	 */
	@Override
	public String updateXmnOrderInfo(XmnOrderParam orderParam)
			throws FailureException, TException {
		XmnOrderParamV2 orderParamV2 = new XmnOrderParamV2();
		orderParamV2.setBid(orderParam.getBid());
		orderParamV2.setStatus(orderParam.getStatus());
		orderParamV2.setZdate(orderParam.getZdate());
		orderParamV2.setUid(orderParam.getUid());
		orderParamV2.setPhoneid(orderParam.getPhoneid());
		orderParamV2.setPayid(orderParam.getPayid());
		orderParamV2.setNumber(orderParam.getNumber());
		orderParamV2.setThirdUid(orderParam.getThirdUid());
		orderParamV2.setPaytype(orderParam.getPaytype());
		orderParamV2.setOrdertype(orderParam.getOrdertype());
		orderParamV2.setIsbalance(orderParam.getIsbalance());
		orderParamV2.setMoney(orderParam.getMoney());
		orderParamV2.setPreferential(orderParam.getPreferential());
		orderParamV2.setPayamount(orderParam.getPayamount());
		orderParamV2.setSamount(orderParam.getSamount());
		orderParamV2.setCommision(orderParam.getCommision());
		orderParamV2.setProfit(orderParam.getProfit());
		orderParamV2.setGiveMoney(orderParam.getGiveMoney());
		orderParamV2.setLiveCoin(orderParam.getLiveCoin());
		orderParamV2.setLiveCoinArrivedMoney("0");
		orderParamV2.setLiveCoinRatio("0");
		orderParamV2.setIntegral("0");
		orderParamV2.setDiscounts(orderParam.getDiscounts());//商家折扣
		orderParamV2.setBase(orderParam.getBase());//基数
		orderParamV2.setSellerCoin(orderParam.getSellerCoin());//商家专享鸟币
		return this.updateXmnOrderInfoV2(orderParamV2);
	}
    /**
     * 准备最新订单信息
     * @Title: modifyBillBean 
     * @Description:
     */
   void  modifyBillBean2(BillBean billBean,XmnOrderParamV2 orderParam,Map<String,String> resMikeMap,SellerBean seller) throws FailureException{
       log.info("modifyBillBean start:" + billBean);
       try
       {
    	   if(orderParam.getPayid()==null && billBean.getPayid()!=null){
    		   
    	   }else{
	           billBean.setNumber(orderParam.getNumber());
	           billBean.setPayid(orderParam.getPayid());
	           billBean.setPaytype(orderParam.getPaytype());
	           billBean.setPayment(ConversionTypeUtil.conversionToBigDecimal(orderParam.getSamount()));
	           billBean.setCommision(ConversionTypeUtil.conversionToBigDecimal(orderParam.getCommision()));
	           billBean.setProfit(ConversionTypeUtil.conversionToBigDecimal(orderParam.getProfit()));
	           billBean.setGiveMoney(ConversionTypeUtil.conversionToBigDecimal(orderParam.getGiveMoney()));
	           billBean.setLiveCoin(ConversionTypeUtil.conversionToBigDecimal(orderParam.getLiveCoin()));
	           billBean.setLiveCoinMoney(ConversionTypeUtil.conversionToBigDecimal(orderParam.getLiveCoinArrivedMoney()));
	           if(orderParam.getBase()==null || orderParam.getBase().equals("") || Double.valueOf(orderParam.getBase())==0){
	        	   billBean.setLiveCoinRatio(Double.valueOf(orderParam.getLiveCoinRatio()==null?"0":orderParam.getLiveCoinRatio()));
	           }else{
	        	   billBean.setLiveCoinRatio(Double.valueOf(orderParam.getBase()));
	           }
	           
	           billBean.setSellerCoin(ConversionTypeUtil.conversionToBigDecimal(orderParam.getSellerCoin()));

    	   }
           billBean.setStatus(1);
           
           if(seller.getIsPaid()!=null && seller.getIsPaid()==0){
        	   log.info("该商家为非付费商家，该订单需额外收取"+FEE_RATIO+"的手续费用");
        	   billBean.setFeeRatio(FEE_RATIO);
           }
          
           billBean.setZdate(billBean.getZdate()==null?new Date():billBean.getZdate());
           billBean.setIntegral(billBean.getCalculateRealZInteger().doubleValue());
           billBean.setLedgerAmount(billBean.getCalculateRealLedgerAmount());//可分账金额部分
           
           billBean.setRealPayMent(billBean.getCalculateRealPayMent());//脉宝分账的实际支付部分
   		   /* 设置寻蜜客相关信息 (消费订单的分账队列用到)*/
           setBillBeanOfXmerInfo(billBean);
   		   
	   		billBean.setLdate(new Date());
	
	        if(null!=resMikeMap)
	        {
	     	   billBean.setMikeType(resMikeMap.containsKey("mikeType")?Byte.valueOf(resMikeMap.get("mikeType")):null);
	     	   billBean.setBfirst(resMikeMap.containsKey("bfirst")?Integer.valueOf(resMikeMap.get("bfirst")):null);
	        }
//           calculateLedger(billBean);//计算分账
           calculateLedgerV2(billBean);//计算分账 // 2017-05-11 
           if(billBean.getLiveLedger()!=null && billBean.getLiveLedger()==1){
        	   orderParam.setLedgertype("1");//若采用直播分账，则普通分账状态全置为1
           }
       }
       catch (Exception e)
       {
           log.error("准备订单数据异常", e);
           throw new FailureException(ResponseState.ELSEEROR, "准备订单数据异常");
       }
       log.info("modifyBillBean end......");
    }
   


  /**
	 * 方法描述：设置寻蜜客相关信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-15下午2:45:53 <br/>
	 * @param billBean
	 */
	private void setBillBeanOfXmerInfo(BillBean billBean) {
		Integer xmerUid = billBean.getXmerUid();
		   
		   if(xmerUid != null){
			   Integer saasChannel = billBean.getSaasChannel();
	           	if(xmerUid==0 || saasChannel==null){
	        		log.info("该订单记录商家寻蜜客的uid为无效ID(或签约商家SAAS来源为null)，现将其设置为null");
	        		billBean.setXmerUid(null);
	        	}else{
	        		UrsEarningsRelation ursRelation= new UrsEarningsRelation();
		           	ursRelation.setUid(xmerUid);
		           	ursRelation.setObjectOriented(convertChannel(saasChannel));
		           	UrsEarningsRelation ursEarningsRelation = ursRelationDao.getUrsEarningsRelation(ursRelation);
		           	
		           	if(ursEarningsRelation==null){//寻蜜客被解除
		           		billBean.setXmerUid(null);//2016年7月7日 若对应寻蜜客被解除，则将其默认为无寻蜜客
		           	}else{
		           		//寻蜜客对应关系: xmerId 当前寻蜜客 ,twoLevelXmerId 上级寻蜜客, oneLevelXmerId 上上级寻蜜客
		           		Map<String,Object> issuesMap = new HashMap<String,Object>();
		           		String uidRelationChain = ursEarningsRelation.getUidRelationChain();
		           		if(uidRelationChain!=null){
		           			String[] uids = uidRelationChain.split(",");
		           			int length = uids.length;
		           			if(length>0){
		           				String xmerId = uids[length-1];
		           				issuesMap.put("xmerId", xmerId.replaceAll("^(0+)", ""));
		           			}
		           			if(length>1){
		           				String twoLevelXmerId = uids[length-2];
		           				issuesMap.put("twoLevelXmerId", twoLevelXmerId.replaceAll("^(0+)", ""));
		           			}
		           			if(length>2){
		           				String oneLevelXmerId = uids[length-3];
		           				issuesMap.put("oneLevelXmerId", oneLevelXmerId.replaceAll("^(0+)", ""));
		           			}
		           			
		           		   getXmerName(issuesMap);
		           		   Integer oneLevelXmerId=issuesMap.get("oneLevelXmerId")==null?null:new Integer(issuesMap.get("oneLevelXmerId").toString());
		           		   Integer twoLevelXmerId=issuesMap.get("twoLevelXmerId")==null?null:new Integer(issuesMap.get("twoLevelXmerId").toString());
				           billBean.setOneLevelXmerId(oneLevelXmerId);
				           billBean.setTwoLevelXmerId(twoLevelXmerId);
				           billBean.setOneLevelXmerName(issuesMap.get("oneLevelXmerName")==null?null:(String)issuesMap.get("oneLevelXmerName"));
				           billBean.setTwoLevelXmerName(issuesMap.get("twoLevelXmerName")==null?null:(String)issuesMap.get("twoLevelXmerName"));
		           		}
		           	}
	        	}
		   } 
		
	}


/**
	 * 方法描述：将SAAS来源 :1 常规SAAS签约 2脉客SAAS签约 3V客SAAS签约 4主播(V客赠送)SAAS签约<br/>
	 * 转化为对应的寻蜜客类型:5.常规寻蜜客 6.主播寻蜜客(V客赠送) 7.V客寻密客 8.脉客寻密客<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-15上午11:52:58 <br/>
	 * @param saasChannel
	 * @return
	 */
	private Integer convertChannel(Integer saasChannel) {
		Integer objectOriented=null;
		if(saasChannel==null){
			return null;
		}
		switch (saasChannel) {
		case 1:
			objectOriented=5;
			break;
		case 2:
			objectOriented=8;
			break;
		case 3:
			objectOriented=7;
			break;
		case 4:
			objectOriented=6;
			break;
		default:
			objectOriented=null;
			break;
		}
		return objectOriented;
	}


/**
    * 订单取消支付
    * @throws Exception 
    * @Title: cannelOrderDeal 
    * @Description:
    */
   public String cannelOrderDeal(XmnOrderParamV2 orderParam,BillBean billBean) throws Exception{
	   
       billBean.setStatus(0);
	   billBean.setLdate(new Date());
       
       //将优惠券标记为已使用
       usedUserCoupon(billBean);
       
       //组装更新订单信息
       ModifyOrderInfoBean reqBean = new ModifyOrderInfoBean();
       reqBean.setBid(orderParam.getBid());
       reqBean.setStatus(0);
       reqBean.setCannelStatus(Integer.parseInt(orderParam.getStatus()));
       reqBean.setLdate(orderParam.getZdate());
       reqBean.setParentXmerUid(billBean.getTwoLevelXmerId());
       //更新订单信息
       orderDao.modifyOrderInfo(reqBean);
	   return orderParam.getBid();
   }
   
   /*更新商家分账数据*/ 
   private void calculateLedger(BillBean billBean){
	   Map<String,Object> resMap = null;
	   if(useSellerPackageConsume(billBean)){//套餐消费，属于特殊分账
		   //套餐订单分账
		   SellerPackageLedger bean = new SellerPackageLedger();
		   Map<String,Object> packageMap =  sellerPackageDao.getUserPackageInfo(billBean.getBid());
//		   if(billBean.getLiveCoin().compareTo(ZERO)>0){
			   bean.setOrderMoney(billBean.getCuser());
			   bean.setAgio(new BigDecimal(billBean.getBaseagio()+""));
//		   }else{
//			   bean.setOrderMoney((BigDecimal)packageMap.get("sellingCoinPrice"));
//			   bean.setLedgerAmount((BigDecimal)packageMap.get("sellingCoinPrice"));
//		   }
		   
		   if((Integer)packageMap.get("ledgerType")==1){
			   bean.setLedgerRatio(null);
			   bean.setLedgerAmount((BigDecimal)packageMap.get("ledgerRatio"));
		   }else{
			   bean.setLedgerRatio((BigDecimal)packageMap.get("ledgerRatio"));
			   bean.setLedgerAmount(null);
		   }
		   resMap = LedgerAlgorithmUtils.getPackageLedgerMoney(bean);
	   }else if(billBean.getLiveLedger()!=null && billBean.getLiveLedger()==1){//是否开启直播分账，属于特殊分账
		   //仅粉丝券分账时，若订单没使用粉丝券，则订单按正常途径分账
		   if(billBean.getLiveLedgerMode()==2 
			&& (billBean.getCouponType()==null || billBean.getCouponType()!=3)){
			   LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess2(billBean);
	           log.info("分账初始数据："+bean);
	           resMap = LedgerAlgorithmUtils.getLedgerMoney(bean);  //普通分账
		   }else{
			   LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess3(billBean);
//			   resMap = LedgerAlgorithmUtils.getLiveLedgerMoney(bean);  
		       log.info("直播分账初始数据："+bean); 
		       if(bean.getSellerExtraMoney()==0){
		    	   log.error("只针对粉丝券使用金额分账");
			       resMap = LedgerAlgorithmUtils.getLiveLedgerMoney(bean);   
		       }else if(bean.getSellerExtraMoney()>0){
		    	   log.error("针对粉丝券使用金额及超出支付金额分账");
		    	   LedgerNewBean bean2 = ledgerServiceImpl.ledgerInfoProcess2(billBean);
		    	   LedgerMixBean mixBean= ledgerServiceImpl.ledgerInfoProcess4(bean, bean2);
//		    	   LedgerMixBean mixBean = new LedgerMixBean();
//					mixBean.setOrderMoney(bean.getSellerExtraMoney());
//					mixBean.setLiveLedgerMoney(bean.getOrderMoney());
//					mixBean.setLiveLedgerRatio(bean.getBaseagio());
//					mixBean.setUserMoney(bean.getUserMoney());
//					mixBean.setBaseagio(billBean.getBaseagio());//直播分账比例
//					mixBean.setLedgerMode(bean2.getLedgerMode());//新增分账模式
//					mixBean.setMikeType(bean2.getMikeType());
//					mixBean.setSellerId(bean2.getSellerId());
//					mixBean.setMikeId(bean2.getMikeId());
//					mixBean.setGenusSellerId(bean2.getGenusSellerId());
//					mixBean.setParentMikeId(bean2.getParentMikeId());
//					mixBean.setTopMikeId(bean2.getTopMikeId());
//					mixBean.setConsumeJointid(bean2.getConsumeJointid());
					log.info("新的分账实体:"+mixBean);
					resMap = LedgerAlgorithmUtils.getLiveMixLedgerMoney(mixBean);   
		       }
		   }
		  
	   }else{
           LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess2(billBean);
           log.info("分账初始数据："+bean);
           resMap = LedgerAlgorithmUtils.getLedgerMoney(bean);   
	   }
	   
       log.info("ledgerSystem resMap::" + resMap);
       
       if(null != resMap && !resMap.isEmpty())
       {
       	JSONObject json=(JSONObject) JSONObject.toJSON(resMap);
       	json.put("proportion", json.getString("proportion"));
        
       	JSONObject commissionJson=(JSONObject) resMap.get("commission");
        String sellerAmount = commissionJson.getString("seller_amount");
        if(billBean.getSellerCoin().compareTo(ZERO)>0){
           	BigDecimal realSellerLedger = sellerRechargeOrderService.updateSellerOrderSurplus(billBean.getSellerid(),billBean.getUid() ,new BigDecimal(sellerAmount));
           	commissionJson.put("seller_amount", ""+realSellerLedger);
           	commissionJson.put("expected_seller_amount", ""+sellerAmount);
        }else{
	       	commissionJson.put("expected_seller_amount", ""+sellerAmount);
        }
       	
       	
       	billBean.setCommission(json.getString("commission"));
       	billBean.setProportion(json.getString("proportion"));;
       }
       //是不是手动分账的直播分账订单
       boolean liveHandleLedger = false;
       if(billBean.getLiveLedger()!=null
    	&& billBean.getLiveLedger()==1
    	&& billBean.getLiveLedgerStyle()==1){
    	   liveHandleLedger = true;
       }
       //不为不分账订单或者不为手动分账的直播分账订单
       if(billBean.getLedgerMode()==1 || liveHandleLedger){
       }else{
    	   billBean.setHstatus(10);
       }
       
   }
   
   /**
    * 
    * 方法描述：处理消费分账信息 <br/>
    * 创建人：  huang'tao <br/>
    * 创建时间：2017-5-5下午4:31:33 <br/>
    * @param billBean
    */
   private void calculateLedgerV2(BillBean billBean){
	   Map<String,Object> resMap = null;
	   if(useSellerPackageConsume(billBean)){//套餐消费，属于特殊分账
		   //套餐订单分账
		   SellerPackageLedger bean = new SellerPackageLedger();
		   Map<String,Object> packageMap =  sellerPackageDao.getUserPackageInfo(billBean.getBid());
		   bean.setOrderMoney(billBean.getCuser());
		   bean.setAgio(new BigDecimal(billBean.getBaseagio()+""));
		   
		   if((Integer)packageMap.get("ledgerType")==1){
			   bean.setLedgerRatio(null);
			   bean.setLedgerAmount((BigDecimal)packageMap.get("ledgerRatio"));
		   }else{
			   bean.setLedgerRatio((BigDecimal)packageMap.get("ledgerRatio"));
			   bean.setLedgerAmount(null);
		   }
		   resMap = LedgerAlgorithmUtils.getPackageLedgerMoney(bean);
	   }else if(billBean.getLiveLedger()!=null && billBean.getLiveLedger()==1){//是否开启直播分账，属于特殊分账
		   //仅粉丝券分账时，若订单没使用粉丝券，则订单按正常途径分账 
		   if(billBean.getLiveLedgerMode()==2 //live_ledger_mode 直播分账模式 1 全额分账 2 仅粉丝券分账
			&& (billBean.getCouponType()==null || billBean.getCouponType()!=3)){
			   
			   //准备消费分账信息
			   resMap=prepareConsumptionLedgerInfo(billBean);
	           
		   }else{
			   LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess3(billBean);
		       log.info("直播分账初始数据："+bean); 
		       if(bean.getSellerExtraMoney()==0){
		    	   log.error("只针对粉丝券使用金额分账");
			       resMap = LedgerAlgorithmUtils.getLiveLedgerMoney(bean);   
		       }else if(bean.getSellerExtraMoney()>0){
		    	   log.error("针对粉丝券使用金额及超出支付金额分账");
		    	   LedgerNewBean bean2 = ledgerServiceImpl.ledgerInfoProcess2(billBean);
		    	   LedgerMixBean mixBean= ledgerServiceImpl.ledgerInfoProcess4(bean, bean2);
					log.info("新的分账实体:"+mixBean);
					resMap = LedgerAlgorithmUtils.getLiveMixLedgerMoney(mixBean);   
		       }
		   }
		  
	   }else if(billBean.getLedgerMode()!=null && billBean.getLedgerMode().intValue() ==2){ //ledger_mode 0 正常分账模式 1 不参与分账 2 仅商家参与分账
           LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess2(billBean);
           log.info("分账初始数据："+bean);
           resMap = LedgerAlgorithmUtils.getLedgerMoney(bean);
           
	   }else{
		 //准备消费分账信息
		   resMap = prepareConsumptionLedgerInfo(billBean);
	   }
	   
       log.info("ledgerSystem resMap::" + resMap);
       
       if(null != resMap && !resMap.isEmpty())
       {
	       	JSONObject json=(JSONObject) JSONObject.toJSON(resMap);
	       	json.put("proportion", json.getString("proportion"));
	        
	       	JSONObject commissionJson=(JSONObject) resMap.get("commission");
	        String sellerAmount = commissionJson.getString("seller_amount");
	        if(billBean.getSellerCoin().compareTo(ZERO)>0){
	           	BigDecimal realSellerLedger = sellerRechargeOrderService.updateSellerOrderSurplus(billBean.getSellerid(),billBean.getUid() ,new BigDecimal(sellerAmount));
	           	commissionJson.put("seller_amount", ""+realSellerLedger);
	           	commissionJson.put("expected_seller_amount", ""+sellerAmount);
	        }else{
		       	commissionJson.put("expected_seller_amount", ""+sellerAmount);
	        }
	       	
	       	
	       	billBean.setCommission(json.getString("commission"));
	       	billBean.setProportion(json.getString("proportion"));
	       	
	       	//保存脉宝网络分账消息
	       	saveMaiboLedgerInfo(resMap,billBean);
       }
       //是不是手动分账的直播分账订单
       boolean liveHandleLedger = false;
       if(billBean.getLiveLedger()!=null
    	&& billBean.getLiveLedger()==1
    	&& billBean.getLiveLedgerStyle()==1){
    	   liveHandleLedger = true;
       }
       //不参与分账或直播手动分账
       if(billBean.getLedgerMode()==1 || liveHandleLedger){
       }else{
    	   billBean.setHstatus(10);
       }
       
   }


 /**
 * 方法描述：保存云脉网络分账通知 <br/>
 * 创建人：  huang'tao <br/>
 * 创建时间：2017-5-16下午5:00:09 <br/>
 * @param resMap
 * @param billBean
 */
private void saveMaiboLedgerInfo(Map<String, Object> resMap, BillBean billBean) {
	this.log.info("保存云脉网络分账通知,订单号:"+billBean.getBid());
	Long bid = billBean.getBid();
	try {
		if(bid!=null && !resMap.isEmpty()){
			Map<String, Object> maibaoLedgerInfo = saasOrderDao.getMaibaoLedgerByorderNo(bid.toString());
			String maibao_amount = resMap.get("maibao_amount")==null ? "0":resMap.get("maibao_amount").toString();
			BigDecimal maibaoAmount = new BigDecimal(maibao_amount);
			boolean toAdd=maibaoAmount!=null && maibaoAmount.compareTo(BigDecimal.ZERO)>0 && maibaoLedgerInfo==null;
			if(toAdd){
				Map<String,Object> maibaoLedgerMap = new HashMap<String,Object>();
				maibaoLedgerMap.put("transNo", billBean.getBid());
//				maibaoLedgerMap.put("orderType", 2);//订单类型 1 购买SAAS订单 2 美食订单
				maibaoLedgerMap.put("mobile", billBean.getPhoneid());
				maibaoLedgerMap.put("ecno", billBean.getUidMbEcno());//脉客中脉EC号
				maibaoLedgerMap.put("uid", billBean.getUid());//脉客UID
				maibaoLedgerMap.put("amount",billBean.getCalculateRealPayMent());//订单总金额
				maibaoLedgerMap.put("ledgerTime", new Date());//支付时间
				maibaoLedgerMap.put("ledgerAmount", maibaoAmount);//云脉网络分账金额
				int merchantType=billBean.getSaasChannel()!=null &&billBean.getSaasChannel().intValue()==2?2:1;
				maibaoLedgerMap.put("merchantType", merchantType);//签约商家SAAS类型 1.其他SAAS类型 2.脉客SAAS类型
				maibaoLedgerMap.put("updateTime", new Date());//更新时间
				maibaoLedgerMap.put("merchantName", billBean.getSellername());//商家名
				maibaoLedgerMap.put("discount", billBean.getBaseagio());//商家折扣
				Integer xmerUid = billBean.getXmerUid();
				if(xmerUid != null){
					Map<String, Object> ursInfoMap = ursDao.getUrsByUid(xmerUid.toString());
					if(ursInfoMap!=null){
						maibaoLedgerMap.put("signedEcno", ursInfoMap.get("mbEcno"));//签店脉客EC号
					}
				}
				maibaoLedgerMap.put("notifyState", 0);//通知状态 0 未通知 1已通知  2通知失败
				
				
				saasOrderDao.insertMaibaoLedgerNotify(maibaoLedgerMap);
				Object id = maibaoLedgerMap.get("id");
				if(id!=null){
					this.log.info("保存云脉网络分账队列:key = "+maibaoLedgerQueueKey);
					redisTemplate.opsForList().leftPush(maibaoLedgerQueueKey, id.toString());
				}
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
		this.log.error("保存云脉网络分账通知异常");
	}
}


/**
 * 方法描述：准备消费分账信息 <br/>
 * 创建人：  huang'tao <br/>
 * 创建时间：2017-5-12下午4:33:53 <br/>
 * @param billBean
     * @return 
 */
private Map<String, Object> prepareConsumptionLedgerInfo(BillBean billBean) {
	Map<String,Object> resMap=null;
	
	LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess2(billBean);
    log.info("分账初始数据："+bean);
    
    Integer saasChannel = billBean.getSaasChannel();//寻蜜客签约商家SAAS来源: null为非SAAS签约商户(平台签的店) 1 常规SAAS签约 2脉客SAAS签约 3V客SAAS签约 4主播(V客赠送)SAAS签约
    String uidMbEcno = billBean.getUidMbEcno();//会员脉宝EC号: 非空脉宝消费订单
    if(StringUtils.isNotBlank(uidMbEcno)){//脉宝订单
 	   
 	   /*if(saasChannel==null || saasChannel.intValue()==1){// 平台签约的店铺/常规SAAS签约店铺
 		   
 		   resMap= LedgerAlgorithmUtils.getLedgerMoneyOfCommon(bean);
 		   
 	   }else if(saasChannel.intValue()==2){ //2脉客SAAS签约 
 		   
 		   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfMaike(bean);
 		   
 	   }else if(saasChannel.intValue()==3){//3V客SAAS签约
 		   
 		   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfVke(bean);
 		   
 	   }else if(saasChannel.intValue()==4){//4主播(V客赠送)SAAS签约
 		   
 		   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfAnchorVke(bean);
 	   }*/
    	
       if(saasChannel!=null && saasChannel.intValue()==2){//2脉客SAAS签约 
    	   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfMaike(bean);
       }else {//非脉客SAAS签约
    	   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfNotMaike(bean);
       }
 	   
    }else{//其他订单
 	   if(saasChannel==null || saasChannel.intValue()==1 || saasChannel.intValue()==2){// 平台签约的店铺/常规SAAS签约店铺
 		   
 		   resMap = LedgerAlgorithmUtils.getLedgerMoney(bean);  //普通分账
 		   
 	   }/*else if(saasChannel.intValue()==2){ //2脉客SAAS签约 
 		   
 		   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfMaikeOther(bean);
 		   
 	   }*/else if(saasChannel.intValue()==3){//3V客SAAS签约
 		   
 		   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfVkeOther(bean);
 		   
 	   }else if(saasChannel.intValue()==4){//4主播(V客赠送)SAAS签约
 		   
 		   resMap=LedgerAlgorithmUtils.getLedgerMoneyOfAnchorVkeOther(bean);
 	   }
    }
    
    return resMap;
	
}


/**
    * 获取商家订单信息
    */
   @Override
	public ResponseData getOrderLedgerInfo(Map<String,String> paraMap) throws FailureException,
			TException {
		return orderLedgerInfoService.getOrderLedgerInfo(paraMap);
	}
	

	@Override
	public Map<String, ResponseData> getOrderLedgerInfoList(
			List<Map<String, String>> paraList) throws FailureException,
			TException {
		return orderLedgerInfoService.getOrderLedgerInfoList(paraList);
	}

	/**
	 * 更新订单支付状态接口
	 */
	@Override
	public String updateXmnOrderInfoV2(XmnOrderParamV2 orderParam)
			throws FailureException, TException {

		log.info("updateXmnOrderInfo:"+orderParam);
		long starTime = System.currentTimeMillis();
		try{
			preVerifyOrderParam(orderParam);
		}catch(Exception e){
			log.error("校验数据异常",e);
			throw new FailureException(107,e.getMessage());
		}
		
        // 更新该订单之前,先获取该条订单是否存在
		String bid = orderParam.getBid();
        BillBean billBean = orderDao.getBillBean(orderParam.getBid());
        log.info("获取的订单信息："+billBean);
        
        // 若有该订单信息,则再进行下步操作
        if (null == billBean)
        {
            log.error("没有获取到订单号为:【" + orderParam.getBid() + "】订单信息");
            throw new FailureException(ResponseState.ORDERNULL, "没有获取到订单号为:"+ orderParam.getBid() + "订单信息");
        }
        // 订单状态
        int status = billBean.getStatus();
        // 若订单状态未更新为1待返现,则进行后面业务操作,反之，则无需再进行后面业务操作
        if (status >= 1 && status!=14){
            log.error("该订单【" + orderParam.getBid() + "】已重复支付");
            throw new FailureException(ResponseState.ORDER_READY_MODIFY,"该订单已重复支付");
        }

        if(orderParam.getStatus().equals(billBean.getStatus().toString())){
        	log.info("重复修改订单状态");
        	return bid;
        }
        if(orderParam.getStatus().matches("^[2-3]$")){
        	log.info("订单支付取消或失败");
        	try{
        		
        		//设置寻蜜客相关信息
        		setBillBeanOfXmerInfo(billBean);
        		
        		cannelOrderDeal(orderParam,billBean);
        	}catch(Exception e){
        		log.error("更新订单支付失败/取消异常",e);
        		throw new FailureException(ResponseState.ELSEEROR,"更新订单失败");
        	}
        	return bid;
        }else if(orderParam.getStatus().matches("^[4]$")){
        	//未完成订单
        	
        }else if(orderParam.getStatus().matches("^[1]$")){
        	log.info("订单支付成功");
        }
        if(!this.verifyOrderParam(orderParam,billBean)){
            log.error("该订单【" + orderParam.getBid() + "】数据校验失败");
            throw new FailureException(ResponseState.PARAM_ERROR,"该订单数据校验失败");
        }
        try
        {
       	
            /*
             * 分账系统的分账类型与支付系统的分账类型的关系
             * 分账系统 1是手动分账 2是自动分账 
             * request中分账类型  1是自动分账 2是手动
             */
            int ledgerType =0;
            Map<String, String> modeMap =commonServiceImpl.getSellerMentionLedger(billBean.getSellerid(), 2);
            if(modeMap!=null){
            	if(modeMap.get("state").equals("0")){
            		ledgerType=2;
            	}else {
            		ledgerType=1;
            	}
            }
            if(useSellerPackageConsume(billBean)){
            	ledgerType=1;
            }
            if(ledgerType==0){
            	throw new Exception("获取商家的分账类型失败");
            }
            orderParam.setLedgertype(ledgerType+"");
        	Map<String,String> uMap = new HashMap<String,String>();
        	uMap.put("uid", billBean.getUid()+"");
        	
        	//绑定商家信息
        	Map<String,Object> urs = ursDao.getUrsAscription(uMap);
        	
        	//是否首单信息
            Map<String, String> resMikeMap = new HashMap<String, String>();
            resMikeMap.put("mikeType", "2");
            resMikeMap.put("bfirst", "0");
            
            //首单判断
            log.info("消费会员【"+billBean.getUid()+"】当前的所属信息:"+urs);
            if(urs.get("genussellerid") == null)
            {           
            	log.info("会员消费首单");
                resMikeMap.put("mikeType", "2");
                resMikeMap.put("bfirst", "1");
            }      
            
            SellerBean seller = sellerDao.getSellerInfo(billBean.getSellerid());
            
            //准备最新订单信息
            modifyBillBean2(billBean,orderParam,resMikeMap,seller);//  寻蜜客消费分账入口
            
            log.info("更新后，订单信息："+billBean);

            //将优惠券标记为已使用
            usedUserCoupon(billBean);
            
            //更新订单信息 TODO 18:19 更新上级寻蜜客Uid
            mdyOrderInfoProcess2(billBean);
            
            //向用户赠送积分
            //consumerRebate(billBean);
            
            //向用户赠优惠券
            //consumerCoupon(billBean);
            
            //向用户赠商家券
            //if(billBean.getCouponType()==null){
            	consumerSellerCoupon(billBean);
            //}
            
            //向用户赠商家红包
            consumerRedPacket(billBean);
            
            //向推荐人赠商家推荐人红包
            consumerRecommendRedPacket(billBean);
            
            //使用商家消费满减活动
            consumerFullreduction(billBean);
            
            //商家免费尝新活动
            consumerFreetry(billBean);
            
            //商家免费尝新活动
            consumerRoullete(billBean);
            
            //商家聚点活动
            consumerFcouspoints(billBean);
            
            //向主播送鸟蛋活动
            consumerAnchorLedger(billBean);
            
            if((urs.get("genussellerid") == null || "".equals(urs.get("genussellerid")))
            		&& billBean.getGenussellerid() != null)
            {
                //消费绑定商家
            	log.info("首单绑定商家");
            	sellerMikeBuilding(billBean);
            }
            
            //插入分账消息队列
            pushLedgerRedis(billBean,zdQueueKey,Integer.parseInt(orderParam.getLedgertype()));
            
            //插入订单处理记录接口
             /*2016-02-23 删除插入订单记录信息，减少INSERT语句 提高订单处理速度*/
            Map<String, String> reqMap = new HashMap<String, String>();
            reqMap.put("bid", orderParam.getBid());
            reqMap.put("status", "1");
            reqMap.put("explains", "订单号" + orderParam.getBid() + "支付订单（支付系统）支付成功");
            insertBillRecord(reqMap);
            
            // 发送短信处理方法   微信推送消息，此处不判断手机号码是否存在
            if(isCurrentOrder(billBean.getZdate())){
            	sendSms(billBean, orderParam.getPhoneid());
            }
            
            // 推送类型,1为订单支付成功
            String orderPushType = "1";
            
            // 订单支付成功后,调用推送消息发送redis队列方法
           this.pushMsgRedisProcess2(billBean, orderPushType);
            
           // 发送短信至商家
           if(seller != null){
               JSONObject json = JSON.parseObject(billBean.getCommission());  
               String str=json.getString("seller_amount");
        	   sendSmsForSeller(billBean.getSellerid(),billBean.getBid()+"",str); 
           }
           if(seller.getIsPublic()== null || seller.getIsPublic()==1){//是可见商家才记录其消费信息
        	   updateUserActionQueue(billBean);
           }
            // 更新订单接口流程操作完成后,返回订单号
            long endTime = System.currentTimeMillis();
            log.info("更新订单接口总耗时:" + (endTime - starTime) + "ms");
            
            //发送订单流水记录消息
            sendBillRecordMQ(bid);
        }
        catch (Exception ex)
        {
            log.error("更新订单接口服务异常", ex);
            throw new FailureException(ResponseState.ORDERFAIL, "更新订单接口服务异常");
        }
        log.info("updateOrderInfo end......");
        return bid;
	}
	
	/*
	 * 是否是套餐消费
	 */
	public boolean useSellerPackageConsume(BillBean billBean){
		if(billBean.getCouponType() != null
			&& billBean.getCouponType()==4
			&& billBean.getCuser() != null
			&& billBean.getCuser().compareTo(ZERO)>0){
			return true;
		}
		return false;
	}
}