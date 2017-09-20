package com.xmniao.xmn.core.util.ledger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * 
 * 项目名称：
 * 
 * 类名称：SplitUtil
 * 
 * 类描述： 分账计算
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2015年5月22日 下午7:19:55
 * 
 */
public class SplitUtil {

	/**
	 * 日志记录
	 */
	private static final Logger log = Logger.getLogger(SplitUtil.class);

	public static final double SAAS_PRICE = 360;// SAAS单价
	public static final String SAAS_RETURN_MONEY_KEY = "reMoney";// SAAS返还押金Key
	public static final String RESIDUE_MONEY_KEY = "residueMoney";// 分账后剩余金额key

	/**
	 * 分账
	 * 
	 * @param money
	 *            分账金额
	 * @param rateMap
	 *            分账费率
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Double> leger(Double money, HashMap<String, Double> rateMap) throws Exception {

		log.info("分账参数 分账金额=" + money + ",分账费率=" + rateMap);

		HashMap<String, Double> resultMap = new HashMap<String, Double>();

		double reteSum = 0, legerMoney = 0, sumLegerMoney = 0, residueMoney = 0;

		// 校验参数是否正常
		if (null == rateMap || rateMap.isEmpty()) {
			log.error("分账参数校验失败 分账费率不能为空 rateMap=" + rateMap);
			throw new Exception("分账参数校验失败 分账费率不能为空 rateMap=" + rateMap);
		}

		if (null == money || money.doubleValue() <= 0) {
			log.error("分账参数校验失败 分账费率不能小于零 money=" + money);
			throw new Exception("分账参数校验失败 分账费率不能小于零 money=" + money);
		}

		Iterator iterator = rateMap.entrySet().iterator();
		Map.Entry<String, Double> entry = null;

		String key = null;
		Double val = null;

		while (iterator.hasNext()) {
			entry = (Entry<String, Double>) iterator.next();
			key = entry.getKey();
			val = entry.getValue();

			if (null == val || val < 0 || val > 1) {
				log.error("分账参数校验失败 分账费率取值氛围[0,1] key=" + key + ",val=" + val.doubleValue());
				throw new Exception("分账参数校验失败 分账费率取值范围[0,1] key=" + key + ",val=" + val.doubleValue());
			}

			reteSum += val.doubleValue();

			if (reteSum > 1) {
				log.error("分账参数校验失败 分账费率大于100%  当前总费率=" + reteSum * 100 + "%");
				throw new Exception("分账参数校验失败 分账费率大于100%  当前总费率=" + reteSum * 100 + "%");
			}

			legerMoney = NumUtil.mul(money.doubleValue(), val.doubleValue());

			sumLegerMoney = NumUtil.add(sumLegerMoney, legerMoney);

			resultMap.put(key, legerMoney);
		}

		// 计算剩余部分
		residueMoney = NumUtil.sub(money.doubleValue(), sumLegerMoney);
		residueMoney = residueMoney < 0 ? 0 : residueMoney;
		resultMap.put(RESIDUE_MONEY_KEY, residueMoney);
		log.info("分账成功 result=" + resultMap);
		return resultMap;
	}

	/**
	 * 商品销售分账（生鲜）
	 * 
	 * @param purchaseMoney
	 *            采购价
	 * @param sellMoney
	 *            销售价
	 * @param rateMap
	 *            分账费率
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Double> freshLeger(Double purchaseMoney, Double sellMoney, HashMap<String, Double> rateMap) throws Exception {

		log.info("商品销售分账（生鲜） purchaseMoney=" + purchaseMoney + ",sellMoney=" + sellMoney + ",rateMap=" + rateMap);
		if (null == purchaseMoney || purchaseMoney.doubleValue() < 0) {
			log.error("商品销售分账（生鲜）参数校验失败 采购价大于等于零  当前采购价 purchaseMoney=" + purchaseMoney);
			throw new Exception("商品销售分账（生鲜）参数校验失败 采购价大于等于零  当前采购价 purchaseMoney=" + purchaseMoney);
		}

		if (null == sellMoney || sellMoney.doubleValue() < 0) {
			log.error("商品销售分账（生鲜）参数校验失败 销售价大于等于零  当前销售价 sellMoney=" + sellMoney);
			throw new Exception("商品销售分账（生鲜）参数校验失败 销售价大于等于零  当前销售价 sellMoney=" + sellMoney);
		}

		if (sellMoney.doubleValue() < purchaseMoney.doubleValue()) {
			log.error("商品销售分账（生鲜）参数校验失败 销售价小于采购价暂不利润可分");
			throw new Exception("商品销售分账（生鲜）参数校验失败 销售价小于采购价暂不利润可分");
		}

		HashMap<String, Double> resultMap = leger(sellMoney.doubleValue(), rateMap);
		// double residueMoney = resultMap.get(RESIDUE_MONEY_KEY).doubleValue();
		// residueMoney = NumUtil.add(residueMoney, purchaseMoney);
		// resultMap.remove(RESIDUE_MONEY_KEY);
		// resultMap.put(RESIDUE_MONEY_KEY, residueMoney);
		return resultMap;
	}

	/**
	 * 商品销售分账（商品）
	 * 
	 * @param purchaseMoney
	 *            采购价
	 * @param profitRate
	 *            利润率
	 * @param rateMap
	 *            分账费率
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Double> goodsLeger(Double purchaseMoney, Double profitRate, HashMap<String, Double> rateMap) throws Exception {

		if (null == purchaseMoney || purchaseMoney.doubleValue() < 0) {
			log.error("商品销售分账（商品）参数校验失败 采购价大于等于零  当前采购价 purchaseMoney=" + purchaseMoney);
			throw new Exception("商品销售分账（商品）参数校验失败 采购价大于等于零  当前采购价 purchaseMoney=" + purchaseMoney);
		}

		if (null == profitRate || profitRate.doubleValue() < 0) {
			log.error("商品销售分账（商品）参数校验失败 利润率大于等于零  当前利润率 profitRate=" + profitRate);
			throw new Exception("商品销售分账（商品）参数校验失败 利润率大于等于零  当前销售价 profitRate=" + profitRate);
		}

		double money = NumUtil.add(purchaseMoney, NumUtil.mul(purchaseMoney.doubleValue(), profitRate.doubleValue()));
		HashMap<String, Double> resultMap = leger(money, rateMap);
		double residueMoney = resultMap.get(RESIDUE_MONEY_KEY).doubleValue();
		residueMoney = NumUtil.sub(residueMoney, purchaseMoney.doubleValue());
		residueMoney = residueMoney < 0 ? 0 : residueMoney;
		resultMap.remove(RESIDUE_MONEY_KEY);
		resultMap.put(RESIDUE_MONEY_KEY, residueMoney);
		return resultMap;
	}

	/**
	 * SAAS销售分账
	 * 
	 * @param rateMap
	 *            分账费率
	 * @param saasPricee
	 *            SAAS单价
	 * @param isReturn
	 *            是否退还押金
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Double> saasLeger(HashMap<String, Double> rateMap, Double saasPricee, Boolean isReturn) throws Exception {

		double money = SAAS_PRICE, reMoney = SAAS_PRICE;

		if (null != saasPricee) {
			money = saasPricee;
		}

		HashMap<String, Double> resultMap = leger(money, rateMap);

		if (null == isReturn || !isReturn) {
			reMoney = 0;
		}

		resultMap.put(SAAS_RETURN_MONEY_KEY, reMoney);
		return resultMap;
	}

	public static void main(String[] args) throws Exception {

		double initMoney = 0.01;
		double money = 100;

		HashMap<String, Double> resultMap = null;

		// 初始化分账费率
		HashMap<String, Double> rateMap = new HashMap<String, Double>();
		rateMap.put("shRate", 0.01);// 商户费率
		rateMap.put("zmRate", 0.01);// 中脉费率

		// 分账计算
		resultMap = leger(money, rateMap);
		log.info("");
		log.info("-----------------分账-----------------");
		log.info("分账结果 result=" + resultMap);
		log.info("--------------------------------------");
		log.info("");

		resultMap.clear();
		resultMap = null;
		rateMap.clear();
		rateMap.put("shRate", 0.01);// 商户费率
		rateMap.put("sysRate", 0.02);// 平台费率
		resultMap = freshLeger(8000.00, 8800.00, rateMap);
		log.info("");
		log.info("-----------------商品销售分账（生鲜）-----------------");
		log.info("分账结果 result=" + resultMap);
		log.info("--------------------------------------");
		log.info("");

		resultMap.clear();
		resultMap = null;
		rateMap.clear();
		rateMap.put("shRate", 0.01);// 商户费率
		rateMap.put("zmRate", 0.01);// 中脉费率
		resultMap = goodsLeger(30.00, 0.05, rateMap);
		log.info("");
		log.info("-----------------商品销售分账（商品）-----------------");
		log.info("分账结果 result=" + resultMap);
		log.info("--------------------------------------");
		log.info("");

		resultMap.clear();
		resultMap = null;
		rateMap.clear();
		rateMap.put("xmRate", 0.3);// 寻密客费率
		rateMap.put("oneRate", 0.1);// 一级
		rateMap.put("twoRate", 0.1);// 二级

		resultMap = saasLeger(rateMap, null, true);
		log.info("");
		log.info("-----------------SAAS销售分账(用户寻蜜客)-----------------");
		log.info("分账结果 result=" + resultMap);
		log.info("--------------------------------------");
		log.info("");

		resultMap.clear();
		resultMap = null;
		rateMap.clear();
		rateMap.put("zmRate", 0.7);// 中脉费率
		rateMap.put("sysRate", 0.3);// 平台费率

		resultMap = saasLeger(rateMap, null, true);
		log.info("");
		log.info("-----------------SAAS销售分账(中脉寻蜜客	)-----------------");
		log.info("分账结果 result=" + resultMap);
		log.info("--------------------------------------");
		log.info("");

		resultMap.clear();
		resultMap = null;
		rateMap.clear();
		rateMap.put("jxsRate", 0.2);// 经销商费率
		rateMap.put("xmRate", 0.1);// 寻密客费率
		rateMap.put("oneRate", 0.05);// 一级寻密客费率
		rateMap.put("twoRate", 0.05);// 二级寻密客费率

		resultMap = leger(money, rateMap);
		log.info("");
		log.info("-----------------增值收益分账-----------------");
		log.info("分账结果 result=" + resultMap);
		log.info("--------------------------------------");
		log.info("");
	}

}
