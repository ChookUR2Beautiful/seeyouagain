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
import org.apache.thrift.transport.TTransportException;

import com.xmniao.thrift.ledger.PageList;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.SubList;
import com.xmniao.thrift.ledger.ValueCardService;
import com.xmniao.thrift.ledger.ValueCardService.Client;



public class ValueCardThriftClient {
	 // 服务端的IP地址
//  private static final String IP_NUMBER = "localhost";
  private static final String IP_NUMBER = "192.168.50.110";
  // 服务端的端口号
  private static final int PORT = 7911;

  private static TTransport transport = null;

  public static void main(String[] args) {
	try {
	    transport = new TSocket(IP_NUMBER, PORT);
	    TFramedTransport frame = new TFramedTransport(transport);
	    // 设置传输协议为 TBinaryProtocol
	    TProtocol protocol = new TBinaryProtocol(frame);
	    TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
		    protocol, "ValueCardService");
	    ValueCardService.Client client = new Client(ManagerProtocol);
	    // 打开端口,开始调用
	    transport.open();

	    Map<String, String> map = new HashMap<String, String>();
//		map.put("uid", "604898");
		map.put("sellerid","33548");
		map.put("rtype", "1,4,5");
//		map.put("quota","5000");
//		map.put("orderNo","123456789");
//		map.put("sellername","测试");
//		map.put("remarks","广州酒家_1");
//		map.put("sellertype","2");
//		map.put("consumeSellerid","654321");
//		map.put("consumeSellername","耗材");
//		map.put("orderNoType","1");
//		map.put("isOverdue","1");
//		map.put("page","1");
//		map.put("pageSize","20");
		map.put("sdate","2017-02-28");
		
		/**
		 * 查询商家记录
		 */
		PageList valueCardRecord = client.getValueCardRecord(map);
		System.out.println(valueCardRecord.toString());
		
		/**
		 * 商家充值会员列表
		 */
//		SubList userList = client.getUserList(map);
//		System.out.println(userList.toString());
		
		/**
		 * 更新
		 */
//		ResponseData responseData = client.updateValueCard(map);
//		System.out.println(responseData.getMsg());
		
		/**
		 * 注销
		 */
//		client.updateCardStatus(map);
		
		/**
		 * 获取储值卡
		 */
//		List<Map<String, String>> list = client.getValueCardMsg(map);
//		System.out.println(list.toString());
	    
//	    List<Map<String, String>> cardRecord = client.getCardRecord(map);
//	    for (Map<String, String> map2 : cardRecord) {
//	    	System.out.println(map2.toString());
//		}
		
//		Map<String, String> userDetail = client.getUserDetail(map);
//		System.out.println(userDetail);
		
//		List<Map<String,String>> sList = new ArrayList<>();
//		sList.add(map);
//		List<Map<String, String>> list = client.getValueCardList(map);
//		System.out.println(list.toString());
	} catch (TTransportException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    transport.close();
	}
  }
}
