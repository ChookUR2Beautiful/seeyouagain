package com.xmniao.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.thrift.ledger.LiveWalletService;
import com.xmniao.thrift.ledger.LiveWalletService.Client;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.ResponseList;
import com.xmniao.thrift.ledger.ResponseSubList;
import com.xmniao.thrift.ledger.TopList;
import com.xmniao.thrift.ledger.WalletRecord;

/**
 * 项目名称：payService
 * 
 * 类名称：LiveWalletSThriftClient
 * 
 * 类描述：测试LiveWalletService服务
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年8月15日下午5:59:47
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class LiveWalletSThriftClient {

	private static final String IP_NUMBER = "192.168.50.110";
//	private static final String IP_NUMBER = "localhost";

	private static final int port = 7911;

	private static TTransport transport = null;
	
	public static void main(String[] args) {
		try {
			System.out.println("===start==");
			transport = new TSocket(IP_NUMBER,port);
			TFramedTransport frame = new TFramedTransport(transport);
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol,"LiveWalletService");
			LiveWalletService.Client client = new Client(tMultiplexedProtocol);
			transport.open();
			List<Map<String,String>> list = new ArrayList<>();
			Map<String,String> map = new HashMap<>();
			Map<String,String> map1 = new HashMap<>();
			Map<String,String> map2 = new HashMap<>();
			map.put("uid","604821");
//			map.put("balance","400000000");
			map.put("rtype","2");
//			rtype=3, uid=604969, anchorId=406, commision=200
//			map2.put("uid","337542");
//			map2.put("balance","1000");
//			map2.put("rtype","5");
			//{uid=604896, rtype=2, remarks=0.10, returnRatio=0.10, zbalance=125.95, liveRecordId=234, consumeAmount=1259.58, commision=1259.58, anchorId=32523, description=黄瓜}
//			map.put("id","6");
//			map.put("uid","606653");//{uid=604866, pageSize=10, cPage=1, option=2}
//			map.put("status","10");
//			map.put("id","4");
//			map.put("id","13");
//			map.put("id",null);
//			map.put("sellerCoin","2000");//{"uid":"604873","description":"2017-02-28","ledgerType":"2","rtype":"19","remarks":"19883","zbalance":"1286.91","recordid":"19883","option":"0"}
//			map.put("option","0");
			map.put("zbalance","665.60");
//			map.put("money","1000");
//			map.put("percent","1");
//			map.put("turnOutAll","1");
//			map.put("rtype","5");
//			map.put("uid","604948,337542,604874,604914");
//			map.put("pageNo","1");
//			map.put("pageNo2","1");
//			map.put("pageSize2","20");
			map.put("anchorId","382");
			map.put("liveRecordId","3446");
			map.put("commision","6666");
			map.put("description","满汉全席");
			map.put("giftId","8");
			map.put("returnRatio","0.1");
			map.put("remarks","0.1");
			map.put("sellerCoin","1.00");
			map.put("consumeAmount","6666");
//			
//			list.add(map2);
//			list.add(map1);
//			list.add(map);
//			ResponseData responseData = client.getLiveWallet(map);
		/*	WalletRecord birdCoinList = client.birdCoinList(map);
			for (Map<String, String> map3 : birdCoinList.getWalletList()) {
				System.out.println(map3.toString());
			}*/
//			WalletRecord birdEggDetail = client.birdEggDetail(map);
//			List<Map<String, String>> walletList = birdCoinList.getWalletList();
//			ResponseData responseData = client.addLiveWallet("666666");
//			ResponseData responseData = client.getBirdeggNums(map);
//			System.out.println(responseData.getResultMap());
				ResponseData responseData = client.updateWalletAmount(map);
//				System.out.println(responseData.getState());
//			ResponseData responseData = client.updateLiveWalletsForList(list);
//			ResponseData responseData = client.turnoutLiveWallet(map);
//			WalletRecord consumerDetail = client.getConsumerDetail(map);
//			ResponseData responseData = client.getBirdBeans(map);
//			ResponseList birdBeansList = client.getBirdBeansList(map);
//			for (ResponseSubList list2 : birdBeansList.getDataList()) {
//				for (Map<String, String> map3 : list2.getSubList()) {
//					System.out.println(map3);
//				}
//			}
			/*ResponseSubList birdeggIncomeList = client.BirdeggIncomeList(map);
			System.out.println(birdeggIncomeList.getCountSum());
			System.out.println(birdeggIncomeList.getSubList());*/
//			Map<String,String> map3 = new HashMap<>();
//			map.put("uid","337542");
//			map.put("pageNo","1");
//			map.put("pageSize","10");
//			map.put("type","1");
			
//			Map<String, String> countBirdCoin = client.countBirdCoin(map);
//			System.out.println(countBirdCoin);
			
//			List<Map<String, String>> birdCoinDetail = client.birdCoinDetail(map);
//			for (Map<String, String> map4 : birdCoinDetail) {
//				System.out.println(map4);
//			}
			
			
		/*	List<TopList> birdeggTopList = client.BirdeggTopList(map);
			for (TopList topList : birdeggTopList) {
				System.out.println(topList.getResultMap());
				System.out.println(topList.getTopThree());
			}*/
			
//			System.out.println(responseData.getState());
//			System.out.println(responseData.getMsg());
//			System.out.println(responseData.getResultMap());
//			System.out.println(consumerDetail.getWalletList());
//			System.out.println(birdEggDetail);
			
//			map.put("pageSize","10");
//			map.put("option","2");
//			client.liveWalletOption(map);
//			List<Map<String, String>> userRecord = client.getUserRecord(map);
//			for (Map<String, String> map3 : userRecord) {
//				System.out.println(map3);
//			}
//			
//			List<String> paraList = new ArrayList<>();
//			paraList.add("601200");
//			paraList.add("604863");
//			Map<String, Map<String, String>> liveWalletMsg = client.getLiveWalletMsg(paraList);
//			System.out.println(liveWalletMsg);s
			System.out.println("end");
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			transport.close();
		}
	}
}
