/**    
 * 文件名：AdjustOrderService.java    
 *    
 * 版本信息：    
 * 日期：2017年3月23日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.MapUtil;
import com.xmniao.common.NullU;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.ledger.LedgerBean;
import com.xmniao.domain.order.AdjustApplyBean;
import com.xmniao.service.ledger.LedgerServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：AdjustOrderService
 * 
 * 类描述： 调单旧代码
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月23日 下午5:37:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service("adjustOrderServiceImpl")
public class AdjustOrderServiceImpl {
	
	
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(AdjustOrderServiceImpl.class);
	
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
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    
    /**
     * 注入分账系统服务接口DAO层
     */
    @Autowired
    private LedgerServiceImpl ledgerServiceImpl;
    
    /**
     * 注入调单更新商户订单统计实现类
     */
    @Autowired
    private ModifySellerDayCensusImpl mdySellerDayCensusImpl;
    
    /**
     * 注入调单更新合作商订单统计实现类
     */
    @Autowired
    private ModifyJointDayCensusImpl mdyJointDayCensusImpl;
    
    /**
     * 注入调单更新合作商收益订单统计实现类
     */
    @Autowired
    private ModifyJointProfitDayCensusImpl mdyJointProfitDayCensusImpl;
    
    
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    

    @Resource(name="xmkqueue")
    private String mikeQueue;
    
    /**
     * 获取调单前的订单信息
     * @param paraMap paraMap [查询的请求参数]
     * @return Map [查询结果MAP]
     * @throws FailureException
     * @throws TException
     */
    public Map<String, String> queryAdjBeforeOrderInfo(Map<String, String> paraMap) 
            throws FailureException, TException
    {
        log.info("queryAdjBeforeOrderInfo start::"+paraMap);;
        Map<String, String> resMap = new HashMap<String, String>();
        //1：无风险的订单；  2：金额大于3000 的订单 ;3：5分钟内提交3次 的订单 ;4：商户折扣变化的订单 ;5：商户折扣  > 50%  订单金额 > 1000
        Integer windcontrol = 1;        

        //获取调单前的订单信息
        Map<String, Object> queryMap=orderDao.queryAdjBeforeOrderInfo(paraMap);
        if(null==queryMap)
        {
            log.error("获取调单前的订单信息为空");
            resMap.put("code","1");
            resMap.put("remark","获取调单前的订单信息为空");
            return resMap;
        }
        String orderStatus="1,2,3,6,8,9";
        if(orderStatus.contains(String.valueOf(queryMap.get("orderState")))==false)
        {
            log.error("调单前的订单状态不符合规则");
            resMap.put("code","1");
            resMap.put("remark","调单前的订单状态不符合规则");
            return resMap;
        }
        
        try
        {
            //计算风险识别
            Double expense = NumberUtils.toDouble(queryMap.get("money")+"");          /* 订单金额*/
            Double corrbaseagio = NumberUtils.toDouble(queryMap.get("baseagio")+"");    /* 订单折扣*/
            if( expense >= WIND_CONTROL_MONEY)
            {     /* 查过3000的订单进入风控*/
                log.info("订单金额大于3000：订单实际金额："+queryMap.get("money"));
                windcontrol = 2;
            }else
            {                  
                if(corrbaseagio < 0.5 && expense > 1000.00)
                {
                    log.debug("商户折扣  > 50%  订单金额 > 1000，折扣："+corrbaseagio+"，订单金额"+expense);
                    windcontrol = 5;        /* 商户折扣  > 50%  订单金额 > 1000*/
                }else
                { 
                    SimpleDateFormat mft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date enddate = mft.parse(String.valueOf(queryMap.get("zdate")));     /* 该订单的时间为截止时间*/
                    Date startdate = DateUtils.addMinutes(enddate, WIND_CONTROL_AMOUNT);
                    Map<String,Object> parammap = new HashMap<String,Object>();
                    parammap.put("sellerid", queryMap.get("sellerId"));
                    parammap.put("uid", queryMap.get("uid"));
                    parammap.put("startdate", startdate);
                    parammap.put("enddate", enddate);
                    Integer count = orderDao.findBillBySelloruser(parammap);
                    log.debug("查询该订单的5分钟之内的订单，起始时间为："+DateUtil.dateFormat1(startdate)+";终止时间为："+DateUtil.dateFormat1(enddate)+"；查询结果条数："+count);
                    if(NullU.nvl(count) > 0 && count > WIND_CONTROL_AMOUNT_COUNT)
                    {
                        windcontrol = 3;                        /* 5分钟内提交3次 的订单*/
                    }else
                    {                                      /*于上一笔订单折扣发生变化*/
                        Double baseagio = orderDao.findPreviousBillBySell(parammap);
                        if(NullU.nvl(baseagio) > 0)
                        {                                
                            log.debug("查询该订单的折扣是否变化，改订单折扣："+corrbaseagio+";上一笔订单折扣："+baseagio);
                            if(!baseagio.equals(corrbaseagio))
                            {
                                windcontrol = 4;                /* 商户折扣变化的订单*/
                            }
                        }
                    }
                }
            }
            resMap.putAll(MapUtil.formatMapStr(queryMap));
            resMap.put("code", "0");
            resMap.put("divideType", "2");
            resMap.put("dealType", "1");
            resMap.put("dealState", "1");
            resMap.put("mikeId", String.valueOf(queryMap.get("genussellerid")));
            resMap.put("mikeName", String.valueOf(queryMap.get("genusname")));
            resMap.put("mikeType", "2");
            
            //获取商户区域信息
            Map<String,String> sellerMap = orderDao.findSellerByid(NumberUtils.toLong(resMap.get("sellerId")));
            sellerMap = sellerMap == null ? new HashMap<String, String>() : sellerMap;
            if(sellerMap.containsKey("typename") && StringUtils.isNotBlank(sellerMap.get("typename")))
            {
                resMap.put("sellerIndustry", sellerMap.get("typename"));
            }else
            {
                resMap.put("sellerIndustry", "");
            }
            if(sellerMap.containsKey("area") && StringUtils.isNotBlank(sellerMap.get("area")))
            {
                resMap.put("sellerArea", sellerMap.get("area"));
            }else
            {
                resMap.put("sellerArea", "");
            }
            Long sellerarea = NumberUtils.toLong(sellerMap.get("area"));
            String sellerareaname = orderDao.findAreaByid(sellerarea);
            if(StringUtils.isNotBlank(sellerareaname))
            {
                resMap.put("sellerAreaName", sellerareaname);
            }else
            {
                resMap.put("sellerAreaName", ""); 
            }
            //获取用户区域信息
            Long userarea = 0l; 
            String userareaname="无区域";
            Map<String,String> sellerHcMap = orderDao.findSellerByid(NumberUtils.toLong(resMap.get("mikeId")));
            sellerHcMap = sellerHcMap == null ? new HashMap<String, String>() : sellerHcMap;
            userarea = NumberUtils.toLong(sellerHcMap.get("area"));
            userareaname = orderDao.findAreaByid(userarea);
            resMap.put("userArea", userarea+"");
            if(StringUtils.isNotBlank(userareaname))
            {
                resMap.put("userAreaName", userareaname);
            }else
            {
                resMap.put("userAreaName", "");
            }
            
            resMap.put("windControl", windcontrol+"");
            /*获取所属商户区域信息*/
            String genussellerArea=orderDao.querySellerArea(String.valueOf(queryMap.get("genussellerid")));
            if(StringUtils.isNotBlank(genussellerArea) && String.valueOf(queryMap.get("sellerArea")).equals(genussellerArea))
            {
                resMap.put("isInterregional", "0");
            }else
            {
                resMap.put("isInterregional", "1");
            }
            resMap.put("subsidy", String.valueOf(queryMap.get("subsidy")));
            resMap.put("subsidy_money", String.valueOf(queryMap.get("subsidy_money")));
            resMap.put("infoType", "1");
            resMap.put("commission", String.valueOf(queryMap.get("commission")));
        }
        catch (Exception e)
        {
            log.error("获取调单前的订单信息异常",e);
            throw new FailureException(ResponseState.ELSEEROR,"获取调单前的订单信息异常");
        }
        log.info("queryAdjBeforeOrderInfo end"+resMap);
        return resMap;
    }
    
    /**
     * 查询调单后的订单信息
     * @param paraMap [查询的请求参数]
     * @return Map [查询结果MAP]
     * @throws FailureException
     * @throws TException
     */
    public Map<String, String> queryAdjustOrderInfo(Map<String, String> paraMap)
            throws FailureException, TException
    {
        log.info("queryAdjustOrderInfo start:"+paraMap);
        Map<String, String> resMap = new HashMap<String,String>();
        try
        {
            //查询调单后的订单信息
            Map<String, String> queryMap = orderDao.queryAdjAfterOrderInfo(paraMap.get("orderId"));
            if(null==queryMap)
            {
                log.error("没有查询出调单后的订单信息");
                resMap.put("code", "1");
                resMap.put("remark", "没有查询出调单后的订单信息");
                return resMap;
            }
            //判断调单后的订单状态是否为退款
            String orderStatus="1,2,3,6,8,9";
            if(orderStatus.contains(queryMap.get("status"))==false)
            {
                log.error("调单后的订单状态不符合规则");
                resMap.put("code","1");
                resMap.put("remark","调单后的订单状态不符合规则");
                return resMap;
            }
            resMap.put("code", "0");
            resMap.put("orderId", paraMap.get("orderId"));
            resMap.put("sellerId", paraMap.get("sellerId"));
            resMap.put("sellerRation", String.valueOf(queryMap.get("baseagio")));
            
            //查询商户信息
            Map<String,Object> querySellerMap=orderDao.querySellerInfos(paraMap.get("sellerId"));
            if(null!=querySellerMap)
            {
                if(querySellerMap.containsKey("sellername") && null!=querySellerMap.get("sellername"))
                {
                    resMap.put("sellerName", String.valueOf(querySellerMap.get("sellername")));
                }else
                {
                    resMap.put("sellerName", "");
                }
                /* 验证调单前后的合作商是否相同 */
                if(querySellerMap.containsKey("jointid")==false && null==querySellerMap.get("jointid"))
                {
                    /*获取商户区域信息*/
                    Map<String,String> sellerAreaMap=orderDao.findSellerByid(Long.parseLong(paraMap.get("sellerId")));
                    if(sellerAreaMap.containsKey("area") && StringUtils.isNotBlank(sellerAreaMap.get("area")))
                    {
                        resMap.put("sellerAreaId", sellerAreaMap.get("area"));
                    }else
                    {
                        resMap.put("sellerAreaId", "");
                    }
                    
                    String sellerAreaname = orderDao.findAreaByid(Long.parseLong(sellerAreaMap.get("area")));
                    if(StringUtils.isNotBlank(sellerAreaname))
                    {
                        resMap.put("sellerAreaName", sellerAreaname);
                    }else
                    {
                        resMap.put("sellerAreaName", "");
                    }
                }else
                {
                    if(!String.valueOf(querySellerMap.get("jointid")).equals(queryMap.get("consume_jointid")))
                    {
                        resMap.put("cpartnerId", String.valueOf(querySellerMap.get("jointid")));
                        String jointName=orderDao.queryJointInfos(String.valueOf(querySellerMap.get("jointid")));
                        if(StringUtils.isNotBlank(jointName))
                        {
                            resMap.put("cpartnerName", jointName);
                        }
                    }else
                    {
                        resMap.put("cpartnerId", String.valueOf(querySellerMap.get("jointid")));
                        String jointName=orderDao.queryJointInfos(String.valueOf(querySellerMap.get("jointid")));
                        if(StringUtils.isNotBlank(jointName))
                        {
                            resMap.put("cpartnerName", jointName);
                        }
                    }
                    /*获取商户区域信息*/
                    Map<String,String> sellerAreaMap=orderDao.findSellerByid(Long.parseLong(paraMap.get("sellerId")));
                    resMap.put("sellerAreaId", NullU.nvl(sellerAreaMap.get("area")));
                    String sellerAreaname = orderDao.findAreaByid(Long.parseLong(sellerAreaMap.get("area")));
                    if(StringUtils.isNotBlank(sellerAreaname))
                    {
                        resMap.put("sellerAreaName", sellerAreaname);
                    }else
                    {
                        resMap.put("sellerAreaName", "");
                    }
                }
                /*  是否首单 */
                if(String.valueOf(queryMap.get("bfirst")).equals("1") 
                   && String.valueOf(queryMap.get("sellerid")).equals(String.valueOf(queryMap.get("genussellerid"))))
                {
                    /* 寻蜜客 */
                    resMap.put("mikeId", paraMap.get("sellerId"));
                    resMap.put("mikeName", String.valueOf(querySellerMap.get("sellername")));
                    /* 所属商家 */
                    resMap.put("genussellerid", paraMap.get("sellerId"));
                    resMap.put("genusname", String.valueOf(querySellerMap.get("sellername")));
                    /* 所属区域合作商 */
                    resMap.put("bpartnerId", String.valueOf(querySellerMap.get("jointid")));
                    
                    String jointName=orderDao.queryJointInfos(String.valueOf(querySellerMap.get("jointid")));
                    if(StringUtils.isNotBlank(jointName))
                    {
                        resMap.put("bpartnerName", jointName);
                    }else
                    {
                        resMap.put("bpartnerName", "");
                    }
                    //获取用户区域信息
                    Long userarea = 0l; 
                    String userareaname="无区域";
                    Map<String,String> sellerHcMap = orderDao.findSellerByid(Long.valueOf(paraMap.get("sellerId")));
                    sellerHcMap = sellerHcMap == null ? new HashMap<String, String>() : sellerHcMap;
                    userarea = NumberUtils.toLong(sellerHcMap.get("area"));
                    userareaname = orderDao.findAreaByid(userarea);
                    resMap.put("user_area", userarea+"");
                    if(StringUtils.isNotBlank(userareaname))
                    {
                        resMap.put("user_area_name", userareaname);
                    }else
                    {
                        resMap.put("user_area_name", "");
                    }
                }else
                {
                    /* 所属商家 */
                    resMap.put("genussellerid", String.valueOf(queryMap.get("genussellerid")));
                    resMap.put("genusname", queryMap.get("genusname"));
                    /* 寻蜜客 */
                    resMap.put("mikeId", String.valueOf(queryMap.get("genussellerid")));
                    resMap.put("mikeName", queryMap.get("genusname"));
                    
                    /* 所属区域合作商 */
                    resMap.put("bpartnerId", String.valueOf(queryMap.get("jointid")));
                    resMap.put("bpartnerName", String.valueOf(queryMap.get("corporate")));
                    
                    //获取用户区域信息
                    Long userarea = 0l; 
                    String userareaname="无区域";
                    Map<String,String> sellerHcMap = orderDao.findSellerByid(Long.valueOf(String.valueOf(queryMap.get("genussellerid"))));
                    sellerHcMap = sellerHcMap == null ? new HashMap<String, String>() : sellerHcMap;
                    userarea = NumberUtils.toLong(sellerHcMap.get("area"));
                    userareaname = orderDao.findAreaByid(userarea);
                    resMap.put("user_area", userarea+"");
                    if(StringUtils.isNotBlank(userareaname))
                    {
                        resMap.put("user_area_name", userareaname);
                    }else
                    {
                        resMap.put("user_area_name", "");
                    }
                }
            }
            Map<String,String> sellerAreaMap=orderDao.findSellerByid(Long.parseLong(paraMap.get("sellerId")));
            if(sellerAreaMap.containsKey("typename") && StringUtils.isNotBlank(sellerAreaMap.get("typename")))
            {
                resMap.put("sellerIndustry", sellerAreaMap.get("typename"));
            }else
            {
                resMap.put("sellerIndustry", "");
            }
            
            /*获取所属商户区域信息*/
            String genussellerArea=orderDao.querySellerArea(resMap.get("genussellerid"));
            if(StringUtils.isNotBlank(genussellerArea)&& String.valueOf(resMap.get("sellerAreaId")).equals(genussellerArea))
            {
                resMap.put("isInterregional", "0");
            }else
            {
                resMap.put("isInterregional", "1");
            }
            
            resMap.put("subsidy", String.valueOf(queryMap.get("flat_agio")));
            resMap.put("subsidy_money", String.valueOf(queryMap.get("flat_money")));
            resMap.put("infoType", "2");
            //更新调单后的订单佣金JSON处理
            mdyAdjCommissionProcess(resMap,queryMap);
        }
        catch (Exception e)
        {
            log.error("获取调单后的订单信息异常",e);
            throw new FailureException(ResponseState.ELSEEROR,"获取调单后的订单信息异常");
        }
        log.info("queryAdjustOrderInfo end"+resMap);
        return resMap;
    }
    
    /**
     * 更新调单后的订单佣金JSON处理
     * @param resMap [调单后返回的结果MAP]
     * @param queryMap [调单后的订单信息MAP]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void mdyAdjCommissionProcess(Map<String, String> resMap,Map<String, String> queryMap)
    {
        Map<String,Object> commissionMap = new HashMap<String,Object>();
        commissionMap.put("bid", resMap.get("orderId"));
        commissionMap.put("uid", queryMap.get("uid"));
        commissionMap.put("sellerid", resMap.get("sellerId"));
        commissionMap.put("genussellerid", resMap.get("genussellerid"));
        commissionMap.put("baseagio", queryMap.get("baseagio"));
        commissionMap.put("paytype", queryMap.get("paytype"));
        commissionMap.put("flat_agio", queryMap.get("flat_agio"));
        commissionMap.put("jointid", resMap.get("bpartnerId"));
        commissionMap.put("consume_jointid", resMap.get("cpartnerId"));
        commissionMap.put("area", resMap.get("sellerAreaId"));
        commissionMap.put("money", queryMap.get("money"));
        LedgerBean reqBean = ledgerServiceImpl.ledgerInfoProcess(commissionMap);
        Map<String,Object> resCommissionMap=ledgerServiceImpl.getLedgerMoney(reqBean);
        resMap.put("commission", JSONObject.toJSONString(resCommissionMap));
    }

    /**
     * 更新调单的订单信息
     * @param paraMap [查询的请求参数]
     * @return Map [查询结果MAP]
     * @throws FailureException
     * @throws TException
     */
    @Transactional(readOnly=false,isolation=Isolation.DEFAULT,
    propagation=Propagation.REQUIRED,rollbackFor=FailureException.class)
    public Map<String, String> modifyAdjustOrderInfo(Map<String, String> paraMap)
            throws FailureException, TException
    {
        log.info("modifyAdjustOrderInfo start::"+paraMap);
        Map<String, String> resMap=new HashMap<String,String>();
        Map<String, String> adjrecordMap=new HashMap<String,String>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            //若调单不通过
            if(paraMap.get("code").equals("2"))
            {
                log.error("调单操作不通过");
                resMap.put("code","1");
                resMap.put("remark","调单操作驳回");
                
                //组装更新调单记录信息MAP
                adjrecordMap.put("adjstatus", "2");
                adjrecordMap.put("adjreason", "调单操作不通过");
                adjrecordMap.put("pdate", fmt.format(new Date()));
                adjrecordMap.put("bid", paraMap.get("orderId"));
                adjrecordMap.put("sellerid", paraMap.get("sellerId"));
                //更新调单记录信息
                orderDao.modifyAdjustApply(adjrecordMap);
                return resMap;
            }
            
            //获取调单前的订单信息
            Map<String, Object> adjBeforeOrderMap=orderDao.selectBillAll(paraMap.get("orderId"));
            log.info("===adjBeforeOrderMap::"+adjBeforeOrderMap);
            
            //查询申请调单记录表
            Map<String, Object> adjApplyInfo=orderDao.queryAdjustInfo(paraMap.get("orderId"));
            //若无调单记录,则插入调单申请记录
            if(null==adjApplyInfo)
            {
                AdjustApplyBean reqBean = new AdjustApplyBean();
                reqBean.setBid(paraMap.get("orderId"));
                reqBean.setSellerid(paraMap.get("sellerId"));
                reqBean.setPhoneid(String.valueOf(adjBeforeOrderMap.get("phoneid")));
                reqBean.setHandlestatu("1");
                reqBean.setMoney(new BigDecimal(String.valueOf(adjBeforeOrderMap.get("money"))));
                reqBean.setSdate(fmt.format(new Date()));
                reqBean.setPdate(fmt.format(new Date()));
                reqBean.setUid(String.valueOf(adjBeforeOrderMap.get("uid")));
                //插入调单申请记录信息
                orderDao.addAdjustApplyInfo(reqBean);
            }
            
            //判断确认调单后的订单状态是否为退款
            String orderStatus="1,2,3,6,8,9";
            if(orderStatus.contains(String.valueOf(adjBeforeOrderMap.get("status")))==false)
            {
                log.error("订单已退款或正在退款中,不能进行调单操作");
                resMap.put("code","1");
                resMap.put("remark","订单已退款或正在退款中,不能进行调单操作");
                //组装更新调单记录信息MAP
                adjrecordMap.put("adjstatus", "2");
                adjrecordMap.put("adjreason", "订单已退款或正在退款中,不能进行调单操作");
                adjrecordMap.put("pdate", fmt.format(new Date()));
                adjrecordMap.put("bid", paraMap.get("orderId"));
                adjrecordMap.put("sellerid", paraMap.get("sellerId"));
                //更新调单记录信息
                orderDao.modifyAdjustApply(adjrecordMap);
                return resMap;
            }
            
            //若调单已通过
            adjApplyInfo=orderDao.queryAdjustInfo(paraMap.get("orderId"));
            if("3".equals(String.valueOf(adjApplyInfo.get("handlestatu"))))
            {
                log.error("该订单已调单通过，不能进行重复调单操作");
                resMap.put("code","2");
                resMap.put("remark","该订单已调单通过，不能进行重复调单操作");
                
                //组装更新调单记录信息MAP
                adjrecordMap.put("adjstatus", "2");
                adjrecordMap.put("adjreason", "该订单已调单通过，不能进行重复调单操作");
                adjrecordMap.put("pdate", fmt.format(new Date()));
                adjrecordMap.put("bid", paraMap.get("orderId"));
                adjrecordMap.put("sellerid", paraMap.get("sellerId"));
                //更新调单记录信息
                orderDao.modifyAdjustApply(adjrecordMap);
                return resMap;
            }
            
            //申请调单商户与订单消费商户是否一致
            if(String.valueOf(adjBeforeOrderMap.get("sellerid")).equals(adjApplyInfo.get("sellerid").toString()))
            {
                log.error("申请调单商户与被调单商户相同,不能进行调单操作");
                resMap.put("code","1");
                resMap.put("remark","申请调单商户与被调单商户相同,不能进行调单操作");
                
                //组装更新调单记录信息MAP
                adjrecordMap.put("adjstatus", "2");
                adjrecordMap.put("adjreason", "申请调单商户与被调单商户相同,不能进行调单操作");
                adjrecordMap.put("pdate", fmt.format(new Date()));
                adjrecordMap.put("bid", paraMap.get("orderId"));
                adjrecordMap.put("sellerid", paraMap.get("sellerId"));
                //更新调单记录信息
                orderDao.modifyAdjustApply(adjrecordMap);
                return resMap;
            }
            
            //获取商家所在行业和折扣
            Map<String,String> sellerAgioMap=orderDao.findSellerByid(Long.valueOf(paraMap.get("sellerId")));
            if(null!=sellerAgioMap)
            {
              //判断订单消费商家折扣与调单后的商家折扣是否相同
              if(Double.valueOf(String.valueOf(adjBeforeOrderMap.get("baseagio"))).doubleValue()!=Double.valueOf(String.valueOf(sellerAgioMap.get("agio"))).doubleValue())
              {
                  log.error("调单后的商家折扣与被调单的商家折扣不相同,不能进行调单操作");
                  resMap.put("code","1");
                  resMap.put("remark","调单后的商家折扣与被调单的商家折扣不相同,不能进行调单操作");
                  
                  //组装更新调单记录信息MAP
                  adjrecordMap.put("adjstatus", "2");
                  adjrecordMap.put("adjreason", "调单后的商家折扣与被调单的商家折扣不相同,不能进行调单操作");
                  adjrecordMap.put("pdate", fmt.format(new Date()));
                  adjrecordMap.put("bid", paraMap.get("orderId"));
                  adjrecordMap.put("sellerid", paraMap.get("sellerId"));
                  //更新调单记录信息
                  orderDao.modifyAdjustApply(adjrecordMap);
                  return resMap;
              }
            }
            
             //调单更新处理
             resMap = adjustOrderInfoProcess(adjBeforeOrderMap,paraMap);
        }
        catch (Exception e)
        {
            log.error("更新调单后的订单信息操作异常",e);
            throw new FailureException(ResponseState.ELSEEROR,"更新调单后的订单信息操作异常");
        }
        log.info("modifyAdjustOrderInfo end::"+resMap);
        return resMap;
    }
    
    /**
     * 调单处理
     * @param adjBeforeOrderMap [调单前的订单信息]
     * @param adjAfterParaMap [调单后的参数信息]
     * @return Map<String,String> [返回调单结果MAP]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> adjustOrderInfoProcess(Map<String, Object> adjBeforeOrderMap,Map<String, String> adjAfterParaMap) throws FailureException
    {
        Map<String, String> resMap=new HashMap<String,String>();
        try
        {
            //更新调单后的订单信息
            orderDao.modifyAdjustOrderInfo(adjAfterParaMap);
            //获取调单更新后的订单信息
            Map<String, Object> adjAfterOrderMap=orderDao.selectBillAll(adjAfterParaMap.get("orderId"));
            log.info("===adjAfterOrderMap::"+adjAfterOrderMap);
            boolean bflag=(boolean)adjAfterOrderMap.get("bfirst");
            
            //若是首单
            if(bflag==true && String.valueOf(adjAfterOrderMap.get("sellerid")).equals(String.valueOf(adjAfterOrderMap.get("genussellerid"))))
            {
                 //再次绑定向蜜客
                 adjustMikeProcess(adjAfterOrderMap);
            }
            
            //调用分账系统服务的获取分账信息
            LedgerBean ledgerBean = ledgerServiceImpl.ledgerInfoProcess(adjAfterOrderMap);
            
            //调用分账系统分配佣金接口
            Map<String, Object> adjAfterLedgerMap = ledgerServiceImpl.getLedgerMoney(ledgerBean);
            log.info("ledgerSystem commission::" + adjAfterLedgerMap);
            Map<String, String> commissionMap = new HashMap<String, String>();
            if(null!=adjAfterLedgerMap && !adjAfterLedgerMap.isEmpty())
            {
                commissionMap.put("commission", JSONObject.toJSONString(adjAfterLedgerMap));
                commissionMap.put("bid", adjAfterOrderMap.get("bid").toString());
            }
            //调用更新订单佣金方法
            orderDao.modifyCommission(commissionMap);
            
            String zdateStr=String.valueOf(adjBeforeOrderMap.get("zdate"));
            Date zdate=DateUtil.convertStringToDate("yyyy-MM-dd", zdateStr);
            String tempdate=DateUtil.dateFormatY1(zdate);
            //若不是当天才会进行修改统计表中的数据
            if(DateUtil.isCurrentTime(tempdate)==false)
            {
                //更新调单后的商户日订单统计处理
                mdySellerDayCensusImpl.modifySellerDayCensus(adjBeforeOrderMap,adjAfterOrderMap);
                
                //更新调单后的合作商日订单统计处理
                mdyJointDayCensusImpl.modifyJointDayCensus(adjBeforeOrderMap,adjAfterOrderMap);
                
                //更新调单后的合作商收益订单统计处理
                mdyJointProfitDayCensusImpl.modifyJointProfitDayCensus(adjBeforeOrderMap,adjAfterOrderMap);
            }
            
            //组装更新调单记录信息MAP
            Map<String, String> adjrecordMap=new HashMap<String,String>();
            adjrecordMap.put("adjstatus", "3");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            adjrecordMap.put("pdate", fmt.format(new Date()));
            adjrecordMap.put("bid", adjAfterParaMap.get("orderId"));
            adjrecordMap.put("sellerid", adjAfterParaMap.get("sellerId"));
            //更新调单记录信息
            orderDao.modifyAdjustApply(adjrecordMap);
            
            resMap.put("code","0");
            resMap.put("remark","调单更新操作成功");
        }
        catch (Exception e)
        {
            log.error("adjustOrderInfoProcess error::",e);
            throw new FailureException(ResponseState.ELSEEROR,"调单更新操作异常,调单不通过");
        }
        return resMap;
    }
    
    /**
     * 调单后再次绑定向蜜客处理
     * @param resOrderMap [订单信息]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void adjustMikeProcess(Map<String, Object> resOrderMap)
    {
        //组装调单后再次绑定向蜜客的MAP
        Map<String, String> paraMap=new HashMap<String,String>();
        paraMap.put("bid", resOrderMap.get("bid").toString());
        paraMap.put("genussellerid", resOrderMap.get("genussellerid").toString());
        String genusname = resOrderMap.get("genusname").toString().replaceAll("&","");
        paraMap.put("genusname", genusname);
        if (resOrderMap.containsKey("jointid")&& StringUtils.isNotBlank(String.valueOf(resOrderMap.get("jointid"))))
        {
            paraMap.put("jointid", resOrderMap.get("jointid").toString());
        }
        else
        {
            paraMap.put("jointid", null);
        }
        if (resOrderMap.containsKey("corporate")&& StringUtils.isNotBlank(String.valueOf(resOrderMap.get("corporate"))))
        {
            paraMap.put("corporate", resOrderMap.get("corporate").toString());
        }
        else
        {
            paraMap.put("corporate", null);
        }
        paraMap.put("mikeType", "2");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String attachTime = dateFormat.format(new Date());
        paraMap.put("attach_time", attachTime);
        paraMap.put("uid", resOrderMap.get("uid").toString());
        paraMap.put("addNum", "0");
        
        String mikeQueueMap = JSONObject.toJSONString(paraMap);
        
        log.info("adjustMikeRedisKey:" + mikeQueue + "==send adjustMikeRedis JSON:" + mikeQueueMap);
        try
        {
            //将调用用户中心服务的请求参数MAP放到redis队列中
            redisTemplate.opsForList().rightPush(mikeQueue, mikeQueueMap);
        }catch(RedisConnectionFailureException ex)
        {
            log.error("adjustMikeRedisConnection failure:",ex);
            redisTemplate.opsForList().rightPush(mikeQueue, mikeQueueMap);
        }
    }
 
}
