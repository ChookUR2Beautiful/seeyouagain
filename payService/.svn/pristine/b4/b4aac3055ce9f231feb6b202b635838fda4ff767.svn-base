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


/**
 * 客户端测试类
 * 测试添加与获取银行卡接口
 * @author  ChenBo
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UpdateWalletBalanceThriftClient
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
            //"1001","0","0","23662","19.86","199.44","e93405e7858995d1ac5417887e985ba6","0.00"
            
            System.out.println("------------------");
            
            long sdate = System.currentTimeMillis();
            
            List<Map<String ,String>> list  = new ArrayList<Map<String,String>>();
            Map<String,String> tempmap = new HashMap<String,String>();
            tempmap.put("uId","1001");
            tempmap.put("userType","1");       
            tempmap.put("balanceType","2");
            tempmap.put("balance","50000.00");
            list.add(tempmap);
            Map<String,String> tempmap1 = new HashMap<String,String>();
            tempmap1.put("uId","1232");
            tempmap1.put("userType","2");       
            tempmap1.put("balanceType","3");
            tempmap1.put("balance","247.54");
            list.add(tempmap1);
            Map<String,String> tempmap2 = new HashMap<String,String>();
            tempmap2.put("uId","3");
            tempmap2.put("userType","2");       
            tempmap2.put("balanceType","3");
            tempmap2.put("balance","8888");
            list.add(tempmap2);
            Map<String,String> tempmap3 = new HashMap<String,String>();
            tempmap3.put("uId","1234");
            tempmap3.put("userType","3");       
            tempmap3.put("balanceType","1");
            tempmap3.put("balance","247.54");
            list.add(tempmap3);          
            int test = client.updateWalletBalance(list);
            System.out.println(test);
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