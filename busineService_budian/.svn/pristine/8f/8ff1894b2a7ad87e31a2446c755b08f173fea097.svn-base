package com.xmniao.main;


import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.domain.ledger.LedgerConstants;
import com.xmniao.domain.ledger.LedgerNewBean;
import com.xmniao.domain.order.BillEntity;
import com.xmniao.service.ledger.LedgerAlgorithmUtils;

public class Test
{
	 /**
     * 日志记录
     */
    private final static Logger log = Logger.getLogger(Test.class);
    
    private	final static String  LEDER_REDIS_QUEUE = "auto_orderform_queue_dev_1000l";

	public static void main(String[] args) throws Exception
	{

		 @SuppressWarnings("resource")
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("conf/busine-base.xml");
         context.start();
         StringRedisTemplate redisTemplate =(StringRedisTemplate) context.getBean("redisTemplate");
         
		log.info("消费订单分账开始...\n ---------------------------------------------------------");
		BillEntity bill =  setOrderInfo(); 
		log.info("1.模拟下单，生成订单sql：\n\n"+printInsertSql(bill)+
				"\n\n ---------------------------------------------------------");
		// 2.计算分账结果
		getLedgerInfo(bill);
		log.info("3.更新订单信息sql(分账信息)：\n"+printUpdateSql(bill)+
				"\n\n ---------------------------------------------------------");
		log.info("4.模拟商户发起分账，插入订单数据至队列中...\n\n monitor_queue_dev_1000l(list)：\n"+addRedisListInfo(bill,redisTemplate)+
				"\n\n ---------------------------------------------------------");
		log.info("5.查询订单分账状态,(消息服务更新订单状态，由分账服务发起)：\n\n"+
				"SELECT b.bid,b.hstatus FROM t_bill b where b.bid = "+bill.getBid()+
				"\n\n---------------------------------------------------------");
		log.info("消费订单分账结束，请查询支付服务中的钱包账户数据!");
	}

	
	private static String addRedisListInfo(BillEntity bill,StringRedisTemplate redisTemplate){
		
         JSONObject json = (JSONObject) JSONObject.toJSON(bill);
         json.put("ledger_type", 2); 																			//分账方式     1:手动，2:自动，3:T+3
         
         redisTemplate.opsForList().leftPush(LEDER_REDIS_QUEUE, JSONObject.toJSONString(bill));					/* 添加到 分账服务订单监听队列 */
         
         return json.toJSONString();
         
	}
	
	private static JSONObject getLedgerInfo(BillEntity bill){
		
		LedgerNewBean bean = new LedgerNewBean();
		bean.setOrderMoney(bill.getMoney());  				// 订单金额
		bean.setBaseagio(bill.getBaseagio());   			// 商户折扣
		bean.setMikeType(bill.getMike_type());				// 寻蜜客类型
		
		bean.setParentMikeId(1); 							// 上级寻蜜客ID(订单表无此参数,需要查询)
		bean.setTopMikeId(2);  								// 上上级寻蜜客ID(订单表无此参数，,需要查询)
		
		bean.setConsumeJointid(bill.getConsume_jointid());  // 经销商ID
		Map<String,Object> resultMap =  printResult(bean);
		JSONObject json = (JSONObject) JSONObject.toJSON(resultMap);
		bill.setCommission(json.getString("commission"));
		return (JSONObject) JSONObject.toJSON(resultMap);
	}
	
	
	/**
	 * 获取分账结果，并打印
	 * @param bean
	 */
	private  static Map<String,Object> printResult(LedgerNewBean bean){
		
		Map<String,Object> resultMap = LedgerAlgorithmUtils.getLedgerMoney(bean);
		
		JSONObject jsonObject = (JSONObject)resultMap.get("commission") ;
		log.info("2.计算本单分账,输出分账结果:分账结果JSON："+resultMap
		+"\n分账结果解释："
		+"\n      订单金额："+bean.getOrderMoney()+"\t          商户折扣："+PreciseComputeUtil.mul(bean.getBaseagio(), 10, 2)+"折"+
				"\t        最高分账比例："+resultMap.get("ledgerRatio")+"%"
		+"\n  可供分账金额：" + resultMap.get("ledgerMoney")+
				"\t      商户营收金额："+jsonObject.get(LedgerConstants.SELLER_AMOUNT)+
				"\t        商户店外收益："+jsonObject.get(LedgerConstants.SELLER_MONEY)
		+"\n  用户支付金额：" + resultMap.get("userMoney") +
				"\t      用户获得积分：" + jsonObject.get(LedgerConstants.USERMONEY)+
				"\t        用户优惠金额：" + resultMap.get("user_money") 
		+"\n寻蜜客分账金额："+jsonObject.get(LedgerConstants.MIKE_AMOUNT)+
				"\t上级寻蜜客分账金额："+jsonObject.get(LedgerConstants.PARENT_MIKE_AMOUNT)+
				"\t上上级寻蜜客分账金额："+jsonObject.get(LedgerConstants.TOP_MIKE_AMOUNT)
		+"\n经销商分账金额："+jsonObject.get(LedgerConstants.CPARTNER_AMOUNT)+
				"\t        平台分账金额："+jsonObject.get(LedgerConstants.PLATFORM_AMOUNT)
		+"\n---------------------------------------------------------");
		return resultMap;
	}
	
