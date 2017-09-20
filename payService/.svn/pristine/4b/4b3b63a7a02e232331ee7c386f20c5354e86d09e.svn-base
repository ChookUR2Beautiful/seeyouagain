package com.xmniao.test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.thrift.ledger.FreshRefundService;
import com.xmniao.thrift.ledger.FreshRefundService.Client;
import com.xmniao.thrift.ledger.RefundRequest;


/**
 * 测试商户确认退款功能
 * @author ChenBo
 *
 */
public class FreshRefundThriftClient {
    //服务端的IP地址
    private static final String IP_NUMBER = "localhost";
    //服务端的端口号
    private static final int PORT = 7911;
    
    private static TTransport transport = null;
    
    public static void main(String[] args)
    {
        try
        {
        	System.out.println("-----1-----");
        	// 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(IP_NUMBER, PORT);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol, "FreshRefundService");
            FreshRefundService.Client client = new Client(ManagerProtocol);

            System.out.println("-----2----");
            //打开端口,开始调用
            transport.open();
            long sdate = System.currentTimeMillis();
            
            System.out.println("-----3----");     
            String bid = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //支付宝退款
            //RefundRequest refundRequest = new RefundRequest("141225000322",99,"zfb",bid);//new RefundRequest("1514152214541",0.01,"失败");//new RefundRequest("150128000205",0.01,"支付宝退款");//new RefundRequest("141225000037",145.00,"");
            //U付退款1412191803158925 150126000187 0.01
            //RefundRequest refundRequest = new RefundRequest("1232154214",1.00,"",bid);  
            //微信退款(因为该订单之前退过款，流水号为:1033537274，测试时，要将refund_id改成它)
            //RefundRequest refundRequest = new RefundRequest("141225000090",0.01,"微信退款测试",bid);/*("141225000189",1.00,"微信退款测试");*/
            //汇付退款
            //RefundRequest refundRequest = new RefundRequest("1111111111111",0.10,"汇付退款",bid);
            //钱包退款
            RefundRequest refundRequest = new RefundRequest("160307000005", 200.0,"微信商城退款",bid.substring(1, 10));
            //融宝退款
            //RefundRequest refundRequest = new RefundRequest("141225011304",0.02,"融宝退款2"，bid);
            //盛付通退款
            //RefundRequest refundRequest = new RefundRequest("131225000324",0.02,"sftPay",bid);
            //快钱退款
            //RefundRequest refundRequest = new RefundRequest("150123000285",2.00,"快钱",bid);
            //通联退款
            //RefundRequest refundRequest = new RefundRequest("150321000068",1.01,"通联",bid);
            //连连退款
            //RefundRequest refundRequest = new RefundRequest("150121000068",2.01,"连连退款",bid);
            
            //RefundRequest refundRequest = new RefundRequest("262142511223", 1000.00,"优惠券退款",bid);
            Map resultMap = client.FreshRefund(refundRequest);
            
            System.out.println("msg:"+resultMap.toString());
            
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
