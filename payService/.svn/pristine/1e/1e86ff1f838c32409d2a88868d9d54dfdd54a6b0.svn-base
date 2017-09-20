package com.xmniao.service.redis;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xmniao.service.WalletService;
import com.xmniao.thrift.busine.SellerOrderService;
import com.xmniao.thrift.busine.SellerOrderService.Client;

/**
 * 推送消息业务逻辑处理实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class XmnWalletMessage 
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(XmnWalletMessage.class);
    
    @Autowired
    private WalletService walletService;
    
	@Resource(name = "BUSINESS_IP_NUMBER")
	private String ipNumbertBusine;
	
	@Resource(name = "IP_PORT_BUSINE")
	private int ipPortBusine;
	
    @SuppressWarnings("unchecked")
	public void handleMessage(String reqJson,Map<String,String> map)
    {

        if (reqJson!=null && !reqJson.trim().equals(""))
        {
            log.info("pushMsg handleMessage start:" + reqJson+","+map);
            try{
            	Map<String,String> walletMap = (Map<String,String>)JSON.parse(reqJson);
            	Iterator<Map.Entry<String, String>> it = walletMap.entrySet().iterator();
            	String temp;
            	while(it.hasNext()){
            		temp = it.next().getValue();
            		if(temp==null){
            			it.remove();
            		}
            	}
            	Map<String,String> resultMap=null;
            	
            	try{
            		walletService.checkandaddwallet(walletMap.get("uId"),walletMap.get("userType"),"","");
            		resultMap= walletService.updateBalance2(walletMap);
            	}catch(Exception e){
            		log.error("修改钱包余额异常,",e);
            		try{
            			resultMap= walletService.updateBalance2(walletMap);
            		}catch(Exception e2){
            			log.error("再次修改钱包余额异常",e2);
            		}
            	}
            	if(walletMap.get("returnMode")==null){
            		walletMap.put("returnMode","1");
            	}
            	//returnMode 回调模式 0 不回调 null或1红包回调
            	if(!walletMap.get("returnMode").equals("0")){
            		String id = (walletMap.get("orderId").split("_"))[0];
            		String success = resultMap!=null && resultMap.get("state").equals("0")?"1":"2";
            		Map<String,String> busMap = new HashMap<String,String>();
            		busMap.put("id", id);
            		busMap.put("status", success);
            		busMap.put("type", walletMap.get("returnMode"));
            		notifyStatus(busMap);
            	}
            }catch(Exception e){
            	log.error("处理更新钱包余额Redis队列异常，",e);
            }
        }
        log.info("pushMsg handleMessage end");
    }
    
	/*
	 * 设置调用的服务地址及端口
	 */
	public Map<String, String> notifyStatus(Map<String, String> paramMap){
		log.info("调用业务系统接口SellerOrderService.modifyReceiptStatus("+paramMap+")");
		
		Map<String, String> result = null;
		TTransport transport = null;
		try {
		    transport = new TSocket(ipNumbertBusine, ipPortBusine);
		    TFramedTransport frame = new TFramedTransport(transport);
		    // 设置传输协议为 TBinaryProtocol
		    TProtocol protocol = new TBinaryProtocol(frame);
		    TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
			    protocol, "SellerOrderService");
		    SellerOrderService.Client client = new Client(ManagerProtocol);
		    // 打开端口,开始调用
		    transport.open();

		    try {
		    	result = client.modifyReceiptStatus(paramMap);
		    } catch (TException e) {
		    	log.error("设置调用的服务地址及端口异常，", e);
		    }
		} catch (TTransportException e) {
		    log.error("设置调用的服务地址及端口异常", e);
		} catch (Exception e) {
		    log.error("程序退款调用业务服务异常", e);
		}finally {
		    transport.close();
		}
		return result;
	}
}
