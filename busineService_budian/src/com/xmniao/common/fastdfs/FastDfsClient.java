package com.xmniao.common.fastdfs;


import java.io.BufferedOutputStream;  
import java.io.IOException;  
import java.net.InetSocketAddress;
import java.net.URL;  
import java.net.URLDecoder;  
  
  





import org.csource.common.MyException;  
import org.csource.common.NameValuePair;  
import org.csource.fastdfs.ClientGlobal;  
import org.csource.fastdfs.StorageClient1;  
import org.csource.fastdfs.StorageServer;  
import org.csource.fastdfs.TrackerClient;  
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;  
  
/**
 * 
 * 项目名称：busineService_manor
 * 
 * 类名称：FastDfsClient
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年6月10日 下午4:50:44 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */  
public class FastDfsClient {  
	
	private static FastDfsClient fastDfsClient;
        
	private  TrackerClient trackerClient = null;  
    private  TrackerServer trackerServer = null;  
    private  StorageServer storageServer = null;  
    private  StorageClient1 storageClient = null;  
	      
 
	private FastDfsClient() throws IOException {
		// 连接超时的时限，单位为毫秒
		ClientGlobal.setG_connect_timeout(FastfdsConstant.FILE_UPLOAD_FASTDFS_CONNECT_TIMEOUT);
		// 网络超时的时限，单位为毫秒
		ClientGlobal.setG_network_timeout(FastfdsConstant.FILE_UPLOAD_FASTDFS_NETWORK_TIMEOUT);
		ClientGlobal.setG_anti_steal_token(FastfdsConstant.FILE_UPLOAD_FASTDFS_ANTI_STEAL_TOKEN);
		// 字符集
		ClientGlobal.setG_charset(FastfdsConstant.FILE_UPLOAD_FASTDFS_CHARSET);
		ClientGlobal.setG_secret_key(FastfdsConstant.FILE_UPLOAD_FASTDFS_SECRET_KEY);
		// HTTP访问服务的端口号
		ClientGlobal.setG_tracker_http_port(FastfdsConstant.FILE_UPLOAD_FASTDFS_TRACKER_HTTP_PORT);
		// Tracker服务器列表
		InetSocketAddress[] tracker_servers = new InetSocketAddress[1];
		tracker_servers[0] = new InetSocketAddress(FastfdsConstant.FILE_UPLOAD_FASTDFS_TRACKER_SERVER, FastfdsConstant.FILE_UPLOAD_FASTDFS_TRACKER_SERVER_PORT);
		ClientGlobal.setG_tracker_group(new TrackerGroup(tracker_servers));
	
        trackerClient = new TrackerClient();  
        trackerServer = trackerClient.getConnection();  
        storageServer = null;  
        storageClient = new StorageClient1(trackerServer, storageServer);
	}

	public static FastDfsClient getInstance() {
		if (null == fastDfsClient) {
			synchronized (FastDfsClient.class) {
				if (null == fastDfsClient) {
					try {
						fastDfsClient = new FastDfsClient();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return fastDfsClient;
	}
    /** 
     * 上传文件方法 
     * <p>Title: uploadFile</p> 
     * <p>Description: </p> 
     * @param fileName 文件全路径 
     * @param extName 文件扩展名，不包含（.） 
     * @param metas 文件扩展信息 
     * @return 
     * @throws Exception 
     */  
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) {  
        String result=null;  
        try {  
            result = storageClient.upload_file1(fileName, extName, metas);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
      
    /** 
     * 上传文件,传fileName 
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg 
     * @return null为失败 
     */  
    public String uploadFile(String fileName) {  
        return uploadFile(fileName, null, null);  
    }  
    /** 
     *  
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg 
     * @param extName 文件的扩展名 如 txt jpg等 
     * @return null为失败 
     */  
    public  String uploadFile(String fileName, String extName) {  
        return uploadFile(fileName, extName, null);  
    }  
      
    /** 
     * 上传文件方法 
     * <p>Title: uploadFile</p> 
     * <p>Description: </p> 
     * @param fileContent 文件的内容，字节数组 
     * @param extName 文件扩展名 
     * @param metas 文件扩展信息 
     * @return 
     * @throws Exception 
     */  
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) {  
        String result=null;  
        try {  
            result = storageClient.upload_file1(fileContent, extName, metas);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    /** 
     * 上传文件 
     * @param fileContent 文件的字节数组 
     * @return null为失败 
     * @throws Exception 
     */  
    public String uploadFile(byte[] fileContent) throws Exception {  
        return uploadFile(fileContent, null, null);  
    }  
      
    /** 
     * 上传文件 
     * @param fileContent  文件的字节数组 
     * @param extName  文件的扩展名 如 txt  jpg png 等 
     * @return null为失败 
     */  
    public String uploadFile(byte[] fileContent, String extName) {  
        return uploadFile(fileContent, extName, null);  
    }  
      
    /** 
     * 文件下载到磁盘 
     * @param path 图片路径 
     * @param output 输出流 中包含要输出到磁盘的路径 
     * @return -1失败,0成功 
     */  
    public int downloadFile(String path,BufferedOutputStream output) {  
        //byte[] b = storageClient.download_file(group, path);  
        int result=-1;  
        try {  
            byte[] b = storageClient.download_file1(path);  
                try{  
                    if(b != null){  
                        output.write(b);  
                        result=0;  
                    }  
                }catch (Exception e){} //用户可能取消了下载  
                finally {  
                    if (output != null)  
                        try {  
                            output.close();  
                        } catch (IOException e) {  
                            e.printStackTrace();  
                        }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    /** 
     * 获取文件数组 
     * @param path 文件的路径 如group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return 
     */  
    public byte[] downloadBytes(String path) {  
        byte[] b=null;  
        try {  
            b = storageClient.download_file1(path);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
        return b;  
    }  
      
    /** 
     * 删除文件 
     * @param group 组名 如：group1 
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return -1失败,0成功 
     */  
    public Integer deleteFile(String group ,String storagePath){  
        int result=-1;  
        try {  
            result = storageClient.delete_file(group, storagePath);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
         return  result;  
    }  
    /** 
     *  
     * @param storagePath  文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return 0成功  非0失败
     * @throws IOException 
     * @throws Exception 
     */  
    public Integer deleteFile(String storagePath){  
        int result=-1;  
        try {  
            result = storageClient.delete_file1(storagePath);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (MyException e) {  
            e.printStackTrace();  
        }  
        return  result;  
    }  
    
	public static void main(String[] args) {
	int result = FastDfsClient.getInstance().deleteFile("img/M00/02/CD/wKgyMlkQPOAb47DAAAgL5vCgAE7606015");
	System.out.println("删除结果:"+(result==0?"成功":"失败")+" ("+result+")");
	}
}  