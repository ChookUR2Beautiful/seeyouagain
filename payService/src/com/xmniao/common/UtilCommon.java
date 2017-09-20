package com.xmniao.common;

import java.math.BigDecimal;

public class UtilCommon {
	public static final BigDecimal ZERO = new BigDecimal("0");
	public static final String TYPE_USER = "1";
	public static final String TYPE_SELLER = "2";
	public static final String TYPE_JOINT = "3";
	
	public static final double FREE_EXPENSES_QUOTA = 500;
	
	//对应com.xmniao.entity.Wallet.java中字段名
	//商户扣款规则 commision -> sellerAmount
	public static final String[] SELLER_DEDUCTIONS_ARRAY = {"commision", "sellerAmount"};
	//用户扣款规则 balance -> zbalance
	public static final String[] USER_DUCTIONS_ARRAY = {"balance","zbalance"};
	//合作商扣款规则 sellerAmount
	public static final String[] JOINT_DEDUCTIONS_ARRAY = {"sellerAmount"};
	
	
	//BigDecimal转负数
	public static BigDecimal bigDecimalTrunNegative(BigDecimal bigDecimal){
		return bigDecimal.compareTo(ZERO)<0?new BigDecimal("-"+bigDecimal.toString()):bigDecimal;
		
	}
	
	
	public static Integer getWalletExpansionForbidTransfer(Integer type){
		if(type==XmnConstants.EX_RTYPE_2){
			return 1;
		}else{
			return 0;
		}
	}
	
}
