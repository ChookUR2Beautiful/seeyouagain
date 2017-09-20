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

import com.xmniao.thrift.ledger.SynthesizeService;
import com.xmniao.thrift.ledger.SynthesizeService.Client;


/**
 * 客户端测试类
 * 测试添加与获取银行卡接口
 * @author  ChenBo
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RemoveXmerThriftClient
{
    //服务端的IP地址
    private static final String IP_NUMBER = "localhost";
    //服务端的端口号
    private static final int PORT = 7911;
    
    private static TTransport transport = null;
    
    public static void main(String[] args)
    {
    	System.out.println(" ---- 进入测试 ---");
        try
        {
        	// 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(IP_NUMBER, PORT);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol, "SynthesizeService");
            SynthesizeService.Client client = new Client(ManagerProtocol);

            //打开端口,开始调用
            transport.open();
            
            System.out.println("------------------");
            
            long sdate = System.currentTimeMillis();
            Map<String,String> paramMap = new HashMap<>();
//            paramMap.put("uid","3421321");
//            Map<String,String> resultMap=client.xmerTransferDeposit(paramMap);
            paramMap.put("uid","3421321");
            paramMap.put("amount","200");
            paramMap.put("remark","五行缺錢");
            Map<String, String> resultMap = client.xmerReturnDeposit(paramMap);
            System.out.println(resultMap);
            long edate = System.currentTimeMillis();
            long result = edate - sdate;         
            System.out.println("程序运行时间： "+result+"ms ");
        } catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
        	transport.close();
        }
    }
}