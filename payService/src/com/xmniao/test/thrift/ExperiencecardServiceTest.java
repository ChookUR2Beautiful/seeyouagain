package com.xmniao.test.thrift;

import com.alibaba.fastjson.JSON;
import com.xmniao.thrift.ledger.ExperiencecardService;
import com.xmniao.thrift.ledger.ResponseSubList;
import com.xmniao.thrift.ledger.SellerWallet;
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

import java.util.*;

/**
 * Created by yang.qiang on 2017/5/9.
 */
public class ExperiencecardServiceTest {
    // 服务端的IP地址
    private static final String IP_NUMBER = "localhost";
//    private static final String IP_NUMBER = "192.168.50.110";
    // 服务端的端口号
    private static final int PORT = 7911;
    private static TTransport transport;
    ExperiencecardService.Client client;


    @Before
    public void before() throws Exception {
        transport = new TSocket(IP_NUMBER, PORT);
        TFramedTransport frame = new TFramedTransport(transport);
        // 设置传输协议为 TBinaryProtocol
        TProtocol protocol = new TBinaryProtocol(frame);
        TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol,
                "ExperiencecardService");
        client = new ExperiencecardService.Client(ManagerProtocol);
        transport.open();
    }

    @After
    public void after() throws Exception {
        transport.close();
    }

    @Test
    public void test_createExperiencecard() throws TException {
        HashMap<String, String> param = new HashMap<>();
        param.put("uid","606041");
        param.put("stock","20");
        param.put("dueDate","2017-07-23 10:10:00");
        param.put("source",System.currentTimeMillis()+"");
        param.put("remark","订单备注3");
        System.out.println(client.createExperiencecard(param));
    }

    @Test
    public void test_getExperiencecard() throws TException {
        HashMap<String, String> param = new HashMap<>();
        param.put("uid","606041");

        System.out.println(client.getExperiencecard(param));
    }

    @Test
    public void test_deductExperiencecard() throws TException {
        HashMap<String, String> param= new HashMap<>();
        param.put("uid","606041");
        param.put("operate","2");
        param.put("remark","消费美食体验卡");
        System.out.println(client.deductExperiencecard(param));
    }

    @Test
    public void test_queryExperiencerdList() throws TException {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("pageNum","1");
        paramMap.put("pageSize","5");
        paramMap.put("status","1");
        paramMap.put("startDueDate","2017-05-19 00:00:00");
        paramMap.put("endDueDate","2018-05-21 10:50:03");
        ResponseSubList responseSubList = client.queryExperiencerdList(paramMap);
        System.out.println(responseSubList);
    }

    @Test
    public void test_updateExperiencecardStatus() throws TException {
        HashMap<String, String> param = new HashMap<>();
        param.put("id","14");
        param.put("status","1");
        System.out.println(client.updateExperiencecardStatus(param));

        SellerWallet sellerWallet = new SellerWallet();

    }


    @Test
    public void test_getExperiencecardByUids() throws TException {
        ArrayList<Integer> uids = new ArrayList<>();
        Integer[] uidArr = {606041,606997,607004,606551,604809,607060,607041,607085,606493,339586,11111};
        Collections.addAll(uids,uidArr);
        List<Map<String, String>> excardList = client.getExperiencecardByUids(uids);
        System.out.println(JSON.toJSONString(excardList));
    }

}
