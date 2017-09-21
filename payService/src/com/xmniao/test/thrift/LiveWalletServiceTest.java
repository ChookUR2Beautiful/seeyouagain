package com.xmniao.test.thrift;

import com.xmniao.thrift.ledger.LiveWalletService;
import com.xmniao.thrift.ledger.ResponseData;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by yang.qiang on 2017/5/8.
 */
public class LiveWalletServiceTest {

    // 服务端的IP地址
//    private static final String IP_NUMBER = "localhost";
    private static final String IP_NUMBER = "192.168.50.110";
    // 服务端的端口号
    private static final int PORT = 7911;
    private static TTransport transport;
    LiveWalletService.Client client;


    @Before
    public void before() throws Exception {
        transport = new TSocket(IP_NUMBER, PORT);
        TFramedTransport frame = new TFramedTransport(transport);
        // 设置传输协议为 TBinaryProtocol
        TProtocol protocol = new TBinaryProtocol(frame);
        TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol,
                "LiveWalletService");
        client = new LiveWalletService.Client(ManagerProtocol);
        transport.open();
    }

    @After
    public void after() throws Exception {
        transport.close();
    }


    @Test
    public void test_exchangeSaas() throws TException {
        HashMap<String, String> paraMap = new HashMap<>();
        paraMap.put("uid","606041");
        paraMap.put("deductZbalance","10.0");
        paraMap.put("remarks","201703101653074000168");
        ResponseData responseData = client.exchangeSaas(paraMap);
        System.out.println(responseData);
    }
}