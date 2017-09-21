package com.xmniao.service.ledger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmniao.common.LedgerRation;
import com.xmniao.common.PaymentType;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.dao.ledger.LedgerService;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.ledger.LedgerBean;
import com.xmniao.domain.ledger.LedgerMixBean;
import com.xmniao.domain.ledger.LedgerNewBean;
import com.xmniao.domain.order.BillBean;
import com.xmniao.domain.order.CouponRelationBean;
import com.xmniao.service.seller.SellerServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;


/**
 * 分账系统实现接口类
 * @author  huangxiaobin
 * @version  [版本号, 2014年11月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class LedgerServiceImpl  implements LedgerService
{
	/**
	 * 初始日志类
	 */
	private Logger log = LoggerFactory.getLogger(LedgerServiceImpl.class);

	/**
	 * 注入订单DAO层
	 */
	@Autowired
	private OrderServiceDao orderDao;

	@Autowired
	private SellerServiceImpl sellerService;
	/**
	 * 根据传入的参数，进行分账计算
	 * @param bean [请求参数]
	 * @return map [分账结果，包含各项收益及分账比例]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public Map<String,Object> getLedgerMoney(LedgerBean bean) {
		/* 平台分账比例（包含所属合作商、消费合作商、平台） */
		double ration = 0.15;
		/* 分账结果集 */
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			double baseagio = PreciseComputeUtil.keepTwoPoint( PreciseComputeUtil.mul(bean.getBaseagio(),100),2);
			/* 如果商户折扣设为100 时,手续费将由 平台承担 */
			if(baseagio >= 100d){
				bean.setBaseagio(1d);
				bean.setIsPlatFeesAll("1");
			}
			/* 获取参数以及商户佣金 */
			Map<String,Double> maps = getProperties(bean);
			/* 获取合作商分账信息 */
			Map<String,Double> jointMap = getJointLedgerInfo(maps,bean);

			/* 商户结算（营收）金额 */
			double  seller_amount = maps.get("seller_amount");

			/* 平台分账金额  =  商户返利金额 - 用户分账金额 - 寻蜜客分账金额 - 消费区域合作商分账金额 - 消费区域合作商业务员分账金额   - 用户区域合作商分账金额  - 用户区域合作商业务员分账金额  */
			double platform_money = PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.sub(maps.get("ledgerMoney"), maps.get("user_money"),maps.get("mike_money"), 
							jointMap.get("cpartner_money"),jointMap.get("bpartner_money")),2);

			/* 平台应付渠道手续费= 应付渠道手续费 - 寻蜜客应付渠道手续费 - 消费区域合作商应付渠道手续费  - 用户区域合作商应付渠道手续费      */
			double platform_fees = PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.sub(maps.get("fees_money"), maps.get("mike_fees"),
					jointMap.get("cpartner_fees"),jointMap.get("bpartner_fees")),2);

			/* 如果设置了平台收益比例 */
			if(0 < bean.getPlatRation()){
				
				/* 平台收益金额 = 商户结算（营收）金额  * 平台收益比例  */
				double profitMoney = PreciseComputeUtil.keepTwoPoint(
						PreciseComputeUtil.mul(seller_amount, bean.getPlatRation()),2);
				/* 平台分账金额 = 平台分账金额 + 平台收益金额 */
				platform_money =  PreciseComputeUtil.keepTwoPoint(
						PreciseComputeUtil.add(platform_money, profitMoney),2);
				/* 商户结算（营收）金额 = 商户结算（营收）金额 - 平台收益金额 */
				seller_amount = PreciseComputeUtil.keepTwoPoint(
						PreciseComputeUtil.sub(seller_amount,profitMoney),2);
				/* 平台收益金额  */
				map.put("profitMoney", profitMoney);
			}
			/* 平台应收分账金额  = 平台分账金额 - 平台应付渠道手续费  */
			double platform_amount =  PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.sub(platform_money, platform_fees),2);

			/* 平台分账比例 = 平台分账比例（默认） - 所属区域合作商分账比例 - 消费区域合作商分账比例 */
			double platform_ratio = PreciseComputeUtil.sub(ration,jointMap.get("bpartner_ration"),jointMap.get("cpartner_ration"));

			StringBuffer proportion = 
				new StringBuffer(maps.get("userRation")+"").append(",")
					.append(bean.getBaseagio()+"").append(",")
					.append(maps.get("mikeRation")+"").append(",")
					.append(maps.get("bpartner_ration")+"").append(",")
					.append(maps.get("cpartner_ration")+"").append(",")
					.append(maps.get("platformRation")+"");
            
			/* 平台补贴金额  */
			map.put("subsidy_money", maps.get("subsidy_money"));

			/* 分账比例  */
			map.put("proportion", proportion.toString());
			/* 手续费金额  */
			map.put("fees_money",maps.get("fees_money")+"");
			/* 商户结算金额  */
			map.put("seller_amount", seller_amount+"");
			/* 用户返利金额  */
			map.put("userMoney", maps.get("user_money")+"");

			/* 寻蜜客分账金额  */
			map.put("mike_money", maps.get("mike_money")+"");
			/* 寻蜜客应付渠道手续费  */
			map.put("mike_fees", maps.get("mike_fees")+"");
			/* 寻蜜客应收分账金额  */
			map.put("mike_amount", maps.get("mike_amount")+"");

			/* 用户区域合作商分账比例 */
			map.put("joint_bpartner", jointMap.get("bpartner_ration")+"");
			/* 用户区域合作商分账金额  */
			map.put("bpartner_money", jointMap.get("bpartner_money")+"");
			/* 用户区域合作商应付渠道手续费  */
			map.put("bpartner_fees", jointMap.get("bpartner_fees")+"");
			/* 用户区域合作商应收分账金额  */
			map.put("bpartner_amount", jointMap.get("bpartner_amount")+"");
			/* 用户区域合作商业务员分账金额 */
			map.put("memberJointMoney", jointMap.get("bpartner_salesman")+"");

			/* 消费区域合作商分账比例  */
			map.put("cpartner_bpartner", jointMap.get("cpartner_ration")+"");
			/* 消费区域合作商分账金额  */
			map.put("cpartner_money", jointMap.get("cpartner_money")+"");
			/* 消费区域合作商应付渠道手续费  */
			map.put("cpartner_fees", jointMap.get("cpartner_fees")+"");
			/* 消费区域合作商应收分账金额  */
			map.put("cpartner_amount", jointMap.get("cpartner_amount")+"");
			/* 消费区域合作商业务员分账金额  */
			map.put("consumeJointidMoney", jointMap.get("cpartner_salesman")+"");

			/* 平台分账比例 */
			map.put("platform_ratio", platform_ratio+"");
			/* 平台应付渠道手续费 */
			map.put("platform_fees", platform_fees+"");
			/* 平台分账金额 */
			map.put("platform_money", platform_money+"");
			/* 平台应收分账金额 */
			map.put("platform_amount", platform_amount+"");
		}catch (Exception e) {
			log.error("分账计算出错："+e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 2016-02-23
	 * 根据传入的参数，获取商家营收。跳过 用户  商户  合作商 平台 分账规则。
	 * @param bean [请求参数]
	 * @return map [分账结果，包含各项收益及分账比例]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,Object> getRevenueMoney(LedgerBean bean) {
		
		/* 分账结果集 */
		Map<String,Object> map = new HashMap<String,Object>();
		/* 平台补贴金额  */
		map.put("subsidy_money", "0.0");

		/* 分账比例   格式  会员,商家,向蜜客,合作商(1:1),平台 */
		map.put("proportion", "0.0,0.0,0.0,0.0,0.0");
		/* 手续费金额  */
		map.put("fees_money","0.0");
		
		
		/* 用户返利金额  */
		map.put("userMoney","0.0");

		/* 寻蜜客分账金额  */
		map.put("mike_money", "0.0");
		/* 寻蜜客应付渠道手续费  */
		map.put("mike_fees", "0.0");
		/* 寻蜜客应收分账金额  */
		map.put("mike_amount", "0.0");

		/* 用户区域合作商分账比例 */
		map.put("joint_bpartner", "0.0");
		/* 用户区域合作商分账金额  */
		map.put("bpartner_money", "0.0");
		/* 用户区域合作商应付渠道手续费  */
		map.put("bpartner_fees", "0.0");
		/* 用户区域合作商应收分账金额  */
		map.put("bpartner_amount", "0.0");
		/* 用户区域合作商业务员分账金额 */
		map.put("memberJointMoney", "0.0");

		/* 消费区域合作商分账比例  */
		map.put("cpartner_bpartner", "0.0");
		/* 消费区域合作商分账金额  */
		map.put("cpartner_money", "0.0");
		/* 消费区域合作商应付渠道手续费  */
		map.put("cpartner_fees", "0.0");
		/* 消费区域合作商应收分账金额  */
		map.put("cpartner_amount", "0.0");
		/* 消费区域合作商业务员分账金额  */
		map.put("consumeJointidMoney", "0.0");

		/* 平台分账比例 */
		map.put("platform_ratio", "0.0");
		/* 平台应付渠道手续费 */
		map.put("platform_fees", "0.0");
		
		/* 商户结算(营收)金额   营收 = 订单金额(包含优惠卷金额) * 商户签约折扣  */
		double seller_amount = 0.0;
		double platform = 0.0;
		
		//判断是否是使用商家会员卡支付
		if(bean.getCardMoney() <= 0){
			//不是商家会员卡支付
			if(bean.getConsumeMoney() <= 0.01){
				seller_amount = bean.getConsumeMoney();			
			}else{
				seller_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(bean.getConsumeMoney(),bean.getBaseagio()),2);
				platform = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.sub(bean.getConsumeMoney(), seller_amount),2);
			}
		}else{
			//是商家会员卡支付  商户和平台不获得收益，因为用户在充值会员卡时 已经分账
			seller_amount = 0.0;
			platform = 0.0;
		}
		map.put("seller_amount", seller_amount);
				
		/* 平台分账金额   = 订单金额(包含优惠卷金额) - 商家营收 */
		map.put("platform_money", platform);
		/* 平台应收分账金额 */
		map.put("platform_amount", platform);
		return map;
	}
	
	/**
	 * 2016-03-02
	 * 根据传入的参数，计算新分账数据   《加盟区域毛利分账说明 - 技术需求20160302》。
	 * @param bean [请求参数]
	 * @return map [分账结果，包含各项收益及分账比例]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,Object> getNewLedgerMoney(LedgerBean bean) {
		
		/* 分账结果集 */
		Map<String,Object> map = new HashMap<String,Object>();
		/* 平台补贴金额  */
		map.put("subsidy_money", "0.0");

		/* 分账比例  */
		map.put("proportion", "0.0,0.0,0.0,0.0,0.0");
		/* 手续费金额  */
		map.put("fees_money","0.0");
		
		
		/* 用户返利金额  */
		map.put("userMoney","0.0");

		/* 寻蜜客分账金额  */
		map.put("mike_money", "0.0");
		/* 寻蜜客应付渠道手续费  */
		map.put("mike_fees", "0.0");
		/* 寻蜜客应收分账金额  */
		map.put("mike_amount", "0.0");

		/* 用户区域合作商分账比例 */
		map.put("joint_bpartner", "0.0");
		/* 用户区域合作商分账金额  */
		map.put("bpartner_money", "0.0");
		/* 用户区域合作商应付渠道手续费  */
		map.put("bpartner_fees", "0.0");
		/* 用户区域合作商应收分账金额  */
		map.put("bpartner_amount", "0.0");
		/* 用户区域合作商业务员分账金额 */
		map.put("memberJointMoney", "0.0");

		/* 平台分账比例 */
		map.put("platform_ratio", "0.0");
		/* 平台应付渠道手续费 */
		map.put("platform_fees", "0.0");
		
		/* 商户结算(营收)金额   营收 = 订单金额(包含优惠卷金额) * 商户签约折扣  */
		double seller_amount = 0.0;//商家营收
		double ledger_amount = 0.0;//所分账金额
		double platform = 0.0;//平台营收
		if(bean.getConsumeMoney() <= 0.01){
			seller_amount = bean.getConsumeMoney();
		}else{
			seller_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(bean.getConsumeMoney(),bean.getBaseagio()),2);
			ledger_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.sub(bean.getConsumeMoney(), seller_amount),2);
			platform = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledger_amount, 0.8),2);
		}
		map.put("seller_amount", seller_amount);
				
		/* 平台分账金额   = 订单金额(包含优惠卷金额) - 商家营收 */
		map.put("platform_money", platform);
		/* 平台应收分账金额 */
		map.put("platform_amount", platform);
		
		
		/* 消费区域合作商分账比例  */
		map.put("cpartner_bpartner", "0.0");
		
		/* 消费区域合作商分账金额   = 平台分账金额 * 0.2 */
		double cpartner_money = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledger_amount, 0.2),2);
		map.put("cpartner_money", cpartner_money);
		
		/* 消费区域合作商应付渠道手续费  */
		map.put("cpartner_fees", "0.0");
		/* 消费区域合作商应收分账金额  */
		map.put("cpartner_amount", cpartner_money);
		/* 消费区域合作商业务员分账金额  */
		map.put("consumeJointidMoney", "0.0");
		
		return map;
	}


	/**
	 * 根据参数，读取配置文件，获取预置分账比例及参与方分账金额（商户、用户、寻蜜客分账金额）
	 * @param bean [订单信息中分账计算所需的参数] 
	 * @return maps [包含各项分账比例及分账金额（商户、用户、寻蜜客分账金额）]
	 * @throws Exception 
	 */
	private Map<String, Double> getProperties(LedgerBean bean) throws Exception {

		Map<String,Double> maps = new HashMap<String,Double>();
		try {
			/* 获取支付渠道及各项分账比例 */
			maps.putAll(getRationByEnum(bean.getPayType()));
			/* 应付渠道手续费  */
			double fees_money = PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.mul(bean.getConsumeMoney(), maps.get("feesRation")),2);
			maps.put("fees_money", fees_money);
			/* 商户结算金额  */
			double seller_amount = PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.mul(bean.getConsumeMoney(),bean.getBaseagio()),2);
			maps.put("seller_amount",seller_amount);
			/* 商户返利金额(分账金额)  */
			double ledgerMoney = PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.sub(bean.getConsumeMoney(),seller_amount),2);
			maps.put("ledgerMoney",ledgerMoney);
			/* 用户分账金额  */
			double user_money =PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.mul(ledgerMoney,maps.get("userRation")),2);
			maps.put("user_money", user_money);
			/* 平台补贴金额  */
			double subsidy_money = 0d;
			/* 如果设置了平台补贴比例 */
			if(bean.getSubsidy() > 0){
				subsidy_money = PreciseComputeUtil.keepTwoPoint(
						PreciseComputeUtil.mul(bean.getConsumeMoney(),bean.getSubsidy()),2);
				subsidy_money = PreciseComputeUtil.keepTwoPoint(
						PreciseComputeUtil.sub(subsidy_money, user_money),2);
			}else{
				maps.put("user_money", PreciseComputeUtil.keepTwoPoint(
						PreciseComputeUtil.mul(ledgerMoney,maps.get("userRation")),2));
			}
			/* 寻蜜客应付渠道手续费  */
			double mike_fees = 0d;
			/* 合作商应付渠道手续费  */
			double joint_money = 0d;
			
			/* 如果没有设置平台承担所有手续费 add by hxb 2015年9月15日  */
			if(!"1".equals(bean.getIsPlatFeesAll()) || null == bean.getIsPlatFeesAll()){
				mike_fees  = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.div(fees_money,3),2);
				joint_money = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.div(fees_money,3),2);
			}
			maps.put("mike_fees",mike_fees);
			/* 寻蜜客分账金额  */
			double mike_money  = PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.mul(ledgerMoney,maps.get("mikeRation")),2);
			maps.put("mike_money",mike_money);
			/* 寻蜜客应收分账金额  */
			maps.put("mike_amount", PreciseComputeUtil.keepTwoPoint(
					PreciseComputeUtil.sub(mike_money,mike_fees),2));
			/* 合作商应付渠道手续费  */
			maps.put("joint_money", joint_money);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return maps;
	}

	/**
	 * 获取合作商分账信息
	 * @param mapRation
	 * @param bean
	 * @param joint_money
	 * @param ledgerMoney
	 * @return
	 * @throws Exception 
	 */
	private Map<String, Double> getJointLedgerInfo(Map<String, Double> maps, LedgerBean bean) throws Exception {
		String message = "";
		/* 分账结果集 */
		Map<String,Double> map = new HashMap<String,Double>();
		/* 合作商应付渠道手续费 */
		double joint_money = maps.get("joint_money");
		/* 分账金额 */
		double ledgerMoney = maps.get("ledgerMoney");
		
		/* 消费区域合作商分编号 */
		Integer cpartner_id = bean.getConsumeJointid();
		/* 消费区域合作商分账比例, 默认跨区消费 */
		double cpartner_ration = maps.get("cpartner_ration");
		/* 消费区域合作商应付渠道手续费, 默认跨区消费   */
		double cpartner_fees = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.div(joint_money,2),2);
		/* 消费区域合作商分账金额, 默认跨区消费 */
		double cpartner_money = 0d;
		/* 消费区域合作商应收分账金额, 默认跨区消费 */
		double cpartner_amount = 0d;
		
		/* 消费区域合作商应业务员编号  */
		Integer cpartner_sId = bean.getConsumeSalesmanId();
		/* 消费区域合作商应业务员分账比例  */
		double cpartner_sRation = bean.getConsumeSalesmanRation();
		/* 消费区域合作商应业务员分账金额  */
		double cpartner_sMoney = 0d;
		
		/* 用户区域合作商分编号 */
		Integer bpartner_id = bean.getMemberJointId();
		/* 用户区域合作商分账比例, 默认跨区消费   */
		double bpartner_ration = maps.get("bpartner_ration");
		/* 用户区域合作商应付渠道手续费, 默认跨区消费   */
		double bpartner_fees = PreciseComputeUtil.sub(joint_money,cpartner_fees);
		/* 用户区域合作商分账金额, 默认跨区消费 */
		double bpartner_money = 0d;
		/* 用户区域合作商应收分账金额, 默认跨区消费 */
		double bpartner_amount = 0d;
		
		/* 用户区域合作商应业务员编号  */
		Integer bpartner_sId = bean.getMemberSalesmanId();
		/* 消费区域合作商应业务员分账比例  */
		double bpartner_sRation = bean.getMemberSalesmanRation();
		/* 用户区域合作商应业务员分账金额  */
		double bpartner_sMoney = 0d;
		message = "用户区域或消费区域为空 ";
		/* 如果消费区域合作商为空，将此设为默认虚拟合作商（ID为0）,且收益归平台 */
		if(null == cpartner_id  || cpartner_id == 0){
			cpartner_id = 0 ;
			cpartner_ration = 0d;
			cpartner_fees = 0d;
			joint_money = 0d ;
		}
		/* 如果用户区域合作商为空，将此设为默认虚拟合作商（ID为0）,且收益归平台 */
		if(null == bpartner_id || bpartner_id == 0){
			bpartner_id = 0 ;
			bpartner_ration = PreciseComputeUtil.keepTwoPoint(0,2);
			bpartner_fees = PreciseComputeUtil.keepTwoPoint(0,2);
		}
		/* 如果设置了平台承担所有手续费,合作商将不扣除手续费 */
		if("1".equals(bean.getIsPlatFeesAll())){
			joint_money = 0d;
			bpartner_fees = 0d;
			cpartner_fees = 0d;
		}
		try {
			/* 消费区域合作商分账比例 > 0 */
			if(cpartner_ration > 0){
				/* 消费区域合作商分账金额  */
				cpartner_money = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney,cpartner_ration),2);
				/* 消费区域合作商应收分账金额(默认) =  消费区域合作商分账金额 - 消费区域合作商应付渠道手续费 */
				cpartner_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.sub(cpartner_money, cpartner_fees),2);
				/* 如果是平台帮助签约,该合作商的50%收益 归平台   */
				if("1".equals(bean.getConsumeSigningType())){
					cpartner_money = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(cpartner_money,0.5),2);
					/* 消费区域合作商应收分账金额 = 消费区域合作商分账金额 / 2 - 消费区域合作商应付渠道手续费*/
					cpartner_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.sub(cpartner_money, cpartner_fees),2);
				}else{
					/* 消费区域合作商业务员不为空 */
					if(!StringUtils.isEmpty(cpartner_sId)){
						/* 消费区域业务员分账金额  = 消费区域合作商应收分账金额 * 消费区域业务员分账比例 */
						cpartner_sMoney = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(cpartner_amount, cpartner_sRation),2);
						/* 消费区域合作商应收分账金额 = 消费区域合作商分账金额 - 消费区域业务员提成  */
						cpartner_amount = PreciseComputeUtil.sub(cpartner_amount,cpartner_sMoney);
					}
				}
			}
			/* 用户区域合作商分账比例 > 0 */
			if(bpartner_ration > 0){
				/* 用户区域合作商分账金额  */
				bpartner_money = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(ledgerMoney,bpartner_ration),2);
				/* 用户区域合作商应收分账金额(默认) =  用户区域合作商分账金额 - 用户区域合作商应付渠道手续费 */
				bpartner_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.sub(bpartner_money, bpartner_fees),2);
				/* 如果是平台帮助签约,该合作商的50%收益 归平台   */
				if("1".equals(bean.getMemberSigningType())){
					bpartner_money = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(bpartner_money,0.5),2);
					/* 用户区域合作商应收分账金额 = 用户区域合作商分账金额 / 2 - 用户区域合作商应付渠道手续费*/
					bpartner_amount = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.sub(bpartner_money, bpartner_fees),2);
				}else{
					/* 用户区域合作商业务员不为空 */
					if(!StringUtils.isEmpty(bpartner_sId)){
						/* 用户区域业务员分账金额 =  用户区域合作商应收分账金额 * 用户区域业务员分账比例 */
						bpartner_sMoney = PreciseComputeUtil.keepTwoPoint( PreciseComputeUtil.mul(bpartner_amount, bpartner_sRation),2);
						/* 用户区域合作商应收分账金额   = 用户区域合作商应收分账金额 -  用户区域业务员分账金额 */
						bpartner_amount = PreciseComputeUtil.sub(bpartner_amount, bpartner_sMoney);
					}
				}
				
			}
			/* 区域相同，为非跨区消费,收益归消费合作商 */
			if(bean.getMemberArea().equals(bean.getConsumeArea())){
				cpartner_ration =  PreciseComputeUtil.add(cpartner_ration,bpartner_ration);
				cpartner_fees = PreciseComputeUtil.add(cpartner_fees,bpartner_fees);
				cpartner_money =  PreciseComputeUtil.add(cpartner_money,bpartner_money);
				cpartner_amount = PreciseComputeUtil.sub(cpartner_money, cpartner_fees);
				cpartner_sMoney = PreciseComputeUtil.keepTwoPoint(PreciseComputeUtil.mul(cpartner_amount, cpartner_sRation),2);
				cpartner_amount = PreciseComputeUtil.sub(cpartner_amount,cpartner_sMoney);
				bpartner_fees = 0d;
				bpartner_ration = 0d;
				bpartner_money = 0d;
				bpartner_amount = 0d;
				bpartner_sMoney = 0d;
				
			}
			map.put("cpartner_ration", cpartner_money == 0d ? 0d : cpartner_ration);
			map.put("cpartner_money", cpartner_money); 
			map.put("cpartner_fees", cpartner_fees);
			map.put("cpartner_amount", cpartner_amount);
			map.put("cpartner_salesman", cpartner_sMoney);

			map.put("bpartner_ration", bpartner_ration == 0d? 0d : bpartner_ration);
			map.put("bpartner_money", bpartner_money);
			map.put("bpartner_fees", bpartner_fees);
			map.put("bpartner_amount", bpartner_amount);
			map.put("bpartner_salesman", bpartner_sMoney);

		} catch (Exception e) {
			log.error("获取合作商分账信息出错!:",e);
			throw new Exception("获取合作商分账信息出错,"+message);
		}
		return map;
	}
	
	/**
	 * 获取支付渠道费率及各项分账比例
	 * @param payType [支付渠道code]
	 * @throws TException [参数说明]
	 * @return Map [包含支付渠道费率及各项分账比例]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private Map<String,Double> getRationByEnum(String payType) throws Exception{
		Map<String,Double> maps = new HashMap<String,Double>();
		/* 参与分账方分账比例code */
		String [] rationName = {"userRation","mikeRation","bpartner_ration","cpartner_ration","platformRation"};
		/* 预设异常信息 */
		String message = "";
		try {
			for (int i = 0; i < rationName.length; i++) {
				message = "在【"+LedgerRation.class.getCanonicalName()+"】中，"
						+ "没有配置【"+LedgerRation.getDescriptionByName(rationName[i])+"】的分账比例信息";
				maps.put(rationName[i],  Double.valueOf(LedgerRation.getRationByName(rationName[i])));
			}
			/* 预设异常信息 */
			message = "在【"+PaymentType.class.getCanonicalName()+"】中.没有配置：【"+payType+"】的支付渠道信息";
			/* 支付渠道费率  */
			maps.put("feesRation", Double.valueOf(PaymentType.getRateById(payType)));
		} catch (Exception e) {
			throw new Exception(message);
		}
		return maps;
	}

	/**
	 * 获取分账系统信息
	 * @param paramMap [请求参数]
	 * @throws FailureException [异常参数]
	 * @throws TException [参数说明]
	 * @return LedgerBean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public LedgerBean ledgerInfoProcess(Map<String, Object> paramMap)
	{
		log.info("ledgerInfoProcess start:" + paramMap);
		LedgerBean ledgerBean = new LedgerBean();
		try
		{
			if (null != paramMap)
			{
				// 用户ID
				ledgerBean.setUserId(Integer.parseInt(paramMap.get("uid").toString()));
				// 消费商家ID
				ledgerBean.setSellerId(Integer.parseInt(paramMap.get("sellerid").toString()));
				// 向蜜客ID
				ledgerBean.setMikeId(Integer.parseInt(paramMap.get("genussellerid").toString()));
				// 商户折扣
				ledgerBean.setBaseagio(Double.parseDouble(paramMap.get("baseagio").toString()));
				// 支付方式
				ledgerBean.setPayType(paramMap.get("paytype").toString());
				if(paramMap.containsKey("flat_agio") && null!=paramMap.get("flat_agio"))
				{
				    // 平台补助占比
				    ledgerBean.setSubsidy(Double.parseDouble(paramMap.get("flat_agio").toString()));
				}else
				{
                    // 平台补助占比
                    ledgerBean.setSubsidy(0);
				}

				if(paramMap.containsKey("jointid") && null!=paramMap.get("jointid"))
				{
					// 所属合作商
					ledgerBean.setMemberJointId(Integer.parseInt(paramMap.get("jointid").toString()));
				}else
				{
					ledgerBean.setMemberJointId(0);
				}
				if(paramMap.containsKey("consume_jointid") && null!=paramMap.get("consume_jointid"))
				{
					// 消费合作商
					ledgerBean.setConsumeJointid(Integer.parseInt(paramMap.get("consume_jointid").toString()));
				}else
				{
					ledgerBean.setConsumeJointid(0);
				}
				if(paramMap.containsKey("area") && null!=paramMap.get("area"))
				{
				    //消费商家的区编号
				    ledgerBean.setConsumeArea(paramMap.get("area").toString());
				}
				//所属商家的区编号
				String memberSellerArea = orderDao.querySellerArea(paramMap.get("genussellerid").toString());
				if(!StringUtils.isEmpty(memberSellerArea))
				{
				    ledgerBean.setMemberArea(memberSellerArea);
				}
				//查询商户信息
				Map<String,Object> reSellerMap=orderDao.querySellerInfos(paramMap.get("sellerid").toString());
				if(null!=reSellerMap)
				{
				    //获取是否扣取支付手续费
				    if(reSellerMap.containsKey("isfees") && null!=reSellerMap.get("isfees"))
				    {
				        ledgerBean.setIsPlatFeesAll(reSellerMap.get("isfees").toString());
				    }
				    //获取平台扣款比例
				    if(reSellerMap.containsKey("debit") && null!=reSellerMap.get("debit"))
				    {
				        ledgerBean.setPlatRation(Double.valueOf(String.valueOf(reSellerMap.get("debit"))).doubleValue());
				    }
				}
				
				// 查询是否平台帮助签约商户 2016-02-23 取消查询是否平台帮忙签约
				//selectGiveInfo(ledgerBean);
				// 查询合作商业务员的提成比例
				selectPercentageInfo(ledgerBean);
				// 查询消费商户和所属商户的业务员信息
				selectSalesmanInfo(ledgerBean);
				//优惠券金额处理
				couponProcess(paramMap,ledgerBean);
			}
		}
		catch (Exception e)
		{
			log.error("分账信息业务处理异常", e);
		}
		log.info("ledgerInfoProcess end......");
		return ledgerBean;
	}

	/**
	 * 获取分账系统信息 2016年6月14日 新版本分账规则
	 * @param paramMap [请求参数]
	 * @throws FailureException [异常参数]
	 * @throws TException [参数说明]
	 * @return LedgerBean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public LedgerNewBean ledgerInfoProcess2(BillBean billBean)
	{
		log.info("ledgerInfoProcess start:" + billBean);
		LedgerNewBean ledgerBean = new LedgerNewBean();
		try
		{
			if (null != billBean)
			{	
				if(billBean.getPaytype().equals("1000015")){	//鸟币支付，只仅商家参与分账
					billBean.setLedgerMode(2);
				}
				ledgerBean.setOrderMoney(billBean.getLedgerAmount().doubleValue());
				ledgerBean.setRealPayMent(billBean.getRealPayMent().doubleValue());
				ledgerBean.setUserMoney(billBean.getPayment().doubleValue());
				ledgerBean.setBaseagio(billBean.getBaseagio());
				ledgerBean.setLedgerMode(billBean.getLedgerMode());//新增分账模式
				ledgerBean.setMikeType(1);
				ledgerBean.setFeeRatio(billBean.getFeeRatio());
				ledgerBean.setSellerId(billBean.getSellerid());
				ledgerBean.setMikeId(billBean.getLedgerMode()==2?null
						:billBean.getXmerUid());
				ledgerBean.setGenusSellerId(billBean.getLedgerMode()==2?null
						:sellerService.isSeller(billBean.getGenussellerid())?billBean.getGenussellerid():null);
				ledgerBean.setParentMikeId(billBean.getLedgerMode()==2?null
						:billBean.getTwoLevelXmerId());
				ledgerBean.setTopMikeId(billBean.getLedgerMode()==2?null
						:billBean.getOneLevelXmerId());
				ledgerBean.setConsumeJointid(billBean.getLedgerMode()==2?null
						:sellerService.isJoint(billBean.getConsumeJointid())?billBean.getConsumeJointid():null);
				if(billBean.getCouponType()!=null && billBean.getCouponType()==3 
					&& billBean.getCuser()!=null && billBean.getCuser().compareTo(BigDecimal.ZERO)>0){
					ledgerBean.setBreduction(false);
				}
			}
		}
		catch (Exception e)
		{
			log.error("分账信息业务处理异常", e);
		}
		log.info("ledgerInfoProcess end......");
		return ledgerBean;
	}

	/**
	 * 获取分账系统信息 2016年11月22日 直播分账规则
	 * @param paramMap [请求参数]
	 * @throws FailureException [异常参数]
	 * @throws TException [参数说明]
	 * @return LedgerBean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public LedgerNewBean ledgerInfoProcess3(BillBean billBean)
	{
		log.info("ledgerInfoProcess start:" + billBean);
		LedgerNewBean ledgerBean = null;
		try
		{
			if (null != billBean)
			{	
				ledgerBean = new LedgerNewBean();
				if(billBean.getLiveLedgerMode()==1){//全额分账
					ledgerBean.setOrderMoney(billBean.getLedgerAmount().doubleValue());
					ledgerBean.setSellerExtraMoney(0);
				}else if(billBean.getLiveLedgerMode()==2){//仅粉丝券金额分账
					BigDecimal fansCoupon=billBean.getCouponType()!=null && billBean.getCouponType()==3?billBean.getCuser():BigDecimal.ZERO;
					ledgerBean.setOrderMoney(fansCoupon.doubleValue());
					BigDecimal extraMoney = billBean.getLedgerAmount().subtract(fansCoupon);
					extraMoney = extraMoney.compareTo(BigDecimal.ZERO)>0?extraMoney:BigDecimal.ZERO;
					ledgerBean.setSellerExtraMoney(extraMoney.doubleValue());
				}

				ledgerBean.setUserMoney(billBean.getCalculateRealPayAmount().doubleValue());
				ledgerBean.setBaseagio(billBean.getLiveLedgerRatio());//直播分账比例
				ledgerBean.setLedgerMode(billBean.getLedgerMode());//新增分账模式
				ledgerBean.setMikeType(1);
				ledgerBean.setSellerId(billBean.getSellerid());
				ledgerBean.setMikeId(null);
				ledgerBean.setGenusSellerId(null);
				ledgerBean.setParentMikeId(null);
				ledgerBean.setTopMikeId(null);
				ledgerBean.setConsumeJointid(null);
			}
		}
		catch (Exception e)
		{
			log.error("分账信息业务处理异常", e);
		}
		log.info("ledgerInfoProcess end......");
		return ledgerBean;
	}
	
	/**
	 * 获取分账系统信息 2016年11月30日 直播-常规混合分账规则
	 * @param 
	 * LedgerNewBean bean 直播分账
	 * LedgerNewBean bean2  常规分账
	 * @throws FailureException [异常参数]
	 * @throws TException [参数说明]
	 * @return LedgerBean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public LedgerMixBean ledgerInfoProcess4(LedgerNewBean bean,LedgerNewBean bean2)
	{
		LedgerMixBean mixBean = new LedgerMixBean();
		mixBean.setOrderMoney(bean.getSellerExtraMoney());
		mixBean.setLiveLedgerMoney(bean.getOrderMoney());
		mixBean.setLiveLedgerRatio(bean.getBaseagio());
		mixBean.setUserMoney(bean.getUserMoney());
		mixBean.setBaseagio(bean2.getBaseagio());//直播分账比例
		mixBean.setLedgerMode(bean2.getLedgerMode());//新增分账模式
		mixBean.setMikeType(bean2.getMikeType());
		mixBean.setSellerId(bean2.getSellerId());
		mixBean.setMikeId(bean2.getMikeId());
		mixBean.setGenusSellerId(bean2.getGenusSellerId());
		mixBean.setParentMikeId(bean2.getParentMikeId());
		mixBean.setTopMikeId(bean2.getTopMikeId());
		mixBean.setConsumeJointid(bean2.getConsumeJointid());
		mixBean.setFeeRatio(bean2.getFeeRatio());
		log.info("直播-常规混合分账初始数据："+mixBean);
		return mixBean;
	}

	/**
	 * 获取是否是平台帮忙签约
	 * @param paramMap[请求参数]
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private void selectGiveInfo(LedgerBean ledgerBean)
	{
		Map<String, Object> paramGiveMap = new HashMap<String, Object>();
		log.info("selectGiveInfo start:{sellerId:" + ledgerBean.getSellerId() + ",genusSellerId:" + ledgerBean.getMikeId() + "}");
		try
		{
			//添加查询条件:消费商家ID和所属商家ID
			paramGiveMap.put("sellerId", ledgerBean.getSellerId());
			paramGiveMap.put("genusSellerId", ledgerBean.getMikeId());
			//获取是否平台帮忙签约接口 
			Map<String, Object> resGiveMap = orderDao.selectGiveInfo(paramGiveMap);
			//SET值
			ledgerBean.setMemberSigningType(resGiveMap.get("genusGive") + "");
			ledgerBean.setConsumeSigningType((resGiveMap.get("sellerGive") + ""));
			log.info("selectGiveInfo end:" + resGiveMap);
		}
		catch (Exception e)
		{
			log.error("获取总部帮忙签约标识异常", e);
		}
	}

	/**
	 * 获取消费业务员和所属业务员信息
	 * @param ledgerBean [分账实体Bean]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private void selectSalesmanInfo(LedgerBean ledgerBean)
	{
		log.info("selectSalesmanInfo start......");
		try
		{
			Map<String, Object> salesmanMap = new HashMap<String, Object>();
			salesmanMap.put("genussellerId", ledgerBean.getMikeId());
			salesmanMap.put("consumeJointId", ledgerBean.getConsumeJointid());
			salesmanMap.put("jointId", ledgerBean.getMemberJointId());
			salesmanMap.put("sellerId", ledgerBean.getSellerId());
			log.info("selectSalesmanInfo paraMap:" + salesmanMap);
			//获取所属合作商和消费合作商的业务员ID
			Map<String, Object> resStaffMap = orderDao.selectSalesmanId(salesmanMap);
			if (null != resStaffMap && !resStaffMap.isEmpty())
			{
				//消费业务员ID
				String consumeId = resStaffMap.get("consumeId").toString();
				ledgerBean.setConsumeSalesmanId(Integer.valueOf(consumeId));
				if ( ledgerBean.getConsumeSalesmanId()==0) ledgerBean.setConsumeSalesmanId(null);
				//所属业务员ID
				String salesmanId = resStaffMap.get("salesmanId").toString();
				ledgerBean.setMemberSalesmanId(Integer.valueOf(salesmanId));
				if ( ledgerBean.getMemberSalesmanId()==0) ledgerBean.setMemberSalesmanId(null);
				log.info("consumeStaffId:"+ledgerBean.getConsumeSalesmanId()+"  memberStaffId:"+ledgerBean.getMemberSalesmanId());
			}
		}
		catch (Exception e)
		{
			log.error("获取消费业务员和所属业务员信息异常", e);
		}
		log.info("selectSalesmanInfo end......");
	}

	/**
	 * 获取合作商员工分账比例
	 * @param paramMap [请求参数]
	 * @return Map<String, Object> [返回参数]
	 */
	private void selectPercentageInfo(LedgerBean ledgerBean)
	{
		Map<String, Object> percentageMap = new HashMap<String, Object>();
		percentageMap.put("jointId", ledgerBean.getMemberJointId());
		percentageMap.put("consumeJointId", ledgerBean.getConsumeJointid());

		log.info("selectPercentageInfo start:" + percentageMap);
		try
		{
			//调用获取合作商员工分账比例
			Map<String, Object> resMap = orderDao.selectPercentageInfo(percentageMap);

			if (null != resMap && !resMap.isEmpty())
			{
				ledgerBean.setMemberSalesmanRation(Double.valueOf(resMap.get("percentage").toString()));
				ledgerBean.setConsumeSalesmanRation(Double.valueOf(resMap.get("consumePercentage").toString()));
			}
			log.info("selectPercentageInfo end:" + resMap);
		}
		catch (Exception e)
		{
			log.error("获取合作商员工分账比例异常", e);
		}
	}
	
	/**
	 * 优惠券消费金额处理方法
	 * @param paramMap [订单信息MAP]
	 * @param ledgerBean [分账实体Bean参数]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private void couponProcess(Map<String, Object> paramMap,LedgerBean ledgerBean)
	{
	    BigDecimal cuserMoney=BigDecimal.ZERO;
	    //查询商户发行的优惠券订单关系信息
	    List<CouponRelationBean> resList=orderDao.querCouponRelation(paramMap.get("bid").toString());
	    for(CouponRelationBean bean : resList)
	    {
	        BigDecimal rescuser=bean.getCuser().add(cuserMoney);
	        cuserMoney=rescuser;
	    }
	    //优惠券消费金额=订单消费金额-优惠券支付金额
	    BigDecimal resCuserMoney = BigDecimal.valueOf(Double.valueOf(paramMap.get("money")+"")).subtract(cuserMoney);
	    ledgerBean.setConsumeMoney(resCuserMoney.doubleValue());
	    
	    //会员卡消费金额
	    ledgerBean.setCardMoney(Double.valueOf(paramMap.get("amount")+""));
	}
	
}