	private static String printUpdateSql(BillEntity bill) {
		StringBuffer sql = new StringBuffer("UPDATE t_bill b SET b.commission = ");
		if(!StringUtils.isEmpty(bill.getCommission())){
			sql.append("'").append(bill.getCommission()).append("'");
		}else{
			sql.append("'{}'");
		}
		sql.append(" WHERE b.bid = ").append(bill.getBid());
		
		return sql.toString();
	}


	/**
	 * 设置 分账订单参数
	 */
	public static BillEntity setOrderInfo(){
	
		BillEntity bill = new BillEntity();
		String bid = DateUtil.getOrderNo(new Date());
		bill.setBid(Long.valueOf(bid));                  // 订单编号，12位长度,格式6位年月日加6为顺序数
		
		bill.setUid(12);                  				 // 用户ID
		bill.setNname(" ");                				 // 用户昵称
		
		bill.setSellerid(2);             				 // 消费商家ID
		bill.setSellername("消费测试商户");           		 // 消费商家名称
		
		bill.setMoney(0.14d);                			 // 订单总金额
		bill.setBaseagio(0.9d);             			 // 商户折扣
		
		bill.setMikeid(32);			   					 // 寻蜜客ID(新增)
		bill.setMikename("测试寻蜜客");			   			 // 寻蜜客名称(新增)
		bill.setMike_type(1);            				 // 寻蜜客类型  0 未绑定任何向蜜客  1 用户向蜜客  2中脉寻蜜客     3用户向蜜客(过期)
		
		
		bill.setGenussellerid(1);        				 // 所属商家ID
		bill.setGenusname("所属测试商户");            		 // 所属商家名称
		
		bill.setJointid(1);              				 // 所属商家所属合作商ID
		bill.setCorporate("测试经销商");            		 // 所属商家所属合作商名称
		
		
		bill.setConsume_jointid(3);      				 // 经销商ID（消费商家所属合作商ID)
		bill.setConsume_corporate("测试所属经销商");    		 // 经销商名称（消费商家所属合作商名称)
		
		
		bill.setZdate(new Date()); 						 // 支付时间
		bill.setStatus(3);			   					 // 订单状态
		bill.setHstatus(1); 			   				 // 审核状态
		
		// setOrderOtherInfo(bill);						 // 设置其他参数
			
		return bill;
	}
	
	
	public static String printInsertSql(BillEntity bill){
		JSONObject jsonObj = (JSONObject) JSONObject.toJSON(bill); 
		StringBuffer sbf = new StringBuffer("insert into t_bill");
		String keys = jsonObj.keySet().toString().replace("[", "(").replace("]", ")");
		sbf.append(keys).append(" values(");
		for(Object value:jsonObj.values()){
			if(null != value){
				if(value instanceof String){
					sbf.append("'").append(value).append("'").append(",");
				}else if(value instanceof Date){
					sbf.append("'").append(DateUtil.format((Date)value, "yyyy-mm-dd HH:MM:ss")).append("'").append(",");
				}else{
					sbf.append(value).append(",");
				}
			}else{
				sbf.append(value).append(",");
			}
		}
		String sql  = sbf.substring(0, sbf.length()-1);
		sql += ");";
		return sql;
	}
	
	/**
	 * 设置其他必要参数 
	 * @param bill
	 */
	public static void setOrderOtherInfo(BillEntity bill){
		
		bill.setNumber(DateUtil.getOrderNo(new Date())); // 支付流水号
		bill.setPayid("1232131321");                	 // 支付ID
		bill.setPaytype(123123);              			 // 支付方式
		bill.setArea(1986);                 			 // 所消费地区的编号 如南山区 的编号
		
		bill.setRebate(0d);               				 // 本单可返利金额
		bill.setIs_virtual(1);           				 // 是否虚拟订单，默认为0 不是虚拟订单   1虚拟订单
		bill.setFlat_agio(0d);            				 // 平台补贴占比，默认为0  平台做促销活动时此字段表示平台给用户的折扣补贴
		bill.setFlat_money(0d);           				 // 平台补贴金额
		
		bill.setProfit(0d);               				 // 返利支付金额
		bill.setCommision(0d);            				 // 佣金支付金额
		bill.setPayment(0d);              				 // 需支付金额
		bill.setGive_money(0d);           				 // 赠送支付金额
		bill.setCuser(0d);                				 // 优惠券支付金额
		bill.setCdenom(0d);               				 // 优惠卷面额总数
		bill.setRatio(0d);                				 // 佣金补贴比例(商户补贴比例)
		bill.setRatio_money(0d);          				 // 佣金补贴金额
		
	}
}
