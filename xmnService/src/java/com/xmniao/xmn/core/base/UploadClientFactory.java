package com.xmniao.xmn.core.base;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

/**
 * 
* 项目名称：saasService   
* 类名称：UploadClientFactory   
* 类描述：FastDfs上传客户端工厂类   
* 创建人：liuzhihao   
* 创建时间：2016年4月11日 下午5:33:07   
* @version    
*
 */
public class UploadClientFactory {
	private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;
	
	private StorageClient1 client1;
	
	private int size = 1;

	public UploadClientFactory() {
		
	}
	
	public UploadClientFactory(HashMap<String,String> hashMap){
		try{
			if(hashMap.size() >  0){
				if(hashMap.get("ip") != null && hashMap.get("ip") != ""){
					size = (hashMap.get("connectionPoolSize") == null)?size:Integer.valueOf(hashMap.get("connectionPoolSize"));
					//初始化连接池
					idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(size);
					//初始化ip地址
					InetSocketAddress[] inetSocketAddress = Address(hashMap.get("ip"));
					ClientGlobal.setG_tracker_group(new TrackerGroup(inetSocketAddress));
					//连接超时的时限，单位为毫秒
					ClientGlobal.setG_connect_timeout(Integer.valueOf(hashMap.get("g_connect_timeout")));
					//网络连接超时的时限，单位为毫秒
					ClientGlobal.setG_network_timeout(Integer.valueOf(hashMap.get("g_network_timeout")));
					ClientGlobal.setG_anti_steal_token(Boolean.parseBoolean(hashMap.get("g_anti_steal_token")));
					//字符集
					ClientGlobal.setG_charset(hashMap.get("g_charset"));
					//key
					ClientGlobal.setG_secret_key(hashMap.get("g_secret_key"));
					//建立连接
					initPool(size);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	* @Title: initPool
	* @Description: (初始化连接)
	* @return void    返回类型
	* @throws
	 */
	public void initPool(int size){
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			for(int i=0; i<size; i++){
				StorageServer storageServer = null;
				StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
				idleConnectionPool.add(storageClient1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(trackerServer != null){
				try{
					trackerServer.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	* @Title: getStorageClients
	* @Description: (获取客户端连接)
	* @return StorageClient1    返回类型
	* @throws
	 */
	public StorageClient1 getStorageClients(){
		try {
			client1 = idleConnectionPool.poll(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return client1;
	}
	
	/**
	 * 
	* @Title: closeConnect
	* @Description: (关闭客户端连接)
	* @return void    返回类型
	* @throws
	 */
	public void closeConnect(){
		idleConnectionPool.add(client1);
	}
	
	/**
	 * 组装服务IP地址
	 * @param str
	 * @return
	 */
	private InetSocketAddress[] Address(String str){
		String[] ips = str.split(",");
		InetSocketAddress[] trackerServers = new InetSocketAddress[ips.length];
		
		String ip = "";
		int prot = 0;
		for (int i = 0; i < trackerServers.length; i++) {
			String[] temp = ips[i].split(":");
			ip = temp[0];
			prot = Integer.valueOf(temp[1]);
			trackerServers[i] = new InetSocketAddress(ip, prot);
		}
		return trackerServers;
	}
	
}
