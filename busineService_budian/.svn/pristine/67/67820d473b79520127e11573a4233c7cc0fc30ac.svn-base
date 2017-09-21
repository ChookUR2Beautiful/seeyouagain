package com.xmniao.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.MathUtil;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.dao.profit.JointProfitCensusdMapper2;
import com.xmniao.domain.profit.JointProfitCensusd;

/**
 * 调单更新合作商收益订单统计实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年7月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ModifyJointProfitDayCensusImpl
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(ModifyJointProfitDayCensusImpl.class);
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    
	@Autowired
	private JointProfitCensusdMapper2 jointProDao;
    
    public void modifyJointProfitDayCensus(Map<String, Object> adjBeforeOrderMap,Map<String, Object> adjAfterOrderMap)
    {
        log.info("modifyJointProfitDayCensus start");
        
        //合作商可能为null
        //调单前的订单合作商
        Object beforejoint = adjBeforeOrderMap.get("jointid");
        Object beforeconsume_joint = adjBeforeOrderMap.get("consume_jointid");
        //调单后的订单合作商
        Object afterjoint = adjAfterOrderMap.get("jointid");
        Object afterconsume_joint = adjAfterOrderMap.get("consume_jointid");
        
        Set<String> jointset = new HashSet<String>();
        if(beforejoint != null && MathUtil.isNumeric(String.valueOf(beforejoint))){
        	jointset.add(String.valueOf(beforejoint));
        }
        if(beforeconsume_joint != null && MathUtil.isNumeric(String.valueOf(beforeconsume_joint))){
        	jointset.add(String.valueOf(beforeconsume_joint));
        }
        if(afterjoint != null &&  MathUtil.isNumeric(String.valueOf(afterjoint))){
        	jointset.add(String.valueOf(afterjoint));
        }
        if(afterconsume_joint != null && MathUtil.isNumeric(String.valueOf(afterconsume_joint))){
        	jointset.add(String.valueOf(afterconsume_joint));
        }
        
        
        //调单前的商家id
        String beforesellerid =  String.valueOf(adjBeforeOrderMap.get("sellerid"));
        String beforegenussellerid =  String.valueOf(adjBeforeOrderMap.get("genussellerid"));
        //调单后的商家id
        String aftersellerid =  String.valueOf(adjAfterOrderMap.get("sellerid"));
        String aftergenussellerid =  String.valueOf(adjAfterOrderMap.get("genussellerid"));
        
        Set<String> sellerset = new HashSet<String>();
        sellerset.add(beforesellerid);
        sellerset.add(beforegenussellerid);
        sellerset.add(aftersellerid);
        sellerset.add(aftergenussellerid);
        

        
        String zdate = String.valueOf(adjBeforeOrderMap.get("zdate"));
        Set<String> dateset = new HashSet<String>();
        if(adjBeforeOrderMap.get("fdate") != null){
            //String fdate = String.valueOf(adjBeforeOrderMap.get("fdate")).substring(0,10);
        	String fdate = String.valueOf(adjBeforeOrderMap.get("fdate"));
            dateset.add(fdate);
        }
        dateset.add(zdate);
        
        for(String joint : jointset){
        	for(String seller : sellerset){
        		for(String longdate :dateset){
        			//YYYY-MM-dd
        			String date = longdate.substring(0,10);
        			//如果日期是当天，则不会重新统计
          		  	String  today = DateUtil.format(new Date(),"yyyy-MM-dd");
        			
          		   if(!today.equals(date)){//不是当天才统计
           			
           			JointProfitCensusd jpc = new JointProfitCensusd();
           			jpc.setJointid(Integer.parseInt(joint));
           			jpc.setSellerid(Integer.parseInt(seller));
           			jpc.setOrderDate(date);
           			//合作商和商家名称
           			jpc.setCorporate(jointProDao.getCorporateByjointid(joint));
           			jpc.setSellername(jointProDao.getSellernameBySellerid(seller));
           			
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
              	 	param.put("zdate", date);
              	 	param.put("consume_jointid", String.valueOf(jpc.getJointid()));
              	 	param.put("sellerid", String.valueOf(jpc.getSellerid()));
              	 	
              	 	List<Map<String,Object>> resMapList = jointProDao.queryCommissionAndMoney(param);
                  	 	
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
   	         	                if(jointid==jpc.getJointid().intValue()&&jpc.getSellerid().intValue()==genussellerid)
   	         	                {
   	         	                	//用户区域合作商下从该商户中分得分账金额
   	         	                    ledgerProfitTotal=bpartnerMoney.add(ledgerProfitTotal);
   	         	                    
   	         	                    //未到账收益金额 (订单状态不为2到账时间是今天的)
   	         	                    if(!(2 == Integer.parseInt(String.valueOf(resMap.get("status")))&&date.equals(resMap.get("fdate")+""))){
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
   	         	                if(consume_jointid==jpc.getJointid().intValue()&&jpc.getSellerid().intValue()==sellerid)
   	         	                {
   	         	                    ledgerProfitTotal=cpartnerMoney.add(ledgerProfitTotal); 
   	         	                    
   	         	                    //未到账收益金额
   	         	                    if(!(2 == Integer.parseInt(String.valueOf(resMap.get("status")))&&date.equals(resMap.get("fdate")+""))){               	                    	
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
   	               	 
   	 	            jpc.setLedgerProfit(ledgerProfitTotal);
   	 	            jpc.setNotProfit(ledgerNotProfitTotal);
   	 	            jpc.setHasProfit(hasLedgerProfitTotal);
   	 	            jpc.setPoundage(channnelFeesTotal);
   	 	            jpc.setWaterTotal(waterTotal);
   	 	            jpc.setCensusTime(longdate);//精确时间
   	 	            jpc.setOrderDate(date);
    	            
   	                 //已到账收益总额
   	 	            jointHasLedgerProfit(jpc);
   	                 
   	                 //商户佣金总额
   	 	           	sellerCommisionProcess(jpc);
   	                 //存在统计记录，更新
   	                 if(jointProDao.queryOneJointProfitCensusdBySelleridAndJointIdAndDate(jpc) == 1){
   	                	 
   	                	 //modifyJointProfitDayCensus
   	                	 jointProDao.updateOneJointProfitCensusdBySelleridAndJointIdAndDate(jpc);
   	                	 log.info("更新了合作商收益统计记录  sellerid:"+jpc.getSellerid()+"   &&&   jointid:"+jpc.getJointid());
   	                	//不存在，插入 
   	                 }else if(jointProDao.queryOneJointProfitCensusdBySelleridAndJointIdAndDate(jpc) == 0){
   	                	//插入合作商收益表
                         jointProDao.insertJointProfitCensusd(jpc);
                         log.info("新增了合作商收益统计记录  sellerid:"+jpc.getSellerid()+"   &&&   jointid:"+jpc.getJointid());
   	                 }
          		   }
        		}
        	}
        }
        log.info("modifyJointProfitDayCensus end");
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
