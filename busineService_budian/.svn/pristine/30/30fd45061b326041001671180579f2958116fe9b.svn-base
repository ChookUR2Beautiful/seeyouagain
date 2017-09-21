package com.xmniao.service.ledger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.dao.xmer.SaasOrderDao;
import com.xmniao.domain.ledger.LedgerConstants;
import com.xmniao.domain.ledger.LedgerMixBean;
import com.xmniao.domain.ledger.LedgerNewBean;
import com.xmniao.domain.ledger.LiveLedgerBean;
import com.xmniao.domain.ledger.SellerPackageLedger;
import com.xmniao.domain.order.BillBean;


/**
 * 分账算法实现工具类
 * @author  huangxiaobin
 * @version  [版本号, 2016年05月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("unused")
public class LedgerAlgorithmUtils {
	


	/**
	 * 初始日志类
	 */
	
	private Logger log = LoggerFactory.getLogger(LedgerAlgorithmUtils.class);


	/**
	 * 根据传入的参数，进行分账计算
	 * @param bean [请求参数]
	 * @return map [分账结果，包含各项收益及分账比例]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static Map<String,Object> getLedgerMoney(LedgerNewBean bean) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		JSONObject jsonObject = new JSONObject();

		try {
			// 订单金额100
			double orderMoney = bean.getOrderMoney();
			
			// 商户折扣0.8
			double baseagio = bean.getBaseagio();
			
			// 分账比例0.5
			double ledgerRatio = bean.getLedgerRatio();
			// 分账金额
			double ledgerMoney = orderMoney;		// 默认为订单金额
			
			// 商户营收金额 = 订单金额 * 商户折扣
			double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
			
			// 用户立减比例 = 1-商户折扣 - 分账比例
			double userRatio = PreciseComputeUtil.sub(1,baseagio,ledgerRatio);
			// 商户折扣  > 0.95 
			if(baseagio >= 0.95){
				
				ledgerRatio = PreciseComputeUtil.sub(1,baseagio);
				// 分账金额 = 订单金额 - 商户营收金额
				ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
				// 用户立减比例 = (1-商户折扣) * 50%
				userRatio = PreciseComputeUtil.mul(PreciseComputeUtil.sub(1, baseagio),0.5,3);
				// 经销商分账比例设为5%
				bean.setConsumeJointRatio(0.05);
				// 商户店外收益比例设为10%
				bean.setSellerRatio(0.1);
				// 寻蜜客分账比例设为10%
				bean.setMikeRatio(0.1);
				// 一级分账比例设为5%
				bean.setParentMikeRatio(0.05);
				// 二级寻蜜客分账比例设为5%
				bean.setTopMikeRatio(0.05);
				
			}
			
			// 订单金额小于5分 
			if(orderMoney < 0.05){
				
//				ledgerRatio = PreciseComputeUtil.sub(1,baseagio);
//				// 分账金额 = 订单金额 - 商户营收金额
//				ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
//				// 用户立减比例 = (1-商户折扣) * 50%
				userRatio = 0.00;
				
				sellerAmount = 0.00;
				// 经销商分账比例设为0%
				bean.setConsumeJointRatio(0.00);
				// 商户店外收益比例设为0%
				bean.setSellerRatio(0.00);
				// 寻蜜客分账比例设为0%
				bean.setMikeRatio(0.00);
				// 一级分账比例设为0%
				bean.setParentMikeRatio(0.00);
				// 二级寻蜜客分账比例设为0%
				bean.setTopMikeRatio(0.00);
				
			}
			
			
			returnMap.put("ledgerMoney", ledgerMoney);
			returnMap.put("ledgerRatio",  PreciseComputeUtil.mul(ledgerRatio, 100,2));
			// 用户立减金额金额 = 订单金额  * 用户立减比例
//			if(bean.getLedgerMode()==1||bean.getLedgerMode()==2){
//				userRatio=0.00;	
//			}
//			if(!bean.isBreduction()){
//				userRatio=0.00;	
//			}
			userRatio=0.00;	
			double user_money = PreciseComputeUtil.round(PreciseComputeUtil.mul(orderMoney, userRatio),2);
			returnMap.put("user_money",  user_money);
			
			// 用户立实际支付金额 =  订单金额 - 用户实际支付金额
			double userMoney = PreciseComputeUtil.sub(orderMoney,user_money);
			// 用户实际支付金额
			returnMap.put("userAmount",  userMoney);
			// 用户返利积分  = 用户实际支付金额(四舍五入，取整)
			returnMap.put("userMoney",  PreciseComputeUtil.keepTwoPointStr(userMoney,2));
			
			// 寻蜜客分账金额  = 订单总额  * 寻蜜客分账比例 ( 1%) 
			double mike_amount = PreciseComputeUtil.mul(ledgerMoney, bean.getMikeRatio(), 2);
			mike_amount = bean.getMikeId()==null?0:mike_amount;
			
			// 上级寻蜜客分账金额 = 订单总额 * 上级寻蜜客分账比例（1%）
			double parent_mike_amount = PreciseComputeUtil.mul(ledgerMoney,bean.getParentMikeRatio(),2);
			
			// 上上级寻蜜客分账金额 = 订单总额 * 上上级寻蜜客分账比例（1%）
			double top_mike_amount = PreciseComputeUtil.mul(ledgerMoney,bean.getTopMikeRatio(),2);


			double otherAmount = 0d;				// 用于存放中脉 上、上上级收益
			// 中脉寻蜜客的上级寻蜜客、上上级寻蜜客 收益归平台
			if(2 == bean.getMikeType() ){
				otherAmount = PreciseComputeUtil.add(parent_mike_amount, top_mike_amount);
				parent_mike_amount = 0d;
				top_mike_amount = 0d;
			}else{
				// 上上级寻蜜客为空
				top_mike_amount = null == bean.getTopMikeId()? 0 :top_mike_amount;
				// 上级寻蜜客为空
				if(null == bean.getParentMikeId()){
					parent_mike_amount = 0;
					top_mike_amount = 0;
				}
			}

			// 商户店外收益（原商户寻蜜客） = 订单总额  * 商户店外收益比例（1%）
			double seller_money = PreciseComputeUtil.mul(ledgerMoney,bean.getSellerRatio(),2);
			seller_money = bean.getGenusSellerId()==null?0:seller_money;
			
			// 经销商 (原消费合作商) = 订单总额*  经销商分账比例(1%)
			double cpartner_amount = PreciseComputeUtil.mul(ledgerMoney, bean.getConsumeJointRatio(), 2);
			cpartner_amount =  null == bean.getConsumeJointid()?0:cpartner_amount;

			double feeAmount = 0;
			if(bean.getFeeRatio()>0 && sellerAmount>0){
				//平台扣除商家手续费
				feeAmount =  PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, bean.getFeeRatio()), 2);
				sellerAmount = PreciseComputeUtil.sub(sellerAmount,feeAmount);
				if(sellerAmount<0){
					sellerAmount = 0.0;
				}
			}
			
			
			// 平台分账金额 = 订单金额 - 商户营收金额 - 用户立减金额 - 寻蜜客分账金额- 上级寻蜜客分账金额 - 上上级寻蜜客分账金额  - 商户店外收益  - 经销商分账金额 
			double platform_amount = PreciseComputeUtil.sub(orderMoney,sellerAmount,user_money,
					mike_amount,parent_mike_amount,top_mike_amount,seller_money,cpartner_amount,otherAmount);
			
			StringBuffer proportion = new StringBuffer("");
			proportion.append(userRatio).append(",");									  	// 用户立减比例
			proportion.append(PreciseComputeUtil.keepTwoPointStr(baseagio,2)).append(",");	// 商户折扣
			
			proportion.append(PreciseComputeUtil.keepTwoPointStr(
					PreciseComputeUtil.add(bean.getMikeRatio(), 
							bean.getParentMikeRatio(), 
							bean.getTopMikeRatio()),2)).append(",");												    // 寻蜜客总分账比例 = 寻蜜客分账比例 + 上级寻蜜客分账比例  + 上上级寻蜜客分账比例  
			proportion.append(bean.getConsumeJointRatio()).append(",");													// 经销商分账比例 
			proportion.append(PreciseComputeUtil.div(platform_amount, orderMoney,2)).append("");						// 平台分账比例 = 平台分账金额  / 订单金额 
			returnMap.put("proportion", proportion.toString());															// 分账比例 格式  会员,商家,寻蜜客,合作商(),平台（废弃）
			
			jsonObject.put(LedgerConstants.SELLER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(sellerAmount, 2));  		// 商户营收金额
			
			
			jsonObject.put(LedgerConstants.JOINT_BPARTNER, PreciseComputeUtil.keepTwoPointStr(bean.getSellerRatio(), 2)); // 商户店外收益 比例 
			jsonObject.put(LedgerConstants.SELLER_MONEY, PreciseComputeUtil.keepTwoPointStr(seller_money, 2));	    	  // 商户店外收益 (新增)

			jsonObject.put(LedgerConstants.USERMONEY,
					PreciseComputeUtil.keepTwoPointStr(userMoney, 2)); 					// 用户返利积分(用户实际支付金额)

			jsonObject.put(LedgerConstants.MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(mike_amount, 2));			// 寻蜜客额外分账收益(寻蜜客应收分账金额)

			jsonObject.put(LedgerConstants.PARENT_MIKE_AMOUNT,
					PreciseComputeUtil.keepTwoPointStr(parent_mike_amount, 2));											// 寻蜜客上级分账金额	 (新增)
			jsonObject.put(LedgerConstants.TOP_MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(top_mike_amount, 2));	// 寻蜜客上上级分账金额  (新增)

			jsonObject.put(LedgerConstants.CPARTNER_AMOUNT,PreciseComputeUtil.keepTwoPointStr(cpartner_amount, 2) );	// 经销商分账金额(消费区域合作商分账金额)
			
			jsonObject.put(LedgerConstants.OTHER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(otherAmount, 2));	// 中脉分账金额
			
			jsonObject.put(LedgerConstants.PLATFORM_AMOUNT, PreciseComputeUtil.keepTwoPointStr(platform_amount, 2));	// 平台分账金额
			
			jsonObject.put(LedgerConstants.REDUCTION, PreciseComputeUtil.keepTwoPointStr(user_money, 2));	// 用户立减金额
			
			jsonObject.put("proportion",proportion.toString());

			/********************************* 以下参数以后优化时可移除，但注意各系统对接时也需要移除以下字段的获取 *******************************************/

			jsonObject.put("fees_money", "0.00");				// 平台扣款手续费总额(由合作商 用户 平台分摊) (废弃)

			jsonObject.put("mike_fees", "0.00");				// 寻蜜客应付渠道手续费 (废弃)
			jsonObject.put("mike_money", "0.00");				// 寻蜜客应收分账金额 (废弃)

			jsonObject.put("joint_bpartner","0.00");  			// 用户区域合作商分账比例，未跨区默认为0      (废弃)
			jsonObject.put("bpartner_money", "0.00");		  	// 用户区域合作商分账金额，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_fees","0.00" );			// 用户区域应付渠道手续费，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_amount","0.00" );			// 用户区域合作商应收分账金额,未跨区默认为0   (废弃)
			jsonObject.put("memberJointMoney", "0.00"); 		// 用户区域合作商业务员分账金额，未跨区默认为0 (废弃)

			jsonObject.put("cpartner_bpartner", "0.00");		// 消费区域合作商分账比例				(废弃)
			jsonObject.put("cpartner_money", "0.00");			// 消费区域合作商应收分账金额			(废弃)
			jsonObject.put("cpartner_fees", "0.00");			// 消费区域应付渠道手续费				(废弃)
			jsonObject.put("consumeJointidMoney","0.00" ); 		// 消费区域合作商业务员分账金额 		(废弃)

			jsonObject.put("subsidy_money", "0.00");			// 平台补贴金额(平台做活动时补贴给用户的返利金额)(暂废)
			jsonObject.put("platform_fees", "0.00");			// 平台应付渠道手续费   (废弃)
			jsonObject.put("platform_money","0.00");			// 平台应收分账金额（废弃)
			
			returnMap.put("commission",jsonObject);
		} catch (Exception e) {
			throw e;
		}
		return returnMap;
	}


	/**
	 * 直播订单分账
	 * 特点：只给商家与平台分账
	 * @Title: getSimpleDivideLedger 
	 * @Description:
	 */
	public static Map<String,Object> getLiveLedgerMoney(LedgerNewBean bean) {

		Map<String,Object> returnMap = new HashMap<String,Object>();
		JSONObject jsonObject = new JSONObject();

		try {
			bean.setMikeRatio(0);
			bean.setParentMikeRatio(0);
			bean.setTopMikeRatio(0);
			bean.setConsumeJointRatio(0);
			bean.setSellerRatio(0);
			// 参与分账总金额
			double orderMoney = bean.getOrderMoney();
			double sellerExtraMoney = bean.getSellerExtraMoney();
			// 分账比例
			double baseagio = bean.getBaseagio();
			
			// 分账比例0.5
			double ledgerRatio = bean.getLedgerRatio();
			
			// 分账金额
			double ledgerMoney = PreciseComputeUtil.add(orderMoney, sellerExtraMoney);		// 默认为订单金额
			
			// 商户营收分账
			double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
			
			//用户实付金额
			double userMoney = bean.getUserMoney();
			
			// 用户立减比例 = 1-商户折扣 - 分账比例
			double userRatio = 0;
			
			returnMap.put("ledgerMoney", ledgerMoney);
			returnMap.put("ledgerRatio",  PreciseComputeUtil.mul(ledgerRatio, 100,2));

			double user_money = PreciseComputeUtil.round(PreciseComputeUtil.mul(orderMoney, userRatio),2);
			returnMap.put("user_money",  user_money);
			
			// 用户立实际支付金额 =  订单金额 - 用户实际支付金额
			//double userMoney = PreciseComputeUtil.sub(orderMoney,user_money);
			// 用户实际支付金额
			returnMap.put("userAmount",  userMoney);
			// 用户返利积分  = 用户实际支付金额(四舍五入，取整)
			returnMap.put("userMoney",  PreciseComputeUtil.keepTwoPointStr(userMoney,2));
			
			// 寻蜜客分账金额  = 订单总额  * 寻蜜客分账比例 ( 1%) 
			double mike_amount = PreciseComputeUtil.mul(ledgerMoney, bean.getMikeRatio(), 2);
			mike_amount = bean.getMikeId()==null?0:mike_amount;
			
			// 上级寻蜜客分账金额 = 订单总额 * 上级寻蜜客分账比例（1%）
			double parent_mike_amount = PreciseComputeUtil.mul(ledgerMoney,bean.getParentMikeRatio(),2);
			
			// 上上级寻蜜客分账金额 = 订单总额 * 上上级寻蜜客分账比例（1%）
			double top_mike_amount = PreciseComputeUtil.mul(ledgerMoney,bean.getTopMikeRatio(),2);


			double otherAmount = 0d;				// 用于存放中脉 上、上上级收益
			// 中脉寻蜜客的上级寻蜜客、上上级寻蜜客 收益归平台
			if(2 == bean.getMikeType() ){
				otherAmount = PreciseComputeUtil.add(parent_mike_amount, top_mike_amount);
				parent_mike_amount = 0d;
				top_mike_amount = 0d;
			}else{
				// 上上级寻蜜客为空
				top_mike_amount = bean.getTopMikeId()==null? 0 :top_mike_amount;
				// 上级寻蜜客为空
				if(bean.getParentMikeId()==null){
					parent_mike_amount = 0;
					top_mike_amount = 0;
				}
			}

			// 商户店外收益（原商户寻蜜客） = 订单总额  * 商户店外收益比例（1%）
			double seller_money = PreciseComputeUtil.mul(ledgerMoney,bean.getSellerRatio(),2);
			seller_money = bean.getGenusSellerId()==null?0:seller_money;
			
			// 经销商 (原消费合作商) = 订单总额*  经销商分账比例(1%)
			double cpartner_amount = PreciseComputeUtil.mul(ledgerMoney, bean.getConsumeJointRatio(), 2);
			cpartner_amount = bean.getConsumeJointid()==null?0:cpartner_amount;

			// 平台分账金额 = 订单金额 - 商户营收金额 - 用户立减金额 - 寻蜜客分账金额- 上级寻蜜客分账金额 - 上上级寻蜜客分账金额  - 商户店外收益  - 经销商分账金额 
			double platform_amount = PreciseComputeUtil.sub(orderMoney,sellerAmount,user_money,
					mike_amount,parent_mike_amount,top_mike_amount,seller_money,cpartner_amount,otherAmount);
			
			sellerAmount = PreciseComputeUtil.add(sellerAmount,sellerExtraMoney);
			
			StringBuffer proportion = new StringBuffer("");
			proportion.append(PreciseComputeUtil.keepTwoPointStr(userRatio,2)).append(",");									  	// 用户立减比例
			proportion.append(PreciseComputeUtil.keepTwoPointStr(baseagio,2)).append(",");	// 商户折扣
			
			proportion.append(PreciseComputeUtil.keepTwoPointStr(
					PreciseComputeUtil.add(bean.getMikeRatio(), 
							bean.getParentMikeRatio(), 
							bean.getTopMikeRatio()),2)).append(",");												    // 寻蜜客总分账比例 = 寻蜜客分账比例 + 上级寻蜜客分账比例  + 上上级寻蜜客分账比例  
			proportion.append(PreciseComputeUtil.keepTwoPointStr(bean.getConsumeJointRatio(),2)).append(",");													// 经销商分账比例 
			double platformRatio = PreciseComputeUtil.sub(1, userRatio,baseagio,bean.getMikeRatio(),bean.getParentMikeRatio(),bean.getTopMikeRatio());
			platformRatio = platformRatio<0?0:platformRatio;
			proportion.append(PreciseComputeUtil.keepTwoPointStr(platformRatio,2/*PreciseComputeUtil.div(platform_amount, orderMoney,2),2*/)).append("");						// 平台分账比例 = 平台分账金额  / 订单金额 
			returnMap.put("proportion", proportion.toString());															// 分账比例 格式  会员,商家,寻蜜客,合作商(),平台（废弃）
			
			jsonObject.put(LedgerConstants.SELLER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(sellerAmount, 2));  		// 商户营收金额
			
			
			jsonObject.put(LedgerConstants.JOINT_BPARTNER, PreciseComputeUtil.keepTwoPointStr(bean.getSellerRatio(), 2)); // 商户店外收益 比例 
			jsonObject.put(LedgerConstants.SELLER_MONEY, PreciseComputeUtil.keepTwoPointStr(seller_money, 2));	    	  // 商户店外收益 (新增)

			jsonObject.put(LedgerConstants.USERMONEY,
					PreciseComputeUtil.keepTwoPointStr(userMoney, 2)); 					// 用户返利积分(用户实际支付金额)

			jsonObject.put(LedgerConstants.MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(mike_amount, 2));			// 寻蜜客额外分账收益(寻蜜客应收分账金额)

			jsonObject.put(LedgerConstants.PARENT_MIKE_AMOUNT,
					PreciseComputeUtil.keepTwoPointStr(parent_mike_amount, 2));											// 寻蜜客上级分账金额	 (新增)
			jsonObject.put(LedgerConstants.TOP_MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(top_mike_amount, 2));	// 寻蜜客上上级分账金额  (新增)

			jsonObject.put(LedgerConstants.CPARTNER_AMOUNT,PreciseComputeUtil.keepTwoPointStr(cpartner_amount, 2) );	// 经销商分账金额(消费区域合作商分账金额)
			
			jsonObject.put(LedgerConstants.OTHER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(otherAmount, 2));	// 中脉分账金额
			
			jsonObject.put(LedgerConstants.PLATFORM_AMOUNT, PreciseComputeUtil.keepTwoPointStr(platform_amount, 2));	// 平台分账金额
			
			jsonObject.put(LedgerConstants.REDUCTION, PreciseComputeUtil.keepTwoPointStr(user_money, 2));	// 用户立减金额
			
			jsonObject.put("proportion",proportion.toString());

			/********************************* 以下参数以后优化时可移除，但注意各系统对接时也需要移除以下字段的获取 *******************************************/

			jsonObject.put("fees_money", "0.00");				// 平台扣款手续费总额(由合作商 用户 平台分摊) (废弃)

			jsonObject.put("mike_fees", "0.00");				// 寻蜜客应付渠道手续费 (废弃)
			jsonObject.put("mike_money", "0.00");				// 寻蜜客应收分账金额 (废弃)

			jsonObject.put("joint_bpartner","0.00");  			// 用户区域合作商分账比例，未跨区默认为0      (废弃)
			jsonObject.put("bpartner_money", "0.00");		  	// 用户区域合作商分账金额，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_fees","0.00" );			// 用户区域应付渠道手续费，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_amount","0.00" );			// 用户区域合作商应收分账金额,未跨区默认为0   (废弃)
			jsonObject.put("memberJointMoney", "0.00"); 		// 用户区域合作商业务员分账金额，未跨区默认为0 (废弃)

			jsonObject.put("cpartner_bpartner", "0.00");		// 消费区域合作商分账比例				(废弃)
			jsonObject.put("cpartner_money", "0.00");			// 消费区域合作商应收分账金额			(废弃)
			jsonObject.put("cpartner_fees", "0.00");			// 消费区域应付渠道手续费				(废弃)
			jsonObject.put("consumeJointidMoney","0.00" ); 		// 消费区域合作商业务员分账金额 		(废弃)

			jsonObject.put("subsidy_money", "0.00");			// 平台补贴金额(平台做活动时补贴给用户的返利金额)(暂废)
			jsonObject.put("platform_fees", "0.00");			// 平台应付渠道手续费   (废弃)
			jsonObject.put("platform_money","0.00");			// 平台应收分账金额（废弃)
			returnMap.put("commission",jsonObject);
		} catch (Exception e) {
			throw e;
		}
		return returnMap;
	
	}


	/**
	 * 直播-常规订单组合分账
	 * 订单分账分两部分处理：粉丝券抵用金额部分按直播分账方式，超出粉丝券抵用金额部分按常规分账方式
	 * 分账方式：采用直播分账优化原则
	 * 若该订单使用了直播分账，那么记录各角色的分账比例采用直播的分账比例记录，各角色的分账金额为分账金额按前面描述的分账方式进行分账
	 * 总分账金额为直播分账直播分账总金额+常规分账总金额
	 * 
	 * @Title: getSimpleDivideLedger 
	 * @Description:
	 */
	public static Map<String,Object> getLiveMixLedgerMoney(LedgerMixBean bean) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		JSONObject jsonObject = new JSONObject();

		try {
			double liveSellerAmount = 0;
			double livePlatformAmount = 0;
			double liveAllAmount = bean.getLiveLedgerMoney();
			double liveSellerRatio = bean.getLiveLedgerRatio();
			if(liveAllAmount>0){
				liveSellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(liveAllAmount, liveSellerRatio), 2);
				livePlatformAmount = PreciseComputeUtil.sub(liveAllAmount,liveSellerAmount);
			}
			
			// 订单金额100
			double orderMoney = bean.getOrderMoney();
			
			// 商户折扣0.8
			double baseagio = bean.getBaseagio();
			
			// 分账比例0.5
			double ledgerRatio = bean.getLedgerRatio();
			// 分账金额
			double ledgerMoney = orderMoney;		// 默认为订单金额
			
			// 商户营收金额 = 订单金额 * 商户折扣
			double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
			
			// 用户立减比例 = 1-商户折扣 - 分账比例
			double userRatio = PreciseComputeUtil.sub(1,baseagio,ledgerRatio);
			// 商户折扣  > 0.95 
			if(baseagio >= 0.95){
				
				ledgerRatio = PreciseComputeUtil.sub(1,baseagio);
				// 分账金额 = 订单金额 - 商户营收金额
				ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
				// 用户立减比例 = (1-商户折扣) * 50%
				userRatio = PreciseComputeUtil.mul(PreciseComputeUtil.sub(1, baseagio),0.5,3);
				// 经销商分账比例设为5%
				bean.setConsumeJointRatio(0.05);
				// 商户店外收益比例设为10%
				bean.setSellerRatio(0.1);
				// 寻蜜客分账比例设为10%
				bean.setMikeRatio(0.1);
				// 一级分账比例设为5%
				bean.setParentMikeRatio(0.05);
				// 二级寻蜜客分账比例设为5%
				bean.setTopMikeRatio(0.05);
				
			}
			
			// 订单金额小于5分 
			if(orderMoney < 0.05){
				
//				ledgerRatio = PreciseComputeUtil.sub(1,baseagio);
//				// 分账金额 = 订单金额 - 商户营收金额
//				ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
//				// 用户立减比例 = (1-商户折扣) * 50%
				userRatio = 0.00;
				
				sellerAmount = 0.00;
				// 经销商分账比例设为0%
				bean.setConsumeJointRatio(0.00);
				// 商户店外收益比例设为0%
				bean.setSellerRatio(0.00);
				// 寻蜜客分账比例设为0%
				bean.setMikeRatio(0.00);
				// 一级分账比例设为0%
				bean.setParentMikeRatio(0.00);
				// 二级寻蜜客分账比例设为0%
				bean.setTopMikeRatio(0.00);
				
			}
			
			
			returnMap.put("ledgerMoney", PreciseComputeUtil.add(orderMoney,liveAllAmount));
			returnMap.put("ledgerRatio",  PreciseComputeUtil.mul(ledgerRatio, 100,2));
			// 用户立减金额金额 = 订单金额  * 用户立减比例
			if(bean.getLedgerMode()==1||bean.getLedgerMode()==2){
				userRatio=0.00;	
			}
			userRatio=0.00;	
			double user_money = PreciseComputeUtil.round(PreciseComputeUtil.mul(orderMoney, userRatio),2);
			returnMap.put("user_money",  user_money);
			
			// 用户立实际支付金额 =  订单金额 - 用户实际支付金额
			double userMoney = PreciseComputeUtil.sub(orderMoney,user_money);
			// 用户实际支付金额
			returnMap.put("userAmount",  userMoney);
			// 用户返利积分  = 用户实际支付金额(四舍五入，取整)
			returnMap.put("userMoney",  PreciseComputeUtil.keepTwoPointStr(userMoney,2));
			
			// 寻蜜客分账金额  = 订单总额  * 寻蜜客分账比例 ( 1%) 
			double mike_amount = PreciseComputeUtil.mul(ledgerMoney, bean.getMikeRatio(), 2);
			mike_amount = bean.getMikeId()==null?0:mike_amount;
			
			// 上级寻蜜客分账金额 = 订单总额 * 上级寻蜜客分账比例（1%）
			double parent_mike_amount = PreciseComputeUtil.mul(ledgerMoney,bean.getParentMikeRatio(),2);
			
			// 上上级寻蜜客分账金额 = 订单总额 * 上上级寻蜜客分账比例（1%）
			double top_mike_amount = PreciseComputeUtil.mul(ledgerMoney,bean.getTopMikeRatio(),2);


			double otherAmount = 0d;				// 用于存放中脉 上、上上级收益
			// 中脉寻蜜客的上级寻蜜客、上上级寻蜜客 收益归平台
			if(2 == bean.getMikeType() ){
				otherAmount = PreciseComputeUtil.add(parent_mike_amount, top_mike_amount);
				parent_mike_amount = 0d;
				top_mike_amount = 0d;
			}else{
				// 上上级寻蜜客为空
				top_mike_amount = null == bean.getTopMikeId()? 0 :top_mike_amount;
				// 上级寻蜜客为空
				if(null == bean.getParentMikeId()){
					parent_mike_amount = 0;
					top_mike_amount = 0;
				}
			}

			// 商户店外收益（原商户寻蜜客） = 订单总额  * 商户店外收益比例（1%）
			double seller_money = PreciseComputeUtil.mul(ledgerMoney,bean.getSellerRatio(),2);
			seller_money = bean.getGenusSellerId()==null?0:seller_money;
			
			// 经销商 (原消费合作商) = 订单总额*  经销商分账比例(1%)
			double cpartner_amount = PreciseComputeUtil.mul(ledgerMoney, bean.getConsumeJointRatio(), 2);
			cpartner_amount =  null == bean.getConsumeJointid()?0:cpartner_amount;
			
			double feeAmount = 0;
			if(bean.getFeeRatio()>0 && sellerAmount>0){
				//平台扣除商家手续费
				feeAmount =  PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, bean.getFeeRatio()), 2);
				sellerAmount = PreciseComputeUtil.sub(sellerAmount,feeAmount);
				if(sellerAmount<0){
					sellerAmount = 0.0;
				}
			}
			
			// 平台分账金额 = 订单金额 - 商户营收金额 - 用户立减金额 - 寻蜜客分账金额- 上级寻蜜客分账金额 - 上上级寻蜜客分账金额  - 商户店外收益  - 经销商分账金额 
			double platform_amount = PreciseComputeUtil.sub(orderMoney,sellerAmount,user_money,
					mike_amount,parent_mike_amount,top_mike_amount,seller_money,cpartner_amount,otherAmount);

			double mikeRatio=bean.getMikeRatio();
			double parentMikeRatio=bean.getParentMikeRatio();
			double topMikeRatio=bean.getTopMikeRatio();
			double consumeJointRatio=bean.getConsumeJointRatio();
			double sellerRatio=bean.getSellerRatio();
			double platformRatio = PreciseComputeUtil.div(platform_amount, orderMoney,2);
			if(bean.getLiveLedgerMoney()>0){
				userRatio=0;
				baseagio=bean.getLiveLedgerRatio();
				mikeRatio=0;
				parentMikeRatio=0;
				topMikeRatio=0;
				consumeJointRatio=0;
				sellerRatio=0;
				platformRatio=PreciseComputeUtil.sub(1,baseagio);
			}
			StringBuffer proportion = new StringBuffer("");
			proportion.append(PreciseComputeUtil.keepTwoPointStr(userRatio,2)).append(",");									  	// 用户立减比例
			proportion.append(PreciseComputeUtil.keepTwoPointStr(baseagio,2)).append(",");	// 商户折扣
			
			proportion.append(PreciseComputeUtil.keepTwoPointStr(
					PreciseComputeUtil.add(mikeRatio, 
							parentMikeRatio, 
							topMikeRatio),2)).append(",");												    // 寻蜜客总分账比例 = 寻蜜客分账比例 + 上级寻蜜客分账比例  + 上上级寻蜜客分账比例  
			proportion.append(PreciseComputeUtil.keepTwoPointStr(consumeJointRatio,2)).append(",");													// 经销商分账比例 
			proportion.append(PreciseComputeUtil.keepTwoPointStr(platformRatio,2)).append("");						// 平台分账比例 = 平台分账金额  / 订单金额 
			returnMap.put("proportion", proportion.toString());															// 分账比例 格式  会员,商家,寻蜜客,合作商(),平台（废弃）
			
			jsonObject.put(LedgerConstants.SELLER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(PreciseComputeUtil.add(sellerAmount,liveSellerAmount), 2));  		// 商户营收金额
			
			
			jsonObject.put(LedgerConstants.JOINT_BPARTNER, PreciseComputeUtil.keepTwoPointStr(bean.getSellerRatio(), 2)); // 商户店外收益 比例 
			jsonObject.put(LedgerConstants.SELLER_MONEY, PreciseComputeUtil.keepTwoPointStr(seller_money, 2));	    	  // 商户店外收益 (新增)

			jsonObject.put(LedgerConstants.USERMONEY,
					PreciseComputeUtil.keepTwoPointStr(userMoney, 2)); 					// 用户返利积分(用户实际支付金额)

			jsonObject.put(LedgerConstants.MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(mike_amount, 2));			// 寻蜜客额外分账收益(寻蜜客应收分账金额)

			jsonObject.put(LedgerConstants.PARENT_MIKE_AMOUNT,
					PreciseComputeUtil.keepTwoPointStr(parent_mike_amount, 2));											// 寻蜜客上级分账金额	 (新增)
			jsonObject.put(LedgerConstants.TOP_MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(top_mike_amount, 2));	// 寻蜜客上上级分账金额  (新增)

			jsonObject.put(LedgerConstants.CPARTNER_AMOUNT,PreciseComputeUtil.keepTwoPointStr(cpartner_amount, 2) );	// 经销商分账金额(消费区域合作商分账金额)
			
			jsonObject.put(LedgerConstants.OTHER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(otherAmount, 2));	// 中脉分账金额
			
			jsonObject.put(LedgerConstants.PLATFORM_AMOUNT, PreciseComputeUtil.keepTwoPointStr(PreciseComputeUtil.add(platform_amount,livePlatformAmount), 2));	// 平台分账金额
			
			jsonObject.put(LedgerConstants.REDUCTION, PreciseComputeUtil.keepTwoPointStr(user_money, 2));	// 用户立减金额
			
			jsonObject.put("proportion",proportion.toString());

			/********************************* 以下参数以后优化时可移除，但注意各系统对接时也需要移除以下字段的获取 *******************************************/

			jsonObject.put("fees_money", "0.00");				// 平台扣款手续费总额(由合作商 用户 平台分摊) (废弃)

			jsonObject.put("mike_fees", "0.00");				// 寻蜜客应付渠道手续费 (废弃)
			jsonObject.put("mike_money", "0.00");				// 寻蜜客应收分账金额 (废弃)

			jsonObject.put("joint_bpartner","0.00");  			// 用户区域合作商分账比例，未跨区默认为0      (废弃)
			jsonObject.put("bpartner_money", "0.00");		  	// 用户区域合作商分账金额，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_fees","0.00" );			// 用户区域应付渠道手续费，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_amount","0.00" );			// 用户区域合作商应收分账金额,未跨区默认为0   (废弃)
			jsonObject.put("memberJointMoney", "0.00"); 		// 用户区域合作商业务员分账金额，未跨区默认为0 (废弃)

			jsonObject.put("cpartner_bpartner", "0.00");		// 消费区域合作商分账比例				(废弃)
			jsonObject.put("cpartner_money", "0.00");			// 消费区域合作商应收分账金额			(废弃)
			jsonObject.put("cpartner_fees", "0.00");			// 消费区域应付渠道手续费				(废弃)
			jsonObject.put("consumeJointidMoney","0.00" ); 		// 消费区域合作商业务员分账金额 		(废弃)

			jsonObject.put("subsidy_money", "0.00");			// 平台补贴金额(平台做活动时补贴给用户的返利金额)(暂废)
			jsonObject.put("platform_fees", "0.00");			// 平台应付渠道手续费   (废弃)
			jsonObject.put("platform_money","0.00");			// 平台应收分账金额（废弃)
			
			
			
			returnMap.put("ledgerMoney", PreciseComputeUtil.add(orderMoney,liveAllAmount));
			returnMap.put("ledgerRatio",  PreciseComputeUtil.mul(bean.getLiveLedgerRatio(), 100,2));
			returnMap.put("user_money",  0);
			returnMap.put("userAmount",  0);
			returnMap.put("userMoney",  0);
			returnMap.put("commission",jsonObject);
		} catch (Exception e) {
			throw e;
		}
		return returnMap;
	}

	/**
	 * 商家套餐订单分账
	 * @param bean [请求参数]
	 * @return map [分账结果，包含各项收益及分账比例]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static Map<String,Object> getPackageLedgerMoney(SellerPackageLedger bean) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		JSONObject jsonObject = new JSONObject();

		try {
			// 订单金额100
			BigDecimal agio = bean.getAgio();
			BigDecimal orderMoney = bean.getOrderMoney();
			BigDecimal sellerAmount = BigDecimal.ZERO;
			BigDecimal ledgerRatio = bean.getLedgerRatio();
			if(ledgerRatio!=null){
				sellerAmount = orderMoney.multiply(bean.getLedgerRatio().divide(new BigDecimal("100")));
			}else{
				sellerAmount = bean.getLedgerAmount();
			}
			if(sellerAmount.compareTo(bean.getOrderMoney())>0){
				sellerAmount = bean.getOrderMoney();
			}
			sellerAmount = keepTwoPoint(sellerAmount);
//			if(ledgerRatio.compareTo(BigDecimal.ONE)>0){
//				ledgerRatio = BigDecimal.ONE;
//			}
			
			// 商户折扣0.8
//			double baseagio = bean.getBaseagio();
			
			// 分账比例0.5
//			double ledgerRatio = bean.getLedgerRatio();
			// 分账金额
//			double ledgerMoney = orderMoney;		// 默认为订单金额
			
			// 商户营收金额 = 订单金额 * 商户折扣
//			double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
			
			// 用户立减比例 = 1-商户折扣 - 分账比例
//			double userRatio = PreciseComputeUtil.sub(1,baseagio,ledgerRatio);
			// 商户折扣  > 0.95 
			
			returnMap.put("ledgerMoney", bean.getOrderMoney().doubleValue());
			returnMap.put("ledgerRatio",  agio.doubleValue());

//			userRatio=0.00;	
//			double user_money = PreciseComputeUtil.round(PreciseComputeUtil.mul(orderMoney, userRatio),2);
			returnMap.put("user_money",  0);
			
			// 用户立实际支付金额 =  订单金额 - 用户实际支付金额
//			double userMoney = PreciseComputeUtil.sub(orderMoney,user_money);
			// 用户实际支付金额
			returnMap.put("userAmount",  orderMoney.doubleValue());
			// 用户返利积分  = 用户实际支付金额(四舍五入，取整)
			returnMap.put("userMoney",  0);
			

//			double feeAmount = 0;
//			if(bean.getFeeRatio()>0 && sellerAmount>0){
//				//平台扣除商家手续费
//				feeAmount =  PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, bean.getFeeRatio()), 2);
//				sellerAmount = PreciseComputeUtil.sub(sellerAmount,feeAmount);
//				if(sellerAmount<0){
//					sellerAmount = 0.0;
//				}
//			}
			
			
			// 平台分账金额 = 订单金额 - 商户营收金额
			double platform_amount = orderMoney.subtract(sellerAmount).doubleValue();
			
			StringBuffer proportion = new StringBuffer("");
			proportion.append(0).append(",");									  	// 用户立减比例
			proportion.append(agio).append(",");	// 商户折扣
			
			proportion.append(PreciseComputeUtil.keepTwoPointStr(
					PreciseComputeUtil.add(0, 
							0, 
							0),2)).append(",");												    // 寻蜜客总分账比例 = 寻蜜客分账比例 + 上级寻蜜客分账比例  + 上上级寻蜜客分账比例  
			proportion.append(0).append(",");													// 经销商分账比例 
			proportion.append(PreciseComputeUtil.div(platform_amount, orderMoney.doubleValue(),2)).append("");						// 平台分账比例 = 平台分账金额  / 订单金额 
			returnMap.put("proportion", proportion.toString());															// 分账比例 格式  会员,商家,寻蜜客,合作商(),平台（废弃）
			
			jsonObject.put(LedgerConstants.SELLER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(sellerAmount.doubleValue(), 2));  		// 商户营收金额
			
			
			jsonObject.put(LedgerConstants.JOINT_BPARTNER, PreciseComputeUtil.keepTwoPointStr(0, 2)); // 商户店外收益 比例 
			jsonObject.put(LedgerConstants.SELLER_MONEY, PreciseComputeUtil.keepTwoPointStr(0, 2));	    	  // 商户店外收益 (新增)

			jsonObject.put(LedgerConstants.USERMONEY,
					PreciseComputeUtil.keepTwoPointStr(0, 2)); 					// 用户返利积分(用户实际支付金额)

			jsonObject.put(LedgerConstants.MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(0, 2));			// 寻蜜客额外分账收益(寻蜜客应收分账金额)

			jsonObject.put(LedgerConstants.PARENT_MIKE_AMOUNT,
					PreciseComputeUtil.keepTwoPointStr(0, 2));											// 寻蜜客上级分账金额	 (新增)
			jsonObject.put(LedgerConstants.TOP_MIKE_AMOUNT, PreciseComputeUtil.keepTwoPointStr(0, 2));	// 寻蜜客上上级分账金额  (新增)

			jsonObject.put(LedgerConstants.CPARTNER_AMOUNT,PreciseComputeUtil.keepTwoPointStr(0, 2) );	// 经销商分账金额(消费区域合作商分账金额)
			
			jsonObject.put(LedgerConstants.OTHER_AMOUNT, PreciseComputeUtil.keepTwoPointStr(0, 2));	// 中脉分账金额
			
			jsonObject.put(LedgerConstants.PLATFORM_AMOUNT, PreciseComputeUtil.keepTwoPointStr(platform_amount, 2));	// 平台分账金额
			
			jsonObject.put(LedgerConstants.REDUCTION, PreciseComputeUtil.keepTwoPointStr(0, 2));	// 用户立减金额
			
			jsonObject.put("proportion",proportion.toString());

			/********************************* 以下参数以后优化时可移除，但注意各系统对接时也需要移除以下字段的获取 *******************************************/

			jsonObject.put("fees_money", "0.00");				// 平台扣款手续费总额(由合作商 用户 平台分摊) (废弃)

			jsonObject.put("mike_fees", "0.00");				// 寻蜜客应付渠道手续费 (废弃)
			jsonObject.put("mike_money", "0.00");				// 寻蜜客应收分账金额 (废弃)

			jsonObject.put("joint_bpartner","0.00");  			// 用户区域合作商分账比例，未跨区默认为0      (废弃)
			jsonObject.put("bpartner_money", "0.00");		  	// 用户区域合作商分账金额，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_fees","0.00" );			// 用户区域应付渠道手续费，未跨区默认为0       (废弃)
			jsonObject.put("bpartner_amount","0.00" );			// 用户区域合作商应收分账金额,未跨区默认为0   (废弃)
			jsonObject.put("memberJointMoney", "0.00"); 		// 用户区域合作商业务员分账金额，未跨区默认为0 (废弃)

			jsonObject.put("cpartner_bpartner", "0.00");		// 消费区域合作商分账比例				(废弃)
			jsonObject.put("cpartner_money", "0.00");			// 消费区域合作商应收分账金额			(废弃)
			jsonObject.put("cpartner_fees", "0.00");			// 消费区域应付渠道手续费				(废弃)
			jsonObject.put("consumeJointidMoney","0.00" ); 		// 消费区域合作商业务员分账金额 		(废弃)

			jsonObject.put("subsidy_money", "0.00");			// 平台补贴金额(平台做活动时补贴给用户的返利金额)(暂废)
			jsonObject.put("platform_fees", "0.00");			// 平台应付渠道手续费   (废弃)
			jsonObject.put("platform_money","0.00");			// 平台应收分账金额（废弃)
			returnMap.put("commission",jsonObject);
		} catch (Exception e) {
			throw e;
		}
		return returnMap;
	}

	public static BigDecimal keepTwoPoint(BigDecimal money) {
		String str = "#####0.";
		for (int i = 0; i < 2; i++) {
			str = str + "0";
		}
		DecimalFormat format = new DecimalFormat(str);
		format.setRoundingMode(RoundingMode.FLOOR);
		return new BigDecimal(format.format(money.doubleValue()));
	}


	 /**
	 * 方法描述：平台签约的店铺/常规SAAS签约店铺 [脉宝]订单消费分账<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-11下午6:12:01 <br/>
	 * @param bean
	 * @return 
	 */
	public static Map<String, Object> getLedgerMoneyOfCommon(LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
		double maibao_ratio=0.7d;//脉宝收益70%
	    double platform_ratio=0.3d;//平台收益
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
//		double ledgerMoney = orderMoney-sellerAmount;		// 剩余分账金额
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
	
		double maibao_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, maibao_ratio), 2);//脉宝收益
