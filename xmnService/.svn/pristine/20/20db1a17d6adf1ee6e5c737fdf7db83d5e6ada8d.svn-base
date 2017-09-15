package com.xmniao.xmn.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.UploadClientFactory;

/**
 * 
* 项目名称：saasService   
* 类名称：ImageUtil   
* 类描述： 判断二进制数据是否为图片格式类
* 创建人：liuzhihao   
* 创建时间：2016年3月29日 下午5:48:28   
* @version    
*
 */
@Service
public class CutVideoToImageUtil {
	
	/**
	 * FastDfs客户端工厂
	 */
	@Autowired
	private UploadClientFactory uploadClient;
	
	/**
	 * 初始日志类
	 */
	private final Logger log = Logger.getLogger(CutVideoToImageUtil.class);
	
	/**
	 * 注入会话令牌SESERVICE
	 */
	@Autowired
	private String fileUrl;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
    /**
     * 剪切视频保存封面图 
     * @param videoPath http可访问到的路径
     * @param numbers 张数 
     * */
    public List<String> cutVideoToImages(String videoPath){
    	
    	//返回当前获取视频截图集合  返回list 
    	List<String> listImage = new ArrayList<String>();
    	
    	try {
			//保存本地图片地址
			String rootPath = propertiesUtil.getValue("uploadVideoForImagePath", "conf_common.properties");
	    	
	    	String command =  ""; 
			String imagePath = rootPath+System.currentTimeMillis()+".jpg"; //本地图片地址
			List<String> cmd = new ArrayList<String>();
			cmd.add("/usr/local/bin/ffmpeg");//密令
			cmd.add("-i");//视频标示
			cmd.add(videoPath);//视频地址
			cmd.add("-y");
			cmd.add("-f");
			cmd.add("image2");  
			cmd.add("-t");//时间
			cmd.add("2");//帧数 第N秒开始
			cmd.add("-s 450x450");//图片大小
			cmd.add(imagePath);//保存地址
	    	 try { 
	    		 for (int i = 0; i < cmd.size(); i++) {
	    			 command = command+" "+cmd.get(i);
				}
	    		 log.info("当前的执行截图密令================command : "+command);
	    		 
	    		 //输出到本地指定路径
		    	 Runtime rt = Runtime.getRuntime(); 
		    	 Process proc = rt.exec(command); 
		    	 InputStream stderr = proc.getErrorStream(); 
		    	 InputStreamReader isr = new InputStreamReader(stderr); 
		    	 BufferedReader br = new BufferedReader(isr); 
		    	 String line = null; 
		    	 while ((line = br.readLine()) != null) 
		    		 System.out.println(line);
		    	 
	    	 } catch (Throwable t) { 
	    		 log.info("保存本地文件失败");
		    	 t.printStackTrace(); 
	    	 } 
	    	
	    	 //获取本地文件
	    	File videoImage = new File(imagePath);
			FileInputStream fi = new FileInputStream(videoImage);
			
			//将图片file 转换为 MultipartFile 上传图片服务器
			String fileName = DigestUtils.md5Hex(imagePath) + "";
			MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, fi);
			
	        NameValuePair[] metaListImage = new NameValuePair[3];
	        metaListImage[0] = new NameValuePair("fileName", fileName);
	        metaListImage[1] = new NameValuePair("fileExtName", "jpg");
	        metaListImage[2] = new NameValuePair("fileLength", String.valueOf(mockMultipartFile.getSize()));
	        
	        StorageClient1 client = null;
	        // 获取连接 
			client = uploadClient.getStorageClients();
	        String fileurlimage = client.upload_file1(mockMultipartFile.getBytes(), "jpg", metaListImage);
	        
	        if (!StringUtils.isEmpty(fileurlimage) && !"null".equals(fileurlimage)) {
	        	log.info("视频截取第一帧成功:"+imagePath);
	        	listImage.add(fileurlimage);
			}
			
			boolean delFlag = new File(imagePath).delete();
			if (delFlag) {
				log.info("视频截取第一帧本地图片删除成功:"+imagePath);
			}
			
    	} catch (Exception e) {
    		log.info(e.getMessage());
			e.printStackTrace();
		}
    	return listImage;
    }
}
