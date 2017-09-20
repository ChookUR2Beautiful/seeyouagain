package com.xmniao.common;

import java.lang.reflect.Field;

public class XmnConstants {

	/**
	 * 0 消费（美食）订单分账
	 */
	public static final int RTYPE_0 = 0;
	
	/**
	 * 1 退款
	 */
	public static final int RTYPE_1 = 1;
    
	/**
     * 2 充值
     */
	public static final int RTYPE_2 = 2;
    
	/**
     * 3 赠送(赠送用户，金额写入赠送字段，不可提现)
     */
	public static final int RTYPE_3 = 3;
    
	/**
     * 4 积分(活动)
     */
	public static final int RTYPE_4 = 4;

	/**
     * 5 消费
     */
	public static final int RTYPE_5 = 5;

	/**
     * 6 提现
     */
	public static final int RTYPE_6 = 6;
	
    /**
     * 7 营销平台赠送( 赠送商户，金额写入佣金字段，可提现)
     */
	public static final int RTYPE_7 = 7;
	
    /**
     * 8 平台退款(打款失败退款到余额)
     */
	public static final int RTYPE_8 = 8;

    /**
     * 9 订单退款 手续费(营收)
     */
	public static final int RTYPE_9 = 9;

    /**
     * 10 资金归集(子商户将资金归集到 连锁店帐号)
     */
	public static final int RTYPE_10 = 10;

    /**
     * 11 打赏
     */
	public static final int RTYPE_11 = 11;

    /**
     * 12 平台补贴(补贴给用户赠送钱包)，不可提现
     */
	public static final int RTYPE_12 = 12;

    /**
     * 13 平台发放佣金(发放到用户佣金钱包，用于市场推广 发放给(服务员 业务员 等)佣金使用。可提现
     */
	public static final int RTYPE_13 = 13;
 
	/**
     * 14 取消支付(退回 钱包金额，记录从钱包扣除的金额，如果订单全部金额是使用第三方支付 则无需记录)
     */
	public static final int RTYPE_14 = 14;
	
	/**
     * 15 平台取消提现(用于取消用户 商户 合作商 提现申请，将已申请提现金额写回钱包)
     */
	public static final int RTYPE_15 = 15;
	
    /**
     * 16 平台扣款(扣除刷单款项或扣除罚金 或补贴发放错误 撤销发放使用)
     */
	public static final int RTYPE_16 = 16;
	
    /**
     * 17 平台发员工福利(赠送用户，金额写入赠送字段，不可提现)
     */
	public static final int RTYPE_17 = 17;
	
    /**
     * 18 用户错账处理(如 多支付订单款项由公司承担，然后通过打入返利 还给用户。)
     */
	public static final int RTYPE_18 = 18;
    
    /**
     * 19 调单(减扣)(已分账订单，调单时扣除金额使用)
     */
	public static final int RTYPE_19 = 19;

    /**
     * 20 调单(增加)(已分账订单，调单时增加金额使用)
     */
	public static final int RTYPE_20 = 20;

    /**
     * 21 扣除分账金额(已分账订单，退款时扣除已分账数据使用)
     */
	public static final int RTYPE_21 = 21;
	
    /**
     * 22 线下积分订单分账
     */	
	public static final int RTYPE_22 = 22;
    
    /**
     * 23 商品销售成本返还
     */
	public static final int RTYPE_23 = 23;

    /**
     * 24 saas销售分账
     */
	public static final int RTYPE_24 = 24;

    /**
     * 25 购买套餐订单
     */
	public static final int RTYPE_25 = 25;
	
    /**
     * 26 押金返还
     */
	public static final int RTYPE_26 = 26;
	
    /**
     * 27 押金提现
     */
	public static final int RTYPE_27 = 27;
	
	
    /**
     * 28 积分(抽奖活动赠送)
     */
	public static final int RTYPE_28 = 28;
	
    /**
     * 29 积分(签到活动赠送)
     */
	public static final int RTYPE_29 = 29;
	
    /**
     * 30 积分(分享活动赠送)
     */
	public static final int RTYPE_30 = 30;
	
    /**
     * 31 钱包金额内部转移 
     * RTYPE_31与EX_RECORD_TYPE_8对应
     */
	public static final int RTYPE_31 = 31;
	
	/**
	 * 32 寻蜜客余额转出
	 */
	public static final int RTYPE_32 = 32;
	/**
	 * 33 扣除线下商品订单分账金额(已分账订单，退款时扣除已分账数据使用)
	 */
	public static final int RTYPE_33 = 33;
	