//		double platform_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, platform_ratio), 2);//寻蜜鸟收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney,maibao_amount);//寻蜜鸟收益
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.MAIBAO_AMOUNT, maibao_amount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("commission", commissionJson);
		
		
		if(maibao_amount>0){
			ledgerMap.put("maibao_amount", maibao_amount);
		}else{
			ledgerMap.put("maibao_amount", 0);
		}
		
		return ledgerMap;
		
	}

	 /**
	 * 方法描述：脉客SAAS签约店铺 [脉宝]订单消费分账 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-11下午6:16:51 <br/>
	 * @param bean
	 * @return 
	 */
	public static Map<String, Object> getLedgerMoneyOfMaike(LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
		double maibao_ratio=0.8d;//脉宝收益80%
	    double platform_ratio=0.2d;//平台收益20%
//	    double mike_ratio=0.1d;//脉客(寻蜜客)(10%)
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
//		double ledgerMoney = orderMoney-sellerAmount;		// 剩余分账金额
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
		
		//脉宝分账金额——> 现金实付部分=（余额+第三方）*（1-商家折扣）
		double realPayMent = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(bean.getRealPayMent(),PreciseComputeUtil.sub(1,baseagio)),2);
	
		double maibao_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(realPayMent, maibao_ratio), 2);//脉宝收益
