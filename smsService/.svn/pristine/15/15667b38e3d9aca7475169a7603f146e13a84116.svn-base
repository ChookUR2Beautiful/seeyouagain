package com.sms.common;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.sms.entity.smsSendObj;
import com.sms.service.smsSendInterfaceImp;
import com.sms.service.wxSendService;

/**
 * 运行线程
 * @author douk
 *
 */
class runThread implements Runnable{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(runThread.class);
	
	/**
	 * 短信发送服务
	 */
	private smsSendInterfaceImp smsService;
	
	/**
	 * 微信发送接口
	 */
	private wxSendService wxService;
	
	/**
	 * 队列
	 */
	private LinkedBlockingQueue<smsSendObj> queue;
	
	/**
	 * 构造方法
	 */
	public runThread(smsSendInterfaceImp smsService,wxSendService wxService,LinkedBlockingQueue<smsSendObj> queue) {
		this.queue = queue;
		this.smsService = smsService;
		this.wxService = wxService;
	}

	@Override
	public void run() {
		try {
			log.info("执行发送... "+Thread.currentThread().getName());
			while(true){
				smsSendObj obj = (smsSendObj)queue.take();
				//获取短信内容
				System.out.println("执行短信发送... "+Thread.currentThread().getName());
				
				//判断是否是微信帐号
				int result = 0;
				if(obj.getOpenid()== null || obj.getOpenid().equals("")){
					result = smsService.smsRandomChannel(obj.getText(), obj.getPh());
					if(result == 100){
						log.info("短信发送成功... "+result);
					}else{
						log.error("短信发送失败... "+result);
						//queue.add(obj);
					}
				}else{
					result = smsService.WxPlatformSend(wxService,obj);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}