package com.xmniao.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.alibaba.fastjson.JSON;
import com.xmniao.thrift.ledger.SellerStatistics;
import com.xmniao.thrift.ledger.SynthesizeService;
import com.xmniao.thrift.ledger.SynthesizeService.Client;

/**
 * 测试通用功能
 * 
 * @author ChenBo
 * 
 */
public class SynthesizeThriftClient {
	// 服务端的IP地址
	private static final String IP_NUMBER = "192.168.50.110";
//	private static final String IP_NUMBER = "192.168.20.253";//"localhost";
	// private static final String IP_NUMBER = "192.168.50.101";
//	 private static final String IP_NUMBER = "localhost";
	// 服务端的端口号
	private static final int PORT = 7911;

	private static TTransport transport = null;

	public static void main(String[] args) {
		try {
			Logger log = Logger.getLogger(SynthesizeThriftClient.class);

			// 设置调用的服务地址为本地，端口为 7911
			transport = new TSocket(IP_NUMBER, PORT);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
					protocol, "SynthesizeService");
			SynthesizeService.Client client = new Client(ManagerProtocol);

			// 打开端口,开始调用
			transport.open();

			long allResult = 0;

			int times = 1;
			Map<String, String> walletBalance = client.getWalletBalance("31379",2);
			System.out.println(walletBalance);

			for (int i = 0; i < times; i++) {

				Thread.sleep(1);

				long sdate = System.currentTimeMillis();
					
				// 1.测试新增钱包账号功能
				
//				 int msg = client.addWallet("88418", "1", "","张三丰");
//				 System.out.println("msg:"+msg);
//				 
//
//				// 2.测试钱包余额查询接口
				/* Map<String,String> map1 = client.getWalletBalance("18081", 2);
				 System.out.println(map1.toString());*/
//				 
			/* Map<String,String> map1 = client.getWalletBalance("31781", 2);
				 System.out.println(JSON.toJSONString(map1));*/
				 
				 /*	 Map<String,String> map2 = client.getWalletBalance("1916299", 3);
				 System.out.println(JSON.toJSONString(map2));*/

				// 3.测试添加银行卡
				// 参数 int isuse, String uId, int type, String account, int
				// cardType,
				// 参数 String userName, String bankName, String mobileId, int
				// userType,
				// 参数 int ispublic, int idtype, String identity, String bank,
				// String abbrev, String province, String cityname
				
				 /*int resultStatus = client.AddMentionAccount( 0, "4654", 2,
				 "6222444444441111", 2, "李谋", "中国农业银行上步支行", "13444444444", 1,
				 1, 1, "452201188888888888", "中国农业银行", "ABC", "广东省", "深圳");
				 System.out.println("resultStatus:"+resultStatus);*/
				 

				// 4.测试获取银行卡
//				 List<Map<String,String>> list =
//				 client.getMentionAccount("8888",1);
//				 System.out.println(JSON.toJSONString(list));

				// 5.测试更新银行卡
				 /*int result2 = client.updateMentionAccount( "40", 1,
				 "6222888888889998", 1, "吕玲绮", "中国农业银行和平支行", "",0,
				 "广西壮族自治区", 0, 1, "1", "452201199000101100", "中国农业银行", "ABC");
				 System.out.println("修改银行卡信息result:"+result2);*/

				// 6.删除银行/支付账号
				// int del = client.delMentionAccount("0");
				// System.out.println(del);

			/*	// 7.测试验证是否第一次提现
				 int check = client.checkWallet("31786","2");
				System.out.println(check);
				
				
*/
//				 8.测试修改钱包余额（分账接口）
//
//				 List<Map<String, String>> list1 = new ArrayList<Map<String,
//				 String>>();
//				
//				 Map<String, String> tempmap = new HashMap<String, String>();
//				 tempmap.put("orderId", "250124000129");
//				 tempmap.put("uId", "129");
//				 tempmap.put("uMoney", "4");
//				 tempmap.put("sId",
//				 "4658");
//				 tempmap.put("sMoney", ""+i);
//				 tempmap.put("mId",
//				 "4658");
//				 tempmap.put("mMoney", "1");
//				 tempmap.put("mType",
//				 "2");
//				 tempmap.put("brId", "1916299");
//				 tempmap.put("brMoney",
//				 "1");
//				 tempmap.put("crId", "1916299");
//				 tempmap.put("crMoney",
//				 "1");
//				 tempmap.put("remark", "你妈逼");
//				 list1.add(tempmap);
//
//				
//				 int updateWB = client.updateWalletBalance(list1);
//				 log.info("运行状态：" + updateWB);

				// 9.测试查询分账和提现方式
				// Map<String,String> mlMap = client.getMentionLedger(2,
				// "1001999");
				// System.out.println(mlMap);

				// 10.测试修改分账方式和提现方式
				// int updateML = client.updateMentionLedger(2, 1,
				// "1232","11111","11111");
				// System.out.println(updateML);

				// 11.修改提现状态
				// int updateWRS =
				// client.updateWithdrawalsRecordState("1643",3,1);
				// System.out.println(updateWRS);

				// 12.获取钱包密码修改次数
				// int pwdRecord = client.getUpdatePwdNum("1101","1");
				// System.out.println("uId=1101,userType=1的账号，钱包密码修改次数为:"+pwdRecord);
//
//				// 13.修改钱包密码
				 int updatePwd = client.updateWalletPwd("33512",
				 "e10adc3949ba59abbe56e057f20f883e", 2);
				 System.out.println(updatePwd);
//
//				// 14.查询钱包记录{eDate=, rtype=0, sDate=, userType=2}

				 /* Map<String, String> paramMap = new HashMap<String, String>();
				  paramMap.put("uId", "4658"); //会员或商家或合作商 id
				  paramMap.put("userType", "2"); // 用户类型 
				  paramMap.put("cPage", "1"); // 当前页码
				  paramMap.put("pageSize", "8"); // 页大小
				  paramMap.put("sDate", "2015-01-01"); // 开始时间 // （可选）（格式：yyyy-MM-dd）
				  paramMap.put("eDate", "2015-08-31"); // 结束时间（可选）（格式：yyyy-MM-dd）
				  paramMap.put("rType", "7"); // 0 分账 1 退款 2 充值 3 赠送(活动) 4  积分(活动) // 5 消费 6 提现 7 营销平台赠送( // 赠送商户，金额写入佣金字段，可提现) 默认全部 (可选)
				  paramMap.put("oType", "1"); // 0 按时间升序（默认） 1 按时间降序 (可选) //
				  //paramMap.put("userName", ""); // 用户名 （可选） WalletRecord wr =
				  //paramMap.put("phoneNumber", "");
				  WalletRecord wr =  client.getWalletRecord(paramMap); 
				  System.out.println(wr);*/
				 

				// double resultD = client.getMentionBalance("8080", "2");
				// System.out.println(resultD);

				// 测试 查询钱包记录总金额
				// double amount = client.getWRAmount("168", "1", 7);
				// System.out.println(amount);

				// 获取可提现余额
				// double resultD = client.getMentionBalance("8080", "2");
				// System.out.println(resultD);

				// 测试 查询钱包记录总金额
				// double amount = client.getWRAmount("8080", "2", 0);
				// System.out.println(amount);

				// 获取钱包列表
//				 Map<String, String> paramMap = new HashMap<String, String>();
//				 paramMap.put("cPage", "1");
//				 paramMap.put("pageSize", "20");
//				 paramMap.put("userName", "");
//				 paramMap.put("userType", "");
//				 paramMap.put("uId", "");
//				 paramMap.put("phoneNumber", "13727277777");
//				 Wallet wallet = client.getWalletList(paramMap);
//				 System.out.println("resultMap:" + wallet);
//
//				// 1.2.10.修改钱包余额
//
//				List<Map<String, String>> paramMapList = new ArrayList<Map<String, String>>();
//
//				
//				 Map<String, String> paramMap = new HashMap<String, String>();
//				 
//				 paramMapList:[{remark=, uId=大放送, zbalance=0, userType=2,
//				 commision=123.00, integral=0, amount=0, balance=0,
//				 sellerAmount=0, rType=7, orderId=150316102141597}]
//				 paramMap.put("uId", "4658"); paramMap.put("userType", "2");
//				 paramMap.put("amount", "0"); paramMap.put("balance", "0");
//				 paramMap.put("commision", "123.00"); paramMap.put("zbalance",
//				 "0"); paramMap.put("integral", "0");
//				 paramMap.put("sellerAmount", "0"); paramMap.put("orderId",
//				 "150316102141597"); paramMap.put("remark", "邓善军");
//				 paramMap.put("rType", "7");
//				 
//				 paramMapList.add(paramMap);
				
//
//				Map<String, String> paramMap1 = new HashMap<String, String>();
//
//				paramMap1.put("uId", "8080");
//				paramMap1.put("userType", "2");
//				paramMap1.put("amount", "0");
//				paramMap1.put("balance", "0");
//				paramMap1.put("commision", "0");
//				paramMap1.put("zbalance", "11212");
//				paramMap1.put("integral", "0");
//				paramMap1.put("sellerAmount", "0");
//				paramMap1.put("orderId", "" + System.currentTimeMillis()+1);
//				paramMap1.put("remark", "测试测试测试");
//				paramMap1.put("rType", "7");
//
//				paramMapList.add(paramMap1);

			/*	Map<String, String> paramMap2 = new HashMap<String, String>();

				paramMap2.put("uId", "584957");
				paramMap2.put("userType", "1");
				paramMap2.put("amount", "100");
//				paramMap2.put("balance", "0");
//				paramMap2.put("commision", "0");
//				paramMap2.put("zbalance", "0");
				paramMap2.put("option","0");
//				paramMap2.put("integral", "20");
//				paramMap2.put("sellerAmount", "0");
//				paramMap2.put("deposit", "10");
//				paramMap2.put("return_deposit", "10");
				paramMap2.put("orderId", "" + System.currentTimeMillis());
				paramMap2.put("remark", "抽取大礼包");
				paramMap2.put("rType", "38");*/

//				paramMapList.add(paramMap2);

//				Map<String, String> testMap = client
//						.updateBalance(paramMapList);
			/*	Map<String, String> testMap = client.updateWalletAmount(paramMap2);
				System.out.println(testMap);*/

				/*--------------------------------获取钱包总数接口与获取钱包修改记录总数-start-----------------------------------------------*/
				// Map<String,String> walletMap = new HashMap<String,String>();
				// walletMap.put("userName", "");
				// walletMap.put("sDate", "2015-03-20");
				// walletMap.put("eDate", "2015-03-23");
				// walletMap.put("rType", "0");
				//
				// System.out.println("钱包总数 ："+client.getWalletCount(walletMap));
				// System.out.println("钱包记录总条数:"+client.getWalletRecordCount(walletMap));
				/*---------------------------------获取钱包总数接口与获取钱包修改记录总数-end------------------------------------------------*/

				/*---------------------------------获取银行卡列表接口-start------------------------------------------------*/
				// Map<String,String> mentionAccountMap = new
				// HashMap<String,String>();
				// mentionAccountMap.put("uId", "4634");
				// mentionAccountMap.put("userType", "2");
				// mentionAccountMap.put("bankid", "6225887857477851");
				// mentionAccountMap.put("username", "方欣");
				// mentionAccountMap.put("mobileid", "15986671443");
				// mentionAccountMap.put("identity", "330327198412152860");
				// mentionAccountMap.put("cPage", "1");
				// mentionAccountMap.put("pageSize", "2");
				// MentionAccount ma =
				// client.getMentionAccountList(mentionAccountMap);
				// System.out.println("获取的银行卡列表信息 ：count="+ma.getCount()+",pageCount="+ma.getPageCount()+"\r\naccountList="+JSON.toJSONString(ma.getAccountList()));
				/*---------------------------------获取银行卡列表接口-end -------------------------------------------------*/

				// 1.2.25. 统计用户总金额
				// Map<String, String> resultMap =
				// client.getUserMoney("10591,10587,10580,18023,18007", 2, 1);
				// System.out.println("统计用户总金额"+resultMap);

				// 1.2.26. 查询商家钱包信息
				// SellerWallet sw = client.getSellerWalletList("13222146,4658",
				// 1);
				// System.out.println("查询商家钱包信息"+sw);

				// 1.2.28. 查询用户银行卡10587,10580,18023,18007
				/*
				 * List<Map<String, String>> param = new ArrayList<Map<String,
				 * String>>();
				 * 
				 * Map<String, String> map = new HashMap<>(); map.put("uId",
				 * "4658"); map.put("userType", "2");
				 * 
				 * param.add(map);
				 * 
				 * Map<String, String> map1 = new HashMap<>(); map1.put("uId",
				 * "4634"); map1.put("userType", "2");
				 * 
				 * param.add(map1);
				 * 
				 * Map<String, String> map2 = new HashMap<>(); map2.put("uId",
				 * "4584"); map2.put("userType", "2");
				 * 
				 * param.add(map2);
				 * 
				 * System.out.println(client.getMentionAccounts(param));
				 */

				/**
				 * 1.2.27. 资金归集
				 */

//				 Map<String, String> map = new HashMap<String, String>();
//				 map.put("ids", "222328");
//				 map.put("moneyType", "1");
//				 map.put("fId", "8886");
//				 map.put("orderId",
//				 String.valueOf(System.currentTimeMillis()));
//				
//				
//				 System.out.println(client.mergeMoney(map));


				// 1.2.28. 查询用户银行卡10587,10580,18023,18007
				// List<Map<String, String>> param = new ArrayList<Map<String,
				// String>>();
				//
				// Map<String, String> map = new HashMap<>();
				// map.put("uId", "4658");
				// map.put("userType", "2");
				//
				// param.add(map);
				//
				// Map<String, String> map1 = new HashMap<>();
				// map1.put("uId", "4634");
				// map1.put("userType", "2");
				//
				// param.add(map1);
				//
				// Map<String, String> map2 = new HashMap<>();
				// map2.put("uId", "4584");
				// map2.put("userType", "2");
				//
				// param.add(map2);
				//
				// System.out.println(client.getMentionAccounts(param));

				// TTest t = new TTest();
				// t.start();

/*				// 1.2.29. 平台退款(打款失败退款到余额)
				 Map<String,String> failMap = new HashMap<String,String>();
				 failMap.put("wId", "333334484"); failMap.put("wType", "1");failMap.put("type", "2");
				 System.out.println("平台退款/取消提现结果："+client.returnWithdrawals(failMap));;
*/				 

				/**
				 * 1.2.30. 提现金额统计（商户端）
				 * 
				 */

				// Map<String, String> param = new HashMap<String, String>();
				//
				// param.put("uId", "536783"); param.put("userType", "1");
				//
				// Map<String, String> resultMap =
				// client.statisticsWithdrawalsMoney(param);
				//
				// System.out.println("返回参数：" + resultMap);

				/**
				 * 1.2.31. 查询合作商月收益与提现
				 * 
				 */
				// Map<String, String> param = new HashMap<String,String>();
				// param.put("uId", "10001");
				// param.put("userType", "3");
				// param.put("months", "1504");
				//
				// Map<String, String> resultMap = client.getJointIncome(param);
				// System.out.println("结果：" + resultMap);

				/**
				 * 1.2.35.	根据时间查询钱包余额列表
				 */
				  /*Map<String,String> selectMap = new HashMap<String,String>();
				  selectMap.put("sDate", "2015-08-28");//
				  //selectMap.put("eDate", "2015-08-30");
				  selectMap.put("userType", "2");
				  selectMap.put("account", "");
				  selectMap.put("userName", "");
				  selectMap.put("cPage", "1");
				  selectMap.put("pageSize", "10");
				  Wallet wt = client.getWalletListByDate(selectMap);
				  System.out.println("数据总数："+wt.count+",总页数："+wt.pageCount+",\r\n");*/
				
				/**
				 * 调单
				 */
				 /*Map<String,String> formerMap = new HashMap<String,String>();
				 Map<String,String> laterMap = new HashMap<String,String>();
				 
				 //调单前
				 formerMap.put("orderId", "122544872");
				 formerMap.put("sellerId", "8080");
				 formerMap.put("sellerName", "张家牛腩");
				 formerMap.put("sellerAmount", "-50.0");
				 formerMap.put("mikeId", "8080");//13222136
				 formerMap.put("mikeName", "李家猪肉丸");
				 formerMap.put("mikeAmount", "2.50");
				 formerMap.put("bpartnerId", "1916290");
				 formerMap.put("bpartnerName", "泉天下");
				 formerMap.put("bpartnerAmount", "0.5");
				 formerMap.put("cpartnerId", "1916282");
				 formerMap.put("cpartnerName", "世界之星");
				 formerMap.put("cpartnerAmount", "2.0");
				 formerMap.put("memberId", "9676");
				 formerMap.put("memberName", "我叫小明");
				 formerMap.put("memberAmount", "5");
				 
				 //调单后
				 laterMap.put("sellerId", "110");
				 laterMap.put("sellerName", "张家牛腩");
				 laterMap.put("sellerAmount", "60");
				 laterMap.put("mikeId", "8080");
				 laterMap.put("mikeName", "向阳花");
				 laterMap.put("mikeAmount", "2.50");
				 laterMap.put("bpartnerId", "1916282");//1916270
				 laterMap.put("bpartnerName", "长生殿");
				 laterMap.put("bpartnerAmount", "1.00");
				 laterMap.put("cpartnerId", "1916282");
				 laterMap.put("cpartnerName", "范花园");
				 laterMap.put("cpartnerAmount", "2");
				 laterMap.put("memberAmount", "-3");
				 
				 Map<String,String> resultMap= client.modifyOrder(formerMap, laterMap);
				 System.out.println("调单结果："+resultMap);*/
				
//				Map<String,String> param = new HashMap<String,String>();
//				param.put("uId", "8090");
//				param.put("userType", "2");
//				param.put("phoneNumber", "13727244776");
//				param.put("password", "98765423109876542310987654231011");
//				Map<String,String> resultMap = client.addWalletMap(param);
//				System.out.println("新增钱包结果："+resultMap);
			
				 
				/**
				 * 平台扣款
				 */
				/*Map<String,String> deductionsMap = new HashMap<String,String>();
				//deductionsMap.put("uId", "222296");
				deductionsMap.put("userType", "2");
				deductionsMap.put("phoneNumber", "13777221819");
				deductionsMap.put("orderId", "100000012");
				deductionsMap.put("amount", "1.00");
				//deductionsMap.put("description", "弄虚作假，厚颜无耻！");
				Map<String,String> resultMap = client.platformDeductions(deductionsMap);
				System.out.println("平台扣款结果："+resultMap);*/
				
				 /**
				  * 设置钱包密码 
				  */
				 /*Map<String, String> resultMap = client.setWPwd("8890","3","JAE.....CCD.");
				 System.out.println("resultMap:"+resultMap);*/
				
				  /**
				   * 扣除分账金额
				   */
//				Map<String,String> deductionMap = new HashMap<String,String>();
//				deductionMap.put("orderId", "150128000538");
//				deductionMap.put("sellerId", "4622");deductionMap.put("sellerAmount", "2.5");
//				deductionMap.put("mikeId", "232");deductionMap.put("mikeAmount", "1");deductionMap.put("mikeType", "1");
//				deductionMap.put("bpartnerId", "10099");deductionMap.put("bpartnerAmount", "5");
//				deductionMap.put("cpartnerId", "10104");deductionMap.put("cpartnerAmount", "0.02");
//				deductionMap.put("memberId", "232");deductionMap.put("memberAmount", "0.01");
//				Map<String,String> resultMap = client.deductionsMentionLedger(deductionMap);
//				System.out.println("扣除分账金额结果："+resultMap);
				
				/**
				 * 1.2.39.	锁定/解锁钱包
				 */
				/*Map<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("uId", "27180");
				paramMap.put("userType", "2");
				paramMap.put("phoneNumber", "25412");
				paramMap.put("status", "1");
				Map<String,String> resultMap = client.setLockWallet(paramMap);
				System.out.println("锁定/解锁钱包结果："+resultMap);*/
				
				/**
				 * 1.2.40.	取消报障
				 */
				/*Map<String,String> cancelMap = new HashMap<String,String>();
				cancelMap.put("orderId", "150130001088");
				Map<String,String> resultMap = client.recoveryFailOrder(cancelMap);
				System.out.println("取消报障结果："+resultMap);*/
				
				/**
				 * 1.2.41.	查询商家营收返利提现统计
				 */
				/*Map<String,String> sellerMap = new HashMap<String,String>();
				sellerMap.put("sellerId", "4613");
				sellerMap.put("amountType", "1");
				sellerMap.put("cPage", "0");
				sellerMap.put("pageSize", "10");
				SellerStatistics ss = client.getSellerStatisticsList(sellerMap);
				System.out.println("获取结果：count="+ss.getCount()+",pageCount="+ss.getPageCount()
						+"\r\nstate="+ss.getState()+",msg="+ss.getMsg()+"\r\nList="+ss.getStatisticsList());*/
				
				
				/**
				 * 1.2.42.	查询商家营收返利提现明细接口
				 */
			/*	Map<String,String> sellerMap = new HashMap<String,String>();
				sellerMap.put("sellerId", "1346");
				sellerMap.put("amountType", "1");
				sellerMap.put("date", "2015-02-02");
				sellerMap.put("cPage", "1");
				sellerMap.put("pageSize", "10");
				SellerStatistics ss = client.getSellerDetailList(sellerMap);
				System.out.println("获取结果：count="+ss.getCount()+",pageCount="+ss.getPageCount()
						+"\r\nstate="+ss.getState()+",msg="+ss.getMsg()+"\r\nList="+ss.getStatisticsList());*/
				
		/*		
				
				Map<String,String> sellerMap = new HashMap<String,String>();
				sellerMap.put("sellerId", "4613");
				sellerMap.put("amountType", "1");
				Map<String,String> resultMap = client.getSellerMonthlyStatistics(sellerMap);
				System.out.println("获取结果："+resultMap);*/
				
				/**
				 * 测试分账返回
				 */
			/*	Map<String,String> map = new HashMap<>();
				map.put("orderId","20160618102054");
				map.put("orderType","1");
				Map<String, String> resultMap = client.deductionsOrderLedger(map);
				System.out.println(resultMap);*/
				
				/**
				 * 查询用户银行卡信息
				 */
			/*	Map<String,String> map = new HashMap<>();
				map.put("uId", "31619");
				map.put("userType","2");
				Map<String, String> singleMentionAccount = client.getSingleMentionAccount(map);
				System.out.println("银行卡:"+singleMentionAccount.get("account"));
				long edate = System.currentTimeMillis();
				long result = edate - sdate;
				allResult += result;
				log.error("程序运行时间： " + result + "ms   i=" + i);*/
			}

			log.error("总运行时间：" + new BigDecimal(allResult / 1000.00) + "s");
			log.error("平均运行时间：" + (allResult / times) + "ms ");
			/*
			 * } catch (FailureException e) {
			 * 
			 * String info = e.info; int state = e.state;
			 * System.out.println("响应状态：" + state + "-----响应消息：" + info);
			 */

		} catch (TException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			transport.close();
		}
	}
}