	/**
	 * 34 线上积分订单分账
	 */
	public static final int RTYPE_34 = 34;
	
	/**
	 * 35 注册送积分
	 */
	public static final int RTYPE_35 = 35;
	
	/**
	 *36 鸟蛋提现
	 */
	public static final int RTYPE_36 = 36;
	
	/**
	 * 37 鸟币充值
	 */
	public static final int RTYPE_37 = 37;
	
	/**
	 * 38 积分购买直播礼物
	 */
	public static final int RTYPE_38 = 38;
	
	/**
	 * 39 商家金额退回
	 */
	public static final int RTYPE_39 = 39;
	
	/**
	 * 40 平台每日分红
	 */
	public static final int RTYPE_40 = 40;
	
	/**
	 * 41 商家推荐奖励
	 */
	public static final int RTYPE_41 = 41;
	
	/**
	 *42  充值分账
	 */
	public static final int RTYPE_42 = 42;
	
	/**
	 *43 扩展钱包综合收益转入
	 */
	public static final int RTYPE_43 = 43;
	
	
	/**
	 *44 V客推荐转入 
	 */
	public static final int RTYPE_44 = 44;
	
	
	/**
	 *45 V客红包转入
	 */
	public static final int RTYPE_45 = 45;
	
	
	/**
	 *46 壕赚VIP红包转入
	 */
	public static final int RTYPE_46 = 46;
	
	
	/**
	 *47 壕赚商户充值红包转入
	 */
	public static final int RTYPE_47 = 47;
	
	
	/**
	 *48 V客创业管理奖金转入
	 */
	public static final int RTYPE_48 = 48;
	
	
	/**
	 * 49 购买套餐
	 */
	public static final int RTYPE_49 = 49;

	
	/**
	 * 50 购买粉丝券
	 */
	public static final int RTYPE_50 = 50;

	/**
	 * 51 V客兑换SAAS套餐
	 */
	public static final int RTYPE_51 = 51;
	
	
	/**
	 * 52 寻蜜客签约收益转出
	 */
	private static final Integer RTYPE_52 = 52;
	
	/**
	 * 53 寻蜜客分账收益
	 */
	private static final Integer RTYPE_53 = 53;
	
	/**
	 * 54 V客分账收益
	 */
	private static final Integer RTYPE_54 = 54;
	
	/**
	 * 55 中脉分账收益
	 */
	private static final Integer RTYPE_55 = 55;
	
	/**
	 * 56 V客打赏分账收益
	 */
	private static final Integer RTYPE_56 = 56;
	
	/**
	 * 57 黄金庄园花蜜转入
	 */
	private static final Integer RTYPE_57 = 57;
	
	/**
	 * 58 平台发放收益
	 */
	private static final Integer RTYPE_58 = 58;
	
	/**
	 * 59 商品套餐分账
	 */
	private static final Integer RTYPE_59 = 59;
	
/* -------------------------------------------------------------------------- */
	
	/**
	 * 0  寻蜜客分账
	 */
	public static final int XMER_RTYPE_0 = 0;
	
	/**
	 * 1 寻蜜客转出
	 */
	public static final int XMER_RTYPE_1 = 1;
	
	/**
	 * 2 Saas 分账
	 */
	public static final int XMER_RTYPE_2 = 2;
	
	
	/**
	 * 3 分账返回
	 */
	public static final int XMER_RTYPE_3 = 3;
	
	
	/**
	 * 4 SAAS分账扣回
	 */
	public static final int XMER_RTYPE_4 = 4;
	