//		double mike_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, mike_ratio), 2);//寻蜜客收益
//		double platform_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, platform_ratio), 2);//寻蜜鸟收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney,maibao_amount);//寻蜜鸟收益
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.MAIBAO_AMOUNT, maibao_amount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
//		commissionJson.put(LedgerConstants.MIKE_AMOUNT, mike_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("commission", commissionJson);
		
		if(maibao_amount>0){
			ledgerMap.put("maibao_amount", maibao_amount);
		}else{
			ledgerMap.put("maibao_amount", 0);
		}
		
		return ledgerMap;
	}
	
	/**
	 * 方法描述：非脉客SAAS签约店铺 [脉宝]订单消费分账 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-11下午6:16:51 <br/>
	 * @param bean
	 * @return 
	 */
	public static Map<String, Object> getLedgerMoneyOfNotMaike(LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
		double maibao_ratio=0.7d;//脉宝收益70%
	    double platform_ratio=0.3d;//平台收益30%
//	    double mike_ratio=0.1d;//脉客(寻蜜客)(10%)
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
		
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
		
		//脉宝分账金额——> 现金实付部分=（余额+第三方）*（1-商家折扣）
		double realPayMent = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(bean.getRealPayMent(),PreciseComputeUtil.sub(1,baseagio)),2);
	
		double maibao_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(realPayMent, maibao_ratio), 2);//脉宝收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney,maibao_amount);//寻蜜鸟收益
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.MAIBAO_AMOUNT, maibao_amount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("commission", commissionJson);
		
		if(maibao_amount>0){
			ledgerMap.put("maibao_amount", maibao_amount);
		}else{
			ledgerMap.put("maibao_amount", 0);
		}
		
		return ledgerMap;
	}


	 /**
	 * 方法描述：V客SAAS签约 [脉宝]订单消费分账 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-11下午6:20:21 <br/>
	 * @param bean
	 * @return 
	 */
	public static Map<String, Object> getLedgerMoneyOfVke(LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
		double maibao_ratio=0.7d;//脉宝收益70%
	    double platform_ratio=0.2d;//平台收益20%
	    double mike_ratio=0.1d;//V客(寻蜜客)10%
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
//		double ledgerMoney = orderMoney-sellerAmount;		// 剩余分账金额
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
	
		double maibao_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, maibao_ratio), 2);//脉宝收益
		double mike_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, mike_ratio), 2);//寻蜜客收益
