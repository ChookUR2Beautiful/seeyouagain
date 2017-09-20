package com.xmniao.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.thrift.ledger.SynthesizeService;
import com.xmniao.thrift.ledger.SynthesizeService.Client;

public class UpdateWithdrawalsRecord {

	// 服务端的IP地址
	private static final String IP_NUMBER = "192.168.20.243";
	// 服务端的端口号
	private static final int PORT = 7911;

	private static TTransport transport = null;

	public static void main(String[] args) {
		try {
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

			long sdate = System.currentTimeMillis();

			// 商家提现
			List<Map<String, String>> amountMapList = new ArrayList<Map<String, String>>();

			Map<String, String> amountMap = new HashMap<String, String>();
			amountMap.put("balanceType", "1");
			amountMap.put("amount", "1.5");
			amountMapList.add(amountMap);

			 Map<String, String> amountMap1 = new HashMap<String, String>();
			 amountMap1.put("balanceType", "3");
			 amountMap1.put("amount", "20");
			 amountMapList.add(amountMap1);

			Map<String, String> orderMap = new HashMap<String, String>();

			orderMap.put("uId", "8080");
			orderMap.put("userType", "2");
			orderMap.put("mentionAccountId", "9");
			orderMap.put("cash", "2");
			orderMap.put("recchannel", "1");
			orderMap.put("name", "杨京");
			orderMap.put("tdesc", "从支付宝提现");
			orderMap.put("purpose", "提现目的名称");

//			int map = client.updateWithdrawalsRecord(amountMapList, orderMap);

			// 合作商提现
			// List<Map<String,String>> amountMapList = new
			// ArrayList<Map<String,String>>();
			//
			// Map<String, String> amountMap = new HashMap<String, String>();
			// amountMap.put("balanceType", "1");
			// amountMap.put("amount", "12.12");
			// amountMapList.add(amountMap);

			/*
			 * Map<String, String> orderMap = new HashMap<String,String>();
			 * 
			 * orderMap.put("uId", "8890"); orderMap.put("userType", "3");
			 * orderMap.put("mentionAccountId", "3"); orderMap.put("cash", "1");
			 * orderMap.put("recchannel", "1"); orderMap.put("name", "杨京");
			 * orderMap.put("tdesc", "从支付宝提现"); orderMap.put("purpose",
			 * "提现目的名称"); orderMap.put("invoice", "123456789");
			 * orderMap.put("express", "taylor速运"); orderMap.put("expressid",
			 * String.valueOf(System.currentTimeMillis()));
			 * 
			 * int map = client.updateJointWithdrawalsRecord(amountMapList,
			 * orderMap);
			 */

//			 System.out.println(map);
			long edate = System.currentTimeMillis();
			long result = edate - sdate;

			System.out.println("程序运行时间： " + result + "ms ");
		} catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			transport.close();
		}
	}
}
