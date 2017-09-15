package com.xmniao.xmn.core.xmer.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.XmerWalletService;
import com.xmniao.xmn.core.thrift.XmerWalletService.Client;

/**
 * 测试寻蜜客钱包功能
 * @author chenjie 
 *
 */
public class XmerWalleThriftClent {
	
	private static final String IP_NUMBER="192.168.50.110";
	
	private static final int port =7911;
	
	private static TTransport transport=null;
	
	public static void main(String[] args) {
		try {
			System.out.println("===start===");
			transport = new TSocket(IP_NUMBER, port);
			TFramedTransport frame = new TFramedTransport(transport);
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol, "XmerWalletService");
			XmerWalletService.Client client = new Client(tMultiplexedProtocol);
			System.out.println("------2-----");
			transport.open();
			Map<String,String> walletMap =  new HashMap<>();
			ResponseData responseData = null;
			walletMap.put("uid","337561");
			walletMap.put("uname","嘻嘻哈哈");
			walletMap.put("state", "1");
//			responseData = client.addXmerWallet(walletMap);
//			responseData = client.lockXmerWallet(walletMap);
//			responseData = client.getXmerWallet(walletMap);
//			responseData = client.getXmerWalletState(walletMap);
			walletMap.put("money","0.01");
			responseData = client.turnOutXmerWallet(walletMap);
			
			System.out.println(responseData.toString());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			transport.close();
		}
	}
}