//		double platform_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, platform_ratio), 2);//寻蜜鸟收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney, maibao_amount,mike_amount);//寻蜜鸟收益
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.MAIBAO_AMOUNT, maibao_amount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
		commissionJson.put(LedgerConstants.MIKE_AMOUNT, mike_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("commission", commissionJson);
		
		if(maibao_amount>0){
			ledgerMap.put("maibao_amount", maibao_amount);
		}else{
			ledgerMap.put("maibao_amount", 0);
		}
		
		return ledgerMap;
		
	}


	 /**
	 * 方法描述：主播(V客赠送)SAAS签约 [脉宝]订单消费分账 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-11下午6:21:49 <br/>
	 * @param bean
	 * @return 
	 */
	public static Map<String, Object> getLedgerMoneyOfAnchorVke(LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
		double maibao_ratio=0.7d;//脉宝收益70%
	    double platform_ratio=0.2d;//平台收益20%
	    double mike_ratio=0.05d;//主播(当前寻蜜客)5%
	    double parent_mike_ratio=0.05d;//V客(上级寻蜜客)5%
	    
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
//		double ledgerMoney = orderMoney-sellerAmount;		// 剩余分账金额
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
	
		double maibao_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, maibao_ratio), 2);//脉宝收益
		double mike_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, mike_ratio), 2);//寻蜜客收益
		double parent_mike_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, parent_mike_ratio), 2);//上级寻蜜客收益
