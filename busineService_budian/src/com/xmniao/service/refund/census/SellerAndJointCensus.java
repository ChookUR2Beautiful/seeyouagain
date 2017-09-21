package com.xmniao.service.refund.census;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.refund.RefundOrderServiceDao;

/**
 * 
*    
* 项目名称：busineService   
* 类名称：SellerAndJointCensus   
* 类描述：   根据订单信息重新计算商户的日收益及合作商收益，
* 适用于隔天的已分账退款，故障单恢复正常已支付的订单统计计算
* 创建人：huww   
* 创建时间：2015年9月8日 上午11:02:06   
* @version    
*
 */
public class SellerAndJointCensus {

	/**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SellerAndJointCensus.class);
    
    /**
     * 未分账订单状态
     */
    private final String wledger = "1,3,6,8,9";
    
    /**
     * 退款订单状态
     */
    private final String refundStatus = "4,5,13";
    
    /**
     * 订单退款DAO层
     */
    @Autowired
    private RefundOrderServiceDao refundDao;
    
    /**
     * bid:订单号
    * @Title: modifySellerDayCensus
    * @Description: 商户日收益数据统计
    * @return boolean    返回类型
    * @throws
     */
    public boolean modifySellerDayCensus(String bid){
    	//订单号验证
    	if(StringUtils.isNotBlank(bid)){
    		//查询订单信息
    		Map<String,Object> orderIfoMap = refundDao.queryOrderInfoByBid(bid);
    		if(null!=orderIfoMap){
    			//订单支付时间
    			String zdate = orderIfoMap.get("zdate")+"";
    			//非今日订单无需处理统计数据，到第二日凌晨会进行统计
    			if(!DateUtil.isCurrentTime(zdate)){
    				//订单分账佣金
    				String commission = orderIfoMap.get("commission")+"";
    				//订单佣金做非空判断
                    if (StringUtils.isNotBlank(commission)){
                    	//订单分账佣金转成json对象
                        JSONObject resJson = JSONObject.parseObject(commission);
                        //计算符号
                        int tag = Integer.parseInt(orderIfoMap.get("tag")+"");
                        //消费商户
                        String sellerid = orderIfoMap.get("sellerid")+""; 
                        //消费商家名称
                        String sellername = orderIfoMap.get("sellername")+"";  
                        //会员绑定的所属商户
                        String genussellerid = orderIfoMap.get("genussellerid")+"";  
                        //会员绑定的所属商户名称
                        String genusname = orderIfoMap.get("genusname")+""; 
                        //会员所属合作商
                        String jointid = orderIfoMap.get("jointid")+"";
                        //会员所属合作商名称
                        String corporate = orderIfoMap.get("corporate")+"";
                        //消费商户合作商
                        String consume_jointid = orderIfoMap.get("consume_jointid")+"";
                        //消费商户合作商名称
                        String consume_corporate = orderIfoMap.get("consume_corporate")+"";
                        //分账时间
                        String ldate = orderIfoMap.get("ldate")+"";
                        //订单状态
                        String status = orderIfoMap.get("status")+"";
                        //消费金额
                        double money = Double.parseDouble(orderIfoMap.get("money")+"")*tag;
                        //订单用户返利金额 
                        double rebate = Double.parseDouble(orderIfoMap.get("rebate")+"")*tag;
                        //营收
                        double seller_amount = resJson.getDouble("seller_amount")*tag;
                        //商户佣金
                        double mike_amount = resJson.getDouble("mike_amount")*tag;
                        //日分账金额
                        double ledger_amount = rebate;
                        //日订单数
                        int orderNum = 1*tag;
                        //已分账订单数
                        int yledgerNum = 1*tag;
                        //未分账订单数
                        int wledgerNum = 1*tag;
                        
                        //存入要修改的数据
                        Map<String,Object> wledgerMap = new HashMap<String, Object>();
            			//日流水总额
            			wledgerMap.put("water_total", money);
            			//日返利总额
            			wledgerMap.put("rebate_total", rebate);
            			//日营业收入
            			wledgerMap.put("income", seller_amount);
            			//日佣金总额
            			wledgerMap.put("commision", mike_amount);
            			//日分账总额(已分账的订单);初始值给0 
            			wledgerMap.put("ledger_total", 0);
            			//日订单数
            			wledgerMap.put("order_total", orderNum);
            			//已分账订单数;初始值给0
            			wledgerMap.put("yledger", 0);
            			//未分账订单数
            			wledgerMap.put("wledger", wledgerNum);
            			//会员店外消费总额；初始值给0
            			wledgerMap.put("shop_total", 0);
            			
            			//截取支付时间的日期部分
                    	String zdateSub = zdate.substring(0,10);
                    	//截取分账时间的日期部分
                    	String ldateSub = StringUtils.isNotBlank(ldate)?ldate.substring(0,10):"";
                    	
                        //消费商家与所属商家相同
                        if(sellerid.equals(genussellerid)){                       	
                        	//支付时间和分账时间不相同
                        	if(!zdateSub.equals(ldateSub)){
                       		
                        		/**
                            	 * 报障单由(14状态) 恢复到(9状态)
                            	 * 消费商家和所属商家相同，需要修改 以下组合的统计，不存则需要新增该条统计
                            	 * 1.消费商户+支付时间 修改water_total，rebate_total，income，order_total，wledger,commision
                            	 */
                        		if((","+wledger+",").contains((","+status+","))){
                        			//查询该商家当日是否存在该统计
                        			Map<String,String> qMap = new HashMap<String,String>();
                        			qMap.put("sellerid", sellerid);
                        			qMap.put("order_date", zdateSub);
                        			int census = refundDao.querySellerCensusBySellerid(qMap);
                        			if(census>0){
                        				//商户编号
                            			wledgerMap.put("sellerid", sellerid);
                            			//下单支付时间
                            			wledgerMap.put("order_date",zdateSub);
                            			//修改该商户该天的统计数据
                            			refundDao.modifySellerCensusBySellerid(wledgerMap);
                        			}else{
                        				//新增一条该商户该天的统计数据
                        				wledgerMap.put("sellerid", sellerid);
                        				wledgerMap.put("jointid", jointid);
                        				wledgerMap.put("corporate", corporate);
                        				wledgerMap.put("order_date", zdateSub);
                        				wledgerMap.put("sellername", sellername);
                        				wledgerMap.put("census_time", zdateSub+" 23:59:59");
                        				refundDao.addSellerDayCensus(wledgerMap);
                        			}                       			
                        		}
                        		
                        		/**
                        		 * 已分账退款单由(2状态)修改到(13状态)   
                        		 * 消费商户和所属商家相同,需要修改 以下组合的统计
                        		 * 1.消费商户+支付时间 修改 water_total，rebate_total，income，order_total，wledger,commision
                        		 * 2.消费商户+分账时间 修改yledger,ledger_total
                        		 */
                        		if((","+refundStatus+",").contains((","+status+","))){
                        			//订单支付时间
                        			wledgerMap.put("order_date",zdateSub);
                        			//商家编号
                        			wledgerMap.put("sellerid",sellerid);
                        			//修改商户统计 排除shop_total，ledger_total
                        			refundDao.modifySellerCensusBySellerid(wledgerMap);
                        			//修改消费商户分账时间的统计
                        			if(!ldateSub.contains("0000-00-00")){
                        				//日分账总额(已分账的订单)
                            			wledgerMap.put("ledger_total", ledger_amount);
                            			//已分账订单数
                            			wledgerMap.put("yledger", yledgerNum);
                            			//依分账时间修改统计
                            			wledgerMap.put("order_date",ldateSub);
                            			//已分账的数据
                            			refundDao.modifySellerCensusBySellerid(wledgerMap);
                        			}
                        			
                        		}
                        	}else{//支付时间和分账时间相同
                        		if((","+wledger+",").contains((","+status+","))){
                        			//查询该商家当日是否存在该统计
                        			Map<String,String> qMap = new HashMap<String,String>();
                        			qMap.put("sellerid", sellerid);
                        			qMap.put("order_date", zdateSub);
                        			int census = refundDao.querySellerCensusBySellerid(qMap);
                        			if(census>0){
                            			refundDao.modifySellerCensusBySellerid(wledgerMap);
                        			}else{
                        				//如果是报障恢复正常单，没有统计则要新增一条统计数据
                        				wledgerMap.put("sellerid", sellerid);
                        				wledgerMap.put("jointid", consume_jointid);
                        				wledgerMap.put("sellername", sellername);
                        				wledgerMap.put("corporate", consume_corporate);
                        				wledgerMap.put("order_date", zdateSub);
                        				wledgerMap.put("census_time", zdateSub+" 23:59:59");
                        				refundDao.addSellerDayCensus(wledgerMap);
                        			}
                        		}else{
                        			wledgerMap.put("order_date",zdateSub);
                            		//日分账总额(已分账的订单)
                        			wledgerMap.put("ledger_total", ledger_amount);
                        			//已分账订单数
                        			wledgerMap.put("yledger", yledgerNum);
                        			//订单支付时间
                        			wledgerMap.put("order_date",zdateSub);
                        			//商家编号
                        			wledgerMap.put("sellerid",sellerid);
                        			refundDao.modifySellerCensusBySellerid(wledgerMap);
                        		}                      		                   			
                        	}
                        }else{//消费商户和所属商户不同
                        	
                        	//支付时间和分账时间不相同
                        	//if(!zdateSub.equals(ldateSub)){
                        		
                        		/**
                        		 * 已分账退款单由(2状态)修改到(13状态)   
                        		 * 消费商户和所属商家不同,需要修改 以下组合的统计
                        		 * 1.消费商户+支付时间 修改 water_total，rebate_total，income，order_total，wledger
                        		 * 2.消费商户+分账时间 修改yledger,ledger_total
                        		 * 3.所属商户+支付时间 修改commision,shop_total
                        		 * 4.所属商户+分账时间 修改yledger,ledger_total
                        		 */
                            	if((","+refundStatus+",").contains((","+status+","))){                          		                            	
                            		
                            		//统计消费商户+支付时间
                            		//修改water_total，rebate_total，income，order_total，wledger
                        			wledgerMap.remove("commision");
                        			wledgerMap.put("sellerid", sellerid);
                        			wledgerMap.put("order_date", zdateSub);
                            		refundDao.modifySellerCensusBySellerid(wledgerMap);
                            		                           		                            		
                            		//统计所属商户+支付时间
                            		//修改commision,shop_total
                            		wledgerMap.clear();
                            		wledgerMap.put("commision", mike_amount);
                            		wledgerMap.put("shop_total", money);
                            		wledgerMap.put("sellerid", genussellerid);
                        			wledgerMap.put("order_date", zdateSub);
                            		refundDao.modifySellerCensusBySellerid(wledgerMap);
                            		                            		
                            		//分账时间排除0000-00-00及为""的错误时间
                            		if(!ldateSub.contains("0000-00-00")&&!ldateSub.equals("")){
                            			
                            			//统计消费商户+分账时间
                                		//修改yledger,ledger_total
                            			wledgerMap.clear();
                                		wledgerMap.put("ledger_total", rebate);
                                		wledgerMap.put("yledger", yledgerNum);
                                		wledgerMap.put("sellerid", sellerid);
                            			wledgerMap.put("order_date", ldateSub);
                                		refundDao.modifySellerCensusBySellerid(wledgerMap);
                                		
                            			//统计所属商户+分账时间
                                		//修改yledger,ledger_total
                                		wledgerMap.clear();
                                		wledgerMap.put("ledger_total", rebate);
                                		wledgerMap.put("yledger", yledgerNum);
                                		wledgerMap.put("sellerid", genussellerid);
                            			wledgerMap.put("order_date", ldateSub);
                                		refundDao.modifySellerCensusBySellerid(wledgerMap);                                		
                            		}
                            	}
                            	                          	                                                 	
                            	/**
                            	 * 报障单由(14状态) 恢复到(9状态)
                            	 * 消费商户和所属商家不同,需要修改 以下组合的统计，不存则需要新增该条统计
                            	 * 1.消费商户+支付时间 修改water_total，rebate_total，income，order_total，wledger（未分账）
                            	 * 2.所属商户+支付时间 修改commision,shop_total
                            	 */
                            	                            	
                            	if((","+wledger+",").contains((","+status+","))){
                            		//统计消费商户+支付时间
                            		//查询该商家当日是否存在该统计
                        			Map<String,String> qMap = new HashMap<String,String>();
                        			qMap.put("sellerid", sellerid);
                        			qMap.put("order_date", zdateSub);
                        			int census = refundDao.querySellerCensusBySellerid(qMap);
                        			if(census>0){
                        				//移除commision的修改
                        				wledgerMap.remove("commision");
                        				//商户编号
                            			wledgerMap.put("sellerid", sellerid);
                            			//下单支付时间
                            			wledgerMap.put("order_date",zdateSub);
                            			//修改该商户该天的统计数据 water_total，rebate_total，income，order_total，wledger
                            			refundDao.modifySellerCensusBySellerid(wledgerMap);
                        			}else{
                        				//新增一条该商户该天的统计数据
                        				wledgerMap.put("sellerid", sellerid);
                        				wledgerMap.put("jointid", consume_jointid);
                        				wledgerMap.put("sellername", sellername);
                        				wledgerMap.put("corporate", consume_corporate);
                        				wledgerMap.put("order_date", zdateSub);
                        				wledgerMap.put("census_time", zdateSub+" 23:59:59");
                        				refundDao.addSellerDayCensus(wledgerMap);
                        			}
                            		
                            		
                        			//统计所属商户+支付时间
                            		//查询该商家当日是否存在该统计
                            		//修改commision,shop_total
                        			qMap.put("sellerid", genussellerid);
                        			qMap.put("order_date", ldateSub);
                        			if(refundDao.querySellerCensusBySellerid(qMap)>0){
                        				//商户编号
                            			wledgerMap.put("sellerid", genussellerid);
                            			//下单支付时间
                            			wledgerMap.put("order_date",zdateSub);
                            			//修改该商户该天的统计数据 water_total，rebate_total，income，order_total，wledger
                            			refundDao.modifySellerCensusBySellerid(wledgerMap);
                        			}else{
                        				//新增一条该商户该天的统计数据
                        				wledgerMap.put("sellerid", genussellerid);
                        				wledgerMap.put("jointid", jointid);
                        				wledgerMap.put("sellername", genusname);
                        				wledgerMap.put("corporate", corporate);
                        				wledgerMap.put("order_date", zdateSub);
                        				wledgerMap.put("census_time", zdateSub+" 23:59:59");
                        				refundDao.addSellerDayCensus(wledgerMap);
                        			}
                            		
                            	}
                         	                           	
                        	//}                  	
                        }                        
                    }
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * 
    * @Title: modifySellerCensus
    * @Description: 修改商户统计方法
    * @return boolean    返回类型
    * @throws
     */
    public boolean modifySellerCensus(Map<String,Object> censusMap){
    	if(null!=censusMap){
    		if(refundDao.modifySellerCensusBySellerid(censusMap)>0){
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void main(String[] args) {
    	String ss = "11";
    	System.out.println("111".contains(ss));
    	
	}
}
