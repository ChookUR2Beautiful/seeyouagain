package com.xmniao.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.xmniao.service.pay.FreshRefundServiceImpl;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.ResponsePageList;
import com.xmniao.thrift.manor.ManorPropsThriftService;
import com.xmniao.thrift.manor.ManorPropsThriftService.Client;
import com.xmniao.thrift.manor.Result;


public class ManorThriftClient {
	
private static final Logger log = Logger.getLogger(FreshRefundServiceImpl.class);
	 // 服务端的IP地址
  private static final String IP_NUMBER = "192.168.1.89";
//  private static final String IP_NUMBER = "192.168.50.110";
  // 服务端的端口号
  private static final int PORT = 7913;

  private static TTransport transport = null;

  public static void main(String[] args) {
	try {
	    transport = new TSocket(IP_NUMBER, PORT);
	    TFramedTransport frame = new TFramedTransport(transport);
	    // 设置传输协议为 TBinaryProtocol
	    TProtocol protocol = new TBinaryProtocol(frame);
	    TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
		    protocol, "ManorPropsThriftService");
	    ManorPropsThriftService.Client client = new Client(ManagerProtocol);
	    // 打开端口,开始调用
	    transport.open();
	    SimpleDateFormat sdf  = new SimpleDateFormat("yyMMddHHmmssSSS");
	    String transNo = sdf.format(new Date());
	    List<Integer> list = getList();
	    for(Integer uid :list){
	    	Result result = client.addUserEnergy(transNo+uid, uid, 360, 4);
	    	log.info("result:"+result);
	    }
	} catch (TTransportException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    transport.close();
	}
  }
  
  public static List<Integer> getList(){
	  List<Integer> list = new ArrayList<Integer>();
	  
	  list.add(474699);
	

	  return list;
  }
}
