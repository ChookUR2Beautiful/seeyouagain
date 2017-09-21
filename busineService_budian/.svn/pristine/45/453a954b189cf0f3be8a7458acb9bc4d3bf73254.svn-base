package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.profit.JointProfitCensusdMapper2;
import com.xmniao.domain.profit.JointProfitCensusd;

/**
 * 合作商收益统计
 * 
 * @author wench
 *
 */

public class JointProfitQuertzService2 {

	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(JointProfitQuertzService2.class);

	/**
	 * 注入收益DAO层
	 */
	@Autowired
	private JointProfitCensusdMapper2 jointProDao;

	public void jointProfitProcess()
	{
	    log.info("定时调用合作商收益日统计方法开始......");
	    
	    long enterTime = System.currentTimeMillis();
	    
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
                
                Map<String,String> reqMap = new HashMap<String,String>();
                reqMap.put("zdate", orderDate);//orderDate
                reqMap.put("fdate", orderDate);
                
                //查询合作商和消费合作商在该日订单中存在
                List<Map<String,Integer>> resList = jointProDao.queryJointAndConsumJoint(reqMap);
            
                log.info("已获取到昨日已支付的合作商"+resList.size()+"条");
                if(null!=resList&&resList.size()>0){
                	for(Map<String,Integer> joint : resList){
                    	
                    	log.info("joint:"+joint.get("consume_jointid"));
                    	if(Integer.parseInt(joint.get("consume_jointid")+"") !=0){
                    		long querySellerAndJointInfoTime = System.currentTimeMillis();
                            //查询商户和合作商信息
                            List<JointProfitCensusd> resBeanList=querySellerAndJointInfo(String.valueOf(joint.get("consume_jointid")),orderDate);
                            log.info("querySellerAndJointInfo time:"+(System.currentTimeMillis()-querySellerAndJointInfoTime));
                            for(JointProfitCensusd reqBean : resBeanList)//1500
                            {
                            	               	                                                                                                                                
                            	/**合作商分账收益总额**/
                            	BigDecimal ledgerProfitTotal=BigDecimal.ZERO;
                    	        /**合作商未到账收益总额**/
                            	BigDecimal ledgerNotProfitTotal=BigDecimal.ZERO;
                    	        /**已到账收益总额**/
                            	BigDecimal hasLedgerProfitTotal=BigDecimal.ZERO;
                    	        /***渠道手续费收益总额***/
                            	BigDecimal channnelFeesTotal=BigDecimal.ZERO;
                    	        /***商户流水总额***/
                            	BigDecimal waterTotal=BigDecimal.ZERO;
                            	
                            	 Map<String,String> param = new HashMap<String,String>();
                                 param.put("zdate", orderDate);
                                 param.put("consume_jointid", String.valueOf(reqBean.getJointid()));
                                 param.put("sellerid", String.valueOf(reqBean.getSellerid()));
                                 
                                long queryCommissionAndMoneyTime = System.currentTimeMillis();
                            	List<Map<String,Object>> resMapList = jointProDao.queryCommissionAndMoney(param);
                            	log.info("queryCommissionAndMoney time:"+(System.currentTimeMillis()-queryCommissionAndMoneyTime));
                            	
                            	long startHandleTime = System.currentTimeMillis();
                                for(Map<String,Object> resMap : resMapList)//
                        	    {	
                        	        if(StringUtils.isNotBlank(resMap.get("commission")+""))
                        	        {
                        	        	//订单佣金json
                        	            JSONObject resJson = JSONObject.parseObject(resMap.get("commission")+"");
                        	            
                        	            //用户区域合作商应收分账金额
                        	            BigDecimal bpartnerMoney=(null==resJson.getBigDecimal("bpartner_amount"))?BigDecimal.ZERO:resJson.getBigDecimal("bpartner_amount");
                        	            //消费区域合作商应收分账金额
                        	            BigDecimal cpartnerMoney=(null==resJson.getBigDecimal("cpartner_amount"))?BigDecimal.ZERO:resJson.getBigDecimal("cpartner_amount");
                        	            //所属合作商
                        	            if(StringUtils.isNotBlank(String.valueOf(resMap.get("jointid")))&&bpartnerMoney.doubleValue()>0)
                        	            {
                        	            	//用户所属合作商id
                        	                int jointid=Integer.valueOf(String.valueOf(resMap.get("jointid"))).intValue();
                        	                //用户所属商家id
                        	                int genussellerid = Integer.valueOf(String.valueOf(resMap.get("genussellerid"))).intValue();
                        	                //所属合作商下的所属商家进行累加
                        	                if(jointid==reqBean.getJointid().intValue()&&reqBean.getSellerid().intValue()==genussellerid)
                        	                {
                        	                	//用户区域合作商下从该商户中分得分账金额
                        	                    ledgerProfitTotal=bpartnerMoney.add(ledgerProfitTotal);
                        	                    
                        	                    //未到账收益金额 (订单状态不为2到账时间是今天的)
                        	                    if(!(2 == Integer.parseInt(String.valueOf(resMap.get("status")))&&orderDate.equals(resMap.get("fdate")+""))){
                        	                    	ledgerNotProfitTotal=bpartnerMoney.add(ledgerNotProfitTotal);
                        	                    }
                        	                    //手续费             	                    
                        	                    if(resJson.getBigDecimal("bpartner_fees") != null){
                        	                    	channnelFeesTotal=resJson.getBigDecimal("bpartner_fees").add(channnelFeesTotal);
                        	                    }              	                                  	                    
                        	                }
                        	            }
                        	            //消费合作商
                        	            if(StringUtils.isNotBlank(String.valueOf(resMap.get("consume_jointid"))))
                                        {
                        	            	//消费商户合作商id
                        	                int consume_jointid=Integer.valueOf(String.valueOf(resMap.get("consume_jointid"))).intValue();
                        	                //消费商家id
                        	                int sellerid=Integer.valueOf(String.valueOf(resMap.get("sellerid"))).intValue();
                        	                //消费商家下的消费商家进行累加
                        	                if(consume_jointid==reqBean.getJointid().intValue()&&reqBean.getSellerid().intValue()==sellerid)
                        	                {
                        	                    ledgerProfitTotal=cpartnerMoney.add(ledgerProfitTotal); 
                        	                    
                        	                    //未到账收益金额
                        	                    if(!(2 == Integer.parseInt(String.valueOf(resMap.get("status")))&&orderDate.equals(resMap.get("fdate")+""))){               	                    	
                        	                    	ledgerNotProfitTotal=cpartnerMoney.add(ledgerNotProfitTotal);
                        	                    }
                        	                                    	                    
                        	                    if(resJson.getBigDecimal("cpartner_fees") != null){
                        	                    	channnelFeesTotal=resJson.getBigDecimal("cpartner_fees").add(channnelFeesTotal);
                        	                    }       
                        	                    
                        	                    //累加消费金额
                                	            BigDecimal water = new BigDecimal(resMap.get("money")+""== null ?"0":resMap.get("money")+"");
                                	            waterTotal = water.add(waterTotal);
                        	                }                	                                             
                                        }
                        	        }
                        	        
                        	    }
                                log.info("if HandleTime time:"+(System.currentTimeMillis()-startHandleTime));
                	            reqBean.setLedgerProfit(ledgerProfitTotal);
                	            reqBean.setNotProfit(ledgerNotProfitTotal);
                	            reqBean.setHasProfit(hasLedgerProfitTotal);
                	            reqBean.setPoundage(channnelFeesTotal);
                	            reqBean.setWaterTotal(waterTotal);
                	            reqBean.setCensusTime(DateUtil.getCurrentTimeStr());
                	            reqBean.setOrderDate(orderDate);
                	            
                                //已到账收益总额
                                jointHasLedgerProfit(reqBean);
                                
                                //商户佣金总额
                                sellerCommisionProcess(reqBean);
                                
                                //插入合作商收益表
                                jointProDao.insertJointProfitCensusd(reqBean);
                                
                                //查询合作商收益全都为0的记录
                                int resCount=jointProDao.queryJointProfitZero(reqBean);
                                
                                if(resCount>=1)
                                {
                                    //删掉合作商收益全都为0的记录
                                    jointProDao.deleteJointProfitZero(reqBean);
                                }
                            }
                            
                        }
                    	}

                    }
                    	
                }                                         
        }
        catch (Exception e)
        {
            log.error("合作商收益日统计出现异常",e);
        }
        long endTime = System.currentTimeMillis();
        
        log.info("时间差......"+(endTime-enterTime));
        
        log.info("定时调用合作商收益日统计方法结束......");
	}

	/**
	 * 商户佣金总额
	 * @param reqBean [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
    public void sellerCommisionProcess(JointProfitCensusd reqBean)
    {
        BigDecimal commisionTotal=BigDecimal.ZERO;
        
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("zdate", String.valueOf(reqBean.getOrderDate()));
        reqMap.put("jointid", String.valueOf(reqBean.getJointid()));
        reqMap.put("genussellerid", String.valueOf(reqBean.getSellerid()));
        long querySellerCommisionTime = System.currentTimeMillis();
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
        log.info("querySellerCommision time:"+(System.currentTimeMillis()-querySellerCommisionTime));

    }


  

    /**
     * 查询商户和合作商信息
     * @param jointid [合作商ID]
     * @param orderTime [订单日期]
     * @return List<JointProfitCensusd> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<JointProfitCensusd> querySellerAndJointInfo(String jointid,String orderTime)
	{
	    Map<String,Object> param = new HashMap<String,Object>();
	    param.put("jointid", jointid);
	    param.put("orderDate", orderTime);
	    
	    List<JointProfitCensusd> resBeanList = jointProDao.querySellerAndJointInfo2(param);

	    return resBeanList;
	}
    
    /**
     * 已到账收益总额
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void jointHasLedgerProfit(JointProfitCensusd reqBean)
    {
    	//统计合作商下的某个商户的到账金额
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
                
                BigDecimal bpartnerMoney=(null==resJson.getBigDecimal("bpartner_amount"))?BigDecimal.ZERO:resJson.getBigDecimal("bpartner_amount");
                BigDecimal cpartnerMoney=(null==resJson.getBigDecimal("cpartner_amount"))?BigDecimal.ZERO:resJson.getBigDecimal("cpartner_amount");
                
                if(StringUtils.isNotBlank(String.valueOf(resMap.get("jointid")))&&bpartnerMoney.doubleValue()>0)
                {
                    int jointid=Integer.valueOf(String.valueOf(resMap.get("jointid"))).intValue();
                    //用户所属商家id
	                int genussellerid = Integer.valueOf(String.valueOf(resMap.get("genussellerid"))).intValue();
                    if(jointid==reqBean.getJointid().intValue()&&genussellerid==reqBean.getSellerid())
                    {
                        hasLedgerProfitTotal=bpartnerMoney.add(hasLedgerProfitTotal);
                    }
                }
                if(StringUtils.isNotBlank(String.valueOf(resMap.get("consume_jointid")))&&cpartnerMoney.doubleValue()>0)
                {
                    int consume_jointid=Integer.valueOf(String.valueOf(resMap.get("consume_jointid"))).intValue();
                    //消费商家id
	                int sellerid=Integer.valueOf(String.valueOf(resMap.get("sellerid"))).intValue();
                    if(consume_jointid==reqBean.getJointid().intValue()&&sellerid == reqBean.getSellerid())
                    {
                        hasLedgerProfitTotal=cpartnerMoney.add(hasLedgerProfitTotal);
                    }
                }
                reqBean.setHasProfit(hasLedgerProfitTotal);
            }
        }
    }
    
	
}