	/* -------------------------------------------------------------------------- */
	
	
	/**
	 * 0  直播鸟豆充值
	 */
	public static final int LIVE_RTYPE_0 = 0;
	/**
	 * 1 直播鸟蛋转出
	 */
	public static final int LIVE_RTYPE_1 = 1;
	/**
	 * 2 直播赠送礼物
	 */
	public static final int LIVE_RTYPE_2 = 2;
	/**
	 * 3 直播发送私信
	 */
	public static final int LIVE_RTYPE_3 = 3;
	/**
	 * 直播 4 发送弹幕
	 */
	public static final int LIVE_RTYPE_4 = 4;
	/**
	 * 5 主播礼物收入
	 */
	public static final int LIVE_RTYPE_5 = 5;
	/**
	 * 6 主播私信收入
	 */
	public static final int LIVE_RTYPE_6 = 6;
	/**
	 * 7 主播弹幕收入
	 */
	public static final int LIVE_RTYPE_7 = 7;
	/**
	 * 8 购买粉丝券
	 */
	public static final int LIVE_RTYPE_8 = 8;
	/**
	 * 9 主播卖出粉丝券分账收入
	 */
	public static final int LIVE_RTYPE_9 = 9;
	/**
	 * 10用户使用粉丝券主播分账收入
	 */
	public static final int LIVE_RTYPE_10 = 10;
	/**
	 * 11 主播购买红包
	 */
	public static final int LIVE_RTYPE_11 = 11;
	/**
	 * 12 用户领取主播红包
	 */
	public static final int LIVE_RTYPE_12 = 12;
	/**
	 * 13 红包为领完金额返还
	 */
	public static final int LIVE_RTYPE_13 = 13;
	/**
	 * 14  推荐壕友奖励鸟币
	 */
	public static final int LIVE_RTYPE_14 = 14;
	/**
	 * 15 鸟币消费买单
	 */
	public static final int LIVE_RTYPE_15 = 15;
	
	/**
	 * 16注册送鸟豆
	 */
	public static final int LIVE_RTYPE_16 = 16;
	
	/**
	 * 17平台扣除鸟币/鸟豆/鸟蛋
	 */
	public static final int LIVE_RTYPE_17 = 17;
	
	/**
	 * 18平台退还鸟币/鸟豆/鸟蛋
	 */
	public static final int LIVE_RTYPE_18 = 18;
	
	/**
	 * 19平台发红包
	 */
	public static final int LIVE_RTYPE_19 = 19;
	
	/**
	 * 20商家释放储值卡（专享鸟币转移至公共鸟币）
	 */
	public static final int LIVE_RTYPE_20 = 20;
	
	/**
	 * 购买鸟币商品
	 */
	public static final int LIVE_RTYPE_22 = 22;
	
	/**
	 * 赠送主播礼物
	 */
	public static final int LIVE_RTYPE_23 = 23;
	
	
	/**
	 * 赠送主播套餐券
	 */
	public static final int LIVE_RTYPE_24 = 24;
	
	
	/**
	 * 购买套餐券
	 */
	public static final int LIVE_RTYPE_25 = 25;
	
	/**
	 * 购买鸟粉专享卡（支出）
	 */
	public static final int LIVE_RTYPE_26 = 26;

	/**
	 * 清空钱包
	 */
	public static final int LIVE_RTYPE_27 = 27;
	
	/**
	 * 购买鸟粉专享卡（收入）
	 */
	public static final int LIVE_RTYPE_28 = 28;

	/**
	 * 购买鸟粉专享卡（奖励）
	 */
	public static final int LIVE_RTYPE_29 = 29;
	
	/**
	 * 丢失礼物鸟蛋返还
	 */
	public static final int LIVE_RTYPE_30 = 30;
	
	/**
	 * 庄园发鸟币红包
	 */
	public static final int LIVE_RTYPE_31 = 31;
	
	/**
	 * 庄园领鸟币红包
	 */
	public static final int LIVE_RTYPE_32 = 32;
	
	/**
	 * 庄园退鸟币红包
	 */
	public static final int LIVE_RTYPE_33 = 33;
	
	/**
	 * 庄园花蜜兑换鸟币
	 */
	public static final int LIVE_RTYPE_34 = 34;
	
	/**
	 * 庄园消费鸟币
	 */
	public static final int LIVE_RTYPE_35 = 35;
	
	/**
	 * 园友赠送鸟币
	 */
	public static final int LIVE_RTYPE_36 = 36;
	
	/**
	 * 园友领取赠送鸟币
	 */
	public static final int LIVE_RTYPE_37 = 37;
	
	/**
	 * 大赛推荐奖励
	 */
	public static final int LIVE_RTYPE_38 = 38;
	
	/**
	 * 大赛平台奖励
	 */
	public static final int LIVE_RTYPE_39 = 39;
	
	/**
	 * 扩展钱包是否禁止转出 0：否
	 */
	public static final Byte  FORBIDTRANSFER_1 = Byte.valueOf("1");
	
	/**
	 * 综合收益
	 */
	public static final int EX_RTYPE_0 = 0;
	 
	/**
	 *V客推荐，
	 */
	public static final int EX_RTYPE_1 = 1;
	
	/**
	 *V客红包
	 */
	public static final int EX_RTYPE_2 = 2;
	
