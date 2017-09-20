package com.xmniao.xmn.core.util.ledger;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 分账测试
 * 
 * @author Administrator
 *
 */
public class SplitUtilTest {

	/**
	 * 日志记录
	 */
	private static final Logger log = Logger.getLogger(SplitUtilTest.class);
	private Jedis jedis; // redis客户端

	@Before
	public void before() {
		jedis = new Jedis("szdev.xmniao.com", 66379, 0);
	}

	@Test
	public void legerTest() throws Exception {

		double data = 100;// 增量因子
		double money = 0;// 分账金额
		int num = 2;// 循1环次数
		
		StringBuffer sb = new StringBuffer();

		HashMap<String, Double> resultMap = null;

		// 初始化分账费率
		HashMap<String, Double> rateMap = new HashMap<String, Double>();
		rateMap.put("shRate", 0.1);// 商户费率
		rateMap.put("zmRate", 0.2);// 中脉费率

		for (int i = 1; i <= num; i++) {

			money = NumUtil.mul(data, i);// 分账金额

			// 分账计算
			resultMap = SplitUtil.leger(money, rateMap);
			
			
//			jedis.lpush("delay_SAASLedger_queue_dev_1000l".getBytes(), sb.toString().getBytes());
			log.info("");
			log.info("-----------------分账(第" + i + "次)-----------------");
			log.info("分账结果 result=" + resultMap);
			log.info("redis队列消息="+sb.toString());
			log.info("-----------------------------------------------------");
			log.info("");
		}
	}

	@Test
	public void freshLegerTest() throws Exception {

		double data = 0.01;// 增量因子

		int num = 1;// 循环次数

		double purchaseMoney = 8000.00;// 采购价
		double sellMoney = 0;// 销售价

		HashMap<String, Double> resultMap = null;

		// 初始化分账费率
		HashMap<String, Double> rateMap = new HashMap<String, Double>();
		rateMap.put("shRate", 0.01);// 商户费率
		rateMap.put("sysRate", 0.02);// 平台费率

		for (int i = 1; i <= num; i++) {

			sellMoney = NumUtil.add(purchaseMoney, NumUtil.mul(NumUtil.mul(data, i), 100));

			// 分账计算
			resultMap = SplitUtil.freshLeger(purchaseMoney, sellMoney, rateMap);

			log.info("");
			log.info("---------商品销售分账（生鲜）(第" + i + "次)---------");
			log.info("分账结果 result=" + resultMap);
			log.info("-----------------------------------------------------");
			log.info("");
		}
	}

	@Test
	public void goodsLegerTest() throws Exception {

		double data = 0.01;// 增量因子

		int num = 1;// 循环次数

		double money = 0;

		double purchaseMoney = 30.00;// 采购价
		double profitRate = 0.05;// 利润率

		HashMap<String, Double> resultMap = null;

		// 初始化分账费率
		HashMap<String, Double> rateMap = new HashMap<String, Double>();
		rateMap.put("shRate", 0.01);// 商户费率
		rateMap.put("zmRate", 0.01);// 中脉费率

		for (int i = 1; i <= num; i++) {

			money = NumUtil.add(purchaseMoney, NumUtil.mul(NumUtil.mul(data, i), 100));

			// 分账计算
			resultMap = SplitUtil.goodsLeger(money, profitRate, rateMap);

			log.info("");
			log.info("---------商品销售分账（商品）(第" + i + "次)---------");
			log.info("分账结果 result=" + resultMap);
			log.info("-----------------------------------------------------");
			log.info("");
		}

	}

	@Test
	public void saasLegerTest() throws Exception {
		double data = 0.01;// 增量因子
		double moneyDate = 360;// 分账金额增量
		double money = 0;// 分账金额
		int num = 100;// 循环次数

		HashMap<String, Double> resultMap = null;
		StringBuffer sb = new StringBuffer();
		
		// 初始化分账费率
		HashMap<String, Double> rateMap = new HashMap<String, Double>();
		rateMap.put("xmRate", 0.3);// 寻密客费率
		rateMap.put("oneRate", 0.1);// 一级
		rateMap.put("twoRate", 0.1);// 二级

		for (int i = 1; i <= num; i++) {

			money = NumUtil.add(moneyDate, NumUtil.mul(data, i));

			// 分账计算
			resultMap = SplitUtil.saasLeger(rateMap, money, true);
			
			//MQ消息
			sb.delete(0, sb.length());
			sb.append("{");
			sb.append("\"orderId\":\"151001000021\",");//订单Id
			sb.append("\"sellerId\":\"886\",");//消费商家Id
			sb.append("\"sellerName\":\"味千拉面\",");//消费商家名称
			sb.append("\"xmerId\":\"121\",");//签约寻蜜客Id
			sb.append("\"xmerName\":\"签约达人\",");//签约寻蜜客名称
			sb.append("\"xmerBackMoney\":\""+rateMap.get(SplitUtil.SAAS_RETURN_MONEY_KEY).doubleValue()+"\",");//签约寻蜜客押金返还金额（存入押金）
			sb.append("\"xmerMoney\":\""+rateMap.get("xmRate").doubleValue()+"\",");//签约寻蜜客获得提成（存入佣金）
			sb.append("\"oneLevelXmerId\":\"234\",");//上线寻蜜客Id
			sb.append("\"oneLevelXmerName\":\"签约达人\",");//上线寻蜜客名称
			sb.append("\"oneLevelXmerMoney\":\""+rateMap.get("oneRate").doubleValue()+"\",");//上线寻蜜客获得提成（存入佣金）
			sb.append("\"twoLevelXmerId\":\"3345\",");//上上线寻蜜客Id


			sb.append("\"twoLevelXmerName\":\"签约达人\",");//上上线寻蜜客名称
			sb.append("\"twoLevelXmerMoney\":\""+rateMap.get("twoRate").doubleValue()+"\",");//上上线寻蜜客获得提成（存入佣金）
			sb.append("\"payType\":\"1000000\",");//支付类型
			sb.append("\"payCode\":\"123456789456123456789\",");//支付流水号
			sb.append("\"money\":\"360.00\",");//支付金额
			sb.append("\"payId\":\"16052716180000001\",");//支付ID
			sb.append("\"payment\":\"360.00\",");//第三方支付金额
			sb.append("\"sellerIndustry\":\"餐饮\",");//商户所在行业
			sb.append("\"sellerArea\":\"36\",");//商户所在区域
			sb.append("\"sellerAreaName\":\"36区\",");//商户所在区域名称
			sb.append("}");
			
			log.info("");
			log.info("-------------SAAS销售分账(第" + i + "次)--------------");
			log.info("分账结果 result=" + resultMap);
			log.info("redis队列消息="+sb.toString());
			log.info("-----------------------------------------------------");
			log.info("");
		}
	}

}
