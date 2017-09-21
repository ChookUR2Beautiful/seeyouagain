package com.xmniao.service.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.order.SellerDayCensusBean;

/**
 * 调单后修改商户订单统计实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年7月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ModifySellerDayCensusImpl
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(ModifySellerDayCensusImpl.class);
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 调单后修改商户订单统计
     * @param adjBeforeOrderMap [调单前的订单信息]
     * @param adjAfterOrderMap [调单后的订单信息]
     * @param adjAfterLedgerMap [调单后的]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void modifySellerDayCensus(Map<String, Object> adjBeforeOrderMap,Map<String, Object> adjAfterOrderMap)
    {
        log.info("modifySellerDayCensus start");
        try
        {
            Set<String> orderDateSet = new HashSet<String>();
            SimpleDateFormat mft = new SimpleDateFormat("yyyy-MM-dd");
            if(adjBeforeOrderMap.containsKey("zdate") && null != adjBeforeOrderMap.get("zdate"))
            {
                orderDateSet.add(mft.format(mft.parse(String.valueOf(adjBeforeOrderMap.get("zdate")))));
            }
            if(adjBeforeOrderMap.containsKey("fdate") && null != adjBeforeOrderMap.get("fdate"))
            {
                orderDateSet.add(mft.format(mft.parse(String.valueOf(adjBeforeOrderMap.get("fdate")))));
            }
            Iterator<String> orderDateIterator = orderDateSet.iterator();
            while (orderDateIterator.hasNext())
            {
                //订单日期
                String orderdate = orderDateIterator.next();
                Map<String, Object> sellerCensusMap = new HashMap<String, Object>();
                Set<String> beforeSellerSet = new HashSet<String>();
                //调单前的消费商家
                String beforeSellerid = String.valueOf(adjBeforeOrderMap.get("sellerid"));
                //调单前的所属商家
                String beforeGenussellerid = String.valueOf(adjBeforeOrderMap.get("genussellerid"));
                //ADD消费商户和所属商户
                beforeSellerSet.add(beforeSellerid);
                beforeSellerSet.add(beforeGenussellerid);
                Iterator<String> beforeIterator = beforeSellerSet.iterator();
                while (beforeIterator.hasNext())
                {
                    String sellerid = beforeIterator.next();
                    //根据商户ID和订单日期获取调单前的商户订单统计信息
                    sellerCensusMap = orderDao.querySellerCensusBySellerAndDate(sellerid,orderdate);
                    if (null != sellerCensusMap)
                    {
                        //更新商户订单统计处理
                        SellerDayCensusBean reqBean = censusProcess(sellerid,orderdate);
                        orderDao.modifySellerDayCensus(reqBean);
                    }
                }
                
                    
                Set<String> afterSellerSet = new HashSet<String>(); 
                //调单后的消费商家
                String afterSellerid = String.valueOf(adjAfterOrderMap.get("sellerid"));
                //调单后的所属商家
                String afterGenussellerid = String.valueOf(adjAfterOrderMap.get("genussellerid"));
                //ADD消费商户和所属商户
                afterSellerSet.add(afterSellerid);
                afterSellerSet.add(afterGenussellerid);
                Iterator<String> afterIterator = afterSellerSet.iterator();
                while (afterIterator.hasNext())
                {
                    String sellerid = afterIterator.next();
                    mft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //获取调单前的统计时间
                    String censusTime = null;
                    if(sellerCensusMap !=  null &&  sellerCensusMap.get("census_time") != null){
                    	censusTime = mft.format(mft.parse(String.valueOf(sellerCensusMap.get("census_time"))));
                    }
                    //根据商户ID和订单日期获取调单后的商户订单统计信息
                    sellerCensusMap = orderDao.querySellerCensusBySellerAndDate(sellerid,orderdate);
                    //若没有获取到统计信息,则进行插入
                    if (null != sellerCensusMap)
                    {
                        //更新商户订单统计处理
                        SellerDayCensusBean reqBean = censusProcess(sellerid,orderdate);
                        orderDao.modifySellerDayCensus(reqBean);
                    }
                    else
                    {
                        SellerDayCensusBean reqBean = censusProcess(sellerid,orderdate);
                        //SET调单前的统计时间
                        reqBean.setCensusTime(censusTime == null ? orderdate : censusTime);
                        //插入调单后的商户订单统计信息
                        orderDao.insertSellerDayCensus(reqBean);
                    }
                }
                    
                

            }
        }
        catch (Exception e)
        {
            log.error("modifySellerDayCensus error::", e);
        }
        log.info("modifySellerDayCensus end");
    }
    
    /**
     * 更新商户订单统计处理
     * @param sellerid [商户ID]
     * @param orderdate [订单日期]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private SellerDayCensusBean censusProcess(String sellerid,String orderdate)
    {
            SellerDayCensusBean resBean = querySellerAndJointInfo(sellerid,orderdate);
            Map<String,String> reMap = new HashMap<String,String>();
            reMap.put("sellerid", sellerid);
            reMap.put("orderDate", orderdate);
            
            //获取商户日流水总额和日返利总额
            Map<String, BigDecimal> resMap  = orderDao.querySellerDayCensus(reMap);
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
            //获取已支付的订单总数
            int orderTotal = orderDao.queryOrderTotalInfo(reMap);
            resBean.setOrderTotal(orderTotal);
            
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
            //获取商户的日营业收入和日佣金总额
            incomeAndCommisionProcess(resBean,reMap);
            return resBean;
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
            BigDecimal initSellerAmount=BigDecimal.ZERO;
            //调用获取消费商家的营业收入方法
            List<String> resIncome = orderDao.selectOrderIncome(reqMap);
            for (String income : resIncome)
            {
                //对订单佣金做非空判断,以免乱数据造成空指针异常
                if (StringUtils.isNotBlank(income))
                {
                    JSONObject resJson = JSONObject.parseObject(income);
                    //判断JSON字符串儿中是否存在seller_amount,以免乱数据造成空指针异常
                    if (resJson.containsKey("seller_amount") && StringUtils.isNotBlank(resJson.getString("seller_amount")))
                    {
                        //将消费商家的营业收入进行相加
                        BigDecimal sellerAmount = resJson.getBigDecimal("seller_amount").add(initSellerAmount);
                        initSellerAmount=sellerAmount;
                        reqBean.setIncome(initSellerAmount);
                    }
                }
            }
            
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
