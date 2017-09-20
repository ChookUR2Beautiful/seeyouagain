package com.xmniao.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.ResponseList;
import com.xmniao.thrift.ledger.ResponsePageList;
import com.xmniao.thrift.ledger.ResponseSubList;
import com.xmniao.thrift.ledger.XmniaoWalletService;
import com.xmniao.thrift.ledger.XmniaoWalletService.Client;

public class XmniaoWalletServiceClient {

//	private static final String IP_NUMBER = "192.168.50.110";
	 private static final String IP_NUMBER = "172.16.130.196";//localhost

	private static final int port = 7911;

	private static TTransport transport = null;

	public static void main(String[] args) {
		try {
			System.out.println("===start==");
			transport = new TSocket(IP_NUMBER, port);
			TFramedTransport frame = new TFramedTransport(transport);
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(
					protocol, "XmniaoWalletService");
			XmniaoWalletService.Client client = new Client(tMultiplexedProtocol);
			transport.open();
			Map<String, String> map = new HashMap<>();
//			map.put("id","138");
			map.put("uId","33548");
			map.put("userType","2");
//			map.put("withdrawKind","2");
//			map.put("status","0");
			map.put("sdate","2017-03-14");
			map.put("rtype","42");
//			map.put("edate","2016-11-04");
//			map.put("pageSize","20");
//			map.put("genussellerid","666685");
//			map.put("sellerid","666692");
				/*map.put("type","1");*/
//			ResponseData responseData = client.getXmnWithdrawAmount(map);
//			ResponseData responseData = client.getBusinessInfo(map);
			/*ResponsePageList businessList = client.getBusinessList(map);
			System.out.println(businessList.getDataInfo().getResultMap().toString());
			for (Map<String,String> map1 : businessList.getPageList()) {
				System.out.println(map1);
			}*/
//			ResponseList xmnWithdrawList = client.getXmnWithdrawList(map);
//			ResponseList xmnWalletLedgerList = client.getXmnWalletLedgerList(map);
//			System.out.println(xmnWalletLedgerList.getDataInfo());
//			ResponseList sellerExpenseList = client.getSellerExpenseList(map);
			/*System.out.println(xmnWithdrawList.getDataInfo().getResultMap().get("totalAmount"));
			System.out.println(xmnWithdrawList.getDataInfo().getResultMap().get("count"));
			for (ResponseSubList dataList: xmnWithdrawList.getDataList()) {
				System.out.println(dataList);
			}*/
//			System.out.println(responseData.getResultMap());
//			ResponseData sellerLedgerInfo = client.getSellerLedgerInfo(map);
//			System.out.println(sellerLedgerInfo);
//			System.out.println(responseData.getResultMap().toString());
			
			
//			List<Map<String, String>> userRecords = client.getUserRecords(map);
			ResponseList responseList = client.getSellerRechargeLedgerRecord(map);
			System.out.println(responseList.toString());
			for (ResponseSubList ResponseSubList: responseList.getDataList()) {
				for (Map<String,String> map2 : ResponseSubList.getSubList()) {
					System.out.println(map2);
				}
			}
			System.out.println("end");
			transport.close();
		} catch (Exception e) {
			System.out.println("BOOM!!!");
		}
	}
}
