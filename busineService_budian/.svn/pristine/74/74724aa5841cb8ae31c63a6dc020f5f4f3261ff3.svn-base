package com.xmniao.service.refund.census;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.refund.RefundOrderServiceDao;

/****
 * 
 * 单个订单恢复时，合作商日统计数据相应变更
*@Description ： 
*@param ：
*@return :
 */
@Service
public class JointDayCensus {
	
    /**
     * 订单退款DAO层
     */
    @Autowired
    private RefundOrderServiceDao refundDao;
	
	
	/****
	 * 
	 *  status 14 -->  9  
	 *  不是当天时，合作商日统计表的profit_total 日收益总额需要加上退款时减去的部分
	 *  
	 */
	public void  modifyJointDayCensus(String bid){
    	//订单号验证
    	if(StringUtils.isNotBlank(bid)){
    		//查询订单信息
    		Map<String,Object> orderIfoMap = refundDao.queryOrderInfoByBid(bid);
    		if(null!=orderIfoMap){
    			//订单支付时间
    			String zdate = orderIfoMap.get("zdate")+"";
    			if(!DateUtil.isCurrentTime(zdate)){
    				//订单分账佣金
    				String commission = orderIfoMap.get("commission")+"";
    				//订单佣金做非空判断
                    if (StringUtils.isNotBlank(commission)&&!commission.equals("null")){
                    	//订单分账佣金转成json对象
                        JSONObject resJson = JSONObject.parseObject(commission);
                        //会员所属合作商
                        String jointid = orderIfoMap.get("jointid")+"";
                        //会员所属合作商名称
                        String corporate = orderIfoMap.get("corporate")+"";
                        //消费商户合作商
                        String consume_jointid = orderIfoMap.get("consume_jointid")+"";
                        //用户 ： 合作商+业务员
                        BigDecimal bpartnerAmount=resJson.getBigDecimal("bpartner_amount").add(resJson.getBigDecimal("memberJointMoney"));
                        //商户 ： 合作商+业务员
                        BigDecimal cpartnerAmount=resJson.getBigDecimal("cpartner_amount").add(resJson.getBigDecimal("consumeJointidMoney"));
                        
                        String census_time = zdate.substring(0, 10)+" 23:59:59";
                                                
                        //消费合作商和所属合作商是同一个
                        if(jointid.equals(consume_jointid)){
                        	Map<String,Object> parammap = new HashMap<String,Object>();
                        	parammap.put("jointid", jointid);
                        	parammap.put("order_date", zdate);
                        	Map<String,Object> jointDayCensusMap = refundDao.queryJointDayCensus(parammap);

                        	if(null != jointDayCensusMap){
                            	BigDecimal beforeProfit = new BigDecimal(jointDayCensusMap.get("profit_total")+"");
                            	BigDecimal afterProfit  = beforeProfit.add(bpartnerAmount).add(cpartnerAmount);
                            	//累计结果不为0才去修改数据
                            	if(cpartnerAmount.doubleValue()!=0||bpartnerAmount.doubleValue()!=0)
                                {
                            		parammap.put("profit_total", afterProfit);
                            		refundDao.updateJointDayCensus(parammap);
                                }                            	
                        	}else{
                        		//统计数据非0方可添加统计记录
                        		if(cpartnerAmount.doubleValue()!=0||bpartnerAmount.doubleValue()!=0){
                        			parammap.put("profit_total", bpartnerAmount.add(cpartnerAmount));
                            		parammap.put("corporate", corporate);
                            		parammap.put("census_time", census_time);
                            		refundDao.insertJointDayCensus(parammap);
                        		}                       		
                        	}
                        	
                        }else{//不是同一个
                        	
                        	//jointid 所属商家
                         	Map<String,Object> parammap = new HashMap<String,Object>();
                        	parammap.put("jointid", jointid);
                        	parammap.put("order_date", zdate);
                        	Map<String,Object> jointDayCensusMap = refundDao.queryJointDayCensus(parammap);                        	

                        	//不为空，更新
                        	if(null != jointDayCensusMap){
                            	BigDecimal beforeProfit = new BigDecimal(jointDayCensusMap.get("profit_total")+"");
                            	BigDecimal afterProfit  = beforeProfit.add(bpartnerAmount);
                            	//累计结果不为0才去修改数据
                            	if(bpartnerAmount.doubleValue()!=0)
                                {
                            		parammap.put("profit_total", afterProfit);
                                	refundDao.updateJointDayCensus(parammap);
                                }                            	
                        	}else{//为空，插入
                        		//统计数据非0方可添加统计记录
                        		if(bpartnerAmount.doubleValue()!=0){
                        			parammap.put("profit_total", bpartnerAmount);
                                	parammap.put("corporate", corporate);
                                	parammap.put("census_time", census_time);
                            		refundDao.insertJointDayCensus(parammap);
                        		}                            	
                        	}
                       	
                        	//consume_jointid消费商家
                         	Map<String,Object> parammap1 = new HashMap<String,Object>();
                         	parammap1.put("jointid", consume_jointid);
                         	parammap1.put("order_date", zdate);
                        	Map<String,Object> jointDayCensusMap1 = refundDao.queryJointDayCensus(parammap1);
                       	
                        	if(null != jointDayCensusMap1){
                            	BigDecimal beforeProfit1 = new BigDecimal(jointDayCensusMap1.get("profit_total")+"");
                            	BigDecimal afterProfit1  = beforeProfit1.add(cpartnerAmount);
                            	//累计结果不为0才去修改数据
                            	if(cpartnerAmount.doubleValue()!=0)
                                {
                            		parammap1.put("profit_total", afterProfit1);
                                	refundDao.updateJointDayCensus(parammap1);
                                }                           	
                        	}else{
                        		if(cpartnerAmount.doubleValue()!=0)
                                {
                        			parammap1.put("profit_total", cpartnerAmount);
                                	parammap.put("corporate", corporate);
                                	parammap.put("census_time", census_time);
                            		refundDao.insertJointDayCensus(parammap1);
                                }                            	
                        	}
                        }                                                
                    }
    			}
    		}
    	}
	}
	
	
}
