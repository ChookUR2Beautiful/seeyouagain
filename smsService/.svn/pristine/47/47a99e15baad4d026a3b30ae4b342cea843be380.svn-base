package com.sms.common;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sms.entity.smsSendObj;
import com.sms.service.smsSendInterfaceImp;
import com.sms.service.wxSendService;

/**
 * 线程工厂类
 * @author douk
 *
 */
public class threadFactory {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(threadFactory.class);
	
	/**
	 * 线程池
	 */
	private ThreadPoolTaskExecutor threadPool;
	
	/**
	 * 队列
	 */
	private LinkedBlockingQueue<smsSendObj> queue;
	
	/**
	 * 线程数
	 */
	private int threadSize = 5;
	
	/**
	 * 短信发送
	 */
	private smsSendInterfaceImp sms;
	
	/**
	 * 微信发送接口
	 */
	private wxSendService wxService;
	
	/**
	 * 构造方法
	 * @param threadPool
	 * @param queue
	 * @param threadSize
	 */
	public threadFactory(ThreadPoolTaskExecutor threadPool,LinkedBlockingQueue<smsSendObj> queue,int threadSize,smsSendInterfaceImp sms,wxSendService wxService) {
		this.threadPool = threadPool;
		this.queue = queue;
		this.threadSize = threadSize;
		this.sms = sms;
		this.wxService = wxService;
	}
	
	/**
	 * 初始化运行线程
	 */
	public void iniRunThread(){
		for (int i = 0; i < threadSize; i++) {
			log.info("初始化线程... "+i);
			threadPool.execute(new runThread(sms,wxService, queue));
		}
	}
}
