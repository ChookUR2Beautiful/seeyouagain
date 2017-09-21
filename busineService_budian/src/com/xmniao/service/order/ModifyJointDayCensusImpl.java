package com.xmniao.service.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.MathUtil;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.order.JointDayCensusBean;

/**
 * 调单更新合作商订单统计实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年7月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ModifyJointDayCensusImpl
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(ModifyJointDayCensusImpl.class);
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    
    // adjBeforeOrderMap  调单前的订单信息           adjAfterOrderMap   调单后的订单信息
    public void modifyJointDayCensus(Map<String, Object> adjBeforeOrderMap,Map<String, Object> adjAfterOrderMap)
    {
      log.info("modifyJointDayCensus start"); 
      
      //合作商可能为null
      //调单前的订单合作商
      Object beforejoint = adjBeforeOrderMap.get("jointid");
      Object beforeconsume_joint = adjBeforeOrderMap.get("consume_jointid");
      //调单后的订单合作商
      Object afterjoint = adjAfterOrderMap.get("jointid");
      Object afterconsume_joint = adjAfterOrderMap.get("consume_jointid");
      
      Set<String> jointset = new HashSet<String>();
      if(beforejoint != null &&  MathUtil.isNumeric(String.valueOf(beforejoint))){
      	jointset.add(String.valueOf(beforejoint));
      }
      if(beforeconsume_joint != null && MathUtil.isNumeric(String.valueOf(beforeconsume_joint))){
      	jointset.add(String.valueOf(beforeconsume_joint));
      }
      if(afterjoint != null && MathUtil.isNumeric(String.valueOf(afterjoint))){
      	jointset.add(String.valueOf(afterjoint));
      }
      if(afterconsume_joint != null && MathUtil.isNumeric(String.valueOf(afterconsume_joint))){
      	jointset.add(String.valueOf(afterconsume_joint));
      }
      
      //订单的详细时间
      String zdate = String.valueOf(adjBeforeOrderMap.get("zdate"));
      Set<String> dateset = new HashSet<String>();
      if(adjBeforeOrderMap.get("fdate") != null){
          //String fdate = String.valueOf(adjBeforeOrderMap.get("fdate")).substring(0,10);
          String fdate = String.valueOf(adjBeforeOrderMap.get("fdate"));
          dateset.add(fdate);
      }
      dateset.add(zdate);
      
      for(String jointid : jointset){
    	  for(String date : dateset){
    	      //格式化为YYYY-MM-dd的时间
    	      String  tempdate = String.valueOf(date).substring(0,10);
    		  //如果日期是当天，则不会重新统计
    		  String  today = DateUtil.format(new Date(),"yyyy-MM-dd");
    		  if(!today.equals(tempdate)){//不是当天才统计

        	      //合作商信息处理
        	      JointDayCensusBean resBean=queryJointInfoProcess(jointid,tempdate);
        	      //统计时间改为date
        	      resBean.setCensusTime(date);
        	      //合作商日收益处理
        	      jointProfitTotalProcess(resBean);
        	      //合作商日收益到账处理
        	      jointAccountTotalProcess(resBean); 
        	      
                  Map<String,String> paraMap = new HashMap<String,String>();
                  paraMap.put("jointid", jointid);
                  paraMap.put("date", tempdate);
                  
                  if(orderDao.queryOneJointDayCensusByIDAndDate(paraMap) == 1){
                      if(resBean.getProfitTotal().doubleValue()!=0 || resBean.getAccountTotal().doubleValue()!=0)
                      {
                          //更新合作商日订单统计表      modifyJointDayCensus
                          orderDao.updateOneJointDayCensusByIDAndDate(resBean);
                          log.info("更新了合作商日订单统计表记录  jointid"+resBean.getJointId());
                      }
                  }else if(orderDao.queryOneJointDayCensusByIDAndDate(paraMap) == 0){
                      if(resBean.getProfitTotal().doubleValue()!=0 || resBean.getAccountTotal().doubleValue()!=0)
                      {
                          //插入合作商日订单统计表
                          orderDao.insertJointDayCensus(resBean);
                          log.info("更新了合作商日订单统计表记录  jointid"+resBean.getJointId());
                      }
                  }
    		  }
    		  
    	  }
      }
      log.info("modifyJointDayCensus end");
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