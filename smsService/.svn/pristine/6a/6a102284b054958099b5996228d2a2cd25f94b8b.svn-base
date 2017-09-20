package com.sms.controller;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sms.common.ConfigFileReader;
import com.sms.common.ErrorCode;
import com.sms.common.utilClass;
import com.sms.entity.requestSmsParam;
import com.sms.entity.smsSendObj;
import com.sms.service.smsSendInterfaceImp;

/**
 * 短信发送服务
 * @author douk
 *
 */
@Controller
public class smsSendController {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(smsSendController.class);
	
	/**
	 * 短信接收队列
	 */
	@Autowired	
	private LinkedBlockingQueue<smsSendObj> queue;
	
	/**
	 * 加载配置文件
	 */
	@Autowired
	private ConfigFileReader appids;
	
	/**
	 * 获取EhCache缓存
	 */
	@Autowired
	private Cache smsCache;
	
	/**
	 * 短信发送服务
	 */
	@Autowired
	private smsSendInterfaceImp smsService;
	
	/**
	 * 构造方法
	 * @param queue
	 */
	public smsSendController() {
	}
	
	/**
	 * 使用平台发送短信
	 * @param data
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/smsSend", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String msmSend(@RequestParam("p")String p){
		log.info("请求参数: "+p);
		try{
			//将JSON转换为对象
			requestSmsParam param = JSON.parseObject(p, requestSmsParam.class);
			//获取APPID
			String appid = String.valueOf(appids.p.get(param.getAppid()));
			if(appid.equals("") || appid.equals("null")){
				return "{\"state\":"+ErrorCode.KEYNOT+",\"info\":\"APPID应用编码验证失败\"}";
			}
			//验证手机号
			if(param.getPhones().length < 1 || (param.getPhones()[0].equals("null") || param.getPhones()[0].equals(""))){
				return "{\"state\":"+ErrorCode.ERRNULL+",\"info\":\"手机号码不能为空\"}";
			}
			//验证短信内容
			if(param.getText().length() < 1){
				return "{\"state\":"+ErrorCode.OBJNULL+",\"info\":\"短信内容不能为空\"}";
			}
			//构造发送对象
			smsSendObj sm = new smsSendObj();
			sm.setText(param.getText());
			sm.setPh(param.getPhones());
			queue.add(sm);
		}catch(Exception e){
			log.error("请求参数或格式错误",e);
			return "{\"state\":"+ErrorCode.ERRDATA+",\"info\":\"请求参数或格式错误\"}";
		}
		return "{\"state\":"+ErrorCode.SUCCESS+",\"info\":\"短信发送成功\"}";
	}
	
	/**
	 * 梦网语音发送短信
	 * @param data
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/smsMongateSend", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String msmMongateSend(@RequestParam("p")String p){
		log.info("请求参数: "+p);
		try{
			//将JSON转换为对象
			requestSmsParam param = JSON.parseObject(p, requestSmsParam.class);
			//获取APPID
			String appid = String.valueOf(appids.p.get(param.getAppid()));
			if(appid.equals("") || appid.equals("null")){
				return "{\"state\":"+ErrorCode.KEYNOT+",\"info\":\"APPID应用编码验证失败\"}";
			}
			//验证手机号
			if(param.getPhones().length < 1 || (param.getPhones()[0].equals("null") || param.getPhones()[0].equals(""))){
				return "{\"state\":"+ErrorCode.ERRNULL+",\"info\":\"手机号码不能为空\"}";
			}
			//验证短信内容
			if(param.getText().length() < 1){
				return "{\"state\":"+ErrorCode.OBJNULL+",\"info\":\"短信内容不能为空\"}";
			}
			//验证短信内容长度
			int size = param.getText().getBytes().length;
			if(size < 4 || size >8){
				return "{\"state\":"+ErrorCode.OBJNULL+",\"info\":\"短信内容长度限制为 4到8位字符 只能是数字或者字母不区分大小写\"}";
			}

			String tempp = Arrays.toString(param.getPhones());
			String phone = tempp.substring(1, tempp.length() -1);
			int result = smsService.MwMongateSend(param.getText(), phone);//发送语音短信
			if(result == ErrorCode.FAILURE){
				result = smsService.MwMongateSend(param.getText(), phone);//发送语音短信
				if(result == ErrorCode.FAILURE){
					return "{\"state\":"+ErrorCode.FAILURE+",\"info\":\"发送失败\"}";
				}
			}
		}catch(Exception e){
			log.error("请求参数或格式错误",e);
			return "{\"state\":"+ErrorCode.ERRDATA+",\"info\":\"请求参数或格式错误\"}";
		}
		return "{\"state\":"+ErrorCode.SUCCESS+",\"info\":\"短信发送成功\"}";
	}
	
	/**
	 * 发送短信验证码接口
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/smsValidate", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String smsfindValidate(@RequestParam("p")String p){
		log.info("请求参数: "+p);
		//获取随机验证码
		int num = utilClass.RandomNum();
		try{
			//将JSON转换为对象
			requestSmsParam param = JSON.parseObject(p, requestSmsParam.class);
			//获取APPID
			String appid = String.valueOf(appids.p.get(param.getAppid()));
			if(appid.equals("") || appid.equals("null")){
				return "{\"state\":"+ErrorCode.KEYNOT+",\"info\":\"APPID应用编码验证失败\"}";
			}
			//验证手机号
			if(param.getPhone().length() < 1){
				return "{\"state\":"+ErrorCode.ERRNULL+",\"info\":\"手机号码不能为空\"}";
			}
			//将随机数存入缓存
			Element cacheCode = new Element(param.getPhone(),num);
			smsCache.put(cacheCode);
			//构造发送对象
			smsSendObj sm = new smsSendObj();
			sm.setText("您的验证码为:"+num);
			sm.setPh(new String[]{param.getPhone()});
			queue.add(sm);
		}catch(Exception e){
			log.error("请求参数或格式错误",e);
			return "{\"state\":"+ErrorCode.ERRDATA+",info:\"请求参数或格式错误\"}";
		}
		return "{\"state\":"+ErrorCode.SUCCESS+",\"codeid\":"+num+",\"info\":\"短信验证码发送成功\"}";
	}
	
	/**
	 * 验证短信验证码接口
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/smsIsValidate", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String smsIsValidate(@RequestParam("p")String p){
		log.info("请求参数: "+p);
		try {
			//将JSON转换为对象
			requestSmsParam param = JSON.parseObject(p, requestSmsParam.class);
			//获取APPID
			String appid = String.valueOf(appids.p.get(param.getAppid()));
			if(appid.equals("") || appid.equals("null")){
				return "{\"state\":"+ErrorCode.KEYNOT+",\"info\":\"APPID应用编码验证失败\"}";
			}
			//验证手机号
			if(param.getPhone().length() < 1){
				return "{\"state\":"+ErrorCode.ERRNULL+",\"info\":\"手机号码不能为空\"}";
			}
			//短信验证码
			if(param.getCodeid().length() < 5){
				return "{\"state\":"+ErrorCode.ERRCODE+",\"info\":\"短信验证码格式不正确\"}";
			}
			//从缓存中取出验证码
			Element CacheCode = smsCache.get(param.getPhone());
			if(CacheCode == null){
				return "{\"state\":"+ErrorCode.ERRCODEGQ+",\"info\":\"短信验证码已过期\"}";
			}
			
			String codeid = String.valueOf(CacheCode.getObjectValue());
			String oldcodeid = String.valueOf(param.getCodeid()).trim();
			if (codeid.equals(oldcodeid)) {
				smsCache.remove("param.getPhone()");
				return "{\"state\":"+ErrorCode.SUCCESS+",\"info\":\"短信验证码验证成功\"}";
			}else{
				return "{\"state\":"+ErrorCode.ERRVCODEGQ+",\"info\":\"短信验证码验证失败\"}";
			}
		} catch (Exception e) {
			log.error("请求参数或格式错误",e);
			return "{\"state\":"+ErrorCode.ERRDATA+",\"info\":\"请求参数或格式错误\"}";
		}
	}
	
	
	/**
	 * 微信推送接口
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/wxPush", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String wxPush(@RequestParam("p")String p){
		log.info("请求参数: "+p);
		try {
			//将JSON转换为对象
			requestSmsParam param = JSON.parseObject(p, requestSmsParam.class);
			//订单编号
			if(param.getBid().equals("") || param.getBid().equals("null")){
				return "{\"state\":"+ErrorCode.BIDNULL+",\"info\":\"订单编号为空\"}";
			}
			
			//openid
			if(param.getOpenid().equals("") || param.getOpenid().equals("null")){
				return "{\"state\":"+ErrorCode.OPENNULL+",\"info\":\"微信openid为空\"}";
			}
			
			//商户名称
			if(param.getSellername().equals("") || param.getSellername().equals("null")){
				return "{\"state\":"+ErrorCode.SELLERNULL+",\"info\":\"商户名称或商品名称为空\"}";
			}
			
			//订单支付金额
			if(param.getMoney().equals("") || param.getMoney().equals("null")){
				return "{\"state\":"+ErrorCode.MONEYNULL+",\"info\":\"订单支付金额为空\"}";
			}
			
			//订单类型
			String type = "";
			if(param.getType().equals("") || param.getType().equals("null")){
				type = "1";
			}else{
				type = param.getType();
			}
			
			//构造发送对象
			smsSendObj sm = new smsSendObj();
			sm.setType(type);  //订单类型
			sm.setTitle(param.getTitle()); //标题
			sm.setOpenid(param.getOpenid()); //openid
			sm.setBid(param.getBid()); //订单编号
			sm.setMoney(param.getMoney());  //订单金额
			sm.setSellername(param.getSellername());  //商家名称或商品名称
			sm.setSdate(param.getSdate()); //交易时间
			sm.setRemarks(param.getRemarks());  //通知备注
			queue.add(sm);
		} catch (Exception e) {
			log.error("请求参数或格式错误",e);
			return "{\"state\":"+ErrorCode.ERRDATA+",\"info\":\"请求参数或格式错误\"}";
		}
		return "{\"state\":"+ErrorCode.SUCCESS+",\"info\":\"微信推送信息发送成功\"}";
	}
	
}
