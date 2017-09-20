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
import com.xmniao.thrift.ledger.MentionAccount;
import com.xmniao.thrift.ledger.MentionAccountService;
import com.xmniao.thrift.ledger.MentionAccountService.Client;



public class MentionAccountThriftClient {
	// 服务端的IP地址
//	private static final String IP_NUMBER = "192.168.50.110";
	private static final String IP_NUMBER = "localhost";
	// 服务端的端口号
	private static final int PORT = 7911;

	private static TTransport transport = null;

	public static void main(String[] args) {
		try {
			Logger log = Logger.getLogger(MentionAccountThriftClient.class);

			// 设置调用的服务地址为本地，端口为 7911
			transport = new TSocket(IP_NUMBER, PORT);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
					protocol, "MentionAccountService");
			MentionAccountService.Client client = new Client(ManagerProtocol);

			// 打开端口,开始调用
			transport.open();

			long allResult = 0;

			int times = 1;

			for (int i = 0; i < times; i++) {
				long sdate = System.currentTimeMillis();



				// 3.测试添加银行卡
//				Map<String,String> paramMap = new HashMap<String,String>();
//				paramMap.put("uId", "3655207");
//				paramMap.put("type", "2");
//				paramMap.put("account", "6222444444442222");
//				paramMap.put("cardType", "2");
//				paramMap.put("userName", "李谋");
//				paramMap.put("bankName", "中国农业银行上步支行");
//				//paramMap.put("mobileId", "13444444444");
//				paramMap.put("isuse", "0");
//				paramMap.put("userType", "3");
//				paramMap.put("ispublic", "1");
//				//paramMap.put("idtype", "1");
//				//paramMap.put("identity", "452201188888888888");
//				paramMap.put("bank", "中国农业银行");
//				paramMap.put("abbrev", "ABC");
//				paramMap.put("province", "广东省");
//				paramMap.put("cityname", "深圳");
//				paramMap.put("license", "dd2");
//				paramMap.put("upidcard", "ccc3");
//				paramMap.put("dwidcard", "aaa4");
//
//				Map<String, String> resultStatus = client.addMentionAccount(paramMap);
//				System.out.println("resultStatus:"+resultStatus);
				
				// 4.测试获取银行卡
				 /*List<Map<String,String>> list = client.getMentionAccount("8888",1);
				 System.out.println(JSON.toJSONString(list));*/

				// 5.测试更新银行卡
				/*Map<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("id","172");
				paramMap.put("type", "2");
				paramMap.put("account", "6222444444441111");
				paramMap.put("cardType", "2");
				paramMap.put("userName", "李小龙");
				paramMap.put("bankName", "中国农业银行上步支行");
				paramMap.put("mobileId", "13444444488");
				paramMap.put("isuse", "0");
				paramMap.put("userType", "1");
				paramMap.put("ispublic", "1");
				//paramMap.put("idtype", "1");
				//paramMap.put("identity", "452201188888888888");
				paramMap.put("bank", "中国农业银行");
				paramMap.put("abbrev", "ABC");
				paramMap.put("province", "广东省");
				paramMap.put("cityname", "深圳");
				paramMap.put("license", "dd");
				paramMap.put("upidcard", "ccc");
				paramMap.put("dwidcard", "aaa");
				Map<String, String> result2 = client.updateMentionAccount(paramMap);
				System.out.println("更新结果"+JSON.toJSONString(result2)); */
				
				// 6.删除银行/支付账号
				// int del = client.delMentionAccount("0");
				// System.out.println(del);

				/*
				 * Map<String, String> testMap =
				 * client.updateBalance(paramMapList);
				 * System.out.println(testMap);
				 */

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
//				 Map<String,String> mentionAccountMap = new HashMap<String,String>();
//				 mentionAccountMap.put("uId", "0");
//				 mentionAccountMap.put("userType", "3");
//				 //mentionAccountMap.put("bankid", "6225887857477851");
//				 //mentionAccountMap.put("username", "方欣");
//				 //mentionAccountMap.put("mobileid", "15986671443");
//				 //mentionAccountMap.put("identity", "330327198412152860");
//				 mentionAccountMap.put("cPage", "1");
//				 mentionAccountMap.put("pageSize", "10");
//				 MentionAccount ma = client.getMentionAccountList(mentionAccountMap);
//				 System.out.println("获取的银行卡列表信息 ：count="+ma.getCount()+",pageCount="+ma.getPageCount()+"\r\naccountList="+JSON.toJSONString(ma.getAccountList()));
				/*---------------------------------获取银行卡列表接口-end -------------------------------------------------*/


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

				// 1.2.28. 查询用户银行卡10587,10580,18023,18007
				 List<Map<String, String>> param = new ArrayList<Map<String,
				 String>>();
				
				 Map<String, String> map = new HashMap<>();
				 map.put("uId", "127");
				 map.put("userType","1");
				 param.add(map);
				
//				 Map<String, String> map1 = new HashMap<>();
//				 map1.put("uId", "4634");
//				 map1.put("userType", "2");
//				
//				 param.add(map1);
//				
//				 Map<String, String> map2 = new HashMap<>();
//				 map2.put("uId", "4584");
//				 map2.put("userType", "2");
//				
//				 param.add(map2);
				
			/*	 System.out.println(client.getMentionAccounts(param));*/
				
//				System.out.println(client.getMentionAccount("0",4));

				// TTest t = new TTest();
				// t.start();

//				Map<String,String> map = client.getMentionAccountById("2000");
//				System.out.println(JSON.toJSONString(map));
				
//				Map<String,String> delMap = new HashMap<String,String>();
//				delMap.put("id", "36");
//				delMap.put("uId", "5861");
//				delMap.put("userType", "1");
//				delMap.put("type", "1");
//				delMap.put("account", "6222444444441117");
//				Map<String,String> map = client.unbundlingAccount(delMap);
//				System.out.println(map);
				 Map<String, String> singleMentionAccount = client.getSingleMentionAccount(map);
					System.out.println("银行卡:"+singleMentionAccount.get("account"));
				long edate = System.currentTimeMillis();
				long result = edate - sdate;
				allResult += result;
				log.error("程序运行时间： " + result + "ms   i=" + i);
			}

			log.error("总运行时间：" + new BigDecimal(allResult / 1000.00) + "s");
			log.error("平均运行时间：" + (allResult / times) + "ms ");

		} catch (TException e) {

			e.printStackTrace();
		} finally {
			transport.close();
		}
	}
}
