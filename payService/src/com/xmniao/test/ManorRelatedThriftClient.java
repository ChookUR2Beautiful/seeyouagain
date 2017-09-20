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
import org.apache.thrift.transport.TTransportException;

import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.ResponsePageList;
import com.xmniao.thrift.manor.ManorRelatedService;
import com.xmniao.thrift.manor.ManorRelatedService.Client;



public class ManorRelatedThriftClient {
	 // 服务端的IP地址
  private static final String IP_NUMBER = "localhost";
//  private static final String IP_NUMBER = "192.168.50.110";
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
		    protocol, "ManorRelatedService");
	    ManorRelatedService.Client client = new Client(ManagerProtocol);
	    // 打开端口,开始调用
	    transport.open();

	    
/* ----------------------- 阳光利率  start ---------------------------- */	    
//	    ResponsePageList list =  client.getSunshineProfit();
//	    System.out.println("阳光利率配置列表:"+list);
//	    
//	    int sunshine = 100;
//	    ResponseData  responseData = client.getProfitForSunshine(sunshine);
//	    System.out.println("阳光"+sunshine+"，可获得利率:"+responseData);
//	    
//	    List<Map<String,String>> paralist = new ArrayList<>();
//	    Map<String,String> profitMap = new HashMap<>();
//	    profitMap.put("baseNumber", "100");
//	    profitMap.put("profit", "0.02");
//	    paralist.add(profitMap);
//	    Map<String,String> profitMap2 = new HashMap<>();
//	    profitMap2.put("baseNumber", "200");
//	    profitMap2.put("profit", "0.03");
//	    paralist.add(profitMap2);
//	    Map<String,String> profitMap3 = new HashMap<>();
//	    profitMap3.put("baseNumber", "500");
//	    profitMap3.put("profit", "0.04");
//	    paralist.add(profitMap3);
//	    Map<String,String> profitMap4 = new HashMap<>();
//	    profitMap4.put("baseNumber", "1000");
//	    profitMap4.put("profit", "0.05");
//	    paralist.add(profitMap4);
//	    ResponseData responseData2 = client.updateSunshineProfit(paralist);
//	    System.out.println("添加结果:"+responseData2);
/* ----------------------- 阳光利率  end ---------------------------- */		    
	    
/* ----------------------- 流水记录  start ---------------------------- */		    
	    Map<String,String> converMap = new HashMap<>();
	    ResponsePageList converList = client.getConvertRecord(converMap);
	    System.out.println("兑换列表:"+converList);

	    Map<String,String> sunshineMap = new HashMap<>();
	    ResponsePageList sunshineList = client.getConvertRecord(sunshineMap);
	    System.out.println("阳光流水:"+sunshineList);
/* ----------------------- 流水记录  end ---------------------------- */		    
	    
	} catch (TTransportException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    transport.close();
	}
  }
}
