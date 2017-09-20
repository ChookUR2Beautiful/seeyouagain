package com.xmn.designer.base;

import org.csource.fastdfs.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FastDFS上传客户端工厂类
 * create 2016/10/08
 *
 * @author yangQiang
 */

public class UploadClientFactory implements AutoCloseable {
    private ArrayBlockingQueue<StorageClient1> idleConnectionPool;
    private StorageClient1 client1;

    // ip地址
    private String ip;
    // 连接池大小
    private int connectionPoolSize;
    // 连接超时时间
    private int connectTimeout;
    // 网络连接超时时间
    private int networkTimeout;
    private boolean antiStealToken;
    // 编码
    private String charset;
    // key
    private String secretKey;


    /**
     * 初始化连接
     * @throws Exception
     */
    public void init() {
        // 初始化连接池
        idleConnectionPool = new ArrayBlockingQueue<>(this.connectionPoolSize);
        // 初始化ip地址
        InetSocketAddress[] inetSocketAddresses = Address(this.ip);
        ClientGlobal.setG_tracker_group(new TrackerGroup(inetSocketAddresses));

        // 连接超时时间, 单位为毫秒
        ClientGlobal.setG_connect_timeout(this.connectTimeout);
        // 网络连接超时间, 单位为毫秒
        ClientGlobal.setG_network_timeout(this.networkTimeout);
        ClientGlobal.setG_anti_steal_token(this.antiStealToken);
        // 编码
        ClientGlobal.setG_charset(this.charset);
        // Key
        ClientGlobal.setG_secret_key(this.secretKey);

        // 初始化连接
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            for (int i = 0; i < connectionPoolSize; i++) {
                StorageServer storageServer = null;
                StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
                idleConnectionPool.add(storageClient1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取客户端连接
     * @return
     */
    public StorageClient1 getStorageClients(){
        try {
            client1 = idleConnectionPool.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return client1;
    }


    /**
     * 关闭客户端连接
     * @throws IOException
     */
    @Override
    public void close(){
        idleConnectionPool.add(client1);
    }


    /**
     * 组装服务IP地址
     *
     * @param str
     * @return
     */
    private InetSocketAddress[] Address(String str) {
        String[] ips = str.split(",");
        InetSocketAddress[] trackerServers = new InetSocketAddress[ips.length];

        String ip;
        int prot;
        for (int i = 0; i < trackerServers.length; i++) {
            String[] temp = ips[i].split(":");
            ip = temp[0];
            prot = Integer.valueOf(temp[1]);
            trackerServers[i] = new InetSocketAddress(ip, prot);
        }
        return trackerServers;
    }



    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setNetworkTimeout(int networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

    public void setAntiStealToken(boolean antiStealToken) {
        this.antiStealToken = antiStealToken;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
