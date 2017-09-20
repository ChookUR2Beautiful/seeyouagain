package com.sms.service;

import com.sms.entity.smsSendObj;

/**
 * 短信发送接口
 * @author douk
 *
 */
public interface smsSendInterface {
	
	/**
	 * 随机选择短信发送通道
	 * @param msg
	 * @param ph
	 */
	public int smsRandomChannel(String msg,String[] ph);
	
	/**
	 * E讯通平台发送接口
	 * @param msg 短信内容
	 * @param ph 手机号码，最多支付300个号码
	 * @return
	 */
	public int smsPlatformSend(String msg,String[] ph);
	
	/**
	 * 创世漫道平台发送接口
	 * @param msg
	 * @param ph
	 * @return
	 */
	public int smsZucpSend(String msg,String[] ph);
	
	/**
	 * 梦网平台发送接口
	 * @param msg 短信内容
	 * @param ph 手机号码，最多支付300个号码
	 * @return
	 */
	public int MwPlatformSend(String msg,String[] ph);
	
	/**
	 * 短信网关发送
	 * @param msg 短信内容
	 * @param ph 手机号码，最多支付300个号码
	 * @return
	 */
	public int smsGatewaySend(String msg,String[] ph);
	
	/**
	 * 梦网语音
	 * @param msg
	 * @param ph
	 * @return
	 */
	public int MwMongateSend(String msg,String ph);
	
	/**
	 * 微信平台发送消息接口
	 * @param msg
	 * @param ph
	 * @return
	 */
	public int WxPlatformSend(wxSendService wxService,smsSendObj obj);
}
