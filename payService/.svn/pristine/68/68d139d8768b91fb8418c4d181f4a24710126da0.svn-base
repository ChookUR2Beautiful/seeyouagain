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

import com.xmniao.thrift.ledger.WithdrawMoneyService;
import com.xmniao.thrift.ledger.WithdrawMoneyService.Client;

public class WithdrawMoneyServiceTest {

    // 服务端的IP地址
     //private static final String IP_NUMBER = "192.168.20.242";
     //private static final String IP_NUMBER = "192.168.30.184";
     private static final String IP_NUMBER = "localhost";
    // 服务端的端口号
    private static final int PORT = 7911;

    private static TTransport transport = null;

    // public static void Test(){
    public static void main(String[] args) {
	try {
	    // 设置调用的服务地址为本地，端口为 7911
	    transport = new TSocket(IP_NUMBER, PORT);
	    TFramedTransport frame = new TFramedTransport(transport);
	    // 设置传输协议为 TBinaryProtocol
	    TProtocol protocol = new TBinaryProtocol(frame);
	    TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
		    protocol, "WithdrawMoneyService");
	    WithdrawMoneyService.Client client = new Client(ManagerProtocol);

	    // 打开端口,开始调用
	    transport.open();

	    long sdate = System.currentTimeMillis();
	    System.out.println("aaa");
	     Map<String, String> a = client.headWithrawMoney("888150860", 3, 2, "2");
	    System.out.println(a);

	 /*   List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	    Map<String,String> map = new HashMap<String,String>();
	    Map<String,String> map1 = new HashMap<String,String>();
	    Map<String,String> map2 = new HashMap<String,String>();
	    map1.put("balanceType", "5");//提现余额类型 ：5 押金
	    map1.put("amount", "5");
	    
//	    map2.put("balanceType", "1");
//	    map2.put("amount", "1");
	    list.add(map1);
//	    list.add(map2);
	    map.put("uId", "1155");
	    //map.put("account", "13777221819");
	    map.put("userType", "1");
	    map.put("purpose", "**");
	    map.put("tdesc", "测试测试");
	    map.put("mentionAccountId", "");
	    map.put("cash", "1");
	    map.put("recchannel", "1");
	    map.put("name", "天下第一粥");
	    Map<String,String> result = client.updateWithdrawalsRecord(list, map);
	    System.out.println(result);*/

	    
/*	    List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	    Map<String,String> map = new HashMap<String,String>();
	    Map<String,String> map1 = new HashMap<String,String>();
	    map1.put("balanceType", "3");
	    map1.put("amount", "3");
//	    map1.put("balanceType", "3");
//	    map1.put("amount", "200000");
	    
	    list.add(map1);
	   // map.put("uId", "3655207");
	    map.put("account", "15723422415");
	    map.put("userType", "3");
	    map.put("purpose", "**");
	    map.put("tdesc", "走了");
	    map.put("mentionAccountId", "");
	    map.put("cash", "1");
	    map.put("recchannel", "1");
	    map.put("name", "流线");
	    Map<String,String> result = client.updateJointWithdrawalsRecord(list, map);
	    System.out.println(result);*/
	    
	    
	    long edate = System.currentTimeMillis();
    
	    long time = edate - sdate;

	    
	    
	    System.out.println("程序运行时间： " + time + "ms ");

	} catch (TException e) {
	    // TODO 自动生成的 catch 块
	    e.printStackTrace();
	} finally {
	    transport.close();
	}
    }
}