	/**
	 *壕赚VIP红包
	 */
	public static final int EX_RTYPE_3 = 3;
	
	/**
	 *壕赚商户充值红包
	 */
	public static final int EX_RTYPE_4 = 4;
	
	
	/**
	 *V客创业管理奖金
	 */
	public static final int EX_RTYPE_5 = 5;
	
	/**
	 *寻蜜客签约收益
	 */
	public static final int EX_RTYPE_6 = 6;
	
	/**
	 *寻蜜客分账收益
	 */
	public static final int EX_RTYPE_7 = 7;
		
	/** 
	 *V客分账收益
	 */
	public static final int EX_RTYPE_8 = 8;
	
	/**
	 *中脉分账收益
	 */
	public static final int EX_RTYPE_9 = 9;
	
	
	/**
	 * V客打赏分账收益
	 */
	public static final int EX_RTYPE_10 = 10;
	
	/**
	 * 黄金庄园花蜜转入
	 */
	public static final int EX_RTYPE_11 = 11;
	
	/**
	 * 平台发放收益  (私有化,数据手动操作)
	 */
	private static final int EX_RTYPE_12 = 12;
	
	/**
	 * 积分超市商品分账钱包
	 */
	public static final int EX_RTYPE_13 = 13;
	
	/**
	 * 未知
	 */
	public static final int EX_RECORD_TYPE_0 = 0;
	
	/**
	 * 增加余额
	 */
	public static final int EX_RECORD_TYPE_1 = 1;
	
	/**
	 * 扣减余额（用于非转出扣余额，如手动扣减）
	 */
	public static final int EX_RECORD_TYPE_2 = 2;
	
	/**
	 * 转出到寻蜜鸟钱包余额(可提现)
	 */
	public static final int EX_RECORD_TYPE_3 = 3;
	
	/**
	 * 转出到寻蜜鸟钱包余额(不可提现)
	 */
	public static final int EX_RECORD_TYPE_4 = 4;
	
	/**
	 * 冻结
	 */
	public static final int EX_RECORD_TYPE_5 = 5;
	
	/**
	 * 解冻
	 */
	public static final int EX_RECORD_TYPE_6 = 6;
	
	/**
	 * 扣手续费
	 */
	public static final int EX_RECORD_TYPE_7 = 7;
	
	/**
	 * 初始化 (创建扩展钱包,从寻蜜鸟钱包转移到拓展钱包,一个用户只有一次操作)
	 * RTYPE_31与EX_RECORD_TYPE_8对应
	 */
	public static final int EX_RECORD_TYPE_8 = 8;
		
	/**
	 * sass签约分账扣回
	 */
	public static final int EX_RECORD_TYPE_9 = 9;

	/**
	 * 主播签店消费V客获得分账
	 */
	public static final int EX_RECORD_TYPE_10 = 10;

	/* rtype对应描述 */
	public static String getRtypeString(int rtype){
		String remarks = null;
		switch(rtype){
		case 0:
			remarks="分账";
			break;
		case 1:
			remarks="订单退款";
			break;
		case 2:
			remarks="充值";
			break;
		case 3:
			remarks="赠送";
			break;
		case 4:
			remarks="赠送积分";
			break;
		case 6:
			remarks="提现转出";
			break;
		case 7:
			remarks="平台赠送";
			break;
		case 8:
			remarks="平台退款";
			break;
		case 9:
			remarks="用户退款手续费";
			break;
		case 10:
			remarks="资金归集";
			break;
		case 11:
			remarks="打赏";
			break;
		case 12:
			remarks="平台补贴";
			break;
		case 13:
			remarks="平台发放佣金";
			break;
		case 14:
			remarks="支付退回";
			break;
		case 15:
			remarks="取消提现";
			break;
		case 16:
			remarks="平台扣款";
			break;
		case 17:
			remarks="平台发送福利";
			break;
		case 18:
			remarks="错账处理";
			break;
		case 19:
			remarks="调单扣回";
			break;
		case 20:
			remarks="调单收入";
			break;
		case 21:
			remarks="用户退款";
			break;
		case 22:
			remarks="线下积分订单分账";
			break;
		case 23:
			remarks="销售成本返还";
			break;
		case 24:
			remarks="SAAS分账";
			break;
		case 25:
			remarks="缴纳押金";
			break;
		case 26:
			remarks="返还押金";
			break;
		case 27:
			remarks="押金提现";
			break;
		case 28:
			remarks="抽奖赠送";
			break;
		case 29:
			remarks="签到赠送";
			break;
		case 30:
			remarks="分享赠送";
			break;
		case 31:
			remarks="内部整理";
			break;
		case 32:
			remarks="寻蜜客转入";
			break;
		case 33:
			remarks="线下积分订单退款";
			break;
		case 34:
			remarks="线上积分订单分账";
			break;
		case 35:
			remarks="注册赠送";
			break;
		case 36:
			remarks="鸟蛋提现";
			break;
		case 37:
			remarks="鸟币充值";
			break;
		case 38:
			remarks="购买直播礼物";
			break;
		case 39:
			remarks="商家金额退回";
			break;
		default :
			break;
		}
		return remarks;
	}

