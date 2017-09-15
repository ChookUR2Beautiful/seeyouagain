package com.xmniao.xmn.core.api.controller.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AppClientErrorLogApi   
* 类描述：   客户端错误日志
* 创建人：yezhiyong   
* 创建时间：2017年1月16日 下午1:50:19   
* @version    
*
 */
@RequestMapping("/app/client")
@Controller
public class AppClientErrorLogApi{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(AppClientErrorLogApi.class);
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@RequestMapping(value="/errorLog",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object errorLog(String systemVersion,String logMessage){
		if (logMessage != null && StringUtils.isNotEmpty(logMessage)) {
			//日志文件夹
			String filePath = "/usr/local/tomcat/logs/app/clientlog"; //只记录linux环境下   windows 客本地调试 无需记录
			
			//日志文件名字
			String fileName = DateUtil.format(new Date(), "yyyy-MM-dd");
					
			if (systemVersion != null && StringUtils.isNotEmpty(systemVersion)) {
				if (systemVersion.toLowerCase().contains("ios")) {
					fileName = "ios" + fileName;
				}else if (systemVersion.toLowerCase().contains("android")) {
					fileName = "android" + fileName;
				}
			}
			
			log.info("===================================================");
			log.info("filePath=" + filePath + ",fileName=" + fileName +",logMessage=" + logMessage);
			
			if(System.getProperty("os.name").toLowerCase().contains("windows")){
				return null;
			}else{
				//记录日志
				this.createLog(filePath, fileName, ".log", logMessage);
			}
		}
		
		return null;
	}
	
	/**
	 * 
	* @Title: createLog
	* @Description: 自定义创建日志文件，记录一条日志信息
	* @return void    返回类型
	* @throws
	 */
	public void createLog(String filePath,String fileName,String logSuffix,String logMessage) {
		//创建写入输出流
		BufferedWriter bw = null;
		try {
			//判断日志文件夹存在不
			File logFolder = new File(filePath);
			if (!logFolder.exists()) {
				//不存在日志文件夹,则直接创建
				logFolder.mkdir();
				log.info("logFolder创建");
			}
			
			String connectStr = "";
			if(System.getProperty("os.name").toLowerCase().contains("windows")){
				connectStr = "\\";//windows环境下
			}else{
				connectStr = "/"; //linux环境下
			}
			
			//判断日志文件存在不
			File logFile = new File(filePath + connectStr + fileName + logSuffix);
			if (!logFile.exists()) {
				//不存在日志文件,则直接创建
				logFile.createNewFile();
				log.info("logFile创建");
			}
			
			log.info("=====================================================");
			//记录一条日志信息
			bw = new BufferedWriter(new FileWriter(logFile,true));
			String logStr = (sdf.format(new Date()) + " " + logMessage + "\r\n\r\n\r\n\r\n\r\n\r\n");
			
			//写入
			bw.write(logStr);
			
		} catch (IOException e) {
			log.info("自定义记录客户端错误日志信息失败,错误信息如下:" + e.getMessage());
		}finally{
			if (bw != null) {
				try {
					//关闭流
					bw.close();
				} catch (Exception e2) {
					log.info("自定义记录客户端错误日志信息失败,关闭写入输出流失败,错误信息如下:" + e2.getMessage());
				}
			}
		}
	}
	
	/**
	 * 
	* @Title: createLog
	* @Description: 自定义创建日志文件，批量记录日志信息
	* @return void    返回类型
	* @throws
	 */
	public void createLog(String filePath,String fileName,String logSuffix,String[] logMessage) {
		//创建写入输出流
		BufferedWriter bw = null;
		try {
			//判断日志文件夹存在不
			File logFolder = new File(filePath);
			if (!logFolder.exists()) {
				//不存在日志文件夹,则直接创建
				logFolder.mkdir();
			}
			
			//判断日志文件存在不
			File logFile = new File(filePath + "\\" + fileName + logSuffix);
			if (!logFile.exists()) {
				//不存在日志文件,则直接创建
				logFile.createNewFile();
			}
			
			bw = new BufferedWriter(new FileWriter(logFile,true));
			String logStr = "";
			//批量写入日志
			if (logMessage != null && logMessage.length > 0) {
				for (int i = 0; i < logMessage.length; i++) {
					logStr = (sdf.format(new Date()) + " " + logMessage[i] + "\r\n");
				}
			}
			
			//写入
			bw.write(logStr);
			
		} catch (IOException e) {
			log.info("自定义记录客户端错误日志信息失败,错误信息如下:" + e.getMessage());
		}finally{
			if (bw != null) {
				try {
					//关闭流
					bw.close();
				} catch (Exception e2) {
					log.info("自定义记录客户端错误日志信息失败,关闭写入输出流失败,错误信息如下:" + e2.getMessage());
				}
			}
		}
	}
}
