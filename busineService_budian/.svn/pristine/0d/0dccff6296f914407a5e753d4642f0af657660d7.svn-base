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

import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.order.JointDayCensusBean;

/**
 * 合作商定时日统计业务处理类
 * @author  LiBingBing
 * @version  [版本号, 2014年12月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PartnerQuertzService
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(PartnerQuertzService.class);
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 合作商定时日统计订单方法
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void addPartnerInfo()
    {
        log.info("合作商日统计业务处理类开始......");
        try
        {
            SimpleDateFormat csdf = new SimpleDateFormat("yyyy-MM-dd");
            String censusTime = csdf.format(new Date());
            //先检查合作商订单日统计是否已经统计过
            int queryCount = orderDao.queryJointDayCensus(censusTime);
            log.info("已获取到合作商订单日统计数据" + queryCount + "条");
            //若查询结果中没有统计过，则进行统计，反之，不再进行统计
            if (queryCount == 0)
            {
                log.info("合作商订单日统计数据开始......");
                
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
                
                //查询订单表中的合作商和消费合作商,并放到Set集合中
                List<Map<String,Integer>> resList=orderDao.queryJointAndConsumJoint(reqMap);
                for(Map<String,Integer> reMap : resList)
                {
                    if(reMap.containsKey("jointid") && StringUtils.isNotBlank(String.valueOf(reMap.get("jointid"))))
                    {
                        reSetList.add(String.valueOf(reMap.get("jointid")));
                    }
                    if(reMap.containsKey("consume_jointid") && StringUtils.isNotBlank(String.valueOf(reMap.get("consume_jointid"))))
                    {
                        reSetList.add(String.valueOf(reMap.get("consume_jointid")));
                    }
                }
                log.info("已获取到昨日已支付的合作商"+reSetList.size()+"条");
                Iterator<String> iterator = reSetList.iterator();
                while (iterator.hasNext())
                {
                    String jointid = iterator.next();
                    
                    //合作商信息处理
                    JointDayCensusBean resBean=queryJointInfoProcess(jointid,orderDate);
                    
                    //合作商日收益处理
                    jointProfitTotalProcess(resBean);
                    
                    //合作商日收益到账处理
                    jointAccountTotalProcess(resBean);
                    
                    if(resBean.getProfitTotal().doubleValue()!=0 || resBean.getAccountTotal().doubleValue()!=0)
                    {
                        //插入合作商日订单统计表
                        orderDao.insertJointDayCensus(resBean);
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("合作商定时日统计业务处理类异常", e);
        }
        log.info("合作商定时日统计业务处理类结束......");
    }
    

    /**
     * 合作商信息处理
     * @param jointid [合作商ID]
     * @param orderTime [订单日期]
     * @return JointDayCensusBean [JAVABean实体类]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public JointDayCensusBean queryJointInfoProcess(String jointid,String orderTime)
    {
        JointDayCensusBean resBean = new JointDayCensusBean();
        resBean.setJointId(jointid);
        String resCorporate = orderDao.queryJointInfos(jointid);
        if(StringUtils.isNotBlank(resCorporate))
        {
            resBean.setCorporate(resCorporate);
        }
        resBean.setOrderDate(orderTime);
        SimpleDateFormat censusTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resBean.setCensusTime(censusTimeFormat.format(new Date()));
        return resBean;
    }
    
    /**
     * 合作商日收益到账处理
     * @param resBean [JAVABean实体类]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void jointAccountTotalProcess(JointDayCensusBean resBean)
    {
        BigDecimal accountTotal = new BigDecimal(0.00);
        
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("jointid", resBean.getJointId());
        reqMap.put("consume_jointid", resBean.getJointId());
        reqMap.put("fdate", resBean.getOrderDate());
        
        List<Map<String,Object>> resAccountTotalList=orderDao.queryJointAccountTotal(reqMap);
        for(Map<String,Object> resAccountMap : resAccountTotalList)
        {
            if(StringUtils.isNotBlank(String.valueOf(resAccountMap.get("commission"))))
            {
                JSONObject resJson = JSONObject.parseObject(String.valueOf(resAccountMap.get("commission")));
                BigDecimal bpartnerAmount=resJson.getBigDecimal("bpartner_amount").add(resJson.getBigDecimal("memberJointMoney"));
                BigDecimal cpartnerAmount=resJson.getBigDecimal("cpartner_amount").add(resJson.getBigDecimal("consumeJointidMoney"));
                
                if(String.valueOf(resAccountMap.get("jointid")).equals(resBean.getJointId()))
                {
                    accountTotal=bpartnerAmount.add(accountTotal);
                }
                
                if(String.valueOf(resAccountMap.get("consume_jointid")).equals(resBean.getJointId()))
                {
                    accountTotal=cpartnerAmount.add(accountTotal);
                }
            }
        }
        resBean.setAccountTotal(accountTotal);
    }

    /**
     * 合作商日收益处理
     * @param resBean [JAVABean实体类]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void jointProfitTotalProcess(JointDayCensusBean resBean)
    {
        BigDecimal profitTotal = new BigDecimal(0.00);
        
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("jointid", resBean.getJointId());
        reqMap.put("consume_jointid", resBean.getJointId());
        reqMap.put("zdate", resBean.getOrderDate());
        
        List<Map<String,Object>> resProfitList=orderDao.queryJointProfit(reqMap);
        for(Map<String,Object> resMap : resProfitList)
        {
            if(StringUtils.isNotBlank(String.valueOf(resMap.get("commission"))))
            {
                JSONObject resJson = JSONObject.parseObject(String.valueOf(resMap.get("commission")));
                BigDecimal bpartnerAmount=resJson.getBigDecimal("bpartner_amount").add(resJson.getBigDecimal("memberJointMoney"));
                BigDecimal cpartnerAmount=resJson.getBigDecimal("cpartner_amount").add(resJson.getBigDecimal("consumeJointidMoney"));
                
                if(String.valueOf(resMap.get("jointid")).equals(resBean.getJointId()))
                {
                    profitTotal=bpartnerAmount.add(profitTotal);
                }
                
                if(String.valueOf(resMap.get("consume_jointid")).equals(resBean.getJointId()))
                {
                    profitTotal=cpartnerAmount.add(profitTotal);
                }
            }
        }
        resBean.setProfitTotal(profitTotal);
    }
}
