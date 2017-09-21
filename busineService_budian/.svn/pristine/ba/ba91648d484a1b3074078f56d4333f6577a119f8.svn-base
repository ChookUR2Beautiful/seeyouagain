package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.order.SellerDayCensusBean;

/**
 * 定时处理当天订单数据实现类
 * @author  LiBingBing
 * @version  [版本号, 2014年12月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OrderQuertzService
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(OrderQuertzService.class);
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 定时扫描调用商户订单日统计方法
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * 修改  xiaoxiong 2016年6月22日 20:48:29
     * 加入积分订单统计 
     */
    public void addOrderInfo()
    {
        log.info("定时调用商户订单日统计方法开始......");
        long startTime = System.currentTimeMillis();
        try
        {
            SimpleDateFormat csdf = new SimpleDateFormat("yyyy-MM-dd");
            String censusTime = csdf.format(new Date());
            //查询当天统计的商户订单总条数
            int sellerCensusCount = orderDao.querySellerStatistics(censusTime);
            log.info("已获取到商户订单日统计数据"+sellerCensusCount+"条");
            //若没有统计，则进行统计，反之，则不再进行统计
            if (sellerCensusCount == 0)
            {
                log.info("商户日订单统计数据开始......");
                Date nDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nDate);
                //前一天日期
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                Date bfDate = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //订单日期
                String orderDate = sdf.format(bfDate);
                Set<String> reSetList = new HashSet<String>();
                Map<String,String> reqMap = new HashMap<String,String>();
                reqMap.put("zdate", orderDate);
                reqMap.put("fdate", orderDate);
                //查询前一天所有已支付过的商户
                List<Map<String,Integer>> sellerMap = orderDao.querySellerAndGenuseller(reqMap);
                
                //查询前一天所有已支付过的积分订单
                
                for(Map<String,Integer> reSellerMap : sellerMap){

                	//积分订单没有sellerid排除
                	if(!String.valueOf(reSellerMap.get("sellerid")).equals("0"))
                	reSetList.add(String.valueOf(reSellerMap.get("sellerid")));
                	reSetList.add(String.valueOf(reSellerMap.get("genussellerid")));
                }
                //查询前一天积分已支付订单
                
                log.info("已获取到昨日已支付的商户"+reSetList.size()+"条");
                Iterator<String> iterator = reSetList.iterator();
                while (iterator.hasNext())
                {
                        String sellerid = iterator.next();
                        //根据商家ID查询商户信息和合作商信息
                        SellerDayCensusBean resBean = querySellerAndJointInfo(sellerid,orderDate);
                        if(StringUtils.isNotBlank(resBean.getSellerId()))
                        {
                            Map<String,String> reMap = new HashMap<String,String>();
                            reMap.put("sellerid", sellerid);
                            reMap.put("orderDate", orderDate);
                            
                            
                            //获取商户日流水总额和日返利总额
                            Map<String, BigDecimal> resMap = orderDao.querySellerDayCensus(reMap);
                            if(null!=resMap)
                            {
                                if(resMap.containsKey("waterTotal") && null!=resMap.get("waterTotal"))
                                {
                                    resBean.setWaterTotal(resMap.get("waterTotal"));
                                }
                                if(resMap.containsKey("rebateTotal") && null!=resMap.get("rebateTotal"))
                                {
                                    resBean.setRebateTotal(resMap.get("rebateTotal"));
                                }
                            }
                            //获取积分订单日流水总额 
                            BigDecimal waterTotal=orderDao.queryBargainMount(reMap);
                            if(waterTotal==null)waterTotal=BigDecimal.ZERO;
                            resBean.setWaterTotal(resBean.getWaterTotal().add(waterTotal));//加上积分流水
                            
                            //获取已支付的订单总数
                            int orderTotal = orderDao.queryOrderTotalInfo(reMap);
                            //获取积分订单总数
                            int bargainTotal=orderDao.queryBargainCount(reMap);
                            resBean.setOrderTotal(orderTotal+bargainTotal);
                            //获取商户的日分账总额和日分账订单数
                            Map<String, Object> reMaps = orderDao.queryYLedgerInfo(reMap);
                            if(null!=reMaps)
                            {
                                if(reMaps.containsKey("yledger") && null!=reMaps.get("yledger"))
                                {
                                    resBean.setYledger(Integer.valueOf(reMaps.get("yledger").toString()));
                                }
                                if(reMaps.containsKey("ledgerTotal") && null!=reMaps.get("ledgerTotal"))
                                {
                                    BigDecimal ledgerTotal = BigDecimal.valueOf(Double.valueOf(reMaps.get("ledgerTotal").toString()));
                                    resBean.setLedgerTotal(ledgerTotal);
                                }
                            }
                             
                            //获取商户的未分账订单数
                            int wledger = orderDao.queryWLedgerInfo(reMap);
                            resBean.setWledger(wledger);
                            //获取商户会员店外消费总额
                            BigDecimal sellerShopTotal = orderDao.querySellerShopTotal(reMap);
                            if(null!=sellerShopTotal)
                            {
                                resBean.setShopTotal(sellerShopTotal);
                            }
                            //获取商户的店外收益
                            getSellerMoney(resBean,reMap);
                            //获取商户的日佣金总额
                            incomeAndCommisionProcess(resBean,reMap);
                            SimpleDateFormat censusTimefmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            resBean.setCensusTime(censusTimefmt.format(new Date()));
                           
                            //获取商户的日营收
                            BigDecimal sellercome=orderDao.queryIncome(reMap);
                            if(sellercome==null){sellercome=BigDecimal.ZERO;}
                            
                            //查询积分订单总营收（总结算价）
                        	BigDecimal income=orderDao.selectBargainIncome(reMap);
                        	if(income==null)income=BigDecimal.ZERO;
                        	//订单总营收加上积分总营收
                        	resBean.setIncome(sellercome.add(income));
                            
                            //插入商户日订单统计表
                            orderDao.insertSellerDayCensus(resBean);
                            
                            //查询插入的商户日订单统计表中全都为0的数据记录数
                            int zeroCount=orderDao.querySellerDayCensusZero(resBean);
                            if(zeroCount>=1)
                            {
                                //删除全都为0的数据
                                orderDao.delSellerDayCensusZero(resBean);
                            }
                            
                            log.info("商户日订单统计信息："+resBean.toString());
                        }
                        
                }
            }
        }catch (Exception e)
        {
            log.error("定时调用商户订单日统计异常", e);
        }
        long endTime = System.currentTimeMillis();
        log.info("商户日订单统计总耗时:"+(endTime-startTime)+"ms");
        log.info("商户日订单统计结束......");
    }
    
    
    /**
     * 获取商户店外收益
     * @param resBean
     * @param reMap
     */
    private void getSellerMoney(SellerDayCensusBean resBean, Map<String, String> reMap) {
    	
    	BigDecimal initSellerMoney=BigDecimal.ZERO;
    	//查询所属商家订单的店外收益
    	List<String> sellerMoneyList = orderDao.querySellerMoney(reMap);
    	for (String sellerMoney:sellerMoneyList){
    		if(StringUtils.isNotBlank(sellerMoney)){
    			JSONObject jsonObj = JSONObject.parseObject(sellerMoney);
    			if(jsonObj.containsKey("seller_money")&&StringUtils.isNotBlank(jsonObj.getString("seller_money"))){
    				initSellerMoney=jsonObj.getBigDecimal("seller_money").add(initSellerMoney);
    			}
    		}
    	}
    	
    	//查询所属商家爆品订单店外收益
    	List<String> bSellerMoneyList = orderDao.queryBSellerMoney(reMap);
    	for(String bSellerMoney:bSellerMoneyList){
    		if(StringUtils.isNotBlank(bSellerMoney)){
    			JSONObject jsonObject = JSONObject.parseObject(bSellerMoney);
    			if(jsonObject.containsKey("bSellerMoney")&&StringUtils.isNotBlank(jsonObject.getString("bSellerMoney"))){
    				initSellerMoney=jsonObject.getBigDecimal("bSellerMoney").add(initSellerMoney);
    			}
    		}
    	}
    	//查询积分订单的店外收益
    	List<String> fSellerMoneyList=orderDao.queryfreshSellerMoney(reMap);
    	for(String fSellerMoney:fSellerMoneyList){
    		if(StringUtils.isNotBlank(fSellerMoney)){
    			JSONObject jsonObject = JSONObject.parseObject(fSellerMoney);
    			if(jsonObject.containsKey("bSellerMoney")&&StringUtils.isNotBlank(jsonObject.getString("bSellerMoney"))){
    				initSellerMoney=jsonObject.getBigDecimal("bSellerMoney").add(initSellerMoney);
    			}
    		}
    	}
    	
    	resBean.setSellerMoney(initSellerMoney);
    	
	}

	/**
     * 获取商户和合作商信息
     * @param sellerid [商户ID]
     * @param orderDate [订单日期]
     * @return SellerDayCensusBean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private SellerDayCensusBean querySellerAndJointInfo(String sellerid,String orderDate)
    {
        SellerDayCensusBean reqBean = new SellerDayCensusBean();
        try
        {
            //获取商户信息
            Map<String, Object> resMap = orderDao.querySellerInfos(sellerid);
            if(null!=resMap)
            {
                if(resMap.containsKey("sellerid") && StringUtils.isNotBlank(String.valueOf(resMap.get("sellerid"))))
                {
                    reqBean.setSellerId(String.valueOf(resMap.get("sellerid")));
                }
                if(resMap.containsKey("sellername") && StringUtils.isNotBlank(String.valueOf(resMap.get("sellername"))))
                {
                    reqBean.setSellerName(resMap.get("sellername").toString());
                }
                if (resMap.containsKey("jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("jointid"))))
                {
                    reqBean.setJointId(String.valueOf(resMap.get("jointid")));
                    //获取合作商信息
                    String jointMap = orderDao.queryJointInfos(reqBean.getJointId());
                    if(StringUtils.isNotBlank(jointMap))
                    {
                        reqBean.setCorporate(jointMap);
                    }
                }
                reqBean.setOrderDate(orderDate);
            }
        }
        catch (Exception e)
        {
            log.error("querySellerAndJointInfo error::", e);
        }
        return reqBean;
    }
    
    /**
     * 统计佣金总金额和营业收入方法
     * @param resLedgerList [分账LIST集合]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void incomeAndCommisionProcess(SellerDayCensusBean reqBean,Map<String,String> reqMap)
    {
        try
        {
//            BigDecimal initSellerAmount=BigDecimal.ZERO;
//            //调用获取消费商家的营业收入方法
//            List<String> resIncome = orderDao.selectOrderIncome(reqMap);
//            for (String income : resIncome)
//            {
//                //对订单佣金做非空判断,以免乱数据造成空指针异常
//                if (StringUtils.isNotBlank(income))
//                {
//                    JSONObject resJson = JSONObject.parseObject(income);
//                    //判断JSON字符串儿中是否存在seller_amount,以免乱数据造成空指针异常
//                    if (resJson.containsKey("seller_amount") && StringUtils.isNotBlank(resJson.getString("seller_amount")))
//                    {
//                        //将消费商家的营业收入进行相加
//                        BigDecimal sellerAmount = resJson.getBigDecimal("seller_amount").add(initSellerAmount);
//                        initSellerAmount=sellerAmount;
//                        reqBean.setIncome(initSellerAmount);
//                    }
//                }
//            }
//            
//            try {
//            	//查询积分订单总营收（总结算价）
//            	BigDecimal income=orderDao.selectBargainIncome(reqBean.getSellerId());
//            	if(income==null)income=BigDecimal.ZERO;
//            	//订单总营收加上积分总营收
//            	reqBean.setIncome(initSellerAmount.add(income));
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
            
        	   BigDecimal initMikeAmount=BigDecimal.ZERO;
               //调用获取所属商家的订单佣金方法
               List<String> resCommission = orderDao.selectOrderCommission(reqMap);
               for (String commission : resCommission)
               {
                   //对订单佣金做非空判断,以免乱数据造成空指针异常
                   if (StringUtils.isNotBlank(commission))
                   {
                       JSONObject resJson = JSONObject.parseObject(commission);
                       //判断JSON字符串儿中是否存在mike_amount,以免乱数据造成空指针异常
                       if (resJson.containsKey("mike_amount") && StringUtils.isNotBlank(resJson.getString("mike_amount")))
                       {
                           //将所属商家的向蜜客佣金进行相加
                           BigDecimal mikeAmount = resJson.getBigDecimal("mike_amount").add(initMikeAmount);
                           initMikeAmount=mikeAmount;
                           reqBean.setCommision(initMikeAmount);
                       }
                   }
               }
        }
        catch (Exception e)
        {
            log.error("insertCommisionAndIncome error::", e);
        }
    }
}