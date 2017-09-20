package com.xmniao.xmn.core.util.task;

import java.io.File;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DeleteQRTask
 * 
 * 类描述： 定时删除服务器生成的二维码
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年8月30日 下午8:09:56
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Component
@Lazy(false)
public class DeleteQRTask {

	// 初始化日志类
	private final Logger log = Logger.getLogger(DeleteQRTask.class);

	@Scheduled(cron = "0 0 2 * * ?")
	public void task() {
		// /D:/tomcat/webapps/XmnWeb/WEB-INF/classes/ 当前类路径
		String path = this.getClass().getClassLoader().getResource("").getPath();
		// /D:/tomcat/webapps/XmnWeb/resources/stickFold  二维码备份存放路径
		StringBuilder filePath = new StringBuilder().append(
				path.substring(0, path.lastIndexOf("WEB-INF"))).append(
				"resources/stickFold");
		log.info("开始删除备份的物料订单二维码");
		delAllFile(filePath.toString());
		log.info("删除完成");
	}

	/**
	 * 
	 * 方法描述：删除所有文件 创建人： chenJie 创建时间：2016年8月30日下午8:40:50
	 * 
	 * @param path
	 * @return
	 */
	public boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (!"base".equals(tempList[i])) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
				if (temp.isDirectory()) {
					delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
					delFolder(path + "/" + tempList[i]);// 再删除空文件夹
					flag = true;
				}
			} else {
				continue;
			}
		}
		return flag;
	}

	/**
	 * 
	 * 方法描述：删除文件夹 创建人： chenJie 创建时间：2016年8月30日下午9:20:22
	 * 
	 * @param folderPath
	 */
	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
