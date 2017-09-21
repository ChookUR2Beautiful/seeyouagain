package com.xmniao.common;

import com.xmniao.thrift.pay.ManorPropsThriftService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Thrift Client 工厂
 * Created by yang.qiang on 2017/6/17.
 */
public class ThriftClientFactory{


    /* 获取支付服务 黄金庄园Thrift 客户端 */
    public static ThriftClient<ManorPropsThriftService.Client> getManorPropsThriftServiceClient(String serverIp, int serverPort) throws TTransportException {
        TTransport transport = new TSocket(serverIp, serverPort);
        TFramedTransport frame = new TFramedTransport(transport);
        // 设置传输协议为 TBinaryProtocol
        TProtocol protocol = new TBinaryProtocol(frame);
        //分账服务的综合服务接口模块
        TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "ManorPropsThriftService");
        ManorPropsThriftService.Client client = new ManorPropsThriftService.Client(orderProtocol);
        transport.open();
        //打开端口,开始调用

        return new ThriftClient<>(transport,client);

    }




    public static class ThriftClient<T> implements AutoCloseable{
        private TTransport transport;
        private T thriftClient;

        private ThriftClient(TTransport transport, T thriftClient) {
            this.transport = transport;
            this.thriftClient = thriftClient;
        }

        @Override
        public void close() throws Exception {
            transport.close();
        }


        public TTransport getTransport() {
            return transport;
        }

        public void setTransport(TTransport transport) {
            this.transport = transport;
        }

        public T getThriftClient() {
            return thriftClient;
        }

        public void setThriftClient(T thriftClient) {
            this.thriftClient = thriftClient;
        }
    }

}
