package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.xmniao.dao.profit.JointProfitCensusdMapper;
import com.xmniao.domain.profit.JointProfitCensusd;

/**
 * 合作商收益统计
 * 
 * @author wench
 *
 */
public class JointProfitQuertzService {

	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(JointProfitQuertzService.class);

	/**
	 * 注入收益DAO层
	 */
	@Autowired
	private JointProfitCensusdMapper jointProDao;

	public void jointProfitProcess()
	{
	    log.info("定时调用合作商收益日统计方法开始......");
        try
        {
            //查询当天统计的合作商日收益订单总条数
            int jointCensusCount = jointProDao.queryProfitCensus();
            log.info("已获取到合作商订单日统计数据"+jointCensusCount+"条");
            //若没有统计，则进行统计，反之，则不再进行统计
            if(jointCensusCount==0)
            {
                log.info("合作商收益统计数据开始......");
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
                
                //查询合作商和消费合作商
                List<Map<String,Integer>> resList=jointProDao.queryJointAndConsumJoint(reqMap);
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

                    //查询商户和合作商信息
                    List<JointProfitCensusd> resBeanList=querySellerAndJointInfo(jointid,orderDate);
                    for(JointProfitCensusd reqBean : resBeanList)
                    {
                        //合作商分账收益总额
                        jointLedgerProfit(reqBean);
                        
                        //合作商未到账收益总额
                        jointNotLedgerProfit(reqBean);
                        
                        //已到账收益总额
                        jointHasLedgerProfit(reqBean);
                        
                        //渠道手续费收益总额
                        jointChannelFeesProfit(reqBean);
                        
                        //商户流水总额
                        sellerWaterTotalProcess(reqBean);
                        
                        //商户佣金总额
                        sellerCommisionProcess(reqBean);
                        //数据不全为空才插入
                        if(compareEqualZero(reqBean.getLedgerProfit())
                        && compareEqualZero(reqBean.getNotProfit())
                        && compareEqualZero(reqBean.getHasProfit())
                        && compareEqualZero(reqBean.getPoundage())
                        && compareEqualZero(reqBean.getWaterTotal())
                        && compareEqualZero(reqBean.getCommision())
                        ){
                        }else{
	                        //插入合作商收益表
	                        jointProDao.insertJointProfitCensusd(reqBean);
                        }
                        //查询合作商收益全都为0的记录
                        //int resCount=jointProDao.queryJointProfitZero(reqBean);
                        
                        //if(resCount>=1)
                        //{
                        //    //删掉合作商收益全都为0的记录
                        //    jointProDao.deleteJointProfitZero(reqBean);
                        //}
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("合作商收益日统计出现异常",e);
        }
        log.info("定时调用合作商收益日统计方法结束......");
	}
	
	private boolean compareEqualZero(BigDecimal data){
		return data.compareTo(BigDecimal.ZERO)==0;
	}
	
	/**
	 * 商户佣金总额
	 * @param reqBean [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
    private void sellerCommisionProcess(JointProfitCensusd reqBean)
    {
        BigDecimal commisionTotal=BigDecimal.ZERO;
        
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("zdate", String.valueOf(reqBean.getOrderDate()));
        reqMap.put("jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("genussellerid", String.valueOf(reqBean.getSellerid()));
        
        List<String> resCommission=jointProDao.querySellerCommision(reqMap);
        for(String commission : resCommission)
        {
            if(StringUtils.isNotBlank(commission))
            {
                JSONObject resJson = JSONObject.parseObject(commission);
                
                BigDecimal mikeAmount=resJson.getBigDecimal("mike_amount").add(commisionTotal);
                
                commisionTotal=mikeAmount;
                
                reqBean.setCommision(commisionTotal);
            }
        }
    }

    /**
     * 商户流水总额
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void sellerWaterTotalProcess(JointProfitCensusd reqBean)
    {
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("zdate", String.valueOf(reqBean.getOrderDate()));
        reqMap.put("consume_jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("sellerid", String.valueOf(reqBean.getSellerid()));
        
        BigDecimal waterTotal=jointProDao.querySellerWaterTotal(reqMap);
        if(null!=waterTotal)
        {
            reqBean.setWaterTotal(waterTotal);
        }
    }

    /**
     * 渠道手续费收益总额
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void jointChannelFeesProfit(JointProfitCensusd reqBean)
    {
        BigDecimal channnelFeesTotal=BigDecimal.ZERO;
        
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("consume_jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("sellerid", String.valueOf(reqBean.getSellerid()));
        reqMap.put("zdate", String.valueOf(reqBean.getOrderDate()));
        
        List<Map<String,String>> resMapList = jointProDao.queryJointChannelFeesProfit(reqMap);
        for(Map<String,String> resMap : resMapList)
        {
            if(StringUtils.isNotBlank(resMap.get("commission")))
            {
                JSONObject resJson = JSONObject.parseObject(resMap.get("commission"));

                if(resMap.containsKey("jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("jointid"))))
                {
                    int jointid=Integer.valueOf(String.valueOf(resMap.get("jointid"))).intValue();
                    if(jointid==reqBean.getJointid().intValue())
                    {
                        channnelFeesTotal=resJson.getBigDecimal("bpartner_fees").add(channnelFeesTotal);
                    }
                }
                
                if(resMap.containsKey("consume_jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("consume_jointid"))))
                {
                    int consume_jointid=Integer.valueOf(String.valueOf(resMap.get("consume_jointid"))).intValue();
                    if(consume_jointid==reqBean.getJointid().intValue())
                    {
                        channnelFeesTotal=resJson.getBigDecimal("cpartner_fees").add(channnelFeesTotal);
                    }
                }
                reqBean.setPoundage(channnelFeesTotal);
            }
        }
    }

    /**
     * 已到账收益总额
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void jointHasLedgerProfit(JointProfitCensusd reqBean)
    {
        BigDecimal hasLedgerProfitTotal=BigDecimal.ZERO;
        
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("consume_jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("sellerid", String.valueOf(reqBean.getSellerid()));
        reqMap.put("fdate", String.valueOf(reqBean.getOrderDate()));
        
        List<Map<String,String>> resMapList = jointProDao.queryJointHasLedgerProfit(reqMap);
        for(Map<String,String> resMap : resMapList)
        {
            if(StringUtils.isNotBlank(resMap.get("commission")))
            {
                JSONObject resJson = JSONObject.parseObject(resMap.get("commission"));
                
                BigDecimal bpartnerMoney=resJson.getBigDecimal("bpartner_amount");
                BigDecimal cpartnerMoney=resJson.getBigDecimal("cpartner_amount");
                
                if(resMap.containsKey("jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("jointid"))))
                {
                    int jointid=Integer.valueOf(String.valueOf(resMap.get("jointid"))).intValue();
                    if(jointid==reqBean.getJointid().intValue())
                    {
                        hasLedgerProfitTotal=bpartnerMoney.add(hasLedgerProfitTotal);
                    }
                }
                if(resMap.containsKey("consume_jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("consume_jointid"))))
                {
                    int consume_jointid=Integer.valueOf(String.valueOf(resMap.get("consume_jointid"))).intValue();
                    if(consume_jointid==reqBean.getJointid().intValue())
                    {
                        hasLedgerProfitTotal=cpartnerMoney.add(hasLedgerProfitTotal);
                    }
                }
                reqBean.setHasProfit(hasLedgerProfitTotal);
            }
        }
    }
    
    /**
     * 合作商未到账收益总额
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void jointNotLedgerProfit(JointProfitCensusd reqBean)
    {
        BigDecimal ledgerProfitTotal=BigDecimal.ZERO;
        
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("consume_jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("sellerid", String.valueOf(reqBean.getSellerid()));
        reqMap.put("zdate", String.valueOf(reqBean.getOrderDate()));
        
        List<Map<String,String>> resMapList = jointProDao.queryJointNotLedgerProfit(reqMap);
        for(Map<String,String> resMap : resMapList)
        {
            if(StringUtils.isNotBlank(resMap.get("commission")))
            {
                JSONObject resJson = JSONObject.parseObject(resMap.get("commission"));
                
                BigDecimal bpartnerMoney=resJson.getBigDecimal("bpartner_amount");
                BigDecimal cpartnerMoney=resJson.getBigDecimal("cpartner_amount");
                
                if(resMap.containsKey("jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("jointid"))))
                {
                    int jointid=Integer.valueOf(String.valueOf(resMap.get("jointid"))).intValue();
                    if(jointid==reqBean.getJointid().intValue())
                    {
                        ledgerProfitTotal=bpartnerMoney.add(ledgerProfitTotal);
                    }
                }
                if(resMap.containsKey("consume_jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("consume_jointid"))))
                {
                    int consume_jointid=Integer.valueOf(String.valueOf(resMap.get("consume_jointid"))).intValue();
                    if(consume_jointid==reqBean.getJointid().intValue())
                    {
                        ledgerProfitTotal=cpartnerMoney.add(ledgerProfitTotal);
                    }
                }
                reqBean.setNotProfit(ledgerProfitTotal);
            }
        }
    }

    /**
     * 查询商户和合作商信息
     * @param jointid [合作商ID]
     * @param orderTime [订单日期]
     * @return List<JointProfitCensusd> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private List<JointProfitCensusd> querySellerAndJointInfo(String jointid,String orderTime)
	{
	    List<JointProfitCensusd> resList = new ArrayList<JointProfitCensusd>();
	    List<JointProfitCensusd> resBeanList = jointProDao.querySellerAndJointInfo(jointid);
	    for(JointProfitCensusd resBean : resBeanList)
	    {
	        JointProfitCensusd reBean = new JointProfitCensusd();
	        reBean.setSellerid(resBean.getSellerid());
	        reBean.setSellername(resBean.getSellername());
	        reBean.setJointid(resBean.getJointid());
	        if(StringUtils.isNotBlank(resBean.getCorporate()))
	        {
	            reBean.setCorporate(resBean.getCorporate());
	        }
	        reBean.setOrderDate(orderTime);
            SimpleDateFormat censusTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            reBean.setCensusTime(censusTime.format(new Date()));
	        resList.add(reBean);
	    }
	    return resList;
	}
	
    /**
     * 合作商分账收益总额
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
	private void jointLedgerProfit(JointProfitCensusd reqBean)
	{
	    BigDecimal ledgerProfitTotal=BigDecimal.ZERO;
	    
	    Map<String,String> reqMap = new HashMap<String,String>();
	    reqMap.put("jointid", String.valueOf(reqBean.getJointid()));
	    reqMap.put("consume_jointid", String.valueOf(reqBean.getJointid()));
	    reqMap.put("sellerid", String.valueOf(reqBean.getSellerid()));
	    reqMap.put("zdate", String.valueOf(reqBean.getOrderDate()));
	    
	    List<Map<String,String>> resMapList = jointProDao.queryJointLedgerProfit(reqMap);
	    for(Map<String,String> resMap : resMapList)
	    {
	        if(StringUtils.isNotBlank(resMap.get("commission")))
	        {
	            JSONObject resJson = JSONObject.parseObject(resMap.get("commission"));
	            
	            BigDecimal bpartnerMoney=resJson.getBigDecimal("bpartner_amount");
	            BigDecimal cpartnerMoney=resJson.getBigDecimal("cpartner_amount");
	            
	            if(resMap.containsKey("jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("jointid"))))
	            {
	                int jointid=Integer.valueOf(String.valueOf(resMap.get("jointid"))).intValue();
	                if(jointid==reqBean.getJointid().intValue())
	                {
	                    ledgerProfitTotal=bpartnerMoney.add(ledgerProfitTotal);
	                }
	            }
	            if(resMap.containsKey("consume_jointid") && StringUtils.isNotBlank(String.valueOf(resMap.get("consume_jointid"))))
                {
	                int consume_jointid=Integer.valueOf(String.valueOf(resMap.get("consume_jointid"))).intValue();
	                if(consume_jointid==reqBean.getJointid().intValue())
	                {
	                    ledgerProfitTotal=cpartnerMoney.add(ledgerProfitTotal);
	                }
                }
	            reqBean.setLedgerProfit(ledgerProfitTotal);
	        }
	    }
	}
}
