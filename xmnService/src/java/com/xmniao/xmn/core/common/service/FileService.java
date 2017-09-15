package com.xmniao.xmn.core.common.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.UploadClientFactory;

@Service
public class FileService {

	private final Logger log = Logger.getLogger(FileService.class);
	/**
	 * FastDfs客户端工厂
	 */
	@Autowired
	private UploadClientFactory uploadClient;
	
	
	/**删除指定单个文件
	 * @param file_id 文件路径
	 * @return
	 */
	public int deleteFile (String file_id){
		StorageClient1 client = uploadClient.getStorageClients();	
		//默认删除失败
		int result = -1;
		try {
			//查询路径是否存在文件
			FileInfo fileInfo = client.query_file_info1(file_id);
			if (fileInfo == null) {
				log.info("文件"+file_id+"路径不存在");
				return result;
			}	
			result = client.delete_file1(file_id);
		} catch (Exception e) {
			log.info("删除文件发生内部错误"+e);
			e.printStackTrace();
			return result;
		} finally {
			uploadClient.closeConnect();
		}		
		return  result;
	}
	
	
	/**批量删除
	 * @param file_ids
	 * @return
	 */
	public int deleteFiles(List<String> file_ids) {
		int num=0;
		if (file_ids != null){	
			for(String file_id:file_ids) {
				if (deleteFile(file_id) == 0 ) {
					num++;
				}	
			}
		}
		log.info("文件成功删除"+num+"个,失败"+(file_ids.size()-num));
		return num;	
	}
	 
	public Object queryFileURL(String file_id) {
		StorageClient1 client = uploadClient.getStorageClients();	
		//默认删除失败
		try {
			//查询路径是否存在文件
			FileInfo fileInfo = client.query_file_info1(file_id);
			return fileInfo;
		} catch (Exception e) {
			log.info("删除文件发生内部错误"+e);
			e.printStackTrace();
		} finally {
			uploadClient.closeConnect();
		}
		return null;
	}
}
