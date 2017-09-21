/**
 * 
 */
package com.xmniao.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.alibaba.fastjson.JSON;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.live.LiveOrderService;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiveOrderServiceClient
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-9 下午3:47:42 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public class LiveOrderServiceClient {

	 //服务端的IP地址
    private static final String IP_NUMBER = "localhost";
//    private static final String IP_NUMBER = "192.168.50.109";
    
    //服务端的端口号
    private static final int PORT = 7911;
    
    private static TTransport transport = null;
    
    public static void main(String[] args) throws InterruptedException
    {
        try
        {
            // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(IP_NUMBER, PORT);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            
            //开通美食体验卡服务模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
                    protocol, "LiveOrderService");
            LiveOrderService.Client liveOrderServiceClient = new LiveOrderService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            
            Map<String, String> paramMap=new HashMap<String,String>();
            
            /*paramMap.put("uid", "604863");//主播UID
            paramMap.put("liveRecordId", "1640");//通告ID
            paramMap.put("eggs", "8000");//鸟蛋收入
            paramMap.put("vkeUid", "606705");//V客UID
			ResponseData responseData = liveOrderServiceClient.anchorEggReceipts(paramMap);*/
            
            paramMap.put("playerId", "424");
            paramMap.put("playerUid", "606674");
            paramMap.put("referrerUid", "606673");
            paramMap.put("liveRecordId", "5265");
            paramMap.put("liveTime", "14");
/*            paramMap.put("playerId", "280");
            paramMap.put("playerUid", "607116");
            paramMap.put("referrerUid", "609075");
            paramMap.put("liveRecordId", "2066");
            paramMap.put("liveTime", "30");
*/            ResponseData responseData = liveOrderServiceClient.vstarRewardIssue(paramMap);
            
			System.out.println(JSON.toJSONString(responseData));
            
        }catch (TException e)
        {
            e.printStackTrace();
        }
        finally
        {
            transport.close();
        }
    }
}
