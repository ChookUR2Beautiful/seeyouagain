package com.xmniao.common;

/**
 * 融通提现参数配置表
 * 
 * @author DongJieTao
 * 
 */
public class RoogTWithdrawMConfig {

	static String batchVersion = "00"; // 版本号

	static String signType = "MD5"; // 加密方式

	// static String batchBizid="100000000006764"; //合作伙伴在融宝的用户ID 测试
	static String batchBizid = "100000000007696"; // 合作伙伴在融宝的用户ID

	// static String
	// key="07aae5a163a28a3b4dag2e4d958b9d8345c21ee84e5743daf39479634f52e2d9";
	// //安全校验码 测试
	static String key = "79a7d1g11egbbd172dfdc0430c5a8f369520g7b9ad038c322ff9fe2agdc2cag1"; // 安全校验码

	static String _input_charset = "gbk"; // 编码gbk, utf8

	static String batchBiztype = "00000"; // 5位，默认00000

	/*
	 * batchContent
	 * 序号,银行账户,开户名,开户行,分行,支行,公/私,金额,币种,省,市,手机号,证件类型,证件号,用户协议号,商户订单号,备注
	 */

}