//		double platform_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, platform_ratio), 2);//寻蜜鸟收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney, maibao_amount,mike_amount,parent_mike_amount);//寻蜜鸟收益
		
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.MAIBAO_AMOUNT, maibao_amount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
		commissionJson.put(LedgerConstants.MIKE_AMOUNT, mike_amount);
		commissionJson.put(LedgerConstants.PARENT_MIKE_AMOUNT, parent_mike_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("seller_amount", sellerAmount);
		ledgerMap.put("commission", commissionJson);
		
		if(maibao_amount>0){
			ledgerMap.put("maibao_amount", maibao_amount);
		}else{
			ledgerMap.put("maibao_amount", 0);
		}
		
		return ledgerMap;
	}


	 /**
	 * 方法描述：脉客SAAS签约店铺 [其他]订单消费分账 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-12下午3:49:05 <br/>
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> getLedgerMoneyOfMaikeOther(
			LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
		double maibao_ratio=0.7d;//脉宝收益70%
	    double platform_ratio=0.3d;//平台收益30%
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
//		double ledgerMoney = orderMoney-sellerAmount;		// 剩余分账金额
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
	
		double maibao_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, maibao_ratio), 2);//脉宝收益
//		double platform_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, platform_ratio), 2);//寻蜜鸟收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney, maibao_amount);//寻蜜鸟收益
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.MAIBAO_AMOUNT, maibao_amount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("seller_amount", sellerAmount);
		ledgerMap.put("commission", commissionJson);
		
		if(maibao_amount>0){
			ledgerMap.put("maibao_amount", maibao_amount);
		}else{
			ledgerMap.put("maibao_amount", 0);
		}
		
		return ledgerMap;
	}


	 /**
	 * 方法描述：V客SAAS签约店铺 [其他]订单消费分账 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-12下午3:53:15 <br/>
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> getLedgerMoneyOfVkeOther(
			LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
	    double platform_ratio=0.9d;//平台收益90%
	    double mike_ratio=0.1d;//V客收益10%
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
//		double ledgerMoney = orderMoney-sellerAmount;		// 剩余分账金额
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
	
		double mike_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, mike_ratio), 2);//寻蜜客收益
//		double platform_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, platform_ratio), 2);//寻蜜鸟收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney, mike_amount);//寻蜜鸟收益
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
		commissionJson.put(LedgerConstants.MIKE_AMOUNT, mike_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("seller_amount", sellerAmount);
		ledgerMap.put("commission", commissionJson);
		
		return ledgerMap;
	}


	 /**
	 * 方法描述：V客SAAS签约店铺 [其他]订单消费分账 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-12下午4:01:55 <br/>
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> getLedgerMoneyOfAnchorVkeOther(
			LedgerNewBean bean) {
		Map<String,Object> ledgerMap=new HashMap<String,Object>();
		JSONObject commissionJson = new JSONObject();
		
	    double platform_ratio=0.9d;//平台收益90%
	    double mike_ratio=0.05d;//主播(寻蜜客)收益5%
	    double parent_mike_ratio=0.05d;//V客(上级寻蜜客)收益5%
	    String proportion="0.0,0.0,0.0,0.0,0.0";//分账比例 格式  会员,商家,寻蜜客,合作商,平台 (分账比例已无实际意义)
		
		// 订单金额100
		double orderMoney = bean.getOrderMoney();
		
		// 商户折扣0.8
		double baseagio = bean.getBaseagio();
		
		// 商户营收金额 = 订单金额 * 商户折扣
		double sellerAmount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(orderMoney, baseagio), 2);
		
//		double ledgerMoney = orderMoney-sellerAmount;		// 剩余分账金额
		// 分账金额 = 订单金额 - 商户营收金额
		double ledgerMoney = PreciseComputeUtil.sub(orderMoney, sellerAmount);
	
		double mike_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, mike_ratio), 2);//寻蜜客收益
		double parent_mike_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, parent_mike_ratio), 2);//寻蜜客收益
//		double platform_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney, platform_ratio), 2);//寻蜜鸟收益
		double platform_amount = PreciseComputeUtil.sub(ledgerMoney, mike_amount,parent_mike_amount);//寻蜜鸟收益
		
		commissionJson.put(LedgerConstants.SELLER_AMOUNT, sellerAmount);
		commissionJson.put(LedgerConstants.PLATFORM_AMOUNT, platform_amount);
		commissionJson.put(LedgerConstants.MIKE_AMOUNT, mike_amount);
		commissionJson.put(LedgerConstants.PARENT_MIKE_AMOUNT, parent_mike_amount);
		
		ledgerMap.put("proportion", proportion);//无实际意义
		ledgerMap.put("seller_amount", sellerAmount);
		ledgerMap.put("commission", commissionJson);
		
		return ledgerMap;
	}
	
}