	public static Integer getExpansionType2rType(int intValue) {
		switch (intValue) {
		case 0:
			return RTYPE_43;
		case 1:
			return RTYPE_44;
		case 2:
			return RTYPE_45;
		case 3:
			return RTYPE_46;
		case 4:
			return RTYPE_47;
		case 5:
			return RTYPE_48;
		case 6:
			return RTYPE_52;
		case 7:
			return RTYPE_53;
		case 8:
			return RTYPE_54;
		case 9:
			return RTYPE_55;
		case 10:
			return RTYPE_56;
		case 11:
			return RTYPE_57;
		case 12:
			return RTYPE_58;
		case 13:
			return RTYPE_59;
		default:
			break;
		}
		return null;
	}

	/**
	 * 
	 * 方法描述：获取EXTYPE的最大值
	 * 创建人：jianming  
	 * 创建时间：2017年5月3日 下午3:37:12   
	 * @return
	 */
	public static Integer getMinType(String typeName) {
		XmnConstants xmnConstants = new XmnConstants();
		Class clz = xmnConstants.getClass();
		Field field=null;
		Integer i=null;
		for(Field f : clz.getFields()){
			if(f.getName().startsWith(typeName)&&f.getName().length()<=typeName.length()+3){
				String name = f.getName();
				Integer integer = new Integer(name.substring(typeName.length()));
				if(i==null||integer<i){
					field=f;
					i=integer;
				}
			}
		}
		try {
			return field.getInt(xmnConstants);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 
	 * 方法描述：获取EX_RTYPE最大值
	 * 创建人：jianming  
	 * 创建时间：2017年5月3日 下午3:56:10   
	 * @return
	 */
	public static Integer getMaxType(String typeName) {
		try {
		Class clz = Class.forName("com.xmniao.common.XmnConstants");
		Object xmnConstants = clz.newInstance();
		Field field=null;
		Integer i=null;
		for(Field f : clz.getFields()){
			if(f.getName().startsWith(typeName)&&f.getName().length()<=typeName.length()+3){
				String name = f.getName();
				Integer integer = new Integer(name.substring(typeName.length()));
				if(i==null||integer>i){
					field=f;
					i=integer;
				}
			}
		}
			return field.getInt(xmnConstants);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String getUserWalletRecordList3_RTYPE(Integer i){
		switch (i) {
		case 0:
			return "消费（美食）订单";
		case 1:
			return "退款";
		case 2:
			return "充值";
		case 3:
			return "赠送";
		case 4:
			return "积分";
		case 5:
			return "消费";
		case 6:
			return "提现";
		case 7:
			return "营销平台赠送";
		case 8:
			return "平台退款";
		case 9:
			return "订单退款 手续费(营收)";
		case 10:
			return "资金归集";
		case 11:
			return "打赏";
		case 12:
			return "平台补贴";
		case 13:
			return "平台发放佣金";
		case 14:
			return "取消支付";
		case 15:
			return "平台取消提现";
		case 16:
			return "平台扣款";
		case 17:
			return "平台发员工福利";
		case 18:
			return "用户错账处理";
		case 19:
			return "调单(减扣)";
		case 20:
			return "调单(增加)";
		case 21:
			return "扣除分账金额";
		case 22:
			return "线下积分订单分账";
		case 23:
			return "商品销售成本返还";
		case 24:
			return "saas销售分账";
		case 25:
			return "押金缴纳";
		case 26:
			return "押金返还";
		case 27:
			return "押金提现";
		case 28:
			return "积分(抽奖活动赠送)";
		case 29:
			return "积分(签到活动赠送)";
		case 30:
			return "积分(分享活动赠送)";
		case 31:
			return "钱包金额内部转移";
		case 32:
			return "寻蜜客余额转出";
		case 33:
			return "扣除线下商品订单分账金额";
		case 34:
			return "线上积分订单分账";
		case 35:
			return "注册送积分";
		case 36:
			return "鸟蛋提现";
		case 37:
			return "鸟币充值";
		case 38:
			return "积分购买直播礼物";
		case 39:
			return "商家金额退回";
		case 40:
			return "平台每日分红";
		case 41:
			return "商家推荐奖励";
		case 42:
			return "充值分账";
		case 43:
			return "扩展钱包综合收益";
		case 44:
			return "V客推荐";
		case 45:
			return "V客红包";
		case 46:
			return "壕赚VIP红包";
		case 47:
			return "壕赚商户充值红包";
		case 48:
			return "V客创业管理奖金";
		case 49:
			return "购买套餐";
		case 50:
			return "购买粉丝券";
		case 51:
			return "V客兑换SAAS套餐";
		case 52:
			return "寻蜜客签约收益转出";
		case 53:
			return "寻蜜客分账收益";
		case 54:
			return "V客分账收益";
		case 55:
			return "中脉分账收益";
		default:
			return "未知";
		}
	}
	
	
	public static String getUserWalletRecordList3_LIVE_RTYPE(Integer i){
		switch (i) {
		case 0:
			return "直播鸟豆充值";
		case 1:
			return "直播鸟蛋转出";
		case 2:
			return "直播赠送礼物";
		case 3:
			return "直播发送私信";
		case 4:
			return "发送弹幕";
		case 5:
			return "主播礼物收入";
		case 6:
			return "主播私信收入";
		case 7:
			return "主播弹幕收入";
		case 8:
			return "购买粉丝券";
		case 9:
			return "主播卖出粉丝券分账收入";
		case 10:
			return "用户使用粉丝券主播分账收入";
		case 11:
			return "主播购买红包";
		case 12:
			return "用户领取主播红包";
		case 13:
			return "红包为领完金额返还";
		case 14:
			return "推荐壕友奖励鸟币";
		case 15:
			return "买单消费";
		case 16:
			return "注册送鸟豆";
		case 17:
			return "平台扣除";
		case 18:
			return "平台退还";
		case 19:
			return "平台发红包";
		case 20:
			return "商家释放储值卡";
		case 22:
			return "购买鸟币商品";
		case 23:
			return "赠送主播礼物";
		case 24:
			return "赠送主播套餐券";
		case 25:
			return "购买套餐券";
		case 26:
			return "购买鸟粉专享卡";
		case 28:		
			return "购买鸟粉专享卡";
		case 29:
			return "购买鸟粉专享卡";
		case 51:
			return "V客兑换SAAS套餐";
		case 30:
			return "丢失礼物鸟蛋返还";
		case 31:
			return "庄园发鸟币红包";
		case 32:
			return "庄园领鸟币红包";
		case 33:
			return "庄园退鸟币红包";
		case 34:
			return "庄园花蜜兑换鸟币";
		case 35:
			return "庄园消费鸟币";
		case 36:
			return "园友赠送鸟币";
		case 37:
			return "园友领取赠送鸟币";
		case 38:
			return "大赛推荐奖励";
		case 39:
			return "大赛平台奖励";
		default:
			return "未知";
		}
	}
	
	public static String getUserWalletRecordList3_EX_RTYPE(Integer i){
		switch (i) {
		case 0:
			return "综合收益";
		case 1:
			return "V客推荐";
		case 2:
			return "V客红包";
		case 3:
			return "壕赚VIP红包";
		case 4:
			return "壕赚商户充值红包";
		case 5:
			return "V客创业管理奖金";
		case 6:
			return "寻蜜客签约收益";
		case 7:
			return "寻蜜客分账收益";
		case 8:
			return "V客分账收益";
		case 9:
			return "中脉分账收益";
		case 10:
			return "V客分账";
		case 11:
			return "黄金庄园";
		case 12:
			return "平台发放收益";
		case 13:
			return "商品消费分账";
		default:
			return "未知";
		}
		
	}
	
	
	public static String getUserWalletRecordList3_EX_RECORD_TYPE(Integer i){
		switch (i) {
		case 0:
			return "未知";
		case 1:
			return "增加余额";
		case 2:
			return "扣减余额";
		case 3:
			return "转出";
		case 4:
			return "转出";
		case 5:
			return "冻结";
		case 6:
			return "解冻";
		case 7:
			return "手续费";
		default:
			return "未知";
		}
	}
	
}
