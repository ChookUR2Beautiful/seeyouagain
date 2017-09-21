package com.xmniao.main;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.LedgerRation;
import com.xmniao.common.PaymentType;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.dao.ledger.LedgerService;
import com.xmniao.domain.ledger.LedgerBean;
import com.xmniao.service.ledger.LedgerServiceImpl;

/**
 * 分账服务接口测试类
 * @author  HuangXiaobin
 * @version  [版本号, 2014年11月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LedgerServiceMain {

	/**
	 * 分账计算类构造
	 * @param userId 会员ID
	 * @param consumeMoney 会员消费金额（订单金额）
	 * @param sellerId	商户ID
	 * @param baseagio	商户折扣
	 * @param mikeId	向蜜客ID
	 * @param isEffective	向蜜客有效标识(0或null 正常,1为个人向蜜客过期)
	 * @param memberJointId	会员所属合作商ID
	 * @param memberSalesmanId	会员所属合作商 的业务员ID
	 * @param consumeJointid	会员消费商户所属合作商ID
	 * @param consumeSalesmanId	会员消费商户所属合作商 的业务员ID
	 * @param memberSigningType 所属合作商是否总部签约(0或null:不是，1：是)
	 * @param consumeSigningType 消费合作商是否总部签约(0或null:不是，1：是)
	 * @param payType	支付方式
	 * @param memberSalesmanRation 所属合作商 员工分账比例
	 * @param consumeSalesmanRation 消费合作商 员工分账比例
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		LedgerBean bean = new LedgerBean();
		LedgerService service = new LedgerServiceImpl();
		
		bean.setUserId(323279);	// 会员编号
		
		bean.setConsumeMoney(100.00); // 消费金额
		
		bean.setSellerId(8620); // 商户编号
		
		bean.setBaseagio(0.80);// 商户折扣
		
		bean.setMikeId(23586); // 寻蜜客编号
		
		// 用户区域合作商编号
		bean.setMemberJointId(10065); 
		// 用户区域合作商业务员编号
		bean.setMemberSalesmanId(341); 
		
		// 消费区域合作商编号
		bean.setConsumeJointid(10035);
		// 消费区域合作商编号
		bean.setConsumeSalesmanId(166);
		// 支付渠道 
		bean.setPayType("1000001");
		
		// 用户区域合作商业务员提成比例
		bean.setMemberSalesmanRation(0.05);
		// 消费区域合作商业务员提成比例
		bean.setConsumeSalesmanRation(0);
		
		// 用户所属商户是否平台签约 0 否 1是
		bean.setMemberSigningType("0");
		// 消费商户是否平台签约 0 否 1是
		bean.setConsumeSigningType("0");
		// 平台补助比例
		bean.setSubsidy(0);
		
		bean.setConsumeArea("1969");
		bean.setMemberArea("1991");
		
		bean.setIsPlatFeesAll("1");
	//	bean.setPlatRation(0.2);
		Map<String,Object> map = service.getLedgerMoney(bean);
		System.out.println(JSONObject.toJSONString(map));
		
//		int ledgerRation = 100 - (int)(bean.getBaseagio() * 100);
//		int userRation =  (int)PreciseComputeUtil.mul(ledgerRation,
//				Double.valueOf(LedgerRation.getRationByName("userRation")));
//		
//		double userMoney = PreciseComputeUtil.mul(bean.getConsumeMoney(),userRation) * 0.01;
//		int mikeRation =  (int)PreciseComputeUtil.mul(ledgerRation,
//				Double.valueOf(LedgerRation.getRationByName("mikeRation")));
//		double mikeMoney = PreciseComputeUtil.mul(bean.getConsumeMoney(),mikeRation) * 0.01;
//		int jointRation =  (int)PreciseComputeUtil.mul(ledgerRation,
//				Double.valueOf(LedgerRation.getRationByName("bpartner_ration")) * 2);
//		double jointMoney = PreciseComputeUtil.mul(bean.getConsumeMoney(),jointRation) * 0.01;
//		
//		int xmniaoRation = ledgerRation - (userRation + mikeRation + jointRation);
//		double xmniaoMoney = PreciseComputeUtil.mul(bean.getConsumeMoney(),xmniaoRation) * 0.01;
//		System.out.println("供分账比例："+ledgerRation+"%("+userRation+"% + "+mikeRation+"% + "+jointRation+"% + "+xmniaoRation+ "%)");
//		System.out.println("手续费:"+ bean.getConsumeMoney() * Double.valueOf(PaymentType.getRateById(bean.getPayType())) +
//				"("+bean.getConsumeMoney()+ " * " + Double.valueOf(PaymentType.getRateById(bean.getPayType()))+")");
//		System.out.println("商户营收："+ (int)(bean.getBaseagio() * 100) +"%,金额："+bean.getConsumeMoney() * bean.getBaseagio());
//		System.out.println("用户返现比例："+ userRation+"%,金额:"+userMoney);
//		System.out.println("向蜜客分账比例："+ mikeRation+"%,金额:"+ mikeMoney);
//		System.out.println("合作商分账比例："+ jointRation+"%,金额:"+ jointMoney);
//		System.out.println("平台分账比例："+ xmniaoRation+"%,金额:"+ xmniaoMoney);
	}
}
